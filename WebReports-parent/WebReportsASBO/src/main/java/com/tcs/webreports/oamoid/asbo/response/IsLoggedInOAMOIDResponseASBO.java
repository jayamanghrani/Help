/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class IsLoggedInOAMOIDResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = -2422221266834571312L;
	
	 private boolean isLoggedIn;

	  public boolean isLoggedIn()
	  {
	    return this.isLoggedIn;
	  }

	  public void setLoggedIn(boolean isLoggedIn)
	  {
	    this.isLoggedIn = isLoggedIn;
	  }

	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("IsLoggedInOAMOIDResponseASBO [isLoggedIn=");
	    builder.append(this.isLoggedIn);
	    builder.append("]");
	    return builder.toString();
	  }
}
