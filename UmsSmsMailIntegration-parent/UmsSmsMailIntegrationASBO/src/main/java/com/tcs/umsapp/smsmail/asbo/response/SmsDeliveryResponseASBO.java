package com.tcs.umsapp.smsmail.asbo.response;

public class SmsDeliveryResponseASBO {

	private String response;
	private int status;
	
	

	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "MailDeliveryResponseSO [response=" + response + ", status="
				+ status + "]";
	}
}
