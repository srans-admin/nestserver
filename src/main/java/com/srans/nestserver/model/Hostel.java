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
@Table(name = "hostel")
public class Hostel implements Serializable {
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
	private Integer numOfFloors;

	@Column
	private boolean tv;
	@Column
	private boolean fridge;
	@Column
	private boolean ac;
	@Column
	private boolean mineralWater;
	@Column
	private boolean parking;
	@Column
	private boolean gym;

	@Column
	private long adminId;

	public Hostel(Long id, String hostelName, String hostelAddress, String hostelType, Integer numOfFloors, boolean tv,
			boolean fridge, boolean ac, boolean mineralWater, boolean parking, boolean gym, long adminId,
			List<Floor> floors) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.hostelAddress = hostelAddress;
		this.hostelType = hostelType;
		this.numOfFloors = numOfFloors;
		this.tv = tv;
		this.fridge = fridge;
		this.ac = ac;
		this.mineralWater = mineralWater;
		this.parking = parking;
		this.gym = gym;
		this.adminId = adminId;
		this.floors = floors;
	}

	public long getAdminId() {
		return adminId;
	}

	public void setAdminId(long adminId) {
		this.adminId = adminId;
	}

	@Transient
	private List<Floor> floors;

	public Hostel() {
		super();
		this.hostelName = "";
		this.hostelAddress = "";
		this.hostelType = "";
		this.numOfFloors = 0;
		this.floors = new ArrayList<>();
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

	public Integer getNumOfFloors() {
		return numOfFloors;
	}

	public void setNumOfFloors(Integer numOfFloors) {
		this.numOfFloors = numOfFloors;
	}

	public boolean isTv() {
		return tv;
	}

	public void setTv(boolean tv) {
		this.tv = tv;
	}

	public boolean isFridge() {
		return fridge;
	}

	public void setFridge(boolean fridge) {
		this.fridge = fridge;
	}

	public boolean isAc() {
		return ac;
	}

	public void setAc(boolean ac) {
		this.ac = ac;
	}

	public boolean isMineralWater() {
		return mineralWater;
	}

	public void setMineralWater(boolean mineralWater) {
		this.mineralWater = mineralWater;
	}

	public boolean isParking() {
		return parking;
	}

	public void setParking(boolean parking) {
		this.parking = parking;
	}

	public boolean isGym() {
		return gym;
	}

	public void setGym(boolean gym) {
		this.gym = gym;
	}

	public List<Floor> getFloors() {
		return floors;
	}

	public void setFloors(List<Floor> floors) {
		this.floors = floors;
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
		builder.append(", tv=");
		builder.append(tv);
		builder.append(", fridge=");
		builder.append(fridge);
		builder.append(", ac=");
		builder.append(ac);
		builder.append(", mineralWater=");
		builder.append(mineralWater);
		builder.append(", parking=");
		builder.append(parking);
		builder.append(", gym=");
		builder.append(gym);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", floors=");
		builder.append(floors);
		builder.append("]");
		return builder.toString();
	}
}
	