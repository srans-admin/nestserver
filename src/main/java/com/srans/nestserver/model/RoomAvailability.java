package com.srans.nestserver.model;

import java.util.ArrayList;
import java.util.List;

public class RoomAvailability {

	private String sharingType;
	private List<Object> data = new ArrayList<>();

	public RoomAvailability() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RoomAvailability(String sharingType, List<Object> data) {
		super();
		this.sharingType = sharingType;
		this.data = data;
	}

	public String getSharingType() {
		return sharingType;
	}

	public void setSharingType(String sharingType) {
		this.sharingType = sharingType;
	}

	public List<Object> getData() {
		return data;
	}

	public void setData(List<Object> data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoomAvailability [sharingType=");
		builder.append(sharingType);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}

}
