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

	@Column(name = "description")
	private String description;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "floor_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Floors floor;

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Floors getFloor() {
		return floor;
	}

	public void setFloor(Floors floor) {
		this.floor = floor;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rooms [id=").append(id).append(", facilities=").append(facilities).append(", description=")
				.append(description).append(", floor=").append(floor).append("]");
		return builder.toString();
	}

}