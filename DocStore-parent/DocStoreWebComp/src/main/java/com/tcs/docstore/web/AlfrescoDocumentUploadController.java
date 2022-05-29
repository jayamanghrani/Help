/**
 * 
 */
package com.tcs.docstore.web;

/**
 * @author 585226
 *
 */
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

import com.tcs.docstore.alfresco.asbo.request.GetListOfDepartmentsASBO;
import com.tcs.docstore.alfresco.asbo.request.GetListOfIssuedByRequestASBO;
import com.tcs.docstore.asbo.emp.request.AlfrescoRequestASBO;
import com.tcs.docstore.controller.AlfrescoUploadIntegrationController;
import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.util.ExceptionUtil;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.web.util.HttpHeaderUtil;

@Component
@RestController
@RequestMapping("/alfrescoUpload")

public class AlfrescoDocumentUploadController {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger(AlfrescoDocumentUploadController.class);
	
	@RequestMapping(value = "/{player}", method = RequestMethod.GET)
	public String getMsg(@PathVariable String player) {
		
		return "" + player;
	}

	@RequestMapping(value = "/uploadDocument", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> uploadDocument(@RequestBody AlfrescoRequestASBO	 requestASBO, HttpServletRequest request){
		long a =System.currentTimeMillis();
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			
			/*String checkmemblerof = docSearchRequestASBO.getHeader().getGroups();
			String userIDcheck = docSearchRequestASBO.getHeader().getEmployeeId();
			String others = docSearchRequestASBO.getHeader().getApplicationId();
			
			System.out.println("he user id is --"+userIDcheck+"***others"+others+"***********memberOf"+checkmemblerof);*/
		
			}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			LOGGER.error(e.getStackTrace());
			return new ResponseEntity<DocStoreResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeader(UtilConstants.UPLOAD_DOCUMENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getManagePortalDetails service completed");
		}



		return new ResponseEntity<DocStoreResponseObject>(
				new AlfrescoUploadIntegrationController().uploadDocument(requestASBO),HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/getDepartmentList", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getDepartments(@RequestBody GetListOfDepartmentsASBO	 requestASBO, HttpServletRequest request){
		long a =System.currentTimeMillis();
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try {
			// added to get the specific department for the user
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.GET_DEPARTMENT_LIST));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getDepartmentList service completed");
		}



		return new ResponseEntity<DocStoreResponseObject>(new AlfrescoUploadIntegrationController().getDepartmentList(requestASBO,userid),HttpStatus.OK);

	}
	
	/* added for document store changes for the portal  03 Oct 2016 this gets the list of departments*/
	
	
	@RequestMapping(value = "/getVisibleToDepartments", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getVisibleToDepartments(@RequestBody GetListOfDepartmentsASBO	 requestASBO, HttpServletRequest request){
		long a =System.currentTimeMillis();
		LOGGER.info("The webservice getDepartmentList"+a);
	//	HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty) request.getAttribute("HTTP_HEADERS");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try {
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.GET_DEPARTMENT_LIST));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getDepartmentList service completed");
		}



		return new ResponseEntity<DocStoreResponseObject>(
				new AlfrescoUploadIntegrationController().getDepartmentList(requestASBO,userid),HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/getSearchDepartmentsList", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getSearchDepartmentsList(@RequestBody GetListOfDepartmentsASBO	 requestASBO, HttpServletRequest request){
		long a =System.currentTimeMillis();
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		try {
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.GET_DEPARTMENT_LIST));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getDepartmentList service completed");
		}



		return new ResponseEntity<DocStoreResponseObject>(
				new AlfrescoUploadIntegrationController().getSearchedDepartmentList(requestASBO,userid),HttpStatus.OK);

	}
	
	
	@RequestMapping(value = "/IssuedBy", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getIssuedBy(@RequestBody GetListOfIssuedByRequestASBO	 requestASBO, HttpServletRequest request){
		long a =System.currentTimeMillis();
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			requestASBO.setHeader(httpHeaderUtil.generateSpringHeader(/*httpHeaderProperty,*/UtilConstants.GET_ISSUED_BY));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug("getIssuedBy service completed");
		}



		return new ResponseEntity<DocStoreResponseObject>(
				new AlfrescoUploadIntegrationController().getIssuedBy(requestASBO),HttpStatus.OK);

	}
}
