package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long>  {
	
	 public List<Bed> findByRoomId(Long floor_id); 
	 public List<Bed> findByFloorId(Long id);

}
