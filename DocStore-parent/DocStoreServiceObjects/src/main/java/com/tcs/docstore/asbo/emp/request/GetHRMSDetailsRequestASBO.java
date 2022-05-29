/**
 * 
 */
package com.tcs.docstore.asbo.emp.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class GetHRMSDetailsRequestASBO extends DocStoreRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String userID;
	private String position;
	private String departmentName;
	private String flag;
	private String roleOID;
	/**
	 * @return the roleOID
	 */
	public String getRoleOID() {
		return roleOID;
	}
	/**
	 * @param roleOID the roleOID to set
	 */
	public void setRoleOID(String roleOID) {
		this.roleOID = roleOID;
	}
	/**
	 * @return the userID
	 */
	public String getUserID() {
		return userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}
	/**
	 * @return the position
	 */
	public String getPosition() {
		return position;
	}
	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}
	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}
	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	/**
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}
	/**
	 * @param flag the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	
}
