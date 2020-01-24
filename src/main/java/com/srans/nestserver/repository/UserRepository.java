package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	@Autowired
	TenantBookRepository tenantBookRepository = null;

	@Autowired
	PaymentRepository paymentRepository = null;

	@Autowired
	HostelRepository hostelRepository = null;

	@Query(value = "SELECT u FROM User u WHERE u.name=?1")
	public User findByName(String name);
	
	/*
	 * @Query(value =
	 * "SELECT room_rent FROM tenantbooking WHERE tenant_id=?1",nativeQuery = true)
	 * public TenantBooking findBytenantid(Long tenantId);
	 */

	@Query(value = "SELECT room_name, room_type, date, amount, deposit_amount, payment_type ,room_rent FROM payments where user_id=?1", nativeQuery = true)
	public User findByuserid(Long userId);

	@Query(value = "SELECT hostel_name FROM HOSTEL WHERE hostelName=?1", nativeQuery = true)
	public Hostel findByhostelName(String hostelName);

	@Query(value = "SELECT u FROM User u WHERE u.role=?1")
	public List<User> getUsersByRole(String role);

	@Query(value = "SELECT u FROM User u WHERE u.contactNumber=?1")
	public User findByContactNumber(Long contactNumber);

	/*
	 * @Query(value="SELECT * FROM PAYMENT WHERE userid=?1," nativeQuery = true)
	 * public User findByUserid(Long userId);
	 */

	/*
	 * @Query(value = "SELECT hostel_name FROM Hostel", nativeQuery = true) public
	 * List<String> getAllHostelName();
	 * 
	 * @Query(value = "SELECT id FROM HOSTEL WHERE hostel_name=?1", nativeQuery =
	 * true) public Long getHostelId(String hostelname);
	 * 
	 * @Query(value =
	 * "select t1.hostel_id, t1.floor_name, t2.id ,t2.room_name, t2.room_rent,t2.room_type, t3.alloted,t3.bed_no,t3.position\n"
	 * + "from floor t1 inner join room t2 on t1.hostel_id = t2.hostel_id\n" +
	 * "inner join bed t3 on t2.hostel_id=t3.hostel_id where t1.hostel_id=?1",
	 * nativeQuery = true) public List<Object[]> getBedInfo(Long hostelId);
	 */

	/*
	 * public default Tenant saveWholeObject(Tenant tenant){
	 * 
	 * Tenant tmpTenant = null;
	 * 
	 * tmpTenant = this.save(tenant);
	 * 
	 * tenant.getTenantBooking().forEach( tenantBooking
	 * ->tenantBookRepository.save(tenantBooking));
	 * 
	 * tmpTenant.setTenantBooking(tenant.getTenantBooking());
	 * 
	 * return tmpTenant;
	 * 
	 * }
	 */
}
