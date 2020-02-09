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
																"--\n@Team NIODS";
	
	public static final String TENANT_CREDETIALS = "Dear ##USER_NAME##,\nYour User ID: ##USER_ID## \nTemp Password : ##TEMP_PASSWORD## \n--\n@Team NIDOS \nWe recommend you to change passowrd on first login.\n"; 
	
	public static final String TENANT_INVOICE_TEMPLATE= "Dear User, \n"+
	                                                     "Your monthly invoice has been generated, pleasee check your registered email for more information \n--\n@Team NIODS"; 
	
	public static final String ADMIN_SUBSCRIPTION_TEMPLATE=" ";
	
	public static final String GUEST_TEMPLATE="Hello ##USER_NAME##, \n"+
	                                                          "Thank you for choosing NIDOS.\n"+
	                                                          "YOUR reservation details below: \n"
	                                                          + "Hostel Name :##HOSTEL_NAME## \n"+
	                                                          "Floor Number : ##FLOOR_NUMBER##\n"+
	                                                          "Room Number : ##ROOM_NUMBER##\n"+
	                                                          "Room Rent : ##ROOM_RENT##\n"+
	                                                          "Bed Number :##BED_NUMBER## \n"+
	                                                          "Last Date :##LAST_DATE## \n"+
			                                                   "--\n@Team NIODS"                 ;
	
	public static final String VACATE_TENANT_MESSAGE_TEMPLATE="Thank you for visiting the hostel";
	
 

}
