package com.srans.nestserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostel;


@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {
	
	@Autowired 
	public FloorRepository floorRepository = null ; 
	
	@Query(value="SELECT num_of_floors FROM HOSTEL WHERE id=?1", nativeQuery = true)
    public Long numOfFloor(Long hostel_id);
	
	@Query(value="SELECT id FROM Hostel", nativeQuery=true) 
   public List<Long> findId();
	
	@Query(value="SELECT hostel_name FROM HOSTEL WHERE id=?1", nativeQuery=true)
	public String hosteName(Long hostel_id);
	
	@Query(value="SELECT room_type FROM ROOM WHERE hostel_id=?1", nativeQuery=true)
	public Set<String> findType(Long hostel_id);
	
	
	public default Hostel saveWholeObject(Hostel hostel){
		
		Hostel tmpHostel = new Hostel();
		
		tmpHostel = this.save(hostel);
		
	    hostel.getfloors().forEach( floor -> floorRepository.saveWholeObject(floor));
		
	    tmpHostel.setfloors(hostel.getfloors());
	    
		return tmpHostel;
		
	}
	

}