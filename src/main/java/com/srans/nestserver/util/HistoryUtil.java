package com.srans.nestserver.util;

import java.util.Date;

public class HistoryUtil {

	private Long roomRent;
	private String roomType;
	private String roomName;
	private Date createdAt;

	public HistoryUtil() {
		super();

	}

	public HistoryUtil(Long roomRent, String roomType, String roomName, Date createdAt) {
		super();
		this.roomRent = roomRent;
		this.roomType = roomType;
		this.roomName = roomName;
		this.createdAt = createdAt;
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

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("HistoryUtil [roomRent=").append(roomRent).append(", roomType=").append(roomType)
				.append(", roomName=").append(roomName).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}

	 
		
	}
