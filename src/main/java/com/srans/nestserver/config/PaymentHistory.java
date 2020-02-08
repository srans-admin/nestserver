package com.srans.nestserver.config;

import java.sql.Date;

public class PaymentHistory {

	private Long id;
	private String name;
	private String roomName;
	private String roomType;
	private Long transactionId;
	private String bankName;
	private String paymentThrough;
	private Long roomRent;
	private Long depositAmount;
	private Long discountAmount;
	private Long amountToBePaid;
    private Date paidDate;
	public PaymentHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public PaymentHistory(Long id, String name, String roomName, String roomType, Long transactionId, String bankName,
			String paymentThrough, Long roomRent, Long depositAmount, Long discountAmount, Long amountToBePaid,
			Date paidDate) {
		super();
		this.id = id;
		this.name = name;
		this.roomName = roomName;
		this.roomType = roomType;
		this.transactionId = transactionId;
		this.bankName = bankName;
		this.paymentThrough = paymentThrough;
		this.roomRent = roomRent;
		this.depositAmount = depositAmount;
		this.discountAmount = discountAmount;
		this.amountToBePaid = amountToBePaid;
		this.paidDate = paidDate;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRoomName() {
		return roomName;
	}
	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}
	public String getRoomType() {
		return roomType;
	}
	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}
	public Long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(Long transactionId) {
		this.transactionId = transactionId;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getPaymentThrough() {
		return paymentThrough;
	}
	public void setPaymentThrough(String paymentThrough) {
		this.paymentThrough = paymentThrough;
	}
	public Long getRoomRent() {
		return roomRent;
	}
	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}
	public Long getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
	}
	public Long getDiscountAmount() {
		return discountAmount;
	}
	public void setDiscountAmount(Long discountAmount) {
		this.discountAmount = discountAmount;
	}
	public Long getAmountToBePaid() {
		return amountToBePaid;
	}
	public void setAmountToBePaid(Long amountToBePaid) {
		this.amountToBePaid = amountToBePaid;
	}
	public Date getPaidDate() {
		return paidDate;
	}
	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("PaymentHistory [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", roomName=");
		builder.append(roomName);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", paymentThrough=");
		builder.append(paymentThrough);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", amountToBePaid=");
		builder.append(amountToBePaid);
		builder.append(", paidDate=");
		builder.append(paidDate);
		builder.append("]");
		return builder.toString();
	}
}
	