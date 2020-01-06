package com.srans.nestserver.controller;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.srans.nestserver.model.ComplaintsComment;
@Repository
public interface ComplaintsCommentRepository extends JpaRepository<ComplaintsComment, Long> {
}

