package com.srans.nestserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "tenantbooking")

public class TenantBooking extends AuditModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long bookingId;

	@Column
	private Long tenantId;
	@Column
	private Long hostelId;
	@Column
	private Long floorId;
	@Column
	private Long roomId;
	@Column(unique = true)
	private Long roomBedId;
	@Column
	private Long roomRent;
	@Column
	private Date allotedFrom;
	@Column
	private Date allotedTill;
	@Column
	private String tenantName;
	@Column
	private String active;
	@Column
	private String bedPosition;
	@Column
	private String roomType;
	@Column
	private Long guestId;
	@Transient
	private String floorName;
	@Transient
	private String roomName;
	@Transient
	private String sharing;
	@Transient
	private Long depositAmount;
	@Transient
	private Long discountAmount;
	@Transient
	private Long amountToBePaid;
	@Transient
	private String paymentType;
	@Transient
	private String hostelName;
	@Transient
	private Payment payment;
	@Column
	private String createdBy;
	@Column
	private String modifiedBy;

	@Column
	private Long remainingDate;

	public TenantBooking() {
		super();
		this.tenantId = 0L;
		this.tenantName = "";
		this.allotedFrom = null;
		this.active = "Y";
		this.allotedTill = null;
		this.bedPosition = "";
		this.floorName = "";
		this.roomBedId = 0L;
		this.tenantName = "";
		this.guestId = 0L;
		this.payment = null;
		this.remainingDate = 0L;

	}

	public TenantBooking(Long bookingId, Long tenantId, Long hostelId, Long floorId, Long roomId, Long roomBedId,
			Long roomRent, Date allotedFrom, Date allotedTill, String tenantName, String active, String bedPosition,
			String roomType, Long guestId, String floorName, String roomName, String sharing, Long depositAmount,
			Long discountAmount, Long amountToBePaid, String paymentType, String hostelName, Payment payment,
			String createdBy, String modifiedBy, Long remainingDate) {
		super();
		this.bookingId = bookingId;
		this.tenantId = tenantId;
		this.hostelId = hostelId;
		this.floorId = floorId;
		this.roomId = roomId;
		this.roomBedId = roomBedId;
		this.roomRent = roomRent;
		this.allotedFrom = allotedFrom;
		this.allotedTill = allotedTill;
		this.tenantName = tenantName;
		this.active = active;
		this.bedPosition = bedPosition;
		this.roomType = roomType;
		this.guestId = guestId;
		this.floorName = floorName;
		this.roomName = roomName;
		this.sharing = sharing;
		this.depositAmount = depositAmount;
		this.discountAmount = discountAmount;
		this.amountToBePaid = amountToBePaid;
		this.paymentType = paymentType;
		this.hostelName = hostelName;
		this.payment = payment;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.remainingDate = remainingDate;
	}

	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	public Long getRoomBedId() {
		return roomBedId;
	}

	public void setRoomBedId(Long roomBedId) {
		this.roomBedId = roomBedId;
	}

	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}

	public Date getAllotedFrom() {
		return allotedFrom;
	}

	public void setAllotedFrom(Date allotedFrom) {
		this.allotedFrom = allotedFrom;
	}

	public Date getAllotedTill() {
		return allotedTill;
	}

	public void setAllotedTill(Date allotedTill) {
		this.allotedTill = allotedTill;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getBedPosition() {
		return bedPosition;
	}

	public void setBedPosition(String bedPosition) {
		this.bedPosition = bedPosition;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
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

	public String getSharing() {
		return sharing;
	}

	public void setSharing(String sharing) {
		this.sharing = sharing;
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

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getRemainingDate() {
		return remainingDate;
	}

	public void setRemainingDate(Long remainingDate) {
		this.remainingDate = remainingDate;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantBooking [bookingId=");
		builder.append(bookingId);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", floorId=");
		builder.append(floorId);
		builder.append(", roomId=");
		builder.append(roomId);
		builder.append(", roomBedId=");
		builder.append(roomBedId);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", allotedFrom=");
		builder.append(allotedFrom);
		builder.append(", allotedTill=");
		builder.append(allotedTill);
		builder.append(", tenantName=");
		builder.append(tenantName);
		builder.append(", active=");
		builder.append(active);
		builder.append(", bedPosition=");
		builder.append(bedPosition);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", guestId=");
		builder.append(guestId);
		builder.append(", floorName=");
		builder.append(floorName);
		builder.append(", roomName=");
		builder.append(roomName);
		builder.append(", sharing=");
		builder.append(sharing);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", discountAmount=");
		builder.append(discountAmount);
		builder.append(", amountToBePaid=");
		builder.append(amountToBePaid);
		builder.append(", paymentType=");
		builder.append(paymentType);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", modifiedBy=");
		builder.append(modifiedBy);
		builder.append(", remainingDate=");
		builder.append(remainingDate);
		builder.append("]");
		return builder.toString();
	}

}