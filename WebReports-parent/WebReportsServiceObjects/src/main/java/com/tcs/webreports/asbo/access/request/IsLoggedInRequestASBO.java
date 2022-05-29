package com.tcs.webreports.asbo.access.request;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 * 
 */
public class IsLoggedInRequestASBO extends WebReportsRequestObject {
	private static final long serialVersionUID = -388485882544244225L;
	private String sessionToken;
	private String userId;

	/**
	 * @return the sessionToken
	 */
	public String getSessionToken() {
		return sessionToken;
	}

	/**
	 * @param sessionToken
	 *            the sessionToken to set
	 */
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("IsLoggedInRequestASBO [sessionToken=");
		builder.append(this.sessionToken);
		builder.append(", userId=");
		builder.append(this.userId);
		builder.append("]");
		return builder.toString();
	}
}