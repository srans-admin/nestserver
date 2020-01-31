package com.srans.nestserver.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vacation")
public class Vacation extends AuditModel implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private Long tenantId;

	@Column
	private Date date;

	@Column
	private Character approvedStatus;

	@Column
	private String comment;

	@Column
	private Long refundAmount;

	
	public Vacation() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Vacation(Long id, Long tenantId, Date date, Character approvedStatus, String comment, Long refundAmount) {
		super();
		this.id = id;
		this.tenantId = tenantId;
		this.date = date;
		this.approvedStatus = approvedStatus;
		this.comment = comment;
		this.refundAmount = refundAmount;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Character getApprovedStatus() {
		return approvedStatus;
	}

	public void setApprovedStatus(Character approvedStatus) {
		this.approvedStatus = approvedStatus;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Long getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Long refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vacation [id=").append(id).append(", tenantId=").append(tenantId).append(", date=").append(date)
				.append(", approvedStatus=").append(approvedStatus).append(", comment=").append(comment)
				.append(", refundAmount=").append(refundAmount).append("]");
		return builder.toString();
	}

}