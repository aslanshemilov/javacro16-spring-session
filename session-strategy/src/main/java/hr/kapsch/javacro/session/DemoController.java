package hr.kapsch.javacro.session;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@GetMapping("/")
	String sessionId(HttpSession session) {
		return session.getId();
	}

}
