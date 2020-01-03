package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notification")
public class Notification extends AuditModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private String userRole;

	@Column
	private String selectedSubscriptionPlan;

	@Column
	private String message;

	@Column
	private Character viewStatus;

	@Column
	private String notificationCategory;

	@Column
	private Long adminId;

	@Column
	private String userName;
	
	

	public Notification() {
		this.id = 0L;
		this.userRole = "";
		this.selectedSubscriptionPlan = "";
		this.message = "";
		this.viewStatus = ' ';
		this.notificationCategory = "";
		this.adminId = 0L;
		this.userName = "";
	}

	public Notification(Long id, String userRole, String selectedSubscriptionPlan, String message, Character viewStatus,
			String notificationCategory, Long adminId, String userName) {
		super();
		this.id = id;
		this.userRole = userRole;
		this.selectedSubscriptionPlan = selectedSubscriptionPlan;
		this.message = message;
		this.viewStatus = viewStatus;
		this.notificationCategory = notificationCategory;
		this.adminId = adminId;
		this.userName = userName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getSelectedSubscriptionPlan() {
		return selectedSubscriptionPlan;
	}

	public void setSelectedSubscriptionPlan(String selectedSubscriptionPlan) {
		this.selectedSubscriptionPlan = selectedSubscriptionPlan;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Character getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(Character viewStatus) {
		this.viewStatus = viewStatus;
	}

	public String getNotificationCategory() {
		return notificationCategory;
	}

	public void setNotificationCategory(String notificationCategory) {
		this.notificationCategory = notificationCategory;
	}

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [id=").append(id).append(", userRole=").append(userRole)
				.append(", selectedSubscriptionPlan=").append(selectedSubscriptionPlan).append(", message=")
				.append(message).append(", viewStatus=").append(viewStatus).append(", notificationCategory=")
				.append(notificationCategory).append(", adminId=").append(adminId).append(", userName=")
				.append(userName).append("]");
		return builder.toString();
	}

}
