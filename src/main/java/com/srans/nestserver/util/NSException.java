/**
 * 
 */
package com.srans.nestserver.util;

/**
 * @author Mine
 *
 */
public class NSException extends Exception {
	 
	private static final long serialVersionUID = 1L;
	
	private String errCode;
	private String errMsg;

	public NSException(){
		this.errCode = "";
		this.errMsg = "";
	}
	
	public NSException(String errCode ) {
		super();
		this.errCode = errCode;
		this.errMsg = " "; //TODO Need to get from properties file with specified errorcode
	}

	public NSException(String errCode, String errMsg) {
		super();
		this.errCode = errCode;
		this.errMsg = errMsg;
	}

	public String getErrCode() {
		return errCode;
	}

	public void setErrCode(String errCode) {
		this.errCode = errCode;
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NSException [errCode=");
		builder.append(errCode);
		builder.append(", errMsg=");
		builder.append(errMsg);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
}
