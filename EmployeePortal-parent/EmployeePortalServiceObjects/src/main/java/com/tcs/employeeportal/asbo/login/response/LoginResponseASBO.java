/**
 * 
 */
package com.tcs.employeeportal.asbo.login.response;

import java.util.List;

import com.tcs.employeeportal.asbo.alfresco.AlfrescoInputs;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class LoginResponseASBO extends EmployeePortalResponseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4832347709971431426L;
	
	/*private String userId;
	
	private String userName;*/
	
	private List<AlfrescoInputs> cwissLinks;
	
	/**
	 * @return the userName
	 *//*
	public String getUserName() {
		return userName;
	}

	*//**
	 * @param userName the userName to set
	 *//*
	public void setUserName(String userName) {
		this.userName = userName;
	}

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
	 * @return the cwissLinks
	 */
	public List<AlfrescoInputs> getCwissLinks() {
		return cwissLinks;
	}

	/**
	 * @param cwissLinks the cwissLinks to set
	 */
	public void setCwissLinks(List<AlfrescoInputs> cwissLinks) {
		this.cwissLinks = cwissLinks;
	}
	
	
	
	
	
	

	
}
