/**
 * 
 */
package com.tcs.docstore.db.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 738566
 *
 */
public class LoginDBRequestASBO extends DocStoreRequestObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7208607790572037335L;
	
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
