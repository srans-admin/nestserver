package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Contact;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ContactsController {
	private Logger logger = LoggerFactory.getLogger(ContactsController.class);
	
	
	@GetMapping("/contacts")
	@PreAuthorize("permitAll()")
	public List<Contact> getAll() {
		logger.info("get all important contacts");
		
		//TODO Need to implement these..
		List<Contact> contacts = new ArrayList<>();
		
		contacts.add(new Contact("Mohamohed", "9234596666", "reply@srans.in", "Hostel Owner"));
		contacts.add(new Contact("John", "9234596666", "reply@srans.in", "Hostel Gaint"));
		contacts.add(new Contact("Kahim", "97979797979", "repl3y@srans.in", "Hostel Owner"));
		contacts.add(new Contact("Naren", "9234596666", "reply2@srans.in", "Hostel Admin"));
		contacts.add(new Contact("Jagan", "79797666767", "reply@srans.in", "Hostel Owner"));
		contacts.add(new Contact("Chandrababu", "89797976666", "re4ply@srans.in", "Hostel King"));
		
		return  contacts;
	}

}
