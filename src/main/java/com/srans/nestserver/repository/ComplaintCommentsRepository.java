
package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Complaint;
import com.srans.nestserver.model.ComplaintComment;

@Repository
public interface ComplaintCommentsRepository extends JpaRepository<ComplaintComment, Long >{

	
	@Query(value="SELECT h.adminid , tb.tenant_id, cmpt.description, cmpt.status FROM HOSTEL h,complaints cmpt, tenantbooking tb WHERE tb.hostel_id = h.id  and	 tb.tenant_id = cmpt.userid	and tb.tenant_id= ?1", nativeQuery = true)
	public Long adminid(Long adminid); 
	
	@Query(value="SELECT cc FROM ComplaintComment cc WHERE cc.complaintId=?1 ORDER by resolutionDate")
	public List<ComplaintComment> getAllComments(Long commentId );
	
	
	
	
	/*
	/*
	 * @Query(value =
	 * "SELECT hostel_owner_id FROM HOSTEL WHERE hostel_id IN ('SELECT hostel_id FROM TENANTBOOKING where user_id=?1')"
	 * , nativeQuery = true) public Long hostelownerid(Long hostel_owner_id);
	 * 
	 * public Long id(Long hostel_id);
	 * 
	 * @Query(value =
	 * "SELECT * FROM complaint as com, INNERJOIN complaintcomments as comcom ON('com.id=comcom.complaint_id'),INNERJOIN hostel as hos ON('comcom.hostel_owner_id=hos.hostel_owner_id')WHERE hos.hostel_id=?1')"
	 * , nativeQuery = true) public Long hostelownerid(Long hostel_owner_id);
	 * 
	 * public Long id(Long hostel_id);
	 */
	
	

}
