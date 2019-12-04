package com.srans.nestserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "floors")
public class Floor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String floor_name;

	private String description;

	@Transient
	@ManyToOne
	@JoinColumn(name = "floor_id", referencedColumnName = "id")
	private Floor floor;

	@Transient
	@ManyToOne
	@JoinColumn(name = "hostel_id", referencedColumnName = "id")
	private Hostels hostel;

	public Floor() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Floor(Long id, String floor_name, String description, Floor floor, Hostels hostel) {
		super();
		this.id = id;
		this.floor_name = floor_name;
		this.description = description;
		this.floor = floor;
		this.hostel = hostel;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFloor_name() {
		return floor_name;
	}

	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	public Hostels getHostel() {
		return hostel;
	}

	public void setHostel(Hostels hostel) {
		this.hostel = hostel;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Floor [id=");
		builder.append(id);
		builder.append(", floor_name=");
		builder.append(floor_name);
		builder.append(", description=");
		builder.append(description);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", hostel=");
		builder.append(hostel);
		builder.append("]");
		return builder.toString();
	}

}