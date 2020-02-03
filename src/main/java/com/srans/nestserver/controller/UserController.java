 
package com.srans.nestserver.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.hibernate.usertype.UserType;
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
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.service.BedAvailabilityService;
import com.srans.nestserver.service.StorageService;
import com.srans.nestserver.service.UserService;
import com.srans.nestserver.util.AvailableBedsUtil;
import com.srans.nestserver.util.NSConstants;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private StorageService storageService;

	@Autowired
	private UserService userService = new UserService();

	@Autowired
	private TenantBookRepository tenantBookingRepo;

	@Autowired
	private BedAvailabilityService bedAvailabilityService = new BedAvailabilityService();

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private RoomRepository roomRepo;

	@Autowired
	private BedRepository bedRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private FloorRepository floorRepo;

	@PostMapping("/users")
	@PreAuthorize("permitAll()")
	public User user(@Valid @RequestBody User user ) throws NSException {

		logger.info("IN::POST::/users::user::" + user);

		user = userService.processUser(user);

		logger.info("OUT::POST::/users::user::" + user);
		return user;
	}

	@GetMapping("/users")
	// @PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	@PreAuthorize("permitAll()")
	public List<User> getAllTenants(@RequestParam("adminId") Long adminId, @RequestParam("type") String type) {
		logger.info("IN::getAllTenants::" + adminId + "::" + type);

		List<User> users = null;

		if (type != null && type.equalsIgnoreCase(NSConstants.ROLE_TENANT)) {
			users = userRepository.getUsersForAdmin(adminId, type);
			logger.info("IN::getAllTenants::" + adminId);
		} else if (type != null && type.equalsIgnoreCase(NSConstants.ROLE_GUEST)) {
			users = userRepository.getUsersByRole(NSConstants.ROLE_GUEST);

		}
		logger.info("OUT::getAllTenants::" + adminId);

		return users;
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public User getTenantById(@PathVariable(value = "id") Long tenantId) throws ResourceNotFoundException {
		logger.info("IN::getTenantById::" + tenantId);
		User user = userRepository.getOne(tenantId);
		TenantBooking tenantBookingInfo = tenantBookingRepo.getTenantBookedInfoForUser(tenantId);
		Hostel hostel=hostelRepo.getOne(tenantBookingInfo.getHostelId());
		tenantBookingInfo.setHostelName(hostel.getHostelName());
		Floor floor=floorRepo.getOne(tenantBookingInfo.getFloorId());
		tenantBookingInfo.setFloorName(floor.getFloorName());
	    Room room=roomRepo.getOne(tenantBookingInfo.getRoomId());
	    tenantBookingInfo.setRoomName(room.getRoomName());
	    tenantBookingInfo.setSharing(room.getRoomType());
        user.setPayment(paymentRepo.getPaymentByUserId(tenantId));
		user.setTenantBooking(tenantBookingInfo);
		logger.info("OUT::getTenantById::" + tenantId);
		return user;
	}

	@GetMapping("/users/byname/{name}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> getTenantByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		logger.info("IN::getTenantByName::" + name);

		User user = userRepository.findByName(name);
		logger.info("OUT::getTenantByName::" + name);
		return ResponseEntity.ok().body(user);
	}

	@PostMapping("/users/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeTenantImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("IN::POST::/users/{id}/upload/{cat}::uploadTenantImages::" + id + "::" + cat);
		storageService.storeTenantImage(file, cat, id);
		logger.info("OUT::POST:://users/uploadImage/{cat}/{id}::uploadTenantImages::" + id + "::" + cat);

	}

	@GetMapping("/users/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {
		logger.info("IN::POST::/users/{id}/retrive/{cat}::retriveHostelImage::" + id + "::" + cat);
		logger.info("OUT::POST::/users/{id}/retrive/{cat}::retriveHostelImage::" + id + "::" + cat);

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retrivetenantImage(id, cat)));

	}

	@GetMapping("/usersidproof/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveIdproofImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {
		logger.info("In::POST::/usersidproof/{id}/retrive/{cat}::retriveIdproofImage::" + id + "::" + cat);
		logger.info("OUT::POST::/usersidproof/{id}/retrive/{cat}::retriveIdproofImage::" + id + "::" + cat);
		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retriveIdproofImage(id, cat)));

	}

	@PostMapping("/usersidproof/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeIdproofImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/users/{id}/upload/{cat}::uploadIdproofImages::" + id + "::" + cat);
		storageService.storeIdproofImage(file, cat, id);
		logger.info("OUT::POST:://users/uploadImage/{cat}/{id}::uploadIdproofImage::" + id + "::" + cat);

	}

	// API for available beds to guest
	@GetMapping("/users/guest-reserve-bed/{hostelId}")
	@PreAuthorize("permitAll()")
	public List<AvailableBedsUtil> getAvailableBed(@PathVariable("hostelId") Long hostelId) throws NSException {
		logger.info("IN::getAvailableBed::" + hostelId);
		logger.info("OUT::getAvailableBed::" + hostelId);
		return bedAvailabilityService.getAllAvailableBed(hostelId);

	}

	// API for book the bed by guest

	@PostMapping("/users/bed-booking")
	@PreAuthorize("permitAll()")
	public TenantBooking postBedBookingDetails(@Valid @RequestBody TenantBooking tenantBooking) throws NSException {
		logger.info("IN::POST::/users::postBedBookingDetails::" + tenantBooking);
		logger.info("OUT::POST::/users::postBedBookingDetails::" + tenantBooking);

		return bedAvailabilityService.saveBookedBedDetails(tenantBooking);
	}

	// API for save amount who's payment by guest for booked bed
	@PostMapping("/users/guest-payment")
	@PreAuthorize("permitAll()")
	public Payment postSaveAmountDetails(@Valid @RequestBody Payment payment) throws NSException {
		logger.info("IN::POST::/users::postSaveAmountDetails::" + payment);
		logger.info("OUT::POST::/users::postSaveAmountDetails::" + payment);
		return null;// bedAvailabilityService.saveAmountDetails(payment);

	}

	/*
	 * @PostMapping("/users") public Tenant createUser(@RequestBody Tenant tenant) {
	 * System.out.println("User : " + tenant); return tenantRepository.save(tenant);
	 * }
	 */

	@PutMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> updateUser(@PathVariable(value = "id") Long tenantId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		/*
		 * User user = userRepository.findById(tenantId) .orElseThrow(() -> new
		 * ResourceNotFoundException("Tenant not found for this Id :: " + tenantId));
		 * 
		 * user.setUserId(userDetails.getUserId());
		 * user.setBloodGroup(userDetails.getBloodGroup());
		 * user.setContactNumber(userDetails.getContactNumber());
		 * user.setDob(userDetails.getDob()); user.setEmailId(userDetails.getEmailId());
		 * user.setPermanentAddress(userDetails.getPermanentAddress());
		 */

		logger.info("IN::updateUser::" + tenantId);

		final User updatedTenant = userRepository.save(userDetails);
		logger.info("OUT::updateUser::" + tenantId);

		return ResponseEntity.ok(updatedTenant);
	}

	@DeleteMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public <tenantRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "id") Long tenantId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/users::deleteUser::" + tenantId);

		@SuppressWarnings("unused")
		User user = userRepository.findById(tenantId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this id :: " + tenantId));

		userRepository.deleteById(tenantId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/users::deleteUser::" + tenantId);
		return response;
	}
 
}