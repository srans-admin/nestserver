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

import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.model.UaaSubscription;
import com.srans.nestserver.model.UaaUser;
import com.srans.nestserver.model.UserSubscription;
import com.srans.nestserver.util.NSConstants;

/**
 * @author user
 *
 */
@Service
public class TenantToUaaService {
	
	private Logger logger = LoggerFactory.getLogger(TenantToUaaService.class);
  
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${uaa-server-url:http://localhost:9090/uaa-server}")
	private String UAA_SERVER_URL;
	
	public boolean postUserToUaa(Tenant tenant) {
		
		boolean status = false;
		
		try {
			logger.debug("In::postUserToUaa");  
			
			String url = UAA_SERVER_URL+"/v1/users";
			
			UserSubscription userSubscriptionWrapper = new UserSubscription();
			
			UaaUser uaaUser = userSubscriptionWrapper.getUser();
			uaaUser.setUsername(tenant.getName());
			uaaUser.setPassword("ABCD");//TODO generate random password and email this
			uaaUser.setRole(NSConstants.ROLE_USER); 
			
			UaaSubscription uaaSubscription = userSubscriptionWrapper.getSubscription();
			uaaSubscription.setUserName(uaaUser.getUsername());
			
			 
			userSubscriptionWrapper = restTemplate.postForObject(url, userSubscriptionWrapper, UserSubscription.class);
			
			tenant.setUserSubscriptionWrapper(userSubscriptionWrapper);
			
			logger.debug("Out::postUserToUaa");
			
		}  catch (Exception e) {
			logger.error("Unable to post tenant user info to uaa server: "+e.getMessage());
			e.printStackTrace();
		}
		
		return status;
		
	}
	
}
