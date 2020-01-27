package com.srans.nestserver.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.service.VacateService;
import com.srans.nestserver.util.NSException;

import freemarker.template.TemplateException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")

public class VacateController {

	@Autowired
	private VacateService vacateService = new VacateService();

	@Autowired
	private TenantBookRepository tenantBookRepository;

	@Autowired
	private PaymentRepository paymentRepo;

	Logger logger = LoggerFactory.getLogger(VacateController.class);

	@PostMapping("/users/vacate")
	@PreAuthorize("permitAll()")
	public Vacation saveVacationDetails(@Valid @RequestBody Vacation vacation) throws NSException, MailException, MessagingException, IOException, TemplateException {
		logger.info("IN::POST::/users/vacation::saveVacationDetails::" + vacation);

		vacation = vacateService.processVacation(vacation);

		logger.info("OUT::POST::/users/vacation::saveVacationDetails::" + vacation);

		
		return vacation;
	}

	@GetMapping("users/vacate/{id}")
	@PreAuthorize("permitAll()")
	public Vacation displayVacationDetails(@PathVariable(value = "id") Long tenantId) throws NSException {
		logger.info("IN::POST::/users/vacation::displayVacationDetails::" + tenantId);
		
		logger.info("OUT::POST::/users/vacation::displayVacationDetails::" + tenantId);

		
		return vacateService.getVacationDetails(tenantId);

	}
	
	@GetMapping("users/vacate/approved/{id}")
	@PreAuthorize("permitAll()")
	public String vacationApproved(@PathVariable(value="id") Long tenantId)throws NSException{
		logger.info("IN::POST::/users/vacation::vacationApproved::" + tenantId);
		logger.info("OUT::POST::/users/vacation::vacationApproved::" + tenantId);

		return vacateService.approveVacation(tenantId);
		
	}

}
