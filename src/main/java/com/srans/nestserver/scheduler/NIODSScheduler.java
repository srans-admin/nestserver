/**
 * 
 */
package com.srans.nestserver.scheduler;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.mail.MessagingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.communication.NiodsSmsGateway;
import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.service.TenantService;
import com.srans.nestserver.service.VacateService;
import com.srans.nestserver.util.MailTemplates;
import com.srans.nestserver.util.SMSTemplates;

/**
 * @author manish
 *
 */
@Component
public class NIODSScheduler {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private BedRepository bedRepository;

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private TenantBookRepository tenantBookRepo;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private NiodsMailer niodsMailer;

	@Autowired
	private NiodsSmsGateway niodsSmsGateway;
	
	@Autowired
	private TemplateEngine templateEngine;
	
	@Autowired
	private PaymentRepository paymentRepo;

	private Logger logger = LoggerFactory.getLogger(NIODSScheduler.class);

  private	Date bookingDate = null;
  private Long bedId = null;
  private LocalDate nextWeek = null;
  private Map<String, Object> reqParamtersMap;

	public String getTemplate(String templateFileName, User user) {
		logger.trace("In:: getTemplate" + templateFileName);
		reqParamtersMap = new HashMap<>();

		System.out.println(user.getUserId());
		
		  reqParamtersMap.put("name", user.getName());
		  
		  reqParamtersMap.put("date", LocalDate.now());	  
		  
		  reqParamtersMap.put("monthlyRent",paymentRepo.getRoomRent(user.getUserId()));  
		 
		  reqParamtersMap.put("amount", paymentRepo.getRoomRent(user.getUserId()));
		 

		String output = this.templateEngine.process(templateFileName,
				new Context(Locale.getDefault(), reqParamtersMap));

		reqParamtersMap = null;

		logger.trace("Out::getTemplate" + templateFileName);
		return output;
	}


	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Fire The Scheduler Every Day On 23:59 AM(Night)
	@Scheduled(cron = "${nidos.cron.bed-reservation-trigger}")
	public void updateBedStatusAfterSevenDays() throws ResourceNotFoundException {

		List<Object> bedsDetails = paymentRepository.guestBedInfo();
		for (Iterator<Object> iterator = bedsDetails.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			for (int i = 0; i < object.length; i++) {

				if (i == 0) {
					bookingDate = (Date) object[i];
					System.out.println(bookingDate);

					LocalDate today = convertToLocalDateViaInstant(bookingDate);
					nextWeek = today.plus(1, ChronoUnit.WEEKS);

				} else if (i == 1) {
					bedId = ((BigInteger) object[i]).longValue();

					if (nextWeek.isEqual(LocalDate.now())) {
						bedRepository.findById(bedId).map(bed -> {
							bed.setAlloted('N');
							System.out.println("Updated alloted status Of the bed, Bed id- " + bedId);
							return bedRepository.saveAndFlush(bed);
						});

					}

				}

			}

		}
	}
	
	
	
	

	// Triggering the scheduler starting 5 days of the every month.
		@Scheduled(cron = "${nidos.cron.tenant-invoice-trigger}")
		public void generateInvoiceOntime() throws MessagingException {

			try {

				Long[] tenantId = tenantBookRepo.getAllTenantId();
				for (Long tid : tenantId) {
					User userInfo = userRepo.getOne(tid);
					String email = userInfo.getEmailId();
					String subject = "Invoice";
					String ccMail = null;
					String bccMail = null;
					String message = this.getTemplate("invoice", userInfo);

					niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);
					// trigger Email
					//tenantService.triggerSMS(userInfo);

					// Trigger SMS
					String invoiceMessage = SMSTemplates.TENANT_INVOICE_TEMPLATE;
					System.out.println(userInfo.getContactNumber());
					niodsSmsGateway.sendSMS("" + userInfo.getContactNumber(), invoiceMessage);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

		}


}
