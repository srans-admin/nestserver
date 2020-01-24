/**
 * 
 */
package com.srans.nestserver.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author user
 *
 */
public class ComplaintWrapper {
	
	private Complaint complaint;
	private List<ComplaintComment> complaintComments;
	
	public ComplaintWrapper(){
		this.complaint = new Complaint();
		this.complaintComments = new ArrayList<>();
	}
	
	public Complaint getComplaint() {
		return complaint;
	}
	public void setComplaint(Complaint complaint) {
		this.complaint = complaint;
	}
	public List<ComplaintComment> getComplaintComments() {
		return complaintComments;
	}
	public void setComplaintComments(List<ComplaintComment> complaintComments) {
		this.complaintComments = complaintComments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplaintWapper [complaint=");
		builder.append(complaint);
		builder.append(", complaintComments=");
		builder.append(complaintComments);
		builder.append("]");
		return builder.toString();
	} 
	
	
	
	

}
