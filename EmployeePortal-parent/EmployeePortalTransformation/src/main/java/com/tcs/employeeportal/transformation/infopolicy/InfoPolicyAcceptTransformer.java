package com.tcs.employeeportal.transformation.infopolicy;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.asbo.infopolicy.request.GetInfoPolicyDetailsRequestASBO;
import com.tcs.employeeportal.asbo.infopolicy.request.InfoPolicyAcceptRequestASBO;
import com.tcs.employeeportal.asbo.infopolicy.response.GetInfoPolicyDetailsResponseASBO;
import com.tcs.employeeportal.asbo.infopolicy.response.InfoPolicyAcceptResponseASBO;
import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;


public class InfoPolicyAcceptTransformer {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	
	private GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO;
	
	private GetInfoPolicyDetailsResponseASBO getInfoPolicyDetailsResponseASBO;
	
	private GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO;
	
	private GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO;
	
	private InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO;
	 
	private InfoPolicyAcceptRequestASBO infoPolicyAcceptRequestASBO;
	
	private InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO;
	
	private InfoPolicyAcceptResponseASBO infoPolicyAcceptResponseASBO;
	
	public InfoPolicyAcceptTransformer(){

		getInfoPolicyDetailsDBResponseASBO=new GetInfoPolicyDetailsDBResponseASBO();
		getInfoPolicyDetailsResponseASBO = new GetInfoPolicyDetailsResponseASBO();
		getInfoPolicyDetailsDBRequestASBO = new GetInfoPolicyDetailsDBRequestASBO();
		getInfoPolicyDetailsRequestASBO  = new GetInfoPolicyDetailsRequestASBO();
		infoPolicyAcceptDBRequestASBO	= new InfoPolicyAcceptDBRequestASBO();
		infoPolicyAcceptRequestASBO 	= new InfoPolicyAcceptRequestASBO();
		infoPolicyAcceptDBResponseASBO	= new InfoPolicyAcceptDBResponseASBO();
		infoPolicyAcceptResponseASBO	= new InfoPolicyAcceptResponseASBO();
		
	}
	
	public EmployeePortalRequestObject transformInfoPolicyRequest(
			GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO)
			throws IntegrationTransformationException {
		this.getInfoPolicyDetailsRequestASBO = getInfoPolicyDetailsRequestASBO;
		this.getInfoPolicyDetailsDBRequestASBO
				.setHeader(getInfoPolicyDetailsRequestASBO.getHeader());
		LOGGER.info("getInfoPolicyDetailsRequestASBO.getHeader" +getInfoPolicyDetailsRequestASBO.getHeader());
		transformInfoPolicyRequest();
		return getInfoPolicyDetailsDBRequestASBO;
	}
	
	private void transformInfoPolicyRequest() {
		this.getInfoPolicyDetailsDBRequestASBO
				.setUserId(this.getInfoPolicyDetailsRequestASBO.getUserId());
	}
	
	public EmployeePortalResponseObject transformInfoPolicyResponse(
			GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO)
			throws IntegrationTransformationException {
		this.getInfoPolicyDetailsDBResponseASBO = getInfoPolicyDetailsDBResponseASBO;
		this.getInfoPolicyDetailsResponseASBO
				.setHeader(getInfoPolicyDetailsDBResponseASBO.getHeader());
		transformInfoPolicyResponse();
		return getInfoPolicyDetailsResponseASBO;
	}
	
	private void transformInfoPolicyResponse() {
		
		this.getInfoPolicyDetailsResponseASBO.setUserId(this.getInfoPolicyDetailsDBResponseASBO.getUserId());
		LOGGER.debug("this.getInfoPolicyDetailsDBResponseASBO.getUserId()" + this.getInfoPolicyDetailsDBResponseASBO.getUserId());
		this.getInfoPolicyDetailsResponseASBO.setFirstName(this.getInfoPolicyDetailsDBResponseASBO.getFirstName());
		LOGGER.debug("this.getInfoPolicyDetailsDBResponseASBO.getFirstName()" + this.getInfoPolicyDetailsDBResponseASBO.getFirstName());
		this.getInfoPolicyDetailsResponseASBO.setLastName(this.getInfoPolicyDetailsDBResponseASBO.getLastName());
		LOGGER.debug("this.getInfoPolicyDetailsDBResponseASBO.getLastName()" + this.getInfoPolicyDetailsDBResponseASBO.getLastName());
		this.getInfoPolicyDetailsResponseASBO.setStatus(this.getInfoPolicyDetailsDBResponseASBO.getStatus());
		this.getInfoPolicyDetailsResponseASBO.setOfficeCode(this.getInfoPolicyDetailsDBResponseASBO.getOfficeCode());
	}

	public EmployeePortalRequestObject transformInfoAcceptPolicyRequest(
			InfoPolicyAcceptRequestASBO infoPolicyAcceptRequestASBO)
			throws IntegrationTransformationException {
		this.infoPolicyAcceptRequestASBO = infoPolicyAcceptRequestASBO;
		this.infoPolicyAcceptDBRequestASBO
				.setHeader(infoPolicyAcceptRequestASBO.getHeader());
		LOGGER.debug("infoPolicyAcceptRequestASBO.getHeader" +infoPolicyAcceptRequestASBO.getHeader());
		transformInfoAcceptPolicyRequest();
		return infoPolicyAcceptDBRequestASBO;
	}
	
	private void transformInfoAcceptPolicyRequest() {
		this.infoPolicyAcceptDBRequestASBO
				.setUserId(this.infoPolicyAcceptRequestASBO.getUserId());
	}
	
	public EmployeePortalResponseObject transformInfoAcceptPolicyResponse(
			InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO)
			throws IntegrationTransformationException {
		this.infoPolicyAcceptDBResponseASBO = infoPolicyAcceptDBResponseASBO;
		this.infoPolicyAcceptResponseASBO
				.setHeader(infoPolicyAcceptDBResponseASBO.getHeader());
		transformInfoAcceptPolicyResponse();
		return infoPolicyAcceptResponseASBO;
	}
	
	private void transformInfoAcceptPolicyResponse() {
		this.infoPolicyAcceptResponseASBO.setStatus(this.infoPolicyAcceptDBResponseASBO.getStatus());
		
		
	}
	
}
