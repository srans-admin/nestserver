package com.srans.nestserver.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	private BedRepository bedRepository;

	@GetMapping("hostels/{id}/singlebed-info")
	public List<Object> getHostelInfo(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalSingleBeds(hostelId), bedRepository.totalEmptySingleBeds(hostelId),
				bedRepository.totalFilledSingleBeds(hostelId));
	}

	@GetMapping("hostels/{id}/doublebed-info")
	public List<Object> getHostelInfo1(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalDoubleBeds(hostelId), bedRepository.totalEmptyDoubleBeds(hostelId),
				bedRepository.totalFilledDoubleBeds(hostelId));
	}

	@GetMapping("hostels/{id}/triplebed-info")
	public List<Object> getHostelInfo2(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalTripleBeds(hostelId), bedRepository.totalEmptyTripleBeds(hostelId),
				bedRepository.totalFilledTripleBeds(hostelId));
	}

	@GetMapping("hostels/{id}/bed-info")
	public List<Object> getHostelInfo3(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalMiscBeds(hostelId), bedRepository.totalFilleMiscdBeds(hostelId),
				bedRepository.totalEmptyMiscBeds(hostelId));
	}
	
	//======New Api=========
	
	@GetMapping(value= "hostels/{id}/chart")
	public ResponseEntity<Map<String[], List<Object>>> chart(@PathVariable(value="id", required=false)Long hostelId ){
		Map<String[],List<Object>> result=new HashMap<String[],List<Object>>();
		result.put(bedRepository.getRoomType(hostelId), Arrays.asList(bedRepository.totalSingleBeds(hostelId),
				
				bedRepository.totalFilledSingleBeds(hostelId)
				
				
				));
		return ResponseEntity.ok().body(result);
	}

}
