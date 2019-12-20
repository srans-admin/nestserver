package com.srans.nestserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.srans.nestserver.model.TenantBooking;

public interface TenantBookRepository extends JpaRepository<TenantBooking, Long>{
	
	@Query(value = "SELECT COUNT(bookingid) from TENANTBOOKING WHERE EXTRACT (month FROM CREATED_AT)  =?1 GROUP BY EXTRACT (year FROM CREATED_AT),EXTRACT (month FROM CREATED_AT) ORDER BY EXTRACT (YEAR FROM CREATED_AT),EXTRACT (month FROM CREATED_AT);", nativeQuery = true)
	public Integer getTotalBookedBed(Integer monthId);
}
