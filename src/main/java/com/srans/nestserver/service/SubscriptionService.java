package com.srans.nestserver.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.NSConstants;

@Service
public class SubscriptionService {
	private Logger logger = LoggerFactory.getLogger(SubscriptionService.class);

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private UserRepository userRepository; 
	

	public Payment processOfApproving(Payment payment) {
		logger.debug("In::" + payment);
		Payment responsePayment = null;

		try {
			responsePayment = paymentRepository.save(payment);

			//if (responsePayment.getAmountType().equalsIgnoreCase(NSConstants.PAYMENT_TYPE_SUBSCRIPTION)) {
				
				User needTobeUpdatedUser = new User();
			//	needTobeUpdatedUser.setUserId(responsePayment.getAdminId());
				needTobeUpdatedUser.setStatus(NSConstants.USER_STATUS_ACTIVE);
				
				userRepository.save(needTobeUpdatedUser); 
				 
			//}else {
			//	logger.warn("Unable to get any possible getAmmountType for the subscription " );
			//}

		} catch (Exception e) {
			e.printStackTrace();

		}

		logger.debug("Out::" + payment);
		return (responsePayment);

	}

}
