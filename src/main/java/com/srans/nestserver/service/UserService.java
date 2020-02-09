/**
 * 
 */
package com.srans.nestserver.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.model.Bed;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;
import com.srans.nestserver.util.NSException;

/**
 * @author user
 *
 */
@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

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

	/**
	 * 
	 * @param user
	 * @return
	 */

	private Date reserveDate = null;

	public User processUser(User user) {

		logger.debug("In::");
		switch (user.getRole()) {

		case NSConstants.ROLE_GUEST:
		case NSConstants.ROLE_USER:
			user = processGuestOps(user);
			break;

		case NSConstants.ROLE_TENANT:
			user = processTenantOps(user);
			break;

		case NSConstants.ROLE_ADMIN: // Subscription Request
			user = processAdminOps(user);
			break;

		case NSConstants.ROLE_SUPERADMIN:
			user = processSuperAdminOps(user);
			break;

		default:
			break;
		}

		logger.debug("Out::");
		return user;

	}

	private User processTenantOps(User user) {
		logger.debug("In::processTenantOps");
		User responseTenant = null;

		try {

			// STEP-1: Save User

			user.setStatus("A");
			responseTenant = userRepository.save(user);

			if (responseTenant.getUserId() != -1) {

				// STEP-2

				user.getTenantBooking().setTenantId(responseTenant.getUserId());
				TenantBooking tenantBooking = new TenantBooking();
				tenantBooking = tenantBookRepository.save(user.getTenantBooking());

				// Update Bed with alloted_state as N
				Bed bed = new Bed();
				bed.setId(tenantBooking.getRoomBedId());
				bed.setHostelId(tenantBooking.getHostelId());
				bed.setFloorId(tenantBooking.getFloorId());
				bed.setRoomId(tenantBooking.getRoomId());
				bed.setAlloted('Y');
				bed.setUpdatedAt(new Date());

				responseTenant.setTenantBooking(tenantBooking);
				responseTenant.setBed(bedRepository.saveAndFlush(bed));

				// STEP-3: Save Payment Information

				user.getPayment().setUserId(responseTenant.getUserId());
				responseTenant.setPayment(paymentRepository.save(user.getPayment()));

				// STEP-4 : Now drop an email to tenant
				if (responseTenant.getEmailId() != null && !responseTenant.getEmailId().isEmpty()) {
					// tenantService.triggerAlertEmail(responseTenant);

					// STEP-5 : Prepare one Notification to SuperAdmin(s)
					notificationService.addTenantNotifictaion(responseTenant);
				}

				// STEP-6 : Now drop an SMS to tenant
				if (!("" + responseTenant.getContactNumber()).isEmpty()) {
					tenantService.triggerSMS(responseTenant);
				}

				// STEP-7 : Post this info to UAA
				tenantToUaaService.postUserToUaa(responseTenant);

			} else {
				throw new NSException("Unable to save tenant ");
			}

		} catch (Exception e) {

			// TODO Auto-generated catch block

			e.printStackTrace();
		}
		logger.debug("Out::processTenantOps");
		return responseTenant;

	}

	private User processAdminOps(User user) {
		logger.debug("In::processAdminOps");
		User responseTenant = null;

		try {

			// STEP-1: Save User
			responseTenant = userRepository.save(user);

			// STEP-2 : Now drop an email to tenant
			/*
			 * if (responseTenant.getEmailId() != null &&
			 * !responseTenant.getEmailId().isEmpty()) {
			 * tenantService.triggerAlertEmail(responseTenant); }
			 */

			// STEP-3 : Now drop an SMS to tenant
			if (!("" + responseTenant.getContactNumber()).isEmpty()) {
				tenantService.triggerSMS(responseTenant);
			}

			// STEP-4 : Post this info to UAA
			tenantToUaaService.postUserToUaa(responseTenant);

			// STEP-5 : Prepare one Notification to SuperAdmin(s)
			notificationService.addAdminRequestNotifictaion(responseTenant);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Out::processAdminOps");
		return responseTenant;

	}

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	private static long daysBetween(Date one, Date two) {

		Instant instant1 = Instant.ofEpochMilli(one.getTime());
		Instant instant2 = Instant.ofEpochMilli(two.getTime());

		LocalDateTime localDateTime1 = LocalDateTime.ofInstant(instant1, ZoneId.systemDefault());
		LocalDateTime localDateTime2 = LocalDateTime.ofInstant(instant2, ZoneId.systemDefault());

		LocalDate localDate1 = localDateTime1.toLocalDate();
		System.out.println(localDate1);
		LocalDate localDate2 = localDateTime2.toLocalDate();
		System.out.println(localDate2);

		Period diff = Period.between(localDate2, localDate1);

		System.out.printf("Difference is %d years, %d months and %d days old", diff.getYears(), diff.getMonths(),
				diff.getDays());

		// long difference = (one.getTime()- two.getTime()) / 86400000;

		return (diff.getDays());
	}

	private User processGuestOps(User user) {
		logger.debug("In::processGuestOps");
		User responseTenant = null;

		try {

			// STEP-1: Save User
			responseTenant = userRepository.save(user);

			if (responseTenant.getUserId() != -1) {

				// STEP-2: Set Alloted Till Date And Remaining Days

				user.getTenantBooking().setTenantId(responseTenant.getUserId());
				TenantBooking tenantBooking = new TenantBooking();
				tenantBooking = tenantBookRepository.save(user.getTenantBooking());
				reserveDate = tenantBooking.getCreatedAt();
				LocalDate bookingDate = convertToLocalDateViaInstant(reserveDate);
				ZoneId defaultZoneId = ZoneId.systemDefault();

				LocalDate endDate = bookingDate.plusDays(7);
				System.out.println(endDate);
				Date date = Date.from(endDate.atStartOfDay(defaultZoneId).toInstant());

				tenantBooking.setAllotedTill(date);
				long differentDate = daysBetween(date, new Date());

				System.out.println(differentDate);
				tenantBooking.setRemainingDate(differentDate);

				tenantBookRepository.save(tenantBooking);
				// Update Bed with alloted_state as R
				Bed bed = new Bed();
				bed.setId(tenantBooking.getRoomBedId());
				bed.setHostelId(tenantBooking.getHostelId());
				bed.setFloorId(tenantBooking.getFloorId());
				bed.setRoomId(tenantBooking.getRoomId());
				bed.setAlloted('R');
				bed.setUpdatedAt(new Date());
				responseTenant.setTenantBooking(tenantBooking);
				responseTenant.setBed(bedRepository.saveAndFlush(bed));

				// STEP-3: Save Payment Information

				user.getPayment().setUserId(responseTenant.getUserId());
				responseTenant.setPayment(paymentRepository.save(user.getPayment()));

				// STEP-4 : Now drop an email to tenant
				if (responseTenant.getEmailId() != null && !responseTenant.getEmailId().isEmpty()) {
					 tenantService.triggerAlertEmail(responseTenant);

					// STEP-5 : Prepare one Notification to SuperAdmin(s)
					//notificationService.addTenantNotifictaion(responseTenant);
				}

				// STEP-6 : Now drop an SMS to tenant
				if (!("" + responseTenant.getContactNumber()).isEmpty()) {
					tenantService.triggerSMS(responseTenant);
				}

			} else {
				throw new NSException("Unable to save Guest ");
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Out::processGuestOps");
		return responseTenant;

	}

	private User processSuperAdminOps(User SuperAdminGuest) {
		logger.debug("In::processSuperAdminOps");
		User responseTenant = null;

		try {

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.debug("Out::processSuperAdminOps");
		return responseTenant;

	}

}
