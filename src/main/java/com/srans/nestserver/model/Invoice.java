package com.srans.nestserver.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Invoices")

public class Invoice  implements Serializable {
	private static final long serialVersionUID = 1L;
	private long id;
	private String name;
	private String paymentType;
	private long amount;
	
	public Invoice() {
		
	}

	public Invoice(long id, String name, String paymentType, long amount) {
		
		this.id = id;
		this.name = name;
		this.paymentType = paymentType;
		this.amount = amount;
	}
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
       public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	@Column(name = "Name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	@Column(name = "paymentType")
	public String getPaymentType() {
		return paymentType;
	}

	public void setpaymentType(String paymentType) {
		this.paymentType = paymentType;
	}
	@Column(name = "amount")
	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Invoices [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", amount=");
		builder.append(amount);
		builder.append("]");
		return builder.toString();
	}

	


}
