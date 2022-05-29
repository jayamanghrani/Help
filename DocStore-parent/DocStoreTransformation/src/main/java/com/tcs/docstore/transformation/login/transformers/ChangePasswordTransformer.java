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
package com.tcs.docstore.transformation.login.transformers;

import com.tcs.docstore.asbo.login.request.ChangePasswordRequestASBO;
import com.tcs.docstore.asbo.login.response.ChangePasswordResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.login.asbo.request.ChangePasswordOAMOIDRequestASBO;
import com.tcs.docstore.login.asbo.response.ChangePasswordOAMOIDResponseASBO;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;


// TODO: Auto-generated Javadoc
/**
 * The Class ChangePasswordTransformer.
 * 
 * @author 738566
 */
public class ChangePasswordTransformer {

	/** The change password request asbo. */
	private ChangePasswordRequestASBO changePasswordRequestASBO;

	/** The change password response asbo. */
	private ChangePasswordResponseASBO changePasswordResponseASBO;

	/** The change password oamoid request asbo. */
	private ChangePasswordOAMOIDRequestASBO changePasswordOAMOIDRequestASBO;

	/** The change password oamoid response asbo. */
	private ChangePasswordOAMOIDResponseASBO changePasswordOAMOIDResponseASBO;

	/**
	 * Instantiates a new change password transformer.
	 */
	public ChangePasswordTransformer() {
		super();
		this.changePasswordOAMOIDRequestASBO = new ChangePasswordOAMOIDRequestASBO();
		this.changePasswordResponseASBO = new ChangePasswordResponseASBO();
	}

	/**
	 * Transform request.
	 * 
	 * @param changePasswordRequestASBO
	 *            the change password request asbo
	 * @return the integration request vo
	 * @throws IntegrationTransformationException
	 *             the integration transformation exception
	 */
	public DocStoreRequestObject transformRequest(
			ChangePasswordRequestASBO changePasswordRequestASBO)
			throws IntegrationTransformationException {
		this.changePasswordRequestASBO = changePasswordRequestASBO;
		this.changePasswordOAMOIDRequestASBO
				.setHeader(this.changePasswordRequestASBO.getHeader());
		transformRequest();
		return this.changePasswordOAMOIDRequestASBO;
	}

	/**
	 * Transform request.
	 */
	private void transformRequest() {
		changePasswordOAMOIDRequestASBO.setFlag(changePasswordRequestASBO
				.getFlag());
		changePasswordOAMOIDRequestASBO
				.setOldPassword(changePasswordRequestASBO.getOldPassword());
		changePasswordOAMOIDRequestASBO
				.setNewPassword(changePasswordRequestASBO.getNewPassword());
		changePasswordOAMOIDRequestASBO.setUserId(changePasswordRequestASBO.getUserId());

	}

	/**
	 * Transform response.
	 * 
	 * @param changePasswordOAMOIDResponseASBO
	 *            the change password oamoid response asbo
	 * @return the integration response vo
	 * @throws IntegrationTransformationException
	 *             the integration transformation exception
	 */
	public DocStoreResponseObject transformResponse(
			ChangePasswordOAMOIDResponseASBO changePasswordOAMOIDResponseASBO)
			throws IntegrationTransformationException {
		this.changePasswordOAMOIDResponseASBO = changePasswordOAMOIDResponseASBO;

		this.changePasswordResponseASBO
				.setHeader(this.changePasswordOAMOIDResponseASBO.getHeader());
		transformResponse();
		return this.changePasswordResponseASBO;
	}

	/**
	 * Transform response.
	 */
	private void transformResponse() {
		changePasswordResponseASBO
				.setStatusMessage(changePasswordOAMOIDResponseASBO
						.getStatusMessage());
		changePasswordResponseASBO.setStatusCode(changePasswordOAMOIDResponseASBO.getStatusCode());
	}

}
