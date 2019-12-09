package com.srans.nestserver.model;

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
@Table(name = "hostel")
public class Hostel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String hostelName;

	@Column
	private String hostelAddress;

	@Column
	private String hostelType;

	@Column
	private int numOfFloors;

	@Transient
	private List<Floor> floors;

	public Hostel() {
		super();
		this.hostelName = "";
		this.hostelAddress = "";
		this.hostelType = "";
		this.floors = new ArrayList<>();
	}

	

	public Hostel(Long id, String hostelName, String hostelAddress, String hostelType, int numOfFloors,
			List<Floor> floors) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.hostelAddress = hostelAddress;
		this.hostelType = hostelType;
		this.numOfFloors = numOfFloors;
		this.floors = floors;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public String getHostelAddress() {
		return hostelAddress;
	}

	public void setHostelAddress(String hostelAddress) {
		this.hostelAddress = hostelAddress;
	}

	public String getHostelType() {
		return hostelType;
	}

	public void setHostelType(String hostelType) {
		this.hostelType = hostelType;
	}

	public List<Floor> getfloors() {
		return floors;
	}

	public void setfloors(List<Floor> floors) {
		this.floors = floors;
	}

	public int getNumOfFloors() {
		return numOfFloors;
	}

	public void setNumOfFloors(int numOfFloors) {
		this.numOfFloors = numOfFloors;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Hostel [id=");
		builder.append(id);
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", hostelAddress=");
		builder.append(hostelAddress);
		builder.append(", hostelType=");
		builder.append(hostelType);
		builder.append(", numOfFloors=");
		builder.append(numOfFloors);
		builder.append(", floors=");
		builder.append(floors);
		builder.append("]");
		return builder.toString();
	}

}