package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment extends AuditModel {

	/**
	 *
	 * @author likhit
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column
	private Long userId;
	@Column
	private Long bookingid;
	// @Column private String name;
	@Column
	private String paymentType;
	// @Column private String roomName;
	// @Column private String roomType;
	// @Column private Long roomRent;
	// @Column private Long roomBedId;
	@Column
	private String paymentThrough;
	@Column
	private Long transactionId;
	@Column
	private String bankName;
	@Column
	private String date;
	@Column
	private Long depositAmount;
	@Column
	private Long roomRent;
	@Column
	private Long discountAmount;
	@Column
	private Long amountToBePaid;

	@Column
	private Long adminId;

	public Payment() {
		super();

	}

	public Payment(Long id, Long userId, Long bookingid, String paymentType, String paymentThrough, Long transactionId,
			String bankName, String date, Long depositAmount, Long roomRent, Long discountAmount, Long amountToBePaid,
			Long adminId) {
		super();
		this.id = id;
		this.userId = userId;
		this.bookingid = bookingid;
		this.paymentType = paymentType;
		this.paymentThrough = paymentThrough;
		this.transactionId = transactionId;
		this.bankName = bankName;
		this.date = date;
		this.depositAmount = depositAmount;
		this.roomRent = roomRent;
		this.discountAmount = discountAmount;
		this.amountToBePaid = amountToBePaid;
		this.adminId = adminId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getBookingid() {
		return bookingid;
	}

	public void setBookingid(Long bookingid) {
		this.bookingid = bookingid;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getPaymentThrough() {
		return paymentThrough;
	}

	public void setPaymentThrough(String paymentThrough) {
		this.paymentThrough = paymentThrough;
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

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", bookingid=");
		builder.append(bookingid);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", paymentThrough=");
		builder.append(paymentThrough);
		builder.append(", transactionId=");
		builder.append(transactionId);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", date=");
		builder.append(date);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", amountToBePaid=");
		builder.append(amountToBePaid);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append("]");
		return builder.toString();
	}

}