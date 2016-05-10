package hr.kapsch.javacro.session;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;

@SpringBootApplication
@EnableRedisHttpSession
public class SessionStrategyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SessionStrategyApplication.class, args);
	}

	@Bean
	HeaderHttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}

}
