package com.srans.nestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;


@ComponentScan({"com.srans.nestserver","controller"})
@EnableJpaAuditing
@SpringBootApplication
//@EnableScheduling

public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
}
