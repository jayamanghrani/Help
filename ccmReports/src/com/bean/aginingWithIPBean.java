package com.bean;

import java.sql.Date;

public class aginingWithIPBean {
	private int ticketNO;
	private Date ticketLogDate;
	private Date assignedOn;
	private String problemCategory;
	private String problemType;
	private String problemItem;
	private String problemSummary;
	private String problemDescription;
	private int ticketLoggedUserID;
	private String ticketLoggedUsername;
	private String PersonResponsibleUserId;
	private String PersonResponsibeUserName;
	private String role;
	private String pendingToWIP;
	private String department;
	private String status;
	private String userOfficeCode;
	private String priority;
	private String severity;
	private String customerGroupName;
	private String callClassification;
	private String spCallClassification;
	private int noOfAgingDays;
	public int getTicketNO() {
		return ticketNO;
	}
	public void setTicketNO(int ticketNO) {
		this.ticketNO = ticketNO;
	}
	public Date getTicketLogDate() {
		return ticketLogDate;
	}
	public void setTicketLogDate(Date ticketLogDate) {
		this.ticketLogDate = ticketLogDate;
	}
	public Date getAssignedOn() {
		return assignedOn;
	}
	public void setAssignedOn(Date assignedOn) {
		this.assignedOn = assignedOn;
	}
	public String getProblemCategory() {
		return problemCategory;
	}
	public void setProblemCategory(String problemCategory) {
		this.problemCategory = problemCategory;
	}
	public String getProblemType() {
		return problemType;
	}
	public void setProblemType(String problemType) {
		this.problemType = problemType;
	}
	public String getProblemItem() {
		return problemItem;
	}
	public void setProblemItem(String problemItem) {
		this.problemItem = problemItem;
	}
	public String getProblemSummary() {
		return problemSummary;
	}
	public void setProblemSummary(String problemSummary) {
		this.problemSummary = problemSummary;
	}
	public String getProblemDescription() {
		return problemDescription;
	}
	public void setProblemDescription(String problemDescription) {
		this.problemDescription = problemDescription;
	}
	public int getTicketLoggedUserID() {
		return ticketLoggedUserID;
	}
	public void setTicketLoggedUserID(int ticketLoggedUserID) {
		this.ticketLoggedUserID = ticketLoggedUserID;
	}
	public String getTicketLoggedUsername() {
		return ticketLoggedUsername;
	}
	public void setTicketLoggedUsername(String ticketLoggedUsername) {
		this.ticketLoggedUsername = ticketLoggedUsername;
	}
	public String getPersonResponsibleUserId() {
		return PersonResponsibleUserId;
	}
	public void setPersonResponsibleUserId(String personResponsibleUserId) {
		PersonResponsibleUserId = personResponsibleUserId;
	}
	public String getPersonResponsibeUserName() {
		return PersonResponsibeUserName;
	}
	public void setPersonResponsibeUserName(String personResponsibeUserName) {
		PersonResponsibeUserName = personResponsibeUserName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getPendingToWIP() {
		return pendingToWIP;
	}
	public void setPendingToWIP(String pendingToWIP) {
		this.pendingToWIP = pendingToWIP;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserOfficeCode() {
		return userOfficeCode;
	}
	public void setUserOfficeCode(String userOfficeCode) {
		this.userOfficeCode = userOfficeCode;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getSeverity() {
		return severity;
	}
	public void setSeverity(String severity) {
		this.severity = severity;
	}
	public String getCustomerGroupName() {
		return customerGroupName;
	}
	public void setCustomerGroupName(String customerGroupName) {
		this.customerGroupName = customerGroupName;
	}
	public String getCallClassification() {
		return callClassification;
	}
	public void setCallClassification(String callClassification) {
		this.callClassification = callClassification;
	}
	public String getSpCallClassification() {
		return spCallClassification;
	}
	public void setSpCallClassification(String spCallClassification) {
		this.spCallClassification = spCallClassification;
	}
	public int getNoOfAgingDays() {
		return noOfAgingDays;
	}
	public void setNoOfAgingDays(int noOfAgingDays) {
		this.noOfAgingDays = noOfAgingDays;
	}
	
	
	
	public aginingWithIPBean(int ticketNO, Date ticketLogDate, Date assignedOn, String problemCategory, String problemType,
			String problemItem, String problemSummary, String problemDescription, int ticketLoggedUserID,
			String ticketLoggedUsername, String personResponsibleUserId, String personResponsibeUserName, String role,
			String pendingToWIP, String department, String status, String userOfficeCode, String priority,
			String severity, String customerGroupName, String callClassification, String spCallClassification,
			int noOfAgingDays) {
		super();
		this.ticketNO = ticketNO;
		this.ticketLogDate = ticketLogDate;
		this.assignedOn = assignedOn;
		this.problemCategory = problemCategory;
		this.problemType = problemType;
		this.problemItem = problemItem;
		this.problemSummary = problemSummary;
		this.problemDescription = problemDescription;
		this.ticketLoggedUserID = ticketLoggedUserID;
		this.ticketLoggedUsername = ticketLoggedUsername;
		PersonResponsibleUserId = personResponsibleUserId;
		PersonResponsibeUserName = personResponsibeUserName;
		this.role = role;
		this.pendingToWIP = pendingToWIP;
		this.department = department;
		this.status = status;
		this.userOfficeCode = userOfficeCode;
		this.priority = priority;
		this.severity = severity;
		this.customerGroupName = customerGroupName;
		this.callClassification = callClassification;
		this.spCallClassification = spCallClassification;
		this.noOfAgingDays = noOfAgingDays;
	}
	public aginingWithIPBean() {
		super();
	}

}
