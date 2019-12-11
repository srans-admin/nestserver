package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long>{

	
	 public List<Room> findByFloorId(Long floor_id); 
	
		
}

