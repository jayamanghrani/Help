/**
 * 
 */
package com.tcs.employeeportal.db.asbo.response;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class LoginDBResponseASBO extends EmployeePortalResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7809449096176781103L;
	
	private String userName;
	
	private String userId;

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

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
