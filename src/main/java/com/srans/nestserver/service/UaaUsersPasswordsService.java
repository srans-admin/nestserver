/*
 * package com.srans.nestserver.service;
 * 
 * 
 * import org.slf4j.Logger; import org.slf4j.LoggerFactory; import
 * org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.beans.factory.annotation.Value; import
 * org.springframework.stereotype.Service; import
 * org.springframework.web.client.RestTemplate;
 * 
 * import com.srans.nestserver.communication.NiodsSmsGateway; import
 * com.srans.nestserver.model.UaaSubscription; import
 * com.srans.nestserver.model.UaaUser; import
 * com.srans.nestserver.model.UaaUsersPasswords; import
 * com.srans.nestserver.model.User; import
 * com.srans.nestserver.model.UserSubscription; import
 * com.srans.nestserver.util.PasswordGenerator; import
 * com.srans.nestserver.util.NSConstants; import
 * com.srans.nestserver.util.SMSTemplates;
 * 
 *//**
	 * @author user
	 *
	 *//*
		 * @Service public class UaaUsersPasswordsService {
		 * 
		 * private Logger logger =
		 * LoggerFactory.getLogger(UaaUsersPasswordsService.class);
		 * 
		 * @Autowired private RestTemplate restTemplate;
		 * 
		 * @Autowired private NiodsSmsGateway niodsSmsGateway;
		 * 
		 * 
		 * 
		 * @Value("${uaa-server-url:http://localhost:9090/uaa-server}") private String
		 * UAA_SERVER_URL;
		 * 
		 * 
		 * 
		 * public boolean postUaaUserPasswordsToUaa(UaaUsersPasswords uaaUsersPasswords)
		 * {
		 * 
		 * boolean status = false;
		 * 
		 * try { logger.debug("In::postUserToUaa");
		 * 
		 * String url = UAA_SERVER_URL+"/v1/1000/changepassword";
		 * 
		 * UaaUsersPasswords uaaUsersPasswords = new UaaUsersPasswords();
		 * 
		 * UaaUsersPasswords uaaUsersPasswords=
		 * uaaUsersPasswords.getUaaUsersPasswords();
		 * uaaUsersPasswords.setOldPassword(uaaUsersPasswords.getOldPassword());
		 * uaaUsersPasswords.setNewPassword(uaaUsersPasswords.getNewPassword());
		 * uaaUsersPasswords.setConfirmPassword(uaaUsersPasswords.getConfirmPassword());
		 * 
		 * UaaUsersPasswords uaaUsersPasswords =
		 * userSubscriptionWrapper.getSubscription();
		 * uaaSubscription.setUserName(uaaUser.getUsername());
		 * 
		 * if(uaaUser.getRole().equals(NSConstants.ROLE_ADMIN)){
		 * //uaaSubscription.setType(type); //uaaSubscription.setValidFrom(validFrom);
		 * //uaaSubscription.setValidTo(validTo); }
		 * 
		 * userSubscriptionWrapper = restTemplate.postForObject(url,
		 * userSubscriptionWrapper, UserSubscription.class);
		 * 
		 * user.setUserSubscriptionWrapper(userSubscriptionWrapper);
		 * 
		 * //if(user.getContactNumber() ==){ String message =
		 * SMSTemplates.TENANT_CREDETIALS.replaceAll("##USER_NAME##", user.getName())
		 * .replaceAll("##USER_ID##", uaaUser.getUsername())
		 * .replaceAll("##TEMP_PASSWORD##", uaaUser.getPassword()); //}
		 * niodsSmsGateway.sendSMS(""+user.getContactNumber(),message);
		 * 
		 * logger.debug("Out::postUserToUaa");
		 * 
		 * } catch (Exception e) {
		 * logger.error("Unable to post tenant user info to uaa server: "+e.getMessage()
		 * ); e.printStackTrace(); }
		 * 
		 * return status;
		 * 
		 * }
		 * 
		 * }
		 */