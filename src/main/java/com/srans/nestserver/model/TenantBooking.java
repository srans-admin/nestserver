 package com.srans.nestserver.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@Column
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
	private String roomName;
	@Column
	private String active;
	@Column
	private String bedPosition;
	@Column
	private String roomType;
	@Column
	private String floorName;
	@Column
	private Long guestId;

	@Transient
	private Payment payment;

	@Column
	private String createdBy;
	@Column
	private String modifiedBy;

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

	}

	public TenantBooking(Long bookingId, Long tenantId, Long hostelId, Long floorId, Long roomId, Long roomBedId,
			Long roomRent, Date allotedFrom, Date allotedTill, String tenantName, String roomName, String active,
			String bedPosition, String roomType, String floorName, Long guestId, Payment payment, String createdBy,
			String modifiedBy) {
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
		this.roomName = roomName;
		this.active = active;
		this.bedPosition = bedPosition;
		this.roomType = roomType;
		this.floorName = floorName;
		this.guestId = guestId;
		this.payment = payment;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public Long getGuestId() {
		return guestId;
	}

	public void setGuestId(Long guestId) {
		this.guestId = guestId;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantBooking [bookingId=").append(bookingId).append(", tenantId=").append(tenantId)
				.append(", hostelId=").append(hostelId).append(", floorId=").append(floorId).append(", roomId=")
				.append(roomId).append(", roomBedId=").append(roomBedId).append(", roomRent=").append(roomRent)
				.append(", allotedFrom=").append(allotedFrom).append(", allotedTill=").append(allotedTill)
				.append(", tenantName=").append(tenantName).append(", roomName=").append(roomName).append(", active=")
				.append(active).append(", bedPosition=").append(bedPosition).append(", roomType=").append(roomType)
				.append(", floorName=").append(floorName).append(", guestId=").append(guestId).append(", payment=")
				.append(payment).append(", createdBy=").append(createdBy).append(", modifiedBy=").append(modifiedBy)
				.append("]");
		return builder.toString();
	}

}