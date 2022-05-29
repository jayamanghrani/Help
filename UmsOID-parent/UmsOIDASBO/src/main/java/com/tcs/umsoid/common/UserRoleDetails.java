package com.tcs.umsoid.common;

public class UserRoleDetails {
	   private String applicationRoleCode;
	   private String userId;
	   
	   
	   
	public UserRoleDetails(String applicationRoleCode, String userId) {
		super();
		this.applicationRoleCode = applicationRoleCode;
		this.userId = userId;
	}
	public String getApplicationRoleCode() {
		return applicationRoleCode;
	}
	public void setApplicationRoleCode(String applicationRoleCode) {
		this.applicationRoleCode = applicationRoleCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "UserRoleDetails [applicationRoleCode=" + applicationRoleCode
				+ ", userId=" + userId + "]";
	}

	


}
