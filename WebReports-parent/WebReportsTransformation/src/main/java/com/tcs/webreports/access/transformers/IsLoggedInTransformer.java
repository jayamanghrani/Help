/**
 * 
 */
package com.tcs.webreports.access.transformers;

import com.tcs.webreports.asbo.access.request.IsLoggedInRequestASBO;
import com.tcs.webreports.asbo.access.response.IsLoggedInResponseASBO;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.request.IsLoggedInOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.response.IsLoggedInOAMOIDResponseASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class IsLoggedInTransformer {
	  private IsLoggedInRequestASBO isLoggedInRequestASBO;
	  private IsLoggedInResponseASBO isLoggedInResponseASBO;
	  private IsLoggedInOAMOIDRequestASBO isLoggedInOAMOIDRequestASBO;
	  private IsLoggedInOAMOIDResponseASBO isLoggedInOAMOIDResponseASBO;

	  public IsLoggedInTransformer()
	  {
	    this.isLoggedInOAMOIDRequestASBO = new IsLoggedInOAMOIDRequestASBO();
	    this.isLoggedInResponseASBO = new IsLoggedInResponseASBO();
	  }

	  public WebReportsRequestObject transformRequest(IsLoggedInRequestASBO isLoggedInRequestASBO)
	    throws IntegrationTransformationException
	  {
	    this.isLoggedInRequestASBO = isLoggedInRequestASBO;

	    this.isLoggedInOAMOIDRequestASBO.setHeader(this.isLoggedInRequestASBO.getHeader());

	    this.isLoggedInOAMOIDRequestASBO.setSessionToken(this.isLoggedInRequestASBO.getSessionToken());

	    this.isLoggedInOAMOIDRequestASBO.setUserId(this.isLoggedInRequestASBO.getUserId());

	    return this.isLoggedInOAMOIDRequestASBO;
	  }

	  public WebReportsResponseObject transformResponse(IsLoggedInOAMOIDResponseASBO isLoggedInOAMOIDResponseASBO)
	    throws IntegrationTransformationException
	  {
	    this.isLoggedInOAMOIDResponseASBO = isLoggedInOAMOIDResponseASBO;

	    this.isLoggedInResponseASBO.setHeader(this.isLoggedInOAMOIDResponseASBO.getHeader());

	    this.isLoggedInResponseASBO.setLoggedIn(this.isLoggedInOAMOIDResponseASBO.isLoggedIn());

	    return this.isLoggedInResponseASBO;
	  }

}
