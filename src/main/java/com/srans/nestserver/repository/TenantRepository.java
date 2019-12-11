package com.srans.nestserver.repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long>{

	@Query(value="SELECT hostel_name FROM Hostel", nativeQuery=true) 
	   public List<String> getAllHostelName();

}
