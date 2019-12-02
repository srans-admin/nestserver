package com.srans.nestserver.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "hostel")
public class Hostel extends AuditModel {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 100)
	private String hostelName;

	@Size(max = 250)
	private String hostelAddress;

	@Size(max=200)
	private String hostelType;
	
	List<Floor> floors;
	
	 
  
	public Hostel() { 
	}

	public Hostel(Long id, @Size(max = 100) String hostelName, @Size(max = 250) String hostelAddress,
			@Size(max = 200) String hostelType) {
		super();
		this.id = id;
		this.hostelName = hostelName;
		this.hostelAddress = hostelAddress;
		this.hostelType = hostelType;
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
		builder.append("]");
		return builder.toString();
	}
	
	

}