package com.srans.nestserver.controller;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.communication.NiodsMailer;
import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Invoice;
import com.srans.nestserver.model.Room;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.FloorRepository;
import com.srans.nestserver.repository.InvoiceRepository;
import com.srans.nestserver.repository.RoomRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.MailTemplates;

import freemarker.template.TemplateException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class InvoiceController {

	Logger logger = LoggerFactory.getLogger(InvoiceController.class);
	@Autowired
	private InvoiceRepository invoiceRepository;

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private FloorRepository floorRepository;
	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private NiodsMailer niodsMailer;

	@GetMapping("invoice/tenant/{Id}")
	public ResponseEntity<User> getTenantById(@PathVariable(value = "Id") long TenantId)
			throws ResourceNotFoundException {
		logger.info("In::TenantId::" + TenantId);
		User user = userRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));

		logger.info("Out::tenant::" + user);
		return ResponseEntity.ok().body(user);
	}

	@GetMapping("/invoice")
	public List<Invoice> getAllInvoice() {
		logger.info("all invoices");
		return invoiceRepository.findAll();
	}

	@GetMapping("/invoice/{id}")
	public ResponseEntity<Invoice> getInvoicesById(@PathVariable(value = "id") long invoiceId)
			throws ResourceNotFoundException {
		logger.info("In::Id::" + invoiceId);

		Invoice invoice = invoiceRepository.findById(invoiceId)
				.orElseThrow(() -> new ResourceNotFoundException("invoice not found for this id :: " + invoiceId));
		logger.info("Out::invoice::" + invoice);

		return ResponseEntity.ok().body(invoice);
	}

	@GetMapping("invoice/hostels/floor/{id}/room/{room_id}")
	public ResponseEntity<Room> getFloorById(@PathVariable(value = "id") long floor_id,
			

			@PathVariable(value = "room_id") long room_id) {
		logger.info("GET FLOOR AND ROOM DETAILS");

		logger.info("In::Invoice::" + room_id  + "::" +floor_id);
		if (!floorRepository.existsById(floor_id)) {
			throw new ResourceNotFoundException("FloorId " + floor_id + " not found");

		}

		Room room = roomRepository.findById(room_id).orElseThrow(() -> new ResourceNotFoundException(
				"Floor not found for this Floorid :: " + floor_id + "Floor not found for this Floor id::" + room_id));
		logger.info("Out::room::" + room);

		return ResponseEntity.ok().body(room);
	}

	@PostMapping("/invoice")
	public Invoice createinvoice(@Valid @RequestBody Invoice invoice) {
		logger.info("IN::POST::/invoice::saveInvoice::" + invoice);

		invoice = invoiceRepository.save(invoice);
		logger.info("OUT::POST::/invoice::saveInvoice::" + invoice);
		return invoice;

	}


	/*
	 * @PutMapping("/invoice/{id}") public ResponseEntity<Invoice>
	 * updateInvoice(@PathVariable(value = "id") long invoiceId,
	 * 
	 * @Valid @RequestBody Invoice invoiceDetails) throws ResourceNotFoundException
	 * { logger.info("IN::POST::/invoice::updateInvoice::" + invoiceId);
	 * 
	 * Invoice invoice = invoiceRepository.findById(invoiceId) .orElseThrow(() ->
	 * new ResourceNotFoundException("invoice not found for this id :: " +
	 * invoiceId));
	 * 
	 * invoice.setPaymentType(invoiceDetails.getPaymentType());
	 * invoice.setActive(invoiceDetails.getActive());
	 * invoice.setModifiedBy(invoiceDetails.getModifiedBy());
	 * invoice.setModifiedDate(invoiceDetails.getModifiedDate());
	 * invoice.setCreatedBy(invoiceDetails.getCreatedBy());
	 * invoice.setCreatedDateTime(invoiceDetails.getCreatedDateTime());
	 * invoice.setInvoiceDescripition(invoiceDetails.getInvoiceDescripition());
	 * invoice.setInvoiceDate(invoiceDetails.getInvoiceDate());
	 * invoice.setRoomRent(invoiceDetails.getRoomRent());
	 * invoice.setDiscountAmount(invoiceDetails.getDiscountAmount());
	 * invoice.setDueAmount(invoiceDetails.getDueAmount());
	 * 
	 * final Invoice updatedInvoice = invoiceRepository.save(invoice);
	 * logger.info("IN::OUT::/invoice::updateInvoice::" + invoiceId);
	 * 
	 * return ResponseEntity.ok(updatedInvoice); }
	 */
	/*
	 * @DeleteMapping("/invoice/{id}") public Map<String, Boolean>
	 * deleteinvoice(@PathVariable(value = "id") long invoiceId) throws
	 * ResourceNotFoundException { logger.info("IN::POST::/invoice::deleteinvoice::"
	 * + invoiceId);
	 * 
	 * Invoice invoice = invoiceRepository.findById(invoiceId) .orElseThrow(() ->
	 * new ResourceNotFoundException("invoice not found for this id :: " +
	 * invoiceId));
	 * 
	 * invoiceRepository.delete(invoice); Map<String, Boolean> response = new
	 * HashMap<>(); response.put("deleted", Boolean.TRUE);
	 * logger.info("IN::OUT::/invoice::deleteinvoice::" + invoiceId);
	 * 
	 * return response; }
	 */

	// Invoice generate for multiple tenant...
	@GetMapping(value = "/invoices/{ids}")
	@ResponseBody
	List<Invoice> getTenantById(@PathVariable long[] ids) {
		logger.info("In::getTenantById::" + ids);

		List<Invoice> invoice = new ArrayList<>();

		for (long id : ids) {
			Invoice invoiceData = new Invoice();

			System.out.println(id);
			List<Object> invoiceInfo = invoiceRepository.invoiceData(id);
			for (Iterator<Object> iterator = invoiceInfo.iterator(); iterator.hasNext();) {
				Object[] object = (Object[]) iterator.next();

				for (int i = 0; i < object.length; i++) {

					switch (i) {
					case 0:
						invoiceData.setName((String) object[i]);

						break;
					case 1:
						invoiceData.setEmailId((String) object[i]);
						break;
					case 2:
						invoiceData.setRoomRent(((BigInteger) object[i]).longValue());
						break;
					case 3:
						invoiceData.setRoomId(((BigInteger) object[i]).longValue());
						break;
					case 4:
						invoiceData.setHostelId(((BigInteger) object[i]).longValue());
						break;
					case 5:
						invoiceData.setFloorName((String) object[i]);
						break;
					case 6:
						invoiceData.setBankName((String) object[i]);
						break;
					default:
						break;
					}

				}

			}

			invoice.add(invoiceData);

			String email = invoiceData.getEmailId();
			String subject = "Invoice";
			String ccMail = null;
			String bccMail = null;
			String message = MailTemplates.TENANT_INVOICE_TEMPLATE.replaceAll("##USER_NAME##", "rahul")
					.replaceAll("##PASSWORD##", "1234").replaceAll("##HOSTEL_NAME##", "NIODS");

			try {
				niodsMailer.sendEmail(email, subject, ccMail, bccMail, message);
			} catch (MailException | MessagingException | IOException | TemplateException e) {

				e.printStackTrace();
			}

		}
		logger.info("OUT::getTenantById::" + ids);

		return invoice;
	}
}
