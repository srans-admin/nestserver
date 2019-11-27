package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Management;

@Repository
public interface ManagementRepository extends  JpaRepository<Management ,Long>{

}
