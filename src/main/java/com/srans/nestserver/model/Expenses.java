package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Expenses")
public class Expenses implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String TypeOfExpenses;
	private long cost;

	public Expenses() {
		
	}
	public Expenses(long id, String typeOfExpenses, long cost) {
		super();
		this.id = id;
		TypeOfExpenses = typeOfExpenses;
		this.cost = cost;
	}


@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}

@Column(name = "TypeOfExpenses")
public String getTypeOfExpenses() {
	return TypeOfExpenses;
}
public void setTypeOfExpenses(String TypeOfExpenses) {
	this.TypeOfExpenses =TypeOfExpenses;
}

@Column(name = " cost")
public long getcost() {
	return  cost;
}
public void setcost(long  cost) {
	this. cost =  cost;
}
@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Expenses [id=");
	builder.append(id);
	builder.append(", TypeOfExpenses=");
	builder.append(TypeOfExpenses);
	builder.append(", cost=");
	builder.append(cost);
	builder.append("]");
	return builder.toString();
}

}

