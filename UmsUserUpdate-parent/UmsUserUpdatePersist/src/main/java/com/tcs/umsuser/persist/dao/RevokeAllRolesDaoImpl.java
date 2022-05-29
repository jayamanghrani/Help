package com.tcs.umsuser.persist.dao;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsuser.persist.DBUtil;
import com.tcs.umsuser.persist.query.Queries;

public class RevokeAllRolesDaoImpl implements RevokeAllRolesDao{
	private static final Logger LOGGER = Logger.getLogger(RevokeAllRolesDaoImpl.class);
	@Override
	public void revokeRoles(String userId, String modifiedBy) {
		LOGGER.info("Inside revoke all method except HRMS -------------- ");
		EntityManager entityManager=null;
		Query query =null;
		int updateResult =0;
		try {
			entityManager=DBUtil.getEntityManager();
			
			try {
				
				query =entityManager.createNativeQuery(Queries.updateAppMap);
				query.setParameter("userId", userId);
				query.setParameter("modifiedBy", modifiedBy);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("USR_APP_MAP updated successfully -----");
				}
				
				updateResult=0;
				query =entityManager.createNativeQuery(Queries.updateRoleMap);
				query.setParameter("userId", userId);
				query.setParameter("modifiedBy", modifiedBy);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("USR_ROLE_MAP updated successfully -----");
				}
				
				updateResult=0;
				query =entityManager.createNativeQuery(Queries.updatePermListHistory);
				query.setParameter("userId", userId);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("USR_PERM_LIST_HISTORY updated successfully -----");
				}
				
			}catch(Exception e){
				LOGGER.error("Error in USR_PERM_LIST_HISTORY : " +e);
				LOGGER.error(e.getStackTrace(),e);
			}
			
			try{
				updateResult=0;
				query =entityManager.createNativeQuery(Queries.updateUserMst);
				query.setParameter("userId", userId);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("UMS_USR_MST updated successfully -----");
				}
				
			}catch(Exception e){
				LOGGER.error("Error in UMS_USR_MST : " +e);
				LOGGER.error(e.getStackTrace(),e);
			}
			
			try{
				updateResult=0;
				query =entityManager.createNativeQuery(Queries.updateUmsUserAccess);
				query.setParameter("userId", userId);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("UMS_USR_ACCESS updated successfully -----");
				}
				
			}catch(Exception e){
				LOGGER.error("Error in UMS_USR_ACCESS : " +e);
				LOGGER.error(e.getStackTrace(),e);
			}
			
			try{
				updateResult=0;
				query =entityManager.createNativeQuery(Queries.updateUmsUserRoleMap);
				query.setParameter("userId", userId);
				
				entityManager.getTransaction().begin();
				updateResult = query.executeUpdate();
				entityManager.getTransaction().commit();
				if(updateResult>0){
					LOGGER.info("UMS_USR_ROLE_MAP updated successfully -----");
				}
				
			}catch(Exception e){
				LOGGER.error("Error in UMS_USR_ROLE_MAP : " +e);
				LOGGER.error(e.getStackTrace(),e);
			}
				
		}catch(Exception e){
			LOGGER.error("Error : " +e);
		}finally{
			
			try{
				if(null!=entityManager){
				entityManager.close();
				}
			}
				catch(Exception e){
			LOGGER.error(e.getStackTrace(),e);
				
			}
		}
		LOGGER.info("Returning from revoke all method ---------------");
	}
}
