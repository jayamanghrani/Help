/**
 * 
 */
package com.tcs.webreports.asbo.access.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class IsLoggedInResponseASBO extends WebReportsResponseObject{

	private static final long serialVersionUID = 6500393527694359690L;
	private boolean isLoggedIn;
	
	  /**
	 * @return the isLoggedIn
	 */
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	/**
	 * @param isLoggedIn the isLoggedIn to set
	 */
	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("IsLoggedInResponseASBO [isLoggedIn=");
	    builder.append(this.isLoggedIn);
	    builder.append("]");
	    return builder.toString();
	  }
	  
}
