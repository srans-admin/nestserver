package com.srans.nestserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name = "room")

public class Room implements Serializable{

	/**
	 * @author Manish
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column
	private Long hostelId;
	@Column
	private Long floorId;
	@Column
	private String roomName;
	@Column
	private String roomType;
	@Column
	private int roomRent;

	@Transient
	private List<Bed> beds;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Room() {
		super();
		this.beds = new ArrayList();
	}

	public Room(Long id, Long hostelId, Long floorId, String roomName, String roomType, int roomRent, List<Bed> beds) {
		super();
		this.id = id;
		this.hostelId = hostelId;
		this.floorId = floorId;
		this.roomName = roomName;
		this.roomType = roomType;
		this.roomRent = roomRent;
		this.beds = beds;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(int roomRent) {
		this.roomRent = roomRent;
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

	public List<Bed> getBeds() {
		return beds;
	}

	public void setBeds(List<Bed> beds) {
		this.beds = beds;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Room [id=");
		builder.append(id);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", floorId=");
		builder.append(floorId);
		builder.append(", roomName=");
		builder.append(roomName);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", roomRent=");
		builder.append(roomRent);
		builder.append(", beds=");
		builder.append(beds);
		builder.append("]");
		return builder.toString();
	}

}