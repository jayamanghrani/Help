/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.google.gson.Gson;
import com.tcs.docstore.db.asbo.request.LoginDBRequestASBO;
import com.tcs.docstore.db.asbo.response.LoginDBResponseASBO;
import com.tcs.docstore.persist.dao.LoginDao;
import com.tcs.docstore.persist.dao.LoginDaoImpl;

/**
 * @author 738566
 *
 */
public class LoginServiceImpl implements LoginService{
	
	private LoginDao loginDao;
	
	/**
	 * Instantiates a new login service impl.
	 */
	public LoginServiceImpl(){
		loginDao = new LoginDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.tcs.docstore.persist.service.LoginService#getUserName(com.tcs.docstore.db.asbo.request.LoginDBRequestASBO)
	 */
	@Override
	public LoginDBResponseASBO getUserName(LoginDBRequestASBO loginDBRequestASBO) {
		
		LoginDBResponseASBO loginDBResponseASBO = new LoginDBResponseASBO();
		String username=loginDao.getUserName(loginDBRequestASBO);
		loginDBResponseASBO.setUserName(username);
		loginDBResponseASBO.setUserId(loginDBRequestASBO.getUserId());
		loginDBResponseASBO.setHeader(loginDBRequestASBO.getHeader());
	
		return loginDBResponseASBO;
	}

}
