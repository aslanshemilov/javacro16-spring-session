package hr.kapsch.javacro;

import com.hazelcast.config.Config;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.session.hazelcast.config.annotation.web.http.EnableHazelcastHttpSession;

@SpringBootApplication
@EnableHazelcastHttpSession
public class BasicHazelcastSessionApplication {

	public static void main(String[] args) {
		SpringApplication.run(BasicHazelcastSessionApplication.class, args);
	}

	@Bean
	Config config() {
		return new Config();
	}

}
