package com.srans.nestserver.service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.User;

/**
 * 
 * @author Manish Kumar
 *
 */
@Service
public class MailService {

	
	private JavaMailSender javaMailSender;

	/**
	 * 
	 * @param javaMailSender
	 */
	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	/**
	 * This function is used to send mail without attachment.
	 * 
	 * @param user
	 * @throws MailException
	 */

	public void sendEmail(User user) throws MailException {

		

		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(user.getEmailAddress());
		mail.setSubject("Testing Mail API");
		mail.setText("Hurray ! You have done that dude...");

		
		javaMailSender.send(mail);
	}

	/**
	 * This fucntion is used to send mail that contains a attachment.
	 * 
	 * @param user
	 * @throws MailException
	 * @throws MessagingException
	 */
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		helper.setTo(user.getEmailAddress());
		helper.setSubject("Testing Mail API with Attachment");
		helper.setText("Please find the attached document below.");

		ClassPathResource classPathResource = new ClassPathResource("arvsvr-slideshare-160317211830.pdf");
		helper.addAttachment(classPathResource.getFilename(), classPathResource);

		javaMailSender.send(mimeMessage);
	}

}
