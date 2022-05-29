package com.tcs.umsapp.unlock.response;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class UnlockAccountResponseASBO extends UmsappResponseObject{

	private static final long serialVersionUID = -2363412834088758942L;
	
	private String statusCode;
	
	private String statusMsg;

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public String getStatusMsg() {
		return statusMsg;
	}

	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}

	@Override
	public String toString() {
		return "unlockAccountResponseASBO [statusCode=" + statusCode
				+ ", statusMsg=" + statusMsg + "]";
	}
	
	

}
