package com.tcs.employeeportal.persist.service;

import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;

public interface GetUserDetailsService {
	/**
	 * Gets the user details.
	 *
	 * @param forgotPasswordDBRequestASBO the forgot password db request asbo
	 * @return the user details
	 */
	public GetUserDetialsDBResponseASBO getUserDetails(
			GetUserDetailsDBRequestASBO getUserDetailsDBRequestASBO);


}
