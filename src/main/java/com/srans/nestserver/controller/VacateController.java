package com.srans.nestserver.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.VacationRepository;
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

	@Autowired
	private HostelRepository hostelRepo;

	@Autowired
	private VacationRepository vacationRepo;

	Logger logger = LoggerFactory.getLogger(VacateController.class);

	@PostMapping("/users/vacate")
	@PreAuthorize("permitAll()")
	public Vacation saveVacationDetails(@Valid @RequestBody Vacation vacation)
			throws NSException, MailException, MessagingException, IOException, TemplateException {
		logger.info("IN::POST::/users/vacation::saveVacationDetails::" + vacation);

		vacation = vacateService.processVacation(vacation);

		logger.info("OUT::POST::/users/vacation::saveVacationDetails::" + vacation);

		return vacation;
	}

	@GetMapping("users/vacate/")

	@PreAuthorize("permitAll()")
	public List<Vacation> displayVacationDetails(@RequestParam("id") Long adminId) throws NSException {
		
		logger.info("IN::POST::/users/vacation::displayVacationDetails::" + adminId);
		
		
		List<Vacation> vacationInfo = new ArrayList<Vacation>();
		Long[] AllHostelIdForAdmin = hostelRepo.getAdminHostelId(adminId);
		for (Long hostelId : AllHostelIdForAdmin) {
			Long[] tenantId = tenantBookRepository.getAllTenant(hostelId);

			for (Long tid : tenantId) {
				if (vacationRepo.checkTenantId(tid) > 0) {
					final Vacation vacate = new Vacation();
					vacationRepo.getVacationRequest(tid).stream().forEach(vacating -> {
						vacate.setDate(vacating.getDate());
						vacate.setComment(vacating.getComment());
						vacate.setApprovedStatus(vacating.getApprovedStatus());
						vacate.setRefundAmount(vacating.getRefundAmount());
						vacate.setTenantId(vacating.getTenantId());
						vacate.setCreatedAt(vacating.getCreatedAt());

						tenantBookRepository.findByTenantId(tid).stream().forEach(tenant -> {
							vacate.setHostelId(tenant.getHostelId());
							Hostel hostel = hostelRepo.getOne(tenant.getHostelId());
							vacate.setHostelName(hostel.getHostelName());
							vacate.setBedId(tenant.getRoomBedId());
						});

						
						

					});
					vacationInfo.add(vacate);

				}
				

			}

		}

		logger.info("OUT::POST::/users/vacation::displayVacationDetails::" + adminId);

		return vacationInfo;

	}

	@PutMapping("users/vacate")
	@PreAuthorize("permitAll()")
	public void approveVaction(@RequestParam Long tenantId) throws NSException {
		logger.info("IN::POST::/users/vacation::approveVaction::" + tenantId);
		logger.info("OUT::POST::/users/vacation::approveVaction::" + tenantId);

		vacateService.approveVacation(tenantId);

	}

}
