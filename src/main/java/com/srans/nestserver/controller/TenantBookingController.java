package com.srans.nestserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TenantBookRepository tenantBookRepository;
	/*
	 * 
	 * @GetMapping("tenantbooking/hostelName") public List<String> findAll() {
	 * return tenantRepository.getAllHostelName();
	 * 
	 * }
	 * 
	 * @GetMapping("tenantbooking/hostelName/{hostelname}/bed") public
	 * List<Object[]> getNumOfFloor(@PathVariable(value = "hostelname") String
	 * hostelname) {
	 * 
	 * 
	 * Long HostelId=tenantRepository.getHostelId(hostelname);
	 * 
	 * System.out.println(HostelId);
	 * 
	 * return tenantRepository.getBedInfo(HostelId);
	 * 
	 * }
	 */

	@PostMapping("/tenantbooking")
	@PreAuthorize("permitAll()")
	public User bookTenant(@Valid @RequestBody User user) throws NSException {

		logger.info("IN::POST::/tenantbooking::bookTenant::" + user);

		User responsetenant = userRepository.save(user);
		
		if(responsetenant.getUserId() != -1) {
			//user.getTenantBooking().set
			tenantBookRepository.save(user.getTenantBooking());
			
		}

	

		return responsetenant;

	}

	@GetMapping("/tenantbooking")
	public List<User> getAllPosts() {
		logger.info("Get all tenantbooking");

		return userRepository.findAll();

	} 
	 

}
