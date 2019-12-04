package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{
	
	/* public List<Rooms> findByFloorId(Long floor_id); */
	
		
}
