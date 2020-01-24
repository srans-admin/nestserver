package com.srans.nestserver.util;

import java.sql.Date;

public class AvailableBedsUtil {

	private Long roomBedId;
	
	private Date vacatedDate;
	
	private String bedPosition;
	
	private Long floorId;
	
	private Long roomId;
	
	private Long roomRent;
	
	private String roomType;

	public AvailableBedsUtil(Long roomBedId, Date vacatedDate, String bedPosition, Long floorId, Long roomId,
			Long roomRent, String roomType) {
		super();
		this.roomBedId = roomBedId;
		this.vacatedDate = vacatedDate;
		this.bedPosition = bedPosition;
		this.floorId = floorId;
		this.roomId = roomId;
		this.roomRent = roomRent;
		this.roomType = roomType;
	}

	public AvailableBedsUtil() {
		super();
	}

	public Long getRoomBedId() {
		return roomBedId;
	}

	public void setRoomBedId(Long roomBedId) {
		this.roomBedId = roomBedId;
	}

	public Date getVacatedDate() {
		return vacatedDate;
	}

	public void setVacatedDate(Date vacatedDate) {
		this.vacatedDate = vacatedDate;
	}

	public String getBedPosition() {
		return bedPosition;
	}

	public void setBedPosition(String bedPosition) {
		this.bedPosition = bedPosition;
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

	public Long getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(Long roomRent) {
		this.roomRent = roomRent;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AvailableBedsUtil [roomBedId=").append(roomBedId).append(", vacatedDate=").append(vacatedDate)
				.append(", bedPosition=").append(bedPosition).append(", floorId=").append(floorId).append(", roomId=")
				.append(roomId).append(", roomRent=").append(roomRent).append(", roomType=").append(roomType)
				.append("]");
		return builder.toString();
	}

}
