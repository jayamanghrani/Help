package com.tcs.employeeportal.db.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

public class GetUserDetailsDBRequestASBO extends EmployeePortalRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 968669483344076964L;

	/** The user id. */
	private String userId;
	
	/** The channel. */
	private String channel;

	/**
	 * Gets UserId
	 * @return userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets User Id
	 * @param userId
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets Channel
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
