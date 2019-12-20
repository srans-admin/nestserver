package com.srans.nestserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {

	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;

	
	@GetMapping("hostels/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();
	}

	@GetMapping("hostels/{hostelName}/bed-availability")
	public List<Object[]> getBedAvailibilityReport(@PathVariable(value = "hostelName") String hostelName) {
	
		Hostel hostel = new Hostel();
	
		Long HostelId = tenantRepository.getHostelId(hostelName);
		
		System.out.println(HostelId);

		return tenantRepository.getBedInfo(HostelId);

	}

}
