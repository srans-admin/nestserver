package com.srans.nestserver.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.UserRepository;

@Service
public class SubscriptionService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserRepository userRepository;
	
	
	

	public Payment processOfApproving(Payment payment) {
		logger.debug("In::" + payment);
		Payment responsePayment = null;

		try {
			responsePayment = paymentRepository.save(payment);
			if (responsePayment.getAmmountType().equals("subscription")) {

				List<User> userDetails = userRepository.findAll();

				for (User u1 : userDetails) {
					if (u1.getUserId() == (responsePayment.getAdminId())) {
						u1.setStatus("A");
						
						userRepository.save(u1);
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.debug("In::" + payment);
		return (responsePayment);

	}

}
