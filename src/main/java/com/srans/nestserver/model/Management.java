package com.srans.nestserver.model;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name="management")
public class Management implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String hostelName;
	private String name;
	private String roleName;

public Management() {
}

public Management(long id, String hostelName, String name, String roleName) {
	super();
	this.id = id;
	this.hostelName = hostelName;
	this.name = name;
	this.roleName = roleName;
}
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
public long getId() {
	return id;
}

public void setId(long id) {
	this.id = id;
}
@Column(name = "HostelName")
public String getHostelName() {
	return hostelName;
}

public void setHostelName(String hostelName) {
	this.hostelName = hostelName;
}
@Column(name = "name")
public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}
@Column(name = "RoleName")
public String getRoleName() {
	return roleName;
}

public void setRoleName(String roleName) {
	this.roleName = roleName;
}

@Override
public String toString() {
	StringBuilder builder = new StringBuilder();
	builder.append("Management [id=");
	builder.append(id);
	builder.append(", hostelName=");
	builder.append(hostelName);
	builder.append(", name=");
	builder.append(name);
	builder.append(", roleName=");
	builder.append(roleName);
	builder.append("]");
	return builder.toString();
}

}

