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

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.service.TenantService;

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

			Long[] hostelId = hostelRepo.getAllHostelId();
			for (Long hid : hostelId) {

				Long[] tenantId = tenantBookRepo.getAllTenantId(hid);
				for (Long tid : tenantId) {
					User userInfo = userRepo.getOne(tid);
					tenantService.triggerAlertEmail(userInfo);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
