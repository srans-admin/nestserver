package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.SransUser;
import com.srans.nestserver.repository.SransUserRepository;


@CrossOrigin(origins = "http://localhost:4200") 
@RestController
@RequestMapping("/api/v1")
public class SransUserController {
	
	//Logger logger = LoggerFactory.getLogger(SransUserController.class);
	
	@Autowired
	private SransUserRepository   sransuserRepository;

	@GetMapping("/sransuser")
	public List<SransUser> getAllSransUsers() {
		return sransuserRepository.findAll();
	}

	@GetMapping("/sransuser/{Id}")
	public ResponseEntity<SransUser> getSransUserById(@PathVariable(value = "Id") Long SransUserId)
			throws ResourceNotFoundException {
		SransUser sransUser = sransuserRepository .findById(SransUserId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this Id :: " + SransUserId));
		return ResponseEntity.ok().body(sransUser);
	}

	@PostMapping("/sransuser")
	public SransUser createUser(@RequestBody SransUser sransUser) {
		System.out.println("User : "+sransUser);
		return sransuserRepository.save(sransUser);
	}

	@PutMapping("/sransuser/{Id}")
	public ResponseEntity<SransUser> updateUser(@PathVariable(value = "Id") Long SransUserId,
			@Valid @RequestBody SransUser sransuserDetails) throws ResourceNotFoundException {
		SransUser sransUser = sransuserRepository.findById(SransUserId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this Id :: " +SransUserId));

		sransUser.setUserId(sransUser.getUserId());
		sransUser.setName(sransUser.getName());
		sransUser.setEmailId(sransUser.getEmailId());
		sransUser.setDob(sransUser.getDob());
		sransUser.setPhoneNumber(sransUser.getPhoneNumber());
		final SransUser updatedSransUser = sransuserRepository.save(sransUser);
		return ResponseEntity.ok(updatedSransUser);
	}

	@DeleteMapping("/sransuser/{Id}")
	public <sransuserRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "Id") Long SransUserId)
			throws ResourceNotFoundException {
		SransUser sransUser = sransuserRepository . findById(SransUserId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this id :: " + SransUserId));

		sransuserRepository.delete(sransUser);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
