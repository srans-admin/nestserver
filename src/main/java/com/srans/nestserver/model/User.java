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

	@JsonInclude(Include.NON_NULL)
	@Column(name = "hostelName")
	private String hostelName;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "floor")
	private long floor;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "roomName")
	private String roomName;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "roomType")
	private String roomType;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "date")
	private long date;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "depositAmount")
	private long depositAmount;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "paymentThrough")
	private long paymentThrough;

	@Transient
	private TenantBooking tenantBooking;

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

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public long getFloor() {
		return floor;
	}

	public void setFloor(long floor) {
		this.floor = floor;
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}

	public long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(long depositAmount) {
		this.depositAmount = depositAmount;
	}

	public long getPaymentThrough() {
		return paymentThrough;
	}

	public void setPaymentThrough(long paymentThrough) {
		this.paymentThrough = paymentThrough;
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

	public UserSubscription getUserSubscriptionWrapper() {
		return userSubscriptionWrapper;
	}

	public void setUserSubscriptionWrapper(UserSubscription userSubscriptionWrapper) {
		this.userSubscriptionWrapper = userSubscriptionWrapper;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
				.append(", hostelName=").append(hostelName).append(", floor=").append(floor).append(", roomName=")
				.append(roomName).append(", roomType=").append(roomType).append(", date=").append(date)
				.append(", depositAmount=").append(depositAmount).append(", paymentThrough=").append(paymentThrough)
				.append(", tenantBooking=").append(tenantBooking).append(", payment=").append(payment).append(", bed=")
				.append(bed).append(", subscriptions=").append(subscriptions).append(", subscriptionType=")
				.append(subscriptionType).append(", userSubscriptionWrapper=").append(userSubscriptionWrapper)
				.append("]");
		return builder.toString();
	}

}