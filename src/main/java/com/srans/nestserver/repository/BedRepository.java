package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {

	public List<Bed> findByRoomId(Long floor_id);

	public List<Bed> findByFloorId(Long id);
	
	

	@Query(value = "select count(bed.id) from bed inner join room on bed.room_id=room.id where room.room_type='Single' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalNumberOfBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Double' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalNumberOfBeds1(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledBeds1(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyBeds1(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Triple' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalNumberOfBeds2(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledBeds2(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyBeds2(Long hostel_id);
	
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where room.room_type='Misc' AND room.hostel_id=?1", nativeQuery = true)
	public Long totalNumberOfBeds3(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalFilledBeds3(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Long totalEmptyBeds3(Long hostel_id);

}
