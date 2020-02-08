package com.srans.nestserver.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.SubAdminDetails;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.service.HostelService;
import com.srans.nestserver.service.NotificationService;
import com.srans.nestserver.service.StorageService;
import com.srans.nestserver.service.SubAdminService;
import com.srans.nestserver.util.ConsolidatedHostel;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class HostelController {
	private Logger logger = LoggerFactory.getLogger(HostelController.class);

	@Autowired
	private NotificationService notificationService;

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

	@Autowired
	private HostelService hostelService;


	@Autowired
	private SubAdminService adminService = new SubAdminService();

	/*
	 * @GetMapping("/hostels")
	 * 
	 * @PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	 * // @PreAuthorize("permitAll()") public List<Hostel> getAllHostels() { return
	 * hostelRepository.findAll(); }
	 */

	// Get All Consolidated Hostel Details
	@GetMapping("/hostels/consolidated-hostel-info")
	@PreAuthorize("permitAll()")

	public List<ConsolidatedHostel> getAllHostelSubscriptionDetails() {
		logger.info("get  hostel consolidated info ");

		return hostelService.getHostelDetails();
	}

	@GetMapping("/hostels/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	// @PreAuthorize("permitAll()")
	public Hostel getHostel(@PathVariable(value = "id") Long hostelId) throws IOException {
		logger.info("IN::hostels::"  + hostelId);


		Hostel responseHostel = hostelRepository.getOne(hostelId);

		floorRepository.findByHostelId(hostelId).forEach(floor -> {
			responseHostel.getFloors().add(floor);
			roomRepository.findByFloorId(floor.getId()).forEach(room -> {
				floor.getRooms().add(room);
				bedRepository.findByRoomId(room.getId()).forEach(bed -> {
					room.getBeds().add(bed);
				});
			});
		});
		logger.info("OUT::hostels::"  + hostelId);

		return responseHostel;
	}

	/**
	 * This method will save Hostel from UI
	 * 
	 * @param hostel
	 * @return
	 * @throws NSException
	 */
	@PostMapping("/hostels")
	// @PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	@PreAuthorize("permitAll()")
	public Hostel saveHostel(@Valid @RequestBody Hostel hostel) throws NSException {

		logger.info("IN::POST::/hostels::saveHostel::" + hostel);

		Hostel responseHostel = hostelRepository.save(hostel);

		// SAVE Database stuff here
		responseHostel.getFloors().forEach(floor -> {
			floor.setHostelId(responseHostel.getId());
			Floor resFloor = floorRepository.save(floor);

			floor.getRooms().forEach(room -> {
				room.setHostelId(responseHostel.getId());
				room.setFloorId(resFloor.getId());
				Room resRoom = roomRepository.save(room);
				room.getBeds().forEach(bed -> {
					bed.setHostelId(responseHostel.getId());
					bed.setFloorId(resFloor.getId());
					bed.setRoomId(resRoom.getId());
					bedRepository.save(bed);
				});

			});

		});

		// Sending notification to superadmin
		notificationService.addHostelNotifictaion(responseHostel);

		logger.info("OUT::POST::/hostels::saveHostel::" + hostel);
		return responseHostel;
	}
	
	
	
	
	
	

	// Assign Multiple hostel for multiple admin
	@PostMapping("/hostels/assign/")
	@PreAuthorize("permitAll()")
	public SubAdminDetails assignHostel(@Valid @RequestBody SubAdminDetails subAdminDetails) {
		logger.info("IN::POST::/hostels::saveHostelassign::" + subAdminDetails);


		subAdminDetails = adminService.subAdminProcess(subAdminDetails);
		logger.info("IN::OUT::/hostels::saveHostelassign::" + subAdminDetails);

		return subAdminDetails;
	}

	@PostMapping("/hostels/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeHostelImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/hostels/{id}/upload/{cat}::uploadHostelImages::" + id + "::" + cat);
		storageService.storeHostelImage(file, cat, id);
		logger.info("OUT::POST:://hostels/uploadImage/{cat}/{id}::uploadHostelImages::" + id + "::" + cat);

	}

	@GetMapping("/hostels/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {
		logger.info("IN:://hostels/{id}/retrive/{cat}::retriveHostelImages::" + id + "::" + cat);
		logger.info("OUT:://hostels/{id}/retrive/{cat}::retriveHostelImages::" + id + "::" + cat);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retriveHostelImage(id, cat)));


	}
	
	
	
	//Get Hostel Details For Admin 
	@GetMapping("/hostels")
	@PreAuthorize("permitAll()")
	public List<Hostel> getHostelsBasedOnRole(@RequestParam("id")  Long id,@RequestParam("role") String role ) {
		List<Hostel> hostelData = hostelService.getHostelsBasedOnRole(id, role);
		logger.info("get all hostel details based on role");
		return (hostelData);

	}

	@GetMapping("/hostels/admin1/{id}")
	@PreAuthorize("permitAll()")
	public Optional<Hostel> getHostelForAdmin1(@PathVariable("id") Long adminId) {
		logger.info("IN::getHostelForAdmin1::"  + adminId);

		logger.info("OUT::getHostelForAdmin1::"  + adminId);
		return hostelRepository.findById(adminId);

	}

	@PutMapping("/hostels/{id}")
	@PreAuthorize("permitAll()")
	public Hostel updateHostel(@PathVariable Long id, @Valid @RequestBody Hostel hostelRequest) {

		return hostelRepository.findById(id).map(hostel -> {
			logger.info("IN::updateHostel::"  + hostelRequest);

			hostel.setHostelName(hostelRequest.getHostelName());
			hostel.setHostelAddress(hostelRequest.getHostelAddress());
			hostel.setHostelType(hostelRequest.getHostelType());
			hostel.setNumOfFloors(hostelRequest.getNumOfFloors());
			logger.info("OUT::updateHostel::"  + hostelRequest);
			return hostelRepository.save(hostel);
		}).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id + " not found"));
	}

	@DeleteMapping("/hostels/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> deleteHostel(@PathVariable Long id) {
		logger.info("IN::deleteHostel::"  + id);

		return hostelRepository.findById(id).map(hostel -> {
			hostelRepository.delete(hostel);
			logger.info("OUT::deleteHostel::"  + id);
			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("HostelId " + id + " not found"));
	}

	@GetMapping("/hostels/{id}/floor")
	@PreAuthorize("permitAll()")
	public List<Floor> getAllFloorsByHostelid(@PathVariable(value = "id") Long id) {
		logger.info("All Floor Getting");
		return floorRepository.findByHostelId(id);
	}

	@GetMapping("/hostels/{id}/floor/{floor_id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Floor> getHostelById(@PathVariable(value = "id") Long hostelsId,
			@PathVariable(value = "floor_id") Long floor_id) {
		logger.info("IN::getHostelById::"  + hostelsId);

		if (!hostelRepository.existsById(hostelsId)) {
			throw new ResourceNotFoundException("hostelId " + hostelsId + " not found");
		}

		Floor floor = floorRepository.findById(floor_id)
				.orElseThrow(() -> new ResourceNotFoundException("Floor not found for this Hostelid :: " + hostelsId
						+ "Floor not found for this Floor id::" + floor_id));
		logger.info("OUT::getHostelById::"  + hostelsId);

		return ResponseEntity.ok().body(floor);
	}

	@PostMapping("/hostels/{id}/floor")
	@PreAuthorize("permitAll()")
	public Floor createFloor(@PathVariable(value = "id") Long id, @Valid @RequestBody Floor floor) {
		logger.info("IN::POST::/hostels::createFloor::" + id);

		return hostelRepository.findById(id).map(hostel -> {
			floor.setHostelId(id);
			logger.info("OUT::POST::/hostels::createFloor::" + id);

			return floorRepository.save(floor);
		}).orElseThrow(() -> new ResourceNotFoundException("PostId " + id + " not found"));

	}

	@PutMapping("/hostels/{id}/floors/{floor_id}")
	@PreAuthorize("permitAll()")
	public Floor updateFloor(@PathVariable(value = "id") Long id, @PathVariable(value = "floor_id") Long floor_id,

			@Valid @RequestBody Floor floorRequest) {
		if (!hostelRepository.existsById(id)) {
			throw new ResourceNotFoundException("hostelId " + id + " not found");
		}
		logger.info("IN::POST::/hostels::updateFloor::" + floor_id);
		
		return floorRepository.findById(floor_id).map(floor -> {
			floor.setFloorName(floorRequest.getFloorName());
			floor.setDescription(floorRequest.getDescription());
			logger.info("OUT::POST::/hostels::updateFloor::" + floor_id);
			return floorRepository.save(floor);
		}).orElseThrow(() -> new ResourceNotFoundException("floorId " + floor_id + "not found"));

	}

	@DeleteMapping("/hostels/{id}/floors/{floor_id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> deleteFloor(@PathVariable(value = "id") Long id,

			@PathVariable(value = "floor_id") Long floor_id) {
		return floorRepository.findByIdAndHostelId(floor_id, id).map(floor -> {
			logger.info("IN::POST::/hostels::findByIdAndHostelId::" + id);

			floorRepository.delete(floor);
			logger.info("OUT::POST::/hostels::findByIdAndHostelId::" + id);
			return ResponseEntity.ok().build();
		}).orElseThrow(
				() -> new ResourceNotFoundException("Floor not found with id " + id + " and postId " + floor_id));
	}

	@GetMapping("/hostels/floor/{id}/room")
	@PreAuthorize("permitAll()")
	public List<Room> getAllFloorsByHostelid1(@PathVariable(value = "id") Long id) {
		logger.info("IN::getAllFloorsByHostelid1::"  + id);
		logger.info("OUT::getAllFloorsByHostelid1::"  + id);
		return roomRepository.findByFloorId(id);
	}

	@GetMapping("/hostels/floor/{id}/room/{room_id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Room> getFloorById(@PathVariable(value = "id") Long floor_id,

			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}
		logger.info("IN::getFloorById::"  + floor_id);
		logger.info("OUT::getFloorById::"  + floor_id);

		Room room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/hostels/floor/{id}/room")
	@PreAuthorize("permitAll()")
	public Room createRoom(@PathVariable(value = "id") Long id, @Valid @RequestBody Room room) {
		return floorRepository.findById(id).map(floor -> {
			logger.info("IN::POST::/createRoom::" + room);

			room.setFloorId(id);
			logger.info("OUT::POST::/createRoom::" + room);
			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("FloorId " + id + " not found"));
	}

	@PutMapping("/hostels/floor/{id}/room/{room_id}")
	@PreAuthorize("permitAll()")
	public Room updateRoom(@PathVariable(value = "id") Long id, @PathVariable(value = "room_id") Long room_id,

			@Valid @RequestBody Room roomRequest) {
		if (!floorRepository.existsById(id)) {
			throw new ResourceNotFoundException("FloorId " + id + " not found");
		}
		logger.info("IN::POST::/updateRoom::" + room_id);
		return roomRepository.findById(room_id).map(room -> {
			room.setRoomName(roomRequest.getRoomName());
			room.setRoomRent(roomRequest.getRoomRent());
			room.setRoomType(roomRequest.getRoomType());
			logger.info("OUT::POST::/updateRoom::" + room_id);
			return roomRepository.save(room);
		}).orElseThrow(() -> new ResourceNotFoundException("floorId " + room_id + "not found"));

	}

	@DeleteMapping("/hostels/floor/room/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> deleteRoom(@PathVariable Long id) {
		return roomRepository.findById(id).map(hostel -> {
			logger.info("IN::POST::/deleteRoom::" + id);

			roomRepository.delete(hostel);
			logger.info("OUT::POST::/deleteRoom::" + id);

			return ResponseEntity.ok().build();
		}).orElseThrow(() -> new ResourceNotFoundException("roomId " + id + " not found"));
	}

	@GetMapping("/hostels/{id}/extendingviews")
	@PreAuthorize("permitAll()")
	public List<Object> getTestMap(@PathVariable(value = "id") Long hostelId) {
		logger.info("IN::getTestMap::"  + hostelId);
		logger.info("OUT::getTestMap::"  + hostelId);

		return Arrays.asList(hostelRepository.numOfFloor(hostelId), roomRepository.countRoomByHostelId(hostelId),
				roomRepository.countSingleSharing(hostelId), roomRepository.countDoubleSharing(hostelId),
				roomRepository.countTripleSharing(hostelId), roomRepository.countMiscSharing(hostelId));
	}

}
