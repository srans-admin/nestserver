package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "adminDetails")
public class AdminDetails extends AuditModel {

	/**
	 * @author manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long hostelId;

	@Column
	private Long adminId;

	@Column
	private String assignBy;

	@Column
	private Long parentAdminId;

	public AdminDetails() {
		super();
	}

	public AdminDetails(Long id, Long hostelId, Long adminId, String assignBy, Long parentAdminId) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.adminId = adminId;
		this.assignBy = assignBy;
		this.parentAdminId = parentAdminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAssignBy() {
		return assignBy;
	}

	public void setAssignBy(String assignBy) {
		this.assignBy = assignBy;
	}

	public Long getParentAdminId() {
		return parentAdminId;
	}

	public void setParentAdminId(Long parentAdminId) {
		this.parentAdminId = parentAdminId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminDetails [id=").append(id).append(", hostelId=").append(hostelId).append(", adminId=")
				.append(adminId).append(", assignBy=").append(assignBy).append(", parentAdminId=").append(parentAdminId)
				.append("]");
		return builder.toString();
	}

}
