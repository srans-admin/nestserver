package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "subscription")
public class Subscription extends AuditModel {

	/**
	 * @author user
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column
	private String name;

	@Column
	private String email;

	@Column
	private String userName;

	@Column
	private Long phoneNo;

	@Column
	private Integer noOfSubscriptions;

	@Transient
	private Notification notification;

	/*
	 * @Transient private List<Notification> notification;
	 */

	public Subscription() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Subscription(Long id, String name, String email, String userName, Long phoneNo, Integer noOfSubscriptions,
			Notification notification) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.userName = userName;
		this.phoneNo = phoneNo;
		this.noOfSubscriptions = noOfSubscriptions;
		this.notification = notification;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getNoOfSubscriptions() {
		return noOfSubscriptions;
	}

	public void setNoOfSubscriptions(Integer noOfSubscriptions) {
		this.noOfSubscriptions = noOfSubscriptions;
	}

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Subscription [id=").append(id).append(", name=").append(name).append(", email=").append(email)
				.append(", userName=").append(userName).append(", phoneNo=").append(phoneNo)
				.append(", noOfSubscriptions=").append(noOfSubscriptions).append(", notification=").append(notification)
				.append("]");
		return builder.toString();
	}

}
