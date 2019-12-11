package com.srans.nestserver.controller;

import java.io.IOException;
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
import com.srans.nestserver.model.Tenant;
import com.srans.nestserver.repository.TenantRepository;
import com.srans.nestserver.service.StorageService;
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

@GetMapping("/tenant")
public List<Tenant> getAllTenant() {
return tenantRepository.findAll();
}

@GetMapping("/tenant/{Id}")
public ResponseEntity<Tenant> getTenantById(@PathVariable(value = "Id") Long TenantId)
throws ResourceNotFoundException {
Tenant tenant = tenantRepository.findById(TenantId)
.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
return ResponseEntity.ok().body(tenant);
}



@PostMapping("/tenant/{id}/upload/{cat}")
public void storeTenantImage( @PathVariable("id") Long id, @RequestParam("file") MultipartFile file,@PathVariable("cat") String cat) throws NSException {

logger.info("In::POST::/tenant/{id}/upload/{cat}::uploadTenantImages::"+id+"::"+cat); 
storageService.storeTenantImage(file, cat, id);
logger.info("OUT::POST:://tenant/uploadImage/{cat}/{id}::uploadTenantImages::"+id+"::"+cat); 

}


@GetMapping("/tenant/{id}/retrive/{cat}")
public ResponseEntity<InputStreamResource> retriveHostelImage(@PathVariable("id") Long id, @PathVariable("cat") String cat) throws NSException, IOException {

return ResponseEntity
.ok()
.contentType(MediaType.IMAGE_PNG)
.body(new InputStreamResource(storageService.retrivetenantImage(id, cat)));

}


@GetMapping("/tenantidproof/{id}/retrive/{cat}")
public ResponseEntity<InputStreamResource> retriveIdproofImage(@PathVariable("id") Long id, @PathVariable("cat") String cat) throws NSException, IOException {

return ResponseEntity
.ok()
.contentType(MediaType.IMAGE_PNG)
.body(new InputStreamResource(storageService.retriveIdproofImage(id, cat)));

}

@PostMapping("/tenantidproof/{id}/upload/{cat}")
public void storeIdproofImage( @PathVariable("id") Long id, @RequestParam("file") MultipartFile file,@PathVariable("cat") String cat) throws NSException {

logger.info("In::POST::/tenant/{id}/upload/{cat}::uploadIdproofImages::"+id+"::"+cat); 
storageService.storeIdproofImage(file, cat, id);
logger.info("OUT::POST:://tenant/uploadImage/{cat}/{id}::uploadIdproofImage::"+id+"::"+cat); 

}

@PostMapping("/tenant")
public Tenant createUser(@RequestBody Tenant tenant) {
System.out.println("User : " + tenant);
return tenantRepository.save(tenant);
}

@PutMapping("/tenant/{Id}")
public ResponseEntity<Tenant> updateUser(@PathVariable(value = "Id") Long TenantId,
@Valid @RequestBody Tenant tenantDetails) throws ResourceNotFoundException {
Tenant tenant = tenantRepository.findById(TenantId)
.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));

tenant.setUserId(tenant.getUserId());
tenant.setBloodGroup(tenant.getBloodGroup());
tenant.setContactNumber(tenant.getContactNumber());
tenant.setDob(tenant.getDob());
tenant.setEmailId(tenant.getEmailId());
tenant.setEmergencyContactNumber(tenant.getEmergencyContactNumber());
tenant.setFatherName(tenant.getMotherName());
tenant.setFatherphoneNumber(tenant.getFatherphoneNumber());
tenant.setMotherphoneNumber(tenant.getMotherphoneNumber());
tenant.setName(tenant.getName());
tenant.setNameOfTheEmployer(tenant.getNameOfTheEmployer());
tenant.setOfficeAddress(tenant.getOfficeAddress());
tenant.setPermanetAddress(tenant.getPermanetAddress());
tenant.setTelephoneNumber(tenant.getTelephoneNumber());


final Tenant updatedTenant = tenantRepository.save(tenant);
return ResponseEntity.ok(updatedTenant);
}

@DeleteMapping("/tenant/{Id}")
public <tenantRepository> Map<String, Boolean> deleteUser(@PathVariable(value = "Id") Long TenantId)
throws ResourceNotFoundException {
Tenant tenant = tenantRepository.findById(TenantId)
.orElseThrow(() -> new ResourceNotFoundException("SransUser not found for this id :: " + TenantId));

tenantRepository.deleteById(TenantId);
Map<String, Boolean> response = new HashMap<>();
response.put("deleted", Boolean.TRUE);
return response;
}
}