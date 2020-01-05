/**
 * 
 */
package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.srans.nestserver.communication.NiodsSmsGateway;
import com.srans.nestserver.model.UaaSubscription;
import com.srans.nestserver.model.UaaUser;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.UserSubscription;
import com.srans.nestserver.util.NSConstants;
import com.srans.nestserver.util.PasswordGenerator;
import com.srans.nestserver.util.SMSTemplates;

/**
 * @author user
 *
 */
@Service
public class TenantToUaaService {
	
	private Logger logger = LoggerFactory.getLogger(TenantToUaaService.class);
  
	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private NiodsSmsGateway niodsSmsGateway;
	
	@Value("${uaa-server-url:http://localhost:9090/uaa-server}")
	private String UAA_SERVER_URL;
	 
	public boolean postUserToUaa(User user) {
		
		logger.debug("In::postUserToUaa");  
		boolean status = false;
		
		try { 
			
			String url = UAA_SERVER_URL+"/v1/users";
			String randomPassword = PasswordGenerator.generateRamdomPassword();
			
			UserSubscription userSubscriptionWrapper = new UserSubscription();
			
			
			UaaUser uaaUser = userSubscriptionWrapper.getUser();
			uaaUser.setUsername(user.getName()); 
			uaaUser.setPassword(randomPassword);
			uaaUser.setRole(user.getRole()); 
			
			UaaSubscription uaaSubscription = userSubscriptionWrapper.getSubscription();
			uaaSubscription.setUserName(uaaUser.getUsername());
			
			if(uaaUser.getRole().equals(NSConstants.ROLE_ADMIN)){
				//uaaSubscription.setType(type);
				//uaaSubscription.setValidFrom(validFrom);
				//uaaSubscription.setValidTo(validTo); 
			}
			 
			userSubscriptionWrapper = restTemplate.postForObject(url, userSubscriptionWrapper, UserSubscription.class);
			
			user.setUserSubscriptionWrapper(userSubscriptionWrapper);
			
			//if(user.getContactNumber() ==){
			String message =	 SMSTemplates.TENANT_CREDETIALS.replaceAll("##USER_NAME##", user.getName())
					.replaceAll("##USER_ID##", uaaUser.getUsername())
					.replaceAll("##TEMP_PASSWORD##", uaaUser.getPassword());
			//}
			niodsSmsGateway.sendSMS(""+user.getContactNumber(),message);
			
			logger.debug("Out::postUserToUaa");
			
		}  catch (Exception e) {
			logger.error("Unable to post tenant user info to uaa server: "+e.getMessage());
			e.printStackTrace();
		}
		
		return status;
		
	}
	
	
	public boolean updateUserToUaa(User user) {
		
		boolean status = false;
		
		return status;
	}
	
	
	public boolean deleteUserToUaa(User user) {
		
		boolean status = false;
		
		return status;
		
	}
	
	
	public boolean changePassword(User user) { 
		logger.debug("In::changePassword");  
		boolean status = false;
		
		try { 
		
			String url = UAA_SERVER_URL+"/v1/users";
			UserSubscription userSubscriptionWrapper = new UserSubscription(); 
			
			UaaUser uaaUser = userSubscriptionWrapper.getUser();
			uaaUser.setUsername(user.getName()); 
			uaaUser.setPassword(user.getPassword()); 
			
			UaaSubscription uaaSubscription = userSubscriptionWrapper.getSubscription();
			uaaSubscription.setUserName(uaaUser.getUsername());
			
			
			 
		    HttpHeaders headers = new HttpHeaders();
		    headers.setContentType(MediaType.APPLICATION_JSON); 
		    HttpEntity<UserSubscription> entity = new HttpEntity<UserSubscription>(userSubscriptionWrapper, headers); 
		    
		    
		    ResponseEntity<UserSubscription> response = restTemplate.exchange(url, HttpMethod.PUT, entity, UserSubscription.class);
	 
		    logger.debug("Password change updation status : "+response); 
		    status = true;
		    
		}  catch (Exception e) {
			logger.error("Unable to post tenant user info to uaa server: "+e.getMessage());
			e.printStackTrace();
		} 
		 
		logger.debug("Out::changePassword");
		return status;
		
	}
	
	
	
}
