package com.srans.nestserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.SubAdminDetails;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.SubAdminDetailsRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;

@Service
public class SubAdminService {

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private SubAdminDetailsRepository adminDetailsRepo;
	
	
	
	
	public  User  subAdminDetails(User user) {
		
		userRepository.saveAndFlush(user);
		
		return userRepository.saveAndFlush(user);

	}

	public SubAdminDetails subAdminProcess(SubAdminDetails subAdminDetails) {

		adminDetailsRepo.saveAndFlush(subAdminDetails);

		// Update Details of sub admin

		return adminDetailsRepo.findById(subAdminDetails.getId()).map(admin -> {
			Hostel hostel = hostelRepo.getOne(subAdminDetails.getAssignHostelIds());
			subAdminDetails.setAssignHostelName(hostel.getHostelName());
			subAdminDetails.setParentAdminId(hostel.getAdminId());
			
			User user = userRepository.getOne(subAdminDetails.getSubAdminId());
			String subAdminName = user.getName();
			subAdminDetails.setSubAdminName(subAdminName);
			
			User user1=userRepository.getOne(hostel.getAdminId());
			String parentAdminName=user1.getName();
	        subAdminDetails.setParentAdminName(parentAdminName);
	        
	        subAdminDetails.setRole(NSConstants.ROLE_ADMIN);

			return adminDetailsRepo.saveAndFlush(subAdminDetails);

		}).orElseThrow(() -> new ResourceNotFoundException("Admin " + subAdminDetails.getId() + " not found"));

	}

}
