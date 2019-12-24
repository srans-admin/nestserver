package com.srans.nestserver.model;

import org.springframework.stereotype.Component;

/**
	 * Date :24-December-2019
	 * @author ManishKumar
	 * @version 1.0
	 *
	 */
	@Component
	public class User {

		private String emailAddress;

		public String getEmailAddress() {
			return emailAddress;
		}
		public void setEmailAddress(String emailAddress) {
			this.emailAddress = emailAddress;
		}
	}

