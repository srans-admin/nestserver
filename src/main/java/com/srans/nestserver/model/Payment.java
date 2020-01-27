package com.srans.nestserver.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment extends AuditModel {

/**
*
* @author likhit
*/
private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;

		@Column private Long userId; 
		@Column private Long bookingid; 
		//@Column private String name; 
		//@Column private String amountType; 
		//@Column private String roomName; 
		//@Column private String roomType; 
		//@Column private Long roomRent;
		//@Column private Long roomBedId;
		@Column private String paymentThrough; 
		@Column private Long transactionId; 
		@Column private String bankName; 
		@Column private String date; 
		@Column private Long depositAmount; 
		@Column private Long roomRent;
		@Column private Long discountAmount;
		

		@Column private Long adminId;
 
}