/**
 * 
 */
package com.tcs.employeeportal.db.asbo.request;

import java.util.Date;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;


/**
 * @author 738796
 *
 */
/**
 * @author 738796
 *
 */
public class InfoPolicyAcceptDBRequestASBO extends EmployeePortalRequestObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3976279873842124083L;
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
