/**
 * 
 */
package com.tcs.webreports.asbo.access.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LogoutResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = 5109883009950233008L;
	 private String message;

	  /**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LogoutResponseASBO [message=");
	    builder.append(this.message);
	    builder.append("]");
	    return builder.toString();
	  }
}
