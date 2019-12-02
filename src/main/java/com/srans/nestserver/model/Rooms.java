package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "rooms")
public class Rooms extends AuditModel {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "facilities")
	private String facilities;
	
	@Column(name="roomstatus")
	private String roomstatus;

	@Column(name="sharetype")
	private int sharetype;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "floor_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Floor floor;

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

	public String getRoomstatus() {
		return roomstatus;
	}

	public void setRoomstatus(String roomstatus) {
		this.roomstatus = roomstatus;
	}

	public int getSharetype() {
		return sharetype;
	}

	public void setSharetype(int sharetype) {
		this.sharetype = sharetype;
	}

	public Floor getFloor() {
		return floor;
	}

	public void setFloor(Floor floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rooms [id=").append(id).append(", facilities=").append(facilities).append(", roomstatus=")
				.append(roomstatus).append(", sharetype=").append(sharetype).append(", floor=").append(floor)
				.append("]");
		return builder.toString();
	}

	
	
}