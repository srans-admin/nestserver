package com.srans.nestserver.repository;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostel;

@Repository
public interface HostelRepository extends JpaRepository<Hostel, Long> {

	@Autowired
	public FloorRepository floorRepository = null;

	@Query(value = "SELECT num_of_floors FROM HOSTEL WHERE id=?1", nativeQuery = true)
	public Long numOfFloor(Long hostel_id);

	@Query(value = "SELECT hostel_name FROM HOSTEL WHERE id=?1", nativeQuery = true)
	public String hostelName(Long hostel_id);

	@Query(value = "SELECT hostel_type FROM HOSTEL WHERE  id=?1", nativeQuery = true)
	public String hostelType(Long hostel_id);

	@Query(value = "SELECT hostel_address FROM HOSTEL WHERE id=?1", nativeQuery = true)
	public String hostelAddress(Long hostel_id);

	@Query(value = "SELECT room_type FROM ROOM WHERE hostel_id=?1", nativeQuery = true)
	public Set<String> roomTypes(Long Hostel_id);

	@Query(value = "select t1.floor_name, t2.id ,t2.room_name, t2.room_rent,t2.room_type, t3.alloted,t3.bed_no,t3.position\n"
			+ "from floor t1 inner join room t2 on t1.hostel_id = t2.hostel_id\n"
			+ "inner join bed t3 on t2.hostel_id=t3.hostel_id where t1.hostel_id=?1", nativeQuery = true)
	public List<Object[]> getHostelInfo(Long hostelId);

	@Query(value = "select room_name,room_type from room where room.hostel_id=?1", nativeQuery = true)
	public List<Object[]> getRoomDetails(Long hostelId);

	@Query(value = "select * from Hostel where id=?1 ", nativeQuery = true)
	public Hostel getHosteldetails(Long hostelId);

	@Query(value = "select * from Room where id=?1 ", nativeQuery = true)
	public Hostel getRoomdetails(Long hostelId);

	@Query(value = "select * from Bed where id=?1 ", nativeQuery = true)
	public Hostel getBeddetails(Long hostelId);

	public default Hostel saveWholeObject(Hostel hostel) {

		Hostel tmpHostel = new Hostel();

		tmpHostel = this.save(hostel);

		hostel.getfloors().forEach(floor -> floorRepository.saveWholeObject(floor));

		tmpHostel.setfloors(hostel.getfloors());

		return tmpHostel;

	}
	

}