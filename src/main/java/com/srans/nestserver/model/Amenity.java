package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "amenity")
public class Amenity extends AuditModel {

	/**
	 * @author user manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amenityId;

	@Column
	private Long hostelId;

	@Column
	private Long amenityTypeId;

	@Column
	private Long hostelFloorRoomId;

	@Column
	private String createdBy;

	public Amenity() {
		super();
	}

	public Amenity(Long amenityId, Long hostelId, Long amenityTypeId, Long hostelFloorRoomId, String createdBy) {
		super();
		this.amenityId = amenityId;
		this.hostelId = hostelId;
		this.amenityTypeId = amenityTypeId;
		this.hostelFloorRoomId = hostelFloorRoomId;
		this.createdBy = createdBy;
	}

	public Long getAmenityId() {
		return amenityId;
	}

	public void setAmenityId(Long amenityId) {
		this.amenityId = amenityId;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public Long getAmenityTypeId() {
		return amenityTypeId;
	}

	public void setAmenityTypeId(Long amenityTypeId) {
		this.amenityTypeId = amenityTypeId;
	}

	public Long getHostelFloorRoomId() {
		return hostelFloorRoomId;
	}

	public void setHostelFloorRoomId(Long hostelFloorRoomId) {
		this.hostelFloorRoomId = hostelFloorRoomId;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Amenity [amenityId=");
		builder.append(amenityId);
		builder.append(", hostelId=");
		builder.append(hostelId);
		builder.append(", amenityTypeId=");
		builder.append(amenityTypeId);
		builder.append(", hostelFloorRoomId=");
		builder.append(hostelFloorRoomId);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append("]");
		return builder.toString();
	}

}
