package com.srans.nestserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.srans.nestserver.model.Invoice;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, Long>{
	
	@Query (value="select t1.name, t1.email_id, t2.room_rent, t2.room_id, t2.hostel_id,t2.\r\n" + 
			"floor_name,t3.bank_name from tenant t1 inner join tenantbooking t2 on t1.user_id=t2.tenant_id\r\n" + 
			"inner join payment t3 on t2.booking_id=t3.bookingid where user_id=?1",nativeQuery=true)
	       public List<Object> invoiceData (long userId);

}
