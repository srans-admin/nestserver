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

	@Column(name = "motherName")
	private String motherName;

	@Column(name = "motherphoneNumber")
	private long motherphoneNumber;

	@Column(name = "dob")
	private String dob;

	@Column(name = "emergencyContactNumber")
	private long emergencyContactNumber;

	@Column(name = "nameOfTheEmployer")
	private String nameOfTheEmployer;

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
		this.userId = 0L;
		this.name = "";
		this.contactNumber = 0L;
		this.fatherName = "";
		this.fatherphoneNumber = 0L;
		this.permanetAddress = "";
		this.emailId = "";
		this.mobileNumber = 0L;
		this.officeAddress = "";
		this.bloodGroup = "";
		this.dob = "";
		this.tenantBooking = new ArrayList<>();

	}

	public Tenant(Long userId, String name, Long contactNumber, String fatherName, Long fatherphoneNumber,
			String motherName, long motherphoneNumber, String dob, long emergencyContactNumber,
			String nameOfTheEmployer, String bloodGroup, String officeAddress, Long mobileNumber, String emailId,
			String permanetAddress, List<TenantBooking> tenantBooking) {
		super();
		this.userId = userId;
		this.name = name;
		this.contactNumber = contactNumber;
		this.fatherName = fatherName;
		this.fatherphoneNumber = fatherphoneNumber;
		this.motherName = motherName;
		this.motherphoneNumber = motherphoneNumber;
		this.dob = dob;
		this.emergencyContactNumber = emergencyContactNumber;
		this.nameOfTheEmployer = nameOfTheEmployer;
		this.bloodGroup = bloodGroup;
		this.officeAddress = officeAddress;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.permanetAddress = permanetAddress;
		this.tenantBooking = tenantBooking;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(Long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Long getFatherphoneNumber() {
		return fatherphoneNumber;
	}

	public void setFatherphoneNumber(Long fatherphoneNumber) {
		this.fatherphoneNumber = fatherphoneNumber;
	}

	public String getMotherName() {
		return motherName;
	}

	public void setMotherName(String motherName) {
		this.motherName = motherName;
	}

	public long getMotherphoneNumber() {
		return motherphoneNumber;
	}

	public void setMotherphoneNumber(long motherphoneNumber) {
		this.motherphoneNumber = motherphoneNumber;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public long getEmergencyContactNumber() {
		return emergencyContactNumber;
	}

	public void setEmergencyContactNumber(long emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}

	public String getNameOfTheEmployer() {
		return nameOfTheEmployer;
	}

	public void setNameOfTheEmployer(String nameOfTheEmployer) {
		this.nameOfTheEmployer = nameOfTheEmployer;
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

	public Long getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(Long mobileNumber) {
		this.mobileNumber = mobileNumber;
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
				.append(fatherphoneNumber).append(", motherName=").append(motherName).append(", motherphoneNumber=")
				.append(motherphoneNumber).append(", dob=").append(dob).append(", emergencyContactNumber=")
				.append(emergencyContactNumber).append(", nameOfTheEmployer=").append(nameOfTheEmployer)
				.append(", bloodGroup=").append(bloodGroup).append(", officeAddress=").append(officeAddress)
				.append(", mobileNumber=").append(mobileNumber).append(", emailId=").append(emailId)
				.append(", permanetAddress=").append(permanetAddress).append(", tenantBooking=").append(tenantBooking)
				.append("]");
		return builder.toString();
	}

}
