package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {

	public List<Bed> findByRoomId(Long floor_id);

	public List<Bed> findByFloorId(Long id);
	
	

	@Query(value = "select count(bed.id) from bed inner join room on bed.room_id=room.id where room.room_type='Single' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalSingleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledSingleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptySingleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Double' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalDoubleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledDoubleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyDoubleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Triple' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalTripleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledTripleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyTripleBeds(Long hostel_id);
	
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Misc' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalMiscBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilleMiscdBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyMiscBeds(Long hostel_id);
	
	//===================================================
	// new Api Query
	
	@Query(value = "select room_type from room where hostel_id=?1", nativeQuery = true)
	public String[] getRoomType(Long hostel_id);
	

}
