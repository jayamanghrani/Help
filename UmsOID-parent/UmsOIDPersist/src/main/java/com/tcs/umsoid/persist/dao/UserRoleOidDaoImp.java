package com.tcs.umsoid.persist.dao;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.log4j.Logger;

import com.tcs.umsoid.common.UserRoleDetails;
import com.tcs.umsoid.persist.DBUtil;
import com.tcs.umsoid.persist.query.Queries;
import com.tcs.umsoid.request.UserLdapDetailRequestASBO;
import com.tcs.umsoid.vo.bean.OIDUserDetails;
import com.tcs.umsoid.vo.bean.ProvisionDetails;




public class UserRoleOidDaoImp implements UserRoleOidDao{

	private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

	@SuppressWarnings("rawtypes")
	@Override
	public List<UserRoleDetails> ldifRevokeRolesMapManagement(
			UserLdapDetailRequestASBO getuserLdapDetailRequestASBO) {
		List<UserRoleDetails> result=new ArrayList<>();
		String UserId;
		LOGGER.info("Reached inside ldifRevokeRolesMapManagement method  in UserRoleOidDaoImp ");

		if (!getuserLdapDetailRequestASBO.getUserID().isEmpty())
		{
			LOGGER.info("inside UserRoleOidDaoImp/ldifRevokeRolesMapManagement in if condition");
			EntityManager entityManager = DBUtil.getEntityManager();

			String q = Queries.getUserRoleCode;

			Query query = entityManager.createNativeQuery(q);
			LOGGER.info("UserId value in getUserRoleDetailsDB---:"+getuserLdapDetailRequestASBO.getUserID());
			UserId=getuserLdapDetailRequestASBO.getUserID();
			query.setParameter("userId", UserId);
			List obList = query.getResultList();

			if (!obList.isEmpty()) {
				LOGGER.info("obList.size()--------- "+obList.size());

				for (Object ob : obList) {
					result.add(new UserRoleDetails(ob.toString(), UserId));
				}			
			}

			LOGGER.info("Response Size -----       "+result.size());
		}
		return result;
	}



	public List<ProvisionDetails> getProvisionRequestList(UserLdapDetailRequestASBO userLdapDetailRequestASBO){
		List<ProvisionDetails> result = new LinkedList<>();
		EntityManager entityManager =null;
		LOGGER.info("Retreiving provison request details from DB");

		try{
			LOGGER.info("All Props retrieved : " + userLdapDetailRequestASBO.getRequestID() + " : " + userLdapDetailRequestASBO.getProvisionID());
		}catch(Exception e){
			LOGGER.error("Error with Request ID or provision ID");
			LOGGER.error(e.getStackTrace());
			return result;
		}

		if (!userLdapDetailRequestASBO.getProvisionID().isEmpty()){
			entityManager = DBUtil.getEntityManager();
			StringBuilder stringBuilder = new StringBuilder();
			String provStatus= Queries.GetProvisionDetailsAction;
			
			Query queryStaus= entityManager.createNativeQuery(provStatus);
			queryStaus.setParameter("provisionID", userLdapDetailRequestASBO.getProvisionID());
			Object singleResult = queryStaus.getSingleResult();
		
			
			
			
			
			LOGGER.info("Prov Action for : " +  userLdapDetailRequestASBO.getProvisionID() + "    is  ::   "  +  singleResult);
			if(singleResult instanceof String){
				String provAction=(String)singleResult;
				if(null!=provAction && !provAction.isEmpty()){
					if(provAction.equalsIgnoreCase("DU")){
						stringBuilder.append(Queries.GetProvisionDetailsForDeleteUser);
					}else{
						stringBuilder.append(Queries.GetProvisionDetails);
					}
				}
			}
			
			
			
			
			
			if(null!=userLdapDetailRequestASBO.getRequestID() && !userLdapDetailRequestASBO.getRequestID().isEmpty()){
				stringBuilder.append(" and upd.upd_request_id = :requestID ");
			}

			Query query = entityManager.createNativeQuery(stringBuilder.toString());

			if(null!=userLdapDetailRequestASBO.getRequestID() && !userLdapDetailRequestASBO.getRequestID().isEmpty()){
				query.setParameter("requestID", userLdapDetailRequestASBO.getRequestID());
			}
			
			query.setParameter("provisionID", userLdapDetailRequestASBO.getProvisionID());
			@SuppressWarnings("unchecked")

			List<Object[]> obList = query.getResultList();
			LOGGER.info("List Size :  "+ obList.size());
			if (!obList.isEmpty()) {

				try{
				for (Object[] ob : obList) {
					result.add(new ProvisionDetails(
							ob[0]==null ? 0L:Long.parseLong(ob[0].toString()),
							ob[1]==null?null:ob[1].toString(), 
							ob[2]==null?0L:Long.parseLong(ob[2].toString()), 
							ob[3]==null?null:ob[3].toString(),
							ob[4]==null?null:ob[4].toString(), 
							ob[5]==null?null:(Date) ob[5], 
							ob[6]==null?null:ob[6].toString(), 
							ob[7]==null?null:ob[7].toString(),
							ob[8]==null?null:ob[8].toString(),
							ob[9]==null?null:ob[9].toString(),
							ob[10]==null?null:ob[10].toString(),
							ob[11]==null?null:ob[11].toString(), 
							ob[12]==null?null:ob[12].toString(),
							ob[13]==null?null:ob[13].toString(),
							ob[14]==null?null:ob[14].toString(),
							ob[15]==null?null:ob[15].toString()));
				}
				}catch(Exception e){
				    LOGGER.error(e.getStackTrace());
					e.printStackTrace();
					return result;
				}
			}
			
			entityManager.clear();
			entityManager.close();
		}
		return result;
	}


	@Override
	public boolean setActionStatus(OIDUserDetails userDetails, String provisionAction) {

		boolean dbAction = false;
		int upd = 0;
		
		EntityManager entityManager = DBUtil.getEntityManager();
		EntityTransaction transaction =  entityManager.getTransaction();

		String q = Queries.InsertRoleActionStatus;

		Query query = entityManager.createNativeQuery(q);

		String userId = userDetails.getUserID();
		String status = userDetails.getStatus();
		String action = provisionAction;
		Date sysdate = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yy");
		
		query.setParameter("userId", userId);
		query.setParameter("status", status);
		query.setParameter("action", action);
		query.setParameter("sysdate", sdf.format(sysdate).toUpperCase());

		try{
			transaction.begin();
			upd = query.executeUpdate();
			transaction.commit();
			entityManager.clear();
			entityManager.close();
		}catch(Exception e){
		    LOGGER.error(e.getStackTrace());
			e.printStackTrace();
		}
		
		if(upd>0){
			dbAction = true;
			LOGGER.info("DB record updated : " + userId + ":" + status + ":" + action + ":" + sdf.format(sysdate).toUpperCase());
		}else{
			LOGGER.error("DB record update failed : " + userId + ":" + status + ":" + action + ":" + sdf.format(sysdate).toUpperCase());
		}
		
		return dbAction;
	}
	
}