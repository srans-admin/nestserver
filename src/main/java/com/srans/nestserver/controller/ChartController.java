package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.SharingTypeChartInfo;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RequestMapping("api/v1")
@RestController
public class ChartController<T> {

	@Autowired
	private BedRepository bedRepository;

	@Autowired
	private RoomRepository roomRepository;

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

	// ======New Api=========

	@GetMapping(value = "hostels/{id}/chart")
	public ResponseEntity<Map<String[], List<Object>>> chart(
			@PathVariable(value = "id", required = false) Long hostelId) {
		Map<String[], List<Object>> result = new HashMap<String[], List<Object>>();
		result.put(bedRepository.getRoomType(hostelId), Arrays.asList(bedRepository.totalSingleBeds(hostelId),

				bedRepository.totalFilledSingleBeds(hostelId)

		));
		return ResponseEntity.ok().body(result);
	}
	 
	@GetMapping(value = "hostels/{id}/sharing-type-chart-info")
	public List<SharingTypeChartInfo> getSharingTypeChartInfo(@PathVariable(value = "id", required = false) Long hostelId) {
		
		List<SharingTypeChartInfo> sharingTypeChartInfos = new ArrayList<>();
		
		//Get Single Beds Info
		for(int i = 0 ; i < 4 ; i++) {
			
			SharingTypeChartInfo sharingTypeChartInfo = new SharingTypeChartInfo();
			String type = "";
			switch (i) {
				case 0:type="Single"; break;
				case 1:type="Double"; break;
				case 2:type="Triple"; break; 
				default:type="Misc"; break; 
			}
			sharingTypeChartInfo.setSharingType(type);
			
			sharingTypeChartInfo.getBedsAvailibilty().add(23); //TODO need to call database repo for avialibilty here
			sharingTypeChartInfo.getBedsAvailibilty().add(2); //TODO need to call database repo for avialibilty here
			sharingTypeChartInfo.getBedsAvailibilty().add(24);//TODO need to call database repo for avialibilty here
			
			sharingTypeChartInfos.add(sharingTypeChartInfo);
		}
		return sharingTypeChartInfos;
		
	}

	/*//@SuppressWarnings("unchecked")
	//@GetMapping(value = "hostels/{id}/sharing-type-chart-info")
	public Object geta(@PathVariable(value = "id", required = false) Long hostelId) {

		Object[] ob = new Object[1];
		Object[] ob2 = new Object[1];
		Object[] ob3 = new Object[1];
		Object[] ob4 = new Object[1];
		Object[][] ob1 = { { ob, ob2, ob3, ob4 } };

		@SuppressWarnings("rawtypes")
	
		Set<String> s = new HashSet<>();
		s.addAll(roomRepository.getRoomType(hostelId));
		int totalType = s.size();
		for (int j = 1; j <= totalType; j++) {
			
			Map<String,Map> m1=new HashMap<>();
			
			for (int i = 1; i <= totalType; i++) {
				if (i == 1) {

					
					 * RoomAvailability roomA=new RoomAvailability();
					 * 
					 * roomA.setSharingType("Single sharing Typw"); RoomAvailability roomB=new
					 * RoomAvailability(Arrays.asList( bedRepository.totalSingleBeds(hostelId),
					 * bedRepository.totalEmptySingleBeds(hostelId),
					 * bedRepository.totalFilledSingleBeds(hostelId) ));
					 
//				List <String> l1=new ArrayList<>();
//		
//				l1.add("Single sharing type");
					
					 * Map<String,RoomAvailability> typeMap=new LinkedHashMap<>();
					 * typeMap.put("sharingType",roomA.sharingType); typeMap.put("Data", new
					 * RoomAvailability(Arrays.asList(bedRepository.totalSingleBeds(hostelId),
					 * 
					 * bedRepository.totalEmptySingleBeds(hostelId),
					 * bedRepository.totalFilledSingleBeds(hostelId) )));
					 

					@SuppressWarnings("rawtypes")
					Map bedMap = new LinkedHashMap();

					bedMap.put("sharingType", "single sharing type");
					bedMap.put("Data",
							Arrays.asList(bedRepository.totalSingleBeds(hostelId),
									bedRepository.totalFilledSingleBeds(hostelId),
									bedRepository.totalEmptySingleBeds(hostelId)));

					// bedMap.put("sharingType","Single sharing type");
					ob[0] = bedMap;

					
					 * for(Object ob1:ob) { System.out.println(ob1); }
					 * 
					 
					
				}

				else if (i == 2) {

					@SuppressWarnings("rawtypes")
					Map bedMap = new LinkedHashMap();

					bedMap.put("sharingType", "single sharing type");
					bedMap.put("Data",
							Arrays.asList(bedRepository.totalSingleBeds(hostelId),
									bedRepository.totalFilledSingleBeds(hostelId),
									bedRepository.totalEmptySingleBeds(hostelId)));
					ob2[0] = bedMap;
				}

				else if (i == 3) {

					@SuppressWarnings("rawtypes")
					Map bedMap = new LinkedHashMap();

					bedMap.put("sharingType", "single sharing type");
					bedMap.put("Data",
							Arrays.asList(bedRepository.totalSingleBeds(hostelId),
									bedRepository.totalFilledSingleBeds(hostelId),
									bedRepository.totalEmptySingleBeds(hostelId)));
					ob3[0] = bedMap;
				} else {

					@SuppressWarnings("rawtypes")
					Map bedMap = new LinkedHashMap();

					bedMap.put("sharingType", "single sharing type");
					bedMap.put("Data",
							Arrays.asList(bedRepository.totalSingleBeds(hostelId),
									bedRepository.totalFilledSingleBeds(hostelId),
									bedRepository.totalEmptySingleBeds(hostelId)));
					ob4[0] = bedMap;

				}

			}
		}
		return ob1[0];*/
	//}

}
