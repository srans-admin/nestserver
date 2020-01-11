package com.srans.nestserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.AdminDetails;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.AdminDetailsRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.UserRepository;

@Service
public class SubAdminService {

	@Autowired
	private HostelRepository hostelRepo;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private AdminDetailsRepository adminDetailsRepo;
	public AdminDetails subAdminProcess(AdminDetails adminDetails ) {
		
		
	   //AdminDetails	adminDetails1 = adminDetailsRepo.save(adminDetails);
	   
		Hostel hostel=hostelRepo.getOne(adminDetails.getHostelId());
		System.out.println(hostel.getAdminId());	
		adminDetails.setHostelId(hostel.getId());		
		User user=userRepository.getOne(adminDetails.getAdminId());
		String adminName=user.getName();
		System.out.println(adminName);
		adminDetails.setAssignBy(adminName);
		adminDetails.setParentAdminId(hostel.getAdminId());
		return adminDetailsRepo.save(adminDetails);
	}
	
}
