package com.srans.nestserver.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

	@Autowired
	public FloorRepository floorRepository = null;

	@Autowired
	public HostelRepository hostelRepository = null;

	@Autowired
	public RoomRepository roomRepository = null;

	@Autowired
	public UserRepository userRepository = null;

	@Autowired
	public PaymentRepository paymentRepository = null;

//	@Query(value = "select tb.room_name,tb.room_type,tb.room_rent,name,user_id,py.created_at\r\n"
//			+ "from tenantbooking tb\r\n" + "inner join payments py\r\n" + "on tb.tenant_id = py.user_id\r\n"
//			+ "where user_id =1;", nativeQuery = true)
//
//	public List<Object> getDataForpaymentHistory(Long userId);

	@Query(value = "SELECT p FROM Payment p WHERE p.userId=?1" )
	public List<Payment> getPaymentHistory(Long userId);
	
	@Query(value = "SELECT p FROM Payment p WHERE p.adminId=?1" )
	public List<Payment> getAllUserPaymentsForAdmin(Long adminId);
	
	@Query(value = "SELECT room_type FROM ROOM WHERE id=?1", nativeQuery = true)
	public String roomtype(Long floor_id);

	@Query(value = "SELECT room_name FROM ROOM WHERE id=?1", nativeQuery = true)
	public Long roomName(Long floor_id);

	@Query(value = "SELECT room_rent FROM ROOM WHERE id=?1", nativeQuery = true)
	public Long roomRent(Long floor_id);

	@Query(value = "SELECT created_at, room_bed_id from payment where ammount_type='ReservedBedAmount'", nativeQuery = true)
	public List<Object> guestBedInfo();

	@Query(value = "select count(room_bed_id) from payment where room_bed_id=?1", nativeQuery = true)
	public Long findRoomBedId(Long bedId);
	
	@Query(value="select created_at from payment where user_id=?1 order by created_at desc limit 1",nativeQuery = true)
	public Date lastPayment(Long tenantId);

}