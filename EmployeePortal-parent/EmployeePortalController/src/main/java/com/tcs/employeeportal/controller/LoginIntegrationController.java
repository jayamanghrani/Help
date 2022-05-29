/**
 * 
 */
package com.tcs.employeeportal.controller;

import java.text.MessageFormat;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.asbo.login.request.ChangePasswordRequestASBO;
import com.tcs.employeeportal.asbo.login.request.ForgotPasswordRequestASBO;
import com.tcs.employeeportal.asbo.login.request.LoginRequestASBO;
import com.tcs.employeeportal.asbo.login.response.ChangePasswordResponseASBO;
import com.tcs.employeeportal.asbo.login.response.ForgotPasswordResponse;
import com.tcs.employeeportal.asbo.login.response.LoginResponseASBO;
import com.tcs.employeeportal.component.integrator.DBChannelIntegrator;
import com.tcs.employeeportal.component.integrator.DomainChannelIntegrator;
import com.tcs.employeeportal.component.integrator.EmailChannelIntegrator;
import com.tcs.employeeportal.component.integrator.MessageChannelIntegrator;
import com.tcs.employeeportal.component.integrator.OAMOIDChannelIntegrator;
import com.tcs.employeeportal.db.asbo.request.ForgotPasswordDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.ForgotPasswordDBResponseASBO;
import com.tcs.employeeportal.email.asbo.request.EmailServiceRequestASBO;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.login.asbo.request.ForgotPasswordOAMOIDRequestASBO;
import com.tcs.employeeportal.login.asbo.response.ChangePasswordOAMOIDResponseASBO;
import com.tcs.employeeportal.login.asbo.response.ForgotPasswordOAMOIDResponseASBO;
import com.tcs.employeeportal.message.asbo.request.MessageServiceRequestASBO;
import com.tcs.employeeportal.transformation.alfresco.transformers.EmailContentTransformer;
import com.tcs.employeeportal.transformation.alfresco.transformers.LoginContentTransformer;
import com.tcs.employeeportal.transformation.alfresco.transformers.MessageContentTransformer;
import com.tcs.employeeportal.transformation.email.transformers.EmailTransformer;
import com.tcs.employeeportal.transformation.login.transformers.ChangePasswordTransformer;
import com.tcs.employeeportal.transformation.login.transformers.ForgotPasswordTransformer;
import com.tcs.employeeportal.transformation.login.transformers.LoginTransformer;
import com.tcs.employeeportal.transformation.message.transformers.MessageTransformer;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.MessageConstants;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.util.ValidationUtil;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.Header;

/**
 * @author 738566
 *
 */
public class LoginIntegrationController {
	

	/** The Constant LOGGER. .... */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

	/** The oimoid channel integrator. */
	private OAMOIDChannelIntegrator oamoidChannelIntegrator;

	/** The Email channel integrator. */
	private EmailChannelIntegrator emailChannelIntegrator;

	/** The message channel integrator. */
	private MessageChannelIntegrator messageChannelIntegrator;

	/** The Email transformer. */
	private EmailTransformer emailTransformer;

	/** The db channel integrator. */
	private DBChannelIntegrator dbChannelIntegrator;

	/** The domain channel integrator. */
	private DomainChannelIntegrator domainChannelIntegrator;

	/** The email content transformer. */
	private EmailContentTransformer emailContentTransformer;

	/** The message content transformer. */
	private MessageContentTransformer messageContentTransformer;

	/** The message transformer. */
	private MessageTransformer messageTransformer;

	/**
	 * Instantiates a new login integration controller.
	 */
	public LoginIntegrationController() {
		oamoidChannelIntegrator = new OAMOIDChannelIntegrator();
		emailChannelIntegrator = new EmailChannelIntegrator();
		emailTransformer = new EmailTransformer();
		dbChannelIntegrator = new DBChannelIntegrator();
		domainChannelIntegrator = new DomainChannelIntegrator();
		emailContentTransformer = new EmailContentTransformer();
		messageContentTransformer = new MessageContentTransformer();
		messageTransformer = new MessageTransformer();
		messageChannelIntegrator = new MessageChannelIntegrator();
		
	}

	public EmployeePortalResponseObject processCwissLinks(
			LoginRequestASBO loginRequestASBO) {
		LOGGER.info("Inside employee login getCwissLinks");
				
		LoginTransformer loginTransformer = new LoginTransformer();
		LoginContentTransformer loginContentTransformer= new LoginContentTransformer();
		GetAlfrecoContentRequestASBO getAlfrecoContentRequestASBO = null;
		GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO = null;
		LoginResponseASBO loginResponseASBO=null;
		Header header=loginRequestASBO.getHeader();
		try {

			getAlfrecoContentRequestASBO = (GetAlfrecoContentRequestASBO) loginContentTransformer.transformLoginAddLink(loginRequestASBO);
			getAlfrecoContentRequestASBO.getHeader().setEventID(UtilConstants.ALFRESCO_GET_CONTENT);

		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}

		try {

			getAlfrescoContentResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator.execute(getAlfrecoContentRequestASBO);
			
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		}
			LOGGER.info("getAlfrescoContentResponseASBO---AdditionalLink---"+new Gson().toJson(getAlfrescoContentResponseASBO));
				
			try {
				loginResponseASBO=(LoginResponseASBO) loginTransformer.transformLoginAddLink(getAlfrescoContentResponseASBO);
				LOGGER.info("Response links loginResponseASBO"+new Gson().toJson(loginResponseASBO));
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
			}
	
		return loginResponseASBO;
	}

	public EmployeePortalResponseObject processForgotPassword(
			ForgotPasswordRequestASBO forgotPasswordRequestASBO) {
		LOGGER.info("Inside employee forgot password" +forgotPasswordRequestASBO.getUserId());
		ForgotPasswordTransformer transformer = new ForgotPasswordTransformer();
		ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO = null;
		ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO = null;

		// email
		GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO = null;
		GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO = null;
		EmailServiceRequestASBO emailServiceRequestASBO = null;
		Header header = forgotPasswordRequestASBO.getHeader();

		try {
			//validation for user input starts
			ErrorVO errorVO = new ErrorVO();
			if(!ValidationUtil.isUserIdValid(forgotPasswordRequestASBO.getUserId()))
			{
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
				errorVO.setErrorMessage(MessageConstants.USER_ID_INVALID_EXCEPTION);
				return errorVO;
			}
			if(!ValidationUtil.isMaxLengthValid(forgotPasswordRequestASBO.getUserId(),15))
    		{
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage(MessageFormat.format(MessageConstants.MAX_LENGTH_ERROR , "User Name"));
    			return errorVO;
    		}
			
			if(ValidationUtil.isNotNull(forgotPasswordRequestASBO.getDob()))
			{
				if(!ValidationUtil.isAgeValid(forgotPasswordRequestASBO.getDob()))
						{
					errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
					errorVO.setErrorMessage(MessageConstants.INVALID_AGE_MESSAGE);
					return errorVO;
 
						}
			}else
			{
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage("Please enter Date of Birth");
				return errorVO;
			}

			forgotPasswordDBRequestASBO = (ForgotPasswordDBRequestASBO) transformer.transformRequest(forgotPasswordRequestASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}

		// calling the db to check the dob and userId
		
		try {
			forgotPasswordDBResponseASBO = (ForgotPasswordDBResponseASBO) dbChannelIntegrator.execute(forgotPasswordDBRequestASBO);
				
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		}
		ForgotPasswordOAMOIDRequestASBO forgotPasswordOAMOIDRequestASBO = null;
		ForgotPasswordResponse forgotPasswordResponseASBO = null;

		if ("0".equals(forgotPasswordDBResponseASBO.getStatusCode())) {

			try {
				forgotPasswordOAMOIDRequestASBO = (ForgotPasswordOAMOIDRequestASBO) transformer.transformOAMOIDRequest(forgotPasswordDBResponseASBO);
				forgotPasswordOAMOIDRequestASBO.setHeader(forgotPasswordDBRequestASBO.getHeader());
				header = forgotPasswordOAMOIDRequestASBO.getHeader();
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}
			ForgotPasswordOAMOIDResponseASBO forgotPasswordOAMOIDResponseASBO = null;

			try {
				forgotPasswordOAMOIDResponseASBO = (ForgotPasswordOAMOIDResponseASBO) oamoidChannelIntegrator.execute(forgotPasswordOAMOIDRequestASBO);
				forgotPasswordOAMOIDResponseASBO.setHeader(forgotPasswordRequestASBO.getHeader());
			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			}

			try {
				forgotPasswordResponseASBO = (ForgotPasswordResponse) transformer.transformOAMOIDResponse(forgotPasswordOAMOIDResponseASBO);
				forgotPasswordResponseASBO.setEmailId(forgotPasswordDBResponseASBO.getEmailId());
				forgotPasswordResponseASBO.setFirstName(forgotPasswordDBResponseASBO.getFirstName());
				forgotPasswordResponseASBO.setLastName(forgotPasswordDBResponseASBO.getLastName());
			//	forgotPasswordResponseASBO.setGender(forgotPasswordDBResponseASBO.getGender());
			//	forgotPasswordResponseASBO.setTitle(forgotPasswordDBResponseASBO.getTitle());
				forgotPasswordResponseASBO.setMobileNo(forgotPasswordDBResponseASBO.getMobileNo());
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "response", e);
			}
				
		
			// sms

			try {

				getContentAlfrescoRequestASBO = (GetAlfrecoContentRequestASBO) messageContentTransformer.transformForgotPasswordMsgRequest(forgotPasswordRequestASBO);
				getContentAlfrescoRequestASBO.getHeader().setEventID(UtilConstants.ALFRESCO_GET_CONTENT);
				header = getContentAlfrescoRequestASBO.getHeader();

			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}

			try {

				getAlfrescoContentResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);

			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			}

			MessageServiceRequestASBO messageServiceRequestASBO;
			try {
				messageServiceRequestASBO = (MessageServiceRequestASBO) messageTransformer.transformForgotPasswordMsgRequest(forgotPasswordResponseASBO, getAlfrescoContentResponseASBO);
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}

			try {

				messageChannelIntegrator.execute(messageServiceRequestASBO);
			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			}

			// email
			Header header1 = new Header();
			header1.setEventID(UtilConstants.ALFRESCO_GET_CONTENT);
			try {

				getContentAlfrescoRequestASBO = (GetAlfrecoContentRequestASBO) emailContentTransformer.transformForgotPasswordRequest(forgotPasswordRequestASBO);

				getContentAlfrescoRequestASBO.setHeader(header1);

			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
			}

			try {

				getAlfrescoContentResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);

			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header1, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			}

			try {
				emailServiceRequestASBO = (EmailServiceRequestASBO) emailTransformer.transformForgotPasswordRequest(forgotPasswordResponseASBO, getAlfrescoContentResponseASBO);
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
			}

			try {

				emailChannelIntegrator.execute(emailServiceRequestASBO);
			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			}

		} else {
			try {
				forgotPasswordResponseASBO = (ForgotPasswordResponse) transformer.transformResponse(forgotPasswordDBResponseASBO);
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}
		}
		forgotPasswordResponseASBO.setPassword("");
		forgotPasswordResponseASBO.setMobileNo("");
		forgotPasswordResponseASBO.setEmailId("");
		return forgotPasswordResponseASBO;

	}
	
	
	/**
	 * Process change password.
	 * 
	 * @param changePasswordRequestASBO
	 *            the change password request asbo
	 * @return the ba ncs integration response vo
	 */
	public EmployeePortalResponseObject processChangePassword(
			ChangePasswordRequestASBO changePasswordRequestASBO) {
		LOGGER.info("Inside Change password");
		ChangePasswordResponseASBO changePasswordResponseASBO = null;
		ChangePasswordTransformer transformer = new ChangePasswordTransformer();
		EmployeePortalRequestObject employeePortalRequestObject = null;
		Header header = changePasswordRequestASBO.getHeader();
		
		try {
			ErrorVO err=ValidationUtil.isValidPassword(changePasswordRequestASBO.getOldPassword(), "Old Password", UtilConstants.PASSWORD_MAXLENGTH,UtilConstants.PASSWORD_MINLENGTH);
			if(err.getErrorCode()!=0)
			{
				return err;
			}
			err=ValidationUtil.isValidPassword(changePasswordRequestASBO.getNewPassword(), "New Password", UtilConstants.PASSWORD_MAXLENGTH,UtilConstants.PASSWORD_MINLENGTH);
			if(err.getErrorCode()!=0)
			{
				return err;
			}
			
			employeePortalRequestObject = transformer
					.transformRequest(changePasswordRequestASBO);

		} catch (IntegrationTransformationException e) {
			return ExceptionUtil.getTransformationErrorVO(header, "request", e);
		}

		ChangePasswordOAMOIDResponseASBO changePasswordOAMOIDResponseASBO = null;

		try {
			changePasswordOAMOIDResponseASBO = (ChangePasswordOAMOIDResponseASBO) oamoidChannelIntegrator
					.execute(employeePortalRequestObject);
		} catch (IntegrationTechnicalException e) {
			return ExceptionUtil.getTechnicalErrorVO(header,
					UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);
		}

		changePasswordOAMOIDResponseASBO.setHeader(changePasswordRequestASBO
				.getHeader());

		try {
			changePasswordResponseASBO = (ChangePasswordResponseASBO) transformer
					.transformResponse(changePasswordOAMOIDResponseASBO);
		} catch (IntegrationTransformationException e) {
			return ExceptionUtil
					.getTransformationErrorVO(header, "response", e);
		}
		return changePasswordResponseASBO;
	}

}
