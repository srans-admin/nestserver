/**
 * 
 */
package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srans.nestserver.model.Notification;
import com.srans.nestserver.model.NotificationUser;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.NotificationRepository;
import com.srans.nestserver.repository.NotificationUserRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;

/**
 * @author user
 *
 */
@Service
public class NotoficationService {
	
	private Logger logger = LoggerFactory.getLogger(NotoficationService.class);
 
	@Autowired
	private UserRepository userRepository ;
	
	@Autowired
	private NotificationRepository notificationRepo;
 

	@Autowired
	private NotificationUserRepository notificationUserRepo;
	
	@Value("${uaa-server-url:http://localhost:9090/uaa-server}")
	private String UAA_SERVER_URL;
	
	public boolean addAdminRequestNotifictaion(User responseTenant) {
		
		logger.debug("In::addAdminRequestNotifictaion"); 
		boolean status = false; 
		try { 
			
			//STEP-1 prepare Notification object with messsage
			Notification notification = new Notification();
			notification.setMessage(responseTenant.getName()+ " has requested subscription, please review.");
			final Notification notificationResponse = notificationRepo.save(notification);
			
			//STEP-2: Get superAdmins from UAA  
			userRepository.getUsersByRole(NSConstants.ROLE_SUPERADMIN).stream().forEach(superAdmin ->{
				 
				//STEP-3 for each super admin assign
				NotificationUser notificationUser = new NotificationUser();
				notificationUser.setNotificationId(notificationResponse.getId());
				notificationUser.setUserId(superAdmin.getUserId());
				 
				notificationUserRepo.save(notificationUser);
				
			});
			 
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		logger.debug("Out::"); 
		return status;
		
	}
	
}
