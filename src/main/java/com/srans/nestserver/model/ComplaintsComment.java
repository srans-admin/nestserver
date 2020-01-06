package com.srans.nestserver.model;

import java.io.Serializable;

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
public class ComplaintsComment implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private  String resolutionstatus;
	@Column
	private  String resolutiondate;
	@Column
	private String comments;
	@Transient
	private Complaints complaints;
	@Column
	private Long complaintsid;
	
	
	public ComplaintsComment() {
		super();
		
		
		
	}
	public ComplaintsComment(Long id, String resolutionstatus, String resolutiondate, String comments,
			Complaints complaints, Long complaintsid) {
		super();
		this.id = id;
		this.resolutionstatus = resolutionstatus;
		this.resolutiondate = resolutiondate;
		this.comments = comments;
		this.complaints = complaints;
		this.complaintsid = complaintsid;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getResolutionstatus() {
		return resolutionstatus;
	}
	public void setResolutionstatus(String resolutionstatus) {
		this.resolutionstatus = resolutionstatus;
	}
	public String getResolutiondate() {
		return resolutiondate;
	}
	public void setResolutiondate(String resolutiondate) {
		this.resolutiondate = resolutiondate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public Complaints getComplaints() {
		return complaints;
	}
	public void setComplaints(Complaints complaints) {
		this.complaints = complaints;
	}
	public Long getComplaintsid() {
		return complaintsid;
	}
	public void setComplaintsid(Long complaintsid) {
		this.complaintsid = complaintsid;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplaintsComment [id=").append(id).append(", resolutionstatus=").append(resolutionstatus)
				.append(", resolutiondate=").append(resolutiondate).append(", comments=").append(comments)
				.append(", complaints=").append(complaints).append(", complaintsid=").append(complaintsid).append("]");
		return builder.toString();
	}

}
