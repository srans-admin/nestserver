package com.srans.nestserver.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.model.Vacation;
import com.srans.nestserver.service.VacateService;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")

public class VacateController {

	@Autowired
	private VacateService vacateService=new VacateService();
	
	Logger logger = LoggerFactory.getLogger(VacateController.class);
	
	@PostMapping("/vacate")
	@PreAuthorize("permitAll()")
	public Vacation saveVacationDetails(@Valid @RequestBody Vacation vacation)throws NSException{
		logger.info("IN::POST::/vacation::saveVacationDetails::"+vacation );
		
		vacation =vacateService.processVacation(vacation);
		
		logger.info("OUT::POST::/vacation::saveVacationDetails::" + vacation);
		
		return vacation;
	}

}
