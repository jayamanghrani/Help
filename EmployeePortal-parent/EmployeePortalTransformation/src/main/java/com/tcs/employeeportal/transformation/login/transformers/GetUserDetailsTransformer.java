package com.tcs.employeeportal.transformation.login.transformers;

import com.tcs.employeeportal.asbo.emp.request.GetUserDetailsRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.GetUserDetailsResponseASBO;
import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class GetUserDetailsTransformer {
	
	/** The forgot password request asbo. */
	private GetUserDetailsRequestASBO getUserDetailsRequestASBO;
	
	/** The get user details db request asbo. */
	private GetUserDetailsDBRequestASBO getUserDetailsDBRequestASBO;
	
	/** The get user details db response asbo. */
	private GetUserDetialsDBResponseASBO getUserDetialsDBResponseASBO;
	
	/** The get user details response asbo. */
	private GetUserDetailsResponseASBO getUserDetailsResponseASBO;
	
	/**
	 * Instantiates a new user details transformer.
	 */
	public GetUserDetailsTransformer() {
		getUserDetailsRequestASBO=new GetUserDetailsRequestASBO();
		getUserDetailsDBRequestASBO = new GetUserDetailsDBRequestASBO();
		getUserDetialsDBResponseASBO = new GetUserDetialsDBResponseASBO();
		getUserDetailsResponseASBO=new GetUserDetailsResponseASBO();
	}
	
	/**
	 * Transform request.
	 *
	 * @param getUserDetailsDBRequestASBO the get user details request asbo
	 * @return the employeeportal request object
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalRequestObject transformRequest(
			GetUserDetailsRequestASBO getUserDetailsRequestASBO)
			throws IntegrationTransformationException {
		this.getUserDetailsRequestASBO = getUserDetailsRequestASBO;
		this.getUserDetailsDBRequestASBO
				.setHeader(getUserDetailsRequestASBO.getHeader());
		transformRequest();
		return getUserDetailsDBRequestASBO;
	}
	
	/**
	 * Transform request.
	 */
	private void transformRequest() {
		this.getUserDetailsDBRequestASBO
				.setUserId(this.getUserDetailsRequestASBO.getUserId());
	}
	
	/**
	 * Transform response.
	 *
	 * @param getUserDetailsDBRequestASBO the get user details request asbo
	 * @return the employeeportal request object
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalResponseObject transformResponse(
			GetUserDetialsDBResponseASBO getUserDetialsDBResponseASBO)
			throws IntegrationTransformationException {
		this.getUserDetialsDBResponseASBO = getUserDetialsDBResponseASBO;
		this.getUserDetialsDBResponseASBO
				.setHeader(getUserDetialsDBResponseASBO.getHeader());
		transformResponse();
		return getUserDetailsResponseASBO;
	}
	
	/**
	 * Transform request.
	 */
	private void transformResponse() {
		this.getUserDetailsResponseASBO
				.setUserId(this.getUserDetailsRequestASBO.getUserId());
		this.getUserDetailsResponseASBO.setBranch(this.getUserDetialsDBResponseASBO.getBranch());
		this.getUserDetailsResponseASBO.setMobileNumber(this.getUserDetialsDBResponseASBO.getMobileNumber());
		this.getUserDetailsResponseASBO.setEmailId(this.getUserDetialsDBResponseASBO.getEmailId());
	}
}
