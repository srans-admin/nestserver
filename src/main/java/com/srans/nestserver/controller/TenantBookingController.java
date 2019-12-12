package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {

	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private BedRepository bedRepository;

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
   

}
