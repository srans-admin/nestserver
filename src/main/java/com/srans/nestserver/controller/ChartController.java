package com.srans.nestserver.controller;

import java.util.Arrays;
import java.util.List;

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
	private BedRepository bedRepository;

	@GetMapping("hostels/{id}/singlebedinfo")
	public List<Object> getHostelInfo(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalNumberOfBeds(hostelId), bedRepository.totalEmptyBeds(hostelId),
				bedRepository.totalFilledBeds(hostelId));
	}

	@GetMapping("hostels/{id}/doublebedinfo")
	public List<Object> getHostelInfo1(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalNumberOfBeds1(hostelId), bedRepository.totalEmptyBeds1(hostelId),
				bedRepository.totalFilledBeds1(hostelId));
	}

	@GetMapping("hostels/{id}/triplebedinfo")
	public List<Object> getHostelInfo2(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalNumberOfBeds2(hostelId), bedRepository.totalEmptyBeds2(hostelId),
				bedRepository.totalFilledBeds2(hostelId));
	}

	@GetMapping("hostels/{id}/miscbedinfo")
	public List<Object> getHostelInfo3(@PathVariable(value = "id") Long hostelId) {
		return Arrays.asList(

				bedRepository.totalNumberOfBeds3(hostelId), bedRepository.totalEmptyBeds3(hostelId),
				bedRepository.totalFilledBeds3(hostelId));
	}

}
