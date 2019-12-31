package com.srans.nestserver.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

public class Notification {

	@Entity
	@Table(name = "notifiaction")
	public class Bed extends AuditModel implements Serializable {

		/**
		 * @author Manish
		 */
		private static final long serialVersionUID = 1L;

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column
		private long id;

		@Column
		private String userRole;

		@Column
		private String selectedSubscriptionPlan;

		@Column
		private String message;

		@Column
		private char viewStatus;

		@Column
		String position;

		public Bed() {
			super();

		}

		public Bed(long id, String message, Character viewStatus, String position) {
			super();
			this.id = id;
			this.message = message;
			this.viewStatus = viewStatus;
			this.position = position;
		}

		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public Character getViewStatus() {
			return viewStatus;
		}

		public void setViewStatus(Character viewStatus) {
			this.viewStatus = viewStatus;
		}

		public String getPosition() {
			return position;
		}

		public void setPosition(String position) {
			this.position = position;
		}

		@Override
		public String toString() {
			StringBuilder builder = new StringBuilder();
			builder.append("Bed [id=").append(id).append(", message=").append(message).append(", viewStatus=")
					.append(viewStatus).append(", position=").append(position).append("]");
			return builder.toString();
		}

	}

}
