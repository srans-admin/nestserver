package com.srans.nestserver.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantBookingController {
	
	
	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;
	
	
	@Autowired
	private HostelRepository hostelRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	@Autowired
	private BedRepository bedRepository;
	
	
	@GetMapping("tenantbooking/hostelName")
	public List<String> findAll() {
		return tenantRepository.getAllHostelName();

	}
	
	@GetMapping("tenantbooking/hostelName/{hostelname}/bed")
    public List<Long> getNumOfFloor(@PathVariable(value="hostelname") String hostelname) {
		
		Integer totalnumberoffloor=tenantRepository.getTotalNumberOfFloors(hostelname);
		System.out.println(totalnumberoffloor);
		
		String floorName=null;
		Long hostelid=tenantRepository.getHostelId(hostelname);
		
		for(int i=1;i<=totalnumberoffloor;i++) {
			
				  floorName=i+"st"+"-floor";
				  if(i==2) {
					  floorName=i+"nd"+"-floor";
				  }
				  
				  else if(i==3) {
					  floorName=i+"rd"+"-floor";
				  }
				  
				  else if(i>=4) {
					  floorName=i+"th"+"-floor";
				  }
				 
				 System.out.println(floorName);
				 
				 
			 
		}
		
		/*
		 * Hostel hostel=null; Hostel responseHostel = hostelRepository.save(hostel);
		 * 
		 * responseHostel.getfloors().forEach(floor -> {
		 * floor.setHostelId(responseHostel.getId()); Floor resFloor =
		 * floorRepository.save(floor);
		 * 
		 * floor.getRooms().forEach(room -> { room.setHostelId(responseHostel.getId());
		 * room.setFloorId(resFloor.getId()); Room resRoom = roomRepository.save(room);
		 * room.getBeds().forEach(bed -> { bed.setHostelId(responseHostel.getId());
		 * bed.setFloorId(resFloor.getId()); bed.setRoomId(resRoom.getId());
		 * bedRepository.save(bed); });
		 * 
		 * });
		 * 
		 * });
		 * 
		 */
		
		
		
		List<Long> l=new ArrayList<>();
		return l;
		
	
	}
	

}
