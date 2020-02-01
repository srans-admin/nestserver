package com.srans.nestserver.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Complaint;
@Repository
public interface ComplaintRepository extends JpaRepository<Complaint, Long> {
 
	@Query(value="SELECT description ,created_at FROM complaints  WHERE userid=?1 order by created_at desc ", nativeQuery = true)
	public List<Object> getDataForcomplaintHistory(Long userId);
	
	
	@Query(value="SELECT c FROM Complaint c WHERE c.userId=?1")
	public Optional<List<Complaint>> getCompliantsForUser(Long userId );
	
	@Query(value="SELECT c FROM Complaint c WHERE c.adminId=?1")
	public List<Complaint> getCompliantsForAdmin(Long adminId );
	
	
	@Query(value="SELECT h.admin_id FROM  Hostel h JOIN tenantbooking tb on (tb.hostel_id = h.id and tb.tenant_id = ?1 )", nativeQuery=true)
	public Optional<Object> getAdminIdForUser(Long userId );
	



	
}