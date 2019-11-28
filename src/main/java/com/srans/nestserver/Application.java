package com.srans.nestserver;

import java.io.File;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.srans.nestserver.controller.FileUploadController;


@ComponentScan({"com.srans.nestserver","controller"})
@EnableJpaAuditing
@SpringBootApplication
public class Application extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		new File(FileUploadController.uploadDirectory).mkdir();
		
		SpringApplication.run(Application.class, args);
	}
}
