/**
 * 
 */
package com.srans.nestserver.model;

/**
 * @author Mine
 *
 */
public class Contact {
	
	private String name;
	private String phone;
	private String email;
	private String type; 
	
	
	
	public Contact(String name, String phone, String email, String type) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.type = type;
	}



	public Contact( ) {
		super(); 
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Contact [name=");
		builder.append(name);
		builder.append(", phone=");
		builder.append(phone);
		builder.append(", email=");
		builder.append(email);
		builder.append(", type=");
		builder.append(type);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	

}
