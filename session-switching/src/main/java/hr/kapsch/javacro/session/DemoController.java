package hr.kapsch.javacro.session;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.session.web.http.HttpSessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@GetMapping("/")
	String sessionInfo(HttpServletRequest request, Model model) {
		HttpSessionManager sessionManager = (HttpSessionManager) request
				.getAttribute(HttpSessionManager.class.getName());
		SessionRepository<ExpiringSession> sessionRepository = (SessionRepository<ExpiringSession>) request
				.getAttribute(SessionRepository.class.getName());

		String currentSessionAlias = sessionManager.getCurrentSessionAlias(request);
		Map<String, String> sessionsIds = sessionManager.getSessionIds(request);
		String newSessionAlias = sessionManager.getNewSessionAlias(request);

		List<Session> sessions = new ArrayList<>(sessionsIds.size());

		for (Map.Entry<String, String> entry : sessionsIds.entrySet()) {
			String alias = entry.getKey();
			String sessionId = entry.getValue();

			ExpiringSession session = sessionRepository.getSession(sessionId);

			if (session == null) {
				continue;
			}

			if (alias.equals(currentSessionAlias)) {
				sessions.add(new Session(alias, session));
			}
			else {
				String link = sessionManager.encodeURL(".", alias);
				sessions.add(new Session(alias, link, session));
			}
		}

		model.addAttribute("addSessionLink", sessionManager.encodeURL("add", newSessionAlias));
		model.addAttribute("sessions", sessions);

		return "index";
	}

	@GetMapping("/add")
	String addSession(HttpSession session) {
		return "redirect:/";
	}

	static class Session {

		String alias;
		String link;
		String id;
		Date creationTime;
		Date lastAccessedTime;

		Session(String alias, String link, ExpiringSession session) {
			this.alias = alias;
			this.link = link;
			this.id = session.getId();
			this.creationTime = new Date(session.getCreationTime());
			this.lastAccessedTime = new Date(session.getLastAccessedTime());
		}

		Session(String alias, ExpiringSession session) {
			this(alias, null, session);
		}

		public String getAlias() {
			return this.alias;
		}

		public String getLink() {
			return this.link;
		}

		public String getId() {
			return this.id;
		}

		public Date getCreationTime() {
			return this.creationTime;
		}

		public Date getLastAccessedTime() {
			return this.lastAccessedTime;
		}

		public boolean isCurrent() {
			return this.link == null;
		}

	}

}
