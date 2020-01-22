package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.User;


@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
	
	
	@Autowired 
	public FloorRepository floorRepository = null ;
	
	@Autowired 
	public HostelRepository hostelRepository = null ;
	
	@Autowired 
	public RoomRepository roomRepository = null ; 
	
	
	@Autowired
	public UserRepository userRepository = null ;
	
	@Autowired
	public PaymentRepository paymentRepository=null;
	
	@Query(value ="SELECT room_type,ammount, created_at from payment where user_Id=?1 order by created_at desc;",nativeQuery = true)
	public List<Object> getDataForpaymentHistory(Long userId);
	
	@Query(value="SELECT room_type FROM ROOM WHERE id=?1", nativeQuery=true)
	public String roomtype(Long floor_id);
	
	
	@Query(value="SELECT room_name FROM ROOM WHERE id=?1", nativeQuery=true)
	public Long roomName(Long floor_id);
	
	
	@Query(value="SELECT room_rent FROM ROOM WHERE id=?1", nativeQuery=true)
	public Long roomRent(Long floor_id);
	
	
	@Query(value="SELECT u FROM User u WHERE u.contactNumber=?1")
	public User findByContactNumber(Long contactNumber);
  
	

	@Query(value="SELECT u FROM User u WHERE u.name=?1") 
	public User findByName(String name);
	
}