/**
 * 
 */
package com.tcs.employeeportal.transformation.email.transformers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.asbo.alfresco.ContentDataASBO;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.asbo.login.response.ForgotPasswordResponse;
import com.tcs.employeeportal.email.asbo.request.EmailServiceRequestASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;

/**
 * @author 738566
 *
 */
public class EmailTransformer {

	/** The Constant LOGGER. .. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

	/** The get content alfresco request asbo. */
	GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;

	/** The get content alfresco response asbo. */
	GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO;
	
	/** The email service request asbo. */
	EmailServiceRequestASBO emailServiceRequestASBO;
	
	/** The forgot password response asbo. */
	ForgotPasswordResponse forgotPasswordResponseASBO;
	
	
	/**
	 * Instantiates a new email transformer.
	 */
	public EmailTransformer() {
		emailServiceRequestASBO = new EmailServiceRequestASBO();
		
	}


	/**
	 * Transform forgot password request.
	 * 
	 * @param forgotPasswordResponseASBO
	 *            the forgot password response asbo
	 * @param getAlfrescoContentResponseASBO
	 *            the get content alfrescoresponse asbo
	 * @return the employeeportal request object
	 * @throws IntegrationTransformationException
	 *             the integration transformation exception
	 */
	public EmployeePortalRequestObject transformForgotPasswordRequest(
			ForgotPasswordResponse forgotPasswordResponseASBO,
			GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO)
			throws IntegrationTransformationException {

		LOGGER.info("email tranformer transform Forgot Password Request request method");

		String content = null;
		this.forgotPasswordResponseASBO = forgotPasswordResponseASBO;
		getContentAlfrescoResponseASBO = getAlfrescoContentResponseASBO;

		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate);

		ContentDataASBO contentData = null;
		for (int i = 0; i < getContentAlfrescoResponseASBO.getContentDataList()
				.size(); i++) {
			LOGGER.debug("process : "
					+ getContentAlfrescoResponseASBO.getContentDataList()
							.get(i).getProcess());
			if (getContentAlfrescoResponseASBO.getContentDataList().get(i)
					.getProcess().contains("[ForgotPassword]") && getContentAlfrescoResponseASBO.getContentDataList().get(i)
					.getChannel().contains("[EMPLOYEE]")) {
				contentData = getContentAlfrescoResponseASBO
						.getContentDataList().get(i);
				break;
			}

		}

		if (contentData != null) {
			content = contentData.getContent();
		} else {
			content = UtilConstants.FORGOT_PASSWORD_EMAIL;
		}

		
			String content1 = content.replace("{forgotPasswordDate}", currentDate);
			String content2 = content1.replace("{UserId}",
					forgotPasswordResponseASBO.getUserId());
			String content3 = content2.replace("{password}",
					forgotPasswordResponseASBO.getPassword());
			

		// java.util.Map<String,String>
		// data=getUserData(createUserRequestASBO.getUserProfile());

		java.util.Map<String, String> data = new HashMap<String, String>();
		data.put("EMAILID", forgotPasswordResponseASBO.getEmailId());// not
																		// available
																		// as of
																		// now
		data.put("MAILTYPE", "FORGOTPASSWORD");
		data.put("MESSAGE", content3);
		data.put("SUBJECT", contentData != null ? contentData.getTitle()
				: "FORGOT PASSWORD");

		emailServiceRequestASBO.setData(data);

		return emailServiceRequestASBO;
	}

}
