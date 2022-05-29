/**
 * 
 */
package com.tcs.employeeportal.asbo.login.request;

import com.tcs.employeeportal.asbo.alfresco.AlfrescoInputs;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

/**
 * @author 738566
 *
 */
public class LoginRequestASBO extends EmployeePortalRequestObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2146309729534729267L;

//	private String userId;
	
//	private String userName;
		
	private AlfrescoInputs alfrescoInput;

/*
	*//**
	 * @return the userId
	 *//*
	public String getUserId() {
		return userId;
	}

	*//**
	 * @param userId the userId to set
	 *//*
	public void setUserId(String userId) {
		this.userId = userId;
	}
*/
	/**
	 * @return the alfrescoInput
	 */
	public AlfrescoInputs getAlfrescoInput() {
		return alfrescoInput;
	}

	/**
	 * @param alfrescoInput the alfrescoInput to set
	 */
	public void setAlfrescoInput(AlfrescoInputs alfrescoInput) {
		this.alfrescoInput = alfrescoInput;
	}
	
	

}
