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

@Entity
@Table(name = "sransUser")
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;

	@Column(name = "name")
	private String name;

	@Column(name = "role")
	private String role;

	@Column(name = "status")
	private String status;

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

	@Column(name = "permanentAddress")
	private String permanentAddress;

	@Column(name = "subscriptions")
	private Long subscriptions;

	@Column(name = "subscriptionType")
	private String subscriptionType;

	@Transient
	private TenantBooking tenantBooking;

	@Transient
	private Payment payment;

	@Transient
	private Bed bed;

	@Transient
	private UserSubscription userSubscriptionWrapper;

	public User() {

		super();

	
	}

	public User(Long userId, String name, String role, String status, Long contactNumber, String fatherName,
			Long fatherphoneNumber, String motherName, long motherphoneNumber, String dob, long emergencyContactNumber,
			String nameOfTheEmployer, String bloodGroup, String officeAddress, Long mobileNumber, String emailId,
			String permanentAddress, Long subscriptions, String subscriptionType, TenantBooking tenantBooking,
			Payment payment, Bed bed, UserSubscription userSubscriptionWrapper) {
		super();
		this.userId = userId;
		this.name = name;
		this.role = role;
		this.status = status;
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
		this.subscriptions = subscriptions;
		this.subscriptionType = subscriptionType;
		this.tenantBooking = tenantBooking;
		this.payment = payment;
		this.bed = bed;
		this.userSubscriptionWrapper = userSubscriptionWrapper;
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

	public Long getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Long subscriptions) {
		this.subscriptions = subscriptions;
	}

	public String getSubscriptionType() {
		return subscriptionType;
	}

	public void setSubscriptionType(String subscriptionType) {
		this.subscriptionType = subscriptionType;
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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=").append(userId).append(", name=").append(name).append(", role=").append(role)
				.append(", status=").append(status).append(", contactNumber=").append(contactNumber)
				.append(", fatherName=").append(fatherName).append(", fatherphoneNumber=").append(fatherphoneNumber)
				.append(", motherName=").append(motherName).append(", motherphoneNumber=").append(motherphoneNumber)
				.append(", dob=").append(dob).append(", emergencyContactNumber=").append(emergencyContactNumber)
				.append(", nameOfTheEmployer=").append(nameOfTheEmployer).append(", bloodGroup=").append(bloodGroup)
				.append(", officeAddress=").append(officeAddress).append(", mobileNumber=").append(mobileNumber)
				.append(", emailId=").append(emailId).append(", permanentAddress=").append(permanentAddress)
				.append(", subscriptions=").append(subscriptions).append(", subscriptionType=").append(subscriptionType)
				.append(", tenantBooking=").append(tenantBooking).append(", payment=").append(payment).append(", bed=")
				.append(bed).append(", userSubscriptionWrapper=").append(userSubscriptionWrapper).append("]");
		return builder.toString();
	}

}
