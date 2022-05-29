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

import com.tcs.employeeportal.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.employeeportal.controller.AlfrescoIntegrationController;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.web.util.HttpHeaderUtil;

//TODO: Auto-generated Javadoc
/**
 * The Class AlfrescoController.
 * 
 * @author 376448
 */

@Component 
@RestController
@RequestMapping("/alfresco")

public class AlfrescoController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("empPortalLogger");

	@RequestMapping(value = "/{player}", method = RequestMethod.GET)
	public String getMsg(@PathVariable String player) {

		return " ";
	}


	/**
	 * Gets the content.
	 * 
	 * @param getContentRequestASBO
	 *            the get content request asbo
	 * @param request
	 *            the request
	 * @return the content
	 */
	@RequestMapping(value = "/getContent", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getContent(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.info("getContent service invoked");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeader(UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("getContent service completed");
		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processGetContent(getContentRequestASBO),
				HttpStatus.OK);
	}


	/**
	 * Gets the Notifications.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the Notifications
	 */
	@RequestMapping(value = "/getNotifications", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getNotifications(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.info("getNotifications service invoked");
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
		}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeaderLogin(userid,firstname,memberOf ,UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("getNotifications service completed");
		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processNotifications(getContentRequestASBO),
				HttpStatus.OK);
	}

	/**
	 * Gets the ExecutiveMsgs.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the ExecutiveMsgs
	 */
	@RequestMapping(value = "/getExecutiveMsgs", method = RequestMethod.POST)
	public ResponseEntity<EmployeePortalResponseObject> getExecutiveMsgs(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.info("getExecutiveMsgs service invoked");
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
		}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeaderLogin(userid,firstname,memberOf ,UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<EmployeePortalResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.info("getExecutiveMsgs service completed");
		return new ResponseEntity<EmployeePortalResponseObject>(
				new AlfrescoIntegrationController()
				.processExecutiveMsgs(getContentRequestASBO),
				HttpStatus.OK);
	}

	
}

