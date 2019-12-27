package com.srans.nestserver.controller;

import java.util.LinkedList;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.User;
import com.srans.nestserver.service.MailService;

/**
 *
 * @author Manish
 *
 */
@RestController
public class RegistrationController {

	@Autowired
	private MailService notificationService;

	@Autowired
	private User user;
	
	@RequestMapping("send-mail")
	//@Scheduled(fixedRate = 1000)
	@Scheduled(cron = "* * * * * *")
	public String send() throws MessagingException {

		List<String> list = new LinkedList<>();

		list.add("manishk219009@gmail.com");
		list.add("manish.p@srans.in");

		user.setEmailAddress(list);
		try {
			notificationService.sendEmail(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations!.";
	}

	@RequestMapping("send-mail-attachment")
	//@Scheduled(cron = "0 0 0 26–31 * ?")
	//@Scheduled(fixedRate = 1000)
	public String sendWithAttachment() throws MessagingException {

		List<String> list = new LinkedList<>();

		list.add("manishk219009@gmail.com");
		list.add("manish.p@srans.in");

		user.setEmailAddress(list); // Receiver's email address

		try {
			notificationService.sendEmailWithAttachment(user);
		} catch (MailException mailException) {
			System.out.println(mailException);
		}
		return "Congratulations! Your mail has been send to the user.";
	}

}