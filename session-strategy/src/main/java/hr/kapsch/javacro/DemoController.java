package hr.kapsch.javacro;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/")
	Session sessionId(HttpSession session) {
		return new Session(session);
	}

	static class Session {

		private String id;
		private Date creationTime;
		private Date lastAccessedTime;

		Session(HttpSession session) {
			this.id = session.getId();
			this.creationTime = new Date(session.getCreationTime());
			this.lastAccessedTime = new Date(session.getLastAccessedTime());
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

	}

}
