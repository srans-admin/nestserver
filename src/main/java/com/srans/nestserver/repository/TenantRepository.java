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
	
	
	
	@Query(value="SELECT id FROM HOSTEL WHERE hostel_name=?1", nativeQuery=true)
	  public Long  getHostelId(String hostelname);
	
	@Query(value="select * from "
			+ "hostel h,floor f, room r where "
			+ "h.id = f.hostel_id "
			+ "and f.id = r.floor_id "
			+ "and h.id = r.hostel_id "
			+ "and h.id=?1"  
			, nativeQuery=true)
	 public List<Object[]> getBedInfo(Long hostelId);
	
	// public List<Object[]>  getTotalNumberOfFloors(String hostelname);

}
