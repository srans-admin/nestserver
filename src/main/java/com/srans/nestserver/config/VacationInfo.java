package com.srans.nestserver.config;


public class VacationInfo {

	private Long id;

	private Long tenantId;

	private String message;

	private Long refundAmount;
	
	

	public VacationInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public VacationInfo(Long id, Long tenantId, String message, Long refundAmount) {
		super();
		this.id = id;
		this.tenantId = tenantId;
		this.message = message;
		this.refundAmount = refundAmount;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTenantId() {
		return tenantId;
	}

	public void setTenantId(Long tenantId) {
		this.tenantId = tenantId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(Long refundAmount) {
		this.refundAmount = refundAmount;
	}

	@Override
	public String toString() {
		return "VacationInfo [id=" + id + ", tenantId=" + tenantId + ", message=" + message + ", refundAmount="
				+ refundAmount + "]";
	}

}
