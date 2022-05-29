/**
 * 
 */
package com.tcs.webreports.asbo.access.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LoginResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = -6796320950931517646L;
	  private String loggedIn;
	  private String channel;
	  private String token;
	  private String statusMessage;
	  private String userId;
	  private String firstName;
	  private String lastName;
	  /*private String changePasswordFlag;*/
	  private int statusCode;
	  private String lastLoginDate;
	 /* private String passwordExpiryNotify;
	  private String city;
	  private String branchCode;*/
	  private String pRetError;
	  private String pRetCode;
	/*  private String channelEmailId;*/
	  private String systemDate;
	  
	  
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
	 * @return the channel
	 */
	public String getChannel() {
		return channel;
	}
	/**
	 * @param channel the channel to set
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
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
	 * @param loggedIn the loggedIn to set
	 */
	public void setLoggedIn(String loggedIn) {
		this.loggedIn = loggedIn;
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
	 * @return the pRetError
	 */
	public String getpRetError() {
		return pRetError;
	}
	/**
	 * @param pRetError the pRetError to set
	 */
	public void setpRetError(String pRetError) {
		this.pRetError = pRetError;
	}
	/**
	 * @return the pRetCode
	 */
	public String getpRetCode() {
		return pRetCode;
	}
	/**
	 * @param pRetCode the pRetCode to set
	 */
	public void setpRetCode(String pRetCode) {
		this.pRetCode = pRetCode;
	}
	/**
	 * @return the systemDate
	 */
	public String getSystemDate() {
		return systemDate;
	}
	/**
	 * @param systemDate the systemDate to set
	 */
	public void setSystemDate(String systemDate) {
		this.systemDate = systemDate;
	}

	 public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LoginResponseASBO [loggedIn=");
	    builder.append(this.loggedIn);
	    builder.append(", channel=");
	    builder.append(this.channel);
	    builder.append(", token=");
	    builder.append(this.token);
	    builder.append(", statusMessage=");
	    builder.append(this.statusMessage);
	    builder.append(", userId=");
	    builder.append(this.userId);
	    builder.append(", firstName=");
	    builder.append(this.firstName);
	    builder.append(", lastName=");
	    builder.append(this.lastName);
	    builder.append(", lastLoginDate=");
	    builder.append(this.lastLoginDate);
	    /*builder.append(", changePasswordFlag=");
	    builder.append(this.changePasswordFlag);*/
	    builder.append("]");
	    return builder.toString();
	  }
}
