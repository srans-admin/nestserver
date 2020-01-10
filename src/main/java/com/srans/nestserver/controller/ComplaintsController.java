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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Complaints;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.ComplaintsRepository;
import com.srans.nestserver.repository.UserRepository;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ComplaintsController {
Logger logger = LoggerFactory.getLogger(RolesController.class);
@Autowired
private ComplaintsRepository complaintsRepository;
@Autowired
private UserRepository userRepository;

@GetMapping("/complaints")
@PreAuthorize("permitAll()")
public List<Complaints> getAllComplaints() {
return complaintsRepository.findAll();
}


@GetMapping("/complaints/{id}")
@PreAuthorize("permitAll()")
public ResponseEntity<Complaints> getComplaintsById(@PathVariable(value = "id") Long complaintsId)
throws ResourceNotFoundException {
Complaints complaints = complaintsRepository.findById(complaintsId)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));
return ResponseEntity.ok().body(complaints);
}




@GetMapping("/complaints/users/{id}")
@PreAuthorize("permitAll()")
public ResponseEntity<User> getTenantById(@PathVariable(value = "id") Long TenantId)
throws ResourceNotFoundException {
User user = userRepository.findById(TenantId)
.orElseThrow(() -> new ResourceNotFoundException("Tenant not found for this Id :: " + TenantId));
return ResponseEntity.ok().body(user);
}

@GetMapping("/complaints/users/byname/{name}")
@PreAuthorize("permitAll()")
//@PreAuthorize("permitAll()")
public ResponseEntity<User> getTenantByName(@PathVariable(value = "name") String name)
throws ResourceNotFoundException {
User user = userRepository.findByName(name);
return ResponseEntity.ok().body(user);
}

@PostMapping("/complaints")
@PreAuthorize("permitAll()")
public Complaints createComplaints(@Valid @RequestBody Complaints complaints) {
return complaintsRepository.save(complaints);
}

@PutMapping("/complaints/{id}")
@PreAuthorize("permitAll()")
public ResponseEntity<Complaints> updateComplaints(@PathVariable(value = "id") Long complaints_Id,
@Valid @RequestBody Complaints complaintsDetails) throws ResourceNotFoundException {
Complaints complaints = complaintsRepository.findById(complaints_Id)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " +complaints_Id));

complaints.setDescription(complaintsDetails.getDescription());
complaints.setId(complaintsDetails.getId());

final Complaints updatedComplaints = complaintsRepository.save(complaints);
return ResponseEntity.ok(updatedComplaints);
}

@DeleteMapping("/complaints/{id}")
@PreAuthorize("permitAll()")
public Map<String, Boolean> deleteRole(@PathVariable(value = "id") Long complaintsId)
throws ResourceNotFoundException {
Complaints complaints = complaintsRepository.findById(complaintsId)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));

complaintsRepository.delete(complaints);
Map<String, Boolean> response = new HashMap<>();
response.put("deleted", Boolean.TRUE);
return response;
}
}