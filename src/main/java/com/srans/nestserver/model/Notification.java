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
	private String message;

	public Notification() {
		this.id = 0L; 
		this.message = "";  
	} 

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	 
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	 

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Notification [id=");
		builder.append(id);
		builder.append(", message=");
		builder.append(message); 
		builder.append("]");
		return builder.toString();
	}
 
	
	
}
