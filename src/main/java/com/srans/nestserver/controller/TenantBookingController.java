package com.srans.nestserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.TenantRepository;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {

	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	@Autowired
	private TenantBookRepository tenantBookRepository; 

	
	@GetMapping("tenantbooking/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();

	}

	@GetMapping("tenantbooking/hostelName/{hostelname}/bed")
	public List<Object[]> getNumOfFloor(@PathVariable(value = "hostelname") String hostelname) {
	
	
		Long HostelId=tenantRepository.getHostelId(hostelname);
		
		System.out.println(HostelId);

		return tenantRepository.getBedInfo(HostelId);

	}
	
	@PostMapping("/tenantbooking")
	
	public Tenant saveTenant(@Valid @RequestBody Tenant tenant) throws NSException {
		
		logger.info("IN::POST::/hostels::saveHostel::" + tenant);

		Tenant responsetenant=tenantRepository.save(tenant);
		
		// SAVE Database stuff here
		
		responsetenant.getTenantBooking().forEach(tenantbooking->{
			tenantbooking.setTenantId(responsetenant.getUserId());
		          tenantBookRepository.save(tenantbooking);
			
		});
		return responsetenant;
			
		}
	
	@GetMapping("/tenantbooking")
	public List<Tenant> getAllPosts() {
		return tenantRepository.findAll();
		
		
	}
	
	
   

}
