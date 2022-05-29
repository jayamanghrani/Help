package com.tcs.employeeportal.persist.dao;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.tcs.employeeportal.persist.DBUtil;
import com.tcs.employeeportal.persist.entities.TEmployeeDetail;

public class GetUserDetailsDaoImpl implements GetUserDetailsDao {
	/**
	 * Instantiates a new get user details dao impl.
	 */
	public GetUserDetailsDaoImpl() {
		//super(TUserProfile.class);
	}
	private static final Logger LOGGER = Logger.getLogger(GetUserDetailsDaoImpl.class);
	
	@Override
	public TEmployeeDetail getUserDetails(String userId) {
		EntityManager entityManager=null;
		
		TEmployeeDetail response=null;
		try {
			entityManager=DBUtil.getEntityManager();
			TypedQuery<TEmployeeDetail> query= entityManager.createNamedQuery("TEmployeeDetail.findUser",TEmployeeDetail.class);
			query.setParameter("loginId", userId);
			response= (TEmployeeDetail) query.getSingleResult();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			response=null;
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

		return response;
	}

}
