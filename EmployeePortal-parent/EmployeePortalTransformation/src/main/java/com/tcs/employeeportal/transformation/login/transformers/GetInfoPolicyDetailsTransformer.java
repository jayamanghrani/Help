/**
 * 
 *//*
package com.tcs.employeeportal.transformation.login.transformers;

import com.tcs.employeeportal.asbo.emp.request.GetUserDetailsRequestASBO;
import com.tcs.employeeportal.asbo.emp.response.GetUserDetailsResponseASBO;
import com.tcs.employeeportal.asbo.login.request.GetInfoPolicyDetailsRequestASBO;
import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.login.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

*//**
 * @author 738796
 *
 *//*
public class GetInfoPolicyDetailsTransformer {

	private GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO;
	private GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO;
	
	
	*//**
	 * Instantiates a new user details transformer.
	 *//*
	public GetInfoPolicyDetailsTransformer() {
		getInfoPolicyDetailsRequestASBO =new GetInfoPolicyDetailsRequestASBO();
		getInfoPolicyDetailsDBRequestASBO = new GetInfoPolicyDetailsDBRequestASBO();
		
	}
		*//**
		 * Transform request.
		 *
		 * @param getUserDetailsDBRequestASBO the get user details request asbo
		 * @return the employeeportal request object
		 * @throws IntegrationTransformationException the integration transformation exception
		 *//*
		public EmployeePortalRequestObject transformRequest(
				GetInfoPolicyDetailsRequestASBO getInfoPolicyDetailsRequestASBO)
				throws IntegrationTransformationException {
			this.getInfoPolicyDetailsRequestASBO = getInfoPolicyDetailsRequestASBO;
			this.getInfoPolicyDetailsDBRequestASBO
					.setHeader(getInfoPolicyDetailsRequestASBO.getHeader());
			transformRequest();
			return getInfoPolicyDetailsDBRequestASBO;
		}
		
		*//**
		 * Transform request.
		 *//*
		private void transformRequest() {
			this.getInfoPolicyDetailsDBRequestASBO
					.setUserId(this.getInfoPolicyDetailsRequestASBO.getUserId());
		}
		
	}


*/