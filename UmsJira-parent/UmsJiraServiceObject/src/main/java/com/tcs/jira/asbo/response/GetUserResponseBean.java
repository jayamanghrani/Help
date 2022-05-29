package com.tcs.jira.asbo.response;

public class GetUserResponseBean {
  private 	String UserId;
  private 	String UserEmailAddress;
  private 	String UserFullName;
  private	String UserActiveStatus;
  private   String ErrorMessage;
	

	public GetUserResponseBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GetUserResponseBean(String userId, String userEmailAddress,
			String userFullName, String userActiveStatus,String errorMessage) {
		super();
		UserId = userId;
		UserEmailAddress = userEmailAddress;
		UserFullName = userFullName;
		UserActiveStatus = userActiveStatus;
		ErrorMessage=errorMessage;
	}
	public String getUserId() {
		return UserId;
	}
	public void setUserId(String userId) {
		UserId = userId;
	}
	public String getUserEmailAddress() {
		return UserEmailAddress;
	}
	public void setUserEmailAddress(String userEmailAddress) {
		UserEmailAddress = userEmailAddress;
	}
	public String getUserFullName() {
		return UserFullName;
	}
	public void setUserFullName(String userFullName) {
		UserFullName = userFullName;
	}
	public String getUserActiveStatus() {
		return UserActiveStatus;
	}
	public void setUserActiveStatus(String userActiveStatus) {
		UserActiveStatus = userActiveStatus;
	}
	
	
	public String getErrorMessage() {
		return ErrorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	@Override
	public String toString() {
		return "JiraGetUserResponseBean [UserId=" + UserId
				+ ", UserEmailAddress=" + UserEmailAddress + ", UserFullName="
				+ UserFullName + ", UserActiveStatus=" + UserActiveStatus
				+ ", ErrorMessage=" + ErrorMessage + "]";
	}
	
	
	
	

}
