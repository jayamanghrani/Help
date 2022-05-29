/**
 * 
 */
package com.tcs.webreports.asbo.report.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class ChannelReportsRequestASBO extends WebReportsRequestObject {

	private static final long serialVersionUID = 7468795663133647787L;
	
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
	    builder.append("ChannelReportsRequestASBO [sessionToken=");
	    builder.append(this.sessionToken);
	    builder.append("]");
	    return builder.toString();
	  }

}
