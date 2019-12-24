package com.srans.nestserver.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

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

	@Column private Long tenantId;
	@Column private Long hostelId;
	@Column private Long floorId;
	@Column private Long roomId;
	@Column private Long roomBedId;
	@Column private Long roomRent;
	
	@Column private Date allotedFrom; 
	@Column private Date allotedTill;

	
	//TODO : Why do we need these??
	@Column private String tenantName;  
	 
	@Column private String roomName;  
	@Column private String active; 
	@Column private String bedPosition; 
	@Column private String roomType; 
	@Column private String floorName;

	
	
	

	//@Transient
	//private List<Payment> payment;
	
	@Column
	private String createdBy; 
	@Column
	private String modifiedBy; 
	 
	 
	public TenantBooking() {
		super();
		this.tenantId = 0L;
		this.tenantName = "";
		this.allotedFrom = null;
		this.active="0";
		this.allotedTill=null;
		this.bedPosition="";
		this.floorName="";
		this.roomBedId=0L;
		this.tenantName="";
		//this.payment = new ArrayList();

	}

	

	public TenantBooking(Long bookingId, Long tenantId, String tenantName, Date allotedFrom, Long roomId,
			String roomName, String createdBy, String modifiedBy, String active, Date allotedTill, Long roomBedId,
			String bedPosition, String roomType, String floorName, Long floorId, Long roomRent, List<Payment> payment) {
		super();
		this.bookingId = bookingId;
		this.tenantId = tenantId;
		this.tenantName = tenantName;
		this.allotedFrom = allotedFrom;
		this.roomId = roomId;
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
		//this.payment = payment;
	}



	public Long getHostelId() {
		return hostelId;
	}



	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}



	public Long getBookingId() {
		return bookingId;
	}

	public void setBookingId(Long bookingId) {
		this.bookingId = bookingId;
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

	public Long getRoomId() {
		return roomId;
	}

	public void setRoomId(Long roomId) {
		this.roomId = roomId;
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

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
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

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantBooking [bookingId=").append(bookingId).append(", tenantId=").append(tenantId)
				.append(", tenantName=").append(tenantName).append(", allotedFrom=").append(allotedFrom)
				.append(", roomId=").append(roomId).append(", roomName=").append(roomName).append(", createdBy=")
				.append(createdBy).append(", modifiedBy=").append(modifiedBy).append(", active=").append(active)
				.append(", allotedTill=").append(allotedTill).append(", roomBedId=").append(roomBedId)
				.append(", bedPosition=").append(bedPosition).append(", roomType=").append(roomType)
				.append(", floorName=").append(floorName).append(", floorId=").append(floorId).append(", roomRent=")
				.append(roomRent).append("]");
		return builder.toString();
	}

	

}