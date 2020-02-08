package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Autowired 
	TenantBookRepository tenantBookRepository = null; 
	
	@Autowired
	public PaymentRepository paymentRepository=null;
	
	@Query(value="SELECT u FROM User u WHERE u.name=?1") 
	public User findByName(String name);
	
	
	/*
	 * @Query(
	 * value="SELECT room_name, room_type, date, ammount, deposit_amount, payment_through FROM PAYMENT where user_id=?1"
	 * ) public User findByuserid(Long userId);
	 * 
	 */
	
	@Query(value="SELECT u FROM User u WHERE u.role=?1 order by u.userId desc") 
	public List<User> getUsersByRole(String role);
 
	// Geting All Consolidated Hostel Details 
	@Query(value = "select t1.id, t1.hostel_name, t1.Hostel_address,t1.admin_id, t2.name, t2.email_id, t2.contact_number, t2.subscriptions, t3.ammount from hostel t1 inner join srans_user t2\r\n" + 
			"	on t1.admin_id=t2.user_id inner join payment t3 on t2.user_id=t3.admin_id where t2.role=?1", nativeQuery = true)
	public List<Object> getConsolidatedHostel(String role);
 
	
	@Query(value="SELECT u from TenantBooking tb "+
	  "join User u on (tb.tenantId = u.userId and u.role=?2) "+
	  "join Hostel h on (tb.hostelId = h.id and h.adminId = ?1) where tb.active='Y' order by u.userId desc ") 
	public List<User> getUsersForAdmin(long adminId, String role);
	
	
	
	
	/*
	 * @Query(value="SELECT * FROM PAYMENT WHERE userid=?1," nativeQuery = true)
	 * public User findByUserid(Long userId);
	 */
  
	/*
	@Query(value = "SELECT hostel_name FROM Hostel", nativeQuery = true)
	public List<String> getAllHostelName();

	@Query(value = "SELECT id FROM HOSTEL WHERE hostel_name=?1", nativeQuery = true)
	public Long getHostelId(String hostelname);

	@Query(value = "select t1.hostel_id, t1.floor_name, t2.id ,t2.room_name, t2.room_rent,t2.room_type, t3.alloted,t3.bed_no,t3.position\n"
			+ "from floor t1 inner join room t2 on t1.hostel_id = t2.hostel_id\n"
			+ "inner join bed t3 on t2.hostel_id=t3.hostel_id where t1.hostel_id=?1", nativeQuery = true)
	public List<Object[]> getBedInfo(Long hostelId);
	*/
	
	
	
	/*public default Tenant saveWholeObject(Tenant tenant){
		
		Tenant tmpTenant = null;
		
		tmpTenant = this.save(tenant);
		
		tenant.getTenantBooking().forEach( tenantBooking ->tenantBookRepository.save(tenantBooking));
		
		tmpTenant.setTenantBooking(tenant.getTenantBooking());
		
		return tmpTenant;
		
	}*/ 
 }
