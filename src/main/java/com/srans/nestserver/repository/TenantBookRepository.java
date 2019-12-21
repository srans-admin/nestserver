package com.srans.nestserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.TenantBooking;

public interface TenantBookRepository extends JpaRepository<TenantBooking, Long>{
	
	@Query(value = "select count(bookingid) from tenantbooking where extract (month from created_at)  =?1 group by extract (year from created_at),extract (month from created_at) order by extract (year from created_at),extract (month from created_at)", nativeQuery = true)
	public Integer getTotalBookedBed(Integer monthId);
}
