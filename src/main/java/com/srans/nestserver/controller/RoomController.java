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
import com.srans.nestserver.model.Rooms;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class RoomController {

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private RoomRepository roomRepository;

	@GetMapping("/hostels/floor/{id}/room")
	public List<Rooms> getAllFloorsByHostelid(@PathVariable(value = "id") Long id) {
		return roomRepository.findByFloorId(id);
	}

	@GetMapping("/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Rooms> getFloorById(@PathVariable(value = "id") Long floor_id,
			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}

		Rooms room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/hostels/floor/{id}/room")
	public Rooms createRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Rooms room) {
		return floorRepository.findById(id).map(floor -> {
			room.setFloor(floor);

			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}

	@PutMapping("/hostels/floor/{id}/room/{room_id}")
	public Rooms updateRoom(@PathVariable(value = "id") Long id, @PathVariable(value = "room_id") Long room_id,
			@Valid @RequestBody Rooms roomRequest) {
		if (!floorRepository.existsById(id)) {
			throw new ResourceNotFoundException("FloorId " + id + " not found");
		}

		return roomRepository.findById(room_id).map(room -> {
			room.setFacilities(roomRequest.getFacilities());
			room.setDescription(roomRequest.getDescription());

			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("floorId " + room_id + "not found"));

	}

	@DeleteMapping("/hostels/floor/room/{id}")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
		return roomRepository.findById(id).map(hostel -> {
			roomRepository.delete(hostel);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("roomId " + id + " not found"));
	}

}
