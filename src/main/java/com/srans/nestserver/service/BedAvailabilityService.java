package com.srans.nestserver.service;

import java.math.BigInteger;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
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
	private Payment guestPayment = new Payment();

	@Autowired
	private BedRepository bedRepository;

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
		return tenantBookRepository.saveAndFlush(tenantBooking);
	}

	// Save Amount Details
	public Payment saveAmountDetails(@Valid Payment payment) {

		guestPayment = paymentRepository.saveAndFlush(payment);

		if (guestPayment.getRoomBedId() != null) {
				
			bedRepository.findById(guestPayment.getRoomBedId()).map(bed -> {
				bed.setAlloted('R');
				
				return bedRepo.save(bed);
			}).orElseThrow(() -> new ResourceNotFoundException("Bed Id " + guestPayment.getRoomBedId() + " not found"));
		}
		return payment;

	}
}
