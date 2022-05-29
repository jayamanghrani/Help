/**
 * 
 */
package com.tcs.docstore.db.asbo.response;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class GetHRMSDetailsDBResponseASBO  extends DocStoreResponseObject{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String position;
	private String departmentName;
	private String flag;
	private String userID;
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
	

}
