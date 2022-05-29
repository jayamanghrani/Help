package com.tcs.employeeportal.asbo.emp.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

public class GetUserDetailsRequestASBO extends EmployeePortalRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5467156826631567736L;

	/** The user id. */
	private String userId;
	
	/** The channel. */
	private String channel;

	/**
	 * Gets User ID
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets UserId
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the channel
	 * @return channel
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Sets channel
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	
}

