package com.tcs.umsoid.request;

import com.tcs.umsoid.vo.cmo.UmsappResponseObject;

public class UserLdapDetailRequestASBO extends UmsappResponseObject{

	private static final long serialVersionUID = -4560956297841902460L;

	private String userID;
	private String requestID;
	private String provisionID;
	
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getProvisionID() {
		return provisionID;
	}
	public void setProvisionID(String provisionID) {
		this.provisionID = provisionID;
	}
	
	
	
	
	
	



}
