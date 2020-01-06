package com.srans.nestserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.model.Complaints;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.ComplaintsRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;

/**
 * @author user
 *
 */
@Service
public class ComplaintService {

	private Logger logger = LoggerFactory.getLogger(ComplaintService.class);

	@Autowired
	private HostelRepository hostelRepository;

	@Autowired
	private NiodsMailer niodsMailer;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private TenantBookRepository tenantBookRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BedRepository bedRepository;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private TenantToUaaService tenantToUaaService;

	@Autowired
	private NotificationService notificationService;
	@Autowired
	private ComplaintsRepository complaintRepository;
	
	@Autowired
	private Complaints complaint;

	/**
	 * 
	 * @param user
	 * @return
	 */
	public Complaints processComplaints() {

		Complaints complaints=new Complaints();
		complaints.setId(complaint.getId());
		complaints.setDescription(complaint.getDescription());
		complaints.setUserid(complaint.getUserid());
		complaints.setStatus("open");
		
	return(complaints);
	}
}

	