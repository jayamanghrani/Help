/**
 * 
 */
package com.tcs.webreports.access.transformers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.tcs.webreports.asbo.access.request.LoginRequestASBO;
import com.tcs.webreports.asbo.access.response.LoginResponseASBO;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.request.LoginOAMOIDRequestASBO;
import com.tcs.webreports.oamoid.asbo.response.LoginOAMOIDResponseASBO;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

/**
 * @author 738566
 *
 */
public class LoginTransformer {
	private static final Logger LOGGER = Logger
			.getLogger(LoginTransformer.class);
	 private LoginRequestASBO loginRequestASBO;
	  private LoginResponseASBO loginResponseASBO;
	  private LoginOAMOIDRequestASBO loginOAMOIDRequestASBO;
	  private LoginOAMOIDResponseASBO loginOAMOIDResponseASBO;
	 /* LoginService loginService;*/

	  public LoginTransformer()
	  {
	    this.loginOAMOIDRequestASBO = new LoginOAMOIDRequestASBO();
	    this.loginResponseASBO = new LoginResponseASBO();
	    /*this.loginService = new LoginServiceImpl();*/
	  }

	  public WebReportsRequestObject transformRequest(LoginRequestASBO loginRequestASBO)
	    throws IntegrationTransformationException
	  {
	    this.loginRequestASBO = loginRequestASBO;
	    this.loginOAMOIDRequestASBO.setHeader(this.loginRequestASBO.getHeader());

	    transformRequest();
	    return this.loginOAMOIDRequestASBO;
	  }

	  private void transformRequest()
	  {
	    this.loginOAMOIDRequestASBO.setPassword(this.loginRequestASBO.getPassword());

	    this.loginOAMOIDRequestASBO.setUserId(null != this.loginRequestASBO.getUserId() ? this.loginRequestASBO.getUserId().trim() : "");
	  }

	  public WebReportsResponseObject transformResponse(LoginOAMOIDResponseASBO loginOAMOIDResponseASBO)
	    throws IntegrationTransformationException
	  {
	    this.loginOAMOIDResponseASBO = loginOAMOIDResponseASBO;
	    LOGGER.info("this.loginOAMOIDResponseASBO.getHeader() - "+this.loginOAMOIDResponseASBO.getHeader());

	    this.loginResponseASBO.setHeader(this.loginOAMOIDResponseASBO.getHeader());
	    
	    transformResponse();
	    return this.loginResponseASBO;
	  }

	  private void transformResponse()
	  {
	    if ((null == this.loginOAMOIDResponseASBO.getLoggedIn()) && (null == this.loginOAMOIDResponseASBO.getToken()))
	    {
	      this.loginResponseASBO.setStatusMessage("Some Error occured. Please try after some time!!");
	    }
	    else {
	      this.loginResponseASBO.setLoggedIn(this.loginOAMOIDResponseASBO.getLoggedIn());
	      this.loginResponseASBO.setToken(this.loginOAMOIDResponseASBO.getToken());
	      this.loginResponseASBO.setLastLoginDate(this.loginOAMOIDResponseASBO.getLastLoginDate());
	      if (null != this.loginOAMOIDResponseASBO.getMemberOf())
	      {
	        if (this.loginOAMOIDResponseASBO.getMemberOf().contains(",")) {
	          String[] parts = this.loginOAMOIDResponseASBO.getMemberOf().split(",");
	          parts = parts[0].split("=");
	          this.loginResponseASBO.setChannel(parts[1]);
	          this.loginResponseASBO.setLastName(this.loginOAMOIDResponseASBO.getLastName());
	        	this.loginResponseASBO.setFirstName(this.loginOAMOIDResponseASBO.getFirstName());
	        	this.loginResponseASBO.setUserId(this.loginOAMOIDResponseASBO.getUserId());
	        }else
	        {
	        	this.loginResponseASBO.setLastName(this.loginOAMOIDResponseASBO.getLastName());
	        	this.loginResponseASBO.setFirstName(this.loginOAMOIDResponseASBO.getFirstName());
	        	this.loginResponseASBO.setUserId(this.loginOAMOIDResponseASBO.getUserId());
	        }
	      }
	      this.loginResponseASBO.setStatusMessage(this.loginOAMOIDResponseASBO.getStatusMessage());
	      this.loginResponseASBO.setStatusCode(this.loginOAMOIDResponseASBO.getStatusCode());
	    }
	    this.loginResponseASBO.setpRetError(this.loginOAMOIDResponseASBO.getErrorMessage());
	    this.loginResponseASBO.setpRetCode(this.loginOAMOIDResponseASBO.getErrorCode());

	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
	    Date date = new Date();
	    this.loginResponseASBO.setSystemDate(dateFormat.format(date));
	  }

	/*  public WebReportsRequestObject transformForUserProfile(LoginRequestASBO loginRequestASBO)
	    throws IntegrationTransformationException
	  {
	    GetNCProfileDetailsDBRequestASBO dbRequestASBO = new GetNCProfileDetailsDBRequestASBO();
	    dbRequestASBO.setUserId(null != loginRequestASBO.getUserId() ? loginRequestASBO.getUserId().toUpperCase() : "");
	    dbRequestASBO.setHeader(loginRequestASBO.getHeader());
	    return dbRequestASBO;
	  }

	  public WebReportsRequestObject transformResponseForUserProfile(GetNCProfileDetailsDBResponseASBO dbResponseASBO)
	    throws IntegrationTransformationException
	  {
	    GetChannelRoleBasedDataRequestASBO getChannelRoleBasedDataRequestASBO = null;
	    if (null != this.loginResponseASBO) {
	      getChannelRoleBasedDataRequestASBO = new GetChannelRoleBasedDataRequestASBO();
	      UserProfile userProfile = new UserProfile();
	      Relation relation = null;
	      Address address = null;
	      List addressList = null;

	      if (null != dbResponseASBO) {
	        relation = new Relation();
	        address = new Address();
	        addressList = new ArrayList();

	        userProfile.setFirstName(dbResponseASBO.getFirstName());
	        userProfile.setLastName(dbResponseASBO.getLastName());
	        userProfile.setUserId(null != dbResponseASBO.getUserId() ? dbResponseASBO.getUserId().toUpperCase() : "");
	        userProfile.setDob(TransformBOUtil.convertDateToString(dbResponseASBO.getDob()));
	        userProfile.setGender(dbResponseASBO.getGender());

	        address.setEmailId1(dbResponseASBO.getEmailId1());
	        address.setEmailId2(dbResponseASBO.getEmailId2());
	        address.setMobileNumber1(dbResponseASBO.getMobileNo1());
	        address.setMobileNumber2(dbResponseASBO.getMobileNo2());

	        addressList.add(address);
	        relation.setAddresses(addressList);
	        userProfile.setRelation(relation);
	        userProfile.setChannel(dbResponseASBO.getChannelCd());

	        if ((null != this.loginRequestASBO.getHeader()) && (null != this.loginRequestASBO.getHeader().getApplicationId()) && 
	          ("MOBILE".equalsIgnoreCase(this.loginRequestASBO.getHeader().getApplicationId()))) {
	          userProfile.setLoggedInRole(null != PropertiesUtil.getConfigData("DEFAULT_ROLE") ? PropertiesUtil.getConfigData("DEFAULT_ROLE") : "SUPERUSER");
	        }

	        userProfile.setStakeCode(dbResponseASBO.getStakeCode());
	        userProfile.setUserId(null != this.loginRequestASBO.getUserId() ? this.loginRequestASBO.getUserId().toUpperCase() : "");
	        userProfile.setUiFlow(dbResponseASBO.getUiFlow());

	        getChannelRoleBasedDataRequestASBO.setChannelCd(dbResponseASBO.getChannelCd());
	        getChannelRoleBasedDataRequestASBO.setRoleCdMap(dbResponseASBO.getRoleCd());
	        getChannelRoleBasedDataRequestASBO.setHeader(dbResponseASBO.getHeader());

	        this.loginResponseASBO.setBranchCode(dbResponseASBO.getBranchCode());

	        this.loginResponseASBO.setMarineOnOrAboutDepartureDate(dbResponseASBO.getMarineDepartureDate());
	      }

	      this.loginResponseASBO.setUserProfile(userProfile);
	    }

	    return getChannelRoleBasedDataRequestASBO;
	  }
*/
}
