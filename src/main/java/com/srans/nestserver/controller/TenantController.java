package com.srans.nestserver.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.hibernate.usertype.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Bed;
import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.model.TenantBooking;
import com.srans.nestserver.repository.BedRepository;
import com.srans.nestserver.repository.PaymentRepository;
import com.srans.nestserver.repository.TenantBookRepository;
import com.srans.nestserver.repository.TenantRepository;
import com.srans.nestserver.service.StorageService;
import com.srans.nestserver.service.TenantService;
import com.srans.nestserver.util.NSException;

@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class TenantController {

	Logger logger = LoggerFactory.getLogger(TenantController.class);

	@Autowired
	private TenantRepository tenantRepository;

	@Autowired
	private StorageService storageService;

	@Autowired
	private TenantBookRepository tenantBookRepository;

	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private BedRepository bedRepository;
	
	@Autowired
	private TenantService tenantService;
	 

	
	@PostMapping("/tenants")
	@PreAuthorize("permitAll()")
	public Tenant saveTenant(@Valid @RequestBody Tenant tenant) throws NSException {

		logger.info("IN::POST::/tenants::savetenant::" + tenant);

		Tenant responseTenant = tenantRepository.save(tenant);
		
		if(responseTenant.getUserId() != -1){ 
			
			tenant.getTenantBooking().setTenantId(responseTenant.getUserId()); 
			TenantBooking tenantBooking = tenantBookRepository.save(tenant.getTenantBooking());
			
			//Update Bed with alloted_state as N
			Bed bed = new Bed();
			bed.setId(tenantBooking.getRoomBedId());
			bed.setHostelId(tenantBooking.getHostelId());
			bed.setFloorId(tenantBooking.getFloorId());
			bed.setRoomId(tenantBooking.getRoomId());
			bed.setAlloted('Y');
			bed.setUpdatedAt(new Date()); 
			
			responseTenant.setTenantBooking(tenantBooking); 
			responseTenant.setBed( bedRepository.saveAndFlush(bed));  
			
			
			//Save Payment Information 
			responseTenant.setPayment( paymentRepository.save(tenant.getPayment())); 

			//Now drop an email to tenant 
			tenantService.triggerAlertEmail(responseTenant);

			
		}else{
			throw new NSException("Unable to save tenant ");
		} 

		logger.info("OUT::POST::/tenants::saveTenant::" + tenant);
		return responseTenant;
	}

	@GetMapping("/tenants")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	//@PreAuthorize("permitAll()")
	public List<Tenant> getAllTenants() {
		return tenantRepository.findAll();
	}
	
	@GetMapping("/tenants/{id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Tenant> getTenantById(@PathVariable(value = "id") Long TenantId)
			throws ResourceNotFoundException {
		Tenant tenant = tenantRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
		return ResponseEntity.ok().body(tenant);
	}
	
	@GetMapping("/tenants/byname/{name}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	//@PreAuthorize("permitAll()")
	public ResponseEntity<Tenant> getTenantByName(@PathVariable(value = "name") String name)
			throws ResourceNotFoundException {
		Tenant tenant = tenantRepository.findByName(name); 
		return ResponseEntity.ok().body(tenant);
	}

	@PostMapping("/tenants/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeTenantImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/tenants/{id}/upload/{cat}::uploadTenantImages::" + id + "::" + cat);
		storageService.storeTenantImage(file, cat, id);
		logger.info("OUT::POST:://tenants/uploadImage/{cat}/{id}::uploadTenantImages::" + id + "::" + cat);

	}

	@GetMapping("/tenants/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retrivetenantImage(id, cat)));

	}

	@GetMapping("/tenantsidproof/{id}/retrive/{cat}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<InputStreamResource> retriveIdproofImage(@PathVariable("id") Long id,
			@PathVariable("cat") String cat) throws NSException, IOException {

		return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG)
				.body(new InputStreamResource(storageService.retriveIdproofImage(id, cat)));

	}

	@PostMapping("/tenantsidproof/{id}/upload/{cat}")
	@PreAuthorize("permitAll()")
	public void storeIdproofImage(@PathVariable("id") Long id, @RequestParam("file") MultipartFile file,
			@PathVariable("cat") String cat) throws NSException {

		logger.info("In::POST::/tenants/{id}/upload/{cat}::uploadIdproofImages::" + id + "::" + cat);
		storageService.storeIdproofImage(file, cat, id);
		logger.info("OUT::POST:://tenants/uploadImage/{cat}/{id}::uploadIdproofImage::" + id + "::" + cat);

	}

	/*
	 * @PostMapping("/tenants") public Tenant createUser(@RequestBody Tenant tenant)
	 * { System.out.println("User : " + tenant); return
	 * tenantRepository.save(tenant); }
	 */

	@PutMapping("/tenants/{Id}")
	@PreAuthorize("permitAll()")
	public ResponseEntity<Tenant> updateUser(@PathVariable(value = "Id") Long TenantId,
			@Valid @RequestBody Tenant tenantDetails) throws ResourceNotFoundException {
		Tenant tenant = tenantRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));

		tenant.setUserId(tenant.getUserId());
		tenant.setBloodGroup(tenant.getBloodGroup());
		tenant.setContactNumber(tenant.getContactNumber());
		tenant.setDob(tenant.getDob());
		tenant.setEmailId(tenant.getEmailId());
      tenant.setPermanentAddress(tenant.getPermanentAddress());
		final Tenant updatedTenant = tenantRepository.save(tenant);
		return ResponseEntity.ok(updatedTenant);
	}

	@DeleteMapping("/tenants/{Id}")
	@PreAuthorize("permitAll()")
	public <tenantRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "Id") Long TenantId)
			throws ResourceNotFoundException {
		@SuppressWarnings("unused")
		Tenant tenant = tenantRepository.findById(TenantId)
				.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this id :: " + TenantId));

		tenantRepository.deleteById(TenantId);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}