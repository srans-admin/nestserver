package com.srans.nestserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.ComplaintsComment;
@Repository
public interface ComplaintsCommentRepository extends JpaRepository<ComplaintsComment, Long> {
	
	/*
	 * @Query(
	 * value="SELECT hostel_owner_id FROM HOSTEL WHERE hostel_id IN ('SELECT hostel_id FROM TENANTBOOKING where user_id=?1')"
	 * ,nativeQuery = true ) public Long hostelownerid(Long hostel_owner_id); public
	 * Long id(Long hostel_id);
	 */
	
	
	/*
	 * @Query(
	 * value="SELECT * FROM complaint as com, INNERJOIN complaintcomments as comcom ON('com.id=comcom.complaint_id'),INNERJOIN hostel as hos ON('comcom.hostel_owner_id=hos.hostel_owner_id')WHERE hos.hostel_id=?1')"
	 * ,nativeQuery = true ) public Long hostelownerid(Long hostel_owner_id); public
	 * Long id(Long hostel_id);
	 */
}

