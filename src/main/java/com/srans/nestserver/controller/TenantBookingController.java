package com.srans.nestserver.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.TenantRepository;
import com.srans.nestserver.service.StorageService;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {
	
	
	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	
	@GetMapping("tenantbooking/getName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();

	}
	
	
	

}
