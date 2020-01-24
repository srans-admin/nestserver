package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "adminDetails")
public class SubAdminDetails extends AuditModel {

	/**
	 * @apiNote Model Class For Holding Input Data Of SubAdmin
	 * 
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Long assignHostelIds;

	@Column
	private Long subAdminId;

	@Column
	private String subAdminName;

	@Column
	private String ParentAdminName;

	@Column
	private Long parentAdminId;

	@Column
	private String assignHostelName;

	@Column
	private String role;

	public SubAdminDetails(Long id, Long assignHostelIds, Long subAdminId, String subAdminName, String parentAdminName,
			Long parentAdminId, String assignHostelName, String role) {
		super();
		this.id = id;
		this.assignHostelIds = assignHostelIds;
		this.subAdminId = subAdminId;
		this.subAdminName = subAdminName;
		ParentAdminName = parentAdminName;
		this.parentAdminId = parentAdminId;
		this.assignHostelName = assignHostelName;
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getAssignHostelIds() {
		return assignHostelIds;
	}

	public void setAssignHostelIds(Long assignHostelIds) {
		this.assignHostelIds = assignHostelIds;
	}

	public Long getSubAdminId() {
		return subAdminId;
	}

	public void setSubAdminId(Long subAdminId) {
		this.subAdminId = subAdminId;
	}

	public String getSubAdminName() {
		return subAdminName;
	}

	public void setSubAdminName(String subAdminName) {
		this.subAdminName = subAdminName;
	}

	public String getParentAdminName() {
		return ParentAdminName;
	}

	public void setParentAdminName(String parentAdminName) {
		ParentAdminName = parentAdminName;
	}

	public Long getParentAdminId() {
		return parentAdminId;
	}

	public void setParentAdminId(Long parentAdminId) {
		this.parentAdminId = parentAdminId;
	}

	public String getAssignHostelName() {
		return assignHostelName;
	}

	public void setAssignHostelName(String assignHostelName) {
		this.assignHostelName = assignHostelName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminDetails [id=").append(id).append(", assignHostelIds=").append(assignHostelIds)
				.append(", subAdminId=").append(subAdminId).append(", subAdminName=").append(subAdminName)
				.append(", ParentAdminName=").append(ParentAdminName).append(", parentAdminId=").append(parentAdminId)
				.append(", assignHostelName=").append(assignHostelName).append(", role=").append(role).append("]");
		return builder.toString();
	}

}
