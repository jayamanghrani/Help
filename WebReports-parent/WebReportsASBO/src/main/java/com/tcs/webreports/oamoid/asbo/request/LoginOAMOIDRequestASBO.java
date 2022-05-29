/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;

/**
 * @author 738566
 *
 */
public class LoginOAMOIDRequestASBO extends WebReportsRequestObject{

	private static final long serialVersionUID = -1699588771600476430L;
	private String userId;
	  private String password;
	 /* private String pwdExpiryValue;
	  private String pwdExpiryNotification;*/

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

	  public String toString()
	  {
	    StringBuilder builder = new StringBuilder();
	    builder.append("LoginOAMOIDResqusetASBO [userId=");
	    builder.append(this.userId);
	    builder.append(", password=");
	    builder.append(this.password);
	    builder.append("]");
	    return builder.toString();
	  }

	/*  public String getPwdExpiryValue()
	  {
	    return this.pwdExpiryValue;
	  }

	  public void setPwdExpiryValue(String pwdExpiryValue)
	  {
	    this.pwdExpiryValue = pwdExpiryValue;
	  }

	  public String getPwdExpiryNotification()
	  {
	    return this.pwdExpiryNotification;
	  }

	  public void setPwdExpiryNotification(String pwdExpiryNotification)
	  {
	    this.pwdExpiryNotification = pwdExpiryNotification;
	  }*/
	

}
