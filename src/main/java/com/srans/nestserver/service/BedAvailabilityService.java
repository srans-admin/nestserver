package com.srans.nestserver.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Bed;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.AvailableBedsUtil;

@Service
public class BedAvailabilityService {

	@Autowired
	private BedRepository bedRepo;

	@Autowired
	private TenantBookRepository tenantBookRepository;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private TenantService tenantService;

	@Autowired
	private BedRepository bedRepository;

	@Autowired
	private UserRepository userRepository;

	// Display All Empty Beds
	public List<AvailableBedsUtil> getAllAvailableBed(Long hostelId) {

		List<AvailableBedsUtil> availableBedsInfo = new ArrayList<AvailableBedsUtil>();

		if (hostelId != 0) {
			List<Object> bedsDetails = bedRepo.getAvailableBedsByHostelId(hostelId);

			for (Iterator<Object> iterator = bedsDetails.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();
				AvailableBedsUtil availableBeds = new AvailableBedsUtil();
				for (int i = 0; i < object.length; i++) {

					if (i == 0) {
						availableBeds.setRoomBedId(((BigInteger) object[i]).longValue());
					} else if (i == 1) {
						availableBeds.setVacatedDate((Date) object[i]);
					} else if (i == 2) {
						availableBeds.setBedPosition((String) object[i]);
					} else if (i == 3) {
						availableBeds.setFloorId(((BigInteger) object[i]).longValue());
					} else if (i == 4) {
						availableBeds.setRoomId(((BigInteger) object[i]).longValue());
					} else if (i == 5) {
						availableBeds.setRoomRent(((BigInteger) object[i]).longValue());
					} else if (i == 6) {
						availableBeds.setRoomType((String) object[i]);
					}

				}

				availableBedsInfo.add(availableBeds);
			}

		}
		return availableBedsInfo;
	}

	// Save Booked Bed Details

	public TenantBooking saveBookedBedDetails(@Valid TenantBooking tenantBooking) {

		TenantBooking guestBooking = tenantBookRepository.saveAndFlush(tenantBooking);

		/*
		 * guestBooking.getPayment().forEach(payment ->{
		 * payment.setRoomBedId(guestBooking.getRoomBedId());
		 * paymentRepository.save(payment); });
		 */

		paymentRepository.save(guestBooking.getPayment());

		User userResponse = new User();

		if (guestBooking.getRoomId() != null) {
			userResponse = userRepository.getOne(guestBooking.getGuestId());
			tenantService.triggerAlertEmail(userResponse);
		}
		return guestBooking;
	}

	// Save Bed Booking Amount Details
	public Payment saveAmountDetails(@Valid Payment payment) {
		Payment guestPayment = new Payment();
		guestPayment = paymentRepository.saveAndFlush(payment);

		try {
			if (payment.getRoomBedId() != null) {
				bedRepository.findById(guestPayment.getRoomBedId()).map(bed -> {
					bed.setAlloted('R');
					return bedRepo.saveAndFlush(bed);
				});

			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			Long guestId;
			guestId = tenantBookRepository.findByGuestId(guestPayment.getRoomBedId());
			User userInfo = new User();
			userInfo = userRepository.getOne(guestId);
			tenantService.triggerAlertEmail(userInfo);
			tenantService.triggerSMS(userInfo);
		}

		return payment;

	}
}
