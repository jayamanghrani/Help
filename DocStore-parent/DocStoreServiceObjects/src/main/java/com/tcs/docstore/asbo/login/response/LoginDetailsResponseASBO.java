/**
 * 
 */
package com.tcs.docstore.asbo.login.response;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class LoginDetailsResponseASBO extends DocStoreResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8355817735774393305L;

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
