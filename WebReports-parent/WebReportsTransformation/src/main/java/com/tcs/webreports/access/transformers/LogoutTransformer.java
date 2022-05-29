/**
 * 
 */
package com.tcs.webreports.access.transformers;

import com.tcs.webreports.asbo.access.request.LogoutRequestASBO;
import com.tcs.webreports.asbo.access.response.LogoutResponseASBO;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.request.LogoutOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.response.LogoutOAMOIDResponseASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LogoutTransformer {
	
	 private LogoutRequestASBO logoutRequestASBO;
	  private LogoutResponseASBO logoutResponseASBO;
	  private LogoutOAMOIDRequestASBO logoutOAMOIDRequestASBO;
	  private LogoutOAMOIDResponseASBO logoutOAMOIDResponseASBO;

	  public LogoutTransformer()
	  {
	    this.logoutOAMOIDRequestASBO = new LogoutOAMOIDRequestASBO();
	    this.logoutResponseASBO = new LogoutResponseASBO();
	  }

	  public WebReportsRequestObject transformRequest(LogoutRequestASBO logoutRequestASBO)
	    throws IntegrationTransformationException
	  {
	    this.logoutRequestASBO = logoutRequestASBO;

	    this.logoutOAMOIDRequestASBO.setHeader(this.logoutRequestASBO.getHeader());

	    this.logoutOAMOIDRequestASBO.setSessionToken(this.logoutRequestASBO.getSessionToken());

	    return this.logoutOAMOIDRequestASBO;
	  }

	  public WebReportsResponseObject transformResponse(LogoutOAMOIDResponseASBO logoutOAMOIDResponseASBO)
	    throws IntegrationTransformationException
	  {
	    this.logoutOAMOIDResponseASBO = logoutOAMOIDResponseASBO;
	    this.logoutResponseASBO.setHeader(this.logoutOAMOIDResponseASBO.getHeader());

	    this.logoutResponseASBO.setMessage(this.logoutOAMOIDResponseASBO.getMessage());

	    return this.logoutResponseASBO;
	  }

}
