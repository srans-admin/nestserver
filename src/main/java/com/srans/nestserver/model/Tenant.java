package com.srans.nestserver.model;

import java.io.Serializable;

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
	private  TenantBooking tenantBooking;
	
	@Transient
	private Payment payment;
	
	@Transient
	private Bed bed;

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
		this.tenantBooking = new TenantBooking();
		this.payment = new Payment();
		this.bed = new Bed();

	}

	public Tenant(Long userId, String name, Long contactNumber, String fatherName, Long fatherphoneNumber,
			String motherName, long motherphoneNumber, String dob, long emergencyContactNumber,
			String nameOfTheEmployer, String bloodGroup, String officeAddress, Long mobileNumber, String emailId,
			String permanetAddress, TenantBooking tenantBooking) {
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
 

	public TenantBooking getTenantBooking() {
		return tenantBooking;
	}

	public void setTenantBooking(TenantBooking tenantBooking) {
		this.tenantBooking = tenantBooking;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}
	
	

	public Bed getBed() {
		return bed;
	}

	public void setBed(Bed bed) {
		this.bed = bed;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tenant [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", contactNumber=");
		builder.append(contactNumber);
		builder.append(", fatherName=");
		builder.append(fatherName);
		builder.append(", fatherphoneNumber=");
		builder.append(fatherphoneNumber);
		builder.append(", motherName=");
		builder.append(motherName);
		builder.append(", motherphoneNumber=");
		builder.append(motherphoneNumber);
		builder.append(", dob=");
		builder.append(dob);
		builder.append(", emergencyContactNumber=");
		builder.append(emergencyContactNumber);
		builder.append(", nameOfTheEmployer=");
		builder.append(nameOfTheEmployer);
		builder.append(", bloodGroup=");
		builder.append(bloodGroup);
		builder.append(", officeAddress=");
		builder.append(officeAddress);
		builder.append(", mobileNumber=");
		builder.append(mobileNumber);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", permanetAddress=");
		builder.append(permanetAddress);
		builder.append(", tenantBooking=");
		builder.append(tenantBooking);
		builder.append(", payment=");
		builder.append(payment);
		builder.append("]");
		return builder.toString();
	} 
	 

}
