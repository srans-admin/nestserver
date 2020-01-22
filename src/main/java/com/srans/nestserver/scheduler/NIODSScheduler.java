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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.PaymentRepository;

/**
 * @author user
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

	Date bookingDate = null;
	Long bedId = null;
	LocalDate nextWeek = null;

	public LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// Fire The Schedular Every Day On 23:59 AM(Night)
	@Scheduled(cron ="${nidos.cron.trigger}" )
	public void updateBedStatusAfterSevenDays() throws ResourceNotFoundException {

		List<Object> bedsDetails = paymentRepository.guestBedInfo();
		for (Iterator<Object> iterator = bedsDetails.iterator(); iterator.hasNext();) {
			Object[] object = (Object[]) iterator.next();
			for (int i = 0; i < object.length; i++) {
				NIODSScheduler obj = new NIODSScheduler();
				if (i == 0) {
					bookingDate = (Date) object[i];
					System.out.println(bookingDate);

					LocalDate today = obj.convertToLocalDateViaInstant(bookingDate);
					nextWeek = today.plus(1, ChronoUnit.WEEKS);
					//System.out.println(nextWeek.plusDays(1));

					if (nextWeek.isEqual(LocalDate.now())) {
						System.out.println(" ");
					}

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

	/*
	 * @Scheduled(cron = "${nidos.cron.trigger}") public void
	 * generateInvoiceOntime() throws MessagingException {
	 * //System.out.println("hey brother");
	 * 
	 * }
	 */

}
