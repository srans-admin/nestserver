package com.srans.nestserver.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "Rooms")

public class Rooms {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String facilities;

	private int sharetype;

	@Transient
	@OneToMany(mappedBy = "room", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
	private List<Floor> hostelList = new ArrayList<>();

	public Rooms() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Rooms(Long id, String facilities, int sharetype, List<Floor> hostelList) {
		super();
		this.id = id;
		this.facilities = facilities;
		this.sharetype = sharetype;
		this.hostelList = hostelList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFacilities() {
		return facilities;
	}

	public void setFacilities(String facilities) {
		this.facilities = facilities;
	}

	public int getSharetype() {
		return sharetype;
	}

	public void setSharetype(int sharetype) {
		this.sharetype = sharetype;
	}

	public List<Floor> getHostelList() {
		return hostelList;
	}

	public void setHostelList(List<Floor> hostelList) {
		this.hostelList = hostelList;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rooms [id=");
		builder.append(id);
		builder.append(", facilities=");
		builder.append(facilities);
		builder.append(", sharetype=");
		builder.append(sharetype);
		builder.append(", hostelList=");
		builder.append(hostelList);
		builder.append("]");
		return builder.toString();
	}

}