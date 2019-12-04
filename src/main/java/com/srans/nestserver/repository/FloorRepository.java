package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
	/*
	 * List<Floor> findByHostelId(Long hostel_id); Optional<Floor>
	 * findByIdAndHostelId(Long floor_id, Long hostel_id);
	 */
	
	@Autowired
	RoomRepository roomRepository = null;
	
	public default Floor saveWholeObject(Floor floor){
		
		Floor tmpfloor = null;
		
		tmpfloor = this.save(floor);
		
		floor.getRooms().forEach( room -> roomRepository.save(room));
		
		tmpfloor.setRooms(floor.getRooms());
		
		return tmpfloor;
		
	}
}