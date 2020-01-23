package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Bed;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.repository.VacationRepository;

@Service
public class VacateService {
	private Logger logger = LoggerFactory.getLogger(VacateService.class);

	@Autowired
	private VacationRepository vacationRepository;

	@Autowired
	private NotificationService notificationService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private TenantBookRepository tenantBookRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BedRepository bedRepo;

	public Vacation processVacation(Vacation vacate) {

		logger.debug("IN::processVacation::");
		Vacation responseVacation = null;

		try {
			// STEP-1: Save Vaction Details
			responseVacation = vacationRepository.save(vacate);

			// STEP-2 : Prepare one Notification to Admin(s)
			notificationService.tenantVacatedNotifictaion(vacate);

			// STEP-3 : Trigger The Mail For Tenant
			User userResponse = userRepo.getOne(responseVacation.getTenantId());
			System.out.println(userResponse.getEmailId());
			tenantService.triggerAlertEmail(userResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return (responseVacation);
	}

	public Vacation getVacationDetails(Long tenantId) {

		return (vacationRepository.getOne(tenantId));
	}

	public String approveVacation(Long tenantId) {

		// Upadte Approved status

		Long status =vacationRepository.findVacationId(tenantId);
		Vacation vacate = vacationRepository.getOne(status);
		vacate.setApprovedStatus('N');
		vacationRepository.save(vacate);

		// Update bed status
		Long roomBedId = vacationRepository.findRoomBedId(tenantId);
		System.out.println(roomBedId);

		Bed bed = bedRepo.getOne(roomBedId);
		System.out.println(bed.getAlloted());
		bed.setAlloted('Y');
		bedRepo.save(bed);

		// Update Tenant Status
		User user = userRepo.getOne(tenantId);
		user.setStatus("NA");
		userRepo.save(user);

		// Trigger Email
		tenantService.triggerAlertEmail(user);

		// Trigger SMS
		tenantService.triggerSMS(user);

		return ("vacate tenant Successfully");

	}

}
