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
	private String position;
	
	

	public Notification() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Notification(Long id, String userRole, String selectedSubscriptionPlan, String message, Character viewStatus,
			String position) {
		super();
		this.id = id;
		this.userRole = userRole;
		this.selectedSubscriptionPlan = selectedSubscriptionPlan;
		this.message = message;
		this.viewStatus = viewStatus;
		this.position = position;
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



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	@Override
	public String toString() {
		return "Notification [id=" + id + ", userRole=" + userRole + ", selectedSubscriptionPlan="
				+ selectedSubscriptionPlan + ", message=" + message + ", viewStatus=" + viewStatus + ", position="
				+ position + "]";
	}
	
	

}
