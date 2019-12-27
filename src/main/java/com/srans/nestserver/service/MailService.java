package com.srans.nestserver.service;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.User;

/**
 * 
 * @author Manish
 *
 */
@Service

public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	public void sendEmail(User user) throws MailException, MessagingException {

		Set<String> mail = new HashSet<>();
		mail.addAll(user.getEmailAddress());

		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

		for (String mailAddress : mail) {
			helper.setTo(mailAddress);
			helper.setSubject("Testing Mail API");
			helper.setText("Hurray ! You have done that dude...");
			javaMailSender.send(mimeMessage);
		}

	}

	/*
	 * At 6 pm on the last 4 days of every month
	 */

	
	public void sendEmailWithAttachment(User user) throws MailException, MessagingException {
		final Calendar c = Calendar.getInstance();
		if (c.get(Calendar.DATE) == c.getActualMaximum(Calendar.DATE)) {
			Set<String> mail = new HashSet<>();
			mail.addAll(user.getEmailAddress());

			MimeMessage mimeMessage = javaMailSender.createMimeMessage();

			MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

			for (String mailAddress : mail) {
				helper.setTo(InternetAddress.parse(mailAddress));
				helper.setSubject("Testing Mail API with Attachment");
				helper.setText("Please find the attached document below.");
				ClassPathResource classPathResource = new ClassPathResource("srans.jpg");// Make sure you add an //
																							// attachment file to src //
																							// ->resources

				helper.addAttachment(classPathResource.getFilename(), classPathResource);

				javaMailSender.send(mimeMessage);
			}
		}

	}

}
