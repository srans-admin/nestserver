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
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
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

	@PostMapping("/users")
	@PreAuthorize("permitAll()")
	public User saveUser(@Valid @RequestBody User user) throws NSException {

		logger.info("IN::POST::/users::saveUser::" + user);

		user = userService.processUser(user);

		logger.info("OUT::POST::/users::saveUser::" + user);
		return user;
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	// @PreAuthorize("permitAll()")
	public List<User> getAllTenants() {
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public User getTenantById(@PathVariable(value = "id") Long TenantId)
			throws ResourceNotFoundException {
		User user = userRepository.getOne(TenantId);
		
		tenantBookingRepo.findByTenantId(TenantId).forEach(tenantbooking->{
			user.setTenantBooking(tenantbooking);
		});
		
				
		return user;
	}

	
	@GetMapping("/users/byname/{name}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> getTenantByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		User user = userRepository.findByName(name);
		return ResponseEntity.ok().body(user);
	}
	

	@PostMapping("/users/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeTenantImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/users/{id}/upload/{cat}::uploadTenantImages::" + id + "::" + cat);
		storageService.storeTenantImage(file, cat, id);
		logger.info("OUT::POST:://users/uploadImage/{cat}/{id}::uploadTenantImages::" + id + "::" + cat);

	}

	@GetMapping("/users/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retrivetenantImage(id, cat)));

	}

	@GetMapping("/usersidproof/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveIdproofImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {

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

		return bedAvailabilityService.getAllAvailableBed(hostelId);

	}

	// API for book the bed by guest

	@PostMapping("/users/bed-booking")
	@PreAuthorize("permitAll()")
	public TenantBooking postBedBookingDetails(@Valid @RequestBody TenantBooking tenantBooking) throws NSException {

		return bedAvailabilityService.saveBookedBedDetails(tenantBooking);
	}

	// API for save amount who's payment by guest for booked bed
	@PostMapping("/users/guest-payment")
	@PreAuthorize("permitAll()")
	public Payment postSaveAmountDetails(@Valid @RequestBody Payment payment) throws NSException {

		return bedAvailabilityService.saveAmountDetails(payment);
	}

	/*
	 * @PostMapping("/users") public Tenant createUser(@RequestBody Tenant tenant) {
	 * System.out.println("User : " + tenant); return tenantRepository.save(tenant);
	 * }
	 */

	@PutMapping("/users/{Id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> updateUser(@PathVariable(value = "Id") Long TenantId,
			@Valid @RequestBody User tenantDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));

		user.setUserId(user.getUserId());
		user.setBloodGroup(user.getBloodGroup());
		user.setContactNumber(user.getContactNumber());
		user.setDob(user.getDob());
		user.setEmailId(user.getEmailId());
		user.setPermanentAddress(user.getPermanentAddress());
		final User updatedTenant = userRepository.save(user);
		return ResponseEntity.ok(updatedTenant);
	}

	@DeleteMapping("/users/{Id}")
	@PreAuthorize("permitAll()")
	public <tenantRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "Id") Long TenantId)
			throws ResourceNotFoundException {
		@SuppressWarnings("unused")
		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this id :: " + TenantId));

		userRepository.deleteById(TenantId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}