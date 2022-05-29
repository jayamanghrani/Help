/**
 * 
 */
package com.tcs.employeeportal.asbo.infopolicy.request;

import java.util.Date;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 738796
 *
 */
public class InfoPolicyAcceptRequestASBO extends EmployeePortalRequestObject {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1771149596222599002L;
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


