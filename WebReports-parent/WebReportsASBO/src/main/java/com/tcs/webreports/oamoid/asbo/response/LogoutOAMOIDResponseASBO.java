/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LogoutOAMOIDResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = -8211346000054843863L;
	
	  private String message;

	  public String getMessage()
	  {
	    return this.message;
	  }

	  public void setMessage(String message)
	  {
	    this.message = message;
	  }

	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LogoutOAMOIDResponseASBO [message=");
	    builder.append(this.message);
	    builder.append("]");
	    return builder.toString();
	  }

}
