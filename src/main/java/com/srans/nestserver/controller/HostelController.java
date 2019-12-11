package com.srans.nestserver.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
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
import org.springframework.web.multipart.MultipartFile;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.service.StorageService;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class HostelController {

	private Logger logger = LoggerFactory.getLogger(HostelController.class);

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private BedRepository bedRepository;

	@Autowired
	private StorageService storageService;

	@GetMapping("/hostels")
	public List<Hostel> getAllPosts() {
		return hostelRepository.findAll();
	}

	@PostMapping("/hostels")
	public Hostel saveHostel(@Valid @RequestBody Hostel hostel) throws NSException {

		logger.info("IN::POST::/hostels::saveHostel::" + hostel);

		Hostel responseHostel = hostelRepository.save(hostel);

		// SAVE Database stuff here
		
		responseHostel.getfloors().forEach(floor -> {
			floor.setHostelId(responseHostel.getId());
			Floor resFloor = floorRepository.save(floor);

			floor.getRooms().forEach(room -> {
				room.setHostelId(responseHostel.getId());
				room.setFloorId(resFloor.getId());
				Room resRoom=roomRepository.save(room);
				
				if(resRoom.getRoomType().equals("Single")) {
					
					int i;
					for(i=0;i<1;i++) {
				     room.getBeds().forEach(bed -> {
					 bed.setHostelId(responseHostel.getId());
					 bed.setFloorId(resFloor.getId());
					 bed.setRoomId(resRoom.getId());
						bedRepository.save(bed);
				 });
			    }
				 
			}
				
				else if (resRoom.getRoomType().equals("Double")) {
					int i;
					for(i=0;i<=1;i++) {
					     room.getBeds().forEach(bed -> {
						 bed.setHostelId(responseHostel.getId());
						 bed.setFloorId(resFloor.getId());
						 bed.setRoomId(resRoom.getId());
							bedRepository.save(bed);
					 
					 });
				    }
					
				}
				
				else if (resRoom.getRoomType().equals("Triple")) {
					int i;
					for(i=0;i<=2;i++) {
					     room.getBeds().forEach(bed -> {
						 bed.setHostelId(responseHostel.getId());
						 bed.setFloorId(resFloor.getId());
						 bed.setRoomId(resRoom.getId());
							bedRepository.save(bed);

					 });
				    }
					
				}
				
				else if (resRoom.getRoomType().equals("Misc")) {
					int i;
					for(i=0;i<10;i++) {
					     room.getBeds().forEach(bed -> {
						 bed.setHostelId(responseHostel.getId());
						 bed.setFloorId(resFloor.getId());
						 bed.setRoomId(resRoom.getId());
							bedRepository.save(bed);
			
					 });
				    }
					
				}
	
			});

		});
		logger.info("OUT::POST::/hostels::saveHostel::" + hostel);
		return responseHostel;
	}

	@PostMapping("/hostels/{id}/upload/{cat}")
	public void storeHostelImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/hostels/{id}/upload/{cat}::uploadHostelImages::" + id + "::" + cat);
		storageService.storeHostelImage(file, cat, id);
		logger.info("OUT::POST:://hostels/uploadImage/{cat}/{id}::uploadHostelImages::" + id + "::" + cat);

	}

	@GetMapping("/hostels/{id}/retrive/{cat}")
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retriveHostelImage(id, cat)));

	}

	@PutMapping("/hostels/{id}")
	public Hostel updateHostel(@PathVariable Long id, @Valid @RequestBody Hostel hostelRequest) {

		return hostelRepository.findById(id).map(hostel -> {

			hostel.setHostelName(hostelRequest.getHostelName());
			hostel.setHostelAddress(hostelRequest.getHostelAddress());
			hostel.setHostelType(hostelRequest.getHostelType());
			hostel.setNumOfFloors(hostelRequest.getNumOfFloors());
			return hostelRepository.save(hostel);
		}).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id + " not found"));
	}

	@DeleteMapping("/hostels/{id}")
	public ResponseEntity<?> deleteHostel(@PathVariable Long id) {
		return hostelRepository.findById(id).map(hostel -> {
			hostelRepository.delete(hostel);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id + " not found"));
	}

	@GetMapping("/hostels/{id}")
	public Hostel getHostel(@PathVariable(value = "id") Long id) {

		return hostelRepository.findById(id).orElse(null);
	}

	@GetMapping("/hostels/{id}/floor")
	public List<Floor> getAllFloorsByHostelid(@PathVariable(value = "id") Long id) {
		logger.info("All Floor Getting");
		return floorRepository.findByHostelId(id);
	}

	@GetMapping("/hostels/{id}/floor/{floor_id}")
	public ResponseEntity<Floor> getHostelById(@PathVariable(value = "id") Long hostelsId,

			@PathVariable(value = "floor_id") Long floor_id) {
		if (!hostelRepository.existsById(hostelsId)) {
			throw new ResourceNotFoundException("hostelId " + hostelsId + " not found");
		}

		Floor floor = floorRepository.findById(floor_id)
				.orElseThrow(() -> new ResourceNotFoundException("Floor not found for this Hostelid :: " + hostelsId
						+ "Floor not found for this Floor id::" + floor_id));
		return ResponseEntity.ok().body(floor);
	}

	@PostMapping("/hostels/{id}/floor")
	public Floor createFloor(@PathVariable(value = "id") Long id, @Valid @RequestBody Floor floor) {

		return hostelRepository.findById(id).map(hostel -> {
			floor.setHostelId(id);

			return floorRepository.save(floor);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));

	}

	@PutMapping("/hostels/{id}/floors/{floor_id}")
	public Floor updateFloor(@PathVariable(value = "id") Long id, @PathVariable(value = "floor_id") Long floor_id,

			@Valid @RequestBody Floor floorRequest) {
		if (!hostelRepository.existsById(id)) {
			throw new ResourceNotFoundException("hostelId " + id + " not found");
		}

		return floorRepository.findById(floor_id).map(floor -> {
			floor.setFloorName(floorRequest.getFloorName());
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
		}).orElseThrow(
				() -> new ResourceNotFoundException("Floor not found with id " + id + " and postId " + floor_id));
	}

	@GetMapping("/hostels/floor/{id}/room")
	public List<Room> getAllFloorsByHostelid1(@PathVariable(value = "id") Long id) {
		return roomRepository.findByFloorId(id);
	}

	@GetMapping("/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Room> getFloorById(@PathVariable(value = "id") Long floor_id,

			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}

		Room room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/hostels/floor/{id}/room")
	public Room createRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Room room) {
		return floorRepository.findById(id).map(floor -> {
			room.setFloorId(id);

			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("FloorId " + id + " not found"));
	}

	@PutMapping("/hostels/floor/{id}/room/{room_id}")
	public Room updateRoom(@PathVariable(value = "id") Long id, @PathVariable(value = "room_id") Long room_id,

			@Valid @RequestBody Room roomRequest) {
		if (!floorRepository.existsById(id)) {
			throw new ResourceNotFoundException("FloorId " + id + " not found");
		}

		return roomRepository.findById(room_id).map(room -> {
			room.setRoomName(roomRequest.getRoomName());
			room.setRoomRent(roomRequest.getRoomRent());
			room.setRoomType(roomRequest.getRoomType());
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

	@GetMapping("/hostels/{id}/extendingviews")
	public Map<String, Object> getTestMap(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total floors : ", hostelRepository.numOfFloor(hostelId));
		map.put("Total Rooms : ", roomRepository.countRoomByHostelId(hostelId));
		map.put("Total Single Sharing Rooms : ", roomRepository.countSingleSharing(hostelId));
		map.put("Total Double Sharing Rooms : ", roomRepository.countDoubleSharing(hostelId));
		map.put("Total Triple Sharing Rooms : ", roomRepository.countTripleSharing(hostelId));
		map.put("Total Misc. Sharing Rooms : ", roomRepository.countMiscSharing(hostelId));
		return map;
	}

	@GetMapping("hostels/getName")
	public List<String> findAll() {
		return hostelRepository.getAllHostelName();

	}

	@GetMapping("hostels/{id}/getName"
			)

	public Map<String, Object> getHostelMap(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total Floor : ", hostelRepository.numOfFloor(hostelId));
		map.put("Hostel Name ", hostelRepository.hosteName(hostelId));
		return map;
	}

	@GetMapping("hostels/{id}/getHostelType")

	public Iterable<String> findAll(@PathVariable(value = "id") Long id) {
		return hostelRepository.findType(id);
	}

}
