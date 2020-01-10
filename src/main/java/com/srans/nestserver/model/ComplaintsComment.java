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
    @Column
    private Long hostelownerid;
	@Column
	private Long complaintsid;
	
	@Transient
	private Complaints complaints;
	@Transient
	private Hostel hostel;
	
	public ComplaintsComment() {
		super();
		
	}

	public ComplaintsComment(Long id, String resolutionstatus, String resolutiondate, String comments,
			Long hostelownerid, Long complaintsid, Complaints complaints, Hostel hostel) {
		super();
		this.id = id;
		this.resolutionstatus = resolutionstatus;
		this.resolutiondate = resolutiondate;
		this.comments = comments;
		this.hostelownerid = hostelownerid;
		this.complaintsid = complaintsid;
		this.complaints = complaints;
		this.hostel = hostel;
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

	public Long getHostelownerid() {
		return hostelownerid;
	}

	public void setHostelownerid(Long hostelownerid) {
		this.hostelownerid = hostelownerid;
	}

	public Long getComplaintsid() {
		return complaintsid;
	}

	public void setComplaintsid(Long complaintsid) {
		this.complaintsid = complaintsid;
	}

	public Complaints getComplaints() {
		return complaints;
	}

	public void setComplaints(Complaints complaints) {
		this.complaints = complaints;
	}

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplaintsComment [id=").append(id).append(", resolutionstatus=").append(resolutionstatus)
				.append(", resolutiondate=").append(resolutiondate).append(", comments=").append(comments)
				.append(", hostelownerid=").append(hostelownerid).append(", complaintsid=").append(complaintsid)
				.append(", complaints=").append(complaints).append(", hostel=").append(hostel).append("]");
		return builder.toString();
	}
}
	
	