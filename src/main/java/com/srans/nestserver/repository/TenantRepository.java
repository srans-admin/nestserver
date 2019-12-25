package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	@Autowired
	TenantBookRepository tenantBookRepository = null; 
	
	
	@Query(value="SELECT t FROM Tenant t WHERE t.name=?1") 
	public Tenant findByName(String name);
 
 }
