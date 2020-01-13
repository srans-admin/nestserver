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
	private Long tenantId;

	@Column
	private Long bookingid;

	@Column
	private String name;

	@Column
	private String ammountType;

	@Column
	private String roomName;

	@Column
	private String roomType;

	@Column
	private Long ammount;

	@Column
	private Long discount;

	@Column
	private String paymentThrough;

	@Column
	private Long transactionId;

	@Column
	private String bankName;

	@Column
	private String date;

	@Column
	private Long adminId;
	@Column
	private Long depositAmount;
	@Column
	private Long discountAmount;

	public Payment() {
		super();

	}

	public Payment(Long id, Long tenantId, Long bookingid, String name, String ammountType, String roomName,
			String roomType, Long ammount, Long discount, String paymentThrough, Long transactionId, String bankName,
			String date, Long adminId, Long depositAmount, Long discountAmount) {
		super();
		this.id = id;
		this.tenantId = tenantId;
		this.bookingid = bookingid;
		this.name = name;
		this.ammountType = ammountType;
		this.roomName = roomName;
		this.roomType = roomType;
		this.ammount = ammount;
		this.discount = discount;
		this.paymentThrough = paymentThrough;
		this.transactionId = transactionId;
		this.bankName = bankName;
		this.date = date;
		this.adminId = adminId;
		this.depositAmount = depositAmount;
		this.discountAmount = discountAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getBookingid() {
		return bookingid;
	}

	public void setBookingid(Long bookingid) {
		this.bookingid = bookingid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAmmountType() {
		return ammountType;
	}

	public void setAmmountType(String ammountType) {
		this.ammountType = ammountType;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
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

	public Long getAmmount() {
		return ammount;
	}

	public void setAmmount(Long ammount) {
		this.ammount = ammount;
	}

	public Long getDiscount() {
		return discount;
	}

	public void setDiscount(Long discount) {
		this.discount = discount;
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

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Payment [id=").append(id).append(", tenantId=").append(tenantId).append(", bookingid=")
				.append(bookingid).append(", name=").append(name).append(", ammountType=").append(ammountType)
				.append(", roomName=").append(roomName).append(", roomType=").append(roomType).append(", ammount=")
				.append(ammount).append(", discount=").append(discount).append(", paymentThrough=")
				.append(paymentThrough).append(", transactionId=").append(transactionId).append(", bankName=")
				.append(bankName).append(", date=").append(date).append(", adminId=").append(adminId)
				.append(", depositAmount=").append(depositAmount).append(", discountAmount=").append(discountAmount)
				.append("]");
		return builder.toString();
	}

}
