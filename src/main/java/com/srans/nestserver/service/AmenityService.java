package com.srans.nestserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.AmenityType;
import com.srans.nestserver.repository.AmenityTypeRepository;

@Service
public class AmenityService{
	
	@Autowired
	AmenityTypeRepository amenityTypeRepo;
	
	public AmenityType saveAmenity(AmenityType amenityType) {
		
		
		
		return amenityTypeRepo.save(amenityType);
		
	}
}
