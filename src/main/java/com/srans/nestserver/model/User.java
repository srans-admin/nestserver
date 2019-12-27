package com.srans.nestserver.model;

import java.util.List;

import org.springframework.stereotype.Component;


	@Component
	public class User {

		private List<String> emailAddress;

		public List<String> getEmailAddress() {
			return emailAddress;
		}

		public void setEmailAddress(List<String> emailAddress) {
			this.emailAddress = emailAddress;
		}

	

	}

