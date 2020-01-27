/**
 * 
 */
package com.srans.nestserver.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.srans.nestserver.communication.NiodsSmsGateway;
import com.srans.nestserver.model.UaaSubscription;
import com.srans.nestserver.model.UaaUser;
import com.srans.nestserver.model.User;
import com.srans.nestserver.model.UserSubscription;
import com.srans.nestserver.util.PasswordGenerator;
import com.srans.nestserver.util.NSConstants;
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
	
	@Autowired
	private PasswordGenerator passwordGenerator;
	
	@Autowired
	private TenantService tenantService;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	
	@Value("${uaa-server-url:http://localhost:9090/uaa-server}")
	private String UAA_SERVER_URL;
	
	/**
	 * 
	 * @param templateFileName Name of the template file without extension
	 * @param text
	 * @return
	 */
	public String getTemplate(String templateFileName, User user) {
		logger.trace("In::");
		Map<String, Object> reqParamtersMap = new HashMap<>();

		reqParamtersMap.put("name", user.getName());
		reqParamtersMap.put("password", user.getUserSubscriptionWrapper().getUser().getPassword());
		

		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::");
		return output;
	}
	
	public boolean postUserToUaa(User user) {
		
		boolean status = false;
		
		String email, subject, ccMail, bccMail, message = null;
		email = user.getEmailId();
		subject = "NIDOS Confirmation";
		ccMail = null;
		bccMail = null;
		
		try {
			logger.debug("In::postUserToUaa");  
			
			
			
			String url = UAA_SERVER_URL+"/v1/users";
			String randomPassword = passwordGenerator.generateRamdomPassword();
			
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
		        message =	 SMSTemplates.TENANT_CREDETIALS.replaceAll("##USER_NAME##", user.getName())
					.replaceAll("##USER_ID##", uaaUser.getUsername())
					.replaceAll("##TEMP_PASSWORD##", uaaUser.getPassword());
			//}
			 
				
			niodsSmsGateway.sendSMS(""+user.getContactNumber(),message);
			
			
			if (user.getEmailId() != null && !user.getEmailId().isEmpty()) { 
				tenantService.triggerAlertEmail(user);
				
				
			}
			
			logger.debug("Out::postUserToUaa");
			
		}  catch (Exception e) {
			logger.error("Unable to post tenant user info to uaa server: "+e.getMessage());
			e.printStackTrace();
		}
		
		return status;
		
	}
	
}
