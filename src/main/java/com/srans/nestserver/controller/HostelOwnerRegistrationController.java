package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

import com.srans.nestserver.model.HostelOwnerRegistration;
import com.srans.nestserver.repository.HostelOwnerRegistrationRepository;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class HostelOwnerRegistrationController {
	Logger logger = LoggerFactory.getLogger(HostelOwnerRegistrationController.class);
	@Autowired
	private HostelOwnerRegistrationRepository hostelownerregistrationRepository;
	
	
	@GetMapping("/hostelownerregistrations")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public List<HostelOwnerRegistration> getAllHostelOwnerRegistration() {
		return hostelownerregistrationRepository.findAll();
	}
	

	@GetMapping("/hostelownerregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public ResponseEntity<HostelOwnerRegistration> getHostelOwnerRegistrationById(@PathVariable(value = "id") Long hostelownerregistrationId)
			throws ResourceNotFoundException {
		HostelOwnerRegistration hostelOwnerRegistration= hostelownerregistrationRepository.findById(hostelownerregistrationId)
				.orElseThrow(() -> new ResourceNotFoundException("HostelOwnerRegistration not found for this id :: " + hostelownerregistrationId));
		return ResponseEntity.ok().body(hostelOwnerRegistration);
	}

	@PostMapping("/hostelownerregistrations")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public HostelOwnerRegistration createHostelOwnerRegistration(@Valid @RequestBody HostelOwnerRegistration hostelownerregistration) {
		return hostelownerregistrationRepository.save(hostelownerregistration);
	}

	@PutMapping("/hostelownerregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public ResponseEntity<HostelOwnerRegistration> updateHostelOwnerRegistration(@PathVariable(value = "id") Long hostelownerregistration_Id,
			@Valid @RequestBody HostelOwnerRegistration hostelownerDetails) throws ResourceNotFoundException {
		HostelOwnerRegistration hostelownerregistration = hostelownerregistrationRepository.findById(hostelownerregistration_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " +hostelownerregistration_Id));
		hostelownerregistration.setName(hostelownerDetails.getName());
		hostelownerregistration.setPhoneNumber(hostelownerDetails.getPhoneNumber());
		hostelownerregistration.setEmailId(hostelownerDetails.getEmailId());
		hostelownerregistration.setPassword(hostelownerDetails.getPassword());
		hostelownerregistration.setConfirmPassword(hostelownerDetails.getConfirmPassword());
		
   
		final HostelOwnerRegistration updatedHostelOwnerRegistration = hostelownerregistrationRepository.save(hostelownerregistration);
		return ResponseEntity.ok(updatedHostelOwnerRegistration);
	}

	@DeleteMapping("/hostelownerregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public Map<String, Boolean> deleteHostelOwnerRegistrartion(@PathVariable(value = "id") Long hostelownerregistrationId)
			throws ResourceNotFoundException {
		HostelOwnerRegistration hostelownerregistration = hostelownerregistrationRepository.findById(hostelownerregistrationId)
				.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + hostelownerregistrationId));

		hostelownerregistrationRepository.delete(hostelownerregistration);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}