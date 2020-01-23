/**
 * 
 */
package com.srans.nestserver.util;

/**
 * @author user
 *
 */
public class SMSTemplates {
	
	public static final String TENANT_REGISTRATION_TEMPLATE = " Hello, \n"+
																"Welcome to NIDOS Hospitalities.\n"+
																"Looking forward to serve you better.\n"+
																"\n"+
																"Thanks\n"+
																"@Team Srans ";
	
	public static final String TENANT_CREDETIALS = "Hello ##USER_NAME##,\n YOUR User ID: ##USER_ID## and Temp Password : ##TEMP_PASSWORD## "; 
	
	public static final String TENANT_INVOICE_TEMPLATE= " "; 
	
	public static final String ADMIN_SUBSCRIPTION_TEMPLATE=" ";
	
	public static final String GUEST_SUBSCRIPTION_TEMPLATE="Hello ##USER_NAME##, \n"+
	                                                          "Thank you for booking bed.\n"+
			                                                   "@Team Srans"                 ;
	
	public static final String VACATE_TENANT_MESSAGE_TEMPLATE="Thank you for visiting the hostel";

}
