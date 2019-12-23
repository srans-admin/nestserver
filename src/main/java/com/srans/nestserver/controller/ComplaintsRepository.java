package com.srans.nestserver.controller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.srans.nestserver.model.Complaints;
@Repository
public interface ComplaintsRepository extends JpaRepository<Complaints, Long> {
}
