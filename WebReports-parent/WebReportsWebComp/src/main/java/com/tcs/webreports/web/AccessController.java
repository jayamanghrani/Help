/**
 * 
 */
package com.tcs.webreports.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.webreports.asbo.access.request.LoginRequestASBO;
import com.tcs.webreports.asbo.access.request.LogoutRequestASBO;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.integration.controller.AccessIntegrationController;
import com.tcs.webreports.security.util.HttpHeaderUtil;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.util.UtilConstants;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;
import com.tcs.webreports.security.util.*;


/**
 * @author 738566
 *
 */
@Component 
@RestController
@RequestMapping("/access")

public class AccessController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AccessController.class);

	@RequestMapping(value = "/{player}", method = RequestMethod.GET)
	public String getMsg(@PathVariable String player) {

		return " Hello";
	}

	/**
	 * 
	 * @param httpServletRequest
	 * @param loginRequestASBO
	 * @return
	 */
	  @RequestMapping(value={"/login"}, method={RequestMethod.POST})
	 
	  public ResponseEntity<WebReportsResponseObject> login(HttpServletRequest httpServletRequest,@RequestBody LoginRequestASBO loginRequestASBO)
	  {
		  
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");
	    LOGGER.info("httpHeaderProperty--"+httpHeaderProperty);
	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	   try {
	    	loginRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, UtilConstants.LOGIN));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException)
	    {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity<WebReportsResponseObject>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity<WebReportsResponseObject>(new AccessIntegrationController().processLogin(loginRequestASBO), HttpStatus.OK);
	  }

	 /**
	  * 
	  * @param logoutRequestASBO
	  * @param httpServletRequest
	  * @return
	  */
	  @RequestMapping(value={"/logout"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ResponseEntity<WebReportsResponseObject> logout(@RequestBody LogoutRequestASBO logoutRequestASBO, HttpServletRequest httpServletRequest)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    try {
	      logoutRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, UtilConstants.LOGOUT));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException) {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity<WebReportsResponseObject>(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

//	    logoutRequestASBO.setSessionToken(httpServletRequest.getHeader("X-Auth-Token"));

	    return new ResponseEntity<WebReportsResponseObject>(new AccessIntegrationController().processLogout(logoutRequestASBO), HttpStatus.OK);
	  }

	/*  @RequestMapping(value={"/validateUser"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ResponseEntity<WebReportsResponseObject> validateUser(@RequestBody ValidateUserRequestASBO validateUserRequestASBO, HttpServletRequest httpServletRequest)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    try
	    {
	      validateUserRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, "validateUser"));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException) {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity(new SecurityIntegrationController().processValidateUser(validateUserRequestASBO), HttpStatus.OK);
	  }

	  @RequestMapping(value={"/forgotPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ResponseEntity<WebReportsResponseObject> forgotPassword(@RequestBody ForgotPasswordRequestASBO forgotPasswordRequestASBO, HttpServletRequest httpServletRequest)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    try {
	      forgotPasswordRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, "forgotPassword"));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException) {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity(new SecurityIntegrationController().processForgotPassword(forgotPasswordRequestASBO), HttpStatus.OK);
	  }

	  @RequestMapping(value={"/generateOtpForgotPassword"}, method={org.springframework.web.bind.annotation.RequestMethod.POST})
	  public ResponseEntity<WebReportsResponseObject> validateForgotPassword(@RequestBody ForgotPasswordRequestASBO forgotPasswordRequestASBO, HttpServletRequest httpServletRequest)
	  {
	    HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty)httpServletRequest.getAttribute("HTTP_HEADERS");

	    HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
	    try {
	      forgotPasswordRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(httpHeaderProperty, "validateForgotPassword"));
	    }
	    catch (IntegrationTechnicalException integrationTechnicalException) {
	      ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	      return new ResponseEntity(errorVO, HttpStatus.INTERNAL_SERVER_ERROR);
	    }

	    return new ResponseEntity(new SecurityIntegrationController().generateOtpForForgotPassword(forgotPasswordRequestASBO), HttpStatus.OK);
	  }

*/
}
