package com.srans.nestserver.model;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	private String  transactionId;
    
	@Column
    private  String comments;
	
	@Column
	private Date expenseDate;
	
	@Column
	private String hostelName;
	
	
	@Column
	private Long adminId;
	

	public Expense() {
		super();
	}


	public Expense(Long id, Long hostelId, String expenseType, Long amount, String paidBy, String paidTo,
			String modeOfPayment, String transactionId, String comments, Date expenseDate, String hostelName,
			Long adminId) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.expenseType = expenseType;
		this.amount = amount;
		this.paidBy = paidBy;
		this.paidTo = paidTo;
		this.modeOfPayment = modeOfPayment;
		this.transactionId = transactionId;
		this.comments = comments;
		this.expenseDate = expenseDate;
		this.hostelName = hostelName;
		this.adminId = adminId;
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


	public Date getExpenseDate() {
		return expenseDate;
	}


	public void setExpenseDate(Date expenseDate) {
		this.expenseDate = expenseDate;
	}


	public String getHostelName() {
		return hostelName;
	}


	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}


	public Long getAdminId() {
		return adminId;
	}


	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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
		builder.append(", expenseDate=");
		builder.append(expenseDate);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append("]");
		return builder.toString();
	}

}