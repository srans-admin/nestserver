package com.srans.nestserver.communication;

import java.io.IOException;
import java.nio.charset.StandardCharsets;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import freemarker.template.TemplateException;

/**
 * 
 * @author Manish
 *
 */

@Service 
public class NiodsMailer {

	@Autowired
	private JavaMailSender javaMailSender; 
	 

	public void sendEmail(String email, String subject, String ccMail, String bccMail, String message ) throws MailException, MessagingException, IOException, TemplateException {
    
		MimeMessage mimeMessage = javaMailSender.createMimeMessage(); 
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED, StandardCharsets.UTF_8.name());

	    //helper.addAttachment("srans.jpg", new ClassPathResource("srans.jpg"));
		helper.setTo(email);  
		helper.setText( message, true); 
		helper.setReplyTo("no-reply@srans.in");
		//helper.setFrom("Nidos");
		helper.setSubject(subject);
		if(ccMail != null) helper.addCc(ccMail);
		if(bccMail != null) helper.addBcc(ccMail);

		//Now trigger an email with above details	
		javaMailSender.send(mimeMessage);

	}

}
