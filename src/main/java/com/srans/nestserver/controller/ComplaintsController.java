package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Complaints;


@CrossOrigin(origins = "*",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class ComplaintsController {
	Logger logger = LoggerFactory.getLogger(RolesController.class);
	@Autowired
	private ComplaintsRepository complaintsRepository;
	
	
	@GetMapping("/complaints")
	public List<Complaints> getAllComplaints() {
		return complaintsRepository.findAll();
	}
	

	@GetMapping("/complaints/{id}")
	public ResponseEntity<Complaints> getComplaintsById(@PathVariable(value = "id") Long complaintsId)
			throws ResourceNotFoundException {
		Complaints complaints = complaintsRepository.findById(complaintsId)
				.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));
		return ResponseEntity.ok().body(complaints);
	}

	@PostMapping("/complaints")
	public Complaints createComplaints(@Valid @RequestBody Complaints complaints) {
		return complaintsRepository.save(complaints);
	}

	@PutMapping("/complaints/{id}")
	public ResponseEntity<Complaints> updateComplaints(@PathVariable(value = "id") Long complaints_Id,
			@Valid @RequestBody Complaints complaintsDetails) throws ResourceNotFoundException {
		Complaints complaints = complaintsRepository.findById(complaints_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " +complaints_Id));
		complaints.setRoleName(complaintsDetails.getRoleName());
   complaints.setDescripition(complaintsDetails.getDescripition());
   complaints.setName(complaintsDetails.getName());
   complaints.setId(complaintsDetails.getId());
   complaints.setPhoneNumber(complaintsDetails.getPhoneNumber());
   complaints.setEmailId(complaintsDetails.getEmailId());
   
		final Complaints updatedComplaints = complaintsRepository.save(complaints);
		return ResponseEntity.ok(updatedComplaints);
	}

	@DeleteMapping("/complaints/{id}")
	public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long complaintsId)
			throws ResourceNotFoundException {
		Complaints complaints = complaintsRepository.findById(complaintsId)
				.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));

		complaintsRepository.delete(complaints);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}