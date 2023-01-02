package com.solution.recipetalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RecipetalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecipetalkApplication.class, args);
	}

}
