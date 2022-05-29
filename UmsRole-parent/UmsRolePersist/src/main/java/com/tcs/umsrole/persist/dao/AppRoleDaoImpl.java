package com.tcs.umsrole.persist.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsrole.persist.DBUtil;
import com.tcs.umsrole.persist.entity.UmsApplicationMap;
import com.tcs.umsrole.persist.entity.UmsRoleMap;
import com.tcs.umsrole.persist.query.Queries;
import com.tcs.umsrole.request.AppRoleMapRequestASBO;
import com.tcs.umsrole.response.AppDetailsResponseASBO;
import com.tcs.umsrole.response.AppRoleMapResponseASBO;
import com.tcs.umsrole.vo.ldif.UmsGetLdif;

public class AppRoleDaoImpl implements AppRoleDao {

	private static final Logger LOGGER = Logger.getLogger(AppRoleDaoImpl.class);

	@Override
	public AppRoleMapResponseASBO appRoleMap(
			AppRoleMapRequestASBO appRoleMapRequestASBO) {
		LOGGER.info("Reached inside AppRoleDaoImpl");
		EntityManager entityManager = DBUtil.getEntityManager();
		Date date= new Date();
		AppRoleMapResponseASBO appRoleMapResponseASBO=new AppRoleMapResponseASBO();
		try {

			LOGGER.info("Action  -----------"+appRoleMapRequestASBO.getActionDo());

			if (appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("A")) {
				Query applMap= entityManager.createQuery("select count(1) from UmsApplicationMap  where appMapUserId=:appMapUserId and appMapappId=:appMapappId and (appMapEndDate is null or appMapEndDate > sysdate)");
				applMap.setParameter("appMapUserId",appRoleMapRequestASBO.getUserId());
				applMap.setParameter("appMapappId",appRoleMapRequestASBO.getAppId());
				int countApplMap =((Number)applMap.getSingleResult()).intValue();
				LOGGER.info("count in to approle map------------"+countApplMap);

				if(countApplMap==0){

					entityManager.getTransaction().begin();
					UmsApplicationMap umsApplicationMap = new UmsApplicationMap();

					umsApplicationMap.setAppMapUserId(appRoleMapRequestASBO.getUserId());
					umsApplicationMap.setAppMapappId(appRoleMapRequestASBO.getAppId());
					umsApplicationMap.setAppMapRequestId(appRoleMapRequestASBO.getRequestId());
					umsApplicationMap.setAppMapModifiedBy(appRoleMapRequestASBO.getModifiedBy());
					umsApplicationMap.setAppMapModifiedDate(date);
					umsApplicationMap.setAppMapCreatedBy(appRoleMapRequestASBO.getCreatedBy());
					umsApplicationMap.setAppMapCreatedDate(date);
					umsApplicationMap.setAppMapStartDate(appRoleMapRequestASBO.getStartDate());
					umsApplicationMap.setAppMapEndDate(appRoleMapRequestASBO.getEndDate());

					entityManager.persist(umsApplicationMap);
					entityManager.getTransaction().commit();
					LOGGER.info("Inserted in  UUAM ------------");
				}

				Query roleMap= entityManager.createQuery("select count(1) from UmsRoleMap where roleMapUserId=:roleMapUserId and roleMapRoleId=:roleMapRoleId and branchId=:branchId and (roleMapEndDate is null or roleMapEndDate > sysdate)");
				roleMap.setParameter("roleMapUserId",appRoleMapRequestASBO.getUserId());
				roleMap.setParameter("roleMapRoleId",appRoleMapRequestASBO.getRoleId());
				roleMap.setParameter("branchId",appRoleMapRequestASBO.getBranchId());
				int countRoles =((Number)roleMap.getSingleResult()).intValue();

				LOGGER.info("count in to ums rol map------------"+countRoles);

				if(countRoles==0){
					entityManager.getTransaction().begin();

					UmsRoleMap umsRoleMap = new UmsRoleMap();
					umsRoleMap.setRoleMapUserId(appRoleMapRequestASBO.getUserId());
					umsRoleMap.setRoleMapRoleId(appRoleMapRequestASBO.getRoleId());
					umsRoleMap.setRoleMapRequestId(appRoleMapRequestASBO.getRequestId());
					umsRoleMap.setRoleMapProvId(appRoleMapRequestASBO.getProvId());
					umsRoleMap.setRoleMapModifiedBy(appRoleMapRequestASBO.getModifiedBy());
					umsRoleMap.setRoleMapModifiedDate(date);
					umsRoleMap.setRoleMapCreatedBy(appRoleMapRequestASBO.getCreatedBy());
					umsRoleMap.setRoleMapCreatedDate(date);
					umsRoleMap.setRoleMapStartDate(appRoleMapRequestASBO.getStartDate());
					umsRoleMap.setRoleMapEndDate(appRoleMapRequestASBO.getEndDate());
					umsRoleMap.setBranchId(appRoleMapRequestASBO.getBranchId());
					umsRoleMap.setRoleMapApproveStatus("N");

					entityManager.persist(umsRoleMap);

					entityManager.getTransaction().commit();
					LOGGER.info("Inserted in  UURM ------------");
				}

				appRoleMapResponseASBO.setStatus("S");
			}

			if (appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("D")) {

				LOGGER.info("EntityManager created for action Revoke            " +appRoleMapRequestASBO.toString());
				int result;
				entityManager.getTransaction().begin();
				Query query=entityManager.createQuery("update UmsRoleMap uurm set uurm.roleMapRequestId = :roleMapRequestId, uurm.roleMapEndDate =:endDate, uurm.roleMapModifiedBy = :roleMapModifiedBy,uurm.roleMapModifiedDate = sysdate,uurm.roleMapApproveStatus = 'N' where uurm.roleMapUserId = :roleMapUserId and uurm.roleMapRoleId = :roleMapRoleId and uurm.branchId=:branchId and (uurm.roleMapEndDate is null or uurm.roleMapEndDate > sysdate)");
				query.setParameter("roleMapRequestId", appRoleMapRequestASBO.getRequestId());
				query.setParameter("roleMapModifiedBy", appRoleMapRequestASBO.getModifiedBy());
				query.setParameter("roleMapUserId", appRoleMapRequestASBO.getUserId());
				query.setParameter("roleMapRoleId", appRoleMapRequestASBO.getRoleId());
				query.setParameter("branchId", appRoleMapRequestASBO.getBranchId());
				if(null!=appRoleMapRequestASBO.getEndDate()){
					query.setParameter("endDate" , appRoleMapRequestASBO.getEndDate());
				}else{
					query.setParameter("endDate" , date);
				}
				result=query.executeUpdate();
				entityManager.getTransaction().commit();
				appRoleMapResponseASBO.setStatus("S");
				LOGGER.info("UURM result --------------   "+result);
			}
		} catch (Exception ex) {
			LOGGER.info("Error"+ex);
			appRoleMapResponseASBO.setStatus("F");
			LOGGER.error(ex.getStackTrace(),ex);
		}
		finally{
			try{
				if(null!=entityManager){
					entityManager.close();
				}
			}
			catch(Exception e){
				LOGGER.error(e.getStackTrace(),e);
			}
		}
		LOGGER.info("Role map status for role updation -------------     "+appRoleMapResponseASBO.getStatus());

		return appRoleMapResponseASBO;
	}

	@Override
	public AppDetailsResponseASBO appldifRoleMap(AppRoleMapRequestASBO appDetailsRequestASBO){
		AppDetailsResponseASBO appDetailsResponseASBO = new AppDetailsResponseASBO();
		LOGGER.info("Reached inside appldifRoleMap ------------ ");
		EntityManager entityManager = DBUtil.getEntityManager();
		try{
			UmsGetLdif umsGetLdif = new UmsGetLdif();
			
				LOGGER.info("Inside appldifRoleMap in if condition");
				

				String q = Queries.getCountDetails;
				Query query = entityManager.createNativeQuery(q);
				query.setParameter("userId", appDetailsRequestASBO.getUserId());
				int count =((Number)query.getSingleResult()).intValue();
				LOGGER.info("Count value --------------   "  + count);
				if (count == 0){
					boolean result = umsGetLdif.getLdifFile(appDetailsRequestASBO.getUserId());
					if(result)
					{
						appDetailsResponseASBO.setStatus("S");
					}
				}else
				{
					appDetailsResponseASBO.setStatus("NA");
				}
			
		}catch(Exception e)
		{
			LOGGER.info("Error"+e);
			appDetailsResponseASBO.setStatus("F");
			LOGGER.error(e.getStackTrace(),e);
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

		return appDetailsResponseASBO;

	}

}
