package com.pizza.spring_pizzeria;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
@EnableJpaAuditing
public class SpringPizzeriaApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringPizzeriaApplication.class, args);
	}

}
