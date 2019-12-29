package com.srans.nestserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;


@ComponentScan({"com.srans.nestserver","controller"})
@EnableJpaAuditing
@SpringBootApplication
@EnableOAuth2Client
@EnableScheduling
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) { 
		SpringApplication.run(Application.class, args);
	}
}
