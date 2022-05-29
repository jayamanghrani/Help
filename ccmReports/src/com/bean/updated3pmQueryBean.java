package com.bean;

public class updated3pmQueryBean {
	private String ticketNo;
	private String problemSummary;
	
	
	public String getTicketNo() {
		return ticketNo;
	}
	public void setTicketNo(String ticketNo) {
		this.ticketNo = ticketNo;
	}
	public String getProblemSummary() {
		return problemSummary;
	}
	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}
	public updated3pmQueryBean(String ticketNo, String problemSummary) {
		super();
		this.ticketNo = ticketNo;
		this.problemSummary = problemSummary;
	}
	public updated3pmQueryBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
