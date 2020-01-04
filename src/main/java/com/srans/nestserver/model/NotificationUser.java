package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificationUser")
public class NotificationUser extends AuditModel implements Serializable {

	/**
	 * @author user
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private long id;

	@Column
	private long userId;
	
	@Column
	private long notificationId;
	
	@Column
	private String viewStatus; 
	  

	public NotificationUser() {
		this.userId =  -1;
		this.notificationId = -1;
		this.viewStatus = "N"; 
	}



	public long getId() {
		return id;
	}



	public void setId(long id) {
		this.id = id;
	}



	public long getUserId() {
		return userId;
	}



	public void setUserId(long userId) {
		this.userId = userId;
	}



	public long getNotificationId() {
		return notificationId;
	}



	public void setNotificationId(long notificationId) {
		this.notificationId = notificationId;
	}



	public String getViewStatus() {
		return viewStatus;
	}



	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}



	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificationUser [id=");
		builder.append(id);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", notificationId=");
		builder.append(notificationId);
		builder.append(", viewStatus=");
		builder.append(viewStatus);
		builder.append("]");
		return builder.toString();
	}
 
	
	
 
}
