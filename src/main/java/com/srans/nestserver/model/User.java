package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "sransUser")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonInclude(Include.NON_NULL)
	private Long userId;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "name")
	private String name; 

	@JsonInclude(Include.NON_NULL)
	@Column(name = "role")
	private String role;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name = "status")
	private String status;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "contactNumber")
	private Long contactNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "fatherName")
	private String fatherName;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "fatherphoneNumber")
	private Long fatherphoneNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "motherName")
	private String motherName;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "motherphoneNumber")
	private long motherphoneNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "dob")
	private String dob;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "emergencyContactNumber")
	private long emergencyContactNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "nameOfTheEmployer")
	private String nameOfTheEmployer;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "bloodGroup")
	private String bloodGroup;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "officeAddress")
	private String officeAddress;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "mobileNumber")
	private Long mobileNumber;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "emailId")
	private String emailId;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "permanentAddress")
	private String permanentAddress;

	@Transient
	private  TenantBooking tenantBooking;
	
	@Transient
	private Payment payment;
	
	@Transient
	private Bed bed;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name = "subscriptions")
	private int subscriptions;
	
	@JsonInclude(Include.NON_NULL)
	@Column(name = "subscriptionType")
	private String subscriptionType;
	 
	
	@Transient
	private UserSubscription userSubscriptionWrapper;
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Long userId, String name, Long contactNumber, String fatherName, Long fatherphoneNumber,
			String motherName, long motherphoneNumber, String dob, long emergencyContactNumber,
			String nameOfTheEmployer, String bloodGroup, String officeAddress, Long mobileNumber, String emailId,
			String permanentAddress, TenantBooking tenantBooking, Payment payment, Bed bed) {
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
		this.permanentAddress = permanentAddress;
		this.tenantBooking = tenantBooking;
		this.payment = payment;
		this.bed = bed;
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

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
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

	public UserSubscription getUserSubscriptionWrapper() {
		return userSubscriptionWrapper;
	}

	public void setUserSubscriptionWrapper(UserSubscription userSubscriptionWrapper) {
		this.userSubscriptionWrapper = userSubscriptionWrapper;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
 
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	 

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(int subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", name=");
		builder.append(name);
		builder.append(", role=");
		builder.append(role);
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
		builder.append(", permanentAddress=");
		builder.append(permanentAddress);
		builder.append(", tenantBooking=");
		builder.append(tenantBooking);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", bed=");
		builder.append(bed);
		builder.append(", status=");
		builder.append(status);
		builder.append(", userSubscriptionWrapper=");
		builder.append(userSubscriptionWrapper);
		builder.append("]");
		return builder.toString();
	}

	
}
	

	