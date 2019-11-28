package com.srans.nestserver.controller;

import java.util.List;

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
import com.srans.nestserver.model.Hostels;
import com.srans.nestserver.repository.HostelRepository;


@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class HostelController {

	@Autowired
	private HostelRepository hostelRepository;

	@GetMapping("/hostels")
	public List<Hostels> getAllPosts() {
		return hostelRepository.findAll();
	}
	
	@GetMapping("/hostels/{id}")
	public ResponseEntity<Hostels> getHostelById(@PathVariable(value = "id") Long hostelsId)
			throws ResourceNotFoundException {
		Hostels hostel = hostelRepository.findById(hostelsId)
				.orElseThrow(() -> new ResourceNotFoundException("Hostel not found for this id :: " + hostelsId));
		return ResponseEntity.ok().body(hostel);
	}

	@PostMapping("/hostels")
	public Hostels postHostel(@Valid @RequestBody Hostels hostel) {
		return hostelRepository.save(hostel);
	}

	@PutMapping("/hostels/{id}")
	public Hostels updateHostel(@PathVariable Long id, @Valid @RequestBody Hostels hostelRequest) {
		return hostelRepository.findById(id).map(hostel -> {
			
			hostel.setHostelName(hostelRequest.getHostelName());
			hostel.setHostelAddress(hostelRequest.getHostelAddress());
			hostel.setHostelType(hostelRequest.getHostelType());
			return hostelRepository.save(hostel);
		}).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id + " not found"));
	}
	
	

	@DeleteMapping("/hostels/{id}")
	public ResponseEntity<?> deleteHostel(@PathVariable Long id) {
		return hostelRepository.findById(id).map(hostel -> {
			hostelRepository.delete(hostel);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
}
