package com.tcs.umsrole.persist.dao;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsrole.persist.DBUtil;
import com.tcs.umsrole.persist.entity.PermListHistoryUpdate;
import com.tcs.umsrole.request.PermissionListRequestASBO;
import com.tcs.umsrole.response.PermissionListResponseASBO;

public class PermissionListDaoImpl implements PermissionListDao{
	
	
	private static final Logger LOGGER = Logger
			.getLogger(PermissionListDaoImpl.class);

	@Override
	public PermissionListResponseASBO permissionListUpdate(
			PermissionListRequestASBO permissionListRequestASBO) {
		
		PermissionListResponseASBO permissionListResponseASBO = new PermissionListResponseASBO();
		EntityManager entityManager = DBUtil.getEntityManager();
		int response=0;
		try{
			
			LOGGER.info("EntityManager created in PermissionListDaoImp");
			Date date = new Date();
			entityManager.getTransaction().begin();
	
			entityManager.persist(new PermListHistoryUpdate(permissionListRequestASBO.getUserId(),permissionListRequestASBO.getPermissionListId(),
			permissionListRequestASBO.getProvisionId(),permissionListRequestASBO.getPermissionCreatedBy(),date,date,permissionListRequestASBO.getpermissionendDate()));
			entityManager.getTransaction().commit();
			LOGGER.info("value persist in PermissionListDaoImp");


			entityManager.getTransaction().begin();
			Query query= entityManager.createQuery("update UserMasterUpdate uum set uum.permissionList=:permissionList, uum.umsmodifiedBy=:umsmodifiedBy, uum.umsmodifiedDate = sysdate where uum.userId = :userId and uum.umsendDate is null and uum.uumsStatus='E'");
			
			query.setParameter("permissionList", permissionListRequestASBO.getUserpermissionList());
			query.setParameter("umsmodifiedBy", permissionListRequestASBO.getUsermasterModifiedBy());
			query.setParameter("userId", permissionListRequestASBO.getUserId());
			response=query.executeUpdate();
			
			entityManager.getTransaction().commit();
			entityManager.close();
			
			permissionListResponseASBO.setResponse(response);
	        
	        permissionListResponseASBO.setStatus("S");
	        
		}catch(Exception ex){
			LOGGER.info("Error" +ex);
			 entityManager.close();
			 permissionListResponseASBO.setStatus("F");
			 LOGGER.error(ex.getStackTrace(),ex);
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
		return permissionListResponseASBO;
	}
	
	
	
	

}
