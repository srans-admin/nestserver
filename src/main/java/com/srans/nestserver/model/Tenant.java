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
@Table(name = "tenant")
public class Tenant implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "contactNumber")
	private Long contactNumber;
	
	@Column(name = "fatherName")
	private String fatherName;
	
	@Column(name = "fatherphoneNumber")
	private Long fatherphoneNumber;
	
	@Column(name = "dob")
	private String dob;
	
	@Column(name = "bloodGroup")
	private String bloodGroup;
	
	@Column(name = "officeAddress")
	private String officeAddress;
	
	@Column(name = "mobileNumber")
	private Long mobileNumber;
	
	@Column(name = "emailId")
	private String emailId;
	
	@Column(name = "permanetAddress")
	private String permanetAddress;

	@Transient
	private List<TenantBooking> tenantBooking;

	public Tenant() {
		super();
		this.userId=0L;
		this.name = "";
		this.contactNumber= 0L ;
		this.fatherName = "";
		this.fatherphoneNumber=0L;
		this.permanetAddress="";
		this.emailId="";
		this.mobileNumber=0L;
		this.officeAddress="";
		this.bloodGroup="";
		this.dob="";
		this.tenantBooking = new ArrayList<>();

	}

	public Tenant(long userId, String name, long contactNumber, String fatherName, long fatherphoneNumber,
			String motherName, long motherphoneNumber, String dob, String bloodGroup, long emergencyContactNumber,
			String nameOfTheEmployer, String officeAddress, long telephoneNumber, String emailId,
			String permanetAddress) {
		super();
		this.userId = userId;
		this.name = name;
		this.contactNumber = contactNumber;
		this.fatherName = fatherName;
		this.fatherphoneNumber = fatherphoneNumber;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.officeAddress = officeAddress;
		this.emailId = emailId;
		this.permanetAddress = permanetAddress;

	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public long getFatherphoneNumber() {
		return fatherphoneNumber;
	}

	public void setFatherphoneNumber(long fatherphoneNumber) {
		this.fatherphoneNumber = fatherphoneNumber;
	}

	public void setMotherName(String motherName) {
	}

	

	public void setMotherphoneNumber(long motherphoneNumber) {
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getOfficeAddress() {
		return officeAddress;
	}

	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}

	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPermanetAddress() {
		return permanetAddress;
	}

	public void setPermanetAddress(String permanetAddress) {
		this.permanetAddress = permanetAddress;
	}

	public List<TenantBooking> getTenantBooking() {
		return tenantBooking;
	}

	public void setTenantBooking(List<TenantBooking> tenantBooking) {
		this.tenantBooking = tenantBooking;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [userId=").append(userId).append(", name=").append(name).append(", contactNumber=")
				.append(contactNumber).append(", fatherName=").append(fatherName).append(", fatherphoneNumber=")
				.append(fatherphoneNumber).append(", motherName=").append(", motherphoneNumber=")
				.append(", dob=").append(dob).append(", bloodGroup=").append(bloodGroup)
				.append(", emergencyContactNumber=").append(", nameOfTheEmployer=")
				.append(", officeAddress=").append(officeAddress).append(", telephoneNumber=")
				.append(", emailId=").append(emailId).append(", permanetAddress=")
				.append(permanetAddress).append("]");
		return builder.toString();
	}

}
