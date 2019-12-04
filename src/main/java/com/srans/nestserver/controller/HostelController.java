package com.srans.nestserver.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class HostelController {

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	FloorRepository floorRepository;

	@Autowired
	RoomRepository roomRepository;

	/*
	 * @GetMapping("/hostels") public List<Hostels> getAllPosts() { return
	 * hostelRepository.findAll(); }
	 * 
	 * @GetMapping("/hostels/{id}") public ResponseEntity<Hostels>
	 * getHostelById(@PathVariable(value = "id") Long hostelsId) throws
	 * ResourceNotFoundException { Hostels hostel =
	 * hostelRepository.findById(hostelsId) .orElseThrow(() -> new
	 * ResourceNotFoundException("Hostel not found for this id :: " + hostelsId));
	 * return ResponseEntity.ok().body(hostel); }
	 * 
	 * @PostMapping("/hostels") public Hostels postHostel(@Valid @RequestBody
	 * Hostels hostel) { return hostelRepository.save(hostel); }
	 * 
	 * @PutMapping("/hostels/{id}") public Hostels updateHostel(@PathVariable Long
	 * id, @Valid @RequestBody Hostels hostelRequest) { return
	 * hostelRepository.findById(id).map(hostel -> {
	 * 
	 * hostel.setHostelName(hostelRequest.getHostelName());
	 * hostel.setHostelAddress(hostelRequest.getHostelAddress());
	 * hostel.setHostelType(hostelRequest.getHostelType()); return
	 * hostelRepository.save(hostel); }).orElseThrow(() -> new
	 * ResourceNotFoundException("HostelId " + id + " not found")); }
	 * 
	 * 
	 * 
	 * @DeleteMapping("/hostels/{id}") public ResponseEntity<?>
	 * deleteHostel(@PathVariable Long id) { return
	 * hostelRepository.findById(id).map(hostel -> {
	 * hostelRepository.delete(hostel); return ResponseEntity.ok().build();
	 * }).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id +
	 * " not found")); }
	 * 
	 * 
	 * 
	 * Floor Controller
	 * 
	 * 
	 * @GetMapping("/hostels/{id}/floor") public List<Floor>
	 * getAllFloorsByHostelid(@PathVariable(value = "id") Long id) { return
	 * floorRepository.findByHostelId(id); }
	 * 
	 * @GetMapping("/hostels/{id}/floor/{floor_id}") public ResponseEntity<Floor>
	 * getHostelById(@PathVariable(value = "id") Long hostelsId, @PathVariable(value
	 * = "floor_id")Long floor_id) { if (!hostelRepository.existsById(hostelsId)) {
	 * throw new ResourceNotFoundException("hostelId " + hostelsId + " not found");
	 * }
	 * 
	 * Floor floor = floorRepository.findById(floor_id) .orElseThrow(() -> new
	 * ResourceNotFoundException("Floor not found for this Hostelid :: " +
	 * hostelsId+"Floor not found for this Floor id::" +floor_id)); return
	 * ResponseEntity.ok().body(floor); }
	 * 
	 * 
	 * @PostMapping("/hostels/{id}/floor") public Floor
	 * createFloor(@PathVariable(value = "id") Long id, @Valid @RequestBody Floor
	 * floor) { return hostelRepository.findById(id).map(hostel -> {
	 * floor.setHostel(hostel);
	 * 
	 * return floorRepository.save(floor); }).orElseThrow(() -> new
	 * ResourceNotFoundException("PostId " + id + " not found")); }
	 * 
	 * @PutMapping("/hostels/{id}/floors/{floor_id}") public Floor
	 * updateFloor(@PathVariable(value = "id") Long id, @PathVariable(value =
	 * "floor_id") Long floor_id,
	 * 
	 * @Valid @RequestBody Floor floorRequest) { if
	 * (!hostelRepository.existsById(id)) { throw new
	 * ResourceNotFoundException("hostelId " + id + " not found"); }
	 * 
	 * return floorRepository.findById(floor_id).map(floor -> {
	 * floor.setFloor_name(floorRequest.getFloor_name());
	 * floor.setDescription(floorRequest.getDescription()); return
	 * floorRepository.save(floor); }).orElseThrow(() -> new
	 * ResourceNotFoundException("floorId " + floor_id + "not found"));
	 * 
	 * }
	 * 
	 * 
	 * @DeleteMapping("/hostels/{id}/floors/{floor_id}") public ResponseEntity<?>
	 * deleteFloor(@PathVariable(value = "id") Long id,
	 * 
	 * @PathVariable(value = "floor_id") Long floor_id) { return
	 * floorRepository.findByIdAndHostelId(floor_id, id).map(floor -> {
	 * floorRepository.delete(floor); return ResponseEntity.ok().build();
	 * }).orElseThrow(()->new ResourceNotFoundException("Floor not found with id " +
	 * id + " and postId " + floor_id) ); }
	 * 
	 * 
	 * 
	 * 
	 * 
	 * 
	 * Room Controller
	 * 
	 * 
	 * 
	 * 
	 * @GetMapping("/hostels/floor/{id}/room") public List<Rooms>
	 * getAllFloorsByHostelid1(@PathVariable(value = "id") Long id) { return
	 * roomRepository.findByFloorId(id); }
	 * 
	 * @GetMapping("/hostels/floor/{id}/room/{room_id}") public
	 * ResponseEntity<Rooms> getFloorById(@PathVariable(value = "id") Long floor_id,
	 * 
	 * @PathVariable(value = "room_id") Long room_id) { if
	 * (!floorRepository.existsById(floor_id)) { throw new
	 * ResourceNotFoundException("FloorId " + floor_id + " not found"); }
	 * 
	 * Rooms room = roomRepository.findById(room_id).orElseThrow(() -> new
	 * ResourceNotFoundException( "Floor not found for this Floorid :: " + floor_id
	 * + "Floor not found for this Floor id::" + room_id)); return
	 * ResponseEntity.ok().body(room); }
	 * 
	 * 
	 * @PostMapping("/hostels/floor/{id}/room") public Rooms
	 * createRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Rooms
	 * room) { return floorRepository.findById(id).map(floor -> {
	 * room.setFloor(floor);
	 * 
	 * return roomRepository.save(room); }).orElseThrow(() -> new
	 * ResourceNotFoundException("FloorId " + id + " not found")); }
	 * 
	 * 
	 * @PutMapping("/hostels/floor/{id}/room/{room_id}") public Rooms
	 * updateRoom(@PathVariable(value = "id") Long id, @PathVariable(value =
	 * "room_id") Long room_id,
	 * 
	 * @Valid @RequestBody Rooms roomRequest) { if (!floorRepository.existsById(id))
	 * { throw new ResourceNotFoundException("FloorId " + id + " not found"); }
	 * 
	 * return roomRepository.findById(room_id).map(room -> {
	 * room.setFacilities(roomRequest.getFacilities());
	 * room.setSharetype(roomRequest.getSharetype()); return
	 * roomRepository.save(room); }).orElseThrow(() -> new
	 * ResourceNotFoundException("floorId " + room_id + "not found"));
	 * 
	 * }
	 * 
	 * 
	 * @DeleteMapping("/hostels/floor/room/{id}") public ResponseEntity<?>
	 * deleteRoom(@PathVariable Long id) { return
	 * roomRepository.findById(id).map(hostel -> { roomRepository.delete(hostel);
	 * return ResponseEntity.ok().build(); }).orElseThrow(() -> new
	 * ResourceNotFoundException("roomId " + id + " not found")); }
	 */

	@PostMapping("/hostels")
	public Hostel saveHostel(@Valid @RequestBody Hostel hostel) {
		
		return hostelRepository.saveWholeObject(hostel);
		//return  hostelRepository.save(hostel);
	}

}
