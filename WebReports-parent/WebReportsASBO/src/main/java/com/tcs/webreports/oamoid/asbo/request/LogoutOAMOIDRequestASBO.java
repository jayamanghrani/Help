/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class LogoutOAMOIDRequestASBO extends WebReportsRequestObject{

	private static final long serialVersionUID = -3065220035371893442L;
	private String sessionToken;

	  public String getSessionToken()
	  {
	    return this.sessionToken;
	  }

	  public void setSessionToken(String sessionToken)
	  {
	    this.sessionToken = sessionToken;
	  }

	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LogoutOAMOIDRequestASBO [sessionToken=");
	    builder.append(this.sessionToken);
	    builder.append("]");
	    return builder.toString();
	  }
	  
}
