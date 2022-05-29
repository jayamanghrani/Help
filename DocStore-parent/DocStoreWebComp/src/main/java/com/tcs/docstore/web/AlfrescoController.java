package com.tcs.docstore.web;

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

import com.tcs.docstore.alfresco.asbo.request.DocSearchRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.docstore.controller.AlfrescoIntegrationController;
import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.util.ExceptionUtil;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.util.ValidationUtil;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.web.util.HttpHeaderUtil;

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
			.getLogger(AlfrescoController.class);

/*	@RequestMapping(value = "/{player}", method = RequestMethod.GET)
	public String getMsg(@PathVariable String player) {

		return " ";
	}*/


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
	public ResponseEntity<DocStoreResponseObject> getContent(
			@RequestBody GetContentRequestASBO getContentRequestASBO,
			HttpServletRequest request) {

		LOGGER.debug("getContent service invoked");
		//	HttpHeaderProperty httpHeaderProperty = (HttpHeaderProperty) request.getAttribute("HTTP_HEADERS");
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		try {
			getContentRequestASBO.setHeader(httpHeaderUtil
					.generateSpringHeader(/*httpHeaderProperty,*/
							UtilConstants.ALFRESCO_GET_CONTENT));
		} catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.debug("getContent service completed");
		return new ResponseEntity<DocStoreResponseObject>(
				new AlfrescoIntegrationController()
				.processGetContent(getContentRequestASBO),
				HttpStatus.OK);
	}



	/**
	 * Gets the documents.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the documents
	 */
	@RequestMapping(value = "/getSearchedDocuments", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getDocuments(
			@RequestBody DocSearchRequestASBO docSearchRequestASBO,
			HttpServletRequest request) {

		LOGGER.debug("getDocuments service invoked");
		
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{
			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			//validation for header
			ErrorVO errorVO=ValidationUtil.validateUserHeader(userid, firstname, memberOf);
			if(errorVO.getErrorCode()!=0)
			{
				return new ResponseEntity<DocStoreResponseObject>(errorVO,HttpStatus.OK);
			}
			//Validation for header ends
			
			}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			LOGGER.error(e.getStackTrace());
			return new ResponseEntity<DocStoreResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
			LOGGER.info("memberOf------"+memberOf);

		
		try {
			docSearchRequestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.ALFRESCO_GET_SEARCH_DOCUMENT));
		} 
		
		
		catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
			
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.debug("getDocuments service completed");
		return new ResponseEntity<DocStoreResponseObject>(new AlfrescoIntegrationController().processDocuments(docSearchRequestASBO),HttpStatus.OK);
	}


	
	/**
	 * Gets the documents.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @param request the request
	 * @return the documents
	 */
	@RequestMapping(value = "/getOldDocuments", method = RequestMethod.POST)
	public ResponseEntity<DocStoreResponseObject> getOldDocuments(
			@RequestBody DocSearchRequestASBO docSearchRequestASBO,
			HttpServletRequest request) {

		LOGGER.debug("getOldDocuments service invoked");
		String memberOf=null;
		String firstname=null;
		String userid=null;
		
		try{

			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
			//validation for header
			ErrorVO errorVO=ValidationUtil.validateUserHeader(userid, firstname, memberOf);
			if(errorVO.getErrorCode()!=0)
			{
				return new ResponseEntity<DocStoreResponseObject>(errorVO,HttpStatus.OK);
			}
			//Validation for header ends
	
			}
		catch(Exception e){
			ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(e);
			LOGGER.error(e.getStackTrace());
			return new ResponseEntity<DocStoreResponseObject>(errorVO,HttpStatus.INTERNAL_SERVER_ERROR);
		}
				
		HttpHeaderUtil httpHeaderUtil = new HttpHeaderUtil();
		LOGGER.info("memberOf------"+memberOf);

		
		try {
			docSearchRequestASBO.setHeader(httpHeaderUtil.generateSpringHeaderLogin(userid,firstname,memberOf,UtilConstants.ALFRESCO_GET_OLD_DOCUMENT));
		} 
		
		
		catch (IntegrationTechnicalException integrationTechnicalException) {
			ErrorVO errorVO = ExceptionUtil.getApplicationIdErrorVO(integrationTechnicalException);
	//		ErrorVO errorVO = ExceptionUtil.getUserIDErrorVO(integrationTechnicalException);
			// ask chennai team to ad a separate parameter
			
			return new ResponseEntity<DocStoreResponseObject>(errorVO,
					HttpStatus.INTERNAL_SERVER_ERROR);

		}
		LOGGER.debug("getOldDocuments service completed");
		return new ResponseEntity<DocStoreResponseObject>(new AlfrescoIntegrationController().processOldDocuments(docSearchRequestASBO),HttpStatus.OK);
	}

	
}

