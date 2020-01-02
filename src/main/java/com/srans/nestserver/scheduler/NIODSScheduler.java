/**
 * 
 */
package com.srans.nestserver.scheduler;

import java.nio.charset.StandardCharsets;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author user
 *
 */
@Component
public class NIODSScheduler {
	
	@Autowired
	private JavaMailSender javaMailSender;

	@Scheduled(cron = "${nidos.cron.trigger}")
	public void generateInvoiceOntime() throws MessagingException {

		

	}

}
