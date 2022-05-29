/**
 * 
 */
package com.tcs.webreports.oamoid.asbo.response;

import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class ForgotPasswordOAMOIDResponseASBO extends WebReportsResponseObject {

	private static final long serialVersionUID = -2857737249079631804L;
	private String statusCode;
	  private String statusMessage;
	  private String userId;
	  private String password;

	  public String getStatusCode()
	  {
	    return this.statusCode;
	  }

	  public void setStatusCode(String statusCode)
	  {
	    this.statusCode = statusCode;
	  }

	  public String getStatusMessage()
	  {
	    return this.statusMessage;
	  }

	  public void setStatusMessage(String statusMessage)
	  {
	    this.statusMessage = statusMessage;
	  }

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
