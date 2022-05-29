/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LoginOAMOIDResponseASBO extends WebReportsResponseObject {
	private static final long serialVersionUID = -1338043422814824364L;
	 private String loggedIn;
	  private String memberOf;
	  private String token;
	  private String statusMessage;
	//  private String changePasswordFlag;
	  private int statusCode;
	  private String lastLoginDate;
	private String firstName;
	private String lastName;
	  private String errorCode;
	  private String errorMessage;
	  private String userId;
	  
	  


	  
	/*  public String getChangePasswordFlag()
	  {
	    return this.changePasswordFlag;
	  }

	  public void setChangePasswordFlag(String changePasswordFlag)
	  {
	    this.changePasswordFlag = changePasswordFlag;
	  }

		public String getChangePasswordNotify()
	  {
	    return this.changePasswordNotify;
	  }

	  public void setChangePasswordNotify(String changePasswordNotify)
	  {
	    this.changePasswordNotify = changePasswordNotify;
	  }*/
	
	  

	  /**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}



	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}



	/**
	 * @return the loggedIn
	 */
	public String getLoggedIn() {
		return loggedIn;
	}



	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}



	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	/**
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
	}



	/**
	 * @return the memberOf
	 */
	public String getMemberOf() {
		return memberOf;
	}



	/**
	 * @param memberOf the memberOf to set
	 */
	public void setMemberOf(String memberOf) {
		this.memberOf = memberOf;
	}



	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}



	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}



	/**
	 * @return the statusMessage
	 */
	public String getStatusMessage() {
		return statusMessage;
	}



	/**
	 * @param statusMessage the statusMessage to set
	 */
	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}



	/**
	 * @return the statusCode
	 */
	public int getStatusCode() {
		return statusCode;
	}



	/**
	 * @param statusCode the statusCode to set
	 */
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}



	/**
	 * @return the lastLoginDate
	 */
	public String getLastLoginDate() {
		return lastLoginDate;
	}



	/**
	 * @param lastLoginDate the lastLoginDate to set
	 */
	public void setLastLoginDate(String lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}



	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}



	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return errorCode;
	}



	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}



	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}



	/**
	 * @param errorMessage the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}



	public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LoginOAMOIDResponseASBO [loggedIn=");
	    builder.append(this.loggedIn);
	    builder.append(", memberOf=");
	    builder.append(this.memberOf);
	    builder.append(", token=");
	    builder.append(this.token);
	    builder.append(", statusMessage=");
	    builder.append(this.statusMessage);
	    builder.append(", firstName=");
	     builder.append(this.firstName);
	    builder.append("]");
	    return builder.toString();
	  }}
