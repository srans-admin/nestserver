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
import com.srans.nestserver.model.User;
import com.srans.nestserver.util.MailTemplates;
import com.srans.nestserver.util.SMSTemplates;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class TenantService {
	
	private Logger logger = LoggerFactory.getLogger(TenantService.class);

  
	@Autowired
	private NiodsMailer niodsMailer; 
	
	@Autowired
	private NiodsSmsGateway niodsSmsGateway; 
	
	public boolean triggerAlertEmail(User responseTenant) {
		
		boolean emailStatus = false;
		
		try {
			
			logger.debug("In::triggerAlertEmail"); 
			
			String email, subject, ccMail, bccMail, message;
			email = responseTenant.getEmailId();
			subject = "Welcome to Hostel ";
			ccMail = null;
			bccMail = null; 
			 
			message = MailTemplates.TENANT_REGISTRATION_TEMPLATE.replaceAll("##USER_NAME##",responseTenant.getName()) 
																.replaceAll("##PASSWORD##", responseTenant.getName())
																.replaceAll("##HOSTEL_NAME##",  "NIODS")//hostelRepository.getHostelName(responseTenant.getTenantBooking().getHostelId()) )
																.replaceAll("##ROOM_NUMBER##", ""+responseTenant.getTenantBooking().getRoomName())
																.replaceAll("##FLOOR_NUMBER##",  ""+responseTenant.getTenantBooking().getFloorName())
																.replaceAll("##ROOM_RENT##", ""+responseTenant.getTenantBooking().getRoomRent()) ;
																		 
			
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
			
			  
			String message = SMSTemplates.TENANT_REGISTRATION_TEMPLATE.replaceAll("##USER_NAME##",responseTenant.getName()) 
																.replaceAll("##PASSWORD##", responseTenant.getName())
																.replaceAll("##HOSTEL_NAME##",  "NIODS")//hostelRepository.getHostelName(responseTenant.getTenantBooking().getHostelId()) )
																.replaceAll("##ROOM_NUMBER##", ""+responseTenant.getTenantBooking().getRoomName())
																.replaceAll("##FLOOR_NUMBER##",  ""+responseTenant.getTenantBooking().getFloorName())
																.replaceAll("##ROOM_RENT##", ""+responseTenant.getTenantBooking().getRoomRent()) ;
																		 
			
			 niodsSmsGateway.sendSMS(""+responseTenant.getContactNumber(), message);
			
			smsStatus = true;
			
			logger.debug("Out::triggerAlertEmail");
			
		}  catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return smsStatus;
		
	}
	
}
