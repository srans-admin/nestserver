package com.srans.nestserver.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.User;
import com.srans.nestserver.service.UserService;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class RegistrationController {

	Logger logger = LoggerFactory.getLogger(RegistrationController.class); 
 
	@Autowired
	private UserService userService = new UserService();

	
	@PostMapping("/registration")
	//@PreAuthorize("permitAll()")
	public User saveUser(@Valid @RequestBody User user) throws NSException {

		logger.info("IN::POST::/users::saveUser::" + user);
         
		user = userService.processUser(user);

		logger.info("OUT::POST::/users::saveUser::" + user);
		return user;
	}
}