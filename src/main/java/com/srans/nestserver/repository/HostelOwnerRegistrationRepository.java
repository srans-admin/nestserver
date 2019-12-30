package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.srans.nestserver.model.HostelOwnerRegistration;
@Repository
public interface HostelOwnerRegistrationRepository extends JpaRepository<HostelOwnerRegistration, Long> {
}
