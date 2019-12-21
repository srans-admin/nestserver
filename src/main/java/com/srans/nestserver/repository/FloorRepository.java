package com.srans.nestserver.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Floor;

@Repository
public interface FloorRepository extends JpaRepository<Floor, Long> {
	
	
	  List<Floor> findByHostelId(Long hostel_id); 
	  Optional<Floor> findByIdAndHostelId(Long floor_id, Long hostel_id);
	  
	  @Query(value = "select * from Floor where hostel_id=?1 ", nativeQuery = true)
		public Floor getFloordetails(Long hostelId);
	  
	  @Query(value = "select id from Floor where hostel_id=?1 ", nativeQuery = true)
		public Iterable<Long> getFloorId(Iterable<Long> id);
	
	
	
	

	
}