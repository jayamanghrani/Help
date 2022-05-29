/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.db.asbo.request.LoginDBRequestASBO;
import com.tcs.docstore.db.asbo.response.LoginDBResponseASBO;

/**
 * @author 738566
 *
 */
public interface LoginService {

	/**
	 * @param loginDBRequestASBO
	 * @return
	 */
	public LoginDBResponseASBO getUserName(LoginDBRequestASBO loginDBRequestASBO);

}
