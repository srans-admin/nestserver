package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.User;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.repository.VacationRepository;

@Service
public class VacateService {
	private Logger logger = LoggerFactory.getLogger(VacateService.class);

	@Autowired
	private VacationRepository vactionRepository;

	@Autowired
	private NotificationService notificationService;
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private TenantService tenantService;

	public Vacation processVacation(Vacation vacate) {

		logger.debug("IN::processVacation::");
		Vacation responseVacation = null;

		try {
			// STEP-1: Save Vaction Details
			responseVacation = vactionRepository.save(vacate);

			// STEP-2 : Prepare one Notification to SuperAdmin(s)
			notificationService.tenantVacatedNotifictaion(vacate);

			// STEP-3 : Trigger The Mail For Tenant
			User userResponse=userRepo.getOne(responseVacation.getTenantId());			
			System.out.println(userResponse.getEmailId());
				tenantService.triggerAlertEmail(userResponse);
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return (responseVacation);
	}

}
