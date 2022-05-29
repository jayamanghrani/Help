/**
 * 
 */
package com.tcs.docstore.persist.dao;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.tcs.docstore.db.asbo.request.LoginDBRequestASBO;
import com.tcs.docstore.persist.DBUtil;
import com.tcs.docstore.persist.entities.TEmployeeDetail;

/**
 * @author 738566
 *
 */
public class LoginDaoImpl implements LoginDao {
	
	/**
	 * Instantiates a new login dao impl.
	 */
	public LoginDaoImpl(){
		
	}
	
	private static final Logger LOGGER = Logger.getLogger(LoginDaoImpl.class);

	/* (non-Javadoc)
	 * @see com.tcs.docstore.persist.dao.LoginDao#getUserName(com.tcs.docstore.db.asbo.request.LoginDBRequestASBO)
	 */
	@Override
	public String getUserName(LoginDBRequestASBO loginDBRequestASBO) {
		
		EntityManager entityManager=null;
		
		TEmployeeDetail response=null;
		try {
			entityManager=DBUtil.getEntityManager();
			TypedQuery<TEmployeeDetail> query= entityManager.createNamedQuery("TEmployeeDetail.findUser",TEmployeeDetail.class);
			query.setParameter("loginId", loginDBRequestASBO.getUserId());
			response= (TEmployeeDetail) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e);
		}
		finally{
			try{
				if(null!=entityManager){
				entityManager.close();
				}
			}
				catch(Exception e){
				LOGGER.error(e);
			}
		}

		return response.getTEmpFirstName();
	}
	
	
}
