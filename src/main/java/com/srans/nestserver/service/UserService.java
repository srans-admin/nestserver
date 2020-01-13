/**
 * 
 */
package com.srans.nestserver.service;

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
	public User processUser(User user) {

		logger.debug("In::");
		switch (user.getRole()) {

		case NSConstants.ROLE_USER:
			user = processGuestOps(user);
			break;

		case NSConstants.ROLE_TENANT:
			user = processTenantOps(user);
			break;

		case NSConstants.ROLE_ADMIN:
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
			responseTenant = userRepository.save(user);

			if (responseTenant.getUserId() != -1) {
				
				// STEP-3
				
				System.out.println(responseTenant.getUserId());
				user.getTenantBooking().setTenantId(responseTenant.getUserId());
				TenantBooking tenantBooking =new TenantBooking();
				 tenantBooking = tenantBookRepository.save(user.getTenantBooking());
				
				System.out.println(user.getTenantBooking());
				

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
				// STEP-4: Save Payment Information
				responseTenant.setPayment(paymentRepository.save(user.getPayment()));

				// STEP-5 : Now drop an email to tenant

				if (responseTenant.getEmailId() != null && !responseTenant.getEmailId().isEmpty()) {
					tenantService.triggerAlertEmail(responseTenant);
					
			        // STEP-2 : Prepare one Notification to SuperAdmin(s)
					  notificationService.addTenantNotifictaion(responseTenant);
				}

				// STEP-6 : Now drop an SMS to tenant

				if (!("" + responseTenant.getContactNumber()).isEmpty()) {
					tenantService.triggerSMS(responseTenant);
					
					// STEP- : Post this info to UAA
					  tenantToUaaService.postUserToUaa(responseTenant);
				}
				

				  
				  
				  

			} else {
				throw new NSException("Unable to save tenant ");
			}

		} catch (Exception e) {
			
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
			if (responseTenant.getEmailId() != null && !responseTenant.getEmailId().isEmpty()) {
				tenantService.triggerAlertEmail(responseTenant);
			}

			
			  //STEP-3 : Now drop an SMS to tenant
			  if(!(""+responseTenant.getContactNumber()).isEmpty()){
			  tenantService.triggerSMS(responseTenant); }
			 

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

	private User processGuestOps(User user) {
		logger.debug("In::processGuestOps");
		User responseTenant = null;

		try {

			// STEP-1: Save User
			responseTenant = userRepository.save(user);

			if (responseTenant.getUserId() != -1) {
        
				// STEP-4 : Now drop an email to tenant
				if (responseTenant.getEmailId() != null && !responseTenant.getEmailId().isEmpty()) {
					tenantService.triggerAlertEmail(responseTenant);
				}

				// STEP-5 : Now drop an SMS to tenant
				if (!("" + responseTenant.getContactNumber()).isEmpty()) {
					tenantService.triggerSMS(responseTenant);
				}
 

				// STEP-6 : Post this info to UAA
				tenantToUaaService.postUserToUaa(responseTenant);

			} else {
				throw new NSException("Unable to save tenant ");
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
