package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.SubAdminDetails;

@Repository
public interface SubAdminDetailsRepository extends JpaRepository<SubAdminDetails, Long> {
	
	@Query(value="select count(sub_admin_id) from admin_details where sub_admin_id=?1", nativeQuery = true)
	public Long checkSubAdminDetails(Long subAdminId);

}
