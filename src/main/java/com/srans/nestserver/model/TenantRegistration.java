package com.srans.nestserver.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenatregistration")

public class TenantRegistration {


	private static final long serialVersionUID = 1L; 
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id; 
	
	@Column private String name;
	@Column private String phoneNumber;
	@Column private String emailId; 
	@Column private String password; 
	@Column private String confirmPassword;
	
	public TenantRegistration() {
		super();
		
	}

	public TenantRegistration(long id, String name, String phoneNumber, String emailId, String password,
			String confirmPassword) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("TenantRegistration [id=").append(id).append(", name=").append(name).append(", phoneNumber=")
				.append(phoneNumber).append(", emailId=").append(emailId).append(", password=").append(password)
				.append(", confirmPassword=").append(confirmPassword).append("]");
		return builder.toString();
	} 
	
}
