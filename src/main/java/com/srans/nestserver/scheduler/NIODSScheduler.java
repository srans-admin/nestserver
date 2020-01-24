/**
 * 
 */
package com.srans.nestserver.scheduler;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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

	Date bookingDate = null;
	Long bedId = null;
	LocalDate nextWeek = null;

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Fire The Schedular Every Day On 23:59 AM(Night)
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
				String message = MailTemplates.TENANT_INVOICE_TEMPLATE.replaceAll("##USER_NAME##", "rahul")
						.replaceAll("##PASSWORD##", "1234").replaceAll("##HOSTEL_NAME##", "NIODS");
				niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);
				//trigger Email
				tenantService.triggerSMS(userInfo);
				
				//Trigger SMS
				String invoiceMessage = SMSTemplates.TENANT_INVOICE_TEMPLATE;
				niodsSmsGateway.sendSMS("" + userInfo.getContactNumber(), invoiceMessage);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
