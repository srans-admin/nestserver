package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.BedBookingTypeChartInfo;
import com.srans.nestserver.model.SharingTypeChartInfo;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.TenantBookRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ChartController<T> {

	@Autowired
	private BedRepository bedRepository;
	
	@Autowired
	private TenantBookRepository tenantBookingRepository;

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

			if (type.equals("Single")) {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedSingleBeds(hostelId));// Reserved
																												// Single
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledSingleBeds(hostelId)); // Filled
																												// Single
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptySingleBeds(hostelId)); // Vaccant
																												// Single
																												// Beds
			} else if (type.equals("Double")) {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedDoubleBeds(hostelId)); // Reserved
																												// Double
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledDoubleBeds(hostelId)); // Filled
																												// Double
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyDoubleBeds(hostelId));// Vaccant
																											// Double
																											// Beds
			}

			else if (type.equals("Triple")) {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedTripleBeds(hostelId)); // Reserved
																												// Triple
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledTripleBeds(hostelId)); // Filled
																												// Triple
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyTripleBeds(hostelId));// Vaccant
																											// Triple
																											// Beds
			}

			else {
				sharingTypeChartInfo.setSharingType(type);

				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalReservedMiscBeds(hostelId)); // Reserved
																												// Misc
																												// Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalFilledMiscBeds(hostelId)); // Filled
																											// Misc Beds
				sharingTypeChartInfo.getBedsAvailibilty().add(bedRepository.totalEmptyTripleBeds(hostelId));// Vaccant
																											// Misc Beds
			}

			sharingTypeChartInfos.add(sharingTypeChartInfo);
		}
		return sharingTypeChartInfos;

	}

	@GetMapping(value = "hostels/bed-booking-type-chart-info")
	public List<BedBookingTypeChartInfo> getBedBookingTypeChartInfo() {

		List<BedBookingTypeChartInfo> bedBookingTypeChartInfos = new LinkedList<>();
		String month = "";
		for (int i = 1; i <= 12; i++) {
			BedBookingTypeChartInfo bedBookingTypeChartInfo = new BedBookingTypeChartInfo();
			switch (i) {
			case 1:
				month = "JANUARY";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			

				break;
			case 2:
				month = " FEBRUARY";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			

				break;
			case 3:
				month = "MARCH";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			

				break;
			case 4:
				month = "APRIL";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			

				break;
			case 5:
				month = "MAY";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 6:
				month = "JUNE";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 7:
				month = "JULY";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 8:
				month = "AUGUST";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 9:
				month = "SEPTEMBER";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 10:
				month = "OCTOBER";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;
			case 11:
				month = "NOVEMBER";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;

			default:
				month = "DECEMBER";
				bedBookingTypeChartInfo.setMonthType(month);
				bedBookingTypeChartInfo.setTotalbookedbed(tenantBookingRepository.getTotalBookedBed(i));			


				break;

			}
			bedBookingTypeChartInfos.add(bedBookingTypeChartInfo);

		}

		return bedBookingTypeChartInfos;
	}

}
