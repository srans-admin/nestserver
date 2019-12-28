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
	private Long expenseType;

	@Column
	private Long amount; 

	public Expense() {
		super();
		
	}

	
	public Long getHostelId() {
		return hostelId;
	}


	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}


	public Expense(Long id, Long expenseType, Long amount) {
		super();
		this.id = id; 
		this.expenseType = expenseType;
		this.amount = amount; 
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

 

	public Long getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(Long expenseType) {
		this.expenseType = expenseType;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}
 

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		builder.append("]");
		return builder.toString();
	}

	 
 
}
	