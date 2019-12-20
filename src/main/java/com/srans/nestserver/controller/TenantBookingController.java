package com.srans.nestserver.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private TenantBookRepository tenantBookRepository;

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

	@PostMapping("/tenantbooking")

	public TenantBooking saveTenantBooking(@Valid @RequestBody TenantBooking tenantbooking) throws NSException {

		TenantBooking responsetenant = tenantBookRepository.save(tenantbooking);
		return responsetenant;

	}

}
