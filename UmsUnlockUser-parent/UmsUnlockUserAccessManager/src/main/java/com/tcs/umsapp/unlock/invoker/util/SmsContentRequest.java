package com.tcs.umsapp.unlock.invoker.util;

public class SmsContentRequest {

	private String body;
	private String mobileNumber;
	
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	@Override
	public String toString() {
		return "SmsContentRequestSO [body=" + body
				+ ", mobileNumber=" + mobileNumber + "]";
	}
}
