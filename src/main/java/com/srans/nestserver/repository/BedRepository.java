package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Bed;

public interface BedRepository extends JpaRepository<Bed, Long> {

	public List<Bed> findByRoomId(Long roomId);

	public List<Bed> findByFloorId(Long id);
	
     //---------------------- Single Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='R') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalReservedSingleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='Y') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalFilledSingleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Single' AND bed.alloted='N') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalEmptySingleBeds(Long hostelId);

	//------------------------ Double Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='R') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalReservedDoubleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='Y') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalFilledDoubleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Double' AND bed.alloted='N') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalEmptyDoubleBeds(Long hostelId);

	//----------------------- Triple Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='R') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalReservedTripleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='Y') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalFilledTripleBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Triple' AND bed.alloted='N') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalEmptyTripleBeds(Long hostelId);
	
	//----------------------- Misc Beds Info
	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='R') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalReservedMiscBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='Y') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalFilledMiscBeds(Long hostelId);

	@Query(value = "select count(bed.id)from bed inner join room on bed.room_id=room.id where (room.room_type='Misc' AND bed.alloted='N') AND room.hostelId=?1", nativeQuery = true)
	public Integer totalEmptyMiscBeds(Long hostelId);
	
	//------------------------ Get Availabile Beds
	@Query(value = "select tb.room_bed_id, va.date,tb.bed_position,tb.floor_id,tb.room_id,tb.room_rent,tb.room_type from vacation va\r\n" + 
			"inner join  tenantbooking tb on (va.tenant_id = tb.tenant_id) "
			+ " where va.date > CURRENT_DATE\r\n" 
			+ "union"
			+ " select b.id, b.vacated_date,b.position,b.floor_id, b.room_id, r.room_rent, r.room_type from bed b inner join room r on r.id=b.room_id  where b.alloted = 'N' and b.hostelId = ?1" 
			,nativeQuery = true)
	public List <Object> getAvailableBedsByHostelId(Long hosteId);
	


}
