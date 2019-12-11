package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostel;


@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {
	
	
	@Query(value="SELECT hostel_name FROM HOSTEL WHERE id=?1", nativeQuery = true)
    public String hostelNameById(Long id);
	
	
	@Autowired 
	public FloorRepository floorRepository = null ; 
	
	public default Hostel saveWholeObject(Hostel hostel){
		
		Hostel tmpHostel = new Hostel();
		
		tmpHostel = this.save(hostel);
		
	    hostel.getfloors().forEach( floor -> floorRepository.saveWholeObject(floor));
		
	    tmpHostel.setfloors(hostel.getfloors());
	    
		return tmpHostel;
		
	}
	


	

}