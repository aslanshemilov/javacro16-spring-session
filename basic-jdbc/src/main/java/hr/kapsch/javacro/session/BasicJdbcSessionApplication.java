package hr.kapsch.javacro.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;

@SpringBootApplication
@EnableJdbcHttpSession
public class BasicJdbcSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicJdbcSessionApplication.class, args);
	}

}
