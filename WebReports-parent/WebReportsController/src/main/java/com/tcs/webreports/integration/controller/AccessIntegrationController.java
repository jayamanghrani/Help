/**
 * 
 */
package com.tcs.webreports.integration.controller;

import org.apache.log4j.Logger;
import com.tcs.webreports.asbo.access.request.IsLoggedInRequestASBO;
import com.tcs.webreports.asbo.access.request.LoginRequestASBO;
import com.tcs.webreports.asbo.access.request.LogoutRequestASBO;
import com.tcs.webreports.asbo.access.response.IsLoggedInResponseASBO;
import com.tcs.webreports.asbo.access.response.LoginResponseASBO;
import com.tcs.webreports.asbo.access.response.LogoutResponseASBO;
import com.tcs.webreports.component.integrator.OAMOIDChannelIntegrator;
import com.tcs.webreports.exception.cmo.ErrorVO;
import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.exception.cmo.IntegrationTransformationException;
import com.tcs.webreports.oamoid.asbo.response.IsLoggedInOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.LoginOAMOIDResponseASBO;
import com.tcs.webreports.oamoid.asbo.response.LogoutOAMOIDResponseASBO;
import com.tcs.webreports.util.ExceptionUtil;
import com.tcs.webreports.util.ValidationUtil;
import com.tcs.webreports.vo.cmo.Header;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;
import com.tcs.webreports.access.transformers.IsLoggedInTransformer;
import com.tcs.webreports.access.transformers.LoginTransformer;
import com.tcs.webreports.access.transformers.LogoutTransformer;
/**
 * @author 738566
 *
 */
public class AccessIntegrationController {

	  private static final Logger LOGGER = Logger.getLogger("webReportsLogger");
	  private OAMOIDChannelIntegrator oamoidChannelIntegrator;
	  /*private EmailChannelIntegrator emailChannelIntegrator;
	  private MessageChannelIntegrator messageChannelIntegrator;
	  private EmailTransformer emailTransformer;
	  private DBChannelIntegrator dbChannelIntegrator;
	  private DomainChannelIntegrator domainChannelIntegrator;
	  private EmailContentTransformer emailContentTransformer;
	  private MessageContentTransformer messageContentTransformer;
	  private MessageTransformer messageTransformer;*/

	  public AccessIntegrationController()
	  {
	    this.oamoidChannelIntegrator = new OAMOIDChannelIntegrator();
	    /*this.emailChannelIntegrator = new EmailChannelIntegrator();
	    this.emailTransformer = new EmailTransformer();
	    this.dbChannelIntegrator = new DBChannelIntegrator();
	    this.domainChannelIntegrator = new DomainChannelIntegrator();
	    this.emailContentTransformer = new EmailContentTransformer();
	    this.messageContentTransformer = new MessageContentTransformer();
	    this.messageTransformer = new MessageTransformer();
	    this.messageChannelIntegrator = new MessageChannelIntegrator();*/
	  }

	  /**
	   * Method to process login
	   * @param loginRequestASBO
	   * @return
	   */
	  public WebReportsResponseObject processLogin(LoginRequestASBO loginRequestASBO) 
	  {
	    LoginResponseASBO loginResponseASBO = null;
	    LOGGER.info("Inside Process Login");
	    LoginTransformer transformer = new LoginTransformer();
	    WebReportsRequestObject webReportsRequestObject = null;
	    Header header = loginRequestASBO.getHeader();
	    String errormessage="";	  
	    errormessage =ValidationUtil.userIdvalidation(loginRequestASBO.getUserId());
	    if(!errormessage.equalsIgnoreCase("pass"))
	    {
	    	System.out.println("Inside if of id");
			LOGGER.info("UserId is invalid");
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorMessage(errormessage);
			LOGGER.info("UserId is invalid");
			return errorVO;
	    }
	
	  else {
		  
		errormessage=ValidationUtil.passwordValidation(loginRequestASBO.getPassword());
				if(!errormessage.equalsIgnoreCase("pass"))
				{
					ErrorVO errorVO = new ErrorVO();
					errorVO.setErrorMessage(errormessage);
					LOGGER.info("Password should meet the password policy");
					return errorVO;
				} else
				{
					try {
						webReportsRequestObject = transformer.transformRequest(loginRequestASBO);
					} catch (IntegrationTransformationException e1) {
						return ExceptionUtil.getTransformationErrorVO(header, "response", e1);
					}
				}	
	    }	
	   
	    LoginOAMOIDResponseASBO loginOAMOIDResponseASBO = null;
	    try
	    {
	      loginOAMOIDResponseASBO = (LoginOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(webReportsRequestObject);
	    }
	    catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    loginOAMOIDResponseASBO.setHeader(loginRequestASBO.getHeader());
	    try
	    {
	      loginResponseASBO = (LoginResponseASBO)transformer.transformResponse(loginOAMOIDResponseASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
	    }
	    
	  /*  if ((null != loginResponseASBO.getLoggedIn()) && ("true".equalsIgnoreCase(loginResponseASBO.getLoggedIn())))
	    {
	      GetNCProfileDetailsDBRequestASBO dbRequestASBO = null;
	      try
	      {
	        dbRequestASBO = (GetNCProfileDetailsDBRequestASBO)transformer.transformForUserProfile(loginDetailsRequestASBO);

	        dbRequestASBO.getHeader().setEventID("getNCUserProfileDB");

	        header = dbRequestASBO.getHeader();
	      } catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVO(header, "request", e);
	      }

	      GetNCProfileDetailsDBResponseASBO dbResponseASBO = null;
	      try {
	        dbResponseASBO = (GetNCProfileDetailsDBResponseASBO)this.dbChannelIntegrator.execute(dbRequestASBO);

	        dbResponseASBO.setHeader(dbRequestASBO.getHeader());
	        loginResponseASBO.setCity(dbResponseASBO.getCity());
	      } catch (IntegrationTechnicalException e) {
	        return ExceptionUtil.getTechnicalErrorVO(dbRequestASBO.getHeader(), 952, e);
	      }

	      GetChannelRoleBasedDataRequestASBO getChannelRoleBasedDataRequestASBO = null;
	      header = dbRequestASBO.getHeader();
	      try {
	        getChannelRoleBasedDataRequestASBO = (GetChannelRoleBasedDataRequestASBO)transformer.transformResponseForUserProfile(dbResponseASBO);

	        if ((null != getChannelRoleBasedDataRequestASBO) && (null != getChannelRoleBasedDataRequestASBO.getHeader()))
	        {
	          if (("PORTAL".equalsIgnoreCase(loginRequestASBO.getHeader().getApplicationId())) && ("CUSTOMER".equalsIgnoreCase(getChannelRoleBasedDataRequestASBO.getChannelCd())))
	          {
	            LOGGER1.debug(dbRequestASBO.getUserId() + "," + dbResponseASBO.getFirstName() + "," + dbResponseASBO.getLastName() + "," + dbResponseASBO.getEmailId1() + "," + dbResponseASBO.getMobileNo1());
	          }

	          getChannelRoleBasedDataRequestASBO.getHeader().setEventID("getChannelRoleBasedData");

	          header = getChannelRoleBasedDataRequestASBO.getHeader();
	        }
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVO(dbRequestASBO.getHeader(), "response", e);
	      }

	      if (null != getChannelRoleBasedDataRequestASBO) {
	        Header header2 = getChannelRoleBasedDataRequestASBO.getHeader();
	        List channelRoleProcessDatas = null;
	        try {
	          channelRoleProcessDatas = processGetChannelRoleBasedDataLogin(getChannelRoleBasedDataRequestASBO, loginDetailsRequestASBO.getHeader().getApplicationId());
	        } catch (Exception e) {
	          return ExceptionUtil.getTransformationErrorVOException(header2, "request", e);
	        }

	        if ((null != channelRoleProcessDatas) && (!channelRoleProcessDatas.isEmpty())) {
	          loginResponseASBO.setChannelRoleProcessDatas(channelRoleProcessDatas);
	        }

	      }

	      try
	      {
	        if (null != dbResponseASBO.getChannelCd()) {
	          String emailId = ControllerUtil.getChannelConfigValue("EMAIL_ID", dbResponseASBO.getChannelCd());
	          if (null != emailId)
	            loginResponseASBO.setChannelEmailId(emailId);
	          else
	            loginResponseASBO.setChannelEmailId(PropertiesUtil.getConfigData("PORTAL_SUPPORT_EMAIL"));
	        }
	      }
	      catch (Exception e) {
	        loginResponseASBO.setChannelEmailId(PropertiesUtil.getConfigData("PORTAL_SUPPORT_EMAIL"));
	      }

	      try
	      {
	        if ((null != dbResponseASBO.getChannelCd()) && 
	          ("SURVEYOR".equalsIgnoreCase(dbResponseASBO.getChannelCd()))) {
	          String surveyorStatus = getSurveyorStatus(loginRequestASBO);
	          if (null != loginResponseASBO.getUserProfile()) {
	            loginResponseASBO.getUserProfile().setSurveyorStatusCode(surveyorStatus);
	          }
	        }

	      }
	      catch (Exception e)
	      {
	        LOGGER.error(e);
	      }

	      if ((null != loginRequestASBO.getQuoteProcess()) && ((!loginRequestASBO.getQuoteProcess().isEmpty()) || (!"true".equalsIgnoreCase(loginDetailsRequestASBO.getQuoteProcess()))))
	      {
	        if ((null != dbResponseASBO) && (null != dbResponseASBO.getStakeCode()) && (!"POLICY-HOL".equalsIgnoreCase(dbResponseASBO.getStakeCode())))
	        {
	          com.tcs.bancsins.integration.cmbo.user.UserProfile userProfile = null;
	          com.tcs.bancsins.integration.cmbo.user.Footer footer = null;
	          if (null != loginResponseASBO.getUserProfile()) {
	            userProfile = loginResponseASBO.getUserProfile();
	            if (null != userProfile.getFooter()) {
	              footer = userProfile.getFooter();
	              footer.setErrorCode("1");
	              footer.setErrorDescription(PropertiesUtil.getConfigProperty("unAuthorizedToView"));
	            } else {
	              footer = new com.tcs.bancsins.integration.cmbo.user.Footer();
	              footer.setErrorCode("1");
	              footer.setErrorDescription(PropertiesUtil.getConfigProperty("unAuthorizedToView"));
	            }
	            userProfile.setFooter(footer);
	          } else {
	            userProfile = new com.tcs.bancsins.integration.cmbo.user.UserProfile();
	            footer = userProfile.getFooter();
	            footer.setErrorCode("1");
	            footer.setErrorDescription(PropertiesUtil.getConfigProperty("unAuthorizedToView"));
	          }
	          loginResponseASBO.setUserProfile(userProfile);
	        }
	      }
	    } else {
	      return loginResponseASBO;
	    }*/
	    return loginResponseASBO;
	  }
	  /**
	   * 
	   * @param logoutRequestASBO
	   * @return
	   */
	  public WebReportsResponseObject processLogout(LogoutRequestASBO logoutRequestASBO)
	  {
	    LogoutResponseASBO logoutResponseASBO = null;
	    LogoutTransformer transformer = new LogoutTransformer();
	    WebReportsRequestObject webReportsRequestObject = null;
	    Header header = logoutRequestASBO.getHeader();
	    LOGGER.info("Inside Process Logout");
	    try {
	      webReportsRequestObject = transformer.transformRequest(logoutRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "request", e);
	    }

	    LogoutOAMOIDResponseASBO logoutOAMOIDResponseASBO = null;
	    try
	    {
	      logoutOAMOIDResponseASBO = (LogoutOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(webReportsRequestObject);
	    }
	    catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    logoutOAMOIDResponseASBO.setHeader(logoutRequestASBO.getHeader());
	    try
	    {
	      logoutResponseASBO = (LogoutResponseASBO)transformer.transformResponse(logoutOAMOIDResponseASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
	    }

	    return logoutResponseASBO;
	  }
	  
	  /**
	   * 
	   * @param isLoggedInRequestASBO
	   * @return
	   */
	  public WebReportsResponseObject processIsLoggedIn(IsLoggedInRequestASBO isLoggedInRequestASBO)
	  {
	    IsLoggedInResponseASBO isLoggedInResponseASBO = null;

	    IsLoggedInTransformer transformer = new IsLoggedInTransformer();
	    WebReportsRequestObject webReportsRequestObject = null;
	    Header header = isLoggedInRequestASBO.getHeader();
	    LOGGER.info("Inside is Loggedin");
	    try
	    {
	      webReportsRequestObject = transformer.transformRequest(isLoggedInRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "request", e);
	    }

	    IsLoggedInOAMOIDResponseASBO isLoggedInOAMOIDResponseASBO = null;
	    try
	    {
	      isLoggedInOAMOIDResponseASBO = (IsLoggedInOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(webReportsRequestObject);
	    }
	    catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    isLoggedInOAMOIDResponseASBO.setHeader(isLoggedInRequestASBO.getHeader());
	    try
	    {
	      isLoggedInResponseASBO = (IsLoggedInResponseASBO)transformer.transformResponse(isLoggedInOAMOIDResponseASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
	    }

	    return isLoggedInResponseASBO;
	  }


/*
	  public WebReportsResponseObject generateOTP(ValidateUserRequestASBO validateUserRequestASBO, ValidateUserTransformer transformer, RegisterOTPDBRequestASBO registerOTPDBRequestASBO, ValidateUserResponseASBO validateUserResponseASBO, GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO, GetContentAlfrescoResponseASBO getContentAlfrescoResponseASBO, OtpGenerationDBResponse otpGenerationDBResponse)
	  {
	    validateUserRequestASBO.getHeader().setEventID("GenerateOTP");

	    Header header = validateUserRequestASBO.getHeader();
	    try {
	      registerOTPDBRequestASBO = transformer.transformRequestForOTPGeneration(validateUserRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "request", e);
	    }

	    try
	    {
	      otpGenerationDBResponse = (OtpGenerationDBResponse)this.dbChannelIntegrator.execute(registerOTPDBRequestASBO);
	    }
	    catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    try
	    {
	      validateUserResponseASBO = transformer.transformResponseForOTPGeneration(otpGenerationDBResponse);

	      validateUserResponseASBO.setUserProfile(validateUserRequestASBO.getUserProfile());

	      validateUserResponseASBO.setHeader(validateUserRequestASBO.getHeader());
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVO(header, "response", e);
	    }

	    if ((null != validateUserRequestASBO.getQuoteNo()) && (!validateUserRequestASBO.getQuoteNo().isEmpty()))
	    {
	      try {
	        sendEmailSMSForApproveQuoteOTP(validateUserRequestASBO, validateUserResponseASBO, getContentAlfrescoRequestASBO, getContentAlfrescoResponseASBO, header);
	      }
	      catch (Exception e)
	      {
	        LOGGER.error(e);
	      }

	    }
	    else
	    {
	      try
	      {
	        getContentAlfrescoRequestASBO = (GetContentAlfrescoRequestASBO)this.messageContentTransformer.transformRegistrationRequest(validateUserRequestASBO);

	        getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	        header = getContentAlfrescoRequestASBO.getHeader();
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      try
	      {
	        getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	      }
	      catch (IntegrationTechnicalException e)
	      {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	      MessageServiceRequestASBO messageServiceRequestASBO;
	      try
	      {
	        messageServiceRequestASBO = (MessageServiceRequestASBO)this.messageTransformer.transformRegisterationRequest(validateUserResponseASBO, getContentAlfrescoResponseASBO);
	      }
	      catch (IntegrationTransformationException e)
	      {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      try
	      {
	        this.messageChannelIntegrator.execute(messageServiceRequestASBO);
	      } catch (IntegrationTechnicalException e) {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	    }

	    validateUserResponseASBO.setOtp("Y");
	    return validateUserResponseASBO;
	  }
*/
	 /* private WebReportsResponseObject sendEmailSMS(ValidateUserRequestASBO validateUserRequestASBO, CreateUserResponseASBO createUserResponseASBO, GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO, GetContentAlfrescoResponseASBO getContentAlfrescoResponseASBO, EmailServiceRequestASBO emailServiceRequestASBO, Header header)
	  {
	    try
	    {
	      getContentAlfrescoRequestASBO = this.messageContentTransformer.transformUserRegistrationRequest(validateUserRequestASBO);

	      getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	      header = getContentAlfrescoRequestASBO.getHeader();
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    try
	    {
	      getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    MessageServiceRequestASBO messageServiceRequestASBO;
	    try
	    {
	      messageServiceRequestASBO = (MessageServiceRequestASBO)this.messageTransformer.transformUserRegistrationRequest(createUserResponseASBO, getContentAlfrescoResponseASBO);
	    }
	    catch (IntegrationTransformationException e)
	    {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    try
	    {
	      this.messageChannelIntegrator.execute(messageServiceRequestASBO);
	    } catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    try
	    {
	      getContentAlfrescoRequestASBO = (GetContentAlfrescoRequestASBO)this.emailContentTransformer.transformRegistrationRequest(validateUserRequestASBO);

	      getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	      header = getContentAlfrescoRequestASBO.getHeader();
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    try
	    {
	      getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    try
	    {
	      createUserResponseASBO.setHeader(validateUserRequestASBO.getHeader());
	      if (validateUserRequestASBO.getAlfrescoInput().getChannel().contains("-")) {
	        String[] channel = validateUserRequestASBO.getAlfrescoInput().getChannel().split("-");
	        createUserResponseASBO.getHeader().setCustomerName(channel[1]);
	        createUserResponseASBO.getHeader().setTypeOfCustomer(channel[0]);
	      }
	      emailServiceRequestASBO = (EmailServiceRequestASBO)this.emailTransformer.transformRegisterationRequest(createUserResponseASBO, getContentAlfrescoResponseASBO);
	    }
	    catch (IntegrationTransformationException e)
	    {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    try
	    {
	      this.emailChannelIntegrator.execute(emailServiceRequestASBO);
	    } catch (IntegrationTechnicalException e) {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    return getContentAlfrescoResponseASBO;
	  }

	  public WebReportsResponseObject generateOtpForForgotPassword(ForgotPasswordRequestASBO forgotPasswordRequestASBO)
	  {
	    LOGGER.info("Inside generateOtpForForgotPassword");
	    RegisterOTPDBRequestASBO registerOTPDBRequestASBO = null;
	    ForgotPasswordTransformer transformer = new ForgotPasswordTransformer();
	    ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO = null;
	    Header header = forgotPasswordRequestASBO.getHeader();
	    OtpGenerationDBResponse otpGenerationDBResponse = null;
	    ForgotPasswordResponse forgotPasswordResponseASBO = null;
	    FetchProfileDetailsResponseASBO fetchProfileDetailsResponseASBO = null;
	    SurveyRequestIntegrationController surveyorController = null;
	    FetchProfileDetailsRequestASBO fetchProfileDetailsRequestASBO = null;
	    com.tcs.bancsins.integration.surveyor.cmbo.UserProfile userProfile = null;

	    GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO = null;
	    GetContentAlfrescoResponseASBO getContentAlfrescoResponseASBO = null;
	    try
	    {
	      forgotPasswordDBRequestASBO = (ForgotPasswordDBRequestASBO)transformer.transformRequest(forgotPasswordRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO = null;
	    try {
	      forgotPasswordDBResponseASBO = (ForgotPasswordDBResponseASBO)this.dbChannelIntegrator.execute(forgotPasswordDBRequestASBO);
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    forgotPasswordDBResponseASBO.setHeader(forgotPasswordDBRequestASBO.getHeader());

	    if ("0".equals(forgotPasswordDBResponseASBO.getStatusCode())) {
	      forgotPasswordDBResponseASBO.getHeader().setEventID("GenerateOTP");

	      header = forgotPasswordDBResponseASBO.getHeader();

	      if ("SURVEYOR".equalsIgnoreCase(forgotPasswordDBResponseASBO.getChannel())) {
	        fetchProfileDetailsRequestASBO = new FetchProfileDetailsRequestASBO();
	        surveyorController = new SurveyRequestIntegrationController();
	        userProfile = new com.tcs.bancsins.integration.surveyor.cmbo.UserProfile();
	        userProfile.setUserID(forgotPasswordRequestASBO.getUserId().toUpperCase());
	        fetchProfileDetailsRequestASBO.setUserProfile(userProfile);
	        fetchProfileDetailsRequestASBO.setHeader(forgotPasswordDBRequestASBO.getHeader());

	        fetchProfileDetailsRequestASBO.getHeader().setEventID("fetchProfileDetails");
	        fetchProfileDetailsResponseASBO = (FetchProfileDetailsResponseASBO)surveyorController.processFetchProfileDetails(fetchProfileDetailsRequestASBO);
	        fetchProfileDetailsResponseASBO.setHeader(forgotPasswordRequestASBO.getHeader());
	        fetchProfileDetailsResponseASBO.getUserProfile().setUserID(forgotPasswordDBRequestASBO.getUserId());

	        if ("0".equalsIgnoreCase(fetchProfileDetailsResponseASBO.getUserProfile().getFooter().getErrorCode())) {
	          try {
	            updateSurveyorProfile(fetchProfileDetailsResponseASBO);
	          }
	          catch (Exception e) {
	            LOGGER.error(e);
	          }

	        }

	      }

	      try
	      {
	        registerOTPDBRequestASBO = (RegisterOTPDBRequestASBO)transformer.transformRequestForOTPGeneration(forgotPasswordDBResponseASBO, fetchProfileDetailsResponseASBO);
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVO(header, "request", e);
	      }

	      registerOTPDBRequestASBO.getHeader().setEventID("GenerateOTP");
	      try {
	        otpGenerationDBResponse = (OtpGenerationDBResponse)this.dbChannelIntegrator.execute(registerOTPDBRequestASBO);
	      }
	      catch (IntegrationTechnicalException e) {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	      try
	      {
	        forgotPasswordResponseASBO = transformer.transformResponseForOTPGeneration(otpGenerationDBResponse);
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVO(header, "response", e);
	      }

	      try
	      {
	        getContentAlfrescoRequestASBO = this.messageContentTransformer.transformForgotPasswordRequest(forgotPasswordRequestASBO);

	        getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	        header = getContentAlfrescoRequestASBO.getHeader();
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      try
	      {
	        getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	      }
	      catch (IntegrationTechnicalException e)
	      {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	      MessageServiceRequestASBO messageServiceRequestASBO;
	      try
	      {
	        messageServiceRequestASBO = (MessageServiceRequestASBO)this.messageTransformer.transformForgotPasswordRequest(forgotPasswordResponseASBO, getContentAlfrescoResponseASBO);
	      }
	      catch (IntegrationTransformationException e)
	      {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      try
	      {
	        this.messageChannelIntegrator.execute(messageServiceRequestASBO);
	      } catch (IntegrationTechnicalException e) {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	    }
	    else
	    {
	      try
	      {
	        forgotPasswordResponseASBO = transformer.transformResponse(forgotPasswordDBResponseASBO);
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	    }

	    forgotPasswordResponseASBO.setOtp("");
	    return forgotPasswordResponseASBO;
	  }


	  public WebReportsResponseObject forgotPasswordNonCust(ForgotPasswordRequestASBO forgotPasswordRequestASBO)
	  {
	    LOGGER.info("Inside forgotPasswordNonCust");
	    ValidateOTPDBRequestASBO validateOTPDBRequestASBO = null;
	    ForgotPasswordTransformer transformer = new ForgotPasswordTransformer();
	    Header header = forgotPasswordRequestASBO.getHeader();
	    ValidateOTPDBResponseASBO validateOTPDBResponseASBO = null;
	    ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO = null;
	    ForgotPasswordResponse forgotPasswordResponseASBO = null;

	    GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO = null;
	    GetContentAlfrescoResponseASBO getContentAlfrescoResponseASBO = null;
	    EmailServiceRequestASBO emailServiceRequestASBO = null;
	    try
	    {
	      validateOTPDBRequestASBO = (ValidateOTPDBRequestASBO)transformer.transformValidateOTPRequest(forgotPasswordRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    try
	    {
	      validateOTPDBResponseASBO = (ValidateOTPDBResponseASBO)this.dbChannelIntegrator.execute(validateOTPDBRequestASBO);
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }
	    catch (Exception e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    if ((null != validateOTPDBResponseASBO) && (validateOTPDBResponseASBO.isValidOTP()))
	    {
	      try {
	        forgotPasswordDBRequestASBO = (ForgotPasswordDBRequestASBO)transformer.transformRequest(forgotPasswordRequestASBO);

	        forgotPasswordDBRequestASBO.getHeader().setEventID("forgotPassword");
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO = null;
	      try {
	        forgotPasswordDBResponseASBO = (ForgotPasswordDBResponseASBO)this.dbChannelIntegrator.execute(forgotPasswordDBRequestASBO);
	      }
	      catch (IntegrationTechnicalException e)
	      {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	      ForgotPasswordOAMOIDRequestASBO forgotPasswordOAMOIDRequestASBO = null;

	      if ("0".equals(forgotPasswordDBResponseASBO.getStatusCode()))
	      {
	        try {
	          forgotPasswordOAMOIDRequestASBO = transformer.transformOAMOIDRequest(forgotPasswordDBResponseASBO);

	          forgotPasswordOAMOIDRequestASBO.setHeader(forgotPasswordDBRequestASBO.getHeader());

	          header = forgotPasswordOAMOIDRequestASBO.getHeader();
	        } catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	        ForgotPasswordOAMOIDResponseASBO forgotPasswordOAMOIDResponseASBO = null;
	        try
	        {
	          forgotPasswordOAMOIDResponseASBO = (ForgotPasswordOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(forgotPasswordOAMOIDRequestASBO);

	          forgotPasswordOAMOIDResponseASBO.setHeader(forgotPasswordRequestASBO.getHeader());
	        }
	        catch (IntegrationTechnicalException e) {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	        try
	        {
	          forgotPasswordResponseASBO = transformer.transformOAMOIDResponse(forgotPasswordOAMOIDResponseASBO);

	          forgotPasswordResponseASBO.setEmailId(forgotPasswordDBResponseASBO.getEmailId());

	          forgotPasswordResponseASBO.setFirstName(forgotPasswordDBResponseASBO.getFirstName());

	          forgotPasswordResponseASBO.setLastName(forgotPasswordDBResponseASBO.getLastName());

	          forgotPasswordResponseASBO.setGender(forgotPasswordDBResponseASBO.getGender());

	          forgotPasswordResponseASBO.setTitle(forgotPasswordDBResponseASBO.getTitle());

	          forgotPasswordResponseASBO.setMobileNo(forgotPasswordDBResponseASBO.getMobileNo());
	        }
	        catch (IntegrationTransformationException e)
	        {
	          return ExceptionUtil.getTransformationErrorVOException(header, "response", e);
	        }

	        Header header1 = new Header();
	        header1.setEventID("getContent");
	        try
	        {
	          getContentAlfrescoRequestASBO = this.messageContentTransformer.transformForgotPasswordMsgRequest(forgotPasswordRequestASBO);

	          getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	          header = getContentAlfrescoRequestASBO.getHeader();
	        }
	        catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	        try
	        {
	          getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	        }
	        catch (IntegrationTechnicalException e)
	        {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	        MessageServiceRequestASBO messageServiceRequestASBO;
	        try
	        {
	          messageServiceRequestASBO = (MessageServiceRequestASBO)this.messageTransformer.transformForgotPasswordMsgRequest(forgotPasswordResponseASBO, getContentAlfrescoResponseASBO);
	        }
	        catch (IntegrationTransformationException e)
	        {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	        try
	        {
	          this.messageChannelIntegrator.execute(messageServiceRequestASBO);
	        } catch (IntegrationTechnicalException e) {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	        try
	        {
	          getContentAlfrescoRequestASBO = (GetContentAlfrescoRequestASBO)this.emailContentTransformer.transformForgotPasswordRequest(forgotPasswordRequestASBO);

	          getContentAlfrescoRequestASBO.setHeader(header1);
	        }
	        catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
	        }

	        try
	        {
	          getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	        }
	        catch (IntegrationTechnicalException e)
	        {
	          return ExceptionUtil.getTechnicalErrorVO(header1, 952, e);
	        }

	        try
	        {
	          forgotPasswordResponseASBO.setChannel(forgotPasswordDBResponseASBO.getChannel());
	          LOGGER.info("Inside Integrator : " + forgotPasswordDBResponseASBO.getChannel());
	          emailServiceRequestASBO = (EmailServiceRequestASBO)this.emailTransformer.transformForgotPasswordRequest(forgotPasswordResponseASBO, getContentAlfrescoResponseASBO);
	        }
	        catch (IntegrationTransformationException e)
	        {
	          return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
	        }

	        try
	        {
	          this.emailChannelIntegrator.execute(emailServiceRequestASBO);
	        } catch (IntegrationTechnicalException e) {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	      }
	      else
	      {
	        try
	        {
	          forgotPasswordResponseASBO = transformer.transformResponse(forgotPasswordDBResponseASBO);
	        }
	        catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	      }

	    }
	    else
	    {
	      forgotPasswordResponseASBO = new ForgotPasswordResponse();

	      if (null != validateOTPDBResponseASBO) {
	        forgotPasswordResponseASBO.setHeader(validateOTPDBResponseASBO.getHeader() != null ? validateOTPDBResponseASBO.getHeader() : validateOTPDBRequestASBO.getHeader());

	        forgotPasswordResponseASBO.setStatusMessage(validateOTPDBResponseASBO.getMessage() != null ? validateOTPDBResponseASBO.getMessage() : "");
	      }

	      forgotPasswordResponseASBO.setStatusCode("1");
	    }
	    forgotPasswordResponseASBO.setPassword("");
	    return forgotPasswordResponseASBO;
	  }

	  public WebReportsResponseObject processForgotPassword(ForgotPasswordRequestASBO forgotPasswordRequestASBO)
	  {
	    LOGGER.info("Inside customer forgot password");
	    ForgotPasswordTransformer transformer = new ForgotPasswordTransformer();
	    ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO = null;

	    GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO = null;
	    GetContentAlfrescoResponseASBO getContentAlfrescoResponseASBO = null;
	    EmailServiceRequestASBO emailServiceRequestASBO = null;
	    Header header = forgotPasswordRequestASBO.getHeader();
	    try
	    {
	      forgotPasswordDBRequestASBO = (ForgotPasswordDBRequestASBO)transformer.transformRequest(forgotPasswordRequestASBO);
	    }
	    catch (IntegrationTransformationException e) {
	      return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	    }

	    ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO = null;
	    try {
	      forgotPasswordDBResponseASBO = (ForgotPasswordDBResponseASBO)this.dbChannelIntegrator.execute(forgotPasswordDBRequestASBO);
	    }
	    catch (IntegrationTechnicalException e)
	    {
	      return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	    }

	    ForgotPasswordOAMOIDRequestASBO forgotPasswordOAMOIDRequestASBO = null;
	    ForgotPasswordResponse forgotPasswordResponseASBO = null;

	    if ("0".equals(forgotPasswordDBResponseASBO.getStatusCode()))
	    {
	      try {
	        forgotPasswordOAMOIDRequestASBO = transformer.transformOAMOIDRequest(forgotPasswordDBResponseASBO);

	        forgotPasswordOAMOIDRequestASBO.setHeader(forgotPasswordDBRequestASBO.getHeader());

	        header = forgotPasswordOAMOIDRequestASBO.getHeader();
	      } catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }

	      ForgotPasswordOAMOIDResponseASBO forgotPasswordOAMOIDResponseASBO = null;
	      try
	      {
	        forgotPasswordOAMOIDResponseASBO = (ForgotPasswordOAMOIDResponseASBO)this.oamoidChannelIntegrator.execute(forgotPasswordOAMOIDRequestASBO);

	        forgotPasswordOAMOIDResponseASBO.setHeader(forgotPasswordRequestASBO.getHeader());
	      }
	      catch (IntegrationTechnicalException e) {
	        return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	      }

	      try
	      {
	        forgotPasswordResponseASBO = transformer.transformOAMOIDResponse(forgotPasswordOAMOIDResponseASBO);

	        forgotPasswordResponseASBO.setEmailId(forgotPasswordDBResponseASBO.getEmailId());

	        forgotPasswordResponseASBO.setFirstName(forgotPasswordDBResponseASBO.getFirstName());

	        forgotPasswordResponseASBO.setLastName(forgotPasswordDBResponseASBO.getLastName());

	        forgotPasswordResponseASBO.setGender(forgotPasswordDBResponseASBO.getGender());

	        forgotPasswordResponseASBO.setTitle(forgotPasswordDBResponseASBO.getTitle());

	        forgotPasswordResponseASBO.setMobileNo(forgotPasswordDBResponseASBO.getMobileNo());
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "response", e);
	      }

	      if ("0".equalsIgnoreCase(forgotPasswordResponseASBO.getStatusCode()))
	      {
	        try
	        {
	          getContentAlfrescoRequestASBO = this.messageContentTransformer.transformForgotPasswordMsgRequest(forgotPasswordRequestASBO);

	          getContentAlfrescoRequestASBO.getHeader().setEventID("getContent");

	          header = getContentAlfrescoRequestASBO.getHeader();
	        }
	        catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	        try
	        {
	          getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	        }
	        catch (IntegrationTechnicalException e)
	        {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	        MessageServiceRequestASBO messageServiceRequestASBO;
	        try
	        {
	          messageServiceRequestASBO = (MessageServiceRequestASBO)this.messageTransformer.transformForgotPasswordMsgRequest(forgotPasswordResponseASBO, getContentAlfrescoResponseASBO);
	        }
	        catch (IntegrationTransformationException e)
	        {
	          return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	        }

	        try
	        {
	          this.messageChannelIntegrator.execute(messageServiceRequestASBO);
	        } catch (IntegrationTechnicalException e) {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	        Header header1 = new Header();
	        header1.setEventID("getContent");
	        try
	        {
	          getContentAlfrescoRequestASBO = (GetContentAlfrescoRequestASBO)this.emailContentTransformer.transformForgotPasswordRequest(forgotPasswordRequestASBO);

	          getContentAlfrescoRequestASBO.setHeader(header1);
	        }
	        catch (IntegrationTransformationException e) {
	          return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
	        }

	        try
	        {
	          getContentAlfrescoResponseASBO = (GetContentAlfrescoResponseASBO)this.domainChannelIntegrator.execute(getContentAlfrescoRequestASBO);
	        }
	        catch (IntegrationTechnicalException e)
	        {
	          return ExceptionUtil.getTechnicalErrorVO(header1, 952, e);
	        }

	        try
	        {
	          forgotPasswordResponseASBO.setChannel(forgotPasswordDBResponseASBO.getChannel());
	          LOGGER.info("Inside Integrator Channel :  " + forgotPasswordDBResponseASBO.getChannel());
	          emailServiceRequestASBO = (EmailServiceRequestASBO)this.emailTransformer.transformForgotPasswordRequest(forgotPasswordResponseASBO, getContentAlfrescoResponseASBO);
	        }
	        catch (IntegrationTransformationException e)
	        {
	          return ExceptionUtil.getTransformationErrorVOException(header1, "request", e);
	        }

	        try
	        {
	          this.emailChannelIntegrator.execute(emailServiceRequestASBO);
	        } catch (IntegrationTechnicalException e) {
	          return ExceptionUtil.getTechnicalErrorVO(header, 952, e);
	        }

	      }

	    }
	    else
	    {
	      try
	      {
	        forgotPasswordResponseASBO = transformer.transformResponse(forgotPasswordDBResponseASBO);
	      }
	      catch (IntegrationTransformationException e) {
	        return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	      }
	    }

	    forgotPasswordResponseASBO.setPassword("");
	    return forgotPasswordResponseASBO;
	  }*/

}
