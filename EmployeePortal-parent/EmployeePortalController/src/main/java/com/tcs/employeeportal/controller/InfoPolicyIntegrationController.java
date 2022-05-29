/**
 * 
 */
package com.tcs.employeeportal.controller;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.employeeportal.asbo.infopolicy.request.GetInfoPolicyDetailsRequestASBO;
import com.tcs.employeeportal.asbo.infopolicy.request.InfoPolicyAcceptRequestASBO;
import com.tcs.employeeportal.asbo.infopolicy.response.GetInfoPolicyDetailsResponseASBO;
import com.tcs.employeeportal.asbo.infopolicy.response.InfoPolicyAcceptResponseASBO;
import com.tcs.employeeportal.component.integrator.DBChannelIntegrator;
import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.ErrorVO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.transformation.infopolicy.InfoPolicyAcceptTransformer;
import com.tcs.employeeportal.util.ExceptionUtil;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;
import com.tcs.employeeportal.vo.cmo.Header;

/**
 * @author 738796
 *
 */
public class InfoPolicyIntegrationController {
	
	/** The Constant LOGGER. .... */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

	/** The db channel integrator. */
	private DBChannelIntegrator dbChannelIntegrator;
	
	/** The login alfresco content transformer. */
	private InfoPolicyAcceptTransformer infoPolicyAcceptTransformer;

	/**
	 * Instantiates a new login integration controller.
	 */
	public InfoPolicyIntegrationController() {
		dbChannelIntegrator = new DBChannelIntegrator();
		infoPolicyAcceptTransformer= new InfoPolicyAcceptTransformer();

	}

	/**
	 * @param getInfoPolicyDetailsRequestASBO
	 * @return
	 */
	public EmployeePortalResponseObject processGetInfoPolicyDetails(GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO) {
		
		LOGGER.info("Inside LoginiIntegrationController get User details");
		GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO;
		GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO;
		GetInfoPolicyDetailsResponseASBO getInfoPolicyDetailsResponseASBO;
		Header header = getInfoPolicyDetailsRequestASBO.getHeader();
		ErrorVO error=new ErrorVO();
		try {
			getInfoPolicyDetailsDBRequestASBO = (GetInfoPolicyDetailsDBRequestASBO) infoPolicyAcceptTransformer.transformInfoPolicyRequest(getInfoPolicyDetailsRequestASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}
		// calling the db 
		LOGGER.debug(" getUserDetailsTransformer.transformLoginRequest request received as----->" +new Gson().toJson(getInfoPolicyDetailsDBRequestASBO));
		
		try {
			getInfoPolicyDetailsDBResponseASBO = (GetInfoPolicyDetailsDBResponseASBO) dbChannelIntegrator.execute(getInfoPolicyDetailsDBRequestASBO);
			getInfoPolicyDetailsDBResponseASBO.setHeader(header);
			LOGGER.debug("response received as----->"+new Gson().toJson(getInfoPolicyDetailsDBResponseASBO));
			
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		}
			try {
				getInfoPolicyDetailsResponseASBO = (GetInfoPolicyDetailsResponseASBO) infoPolicyAcceptTransformer.transformInfoPolicyResponse(getInfoPolicyDetailsDBResponseASBO);
				LOGGER.info("getUserDetailsTransformer.transformLoginResponse");
			} catch (IntegrationTransformationException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
			}
		return getInfoPolicyDetailsResponseASBO;
	}
	

	/**
	 * @param infoPolicyAcceptRequestASBO
	 * @return
	 */
	public EmployeePortalResponseObject processGetInfoAcceptPolicy(InfoPolicyAcceptRequestASBO infoPolicyAcceptRequestASBO)
	{
		LOGGER.info("Inside LoginiIntegrationControllerprocessGetInfoAcceptPolicy");
		InfoPolicyAcceptResponseASBO infoPolicyAcceptResponseASBO;
		InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO;
		InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO;
		Header header = infoPolicyAcceptRequestASBO.getHeader();
	try {
			infoPolicyAcceptDBRequestASBO = (InfoPolicyAcceptDBRequestASBO) infoPolicyAcceptTransformer.transformInfoAcceptPolicyRequest(infoPolicyAcceptRequestASBO);

		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
		}
		// calling the db 
		LOGGER.debug(" getUserDetailsTransformer.transformLoginRequest request received as----->" +new Gson().toJson(infoPolicyAcceptDBRequestASBO));
		
	try {
		infoPolicyAcceptDBResponseASBO = (InfoPolicyAcceptDBResponseASBO) dbChannelIntegrator.execute(infoPolicyAcceptDBRequestASBO);
		infoPolicyAcceptDBResponseASBO.setHeader(header);
		LOGGER.debug("response received as----->"+new Gson().toJson(infoPolicyAcceptDBResponseASBO));
		
	} catch (IntegrationTechnicalException e) {
		LOGGER.error(e.getStackTrace());
		return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

	}
	try {
		infoPolicyAcceptResponseASBO = (InfoPolicyAcceptResponseASBO) infoPolicyAcceptTransformer.transformInfoAcceptPolicyResponse(infoPolicyAcceptDBResponseASBO);
		LOGGER.info("getUserDetailsTransformer.transformInfoAcceptPolicyResponse");
	} catch (IntegrationTransformationException e) {
		LOGGER.error(e.getStackTrace());
		return ExceptionUtil.getTransformationErrorVOException(header, "request", e);
	}
	return infoPolicyAcceptResponseASBO;
	}
		
}
