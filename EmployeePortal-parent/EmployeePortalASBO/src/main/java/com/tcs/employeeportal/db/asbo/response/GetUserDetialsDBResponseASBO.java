package com.tcs.employeeportal.db.asbo.response;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class GetUserDetialsDBResponseASBO extends EmployeePortalResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1404382221552805066L;

	/** The branch. */
	private String branch;
	
	/** The mobile number. */
	private String mobileNumber;
	
	/** The email id. */
	private String emailId;
	
	/** The user id. */
	private String userId;

	/**
	 * @return
	 */
	public String getBranch() {
		return branch;
	}

	/**
	 * @param branch
	 */
	public void setBranch(String branch) {
		this.branch = branch;
	}

	/**
	 * @return
	 */
	public String getMobileNumber() {
		return mobileNumber;
	}

	/**
	 * @param mobileNumber
	 */
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/**
	 * @return
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	
}
