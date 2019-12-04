package com.srans.nestserver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	/*
	 * @GetMapping("/room/sharetype/{id}") public List<Rooms>
	 * getAllFloorsByHostelid1(@PathVariable(value = "id") Long id) {
	 * 
	 * Long id1=id; return roomRepository.findByRoomType(id,id1); }
	 */
	/*@GetMapping("/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Rooms> getFloorById(@PathVariable(value = "id") Long floor_id,
			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}

		Rooms room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}*/

	/*@PostMapping("/hostels/floor/{id}/room")
	public Rooms createRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Rooms room) {
		return floorRepository.findById(id).map(floor -> {
			room.setFloor(floor);

			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));
	}
*/
	/*@PutMapping("/hostels/floor/{id}/room/{room_id}")
	public Rooms updateRoom(@PathVariable(value = "id") Long id, @PathVariable(value = "room_id") Long room_id,
			@Valid @RequestBody Rooms roomRequest) {
		if (!floorRepository.existsById(id)) {
			throw new ResourceNotFoundException("FloorId " + id + " not found");
		}

		return roomRepository.findById(room_id).map(room -> {
			room.setFacilities(roomRequest.getFacilities());
             room.setRoomstatus(roomRequest.getRoomstatus());
             room.setSharetype(roomRequest.getSharetype());
			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("floorId " + room_id + "not found"));
*/
	}

	

