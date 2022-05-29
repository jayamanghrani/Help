package com.tcs.jira.asbo.request;

public class GetJiraUserRequestDetails {
	
	private String username;
	private String emailAddress;
	private String UserFullName;
	private String branch;
	private String city;
	private String phone;
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmailAddress() {
		return emailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
	public String getUserFullName() {
		return UserFullName;
	}
	public void setUserFullName(String userFullName) {
		UserFullName = userFullName;
	}
	public GetJiraUserRequestDetails() {
		super();
		
	}
	public GetJiraUserRequestDetails(String username,
			String emailAddress, String userFullName) {
		super();
	}
	
	
	public String getBranch() {
        return branch;
    }
    public void setBranch(String branch) {
        this.branch = branch;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Override
	public String toString() {
		return "GetCreateJiraUserRequestDetailsASBO [username=" + username
				+ ", emailAddress=" + emailAddress + ", UserFullName="
				+ UserFullName + "]";
	}

	
}
