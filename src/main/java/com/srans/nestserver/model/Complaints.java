package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "complaints")
public class Complaints implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@Transient
	private User user;
	private long userid;
	private String description;
	private String status;

	public Complaints() {
		super();

	}

	public Complaints(long id, User user, long userid, String description, String status) {
		super();
		this.id = id;
		this.user = user;
		this.userid = userid;
		this.description = description;
		this.status = status;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Complaints [id=").append(id).append(", user=").append(user).append(", userid=").append(userid)
				.append(", description=").append(description).append(", status=").append(status).append("]");
		return builder.toString();
	}

}