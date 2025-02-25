package com.revature.P1DemoBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.P1DemoBackend.model") //tells spring to scan this package for DB entity
@ComponentScan("com.revature.P1DemoBackend") //tells spring to scan this package for beans
@EnableJpaRepositories("com.revature.P1DemoBackend.data") //tells spring to enable Jpa repository
public class P1DemoBackendApplication {
	public static void main(String[] args) {
		SpringApplication.run(P1DemoBackendApplication.class, args);
		System.out.println("Our application is running!");
	}

}
