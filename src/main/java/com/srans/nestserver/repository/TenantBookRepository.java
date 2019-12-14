package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.srans.nestserver.model.TenantBooking;

public interface TenantBookRepository extends JpaRepository<TenantBooking, Long>{
	
	

}
