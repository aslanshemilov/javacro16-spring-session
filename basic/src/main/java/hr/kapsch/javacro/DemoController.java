package hr.kapsch.javacro;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	private static final String TEMPLATE =
			"<table border=\"1\">" +
			"<tr><th>id</th><th>creation time</th><th>last accessed time</th></tr>" +
			"<tr><td>%s</td><td>%s</td><td>%s</td></tr>" +
			"</table>";

	@GetMapping("/")
	String sessionInfo(HttpSession session) {
		return String.format(TEMPLATE, session.getId(),
				new Date(session.getCreationTime()), new Date(session.getLastAccessedTime()));
	}

}
