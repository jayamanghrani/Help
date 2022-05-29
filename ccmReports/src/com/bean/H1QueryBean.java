package com.bean;

public class H1QueryBean {
	private String ticketNo;
	private String SPUsername;
	private String Department;
	
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getSPUsername() {
		return SPUsername;
	}
	public void setSPUsername(String sPUsername) {
		SPUsername = sPUsername;
	}
	public String getDepartment() {
		return Department;
	}
	public void setDepartment(String department) {
		Department = department;
	}
	public H1QueryBean(String ticketNo, String sPUsername, String department) {
		super();
		this.ticketNo = ticketNo;
		SPUsername = sPUsername;
		Department = department;
	}
	public H1QueryBean() {
		super();
	}
	
	
	

}
