package hr.kapsch.javacro.session;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.session.ExpiringSession;
import org.springframework.session.SessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

	@Autowired
	private SessionRepository<? extends ExpiringSession> sessionRepository;

	@GetMapping("/")
	String home(HttpSession session) {
		return "index";
	}

	@MessageMapping("/messages")
	@SendTo("/topic/echo")
	Echo handle(Message message, @Header("simpSessionAttributes") Map<String, Object> attributes) {
		String sessionId = (String) attributes.get("SPRING.SESSION.ID");
		return new Echo(message, this.sessionRepository.getSession(sessionId));
	}

	static class Message {

		private String content;

		public String getContent() {
			return this.content;
		}

	}

	static class Echo {

		private String content;
		private String sessionId;
		private Date sessionCreationTime;
		private Date sessionLastAccessedTime;

		Echo(Message message, ExpiringSession session) {
			this.content = message.getContent();
			if (session != null) {
				this.sessionId = session.getId();
				this.sessionCreationTime = new Date(session.getCreationTime());
				this.sessionLastAccessedTime = new Date(session.getLastAccessedTime());
			}
		}

		public String getContent() {
			return this.content;
		}

		public String getSessionId() {
			return this.sessionId;
		}

		public Date getSessionCreationTime() {
			return this.sessionCreationTime;
		}

		public Date getSessionLastAccessedTime() {
			return this.sessionLastAccessedTime;
		}

	}

}
