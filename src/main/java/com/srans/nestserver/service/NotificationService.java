/**
 * 
 */
package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.Notification;
import com.srans.nestserver.model.NotificationUser;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.NotificationRepository;
import com.srans.nestserver.repository.NotificationUserRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;

/**
 * @author user
 *
 */
@Service
public class NotificationService {

	private Logger logger = LoggerFactory.getLogger(NotificationService.class);

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private NotificationRepository notificationRepo;

	@Autowired
	private NotificationUserRepository notificationUserRepo;

	@Autowired
	private TenantBookRepository tenantBookingRepo;

	@Autowired
	private HostelRepository hostelRepo;

	@Value("${uaa-server-url:http://localhost:9090/uaa-server}")
	private String UAA_SERVER_URL;

	public boolean addAdminRequestNotifictaion(User responseTenant) {

		logger.debug("In::addAdminRequestNotifictaion");
		boolean status = false;
		try {

			// STEP-1 prepare Notification object with messsage
			Notification notification = new Notification();
			notification.setMessage(responseTenant.getName() + " has requested subscription, please review.");
			final Notification notificationResponse = notificationRepo.save(notification);

			// STEP-2: Get superAdmins from UAA
			userRepository.getUsersByRole(NSConstants.ROLE_SUPERADMIN).stream().forEach(superAdmin -> {

				// STEP-3 for each super admin assign
				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(notificationResponse.getId());
				notificationUser.setUserId(superAdmin.getUserId());

				notificationUserRepo.save(notificationUser);

			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Out::");
		return status;

	}

	public boolean addTenantNotifictaion(User responseTenant) {

		logger.debug("In::addTenantNotifictaion");
		boolean status = false;
		try {

			// STEP-1 prepare Notification object with messsage
			Notification notification = new Notification();
			notification.setMessage(responseTenant.getName() + " has requested for registration, please review.");
			final Notification notificationResponse = notificationRepo.save(notification);

			// STEP-2: Get superAdmins from UAA
			userRepository.getUsersByRole(NSConstants.ROLE_ADMIN).stream().forEach(admin -> {

				// STEP-3 for each super admin assign
				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(notificationResponse.getId());
				notificationUser.setUserId(admin.getUserId());
				notificationUserRepo.save(notificationUser);

			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Out::");
		return status;

	}

	public boolean addHostelNotifictaion(Hostel responseHostel) {

		// logger.debug("In::addHostelNotifictaion");
		boolean status = false;
		try {

			// STEP-1 prepare Notification object with messsage
			Notification notification = new Notification();
			notification.setMessage(responseHostel.getHostelName() + " hostel has add, please review.");
			final Notification notificationResponse = notificationRepo.save(notification);

			// STEP-2: Get superAdmins from UAA
			userRepository.getUsersByRole(NSConstants.ROLE_SUPERADMIN).stream().forEach(superAdmin -> {

				// STEP-3 for each super admin assign
				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(notificationResponse.getId());
				notificationUser.setUserId(superAdmin.getUserId());

				notificationUserRepo.save(notificationUser);

			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Out::");
		return status;

	}

	public boolean tenantVacatedNotifictaion(Vacation responseVacate) {

		logger.debug("In::tenantVacatedNotifictaion");
		boolean status = false;
		try {

			// STEP-1 prepare Notification object with messsage
			Notification notification = new Notification();

			
			User userResponse = userRepository.getOne(responseVacate.getTenantId());
			Long hostelId=tenantBookingRepo.findHostelId(userResponse.getUserId());
			Hostel hostel=hostelRepo.getOne(hostelId);
			
			notification.setMessage(userResponse.getName() + " ,wanted to vacate on " + responseVacate.getDate()
					+ ". And his Tenant Id::" + responseVacate.getTenantId() + ", please review.");
			notification.setTenantId(responseVacate.getTenantId());
			final Notification notificationResponse = notificationRepo.save(notification);

			// STEP-2: Get admins from UAA
			userRepository.getUsersByRole(NSConstants.ROLE_ADMIN).stream().forEach(admin -> {

				// STEP-3 for each super admin assign
				if(hostel.getAdminId()==admin.getUserId()) {		
				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(notificationResponse.getId());
				notificationUser.setUserId(admin.getUserId());
				notificationUserRepo.save(notificationUser);
				}

			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		logger.debug("Out::");
		return status;

	}

}
