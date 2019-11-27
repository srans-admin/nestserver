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
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Invoice;
import com.srans.nestserver.repository.InvoiceRepository;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {
	//Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	@Autowired
	private InvoiceRepository invoiceRepository;

	@GetMapping("/invoice")
	public List<Invoice> getAllInvoice() {
		return  invoiceRepository.findAll();
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> getInvoicesById(@PathVariable(value = "id") Long invoiceId)
			throws ResourceNotFoundException {
		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));
		return ResponseEntity.ok().body(invoice);
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

		invoice.setName(invoiceDetails.getName());
		invoice.setpaymentType(invoiceDetails.getPaymentType());
		invoice.setAmount(invoiceDetails.getAmount());
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
