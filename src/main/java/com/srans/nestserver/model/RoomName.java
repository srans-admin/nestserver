package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class RoomName {

	@Column(name = "hostelId")
	Long hostelId;

	@Column(name = "floorId")
	Long floorId;

	public RoomName() {
		super();
	}

	public RoomName(Long hostelId, Long floorId) {
		super();
		this.hostelId = hostelId;
		this.floorId = floorId;
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

}
