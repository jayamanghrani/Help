/**
 * 
 */
package com.tcs.employeeportal.db.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 738796
 *
 */
public class GetInfoPolicyDetailsDBRequestASBO extends EmployeePortalRequestObject
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -617847539242896619L;
	
	private String userId;
	
	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}



}
