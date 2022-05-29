package com.tcs.employeeportal.component.invoker;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.db.asbo.request.ForgotPasswordDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.ForgotPasswordDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.persist.service.ForgotPasswordService;
import com.tcs.employeeportal.persist.service.ForgotPasswordServiceImpl;
import com.tcs.employeeportal.persist.service.GetTickerValuesService;
import com.tcs.employeeportal.persist.service.GetTickerValuesServiceImpl;
import com.tcs.employeeportal.persist.service.GetUserDetailsService;
import com.tcs.employeeportal.persist.service.GetUserDetailsServiceImpl;
import com.tcs.employeeportal.persist.service.InfoPolicyService;
import com.tcs.employeeportal.persist.service.InfoPolicyServiceImpl;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class EmployeeDBInvoker {

	private static final Logger LOGGER = Logger.getLogger(EmployeeDBInvoker.class);

	private ForgotPasswordService forgotPasswordService;
	private GetTickerValuesService gettickervalueservice;
	private GetUserDetailsService getUserDetailsService;
	private InfoPolicyService infoPolicyService;
	
	public EmployeeDBInvoker(){

		forgotPasswordService= new ForgotPasswordServiceImpl();
		gettickervalueservice = new GetTickerValuesServiceImpl();
		getUserDetailsService=new GetUserDetailsServiceImpl();
		infoPolicyService=new InfoPolicyServiceImpl();
	}

	public EmployeePortalResponseObject invokeDB(
			EmployeePortalRequestObject requestVO)
					throws IntegrationTechnicalException {
		LOGGER.debug("Inside dBInvoker---" +requestVO.getHeader().getEventID());


		if(UtilConstants.FORGOT_PASSWORD.equalsIgnoreCase(requestVO.getHeader().getEventID())){
			return forgotPassword(requestVO);
		}

		if(UtilConstants.GET_USER_DETAILS.equalsIgnoreCase(requestVO.getHeader().getEventID())){
			return getUserDetails(requestVO);
		}
		// below code added to get the ticker values // not in use now as we have cached the ticker
		else if (UtilConstants.GET_TICKER_VALUES.equalsIgnoreCase(requestVO
				.getHeader().getEventID())) {
			LOGGER.debug("Inside dBInvoker GET_TICKER_VALUES");
			return getTickerValues(requestVO);
		}
		
		else if(UtilConstants.GET_INFO_POLICY_DETAILS.equalsIgnoreCase(requestVO.getHeader().getEventID())){
			
			return getInfoPolicyDetails(requestVO);
		}
		
		else if(UtilConstants.GET_INFO_ACCEPT_POLICY.equalsIgnoreCase(requestVO.getHeader().getEventID()))
		{
			return getInfoAcceptPolicy(requestVO);
		}

		else {
			return null;
		}

	}


	private EmployeePortalResponseObject forgotPassword(
			EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {

		ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO= null;
		ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO=null;
		if (requestVO instanceof ForgotPasswordDBRequestASBO) {
			forgotPasswordDBRequestASBO = (ForgotPasswordDBRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		forgotPasswordDBResponseASBO= forgotPasswordService.getUserDetails(forgotPasswordDBRequestASBO);

		return forgotPasswordDBResponseASBO;

	}

	
	private EmployeePortalResponseObject getTickerValues(EmployeePortalRequestObject requestVO)  throws IntegrationTechnicalException{
		// TODO Auto-generated method stub

		LOGGER.info("###Inside getTickerValues  1###");

		TickerDBRequestASBO getTickerValues = null;

		if (requestVO instanceof EmployeePortalRequestObject) { 

			try{

				getTickerValues =  (TickerDBRequestASBO) requestVO;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			LOGGER.debug("###Inside getTickerValues  2###");
		} 
		else {

			throw new IntegrationTechnicalException();

		}

		LOGGER.info("just before the calling of service class getTickerValues");
		// Call DB Method and set response
		return gettickervalueservice.getTickerValues(getTickerValues);
	}
	
	private EmployeePortalResponseObject getUserDetails(
			EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {

		GetUserDetailsDBRequestASBO getUserDetailsDBRequestASBO= null;
		GetUserDetialsDBResponseASBO getUserDetialsDBResponseASBO=null;
		if (requestVO instanceof GetUserDetailsDBRequestASBO) {
			getUserDetailsDBRequestASBO = (GetUserDetailsDBRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		getUserDetialsDBResponseASBO= getUserDetailsService.getUserDetails(getUserDetailsDBRequestASBO);

		return getUserDetialsDBResponseASBO;

	}
	
	private EmployeePortalResponseObject getInfoPolicyDetails(
			EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {
LOGGER.info("Inside getInfoPolicyDetails dbInvoker");
		GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO= null;
		GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO=null;
		if (requestVO instanceof GetInfoPolicyDetailsDBRequestASBO) {
			getInfoPolicyDetailsDBRequestASBO = (GetInfoPolicyDetailsDBRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		getInfoPolicyDetailsDBResponseASBO= infoPolicyService.getInfoPolicyDetails(getInfoPolicyDetailsDBRequestASBO);
		LOGGER.info("EmployeeDbInvoker getInfoPolicyDetailsDBResponseASBO" + getInfoPolicyDetailsDBResponseASBO);
		LOGGER.info("getInfoPolicyDetailsDBResponseASBO" + getInfoPolicyDetailsDBResponseASBO);
		return getInfoPolicyDetailsDBResponseASBO;

	}
	
	private EmployeePortalResponseObject getInfoAcceptPolicy(
			EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {
		LOGGER.info("Inside getInfoAcceptPolicy dbInvoker");
		InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO;
		InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO=null;
		if (requestVO instanceof InfoPolicyAcceptDBRequestASBO) {
			infoPolicyAcceptDBRequestASBO = (InfoPolicyAcceptDBRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		infoPolicyAcceptDBResponseASBO= infoPolicyService.getInfoAcceptPolicy(infoPolicyAcceptDBRequestASBO);
		LOGGER.info("getInfoPolicyDetailsDBResponseASBO" + infoPolicyAcceptDBResponseASBO);
		return infoPolicyAcceptDBResponseASBO;

	}
}

