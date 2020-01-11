package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.AdminDetails;

@Repository
public interface AdminDetailsRepository extends JpaRepository<AdminDetails, Long> {
	
	

}
