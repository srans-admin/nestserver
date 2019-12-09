package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tenant")
public class Tenant implements Serializable {
 
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;
	@Column(name = "name")
	private String name;
	@Column(name = "contactNumber")
	private String contactNumber;
	@Column(name = "fatherName")
	private String fatherName;
	@Column(name = "fatherphoneNumber")
	private String fatherphoneNumber;
	@Column(name="motherName")
	private String motherName;
	@Column(name="motherphoneNumber")
	private String motherphoneNumber;
	@Column(name="dob")
	private String dob;
	
	@Column(name="bloodGroup")
	private String bloodGroup;
	@Column(name="emergencyContactNumber")
	private String emergencyContactNumber;
	@Column(name="nameOfTheEmployer")
	private String nameOfTheEmployer;
	@Column(name="officeAddress")
	private String officeAddress;
	@Column(name="telephoneNumber")
	private String telephoneNumber;
	@Column(name="emailId")
	private String emailId;
	@Column(name="permanetAddress")
	private String permanetAddress;

	
	
	


	public Tenant(long userId, String name, String contactNumber, String fatherName, String fatherphoneNumber,
			String motherName, String motherphoneNumber, String dob, String bloodGroup, String emergencyContactNumber,
			String nameOfTheEmployer, String officeAddress, String telephoneNumber, String emailId,
			String permanetAddress) {
		super();
		this.userId = userId;
		this.name = name;
		this.contactNumber = contactNumber;
		this.fatherName = fatherName;
		this.fatherphoneNumber = fatherphoneNumber;
		this.motherName = motherName;
		this.motherphoneNumber = motherphoneNumber;
		this.dob = dob;
		this.bloodGroup = bloodGroup;
		this.emergencyContactNumber = emergencyContactNumber;
		this.nameOfTheEmployer = nameOfTheEmployer;
		this.officeAddress = officeAddress;
		this.telephoneNumber = telephoneNumber;
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






	public String getContactNumber() {
		return contactNumber;
	}






	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}






	public String getFatherName() {
		return fatherName;
	}






	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}






	public String getFatherphoneNumber() {
		return fatherphoneNumber;
	}






	public void setFatherphoneNumber(String fatherphoneNumber) {
		this.fatherphoneNumber = fatherphoneNumber;
	}






	public String getMotherName() {
		return motherName;
	}






	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}






	public String getMotherphoneNumber() {
		return motherphoneNumber;
	}






	public void setMotherphoneNumber(String motherphoneNumber) {
		this.motherphoneNumber = motherphoneNumber;
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






	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}






	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}






	public String getNameOfTheEmployer() {
		return nameOfTheEmployer;
	}






	public void setNameOfTheEmployer(String nameOfTheEmployer) {
		this.nameOfTheEmployer = nameOfTheEmployer;
	}






	public String getOfficeAddress() {
		return officeAddress;
	}






	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}






	public String getTelephoneNumber() {
		return telephoneNumber;
	}






	public void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
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






	public static long getSerialversionuid() {
		return serialVersionUID;
	}






	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [userId=").append(userId).append(", name=").append(name).append(", contactNumber=")
				.append(contactNumber).append(", fatherName=").append(fatherName).append(", fatherphoneNumber=")
				.append(fatherphoneNumber).append(", motherName=").append(motherName).append(", motherphoneNumber=")
				.append(motherphoneNumber).append(", dob=").append(dob).append(", bloodGroup=").append(bloodGroup)
				.append(", emergencyContactNumber=").append(emergencyContactNumber).append(", nameOfTheEmployer=")
				.append(nameOfTheEmployer).append(", officeAddress=").append(officeAddress).append(", telephoneNumber=")
				.append(telephoneNumber).append(", emailId=").append(emailId).append(", permanetAddress=")
				.append(permanetAddress).append("]");
		return builder.toString();
	}




}







