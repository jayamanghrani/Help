/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.employeeportal.controller;


import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.employeeportal.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.employeeportal.asbo.emp.request.GetUserDetailsRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.GetUserDetailsResponseASBO;
import com.tcs.employeeportal.component.integrator.DBChannelIntegrator;
import com.tcs.employeeportal.component.integrator.DomainChannelIntegrator;
import com.tcs.employeeportal.controller.util.ControllerUtil;
import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.transformation.alfresco.transformers.GetAlfrescoContentTransformer;
import com.tcs.employeeportal.transformation.alfresco.transformers.GetExecutiveMsgTransformer;
import com.tcs.employeeportal.transformation.login.transformers.GetUserDetailsTransformer;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.MessageConstants;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.util.ValidationUtil;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.Header;

// TODO: Auto-generated Javadoc
/**
 * The Class AlfrescoIntegrationController.
 * 
 * @author 738566
 */
public class AlfrescoIntegrationController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

	/** The gson. */
	private static Gson gson;

	static {
		gson = ControllerUtil.getGson();
	}

	/** The domain channel integrator. */
	private DomainChannelIntegrator domainChannelIntegrator;
	private DBChannelIntegrator dbChannelIntegrator;
	/**
	 * Instantiates a new alfresco integration controller.
	 */
	public AlfrescoIntegrationController() {
		domainChannelIntegrator = new DomainChannelIntegrator();
		dbChannelIntegrator = new DBChannelIntegrator();
	}

	/**
	 * Gets the single instance of DomainIntegrationProcessController.
	 * 
	 * @return single instance of DomainIntegrationProcessController
	 */
	public static synchronized DomainChannelIntegrator getInstance() {
		DomainChannelIntegrator instance = null;
		if (instance == null) {

			instance = new DomainChannelIntegrator();

		}

		return instance;

	}

	/**
	 * Process get content.
	 * 
	 * @param getContentRequestASBO
	 *            the get content request asbo
	 * @return the EmployeePortal response vo
	 */
	public EmployeePortalResponseObject processGetContent(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing Alfresco Request Get ProcessContent");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		EmployeePortalRequestObject employeeRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();
		try {
			employeeRequestObject = getAlfrecoContentTransformer.transformRequest(getContentRequestASBO);
			LOGGER.info("After Tranformer" +employeeRequestObject.getHeader());

		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO = null;

		try {
			getAlfrescoContentResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeeRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getAlfrescoContentResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getAlfrescoContentResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);

		}
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}


	/**
	 * Process carousel.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the ba ncs integration response object
	 */
	public EmployeePortalResponseObject processCarousel(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing Carousel");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		EmployeePortalRequestObject employeePortalRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			employeePortalRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeePortalRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}

	/**
	 * Process latestUpdates.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public EmployeePortalResponseObject processlatestUpdates(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing latestUpdates");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		EmployeePortalRequestObject employeePortalRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			employeePortalRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeePortalRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}


	/**
	 * Process notifications.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public EmployeePortalResponseObject processNotifications(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing notifications");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		EmployeePortalRequestObject employeePortalRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			employeePortalRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeePortalRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}

	/**
	 * Process executive msgs.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public EmployeePortalResponseObject processExecutiveMsgs(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing executive msgs");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		GetExecutiveMsgTransformer getExecutiveMsgTransformer= new GetExecutiveMsgTransformer();
		EmployeePortalRequestObject employeePortalRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			employeePortalRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeePortalRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getExecutiveMsgTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}
	
	public EmployeePortalResponseObject processGetUserDetails(
			GetUserDetailsRequestASBO getUserDetailsRequestASBO) {
		LOGGER.debug("Inside employee get User details");
		GetUserDetailsTransformer transformer = new GetUserDetailsTransformer();
		GetUserDetailsDBRequestASBO getUserDetailsDBRequestASBO = null;
		GetUserDetialsDBResponseASBO getUserDetialsDBResponseASBO = null;
		GetUserDetailsResponseASBO getUserDetailsResponseASBO = null;
		
		Header header = getUserDetailsRequestASBO.getHeader();
		ErrorVO error=new ErrorVO();
		try {
			//validation for user input starts
			if(!ValidationUtil.isUserIdValid(getUserDetailsRequestASBO.getUserId())){
				error.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
				error.setErrorMessage(MessageConstants.USER_ID_INVALID_EXCEPTION);
    			return error;
			}
			
			if(!ValidationUtil.isMaxLengthValid(getUserDetailsRequestASBO.getUserId(),15))
    		{
				error.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				error.setErrorMessage(MessageFormat.format(MessageConstants.MAX_LENGTH_ERROR , "User Name"));
    			return error;
    		}
			
		   //validation for channel starts
			if(!getUserDetailsRequestASBO.getChannel().equals(UtilConstants.ADMIN_USER))
			{
				error.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				error.setErrorMessage(UtilConstants.INVALID_CHANNEL);
    			return error;
			}
			//transform request
			getUserDetailsDBRequestASBO = (GetUserDetailsDBRequestASBO) transformer.transformRequest(getUserDetailsRequestASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}
		// calling the db 
		LOGGER.debug("request received as----->" +new Gson().toJson(getUserDetailsDBRequestASBO));
		
		try {
			getUserDetialsDBResponseASBO = (GetUserDetialsDBResponseASBO) dbChannelIntegrator.execute(getUserDetailsDBRequestASBO);
			getUserDetialsDBResponseASBO.setHeader(header);
			LOGGER.debug("response received as----->"+new Gson().toJson(getUserDetialsDBResponseASBO));
			
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);
		}
			try {
				getUserDetailsResponseASBO = (GetUserDetailsResponseASBO) transformer.transformResponse(getUserDetialsDBResponseASBO);
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}
		return getUserDetailsResponseASBO;
	}
	
}


