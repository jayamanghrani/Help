/**
 * 
 */
package com.tcs.webreports.asbo.access.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class LogoutRequestASBO extends WebReportsRequestObject{

	private static final long serialVersionUID = -1541262479864196293L;
	private String sessionToken;
	  /**
	 * @return the sessionToken
	 */
	public String getSessionToken() {
		return sessionToken;
	}

	/**
	 * @param sessionToken the sessionToken to set
	 */
	public void setSessionToken(String sessionToken) {
		this.sessionToken = sessionToken;
	}

	public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LogoutRequestASBO [sessionToken=");
	    builder.append(this.sessionToken);
	    builder.append("]");
	    return builder.toString();
	  }

}