package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Expense")
public class Expense extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn
	private Hostel hostel;

	@Column
	private String typeOfExpenses;

	@Column
	private Long cost;

	public Expense(Long id, Hostel hostel, String typeOfExpenses, Long cost) {
		super();
		this.id = id;
		this.hostel = hostel;
		this.typeOfExpenses = typeOfExpenses;
		this.cost = cost;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTypeOfExpenses() {
		return typeOfExpenses;
	}

	public void setTypeOfExpenses(String typeOfExpenses) {
		this.typeOfExpenses = typeOfExpenses;
	}

	public Long getCost() {
		return cost;
	}

	public void setCost(Long cost) {
		this.cost = cost;
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
		builder.append("Expense [id=");
		builder.append(id);
		builder.append(", typeOfExpenses=");
		builder.append(typeOfExpenses);
		builder.append(", cost=");
		builder.append(cost);
		builder.append("]");
		return builder.toString();
	}

}
