package com.srans.nestserver.service;

import java.io.IOException;
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
import com.srans.nestserver.util.NSConstants;

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
	
	Long floorId;
	Long roomId;
	Long roomRent;
	Long roomBedId;
	

	public Vacation processVacation(Vacation vacate) throws MailException, MessagingException, IOException, TemplateException {
		
		
		String email = null, subject = null, ccMail = null, bccMail = null, message = null;
		logger.debug("IN::processVacation::");
		Vacation responseVacation = null;

		try {
			// STEP-1: Save Vaction Details
			vacate.setApprovedStatus('N');
			responseVacation = vacationRepository.save(vacate);

			// STEP-2 : Prepare one Notification to Admin(s)
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
			//tenantService.triggerAlertEmail(userResponse);
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
		logger.trace("In::");
		reqParamtersMap = new HashMap<>();

		System.out.println(user.getUserId());
		reqParamtersMap.put("name", user.getName());
		Long hostelid=tenantBookRepo.findHostelId(user.getUserId());
		
		tenantBookRepo.findAll().forEach(tenantInfo ->{
			if(tenantInfo.getTenantId()==user.getUserId()) {
				 floorId=tenantInfo.getFloorId();
				 roomId=tenantInfo.getRoomId();
				 roomRent=tenantInfo.getRoomRent();
				 roomBedId=tenantInfo.getRoomBedId();
				 
			}
		});
		
		
		
		reqParamtersMap.put("hostel_name", (hostelRepo.getOne(hostelid).getHostelName()));
		System.out.println();
		reqParamtersMap.put("floor_number", floorId);
		reqParamtersMap.put("room_number", roomId);
		reqParamtersMap.put("room_rent", roomRent);	
		reqParamtersMap.put("bed", roomBedId);

		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
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
		//tenantService.triggerAlertEmail(user);

		// Trigger SMS
		tenantService.triggerSMS(user);

		return ("vacate tenant Successfully");

	}

}
