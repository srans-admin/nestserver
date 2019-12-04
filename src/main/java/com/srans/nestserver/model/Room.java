package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "room") 
public class Room {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	 @Column private Long hostelId;
	 @Column private Long floorId;
	@Column private String roomName; 
	@Column private String roomType; 
	@Column private int rent;
	
	/*@Transient
	@OneToMany(mappedBy = "room", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<Floor> hostelList = new ArrayList<>();
*/
	public Room() {
		super();
		// TODO Auto-generated constructor stub
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

	public int getRent() {
		return rent;
	}

	public void setRent(int rent) {
		this.rent = rent;
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
		builder.append(", rent=");
		builder.append(rent);
		builder.append("]");
		return builder.toString();
	}

	 
	 
	 

}