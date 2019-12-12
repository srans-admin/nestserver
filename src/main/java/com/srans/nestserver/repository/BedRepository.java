package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long>  {
	
	 public List<Bed> findByRoomId(Long floor_id); 
	 public List<Bed> findByFloorId(Long id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE room_type='Single' AND hostel_id=?1", nativeQuery = true)
	    public Long totalNumberOfBeds(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Single' AND alloted='Y')AND hostel_id=?1", nativeQuery=true)
	 public Long totalFilledBeds(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Single' AND alloted='N')AND hostel_id=?1", nativeQuery=true)
	 public Long totalEmptyBeds(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE room_type='Double' AND hostel_id=?1", nativeQuery = true)
	    public Long totalNumberOfBeds1(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Double' AND alloted='Y')AND hostel_id=?1", nativeQuery=true)
	 public Long totalFilledBeds1(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Double' AND alloted='N')AND hostel_id=?1", nativeQuery=true)
	 public Long totalEmptyBeds1(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE room_type='Triple' AND hostel_id=?1", nativeQuery = true)
	    public Long totalNumberOfBeds2(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Triple' AND alloted='Y')AND hostel_id=?1", nativeQuery=true)
	 public Long totalFilledBeds2(Long hostel_id);
	 
	 @Query(value="SELECT count(id) FROM BED WHERE  (room_type='Triple' AND alloted='N')AND hostel_id=?1", nativeQuery=true)
	 public Long totalEmptyBeds2(Long hostel_id);

}
