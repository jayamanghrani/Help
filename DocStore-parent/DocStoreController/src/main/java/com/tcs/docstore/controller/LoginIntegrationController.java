/**
 * 
 */
package com.tcs.docstore.controller;

import org.apache.log4j.Logger;

import com.tcs.docstore.component.integrator.DomainChannelIntegrator;
import com.tcs.docstore.component.integrator.OAMOIDChannelIntegrator;
import com.tcs.docstore.asbo.login.response.ChangePasswordResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.login.asbo.response.ChangePasswordOAMOIDResponseASBO;
import com.tcs.docstore.transformation.login.transformers.ChangePasswordTransformer;
import com.tcs.docstore.util.ExceptionUtil;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.Header;
import com.tcs.docstore.asbo.login.request.ChangePasswordRequestASBO;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class LoginIntegrationController {
	

	/** The Constant LOGGER. .... */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	/** The oimoid channel integrator. */
	private OAMOIDChannelIntegrator oamoidChannelIntegrator;

	/** The domain channel integrator. */
	private DomainChannelIntegrator domainChannelIntegrator;


	/**
	 * Instantiates a new login integration controller.
	 */
	public LoginIntegrationController() {
		oamoidChannelIntegrator = new OAMOIDChannelIntegrator();
		domainChannelIntegrator = new DomainChannelIntegrator();

	}


	/**
	 * Process change password.
	 * 
	 * @param changePasswordRequestASBO
	 *            the change password request asbo
	 * @return the ba ncs integration response vo
	 */
	public DocStoreResponseObject processChangePassword(
			ChangePasswordRequestASBO changePasswordRequestASBO) {
		LOGGER.info("Inside Change password");
		ChangePasswordResponseASBO changePasswordResponseASBO = null;
		ChangePasswordTransformer transformer = new ChangePasswordTransformer();
		DocStoreRequestObject docStoreRequestObject = null;
		Header header = changePasswordRequestASBO.getHeader();
		
		try {
			docStoreRequestObject = transformer
					.transformRequest(changePasswordRequestASBO);

		} catch (IntegrationTransformationException e) {
			return ExceptionUtil.getTransformationErrorVO(header, "request", e);
		}

		ChangePasswordOAMOIDResponseASBO changePasswordOAMOIDResponseASBO = null;

		try {
			changePasswordOAMOIDResponseASBO = (ChangePasswordOAMOIDResponseASBO) oamoidChannelIntegrator
					.execute(docStoreRequestObject);
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
