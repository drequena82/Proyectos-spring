package io.spring.billing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class SpringDataJpaProyectApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringDataJpaProyectApplication.class, args);
	}
}
