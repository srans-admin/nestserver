package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.TenantBooking;

public interface TenantBookRepository extends JpaRepository<TenantBooking, Long> {

	List<TenantBooking> findByTenantId(Long tenant_id);

	@Query(value = "select count(bookingid) from tenantbooking where extract (month from created_at)  =?1 group by extract (year from created_at),extract (month from created_at) order by extract (year from created_at),extract (month from created_at)", nativeQuery = true)
	public Integer getTotalBookedBed(Integer monthId);

	@Query(value = "select count(guest_id) from tenantbooking where guest_id=?1", nativeQuery = true)
	public Long findGuestId(Long id);
	
	@Query(value="select guest_id from tenantbooking where room_bed_id=?1",nativeQuery=true)
     public Long findByGuestId(Long roomBedId); 
	
	@Query(value="select hostel_id from tenantbooking where tenant_id=?1",nativeQuery = true)
	public Long findHostelId(Long tenant_id);
	
	
	@Query(value="SELECT tenant_id from tenantbooking where active='Y'",nativeQuery=true)
	public Long[] getAllTenantId();
	
	
	@Query(value="SELECT t from TenantBooking t where t.tenantId=?1")
	public TenantBooking getTenantBookedInfoForUser(Long tenantId); 
	

}
