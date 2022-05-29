package com.tcs.umsapp.unlock.response;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class UserDetailDBResponseASBO extends UmsappResponseObject{

	private static final long serialVersionUID = 7902279814390303828L;
	
	private String userId;
	private String firstName;
	private String lastName;
	private String gender;
	private String email;
	private String mobile;
	private String statusCode;
	private String statusMessage;
   
	
	public String getStatusCode() {
    return statusCode;
}
public void setStatusCode(String statusCode) {
    this.statusCode = statusCode;
}
public String getStatusMessage() {
    return statusMessage;
}
public void setStatusMessage(String statusMessage) {
    this.statusMessage = statusMessage;
}
    public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	
	@Override
	public String toString() {
		return "userDetailDBResponseASBO [userId=" + userId + ", firstName="
				+ firstName + ", lastName=" + lastName + ", gender=" + gender
				+ ", email=" + email + ", mobile=" + mobile + "]";
	}
	
	
		
}
