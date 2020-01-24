package com.srans.nestserver.util;

public class ConsolidatedHostel {

	private String hostelName;

	private String hostelAddress;

	private Long adminId;

	private String adminName;

	private Long hostelId;

	private String email;

	private Long phoneNo;

	private Long payment;

	private Long subscriptions;

	public ConsolidatedHostel() {
		super();

	}

	public ConsolidatedHostel(String hostelName, String hostelAddress, String adminName, Long hostelId, String email,
			Long phoneNo, Long payment, Long subscriptions, Long adminId) {
		super();
		this.hostelName = hostelName;
		this.hostelAddress = hostelAddress;
		this.adminName = adminName;
		this.hostelId = hostelId;
		this.email = email;
		this.phoneNo = phoneNo;
		this.payment = payment;
		this.subscriptions = subscriptions;

		this.adminId = adminId;
	}

	public String getHostelName() {
		return hostelName;
	}

	public void setHostelName(String hostelName) {
		this.hostelName = hostelName;
	}

	public String getHostelAddress() {
		return hostelAddress;
	}

	public void setHostelAddress(String hostelAddress) {
		this.hostelAddress = hostelAddress;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public Long getHostelId() {
		return hostelId;
	}

	public void setHostelId(Long hostelId) {
		this.hostelId = hostelId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Long getPayment() {
		return payment;
	}

	public void setPayment(Long payment) {
		this.payment = payment;
	}

	public Long getsubscriptions() {
		return subscriptions;
	}

	public void setsubscriptions(Long subscriptions) {
		this.subscriptions = subscriptions;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ConsolidatedHostel [hostelName=").append(hostelName).append(", hostelAddress=")
				.append(hostelAddress).append(", adminId=").append(adminId).append(", adminName=").append(adminName)
				.append(", hostelId=").append(hostelId).append(", email=").append(email).append(", phoneNo=")
				.append(phoneNo).append(", payment=").append(payment).append(", subscriptions=").append(subscriptions)
				.append(", userId=").append("]");
		return builder.toString();
	}

}
