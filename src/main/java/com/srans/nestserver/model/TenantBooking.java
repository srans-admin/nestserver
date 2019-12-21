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
	private Long bookingid;

	@Column
	private Long tenantId;

	@Column
	private String tenantName;

	@Column
	private Date allotedFrom;

	@Column
	private Long roomid;

	@Column
	private String roomName;

	@Column
	private String createdBy;

	@Column
	private String modifiedBy;

	@Column
	private Character active;

	@Column
	private Date allotedTill;

	@Column
	private Long roomBedId;

	@Column
	private String bedPosition;

	@Column
	private String roomType;

	@Column
	private String floorName;

	@Column
	private Long floorId;
	
	@Column
	private Long roomRent;

	@Transient
	private List<Payment> payment;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public TenantBooking() {
		super();
		this.tenantId = 0L;
		this.tenantName = "";
		this.allotedFrom = null;
		this.active=0;
		this.allotedTill=null;
		this.bedPosition="";
		this.floorName="";
		this.roomBedId=0L;
		this.tenantName="";
		this.payment = new ArrayList();

	}

	

	public TenantBooking(Long bookingid, Long tenantId, String tenantName, Date allotedFrom, Long roomid,
			String roomName, String createdBy, String modifiedBy, Character active, Date allotedTill, Long roomBedId,
			String bedPosition, String roomType, String floorName, Long floorId, Long roomRent, List<Payment> payment) {
		super();
		this.bookingid = bookingid;
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.allotedFrom = allotedFrom;
		this.roomid = roomid;
		this.roomName = roomName;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.active = active;
		this.allotedTill = allotedTill;
		this.roomBedId = roomBedId;
		this.bedPosition = bedPosition;
		this.roomType = roomType;
		this.floorName = floorName;
		this.floorId = floorId;
		this.roomRent = roomRent;
		this.payment = payment;
	}



	public Long getBookingid() {
		return bookingid;
	}

	public void setBookingid(Long bookingid) {
		this.bookingid = bookingid;
	}
	
	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getTenantName() {
		return tenantName;
	}

	public void setTenantName(String tenantName) {
		this.tenantName = tenantName;
	}

	public Date getAllotedFrom() {
		return allotedFrom;
	}

	public void setAllotedFrom(Date allotedFrom) {
		this.allotedFrom = allotedFrom;
	}

	public Long getRoomid() {
		return roomid;
	}

	public void setRoomid(Long roomid) {
		this.roomid = roomid;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public Character getActive() {
		return active;
	}

	public void setActive(Character active) {
		this.active = active;
	}

	public Date getAllotedTill() {
		return allotedTill;
	}

	public void setAllotedTill(Date allotedTill) {
		this.allotedTill = allotedTill;
	}

	public Long getRoomBedId() {
		return roomBedId;
	}

	public void setRoomBedId(Long roomBedId) {
		this.roomBedId = roomBedId;
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

	public Long getFloorId() {
		return floorId;
	}

	public void setFloorId(Long floorId) {
		this.floorId = floorId;
	}

	public List<Payment> getPayment() {
		return payment;
	}

	public void setPayment(List<Payment> payment) {
		this.payment = payment;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantBooking [bookingid=").append(bookingid).append(", tenantId=").append(tenantId)
				.append(", tenantName=").append(tenantName).append(", allotedFrom=").append(allotedFrom)
				.append(", roomid=").append(roomid).append(", roomName=").append(roomName).append(", createdBy=")
				.append(createdBy).append(", modifiedBy=").append(modifiedBy).append(", active=").append(active)
				.append(", allotedTill=").append(allotedTill).append(", roomBedId=").append(roomBedId)
				.append(", bedPosition=").append(bedPosition).append(", roomType=").append(roomType)
				.append(", floorName=").append(floorName).append(", floorId=").append(floorId).append(", roomRent=")
				.append(roomRent).append(", payment=").append(payment).append("]");
		return builder.toString();
	}

	

}