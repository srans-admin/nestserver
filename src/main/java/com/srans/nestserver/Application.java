package com.srans.nestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@ComponentScan({"com.srans.nestserver","controller"})
@EnableJpaAuditing
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
}
