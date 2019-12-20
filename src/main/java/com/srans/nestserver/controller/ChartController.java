package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.SharingTypeChartInfo;
import com.srans.nestserver.repository.BedRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ChartController<T> {

	@Autowired
	private BedRepository bedRepository;


	@GetMapping(value = "hostels/{id}/sharing-type-chart-info")
	public List<SharingTypeChartInfo> getSharingTypeChartInfo(
			@PathVariable(value = "id", required = false) Long hostelId) {

		List<SharingTypeChartInfo> sharingTypeChartInfos = new ArrayList<>();

		
		for (int i = 0; i < 4; i++) {

			SharingTypeChartInfo sharingTypeChartInfo = new SharingTypeChartInfo();
			String type = "";
			switch (i) {
			case 0:
				type = "Single";
				break;
			case 1:
				type = "Double";
				break;
			case 2:
				type = "Triple";
				break;
			default:
				type = "Misc";
				break;
			}
			
			if(type.equals("Single")){
				sharingTypeChartInfo.setSharingType(type);  

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedSingleBeds(hostelId));// Reserved Single Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledSingleBeds(hostelId)); // Filled Single Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptySingleBeds(hostelId)); // Vaccant Single Beds
			}
			else if(type.equals("Double")) {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedDoubleBeds(hostelId)); // Reserved Double Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledDoubleBeds(hostelId)); // Filled Double Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyDoubleBeds(hostelId));// Vaccant Double Beds
			}
			
			else if(type.equals("Triple")) {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedTripleBeds(hostelId)); // Reserved Triple Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledTripleBeds(hostelId)); //  Filled Triple Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyTripleBeds(hostelId));// Vaccant Triple Beds
			}
			
			else {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedMiscBeds(hostelId)); // Reserved Misc Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledMiscBeds(hostelId)); // Filled Misc Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyTripleBeds(hostelId));// Vaccant Misc Beds
			}
			

			sharingTypeChartInfos.add(sharingTypeChartInfo);
		}
		return sharingTypeChartInfos;

	}

}
