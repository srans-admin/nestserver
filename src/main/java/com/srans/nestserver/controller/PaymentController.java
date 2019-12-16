package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Payment;
import com.srans.nestserver.model.Role;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.RoleRepository;
import com.srans.nestserver.repository.RoomRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class PaymentController {
	//Logger logger = LoggerFactory.getLogger(RolesController.class);
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private RoomRepository roomRepository;
	
	@Autowired
	private HostelRepository hostelRepository;
	
	@Autowired
	private FloorRepository floorRepository;
	
	
	@GetMapping("/payment")
	public List<Payment> getAllPayment() {
		return paymentRepository.findAll();
	}
	

	
	
	@GetMapping("payment/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Room> getFloorById(@PathVariable(value = "id") Long floor_id,

			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}

		Room room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}
	
	
	  @GetMapping("/payment/roomName/{id}") 
	  public Payment getAllroomNameByPaymentId(@PathVariable(value = "id") Long id) { 
		  return paymentRepository.findById(id).orElse(null); }
	  
	  
	  
	  @GetMapping("/payment/roomtype/room/{id}") 
	  public Payment getAllroomTypeByPaymentId(@PathVariable(value = "id") Long id) { 
			  return paymentRepository.findById(id).orElse(null); }
	  
	  
	  
	  
	  @GetMapping("/payment/roomRent/room/{id}") 
	  public Payment getroomRentByPaymentId(@PathVariable(value = "id") Long id) { 
			  return  paymentRepository.findById(id).orElse(null); }
	  
	 
	
	
	@GetMapping("/payment/{id}")
	public ResponseEntity<Payment> getPaymentById(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));
		return ResponseEntity.ok().body(payment);
	}

	@PostMapping("/payment")
	public Payment createPayment(@Valid @RequestBody Payment payment) {
		return paymentRepository.save(payment);
	}

	@PutMapping("/payment/{id}")
	public ResponseEntity<Payment> updatePayment(@PathVariable(value = "id") Long payment_Id,
			@Valid @RequestBody Payment paymentDetails) throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(payment_Id)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " +payment_Id));

		payment.setPaymentThrough(paymentDetails.getPaymentThrough());
		payment.setTransactionId(paymentDetails.getTransactionId());
		payment.setBankName(paymentDetails.getBankName());
		final Payment updatedpayment = paymentRepository.save(payment);
		return ResponseEntity.ok(payment);
	}

	@DeleteMapping("/payment/{id}")
	public Map<String, Boolean> deletepayment(@PathVariable(value = "id") Long paymentId)
			throws ResourceNotFoundException {
		Payment payment = paymentRepository.findById(paymentId)
				.orElseThrow(() -> new ResourceNotFoundException("Payment not found for this id :: " + paymentId));

		paymentRepository.delete(payment);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}