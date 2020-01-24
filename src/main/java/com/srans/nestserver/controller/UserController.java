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
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.service.StorageService;
import com.srans.nestserver.service.UserService;
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
	private HostelRepository hostelRepository;

	@Autowired
	private UserService userService = new UserService();

	@Autowired
	private PaymentRepository paymentRepository;

	@PostMapping("/users")
	@PreAuthorize("permitAll()")
	public User saveUser(@Valid @RequestBody User user) throws NSException {

		logger.info("IN::POST::/users::saveUser::" + user);

		user = userService.processUser(user);

		logger.info("OUT::POST::/users::saveUser::" + user);
		return user;
	}

	@GetMapping("users/payment/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		logger.info("IN::users::" + paymentId);

		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		logger.info("OUT::users::" + paymentId);

		return ResponseEntity.ok().body(payment);
	}

	@GetMapping("/users")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	// @PreAuthorize("permitAll()")
	public List<User> getAllTenants() {
		logger.info("Getting all tenant details");
		return userRepository.findAll();
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> getTenantById(@PathVariable(value = "id") Long TenantId)
			throws ResourceNotFoundException {
		logger.info("IN:: users::" +TenantId);
		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
		logger.info("out:: users::" +TenantId);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/users/byname/{name}")
	@PreAuthorize("permitAll()")
	// @PreAuthorize("permitAll()")
	public ResponseEntity<User> getTenantByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		logger.info("IN:: users::"+name);
		User user = userRepository.findByName(name);
		logger.info("OUT:: users::"+name);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/users/byroomRent/{roomRent}")
	@PreAuthorize("permitAll()")
	// @PreAuthorize("permitAll()")
	public ResponseEntity<TenantBooking> getTenantBookingByroomRent(@PathVariable(value = "roomRent") Long roomRent)
			throws ResourceNotFoundException {
		logger.info("IN:: users::"+roomRent);

		TenantBooking tenantbooking = TenantBookRepository.findByroomRent(roomRent);
		logger.info("OUT:: users::"+roomRent);
		return ResponseEntity.ok().body(tenantbooking);
	}

	@GetMapping("/users/byroomName/{roomName}")

	@PreAuthorize("permitAll()")
	// @PreAuthorize("permitAll()")
	public ResponseEntity<TenantBooking> getTenantBookingByroomName(@PathVariable(value = "roomName") String roomName)
			throws ResourceNotFoundException {
		logger.info("IN:: users::"+roomName);
		TenantBooking tenantbooking = TenantBookRepository.findByroomName(roomName);
		logger.info("OUT:: users::"+roomName);
		return ResponseEntity.ok().body(tenantbooking);
	}

	@GetMapping("/users/byhostelName/{hostelName}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Hostel> getHostelName(@PathVariable(value = "hostelName") String hostelname)
			throws ResourceNotFoundException {
		logger.info("IN:: users::"+hostelname);

		Hostel hostel = hostelRepository.findByhostelName(hostelname);
		logger.info("OUT:: users::"+hostelname);
		return ResponseEntity.ok().body(hostel);
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

	/*
	 * @PostMapping("/users") public Tenant createUser(@RequestBody Tenant tenant) {
	 * System.out.println("User : " + tenant); return tenantRepository.save(tenant);
	 * }
	 */

	@PutMapping("/users/{Id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<User> updateUser(@PathVariable(value = "Id") Long userId,
			@Valid @RequestBody User userDetails) throws ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("user not found for this Id :: " + userId));
		user.setName(userDetails.getName());
		user.setContactNumber(userDetails.getContactNumber());
		user.setUserId(userDetails.getUserId());
		user.setBloodGroup(userDetails.getBloodGroup());
		user.setContactNumber(userDetails.getContactNumber());
		user.setDob(userDetails.getDob());
		user.setEmailId(userDetails.getEmailId());
		user.setPermanentAddress(userDetails.getPermanentAddress());
		final User updatedTenant = userRepository.save(user);
		return ResponseEntity.ok(updatedTenant);
	}

	@DeleteMapping("/users/{Id}")
	@PreAuthorize("permitAll()")
	public <userRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "Id") Long TenantId)
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