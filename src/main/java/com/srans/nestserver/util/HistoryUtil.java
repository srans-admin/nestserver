package com.srans.nestserver.util;
import java.util.Date;

public class HistoryUtil {

	private Long amount;
	private String roomType;
	private String roomName;
	private Date createdAt;

	public HistoryUtil() {
		super();

	}

	public HistoryUtil(Long amount, String roomType, String roomName, Date createdAt) {
		super();
		this.amount = amount;
		this.roomType = roomType;
		this.roomName = roomName;
		this.createdAt = createdAt;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
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
		builder.append("HistoryUtil [amount=").append(amount).append(", roomType=").append(roomType)
				.append(", roomName=").append(roomName).append(", createdAt=").append(createdAt).append("]");
		return builder.toString();
	}

	

}