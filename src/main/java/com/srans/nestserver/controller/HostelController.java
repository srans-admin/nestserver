package com.srans.nestserver.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

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
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.repository.HostelRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class HostelController {
	@Autowired
	private HostelRepository hostelRepository;

	@GetMapping("/hostels")
	public List<Hostel> getAllHostels() {
		return hostelRepository.findAll();
	}

	@GetMapping("/hostels/{id}")
	public ResponseEntity<Hostel> getHostelById(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Hostel hostel = hostelRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found for this id :: " + employeeId));
		return ResponseEntity.ok().body(hostel);
	}

	@PostMapping("/hostels")
	public Hostel createHostel(@Valid @RequestBody Hostel hostel) {
		return hostelRepository.save(hostel);
	}

	@PutMapping("/hostels/{id}")
	public ResponseEntity<Hostel> updateHostel(@PathVariable(value = "id") Long employeeId,
			@Valid @RequestBody Hostel employeeDetails) throws ResourceNotFoundException {
		Hostel hostel = hostelRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found for this id :: " + employeeId));

		hostel.setEmailId(employeeDetails.getEmailId());
		hostel.setLastName(employeeDetails.getLastName());
		hostel.setFirstName(employeeDetails.getFirstName());
		final Hostel updatedHostel = hostelRepository.save(hostel);
		return ResponseEntity.ok(updatedHostel);
	}

	@DeleteMapping("/hostels/{id}")
	public Map<String, Boolean> deleteHostel(@PathVariable(value = "id") Long employeeId)
			throws ResourceNotFoundException {
		Hostel hostel = hostelRepository.findById(employeeId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found for this id :: " + employeeId));

		hostelRepository.delete(hostel);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
