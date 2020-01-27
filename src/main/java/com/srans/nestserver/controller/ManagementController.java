package com.srans.nestserver.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Management;
import com.srans.nestserver.repository.ManagementRepository;





@CrossOrigin(origins = "*",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class ManagementController {
	Logger logger = LoggerFactory.getLogger(ManagementController.class);
	@Autowired
	private ManagementRepository managementRepository;
	
	@GetMapping("/management")
	public List<Management> getAllManagement() {
		logger.info("get all management details");

		return managementRepository.findAll();
	}

	@GetMapping("/management/{id}")
	public ResponseEntity<Management> getManagementById(@PathVariable(value = "id") Long managementId)
			throws ResourceNotFoundException {
		logger.info("IN::getManagementById::" + managementId);

		Management management = managementRepository.findById(managementId)
				.orElseThrow(() -> new ResourceNotFoundException("management not found for this id :: " + managementId));
		logger.info("OUT::getManagementById::" + managementId);
		return ResponseEntity.ok().body(management);
	}

	@PostMapping("/management")
	public Management createManagement(@Valid @RequestBody Management management) {

		logger.info("IN::POST::/management::saveManagement::" + management);

		management = managementRepository.save(management);

		logger.info("OUT::POST::/management::saveManagement::" + management);

		return management;
	}

	@PutMapping("/management/{id}")
	public ResponseEntity<Management> updateManagement(@PathVariable(value = "id") Long managementid,
			@Valid @RequestBody Management managementDetails) throws ResourceNotFoundException {
		logger.info("IN::POST::/management::updateManagement::" + managementid);

		Management management = managementRepository.findById(managementid)
				.orElseThrow(() -> new ResourceNotFoundException("Management not found for this id :: " +managementid));

		management.setHostelName(managementDetails.getHostelName());
		management.setName(managementDetails.getName());
		management.setRoleName(managementDetails.getRoleName());
		final Management updatedManagement = managementRepository.save(management);
		logger.info("OUT::POST::/management::updateManagement::" + managementid);
		return ResponseEntity.ok(updatedManagement);
	}

	@DeleteMapping("/management/{id}")
	public Map<String, Boolean> deleteManagement(@PathVariable(value = "id") Long managementId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/management::deleteManagement::" + managementId);

		Management management = managementRepository.findById(managementId)
				.orElseThrow(() -> new ResourceNotFoundException("management not found for this id :: " + managementId));

		managementRepository.delete(management);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/management::deleteManagement::" + managementId);
		return response;
	}
}
