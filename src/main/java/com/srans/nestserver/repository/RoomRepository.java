package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Rooms;

@Repository
public interface RoomRepository extends JpaRepository<Rooms, Long>{
	
	public List<Rooms> findByFloorId(Long floor_id);
	
}