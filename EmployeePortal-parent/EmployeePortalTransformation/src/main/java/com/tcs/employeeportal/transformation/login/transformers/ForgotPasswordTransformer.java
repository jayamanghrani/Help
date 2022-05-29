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
package com.tcs.employeeportal.transformation.login.transformers;

import com.tcs.employeeportal.asbo.login.request.ForgotPasswordRequestASBO;
import com.tcs.employeeportal.asbo.login.response.ForgotPasswordResponse;
import com.tcs.employeeportal.db.asbo.request.ForgotPasswordDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.ForgotPasswordDBResponseASBO;
import com.tcs.employeeportal.login.asbo.request.ForgotPasswordOAMOIDRequestASBO;
import com.tcs.employeeportal.login.asbo.response.ForgotPasswordOAMOIDResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordTransformer.
 */
public class ForgotPasswordTransformer {

	/** The forgot password request asbo. */
	private ForgotPasswordRequestASBO forgotPasswordRequestASBO;
	
	/** The forgot password db request asbo. */
	private ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO;
	
	/** The forgot password db response asbo. */
	private ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO;
	
	/** The forgot password oamoid response asbo. */
	private ForgotPasswordOAMOIDResponseASBO forgotPasswordOAMOIDResponseASBO;
	
	/** The forgot password response. */
	private ForgotPasswordResponse forgotPasswordResponse;
	
	/** The forgot password oamoid request asbo. */
	private ForgotPasswordOAMOIDRequestASBO forgotPasswordOAMOIDRequestASBO;
	
	/** The forgot password response2. */
	private ForgotPasswordResponse forgotPasswordResponse2;

	/** The forgot password request asb o2. */
	private ForgotPasswordRequestASBO forgotPasswordRequestASBO2;
	
	/** The forgot password db response asb o2. */
	private ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO2;

	/**
	 * Instantiates a new forgot password transformer.
	 */
	public ForgotPasswordTransformer() {
		forgotPasswordDBRequestASBO = new ForgotPasswordDBRequestASBO();
		forgotPasswordResponse = new ForgotPasswordResponse();
		forgotPasswordOAMOIDRequestASBO = new ForgotPasswordOAMOIDRequestASBO();
		forgotPasswordResponse2 = new ForgotPasswordResponse();
	}

	/**
	 * Transform request.
	 *
	 * @param forgotPasswordRequestASBO the forgot password request asbo
	 * @return the employeeportal request object
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalRequestObject transformRequest(
			ForgotPasswordRequestASBO forgotPasswordRequestASBO)
			throws IntegrationTransformationException {
		this.forgotPasswordRequestASBO = forgotPasswordRequestASBO;
		this.forgotPasswordDBRequestASBO
				.setHeader(this.forgotPasswordRequestASBO.getHeader());
		transformRequest();
		return forgotPasswordDBRequestASBO;
	}

	/**
	 * Transform request.
	 */
	private void transformRequest() {
		this.forgotPasswordDBRequestASBO
				.setUserId(this.forgotPasswordRequestASBO.getUserId());
		this.forgotPasswordDBRequestASBO.setDob(this.forgotPasswordRequestASBO
				.getDob());

	}

	/**
	 * Transform oamoid request.
	 *
	 * @param forgotPasswordDBResponseASBO the forgot password db response asbo
	 * @return the forgot password oamoid request asbo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public ForgotPasswordOAMOIDRequestASBO transformOAMOIDRequest(
			ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO)
			throws IntegrationTransformationException {
		this.forgotPasswordDBResponseASBO = forgotPasswordDBResponseASBO;
		this.forgotPasswordOAMOIDRequestASBO
				.setHeader(this.forgotPasswordDBResponseASBO.getHeader());
		transformOAMOIDRequest();
		return forgotPasswordOAMOIDRequestASBO;
	}

	/**
	 * Transform oamoid request.
	 */
	private void transformOAMOIDRequest() {
		this.forgotPasswordOAMOIDRequestASBO
				.setUserId(forgotPasswordDBResponseASBO.getUserId());
		this.forgotPasswordOAMOIDRequestASBO
				.setPassword(forgotPasswordDBResponseASBO.getPassword());

	}

	/**
	 * Transform oamoid response.
	 *
	 * @param forgotPasswordOAMOIDResponseASBO the forgot password oamoid response asbo
	 * @return the forgot password response
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public ForgotPasswordResponse transformOAMOIDResponse(
			ForgotPasswordOAMOIDResponseASBO forgotPasswordOAMOIDResponseASBO)
			throws IntegrationTransformationException {
		this.forgotPasswordOAMOIDResponseASBO = forgotPasswordOAMOIDResponseASBO;
		this.forgotPasswordResponse
				.setHeader(this.forgotPasswordOAMOIDResponseASBO.getHeader());
		transformOAMOIDResponse();
		return forgotPasswordResponse;
	}

	/**
	 * Transform oamoid response.
	 */
	private void transformOAMOIDResponse() {
		forgotPasswordResponse
				.setStatusCode(this.forgotPasswordOAMOIDResponseASBO
						.getStatusCode());
		forgotPasswordResponse
				.setStatusMessage(this.forgotPasswordOAMOIDResponseASBO
						.getStatusMessage());
		forgotPasswordResponse.setUserId(this.forgotPasswordOAMOIDResponseASBO
				.getUserId());
		forgotPasswordResponse
				.setPassword(this.forgotPasswordOAMOIDResponseASBO
						.getPassword());

	}



	/**
	 * Transform response.
	 *
	 * @param forgotPasswordDBResponseASBO the forgot password db response asbo
	 * @return the forgot password response
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public ForgotPasswordResponse transformResponse(
			ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO)
			throws IntegrationTransformationException {
		this.forgotPasswordDBResponseASBO = forgotPasswordDBResponseASBO;
		this.forgotPasswordResponse.setHeader(this.forgotPasswordDBResponseASBO
				.getHeader());
		transformResponse();
		return forgotPasswordResponse;
	}

	/**
	 * Transform response.
	 */
	private void transformResponse() {
		forgotPasswordResponse.setStatusCode(this.forgotPasswordDBResponseASBO
				.getStatusCode());
		forgotPasswordResponse
				.setStatusMessage(this.forgotPasswordDBResponseASBO
						.getStatusMessage());
		forgotPasswordResponse.setUserId(this.forgotPasswordDBResponseASBO
				.getUserId());
		forgotPasswordResponse.setPassword(this.forgotPasswordDBResponseASBO
				.getPassword());

	}


}
