package com.srans.nestserver.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenantbooking")

public class TenantBooking {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long bookingid;

	@Column
	private Long tenantId;

	@Column
	private Date allotedFrom;

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

	public TenantBooking() {
		super();
		
	}

	public TenantBooking(Long bookingid, Long tenantId, Date allotedFrom, String createdBy, String modifiedBy,
			Character active, Date allotedTill, Long roomBedId, String position, String roomType, String floorName,
			Long floorId) {
		super();
		this.bookingid = bookingid;
		this.tenantId = tenantId;
		this.allotedFrom = allotedFrom;
		this.createdBy = createdBy;
		this.modifiedBy = modifiedBy;
		this.active = active;
		this.allotedTill = allotedTill;
		this.roomBedId = roomBedId;
		this.bedPosition = position;
		this.roomType = roomType;
		this.floorName = floorName;
		this.floorId = floorId;
	}

	public Long getBookingid() {
		return bookingid;
	}

	public void setBookingid(Long bookingid) {
		this.bookingid = bookingid;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public Date getAllotedFrom() {
		return allotedFrom;
	}

	public void setAllotedFrom(Date allotedFrom) {
		this.allotedFrom = allotedFrom;
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

	public String getPosition() {
		return bedPosition;
	}

	public void setPosition(String position) {
		this.bedPosition = position;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantBooking [bookingid=");
		builder.append(bookingid);
		builder.append(", tenantId=");
		builder.append(tenantId);
		builder.append(", allotedFrom=");
		builder.append(allotedFrom);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", modifiedBy=");
		builder.append(modifiedBy);
		builder.append(", active=");
		builder.append(active);
		builder.append(", allotedTill=");
		builder.append(allotedTill);
		builder.append(", roomBedId=");
		builder.append(roomBedId);
		builder.append(", position=");
		builder.append(bedPosition);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", floorName=");
		builder.append(floorName);
		builder.append(", floorId=");
		builder.append(floorId);
		builder.append("]");
		return builder.toString();
	}

}