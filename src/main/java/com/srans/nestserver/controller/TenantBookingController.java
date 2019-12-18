package com.srans.nestserver.controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

	@GetMapping(value="hostels/{hostelname}/bed", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<?> getNumOfFloor(@PathVariable(value = "hostelname") String hostelname) {
	
		Long HostelId=tenantRepository.getHostelId(hostelname);
		
		System.out.println(HostelId);
		
		List<?> getInfo =tenantRepository.getBedInfo(HostelId);

		return getInfo ;

	}

}
