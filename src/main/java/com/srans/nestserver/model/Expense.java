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
@Table(name = "Expense")
public class Expense extends AuditModel {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "hostel_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Hostel hostel;

	@Column
	private String typeOfExpenses;

	@Column
	private Long ammount;

	public Expense() {
		super();

	}

	public Expense(Long id, Hostel hostel, String typeOfExpenses, Long ammount) {
		super();
		this.id = id;
		this.hostel = hostel;
		this.typeOfExpenses = typeOfExpenses;
		this.ammount = ammount;
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

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
	}

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Expense [id=");
		builder.append(id);
		builder.append(", typeOfExpenses=");
		builder.append(typeOfExpenses);
		builder.append(", cost=");
		builder.append(ammount);
		builder.append("]");
		return builder.toString();
	}

}
