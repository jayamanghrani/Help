package com.tcs.jira.asbo.response;

public class JiraGetUserIssueCreateResponseBean {
	
	private String IssueID;
	private String IssueKeyNumber;
	

	public JiraGetUserIssueCreateResponseBean() {
		super();
		
	}


	public String getIssueID() {
		return IssueID;
	}


	public void setIssueID(String issueID) {
		IssueID = issueID;
	}


	public String getIssueKeyNumber() {
		return IssueKeyNumber;
	}


	public void setIssueKeyNumber(String issueKeyNumber) {
		IssueKeyNumber = issueKeyNumber;
	}


	public JiraGetUserIssueCreateResponseBean(String issueID, String issueKeyNumber) {
		super();
		IssueID = issueID;
		IssueKeyNumber = issueKeyNumber;
	}
	
	

}
