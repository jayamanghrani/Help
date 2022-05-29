/**
 * 
 */
package com.tcs.employeeportal.db.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 738566
 *
 */
public class LoginDBRequestASBO extends EmployeePortalRequestObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7208607790572037335L;
	
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
