package com.srans.nestserver.controller;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.srans.nestserver.exception.ResourceNotFoundException;
import com.srans.nestserver.model.Complaint;
import com.srans.nestserver.model.ComplaintComment;
import com.srans.nestserver.model.ComplaintWrapper;
import com.srans.nestserver.repository.ComplaintCommentsRepository;
import com.srans.nestserver.repository.ComplaintRepository;
import com.srans.nestserver.repository.UserRepository;
import com.srans.nestserver.util.ComplaintHistoryUtil;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class ComplaintController {
	
	private Logger logger = LoggerFactory.getLogger(ComplaintController.class);
	
	@Autowired
	private ComplaintRepository complaintRepository;

	@Autowired
	private ComplaintCommentsRepository complaintCommentsRepository;

	
	@Autowired
	public UserRepository userRepository = null;
	 
	@GetMapping("/complaints")
	@PreAuthorize("permitAll()")
	public List<ComplaintWrapper> getAllComplaints(@RequestParam("uid") long userId, @RequestParam("role") String role) {
		
		logger.info("IN::complaints::"+userId+"::"+role);
		List<ComplaintWrapper> complaintWapper= new ArrayList<>();
		
		List<Complaint> complaints = null;
		if(role != null && role.equalsIgnoreCase("ADMIN")) {
			complaints = complaintRepository.getCompliantsForAdmin(userId);
		}else {
			Optional<List<Complaint>> complaintsOpt = null;
			try {
				complaintsOpt = complaintRepository.getCompliantsForUser(userId);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			complaints = complaintsOpt.get();
		}
		
		for(Complaint complaint : complaints) {
			
			ComplaintWrapper currComplaintWapper = new ComplaintWrapper(); 
			currComplaintWapper.setComplaint(complaint);
			currComplaintWapper.setComplaintComments(complaintCommentsRepository.getAllComments(complaint.getId()));  
		
			complaintWapper.add(currComplaintWapper);
		} 
		
	    logger.info("OUT::complaints::"+complaintWapper);
		return complaintWapper;
	}
	
	
	@PostMapping("/complaints")
	@PreAuthorize("permitAll()")
	public Complaint createComplaint(@Valid @RequestBody Complaint complaint) throws Exception {
		 logger.info("IN::POST::/complaints::saveComplaints::" + complaint);

		//Find AdminId from UserRepo and save 
		Optional<Object> adminId = complaintRepository.getAdminIdForUser(complaint.getUserId());
		
		System.out.println("adminId s : "+adminId);
		if(adminId.isPresent()) {
			
			Object aid = adminId.get();
			if(aid instanceof String) {
				complaint.setAdminId(Long.parseLong((String) aid)); ;
			}
			//complaint.setAdminId((Long)adminId.get());
		}else {
			throw new Exception("Unable to find Admin Id for the user : "+complaint.getUserId());
		}
		 logger.info("IN::OUT::/complaints::saveComplaints::" + complaint);

		return complaintRepository.save(complaint);
	}
	
	
	@PostMapping("/complaint-comments")
	@PreAuthorize("permitAll()")
	public ComplaintComment addCommentOnComplaint(@Valid @RequestBody ComplaintComment complaintComment) throws Exception {
		 logger.info("IN::POST::/complaintComment::saveComplaintComment::" + complaintComment);
	      	complaintComment = complaintCommentsRepository.save(complaintComment);
					logger.info("OUT::POST::/complaintComment::saveComplaintComment::" + complaintComment);
	            return complaintComment;
		}
	@DeleteMapping("/complaints/{id}")
	@PreAuthorize("permitAll()")
	public Map<String, Boolean> deleteComplaint(@PathVariable(value = "id") Long complaintsId)
			throws ResourceNotFoundException {
		 logger.info("IN::POST::/complaints::deleteComplaints::" + complaintsId);
		Complaint complaint = complaintRepository.findById(complaintsId).orElseThrow(
				() -> new ResourceNotFoundException("Complaints not found for this id :: " + complaintsId));

		complaintRepository.delete(complaint);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		logger.info("OUT::POST::/complaints::deleteComplaints::" + complaintsId);
		return response;
	}
	
	
	

	/*
	 * @GetMapping("/complaints/complainthistory/{id}")
	 * 
	 * @PreAuthorize("permitAll()") // @PreAuthorize("permitAll()") public
	 * List<ComplaintHistoryUtil> getComplaintHistoryDetail(@PathVariable(value =
	 * "id") Long userId) throws ResourceNotFoundException { List<Object>
	 * complainthistoryInfo =
	 * complaintRepository.getDataForcomplaintHistory(userId);
	 * List<ComplaintHistoryUtil> getComplaintHistory = new ArrayList<>();
	 * 
	 * for (Iterator<Object> iterator = complainthistoryInfo.iterator();
	 * iterator.hasNext();) { Object[] object = (Object[]) iterator.next();
	 * ComplaintHistoryUtil complainthistoryUtil = new ComplaintHistoryUtil();
	 * 
	 * for (int i = 0; i < object.length; i++) { switch (i) { case 0:
	 * complainthistoryUtil.setDescription((String) object[0]); break; case 1:
	 * complainthistoryUtil.setCreatedAt((Date) object[i]); break; default: break; }
	 * } getComplaintHistory.add(complainthistoryUtil); }
	 * 
	 * return (getComplaintHistory);
	 * 
	 * }
	 * 
	 */}