package com.tcs.umsoid.response;

import com.tcs.umsoid.vo.cmo.UmsappResponseObject;

public class UserLdapDetailResponseASBO extends UmsappResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6287295995220551211L;
	private String statusCode;
	private String statusMessage;
	
	public UserLdapDetailResponseASBO() {
		super();
	}
	public UserLdapDetailResponseASBO(String statusCode, String statusMessage) {
		super();
		this.statusCode = statusCode;
		this.statusMessage = statusMessage;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusMessage() {
		return statusMessage;
	}
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}
	
	
}
