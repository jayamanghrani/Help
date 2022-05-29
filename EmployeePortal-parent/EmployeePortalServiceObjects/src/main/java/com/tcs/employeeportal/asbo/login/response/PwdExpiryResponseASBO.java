/**
 * 
 */
package com.tcs.employeeportal.asbo.login.response;

import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class PwdExpiryResponseASBO extends EmployeePortalResponseObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2161682277689403021L;
	
	private String lastLoginDt;
	
	private String pwdExpiryDt;

	/**
	 * @return the lastLoginDt
	 */
	public String getLastLoginDt() {
		return lastLoginDt;
	}

	/**
	 * @param lastLoginDt the lastLoginDt to set
	 */
	public void setLastLoginDt(String lastLoginDt) {
		this.lastLoginDt = lastLoginDt;
	}

	/**
	 * @return the pwdExpiryDt
	 */
	public String getPwdExpiryDt() {
		return pwdExpiryDt;
	}

	/**
	 * @param pwdExpiryDt the pwdExpiryDt to set
	 */
	public void setPwdExpiryDt(String pwdExpiryDt) {
		this.pwdExpiryDt = pwdExpiryDt;
	}
	
	

}
