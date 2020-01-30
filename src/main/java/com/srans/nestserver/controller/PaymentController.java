package com.srans.nestserver.controller;

import java.util.ArrayList;
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

import com.srans.nestserver.config.PaymentHistory;
import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.UserRepository;
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

	@Autowired
	private RoomRepository roomRepository;

	@GetMapping("/payments")
	@PreAuthorize("permitAll()")
	public List<PaymentHistory> getPaymentHistory(@RequestParam(value = "id") Long userId,
			@RequestParam(value = "role") String role) throws ResourceNotFoundException {

		// List<Payment> payments = null;
		logger.info("IN::GET::/payments::getPaymentHistory::" + userId + "::" + role);
		List<PaymentHistory> paymentList = new ArrayList<>();

		PaymentHistory payment = new PaymentHistory();
		if (role != null && role.equalsIgnoreCase(NSConstants.ROLE_TENANT)) {

			paymentRepository.getPaymentHistory(userId).stream().forEach(payments -> {

				if (payments.getUserId() == userId) {
					payment.setId(payments.getId());
					payment.setRoomRent(payments.getRoomRent());
					payment.setBankName(payments.getBankName());
					payment.setTransactionId(payments.getTransactionId());
					payment.setPaymentThrough(payments.getPaymentThrough());
					payment.setDepositAmount(payments.getDepositAmount());
					payment.setDiscountAmount(payments.getDiscountAmount());
					payment.setAmountToBePaid(payments.getAmountToBePaid());

				}
			});

			List<Object[]> response = paymentRepository.getTenantInfo(userId);
			// List<PaymentHistory> paymentHistory=new ArrayList<PaymentHistory>();

			Long roomId = 0L;
			for (Object[] p : response) {

				roomId = (Long) p[0];
				payment.setName((String) p[1]);

			}

			Room room = roomRepository.getOne(roomId);
			payment.setRoomName(room.getRoomName());
			payment.setRoomType(room.getRoomType());
			paymentList.add(payment);

		} else {

			List<PaymentHistory> ll = new ArrayList<>();
			paymentRepository.getAllUserPaymentsForAdmin(userId).stream().forEach(payments -> {
				PaymentHistory paymentHistory = new PaymentHistory();
				paymentHistory.setId(payments.getId());
				paymentHistory.setRoomRent(payments.getRoomRent());
				paymentHistory.setBankName(payments.getBankName());
				paymentHistory.setTransactionId(payments.getTransactionId());
				paymentHistory.setPaymentThrough(payments.getPaymentThrough());
				paymentHistory.setDepositAmount(payments.getDepositAmount());
				paymentHistory.setDiscountAmount(payments.getDiscountAmount());
				paymentHistory.setAmountToBePaid(payments.getAmountToBePaid());

				List<Object[]> response = paymentRepository.getTenantInfo(payments.getUserId());

				Long roomId = 0L;

				for (Object[] p : response) {

					roomId = (Long) p[0];
					System.out.println(p[1]);
					paymentHistory.setName((String) p[1]);

				}
				Room room = roomRepository.getOne(roomId);
				paymentHistory.setRoomName(room.getRoomName());
				paymentHistory.setRoomType(room.getRoomType());

				ll.add(paymentHistory);

			});
			
			paymentList.addAll(ll);
			logger.info("OUT::GET::/payments::getPaymentHistory::" + userId + "::" + role);
		}
		return paymentList;
	}

	@PostMapping("/payments")
	@PreAuthorize("permitAll()")
	public Payment createPayment(@Valid @RequestBody Payment payment) {
		logger.info("IN::POST::/payments::savePayment::" + payment);
		logger.info("OUT::POST::/payments::savePayment::" + payment);
		return paymentRepository.save(payment);
	}

	@GetMapping("payments/users/{id}")
	@PreAuthorize("permitAll()")
	// @PreAuthorize("permitAll()")
	public Payment getTenantInfo(@PathVariable(value = "id") Long userId) throws ResourceNotFoundException {
		Payment paymentInfo = new Payment();
		paymentRepository.getPaymentByUserId(userId).stream().forEach(tenantInfo -> {
			if (tenantInfo.getUserId() == userId) {
				paymentInfo.setName(tenantInfo.getName());
				paymentInfo.setRoomType(tenantInfo.getRoomType());
				paymentInfo.setRoomName(tenantInfo.getRoomName());
			}

		});
		return paymentInfo;

		/*
		 * logger.info("IN::getTenantByName::" + name); User user =
		 * userRepository.findById(id); logger.info("OUT::getTenantByName::" + name);
		 * return ResponseEntity.ok().body(id);
		 */
	}

	@GetMapping("/payments/{id}")
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

	@GetMapping("payments/tenant/{Id}")
	public ResponseEntity<User> getTenantById(@PathVariable(value = "Id") Long TenantId)
			throws ResourceNotFoundException {

		logger.info("IN::getTenantById::" + TenantId);

		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
		logger.info("OUT::getTenantById::" + TenantId);

		return ResponseEntity.ok().body(user);
	}

	@GetMapping("payments/hostels/{id}/roomdetail")

	public List<Object[]> getRoomdetails(@PathVariable(value = "id") Long hostelId) {
		logger.info("IN::getRoomdetails::" + hostelId);
		logger.info("OUT::getRoomdetails::" + hostelId);

		return hostelRepository.getRoomDetails(hostelId);
	}
}