/**
 * 
 */
package com.srans.nestserver.scheduler;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author user
 *
 */
@Component
public class NIODSScheduler {
	 
	//@Scheduled(cron = "${nidos.cron.trigger}")
	public void generateInvoiceOntime(){
		
		System.out.println("I am triggered here ...");
		
		//TODO Need to genrate Invoice for individual tenants and send an email
		
	
		
	}
	

}
