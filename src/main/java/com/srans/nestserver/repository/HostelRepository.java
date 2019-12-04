package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostel;


@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {
	
	@Autowired 
	public FloorRepository floorRepository = null ;
	
	/*
	 * @Autowired public FloorRepository floorRepository=null;
	 * 
	 * public static Hostels saveMe(Hostels hostel) {
	 * 
	 * for (Floor floor : hostel.getFloorList()) {
	 * 
	 * floor = floorRepository.save(floor); } return hostel; }
	 */
	 
	
	public default Hostel saveWholeObject(Hostel hostel){
		
		Hostel tmpHostel = new Hostel();
		
		tmpHostel = this.save(hostel);
		
	    hostel.getfloors().forEach( floor -> floorRepository.saveWholeObject(floor));
		
	    tmpHostel.setfloors(hostel.getfloors());
	    
		return tmpHostel;
		
	}

}