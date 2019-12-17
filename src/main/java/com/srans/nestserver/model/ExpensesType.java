package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "expensetype")
public class ExpensesType extends AuditModel {

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long typeId;

	@Column
	private String expenseType;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hostel_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Hostel hostel;

	public ExpensesType() {
		super();

	}

	public ExpensesType(Long typeId, String expenseType, Hostel hostel) {
		super();
		this.typeId = typeId;
		this.expenseType = expenseType;
		this.hostel = hostel;
	}

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public String getExpenseType() {
		return expenseType;
	}

	public void setExpenseType(String expenseType) {
		this.expenseType = expenseType;
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
		builder.append("ExpensesType [typeId=");
		builder.append(typeId);
		builder.append(", expenseType=");
		builder.append(expenseType);
		builder.append(", hostel=");
		builder.append(hostel);
		builder.append("]");
		return builder.toString();
	}

}
