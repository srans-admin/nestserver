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

import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.util.MailTemplates;
import com.srans.nestserver.util.NiodsMailer;

import freemarker.template.TemplateException;

/**
 * @author user
 *
 */
@Service
public class TenantService {
	
	private Logger logger = LoggerFactory.getLogger(TenantService.class);

	@Autowired
	private HostelRepository hostelRepository;
	
	@Autowired
	private NiodsMailer niodsMailer; 
	
	public boolean triggerAlertEmail(Tenant responseTenant) {
		
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
	
}
