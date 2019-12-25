package com.srans.nestserver.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Tenant;

@Repository
public interface TenantRepository extends JpaRepository<Tenant, Long> {

	@Autowired 
	TenantBookRepository tenantBookRepository = null; 
	
	
	@Query(value="SELECT t FROM Tenant t WHERE t.name=?1") 
	public Tenant findByName(String name);
  
	/*
	@Query(value = "SELECT hostel_name FROM Hostel", nativeQuery = true)
	public List<String> getAllHostelName();

	@Query(value = "SELECT id FROM HOSTEL WHERE hostel_name=?1", nativeQuery = true)
	public Long getHostelId(String hostelname);

	@Query(value = "select t1.hostel_id, t1.floor_name, t2.id ,t2.room_name, t2.room_rent,t2.room_type, t3.alloted,t3.bed_no,t3.position\n"
			+ "from floor t1 inner join room t2 on t1.hostel_id = t2.hostel_id\n"
			+ "inner join bed t3 on t2.hostel_id=t3.hostel_id where t1.hostel_id=?1", nativeQuery = true)
	public List<Object[]> getBedInfo(Long hostelId);
	*/
	
	
	
	/*public default Tenant saveWholeObject(Tenant tenant){
		
		Tenant tmpTenant = null;
		
		tmpTenant = this.save(tenant);
		
		tenant.getTenantBooking().forEach( tenantBooking ->tenantBookRepository.save(tenantBooking));
		
		tmpTenant.setTenantBooking(tenant.getTenantBooking());
		
		return tmpTenant;
		
	}*/ 
 }
