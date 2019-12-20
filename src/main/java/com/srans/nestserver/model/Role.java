package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "role")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	private long id;
	private String roleName;
	private String name;
	private String emailId;
	
	
	
	public Role() {
		super();
	}

	public Role(long id,String roleName, String name, String emailId) {
		this.roleName = roleName;
		this.name = name;
		this.emailId = emailId;
		this.id=id;
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "RoleName")
	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Role [id=");
		builder.append(id);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", name=");
		builder.append(name);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append("]");
		return builder.toString();
	}


}