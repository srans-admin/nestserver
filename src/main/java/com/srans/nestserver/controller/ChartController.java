package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.repository.BedRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ChartController {
	
	@Autowired
	private  BedRepository bedRepository;	
	
	@GetMapping("hostels/{id}/singlebed")
	public Map<String, Object> getSinglebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total_Bed : ", bedRepository.totalNumberOfBeds(hostelId));
		map.put("Total_Empty_Bed : ", bedRepository.totalEmptyBeds(hostelId));
		map.put("Total_Filled_Bed : ", bedRepository.totalFilledBeds(hostelId));
		return map;
	}
	
	@GetMapping("/hostels/{id}/doublebed")
	public Map<String, Object> getDoublebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total_Bed : ", bedRepository.totalNumberOfBeds1(hostelId));
		map.put("Total_Empty_Bed : ", bedRepository.totalEmptyBeds1(hostelId));
		map.put("Total_Filled_Bed : ", bedRepository.totalFilledBeds1(hostelId));
		return map;
	}
	
	@GetMapping("/hostels/{id}/triplebed")
	public Map<String, Object> getTriplebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total_Bed : ", bedRepository.totalNumberOfBeds2(hostelId));
		map.put("Total_Empty_Bed : ", bedRepository.totalEmptyBeds2(hostelId));
		map.put("Total_Filled_Bed : ", bedRepository.totalFilledBeds2(hostelId));
		return map;
	}
	

}
