/**
 * 
 */
package com.tcs.docstore.persist.dao;

import com.tcs.docstore.db.asbo.request.LoginDBRequestASBO;

/**
 * @author 738566
 *
 */
public interface LoginDao {

	/**
	 * @param loginDBRequestASBO
	 * @return
	 */
	public String getUserName(LoginDBRequestASBO loginDBRequestASBO);

}
