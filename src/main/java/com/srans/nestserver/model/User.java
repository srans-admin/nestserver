package com.srans.nestserver.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "sransUser")
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
	private Date dob;

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

	@Column(name = "hostelName")
	private String hostelName;

	@Column(name = "floor")
	private long floor;

	@Column(name = "roomName")
	private String roomName;

	@Column(name = "roomType")
	private String roomType;

//	@Column(name = "date")
//	private long date;

	@Column(name = "depositAmount")
	private Long depositAmount;

	@Column(name = "paymentThrough")
	private long paymentThrough;

	@Transient
	private TenantBooking tenantBooking;

	@Transient
	private Payment payment;

	@Transient
	private Hostel hostel;

	@Transient
	private Bed bed;

	@Column(name = "subscriptions")
	private int subscriptions;

	@Column(name = "subscriptionType")
	private String subscriptionType;

	@Transient
	private UserSubscription userSubscriptionWrapper;

	@Transient
	private Floor floorInfo;

	@Transient
	private Room room;
	
	@Transient
	private String guestToTenant;

	public User() {
		//this.isGuestToTenant = false;
	}

	public User(Long userId, String name, String role, String status, Long contactNumber, String fatherName,
			Long fatherphoneNumber, String motherName, long motherphoneNumber, Date dob, long emergencyContactNumber,
			String nameOfTheEmployer, String bloodGroup, String officeAddress, Long mobileNumber, String emailId,
			String permanentAddress, String hostelName, long floor, String roomName, String roomType,
			Long depositAmount, long paymentThrough, TenantBooking tenantBooking, Payment payment, Hostel hostel,
			Bed bed, int subscriptions, String subscriptionType, UserSubscription userSubscriptionWrapper,
			Floor floorInfo, Room room) {
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
		this.hostelName = hostelName;
		this.floor = floor;
		this.roomName = roomName;
		this.roomType = roomType;
		this.depositAmount = depositAmount;
		this.paymentThrough = paymentThrough;
		this.tenantBooking = tenantBooking;
		this.payment = payment;
		this.hostel = hostel;
		this.bed = bed;
		this.subscriptions = subscriptions;
		this.subscriptionType = subscriptionType;
		this.userSubscriptionWrapper = userSubscriptionWrapper;
		this.floorInfo = floorInfo;
		this.room = room;
		
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

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
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

	public Long getDepositAmount() {
		return depositAmount;
	}

	public void setDepositAmount(Long depositAmount) {
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

	public Hostel getHostel() {
		return hostel;
	}

	public void setHostel(Hostel hostel) {
		this.hostel = hostel;
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

	public Floor getFloorInfo() {
		return floorInfo;
	}

	public void setFloorInfo(Floor floorInfo) {
		this.floorInfo = floorInfo;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	} 
 

	public String getGuestToTenant() {
		return guestToTenant;
	}

	public void setGuestToTenant(String guestToTenant) {
		this.guestToTenant = guestToTenant;
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
		builder.append(", status=");
		builder.append(status);
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
		builder.append(", hostelName=");
		builder.append(hostelName);
		builder.append(", floor=");
		builder.append(floor);
		builder.append(", roomName=");
		builder.append(roomName);
		builder.append(", roomType=");
		builder.append(roomType);
		builder.append(", depositAmount=");
		builder.append(depositAmount);
		builder.append(", paymentThrough=");
		builder.append(paymentThrough);
		builder.append(", tenantBooking=");
		builder.append(tenantBooking);
		builder.append(", payment=");
		builder.append(payment);
		builder.append(", hostel=");
		builder.append(hostel);
		builder.append(", bed=");
		builder.append(bed);
		builder.append(", subscriptions=");
		builder.append(subscriptions);
		builder.append(", subscriptionType=");
		builder.append(subscriptionType);
		builder.append(", userSubscriptionWrapper=");
		builder.append(userSubscriptionWrapper);
		builder.append(", floorInfo=");
		builder.append(floorInfo);
		builder.append(", room=");
		builder.append(room);
		builder.append(", guestToTenant=");
		builder.append(guestToTenant); 
		builder.append("]");
		return builder.toString();
	}

}
