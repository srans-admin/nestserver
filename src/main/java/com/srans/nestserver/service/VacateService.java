package com.srans.nestserver.service;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.model.Bed;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.repository.VacationRepository;

import freemarker.template.TemplateException;

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
	private NiodsMailer niodsMailer;

	@Autowired
	private TenantBookRepository tenantBookRepo;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private BedRepository bedRepo;

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private TemplateEngine templateEngine;

	private Long floorId;
	private Long roomId;
	private Long roomRent;
	private Long roomBedId;
	private Date vacateDate;
	private Long refundAmount;

	public Vacation processVacation(Vacation vacate)
			throws MailException, MessagingException, IOException, TemplateException {

		String email = null, subject = null, ccMail = null, bccMail = null, message = null;
		logger.debug("IN::processVacation::");
		Vacation responseVacation = null;

		try {
			// STEP-1: Save vacation Details
			vacate.setApprovedStatus('N');
			responseVacation = vacationRepository.save(vacate);
			
			vacateDate=(Date) vacate.getDate();
			refundAmount=vacate.getRefundAmount();

			// STEP-2 : Prepare One Notification to Admin
			notificationService.tenantVacatedNotifictaion(vacate);

			// STEP-3 : Trigger The Mail For Tenant
			User userResponse = userRepo.getOne(responseVacation.getTenantId());
			System.out.println(userResponse.getEmailId());

			logger.debug("In::processVacation");

			email = userResponse.getEmailId();
			System.out.println(email);
			subject = "NIDOS Confirmation";
			ccMail = null;
			bccMail = null;
			// tenantService.triggerAlertEmail(userResponse);
			System.out.println(userResponse.getRole());
			Long checkId = vacationRepository.checkTenantId(userResponse.getUserId());
			System.out.println(checkId);

			message = this.getTemplate("vacation_confirmation", userResponse);

		} catch (Exception e) {
			e.printStackTrace();
		}

		niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);

		System.out.println("EMIAL SENT ----------------------------------");

		logger.debug("Out::processVacation");

		return (responseVacation);
	}

	/**
	 * 
	 * @param templateFileName Name of the template file without extension
	 * @param text
	 * @return
	 */

	Map<String, Object> reqParamtersMap;

	public String getTemplate(String templateFileName, User user) {
		logger.trace("In:: getTemplate" + templateFileName);
		reqParamtersMap = new HashMap<>();

		System.out.println(user.getUserId());
		reqParamtersMap.put("name", user.getName());
		reqParamtersMap.put("contactNumber", user.getContactNumber());
		reqParamtersMap.put("informDate", LocalDate.now());
		
		Long hostelid = tenantBookRepo.findHostelId(user.getUserId());

		tenantBookRepo.getTenantBookingInfo(user.getUserId()).stream().forEach(bookingInfo -> {
			if (bookingInfo.getTenantId() == user.getUserId()) {
				floorId = bookingInfo.getFloorId();
				roomId = bookingInfo.getRoomId();
				roomRent = bookingInfo.getRoomRent();
				roomBedId = bookingInfo.getRoomBedId();
				

			}
		});

		reqParamtersMap.put("hostelName", (hostelRepo.getOne(hostelid).getHostelName()));
		reqParamtersMap.put("FloorNumber", floorId);
		reqParamtersMap.put("roomNumber", roomId);
		reqParamtersMap.put("roomRent", roomRent);
		reqParamtersMap.put("bedNumber", roomBedId);
		reqParamtersMap.put("vacateDate", vacateDate);
		reqParamtersMap.put("refundAmount",refundAmount );
		Date lastPayment=paymentRepo.lastPayment(user.getUserId());
		reqParamtersMap.put("lastPayment", lastPayment);
		

		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::getTemplate" + templateFileName);
		return output;
	}

	public Vacation getVacationDetails(Long tenantId) {

		return (vacationRepository.getOne(tenantId));
	}

	public void approveVacation(Long tenantId) {
		logger.debug("IN::approveVacation::" + tenantId);
		// Update Approved status

		Long vacationId = vacationRepository.findVacationId(tenantId);
		Vacation vacate = vacationRepository.getOne(vacationId);
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
		user.setStatus("NA"); // not active
		userRepo.save(user);

		// Trigger Email
		tenantService.triggerAlertEmail(user);

		// Trigger SMS
		tenantService.triggerSMS(user);
		logger.debug("OUT::approveVacation::" + tenantId);

	}

}
