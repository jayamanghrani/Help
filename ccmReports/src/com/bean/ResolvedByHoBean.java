package com.bean;

import java.sql.Date;

public class ResolvedByHoBean {
	private String ticketNO;
	private Date solvedDate;
	private Date ticketLogDate;
	private String problemCategory;
	private String problemType;
	private String problemItem;
	private String problemSummary;
	private String problemDescription;
	private String ticketLoggedUserID;
	private String ticketLoggedUsername;
	private String SpUserId;
	private String SpUserName;
	private String role;
	private String department;
	private String status;
	private String userOfficeCode;
	private String priority;
	private String severity;
	private String customerGroupName;
	private String callClassification;
	private String spCallClassification;
	public String getTicketNO() {
		return ticketNO;
	}
	public void setTicketNO(String ticketNO) {
		this.ticketNO = ticketNO;
	}
	public Date getSolvedDate() {
		return solvedDate;
	}
	public void setSolvedDate(Date solvedDate) {
		this.solvedDate = solvedDate;
	}
	public Date getTicketLogDate() {
		return ticketLogDate;
	}
	public void setTicketLogDate(Date ticketLogDate) {
		this.ticketLogDate = ticketLogDate;
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
	public String getTicketLoggedUserID() {
		return ticketLoggedUserID;
	}
	public void setTicketLoggedUserID(String ticketLoggedUserID) {
		this.ticketLoggedUserID = ticketLoggedUserID;
	}
	public String getTicketLoggedUsername() {
		return ticketLoggedUsername;
	}
	public void setTicketLoggedUsername(String ticketLoggedUsername) {
		this.ticketLoggedUsername = ticketLoggedUsername;
	}
	public String getSpUserId() {
		return SpUserId;
	}
	public void setSpUserId(String spUserId) {
		SpUserId = spUserId;
	}
	public String getSpUserName() {
		return SpUserName;
	}
	public void setSpUserName(String spUserName) {
		SpUserName = spUserName;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
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
	public ResolvedByHoBean(String ticketNO, Date solvedDate, Date ticketLogDate, String problemCategory,
			String problemType, String problemItem, String problemSummary, String problemDescription,
			String ticketLoggedUserID, String ticketLoggedUsername, String spUserId, String spUserName, String role,
			String department, String status, String userOfficeCode, String priority, String severity,
			String customerGroupName, String callClassification, String spCallClassification) {
		super();
		this.ticketNO = ticketNO;
		this.solvedDate = solvedDate;
		this.ticketLogDate = ticketLogDate;
		this.problemCategory = problemCategory;
		this.problemType = problemType;
		this.problemItem = problemItem;
		this.problemSummary = problemSummary;
		this.problemDescription = problemDescription;
		this.ticketLoggedUserID = ticketLoggedUserID;
		this.ticketLoggedUsername = ticketLoggedUsername;
		SpUserId = spUserId;
		SpUserName = spUserName;
		this.role = role;
		this.department = department;
		this.status = status;
		this.userOfficeCode = userOfficeCode;
		this.priority = priority;
		this.severity = severity;
		this.customerGroupName = customerGroupName;
		this.callClassification = callClassification;
		this.spCallClassification = spCallClassification;
	}
	public ResolvedByHoBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	
	
}
