/**
 * 
 */
package com.srans.nestserver.service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
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
import com.srans.nestserver.communication.NiodsSmsGateway;
import com.srans.nestserver.model.Floor;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.VacationRepository;
import com.srans.nestserver.util.NSConstants;
import com.srans.nestserver.util.SMSTemplates;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class TenantService {

	private static Logger logger = LoggerFactory.getLogger(TenantService.class);

	@Autowired
	private NiodsMailer niodsMailer;

	@Autowired
	private NiodsSmsGateway niodsSmsGateway;

	@Autowired
	private TenantBookRepository tenantBookRepository;

	@Autowired
	private PaymentRepository paymentRepo;

	@Autowired
	private VacationRepository vacationRepo;

	@Autowired
	private TemplateEngine templateEngine;

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private FloorRepository floorRepository;

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private BedRepository bedRepository;

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

		if (user.getRole().endsWith(NSConstants.ROLE_TENANT) && user.getStatus().equals("A")) {
			reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
			reqParamtersMap.put("name", user.getName());

			reqParamtersMap.put("hostel_name",
					(hostelRepo.getOne(user.getTenantBooking().getHostelId())).getHostelName());
			reqParamtersMap.put("floor_number", user.getTenantBooking().getFloorId());
			reqParamtersMap.put("room_number", user.getTenantBooking().getRoomId());
			reqParamtersMap.put("room_rent", user.getTenantBooking().getRoomRent());
			reqParamtersMap.put("bed", user.getTenantBooking().getRoomBedId());
		}

		else if (user.getRole().endsWith(NSConstants.ROLE_TENANT) && user.getStatus().equals("NA")) {

			reqParamtersMap.put("name", user.getName());

		} else if (user.getRole().endsWith(NSConstants.ROLE_GUEST)) {

			Hostel hostelInfo = hostelRepo.getOne(user.getTenantBooking().getHostelId());
			Floor floorInfo = floorRepository.getOne(user.getTenantBooking().getFloorId());
			Room roomInfo = roomRepository.getOne(user.getTenantBooking().getRoomId());

			reqParamtersMap.put("Name", user.getName());
			reqParamtersMap.put("HosteName", hostelInfo.getHostelName());
			reqParamtersMap.put("Floor", floorInfo.getFloorName());
			reqParamtersMap.put("Room", roomInfo.getRoomName());
			reqParamtersMap.put("Bed", user.getTenantBooking().getRoomBedId());
			reqParamtersMap.put("RoomRent", roomInfo.getRoomRent());
			LocalDate localDate = user.getTenantBooking().getAllotedTill().toInstant().atZone(ZoneId.systemDefault())
					.toLocalDate();
			reqParamtersMap.put("LastDate", localDate);

		}

		else if (user.getRole().endsWith(NSConstants.ROLE_ADMIN)) {
			reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
			reqParamtersMap.put("name", user.getName());
			reqParamtersMap.put("subscription", user.getSubscriptions());
		}

		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}

	public boolean triggerAlertEmail(User responseTenant) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerAlertEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = responseTenant.getEmailId();
			System.out.println(email);
			subject = "NIDOS Confirmation";
			ccMail = null;
			bccMail = null;
			System.out.println(responseTenant.getRole());
			Long checkId = vacationRepo.checkTenantId(responseTenant.getUserId());
			System.out.println(checkId);

			if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT) && checkId == 0) {

				message = this.getTemplate("tenant_booking", responseTenant);

			}

			else if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT)
					&& responseTenant.getStatus().equals("NA")) {

				message = this.getTemplate("vacation_approved", responseTenant);
			}

			else if (responseTenant.getRole().endsWith(NSConstants.ROLE_ADMIN)) {
				message = this.getTemplate("admin_subscription", responseTenant);
			}

			else if (responseTenant.getRole().endsWith(NSConstants.ROLE_USER)
					|| responseTenant.getRole().endsWith(NSConstants.ROLE_GUEST)) {
				message = this.getTemplate("guest_confirmation", responseTenant);

			} else {
				message = "User added ...";
			}
			niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);

			System.out.println("EMIAL SENT ----------------------------------");
			emailStatus = true;

			logger.debug("Out::triggerAlertEmail");

		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TemplateException e) {
			e.printStackTrace();
		}

		return emailStatus;

	}

	public boolean triggerSMS(User responseTenant) {

		logger.debug("In::triggerSMS");
		boolean smsStatus = false;

		try {
			String message = "";

			if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT) && responseTenant.getStatus().equals("A")) {

				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE
						.replaceAll("##USER_NAME##", responseTenant.getName())
						.replaceAll("##PASSWORD##", responseTenant.getName()).replaceAll("##HOSTEL_NAME##", "NIODS")
						.replaceAll("##ROOM_NUMBER##", "" + responseTenant.getTenantBooking().getRoomName())
						.replaceAll("##FLOOR_NUMBER##", "" + responseTenant.getTenantBooking().getFloorName())
						.replaceAll("##ROOM_RENT##", "" + responseTenant.getTenantBooking().getRoomRent());

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT)
					&& responseTenant.getStatus().equals("A")) {
				message = SMSTemplates.TENANT_INVOICE_TEMPLATE;
			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT)
					&& responseTenant.getStatus().equals("NA")) {
				message = SMSTemplates.VACATE_TENANT_MESSAGE_TEMPLATE;
			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_USER)) {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_ADMIN)) {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_GUEST)) {

				Hostel hostelInfo = hostelRepo.getOne(responseTenant.getTenantBooking().getHostelId());
				Floor floorInfo = floorRepository.getOne(responseTenant.getTenantBooking().getFloorId());
				Room roomInfo = roomRepository.getOne(responseTenant.getTenantBooking().getRoomId());

				message = SMSTemplates.GUEST_TEMPLATE.replaceAll("USER_NAME",
						responseTenant.getName().replaceAll("HOSTEL_NAME", hostelInfo.getHostelName())
								.replaceAll("FLOOR_NUMBER", floorInfo.getFloorName())
								.replaceAll("ROOM_NUMBER", roomInfo.getRoomName())
								.replaceAll("ROOM_RENT", String.valueOf(roomInfo.getRoomRent()))
								.replaceAll("BED_NUMBER",
										String.valueOf(responseTenant.getTenantBooking().getRoomBedId()))
								.replaceAll("LAST_DATE", responseTenant.getTenantBooking().getAllotedTill().toString())

				);

			} else {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;
			}

			niodsSmsGateway.sendSMS("" + responseTenant.getContactNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerAlertEmail");

		} catch (Exception e) {

			e.printStackTrace();
		}

		return smsStatus;

	}

}
