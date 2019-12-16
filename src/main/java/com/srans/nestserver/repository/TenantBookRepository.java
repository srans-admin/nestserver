package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.TenantBooking;


@Repository
public interface TenantBookRepository extends JpaRepository<TenantBooking, Long>{
	
	

}
