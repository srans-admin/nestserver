package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {

	public List<Bed> findByRoomId(Long roomId);

	public List<Bed> findByFloorId(Long id);
	
     //---------------------- Single Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='R') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalReservedSingleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalFilledSingleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalEmptySingleBeds(Long hostel_id);

	//------------------------ Double Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='R') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalReservedDoubleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalFilledDoubleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalEmptyDoubleBeds(Long hostel_id);

	//----------------------- Triple Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='R') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalReservedTripleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalFilledTripleBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalEmptyTripleBeds(Long hostel_id);
	
	//----------------------- Misc Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='R') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalReservedMiscBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='Y') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalFilledMiscBeds(Long hostel_id);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='N') AND room.hostel_id=?1", nativeQuery = true)
	public Integer totalEmptyMiscBeds(Long hostel_id);
	

	

}
