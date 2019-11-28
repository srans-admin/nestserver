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
import com.srans.nestserver.model.Floors;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class FloorController {
	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@GetMapping("/hostels/{id}/floor")
	public List<Floors> getAllFloorsByHostelid(@PathVariable(value = "id") Long id) {
		return floorRepository.findByHostelId(id);
	}

	@PostMapping("/hostels/{id}/floor")
	public Floors createFloor(@PathVariable(value = "id") Long id, @Valid @RequestBody Floors floor) {
		return hostelRepository.findById(id).map(hostel -> {
			floor.setHostel(hostel);

			return floorRepository.save(floor);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}

	@PutMapping("/hostels/{id}/floors/{floor_id}")
	public Floors updateFloor(@PathVariable(value = "id") Long id, @PathVariable(value = "floor_id") Long floor_id,
			@Valid @RequestBody Floors floorRequest) {
		if (!hostelRepository.existsById(id)) {
			throw new ResourceNotFoundException("hostelId " + id + " not found");
		}

		return floorRepository.findById(floor_id).map(floor -> {
			floor.setFloor_name(floorRequest.getFloor_name());
			floor.setDescription(floorRequest.getDescription());
			return floorRepository.save(floor);
		}).orElseThrow(() -> new ResourceNotFoundException("floorId " + floor_id + "not found"));

	}
	

	@DeleteMapping("/hostels/{id}/floors/{floor_id}")
	public ResponseEntity<?> deleteFloor(@PathVariable(value = "id") Long id,
			@PathVariable(value = "floor_id") Long floor_id) {
		return floorRepository.findByIdAndHostelId(floor_id, id).map(floor -> {
			floorRepository.delete(floor);
			return ResponseEntity.ok().build();
		}).orElseThrow(()->new ResourceNotFoundException("Floor not found with id " + id + " and postId " + floor_id) );
	}

}
