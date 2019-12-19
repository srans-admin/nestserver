package com.srans.nestserver.controller;

import java.util.List;


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


	@Autowired
	private TenantRepository tenantRepository;

	@GetMapping("hostels/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();
	}

	@GetMapping(value = "hostels/{hostelName}/bed-info", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<?> getNumOfFloor(@PathVariable(value = "hostelName") String hostelName) {

		Long HostelId = tenantRepository.getHostelId(hostelName);

		System.out.println(HostelId);

		List<?> getInfo = tenantRepository.getBedInfo(HostelId);

		return getInfo;

	}

}
