package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role {

	private long id;
	private String roleName;
	private String Name;
	private String emailId;
	
	public Role() {
		
	}
	
	public Role(String roleName, String Name, String emailId) {
		this.roleName = roleName;
		this.Name = Name;
		this.emailId = emailId;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	@Column(name = "roleName", nullable = false)
	public String getFirstName() {
		return roleName;
	}
	public void setFirstName(String firstName) {
		this.roleName = roleName;
	}
	
	@Column(name = "Name", nullable = false)
	public String getLastName() {
		return Name;
	}
	public void setLastName(String lastName) {
		this.Name = lastName;
	}
	
	@Column(name = "email_address", nullable = false)
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		return "Hostel [id=" + id + ", roleName=" + roleName + ", Name=" + Name + ", emailId=" + emailId
				+ "]";
	}
	
}
