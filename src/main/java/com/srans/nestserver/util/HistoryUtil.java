package com.srans.nestserver.util;

import java.sql.Date;
import java.sql.Timestamp;

public class HistoryUtil {

	private Long amount;
	private Long roomRent;
	private String paymentThrough;
	private Date paidDate;

	public HistoryUtil() {
		super();

	}

	public HistoryUtil(Long amount, Long roomRent, String paymentThrough, Date paidDate) {
		super();
		this.amount = amount;
		this.roomRent = roomRent;
		this.paymentThrough = paymentThrough;
		this.paidDate = paidDate;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}

	public String getPaymentThrough() {
		return paymentThrough;
	}

	public void setPaymentThrough(String paymentThrough) {
		this.paymentThrough = paymentThrough;
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
		builder.append("HistoryUtil [amount=");
		builder.append(amount);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", paymentThrough=");
		builder.append(paymentThrough);
		builder.append(", paidDate=");
		builder.append(paidDate);
		builder.append("]");
		return builder.toString();
	}

}