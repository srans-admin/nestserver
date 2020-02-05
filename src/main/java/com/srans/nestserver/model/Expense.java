package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expense")
public class Expense extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

//@ManyToOne(fetch = FetchType.LAZY, optional = false)
//@JoinColumn(name = "hostelId", nullable = false)
//@OnDelete(action = OnDeleteAction.CASCADE)
//@JsonIgnore
//private Hostel hostel;

	@Column
	private Long hostelId;

	@Column
	private String expenseType;

	@Column
	private Long amount;

	@Column
	private String paidBy;

	@Column
	private String paidTo;
	@Column
	private String modeOfPayment;

	@Column
	private String transactionId;

	@Column
	private String comments;

	public Expense() {
		super();
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

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public String getPaidBy() {
		return paidBy;
	}

	public void setPaidBy(String paidBy) {
		this.paidBy = paidBy;
	}

	public String getPaidTo() {
		return paidTo;
	}

	public void setPaidTo(String paidTo) {
		this.paidTo = paidTo;
	}

	public String getModeOfPayment() {
		return modeOfPayment;
	}

	public void setModeOfPayment(String modeOfPayment) {
		this.modeOfPayment = modeOfPayment;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [id=");
		builder.append(id);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", expenseType=");
		builder.append(expenseType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append(", paidBy=");
		builder.append(paidBy);
		builder.append(", paidTo=");
		builder.append(paidTo);
		builder.append(", modeOfPayment=");
		builder.append(modeOfPayment);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", comments=");
		builder.append(comments);
		builder.append("]");
		return builder.toString();
	}
}