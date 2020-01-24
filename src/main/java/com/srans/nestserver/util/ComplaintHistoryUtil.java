package com.srans.nestserver.util;

import java.util.Date;

import com.srans.nestserver.model.AuditModel;

public class ComplaintHistoryUtil extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;
	private Date createdAt;

	public ComplaintHistoryUtil() {
		super();
	}

	public ComplaintHistoryUtil(String description, Date createdAt) {
		super();
		this.description = description;
		this.createdAt = createdAt;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ComplaintHistoryUtil [description=").append(description).append(", createdAt=")
				.append(createdAt).append("]");
		return builder.toString();
	}

}