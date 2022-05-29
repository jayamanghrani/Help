/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class ForgotPasswordOAMOIDRequestASBO extends WebReportsRequestObject {

	private static final long serialVersionUID = 3988355206386213557L;
	 private String userId;
	  private String password;

	  public String getUserId()
	  {
	    return this.userId;
	  }

	  public void setUserId(String userId)
	  {
	    this.userId = userId;
	  }

	  public String getPassword()
	  {
	    return this.password;
	  }

	  public void setPassword(String password)
	  {
	    this.password = password;
	  }

}
