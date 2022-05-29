package com.tcs.jira.asbo.request;

public class GetCreateJiraUserIssueRequestDetails {
	
	private String JiraUserName;
	private String JiraUserid;
	private String JiraUserPhonenumber;
	private String JiraUserLocation;
	private String JiraUserCompany;
	private String JiraUserOfficeCode;
	public String getJiraUserName() {
		return JiraUserName;
	}
	public void setJiraUserName(String jiraUserName) {
		JiraUserName = jiraUserName;
	}
	public String getJiraUserid() {
		return JiraUserid;
	}
	public void setJiraUserid(String jiraUserid) {
		JiraUserid = jiraUserid;
	}
	public String getJiraUserPhonenumber() {
		return JiraUserPhonenumber;
	}
	public void setJiraUserPhonenumber(String jiraUserPhonenumber) {
		JiraUserPhonenumber = jiraUserPhonenumber;
	}
	public String getJiraUserLocation() {
		return JiraUserLocation;
	}
	public void setJiraUserLocation(String jiraUserLocation) {
		JiraUserLocation = jiraUserLocation;
	}
	public String getJiraUserCompany() {
		return JiraUserCompany;
	}
	public void setJiraUserCompany(String jiraUserCompany) {
		JiraUserCompany = jiraUserCompany;
	}
	public String getJiraUserOfficeCode() {
		return JiraUserOfficeCode;
	}
	public void setJiraUserOfficeCode(String jiraUserOfficeCode) {
		JiraUserOfficeCode = jiraUserOfficeCode;
	}
	public GetCreateJiraUserIssueRequestDetails(String jiraUserName,
			String jiraUserid, String jiraUserPhonenumber,
			String jiraUserLocation, String jiraUserCompany,
			String jiraUserOfficeCode) {
		super();
		JiraUserName = jiraUserName;
		JiraUserid = jiraUserid;
		JiraUserPhonenumber = jiraUserPhonenumber;
		JiraUserLocation = jiraUserLocation;
		JiraUserCompany = jiraUserCompany;
		JiraUserOfficeCode = jiraUserOfficeCode;
	}
	public GetCreateJiraUserIssueRequestDetails() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "GetCreateJiraUserIssueRequestDetails [JiraUserName="
				+ JiraUserName + ", JiraUserid=" + JiraUserid
				+ ", JiraUserPhonenumber=" + JiraUserPhonenumber
				+ ", JiraUserLocation=" + JiraUserLocation
				+ ", JiraUserCompany=" + JiraUserCompany
				+ ", JiraUserOfficeCode=" + JiraUserOfficeCode + "]";
	}
	
	

}
