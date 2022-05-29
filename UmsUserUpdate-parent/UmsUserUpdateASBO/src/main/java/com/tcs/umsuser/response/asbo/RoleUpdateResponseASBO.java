package com.tcs.umsuser.response.asbo;

import com.tcs.umsuser.vo.cmo.UmsResponseObject;

public class RoleUpdateResponseASBO extends UmsResponseObject{


	private static final long serialVersionUID = 1L;
	private String statusCode;
	private String statusMessage;
	private long requestId;
	
	public long getRequestId() {
		return requestId;
	}
	public void setRequestId(long requestId) {
		this.requestId = requestId;
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
	@Override
	public String toString() {
		return "RoleUpdateResponseASBO [statusCode=" + statusCode
				+ ", statusMessage=" + statusMessage + ", requestId="
				+ requestId + "]";
	}
	
	
	
}
