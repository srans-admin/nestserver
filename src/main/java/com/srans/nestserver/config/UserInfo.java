package com.srans.nestserver.config;

import java.util.Date;

public class UserInfo {

	private String hostelName;
	private String floorName;
	private String roomName;
	private Long bedNo;
	private Long roomRent;
	private Date joiningDate;
	private Long depositAmount;
	private Long discountAmount;
	private Long amountToBePaid;
	private String paymentType;
	private String sharingType;
	private String paymentThrough;

	public UserInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserInfo(String hostelName, String floorName, String roomName, Long bedNo, Long roomRent, Date joiningDate,
			Long depositAmount, Long discountAmount, Long amountToBePaid, String paymentType, String sharingType,
			String paymentThrough) {
		super();
		this.hostelName = hostelName;
		this.floorName = floorName;
		this.roomName = roomName;
		this.bedNo = bedNo;
		this.roomRent = roomRent;
		this.joiningDate = joiningDate;
		this.depositAmount = depositAmount;
		this.discountAmount = discountAmount;
		this.amountToBePaid = amountToBePaid;
		this.paymentType = paymentType;
		this.sharingType = sharingType;
		this.paymentThrough = paymentThrough;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Long getBedNo() {
		return bedNo;
	}

	public void setBedNo(Long bedNo) {
		this.bedNo = bedNo;
	}

	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}

	public Date getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(Date joiningDate) {
		this.joiningDate = joiningDate;
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

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public String getPaymentThrough() {
		return paymentThrough;
	}

	public void setPaymentThrough(String paymentThrough) {
		this.paymentThrough = paymentThrough;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserInfo [hostelName=");
		builder.append(hostelName);
		builder.append(", floorName=");
		builder.append(floorName);
		builder.append(", roomName=");
		builder.append(roomName);
		builder.append(", bedNo=");
		builder.append(bedNo);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", joiningDate=");
		builder.append(joiningDate);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", amountToBePaid=");
		builder.append(amountToBePaid);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", sharingType=");
		builder.append(sharingType);
		builder.append(", paymentThrough=");
		builder.append(paymentThrough);
		builder.append("]");
		return builder.toString();
	}

	

}
