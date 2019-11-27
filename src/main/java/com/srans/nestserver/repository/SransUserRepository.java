package com.srans.nestserver.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.SransUser;

@Repository
public interface SransUserRepository extends JpaRepository<SransUser, Long>{

	


}
