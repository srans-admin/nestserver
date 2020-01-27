package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.HistoryUtil;
import com.srans.nestserver.util.NSConstants;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	
	private Logger logger = LoggerFactory.getLogger(PaymentController.class);
	
	@Autowired
	private PaymentRepository paymentRepository;
 
	@Autowired
	private HostelRepository hostelRepository; 

	@Autowired
	private UserRepository userRepository;
 
	@GetMapping("/payments") 
	@PreAuthorize("permitAll()") 
	public List<Payment> getPaymentHistory(@RequestParam(value = "id") Long userId, @RequestParam(value = "role") String role) throws ResourceNotFoundException {
		
		List<Payment> payments = null;
		logger.info("IN::GET::/payments::getPaymentHistory::" + userId+"::"+role);
		if(role != null && role.equalsIgnoreCase(NSConstants.ROLE_TENANT)) {
			payments = paymentRepository.getPaymentHistory(userId);
		}else {
			payments = paymentRepository.getAllUserPaymentsForAdmin(userId);
			
		}
		return payments; 
	} 
 
	@PostMapping("/payments")
	@PreAuthorize("permitAll()")
	public Payment createPayment(@Valid @RequestBody Payment payment) {
		logger.info("IN::POST::/payments::savePayment::" + payment);
		logger.info("OUT::POST::/payments::savePayment::" + payment);
		return paymentRepository.save(payment);
	}
	
	@GetMapping("payments/users/byname/{name}")
	@PreAuthorize("permitAll()")
    //@PreAuthorize("permitAll()")
	public ResponseEntity<User> getTenantByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		logger.info("IN::getTenantByName::" + name);
		User user = userRepository.findByName(name);
		logger.info("OUT::getTenantByName::" + name);
		return ResponseEntity.ok().body(user);
	}
 

	@GetMapping("/payment/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		logger.info("IN::getPaymentById::" + paymentId);
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		logger.info("OUT::getPaymentById::" + paymentId);

		return ResponseEntity.ok().body(payment);
	}

	
	

	/*
	 * @PutMapping("/payment/{id}") public ResponseEntity<Payment>
	 * updatePayment(@PathVariable(value = "id") Long payment_Id,
	 *
	 * @Valid @RequestBody Payment paymentDetails) throws ResourceNotFoundException
	 * { Payment payment = paymentRepository.findById(payment_Id) .orElseThrow(() ->
	 * new ResourceNotFoundException("Payment not found for this id :: " +
	 * payment_Id));
	 *
	 * payment.setPaymentThrough(paymentDetails.getPaymentThrough());
	 * payment.setTransactionId(paymentDetails.getTransactionId());
	 * payment.setBankName(paymentDetails.getBankName());
	 * payment.setDate(paymentDetails.getDate());
	 * payment.setDepositAmount(payment.getDepositAmount());
	 * payment.setDiscountAmount(payment.getDiscountAmount()); final Payment
	 * updatedpayment = paymentRepository.save(payment); return
	 * ResponseEntity.ok(payment); }
	 */

	@DeleteMapping("/payment/{id}")
	public Map<String, Boolean> deletepayment(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		logger.info("IN::POST::/payment::deletepayment::" + paymentId);

		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

		paymentRepository.delete(payment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/payment::deletepayment::" + paymentId);
		return response;
	}

	@GetMapping("payment/tenant/{Id}")
	public ResponseEntity<User> getTenantById(@PathVariable(value = "Id") Long TenantId)
			throws ResourceNotFoundException {

		logger.info("IN::getTenantById::" + TenantId);

		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
		logger.info("OUT::getTenantById::" + TenantId);

		return ResponseEntity.ok().body(user);
	}

	@GetMapping("payment/hostels/{id}/roomdetail")

	public List<Object[]> getRoomdetails(@PathVariable(value = "id") Long hostelId) {
		logger.info("IN::getRoomdetails::" + hostelId);
		logger.info("OUT::getRoomdetails::" + hostelId);

		return hostelRepository.getRoomDetails(hostelId);
	}
}