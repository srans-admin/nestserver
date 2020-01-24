package com.srans.nestserver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
	
	@Query(value = "select count(tenant_id) from vacation where tenant_id=?1 ",nativeQuery = true)
	public Long checkTenantId(Long tenantId);


	

	
	

}
