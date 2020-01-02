package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notificationuser")
public class NotificationUser extends AuditModel implements Serializable {

	/**
	 * @author user
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private Long id;

	@Column
	private Long notificationId;

	@Column
	private Long superAdminCode;

	@Column
	private String superAdminUsername;

	public NotificationUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NotificationUser(Long id, Long notificationId, Long superAdminCode, String superAdminUsername) {
		super();
		this.id = id;
		this.notificationId = notificationId;
		this.superAdminCode = superAdminCode;
		this.superAdminUsername = superAdminUsername;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getSuperAdminCode() {
		return superAdminCode;
	}

	public void setSuperAdminCode(Long superAdminCode) {
		this.superAdminCode = superAdminCode;
	}

	public String getsuperAdminUsername() {
		return superAdminUsername;
	}

	public void setsuperAdminUsername(String superAdminUsername) {
		this.superAdminUsername = superAdminUsername;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NotificationUser [id=").append(id).append(", notificationId=").append(notificationId)
				.append(", superAdminCode=").append(superAdminCode).append(", superAdminUsername=")
				.append(superAdminUsername).append("]");
		return builder.toString();
	}

}
