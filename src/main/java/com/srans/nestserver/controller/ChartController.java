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
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ChartController {
	
	@Autowired
	private  BedRepository bedRepository;	
	
	@GetMapping("/doughnutchart/{id}/singlebedinfo")
	public Map<String, Object> getSinglebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total Bed : ", bedRepository.totalNumberOfBeds(hostelId));
		map.put("Total Empty Bed : ", bedRepository.totalEmptyBeds(hostelId));
		map.put("Total Filled Bed : ", bedRepository.totalFilledBeds(hostelId));
		return map;
	}
	
	@GetMapping("/doughnutchart/{id}/doublebedinfo")
	public Map<String, Object> getDoublebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total Bed : ", bedRepository.totalNumberOfBeds1(hostelId));
		map.put("Total Empty Bed : ", bedRepository.totalEmptyBeds1(hostelId));
		map.put("Total Filled Bed : ", bedRepository.totalFilledBeds1(hostelId));
		return map;
	}
	
	@GetMapping("/doughnutchart/{id}/triplebedinfo")
	public Map<String, Object> getTriplebedinfo(@PathVariable(value = "id") Long hostelId) {
		Map<String, Object> map = new HashMap<>();
		map.put("Total Bed : ", bedRepository.totalNumberOfBeds2(hostelId));
		map.put("Total Empty Bed : ", bedRepository.totalEmptyBeds2(hostelId));
		map.put("Total Filled Bed : ", bedRepository.totalFilledBeds2(hostelId));
		return map;
	}
	

}
