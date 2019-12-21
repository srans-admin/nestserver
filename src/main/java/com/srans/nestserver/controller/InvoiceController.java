package com.srans.nestserver.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Invoice;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.InvoiceRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.TenantRepository;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private TenantRepository tenantRepository;
	@Autowired
	private FloorRepository floorRepository;
	@Autowired
	private RoomRepository roomRepository;

	@GetMapping("invoice/tenant/{Id}")
	public ResponseEntity<Tenant> getTenantById(@PathVariable(value = "Id") Long TenantId)
			throws ResourceNotFoundException {
		logger.info("In::TenantId::"+TenantId);
		Tenant tenant = tenantRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
		
		logger.info("Out::tenant::"+tenant);
		return ResponseEntity.ok().body(tenant);
	}

	@GetMapping("/invoice")
	public List<Invoice> getAllInvoice() {
		return invoiceRepository.findAll();
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> getInvoicesById(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));
		return ResponseEntity.ok().body(invoice);
	}

	@GetMapping("invoice/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Room> getFloorById(@PathVariable(value = "id") Long floor_id,

			@PathVariable(value = "room_id") Long room_id) {
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");
		}

		Room room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/invoice")
	public Invoice createinvoice(@Valid @RequestBody Invoice invoice) {
		return invoiceRepository.save(invoice);
	}

	@PutMapping("/invoice/{id}")
	public ResponseEntity<Invoice> updateInvoice(@PathVariable(value = "id") Long invoiceId,
			@Valid @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));

		invoice.setPaymentType(invoiceDetails.getPaymentType());
		invoice.setActive(invoiceDetails.getActive());
		invoice.setModifiedBy(invoiceDetails.getModifiedBy());
		invoice.setModifiedDate(invoiceDetails.getModifiedDate());
		invoice.setCreatedBy(invoiceDetails.getCreatedBy());
		invoice.setCreatedDateTime(invoiceDetails.getCreatedDateTime());
		invoice.setInvoiceDescripition(invoiceDetails.getInvoiceDescripition());
		invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
		invoice.setRoomRent(invoiceDetails.getRoomRent());
		invoice.setDiscountAmount(invoiceDetails.getDiscountAmount());
		invoice.setDueAmount(invoiceDetails.getDueAmount());
		invoice.setTotalAmount(invoiceDetails.getTotalAmount());
		final Invoice updatedInvoice = invoiceRepository.save(invoice);
		return ResponseEntity.ok(updatedInvoice);
	}

	@DeleteMapping("/invoice/{id}")
	public Map<String, Boolean> deleteinvoice(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));

		invoiceRepository.delete(invoice);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
