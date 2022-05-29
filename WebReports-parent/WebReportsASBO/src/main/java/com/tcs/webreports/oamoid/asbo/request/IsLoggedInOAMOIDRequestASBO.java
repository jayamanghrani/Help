/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class IsLoggedInOAMOIDRequestASBO extends WebReportsRequestObject{

	private static final long serialVersionUID = 9021757431706424341L;
	private String sessionToken;
	  private String userId;

	  public String getSessionToken()
	  {
	    return this.sessionToken;
	  }

	  public void setSessionToken(String sessionToken)
	  {
	    this.sessionToken = sessionToken;
	  }

	  public String getUserId()
	  {
	    return this.userId;
	  }

	  public void setUserId(String userId)
	  {
	    this.userId = userId;
	  }

	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("IsLoggedInOAMOIDRequestASBO [sessionToken=");
	    builder.append(this.sessionToken);
	    builder.append(", userId=");
	    builder.append(this.userId);
	    builder.append("]");
	    return builder.toString();
	  }

}
