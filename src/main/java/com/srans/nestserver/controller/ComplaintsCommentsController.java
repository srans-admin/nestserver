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
import com.srans.nestserver.model.ComplaintsComment;
import com.srans.nestserver.model.Hostel;
import com.srans.nestserver.model.User;
import com.srans.nestserver.repository.ComplaintsCommentRepository;
import com.srans.nestserver.repository.ComplaintsRepository;
import com.srans.nestserver.repository.HostelRepository;
import com.srans.nestserver.repository.UserRepository;


@CrossOrigin(origins = "*",allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ComplaintsCommentsController {
Logger logger = LoggerFactory.getLogger(RolesController.class);
@Autowired
private ComplaintsCommentRepository complaintscommentRepository;
@Autowired
private UserRepository userRepository;
@Autowired
private HostelRepository hostelRepository;
@Autowired
private ComplaintsRepository complaintsRepository;

@GetMapping("/complaintscomments")
@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
public List<ComplaintsComment> getAllComplaintsComment() {
return complaintscommentRepository.findAll();
}


@GetMapping("/complaintscomments/{id}")
@PreAuthorize("hasRole('ROLE_SUPERADMIN') OR hasRole('ROLE_ADMIN')")
public ResponseEntity<ComplaintsComment> getComplaintsCommentById(@PathVariable(value = "id") Long complaintscommentId)
throws ResourceNotFoundException {
	ComplaintsComment complaintscomment = complaintscommentRepository.findById(complaintscommentId)
.orElseThrow(() -> new ResourceNotFoundException("ComplaintsComment not found for this id :: " + complaintscommentId));
return ResponseEntity.ok().body(complaintscomment);
}



@GetMapping("/complaintscomments/complaints")
@PreAuthorize("permitAll()")
public List<Complaints> getAllComplaints() {
return complaintsRepository.findAll();
}


@GetMapping("/complaintscomments/complaints/{id}")
@PreAuthorize("permitAll()")
public ResponseEntity<Complaints> getComplaintsById(@PathVariable(value = "id") Long complaintsId)
throws ResourceNotFoundException {
Complaints complaints = complaintsRepository.findById(complaintsId)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));
return ResponseEntity.ok().body(complaints);
}


@GetMapping("/complaintscomments/hostelownerid/{id}")
@PreAuthorize("permitAll()")
public ResponseEntity<Hostel> gethostelownerById(@PathVariable(value = "id") long hostelownerid)
		throws ResourceNotFoundException {
	Hostel hostel = hostelRepository.findByhostelownerid(hostelownerid)
			.orElseThrow(() -> new ResourceNotFoundException("hostelownerid not found for this id :: " + hostelownerid));
	return ResponseEntity.ok().body(hostel);
}




@PostMapping("/complaintscomments")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public ComplaintsComment createComplaintsComment(@Valid @RequestBody ComplaintsComment complaintscomment) {
return complaintscommentRepository.save(complaintscomment);
}

@PutMapping("/complaintscomments/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public ResponseEntity<ComplaintsComment> updateComplaintsComments(@PathVariable(value = "id") Long complaintscomments_Id,
@Valid @RequestBody ComplaintsComment complaintscommentDetails) throws ResourceNotFoundException {
ComplaintsComment complaintscomment = complaintscommentRepository.findById(complaintscomments_Id)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " +complaintscomments_Id));
complaintscomment.setComments(complaintscommentDetails.getComments());
complaintscomment.setComplaintsid(complaintscommentDetails.getComplaintsid());
complaintscomment.setResolutiondate(complaintscommentDetails.getResolutiondate());
complaintscomment.setResolutionstatus(complaintscommentDetails.getResolutionstatus());
complaintscomment.setComplaints(complaintscommentDetails.getComplaints());
final ComplaintsComment updatedComplaintsComments = complaintscommentRepository.save(complaintscomment);
return ResponseEntity.ok(updatedComplaintsComments);
}

@DeleteMapping("/complaintscomments/{id}")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public Map<String, Boolean> deleteComplaintsComments(@PathVariable(value = "id") Long complaintscommentId)
throws ResourceNotFoundException {
ComplaintsComment complaintscomment = complaintscommentRepository.findById(complaintscommentId)
.orElseThrow(() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintscommentId));

complaintscommentRepository.delete(complaintscomment);
Map<String, Boolean> response = new HashMap<>();
response.put("deleted", Boolean.TRUE);
return response;
}
}