package com.srans.nestserver.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Vacation;

@Repository
public interface VacationRepository extends JpaRepository<Vacation, Long> {

	@Query(value = "select id from vacation WHERE tenant_id=?1",nativeQuery = true)
	public Long findVacationId(Long tenantId);
	
	@Query(value = "select room_bed_id from tenantbooking  WHERE tenant_id=?1",nativeQuery = true)
	public Long findRoomBedId(Long tenantId);
	
	@Query(value = "select approved_status from vacation  WHERE tenant_id=?1",nativeQuery = true)
	public Character checkApprovedStatus(Long tenantId);
	
	@Query(value = "select count(tenant_id) from vacation where tenant_id=?1 AND approved_status='N' ",nativeQuery = true)
	public Long checkTenantId(Long tenantId);
	
	@Query(value = "SELECT refundAmount from Vacation where tenantId=?1" )
	public Long getRefundAmount(Long tenantId);
	
	@Query(value = "SELECT v from Vacation v where v.tenantId=?1 AND v.approvedStatus='N'")
	public List<Vacation> getVacationRequest(Long tenantId);
	
	


	

	
	

}
