package com.srans.nestserver.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RestController;
import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.TenantRegistration;
import com.srans.nestserver.repository.TenantRegistrationRepository;

@CrossOrigin(origins = "http://localhost:4200",allowedHeaders = "*") 
@RestController
@RequestMapping("/api/v1")
public class TenantRegistrationController {
	Logger logger = LoggerFactory.getLogger(TenantRegistrationController.class);
	@Autowired
	private TenantRegistrationRepository tenantregistrationRepository;
	
	
	@GetMapping("/tenantregistrations")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_USER')")
	public List<TenantRegistration> getAllTenantRegistration() {
		return tenantregistrationRepository.findAll();
	}
	

	@GetMapping("/tenantregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_USER')")
	public ResponseEntity<TenantRegistration> getTenantRegistrationById(@PathVariable(value = "id") Long tenantregistrationId)
			throws ResourceNotFoundException {
		TenantRegistration tenantRegistration= tenantregistrationRepository.findById(tenantregistrationId)
				.orElseThrow(() -> new ResourceNotFoundException("TenantRegistration not found for this id :: " + tenantregistrationId));
		return ResponseEntity.ok().body(tenantRegistration);
	}

	@PostMapping("/tenantregistrations")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_USER')")
	public TenantRegistration createTenantRegistration(@Valid @RequestBody TenantRegistration tenantregistration) {
		return tenantregistrationRepository.save(tenantregistration);
	}

	@PutMapping("/tenantregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_USER') OR hasRole('ROLE_USER')")
	public ResponseEntity<TenantRegistration> updateTenantRegistration(@PathVariable(value = "id") Long tenantregistration_Id,
			@Valid @RequestBody TenantRegistration hostelownerDetails) throws ResourceNotFoundException {
		TenantRegistration tenantregistration = tenantregistrationRepository.findById(tenantregistration_Id)
				.orElseThrow(() -> new ResourceNotFoundException("TenantRegistration not found for this id :: " +tenantregistration_Id));
		tenantregistration.setName(tenantregistration.getName());
		tenantregistration.setPhoneNumber(tenantregistration.getPhoneNumber());
		tenantregistration.setEmailId(tenantregistration.getEmailId());
		tenantregistration.setPassword(tenantregistration.getPassword());
		tenantregistration.setConfirmPassword(tenantregistration.getConfirmPassword());
		
   
		final TenantRegistration updatedTenantRegistration = tenantregistrationRepository.save(tenantregistration);
		return ResponseEntity.ok(updatedTenantRegistration);
	}

	@DeleteMapping("/tenantregistrations/{id}")
	@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
	public Map<String, Boolean> deleteTenantRegistration(@PathVariable(value = "id") Long tenantregistrationId)
			throws ResourceNotFoundException {
		TenantRegistration tenantregistration = tenantregistrationRepository.findById(tenantregistrationId)
				.orElseThrow(() -> new ResourceNotFoundException("TenantRegistration not found for this id :: " + tenantregistrationId));

		tenantregistrationRepository.delete(tenantregistration);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}
}
