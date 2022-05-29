/**
 * 
 */
package com.tcs.webreports.asbo.access.request;

import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
/**
 * @author 738566
 * 
 */
public class LoginRequestASBO extends WebReportsRequestObject {

	private static final long serialVersionUID = -2489717433482225159L;
	private String userId;
	private String password;
	private String employeeNo;
	private String companyName;

	/**
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the employeeNo
	 */
	public String getEmployeeNo() {
		return employeeNo;
	}

	/**
	 * @param employeeNo
	 *            the employeeNo to set
	 */
	public void setEmployeeNo(String employeeNo) {
		this.employeeNo = employeeNo;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName
	 *            the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
}