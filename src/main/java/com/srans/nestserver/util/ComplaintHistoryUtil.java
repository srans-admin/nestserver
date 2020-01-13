package com.srans.nestserver.util;

import java.sql.Timestamp;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.srans.nestserver.model.AuditModel;

public class ComplaintHistoryUtil extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String description;
	private Timestamp createdAt;

	public ComplaintHistoryUtil() {
		super();
	}

	public ComplaintHistoryUtil(String description, Timestamp createdAt) {
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

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
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