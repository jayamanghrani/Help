/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class GetChannelOAMOIDRequestASBO extends WebReportsRequestObject{

	private static final long serialVersionUID = -7457516988603384200L;
	
	private String sessionToken;

	/**
	 * @return the sessionToken
	 */
	 public String getSessionToken()
	  {
	    return this.sessionToken;
	  }


	/**
	 * @param sessionToken the sessionToken to set
	 */
	  public void setSessionToken(String sessionToken)
	  {
	    this.sessionToken = sessionToken;
	  }

	  
	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("GetChannelOAMOIDRequestASBO [sessionToken=");
	    builder.append(this.sessionToken);
	    builder.append("]");
	    return builder.toString();
	  }
	
	
	

}
