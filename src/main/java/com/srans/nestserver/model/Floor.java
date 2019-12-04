package com.srans.nestserver.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "floor")
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column private Long id; 
	@Column private String floorName; 
	@Column private String description;
	
	@Transient
	private List<Room> rooms;

	/*@Transient
	@ManyToOne
	@JoinColumn(name = "floor_id", referencedColumnName = "id")
	private Floor floor;

	@Transient
	@ManyToOne
	@JoinColumn(name = "hostel_id", referencedColumnName = "id")
	private Hostel hostel;*/

	public Floor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFloorName() {
		return floorName;
	}

	public void setFloorName(String floorName) {
		this.floorName = floorName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Room> getRooms() {
		return rooms;
	}

	public void setRooms(List<Room> rooms) {
		this.rooms = rooms;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Floor [id=");
		builder.append(id);
		builder.append(", floorName=");
		builder.append(floorName);
		builder.append(", description=");
		builder.append(description);
		builder.append(", rooms=");
		builder.append(rooms);
		builder.append("]");
		return builder.toString();
	}

	 
	 
}