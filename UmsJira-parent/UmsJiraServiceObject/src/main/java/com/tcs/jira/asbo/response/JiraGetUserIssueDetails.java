package com.tcs.jira.asbo.response;

public class JiraGetUserIssueDetails {
	
	private String JiraUser_ID;
	private String JiraUser_PhoneNumber;
	private String JiraUser_Location;
	private String JiraUser_Company;
	private String JiraUser_OfficeCode;
	private String errorStatus;
	public String getJiraUser_ID() {
		return JiraUser_ID;
	}
	public void setJiraUser_ID(String jiraUser_ID) {
		JiraUser_ID = jiraUser_ID;
	}
	public String getJiraUser_PhoneNumber() {
		return JiraUser_PhoneNumber;
	}
	public void setJiraUser_PhoneNumber(String jiraUser_PhoneNumber) {
		JiraUser_PhoneNumber = jiraUser_PhoneNumber;
	}
	public String getJiraUser_Location() {
		return JiraUser_Location;
	}
	public void setJiraUser_Location(String jiraUser_Location) {
		JiraUser_Location = jiraUser_Location;
	}
	public String getJiraUser_Company() {
		return JiraUser_Company;
	}
	public void setJiraUser_Company(String jiraUser_Company) {
		JiraUser_Company = jiraUser_Company;
	}
	public String getJiraUser_OfficeCode() {
		return JiraUser_OfficeCode;
	}
	public void setJiraUser_OfficeCode(String jiraUser_OfficeCode) {
		JiraUser_OfficeCode = jiraUser_OfficeCode;
	}
	public String getErrorStatus() {
		return errorStatus;
	}
	public void setErrorStatus(String errorStatus) {
		this.errorStatus = errorStatus;
	}
	public JiraGetUserIssueDetails(String jiraUser_ID,
			String jiraUser_PhoneNumber, String jiraUser_Location,
			String jiraUser_Company, String jiraUser_OfficeCode,
			String errorStatus) {
		super();
		JiraUser_ID = jiraUser_ID;
		JiraUser_PhoneNumber = jiraUser_PhoneNumber;
		JiraUser_Location = jiraUser_Location;
		JiraUser_Company = jiraUser_Company;
		JiraUser_OfficeCode = jiraUser_OfficeCode;
		this.errorStatus = errorStatus;
	}
	public JiraGetUserIssueDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "JiraGetUserIssueDetails [JiraUser_ID=" + JiraUser_ID
				+ ", JiraUser_PhoneNumber=" + JiraUser_PhoneNumber
				+ ", JiraUser_Location=" + JiraUser_Location
				+ ", JiraUser_Company=" + JiraUser_Company
				+ ", JiraUser_OfficeCode=" + JiraUser_OfficeCode
				+ ", errorStatus=" + errorStatus + "]";
	}
	

	

}
