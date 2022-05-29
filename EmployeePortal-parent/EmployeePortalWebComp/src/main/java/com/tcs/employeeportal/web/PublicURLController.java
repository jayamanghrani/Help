/**
 * 
 */
package com.tcs.employeeportal.web;

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

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.employeeportal.asbo.emp.request.GetUserDetailsRequestASBO;
import com.tcs.employeeportal.asbo.login.request.ForgotPasswordRequestASBO;
import com.tcs.employeeportal.web.util.HttpHeaderProperty;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.controller.AlfrescoIntegrationController;
import com.tcs.employeeportal.controller.LoginIntegrationController;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;

/**
 * @author 738566
 *
 */
@Component 
@RestController
@RequestMapping("/public")

public class PublicURLController {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	
	@RequestMapping(value = "/{player}", method = RequestMethod.GET)
	public String getMsg(@PathVariable String player) {

		return " ";
	}

	/**
	 * Gets the carousel.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the carousel
	 */
	@RequestMapping(value = "/getCarousel", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getCarousel(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.info("getCarousel service invoked");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeader(/*httpHeaderProperty,*/UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("getCarousel service completed");
		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processCarousel(getContentRequestASBO),
				HttpStatus.OK);
	}

	/**
	 * Gets the latestUpdates.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the carousel
	 */
	@RequestMapping(value = "/getLatestUpdates", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getLatestUpdates(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.info("getLatestUpdates service invoked");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeader(/*httpHeaderProperty,*/UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("getLatestUpdates service completed");
		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processlatestUpdates(getContentRequestASBO),
				HttpStatus.OK);
	}

	
	/**
	 * Forgot password.
	 *
	 * @param forgotPasswordRequestASBO the forgot password request asbo
	 * @param httpServletRequest the http servlet request
	 * @return the response entity
	 */
	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> forgotPassword(
			@RequestBody ForgotPasswordRequestASBO forgotPasswordRequestASBO,
			HttpServletRequest httpServletRequest) {
		HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty) httpServletRequest
				.getAttribute("HTTP_HEADERS");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			forgotPasswordRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(UtilConstants.FORGOT_PASSWORD));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<EmployeePortalResponseObject>(
				new LoginIntegrationController()
				.processForgotPassword(forgotPasswordRequestASBO),
				HttpStatus.OK);
	}

	/**
	 * Get User Details.
	 *
	 * @param GetUserDetailsRequestASBO get user details request asbo
	 * @param httpServletRequest the http servlet request
	 * @return the response entity
	 */
	@RequestMapping(value = "/getUserDetails", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getUserDetails(
			@RequestBody GetUserDetailsRequestASBO getUserDetailsRequestASBO,
			HttpServletRequest httpServletRequest) {
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getUserDetailsRequestASBO.setHeader(httpHeaderUtil.generateSpringHeader(UtilConstants.GET_USER_DETAILS));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}

		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processGetUserDetails(getUserDetailsRequestASBO),
				HttpStatus.OK);
	}

}
