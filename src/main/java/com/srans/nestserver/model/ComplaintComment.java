
package com.srans.nestserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@SuppressWarnings("serial")
@Entity

@Table(name = "complaintscomments")
public class ComplaintComment implements Serializable {

	@Id

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String resolutionStatus;

	@Column
	private Date resolutionDate;

	@Column
	private String comments;

	@Column
	private Long complaintId;

	@Column
	private Long updatedByUserId;

	

	public ComplaintComment() {
		super();

	}



	public ComplaintComment(Long id, String resolutionStatus, Date resolutionDate, String comments, Long complaintId,
			Long updatedByUserId) {
		super();
		this.id = id;
		this.resolutionStatus = resolutionStatus;
		this.resolutionDate = resolutionDate;
		this.comments = comments;
		this.complaintId = complaintId;
		this.updatedByUserId = updatedByUserId;
	}



	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getResolutionStatus() {
		return resolutionStatus;
	}



	public void setResolutionStatus(String resolutionStatus) {
		this.resolutionStatus = resolutionStatus;
	}



	public Date getResolutionDate() {
		return resolutionDate;
	}



	public void setResolutionDate(Date resolutionDate) {
		this.resolutionDate = resolutionDate;
	}



	public String getComments() {
		return comments;
	}



	public void setComments(String comments) {
		this.comments = comments;
	}



	public Long getComplaintId() {
		return complaintId;
	}



	public void setComplaintId(Long complaintId) {
		this.complaintId = complaintId;
	}



	public Long getUpdatedByUserId() {
		return updatedByUserId;
	}



	public void setUpdatedByUserId(Long updatedByUserId) {
		this.updatedByUserId = updatedByUserId;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplaintComment [id=");
		builder.append(id);
		builder.append(", resolutionStatus=");
		builder.append(resolutionStatus);
		builder.append(", resolutionDate=");
		builder.append(resolutionDate);
		builder.append(", comments=");
		builder.append(comments);
		builder.append(", complaintId=");
		builder.append(complaintId);
		builder.append(", updatedByUserId=");
		builder.append(updatedByUserId);
		builder.append("]");
		return builder.toString();
	}
}