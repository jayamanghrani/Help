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
package com.tcs.employeeportal.transformation.alfresco.transformers;

import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.asbo.login.request.ForgotPasswordRequestASBO;
import com.tcs.employeeportal.cache.util.CacheConstants;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * The Class MessageContentTransformer.
 */
public class MessageContentTransformer {

	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;

	/** The forgot password request asbo. */
	private ForgotPasswordRequestASBO forgotPasswordRequestASBO;
	
	/**
	 * Instantiates a new message content transformer.
	 */
	public MessageContentTransformer(){
		this.getContentAlfrescoRequestASBO = new GetAlfrecoContentRequestASBO();
	}

	/**
	 * Transform forgot password msg request.
	 *
	 * @param forgotPasswordRequestASBO the forgot password request asbo
	 * @return the gets the content alfresco request asbo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalRequestObject transformForgotPasswordMsgRequest(ForgotPasswordRequestASBO forgotPasswordRequestASBO)throws IntegrationTransformationException {
		
		this.forgotPasswordRequestASBO=forgotPasswordRequestASBO;
		this.getContentAlfrescoRequestASBO.setHeader(this.forgotPasswordRequestASBO.getHeader());
		this.transformForgotPasswordMsgRequest();
		return this.getContentAlfrescoRequestASBO;
		
		
	}

	/**
	 * Transform forgot password msg request.
	 */
	private void transformForgotPasswordMsgRequest() {
	
		getContentAlfrescoRequestASBO.setChannel(forgotPasswordRequestASBO.getAlfrescoInput().getChannel());
	//	getContentAlfrescoRequestASBO.setLanguage(forgotPasswordRequestASBO.getAlfrescoInput().getLanguage());
		getContentAlfrescoRequestASBO.setTypeOfContent(CacheConstants.SMS_CONTENT);
		getContentAlfrescoRequestASBO.setProcess("ForgotPassword");
		
	}
}
