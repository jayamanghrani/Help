/**
 * 
 */
package com.tcs.employeeportal.controller;

import com.tcs.employeeportal.vo.cmo.Header;
import org.apache.log4j.Logger;

import com.tcs.employeeportal.asbo.emp.request.ContactUsRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.ContactUsResponseASBO;
import com.tcs.employeeportal.component.integrator.MessageChannelIntegrator;
import com.tcs.employeeportal.config.utils.PropertiesUtil;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.message.asbo.request.MessageServiceRequestASBO;
import com.tcs.employeeportal.transformation.message.transformers.MessageTransformer;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;



/**
 * @author 585226
 *
 */
public class CommonComponentIntegrationController {
	
	private static final Logger LOGGER = Logger.getLogger(CommonComponentIntegrationController.class);
	
	/** The message transformer. */
	private MessageTransformer messageTransformer;
	
	/** The message channel integrator. */
	private MessageChannelIntegrator messageChannelIntegrator;
	
	/**
	 * Instantiates a new common component integration controller.
	 */
	public CommonComponentIntegrationController() {
		
		messageTransformer = new MessageTransformer();
		messageChannelIntegrator = new MessageChannelIntegrator();
		
	}
	
	/**
	 * Process mobile app link.
	 *
	 * @param contactUsRequestASBO the contact us request asbo
	 * @return the ba ncs integration response object
	 */
	/*	public EmployeePortalResponseObject processMobileAppLink(ContactUsRequestASBO contactUsRequestASBO){
		ContactUsResponseASBO contactUsResponseASBO=new ContactUsResponseASBO();
		LOGGER.info("EmployeePortalResponseObject>>>>>>>>>>>processMobileAppLink<<<<<<<<<<<<<");
		
		Header header=contactUsRequestASBO.getHeader();
		
		String AndroidURL =PropertiesUtil.getConfigProperty("AndroidApp");
		String iOSURL=PropertiesUtil.getConfigProperty("iOSApp");
		String appLink=null;
		
		if(contactUsRequestASBO.getName().equalsIgnoreCase("Android")){
			LOGGER.debug("the selected application is android");
			appLink=AndroidURL;
		}
	

		if(contactUsRequestASBO.getName().equalsIgnoreCase("iOS")){
			LOGGER.debug("the selected application is iOS");
			appLink=iOSURL;
		}
	
		MessageServiceRequestASBO messageServiceRequestASBO=null;
		try {
			messageServiceRequestASBO = (MessageServiceRequestASBO) messageTransformer.transformMobileAppMsgResponse(contactUsRequestASBO.getMobileNo(), appLink);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}

		try {

			messageChannelIntegrator.execute(messageServiceRequestASBO);
			contactUsResponseASBO.setStatusCode("0");
			contactUsResponseASBO.setStatusMessage("Link has been sent to your mobile number");
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
		//	return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);
			contactUsResponseASBO.setStatusCode("1");
			contactUsResponseASBO.setStatusMessage("Error sending link to mobile number");

		}
		
		
		
		return contactUsResponseASBO;
	}
*/
}