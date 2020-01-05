package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "complaints")
public class Complaints implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String roleName;
	private String name;
	private String emailId;
	private long phoneNumber;
	private String description;
	
	public Complaints() {
		super();
		
	}

	public Complaints(long id, String roleName, String name, String emailId, long phoneNumber, String description) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.name = name;
		this.emailId = emailId;
		this.phoneNumber = phoneNumber;
		this.description = description;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getdescription() {
		return description;
	}

	public void setdescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Complaints [id=").append(id).append(", roleName=").append(roleName).append(", name=")
				.append(name).append(", emailId=").append(emailId).append(", phoneNumber=").append(phoneNumber)
				.append(", description=").append(description).append("]");
		return builder.toString();
	}

	
	
}