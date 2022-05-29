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
package com.tcs.employeeportal.transformation.message.transformers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.asbo.alfresco.ContentDataASBO;
import com.tcs.employeeportal.asbo.login.response.ForgotPasswordResponse;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.message.asbo.request.MessageServiceRequestASBO;
import com.tcs.employeeportal.util.UtilConstants;

/**
 * The Class MessageTransformer.
 */
/**
 * @author 738566
 *
 */

public class MessageTransformer {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger
			.getLogger("empPortalLogger");
//	private GetContentAlfrescoRequestASBO getContentAlfrescoRequestASBO;

	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO;
	
	/** The message service request asbo. */
	private MessageServiceRequestASBO messageServiceRequestASBO;
	
	/** The forgot password response asbo. */
	private ForgotPasswordResponse forgotPasswordResponseASBO;


	/**
	 * Instantiates a new message transformer.
	 */
	public MessageTransformer() {
		messageServiceRequestASBO = new MessageServiceRequestASBO();
	
	}


	public EmployeePortalRequestObject transformForgotPasswordMsgRequest(
			ForgotPasswordResponse forgotPasswordResponseASBO,
			GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO) throws IntegrationTransformationException{

		LOGGER.info("message tranformer transformForgotPasswordMsgRequest request method");
		
		this.forgotPasswordResponseASBO = forgotPasswordResponseASBO;
		getContentAlfrescoResponseASBO = getAlfrescoContentResponseASBO;

		ContentDataASBO contentData = null;

		for (int i = 0; i < getContentAlfrescoResponseASBO.getContentDataList()
				.size(); i++) {
			LOGGER.debug("getContentAlfrescoResponseASBO.getContentDataList()getProcess().contains[ForgotPassword]"+getContentAlfrescoResponseASBO.getContentDataList().get(i)
					.getProcess().contains("[ForgotPassword]"));
			if (getContentAlfrescoResponseASBO.getContentDataList().get(i)
					.getProcess().contains("[ForgotPassword]") && getContentAlfrescoResponseASBO.getContentDataList().get(i)
					.getChannel().contains("[EMPLOYEE]")) {
				LOGGER.debug("INSIDE IF [ForgotPassword]"+getContentAlfrescoResponseASBO.getContentDataList().get(i)
						.getProcess().contains("[ForgotPassword]"));
				contentData = getContentAlfrescoResponseASBO
						.getContentDataList().get(i);
				System.out.println("contentData----"+contentData);
				break;
			}

		}
		
		String content=null;

		if(null!=contentData){
		content = contentData.getContent();
		LOGGER.info("content----"+content);
		}
		else{
			content=UtilConstants.FORGOT_PASSWORD_SMS;
			
		}
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate);

		
		
		String content1 = content.replace("{currentDate}", currentDate);
		String content2 = content1.replace("{UserId}", forgotPasswordResponseASBO.getUserId());
		String content3 = content2.replace("{password}", forgotPasswordResponseASBO.getPassword());
		
		java.util.Map<String, String> data = new HashMap<String, String>();
		data.put("EMAILID", forgotPasswordResponseASBO.getEmailId());
		data.put("mobileNo",forgotPasswordResponseASBO.getMobileNo());
		data.put("messageType", "FORGOTPASSWORDMSG");
		data.put("message", content3+"\n");
		data.put("date", currentDate);
		

		messageServiceRequestASBO.setData(data);

		return messageServiceRequestASBO;
	}

	
	public EmployeePortalRequestObject transformMobileAppMsgResponse(
			String mobileNo, String app) throws IntegrationTransformationException{

		LOGGER.info("message tranformer transformMobileAppMsgResponse response method");
		
		String content=UtilConstants.MOBILEAPP_SMS;
		String finalContent = content.replace("{appLink}", app);
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate);
		
		java.util.Map<String, String> data = new HashMap<String, String>();
	//	data.put("EMAILID", forgotPasswordResponseASBO.getEmailId());
		data.put("mobileNo",mobileNo);
		data.put("messageType", "MOBILEAPPLINK");
		data.put("message", finalContent+"\n");
		data.put("date", currentDate);
		

		messageServiceRequestASBO.setData(data);

		return messageServiceRequestASBO;
	}

}
