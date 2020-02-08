package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "expenseType")
public class AmenityType extends AuditModel {

	/**
	 * @author manish
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long amenityTypeId ;

	@Column
	private String amenityTypeName;
	
	@Column
	private String amenityTypeDesc;
	
	@Column
	private String created_by;
	
	

	public AmenityType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public AmenityType(Long amenityTypeId, String amenityTypeName, String amenityTypeDesc, String created_by) {
		super();
		this.amenityTypeId = amenityTypeId;
		this.amenityTypeName = amenityTypeName;
		this.amenityTypeDesc = amenityTypeDesc;
		this.created_by = created_by;
	}

	public Long getAmenityTypeId() {
		return amenityTypeId;
	}

	public void setAmenityTypeId(Long amenityTypeId) {
		this.amenityTypeId = amenityTypeId;
	}

	public String getAmenityTypeName() {
		return amenityTypeName;
	}

	public void setAmenityTypeName(String amenityTypeName) {
		this.amenityTypeName = amenityTypeName;
	}

	public String getAmenityTypeDesc() {
		return amenityTypeDesc;
	}

	public void setAmenityTypeDesc(String amenityTypeDesc) {
		this.amenityTypeDesc = amenityTypeDesc;
	}

	public String getCreated_by() {
		return created_by;
	}

	public void setCreated_by(String created_by) {
		this.created_by = created_by;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AmenityType [amenityTypeId=");
		builder.append(amenityTypeId);
		builder.append(", amenityTypeName=");
		builder.append(amenityTypeName);
		builder.append(", amenityTypeDesc=");
		builder.append(amenityTypeDesc);
		builder.append(", created_by=");
		builder.append(created_by);
		builder.append("]");
		return builder.toString();
	}

	
}

	