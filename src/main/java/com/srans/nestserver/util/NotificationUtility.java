package com.srans.nestserver.util;

public class NotificationUtility {

	private String message;
	private String notificationCategory;
	private String userRole;
	private Character viewStatus;
	private Long notificationId;

	public NotificationUtility() {

		this.message = "";
		this.notificationCategory = "";
		this.userRole = "";
		this.viewStatus = ' ';
		this.notificationId = 0L;

	}

	public NotificationUtility(String message, String notificationCategory, String userRole, Character viewStatus,
			Long notificationId) {
		super();
		this.message = message;
		this.notificationCategory = notificationCategory;
		this.userRole = userRole;
		this.viewStatus = viewStatus;
		this.notificationId = notificationId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getNotificationCategory() {
		return notificationCategory;
	}

	public void setNotificationCategory(String notificationCategory) {
		this.notificationCategory = notificationCategory;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public Character getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(Character viewStatus) {
		this.viewStatus = viewStatus;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificationUtility [message=").append(message).append(", notificationCategory=")
				.append(notificationCategory).append(", userRole=").append(userRole).append(", viewStatus=")
				.append(viewStatus).append(", notificationId=").append(notificationId).append("]");
		return builder.toString();
	}

}
