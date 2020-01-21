/**
 * 
 */
package com.srans.nestserver.service;

import java.io.IOException;

import javax.mail.MessagingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Service;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.communication.NiodsSmsGateway;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.UserSubscription;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.util.MailTemplates;
import com.srans.nestserver.util.NSConstants;
import com.srans.nestserver.util.PasswordGenerator;
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

	public boolean triggerAlertEmail(User responseTenant) {

		boolean emailStatus = false;

		try {

			logger.debug("In::triggerAlertEmail");

			String email, subject, ccMail, bccMail, message = null;
			email = responseTenant.getEmailId();
			System.out.println(email);
			subject = "Welcome to Hostel ";
			ccMail = null;
			bccMail = null;
			System.out.println(responseTenant.getRole());

			if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT)) {

				message = MailTemplates.ADMIN_VACATED_NOTIFICATION_TEMPLATE.replaceAll("##NAME##",
						responseTenant.getName());
				// hostelRepository.getHostelName(responseTenant.getTenantBooking().getHostelId())
				// )
				// .replaceAll("##ROOM_NUMBER##", "" +
				// responseTenant.getTenantBooking().getRoomName())
				// .replaceAll("##FLOOR_NUMBER##", "" +
				// responseTenant.getTenantBooking().getFloorName())
				// .replaceAll("##ROOM_RENT##", "" +
				// responseTenant.getTenantBooking().getRoomRent());

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_USER)) {
				message = MailTemplates.TENANT_REGISTRATION_TEMPLATE
						.replaceAll("##USER_NAME##", " " + responseTenant.getName())
						.replaceAll("##PASSWORD##", responseTenant.getName()).replaceAll("##HOSTEL_NAME##", "NIODS");

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_GUEST)) {

				try {
					TenantBooking guestInfo = new TenantBooking();

					if (tenantBookRepository.findGuestId(responseTenant.getUserId()) != 0 && paymentRepo.findRoomBedId(
							tenantBookRepository.getOne(responseTenant.getUserId()).getRoomBedId()) == 0) {
						guestInfo = tenantBookRepository.getOne(responseTenant.getUserId());
						message = MailTemplates.GUEST_BOOKING_TEMPLATE.replaceAll("##NAME##", responseTenant.getName())
								.replaceAll("##hostelId##", (guestInfo.getHostelId()).toString())
								.replaceAll("##floorName##", guestInfo.getFloorName())
								.replaceAll("##roomId##", (guestInfo.getRoomId()).toString())
								.replaceAll("##roomBedId##", (guestInfo.getRoomBedId()).toString());
					} else if (responseTenant.getUserId() != null
							&& tenantBookRepository.findGuestId(responseTenant.getUserId()) == 0) {

						String s = responseTenant.getUserSubscriptionWrapper().getUser().getPassword();
						System.out.println(s);

						message = MailTemplates.GUEST_REGISTRATION_TEMPLATE
								.replaceAll("##NAME##", responseTenant.getName())
								.replaceAll("##USER_NAME##", responseTenant.getName()).replaceAll("##PASSWORD##", s);
					} else if (paymentRepo.findRoomBedId(
							tenantBookRepository.getOne(responseTenant.getUserId()).getRoomBedId()) != 0) {
						guestInfo=tenantBookRepository.getOne(responseTenant.getUserId());

						message = MailTemplates.GUEST_AMOUNT_TEMPLATE.replaceAll("##NAME##", responseTenant.getName())							
								.replaceAll("##hostelId##", (guestInfo.getHostelId()).toString())
								.replaceAll("##floorName##", guestInfo.getFloorName())
								.replaceAll("##roomId##", (guestInfo.getRoomId()).toString())
								.replaceAll("##roomBedId##", (guestInfo.getRoomBedId()).toString());

					}

				} catch (Exception e) {
					e.printStackTrace();
				}

			} else {
				message = "User added ...";
			}
			niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);

			emailStatus = true;

			logger.debug("Out::triggerAlertEmail");

		} catch (MailException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return emailStatus;

	}

	public boolean triggerSMS(User responseTenant) {

		logger.debug("In::triggerSMS");
		boolean smsStatus = false;

		try {
			String message = "";

			if (responseTenant.getRole().endsWith(NSConstants.ROLE_TENANT)) {

				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE
						.replaceAll("##USER_NAME##", responseTenant.getName())
						.replaceAll("##PASSWORD##", responseTenant.getName()).replaceAll("##HOSTEL_NAME##", "NIODS")
						.replaceAll("##ROOM_NUMBER##", "" + responseTenant.getTenantBooking().getRoomName())
						.replaceAll("##FLOOR_NUMBER##", "" + responseTenant.getTenantBooking().getFloorName())
						.replaceAll("##ROOM_RENT##", "" + responseTenant.getTenantBooking().getRoomRent());
			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_USER)) {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;

			} else if (responseTenant.getRole().endsWith(NSConstants.ROLE_ADMIN)) {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;

			}
			else if(responseTenant.getRole().endsWith(NSConstants.ROLE_GUEST)) {
				message=SMSTemplates.GUEST_SUBSCRIPTION_TEMPLATE.replaceAll("USER_NAME", responseTenant.getName());
			}

			else {
				message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE;
			}

			niodsSmsGateway.sendSMS("" + responseTenant.getContactNumber(), message);

			smsStatus = true;

			logger.debug("Out::triggerAlertEmail");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return smsStatus;

	}

}
