package com.tcs.umsapp.unlock.invoker.util;

public class MailContentRequest {
	
	private String sender;
	private String subject;
	private String recepient;
	private String body;
	private String cc;
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRecepient() {
		return recepient;
	}
	public void setRecepient(String recepient) {
		this.recepient = recepient;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	@Override
	public String toString() {
		return "GetContentRequestSO [sender=" + sender + ", subject=" + subject
				+ ", recepient=" + recepient + ", body=" + body + ", cc=" + cc
				+ "]";
	}
	
	

}
