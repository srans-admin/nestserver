package com.srans.nestserver.model;

import javax.persistence.Column; 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue; 
import javax.persistence.GenerationType;
import javax.persistence.Id; 
import javax.persistence.Table;

import org.hibernate.annotations.Formula;

@Entity

@Table(name = "invoice")

public class Invoice extends AuditModel { 
private static final long serialVersionUID = 1L; 
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
private long id; 

@Column private long userid; 
@Column private String invoiceDate;
@Column private String invoiceDescripition; 
@Column private String createdDateTime; 
@Column private String createdBy; 
@Column private String modifiedDate;
@Column private String modifiedBy; 
@Column private String active;
@Column private String name;
@Column private String paymentType; 
@Column private float roomRent;
@Column private float dueAmount;
@Column private float discountAmount;
@Formula("totalAmount=roomRent-dueAmount-discountAmount")
private float totalAmount;

public Invoice() {
	super();
	
	
}

public Invoice(long id, long userid, String invoiceDate, String invoiceDescripition, String createdDateTime,
		String createdBy, String modifiedDate, String modifiedBy, String active, String name, String paymentType,
		float roomRent, float dueAmount, float discountAmount, float totalAmount) {
	super();
	this.id = id;
	this.userid = userid;
	this.invoiceDate = invoiceDate;
	this.invoiceDescripition = invoiceDescripition;
	this.createdDateTime = createdDateTime;
	this.createdBy = createdBy;
	this.modifiedDate = modifiedDate;
	this.modifiedBy = modifiedBy;
	this.active = active;
	this.name = name;
	this.paymentType = paymentType;
	this.roomRent = roomRent;
	this.dueAmount = dueAmount;
	this.discountAmount = discountAmount;
	this.totalAmount = totalAmount;
}

public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}

public long getUserid() {
	return userid;
}

public void setUserid(long userid) {
	this.userid = userid;
}

public String getInvoiceDate() {
	return invoiceDate;
}

public void setInvoiceDate(String invoiceDate) {
	this.invoiceDate = invoiceDate;
}

public String getInvoiceDescripition() {
	return invoiceDescripition;
}

public void setInvoiceDescripition(String invoiceDescripition) {
	this.invoiceDescripition = invoiceDescripition;
}

public String getCreatedDateTime() {
	return createdDateTime;
}

public void setCreatedDateTime(String createdDateTime) {
	this.createdDateTime = createdDateTime;
}

public String getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(String createdBy) {
	this.createdBy = createdBy;
}

public String getModifiedDate() {
	return modifiedDate;
}

public void setModifiedDate(String modifiedDate) {
	this.modifiedDate = modifiedDate;
}

public String getModifiedBy() {
	return modifiedBy;
}

public void setModifiedBy(String modifiedBy) {
	this.modifiedBy = modifiedBy;
}

public String getActive() {
	return active;
}

public void setActive(String active) {
	this.active = active;
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public String getPaymentType() {
	return paymentType;
}

public void setPaymentType(String paymentType) {
	this.paymentType = paymentType;
}

public float getRoomRent() {
	return roomRent;
}

public void setRoomRent(float roomRent) {
	this.roomRent = roomRent;
}

public float getDueAmount() {
	return dueAmount;
}

public void setDueAmount(float dueAmount) {
	this.dueAmount = dueAmount;
}

public float getDiscountAmount() {
	return discountAmount;
}

public void setDiscountAmount(float discountAmount) {
	this.discountAmount = discountAmount;
}

public float getTotalAmount() {
	return totalAmount;
}

public void setTotalAmount(float totalAmount) {
	this.totalAmount = totalAmount;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Invoice [id=").append(id).append(", userid=").append(userid).append(", invoiceDate=")
			.append(invoiceDate).append(", invoiceDescripition=").append(invoiceDescripition)
			.append(", createdDateTime=").append(createdDateTime).append(", createdBy=").append(createdBy)
			.append(", modifiedDate=").append(modifiedDate).append(", modifiedBy=").append(modifiedBy)
			.append(", active=").append(active).append(", name=").append(name).append(", paymentType=")
			.append(paymentType).append(", roomRent=").append(roomRent).append(", dueAmount=").append(dueAmount)
			.append(", discountAmount=").append(discountAmount).append(", totalAmount=").append(totalAmount)
			.append("]");
	return builder.toString();
}

}
