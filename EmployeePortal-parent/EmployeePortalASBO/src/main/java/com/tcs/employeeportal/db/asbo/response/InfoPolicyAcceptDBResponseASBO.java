package com.tcs.employeeportal.db.asbo.response;

import java.util.Date;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738796
 *
 */
public class InfoPolicyAcceptDBResponseASBO extends EmployeePortalResponseObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5118097302684667206L;
	private String userId;
   private String status;
    
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

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	
}
