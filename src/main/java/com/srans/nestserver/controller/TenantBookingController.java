package com.srans.nestserver.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	
	@GetMapping("tenantbooking/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();

	}
	
	@GetMapping("tenantbooking/hostelName/{hostelname}/bed")
	public Set<String> data(){
		HashSet<String> hs=null;
		return hs ;
	}
	
	
	
	

}
