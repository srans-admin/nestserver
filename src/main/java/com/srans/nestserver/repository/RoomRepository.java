package com.srans.nestserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

	public List<Room> findByFloorId(Long floor_id);

	public List<Room> findByHostelId(Long id);

	@Autowired
	BedRepository bedRepository = null;

	public default Room saveWholeObject(Room room) {

		Room tmproom = null;

		tmproom = this.save(room);

		room.getBeds().forEach(bed-> bedRepository.save(bed));

		tmproom.setBeds(room.getBeds());

		return tmproom;

	}

	@Query(value = "SELECT COUNT(id) FROM ROOM WHERE hostel_id=?1", nativeQuery = true)
	public Long countRoomByHostelId(Long hostel_id);

	@Query(value = "SELECT COUNT(id) FROM ROOM WHERE hostel_id=?1 AND room_type='Single'", nativeQuery = true)
	public Long countSingleSharing(Long hostel_id);

	@Query(value = "SELECT COUNT(id) FROM ROOM WHERE hostel_id=?1 AND room_type='Double'", nativeQuery = true)
	public Long countDoubleSharing(Long hostel_id);

	@Query(value = "SELECT COUNT(id) FROM ROOM WHERE hostel_id=?1 AND room_type='Triple'", nativeQuery = true)
	public Long countTripleSharing(Long hostel_id);

	@Query(value = "SELECT COUNT(id) FROM ROOM WHERE hostel_id=?1 AND room_type='Misc'", nativeQuery = true)
	public Long countMiscSharing(Long hostel_id);
	
	@Query(value="Select room.room_type from room where hostel_id=?1",nativeQuery = true)
	public Set<String> getRoomType(Long HostelId);

}

