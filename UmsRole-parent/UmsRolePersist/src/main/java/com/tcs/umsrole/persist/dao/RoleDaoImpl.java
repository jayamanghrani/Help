package com.tcs.umsrole.persist.dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.umsrole.persist.DBUtil;
import com.tcs.umsrole.persist.entity.ProvisionDetails;
import com.tcs.umsrole.persist.entity.RoleRequestMaster;
import com.tcs.umsrole.request.AcknowledgeASBO;
import com.tcs.umsrole.request.AppRoleMapRequestASBO;
import com.tcs.umsrole.request.PermissionListRequestASBO;
import com.tcs.umsrole.request.ProvDetailsRequestASBO;
import com.tcs.umsrole.request.RoleDetailsASBO;
import com.tcs.umsrole.request.UpdateRoleDetailsASBO;
import com.tcs.umsrole.response.AppRoleMapResponseASBO;
import com.tcs.umsrole.response.GetRoleDetailsResponseASBO;
import com.tcs.umsrole.response.PermissionListResponseASBO;
import com.tcs.umsrole.response.ProvisionDetailsResponseASBO;
import com.tcs.umsrole.vo.util.UtilProperties;

public class RoleDaoImpl implements RoleDao {

	private static final Logger LOGGER = Logger.getLogger(RoleDaoImpl.class);

	

	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	Date currentDate= new Date();
	ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();

	@Override
	public GetRoleDetailsResponseASBO updateUserRolesDetails(
			UpdateRoleDetailsASBO getRoleDetailsRequestASBO) {
		LOGGER.info("Reached inside RoleDaoImpl");
		Long requestId = 0L;
		EntityManager entityManager = DBUtil.getEntityManager();

		GetRoleDetailsResponseASBO getRoleDetailsResponseASBO = new GetRoleDetailsResponseASBO();
		LOGGER.info("in Update Role Details DAO----------------------------"
				+ getRoleDetailsRequestASBO.toString());

		/* Request Master Update */
		Date date = new Date();

		try {
			entityManager.getTransaction().begin();
			LOGGER.info("transaction begin");
			RoleRequestMaster roleRequestMaster = new RoleRequestMaster();
			roleRequestMaster.setModifyBy(getRoleDetailsRequestASBO
					.getRequestBy());
			roleRequestMaster.setModifydate(date);
			roleRequestMaster.setReason(getRoleDetailsRequestASBO.getReason());
			roleRequestMaster.setRequestBy(getRoleDetailsRequestASBO
					.getRequestBy());
			roleRequestMaster.setRequestDate(date);
			entityManager.persist(roleRequestMaster);
			entityManager.getTransaction().commit();
			LOGGER.info("transaction commit");
			requestId = roleRequestMaster.getRequestId();
			getRoleDetailsResponseASBO.setResultStatus("S");
			getRoleDetailsResponseASBO
			.setStatusMessage("Request Master Updated");
			LOGGER.info("requestId generated--------------------" + requestId);
			entityManager.clear();
		} catch (Exception ex) {
			getRoleDetailsResponseASBO.setResultStatus("F");
			getRoleDetailsResponseASBO
			.setStatusMessage("Request Master Update Failed");
			LOGGER.info("Error" + ex);
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

		/* Inserting Provision details specific to HRMS */
		LOGGER.info("check for PL ");
		if (getRoleDetailsRequestASBO.getpLId() != null
				&& !getRoleDetailsRequestASBO.getpLId().equalsIgnoreCase("")) {

			LOGGER.info("PL exists---------------------");
			ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();

			provDetailsRequestASBO.setProvAction("AP");
			provDetailsRequestASBO.setProvApplicationId("HRMS");
			provDetailsRequestASBO.setProvStatus("R");
			provDetailsRequestASBO.setProvBranchId(getRoleDetailsRequestASBO
					.getBranchId());
			provDetailsRequestASBO.setProvCreatedBy(getRoleDetailsRequestASBO
					.getRequestBy());
			provDetailsRequestASBO.setProvRoleId(null);
			provDetailsRequestASBO.setProvUserId(getRoleDetailsRequestASBO
					.getUserId());
			provDetailsRequestASBO.setProvModifiedBy(getRoleDetailsRequestASBO
					.getRequestBy());
			provDetailsRequestASBO.setProvRequestId(requestId);
			LOGGER.info("PL updation in Provision details starts-------------------");
			ProvisionDetailsResponseASBO asbo = provisionDetailsDao
					.provisionDetailsUpdate(provDetailsRequestASBO);

			LOGGER.info("provid generated is-------------------------"
					+ asbo.getProvId());
			LOGGER.info("provid Status is-------------------------"
					+ asbo.getResultStatus());
			if (asbo.getResultStatus().equalsIgnoreCase("S")) {
				PermissionListRequestASBO permissionListRequestASBO = new PermissionListRequestASBO();
				permissionListRequestASBO
				.setPermissionCreatedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				permissionListRequestASBO.setProvisionId(asbo.getProvId()
						.toString());
				permissionListRequestASBO.setUserId(getRoleDetailsRequestASBO
						.getUserId());
				permissionListRequestASBO
				.setUsermasterModifiedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				permissionListRequestASBO
				.setPermissionListId(getRoleDetailsRequestASBO
						.getpLId());
				permissionListRequestASBO
				.setUserpermissionList(getRoleDetailsRequestASBO
						.getPermission());
				PermissionListDao permissionListDao = new PermissionListDaoImpl();
				PermissionListResponseASBO permissionListResponseASBO = permissionListDao
						.permissionListUpdate(permissionListRequestASBO);
				LOGGER.info("permission List update Status--------"
						+ permissionListResponseASBO.getStatus());
			}
		}
		
		if(null!=getRoleDetailsRequestASBO.getRemovedBranches()&&!getRoleDetailsRequestASBO.getRemovedBranches().isEmpty()){
			Long tempRequestId = requestId;
			getRoleDetailsRequestASBO.getRemovedBranches().forEach(data->{
				LOGGER.info("Remove branch in IIMS ---------  "  +  data);
				
				ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
				ProvisionDetailsResponseASBO provisionDetailsResponseASBO = new ProvisionDetailsResponseASBO();

				provDetailsRequestASBO.setProvApplicationId("IIMS");
				provDetailsRequestASBO.setProvStatus("R");
				provDetailsRequestASBO.setProvBranchId(data);
				provDetailsRequestASBO.setProvCreatedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				provDetailsRequestASBO.setProvUserId(getRoleDetailsRequestASBO
						.getUserId());
				provDetailsRequestASBO.setProvModifiedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				provDetailsRequestASBO.setProvRequestId(tempRequestId);
				provDetailsRequestASBO.setProvAction("DB");
				provDetailsRequestASBO.setProvModifiedDate(currentDate);
				provisionDetailsResponseASBO = provisionDetailsDao
						.provisionDetailsUpdate(provDetailsRequestASBO);
				LOGGER.info("Remove branch in IIMS Status---------  "  +  provisionDetailsResponseASBO.toString());
				
			});
		}
		
		for (RoleDetailsASBO roles : getRoleDetailsRequestASBO.getUserRoles()) {
			LOGGER.info("---------------------------------------------------------------------------"+getRoleDetailsRequestASBO.getRemovedBranches()+"-------------"+roles.getOfficeCode());
			Long provId = null;
			ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();

			provDetailsRequestASBO.setProvApplicationId(roles.getAppId());
			if(getRoleDetailsRequestASBO.getRemovedBranches().contains(roles.getOfficeCode()))
			{
			    provDetailsRequestASBO.setProvStatus("D");
			    LOGGER.info("--roles branch deleted and role not deleted--------"+roles.getRoleName());
			}else
			{
			provDetailsRequestASBO.setProvStatus("R");
			}
			provDetailsRequestASBO.setProvBranchId(roles.getOfficeCode());
			provDetailsRequestASBO.setProvCreatedBy(getRoleDetailsRequestASBO
					.getRequestBy());
			provDetailsRequestASBO.setProvRoleId(roles.getRoleId());
			provDetailsRequestASBO.setProvUserId(getRoleDetailsRequestASBO
					.getUserId());
			provDetailsRequestASBO.setProvModifiedBy(getRoleDetailsRequestASBO
					.getRequestBy());
			provDetailsRequestASBO.setProvRequestId(requestId);

			LOGGER.info("Prov details insert------------------------" + provDetailsRequestASBO.toString());
			try {
				ProvisionDetailsResponseASBO provisionDetailsResponseASBO = new ProvisionDetailsResponseASBO();

				if(roles.getEndDate()!=null && !roles.getEndDate().isEmpty() ){
					provDetailsRequestASBO.setProvAction("DR");
					LOGGER.info("Prov details for end Date------------------------   " + roles.getEndDate());
					provDetailsRequestASBO.setProvModifiedDate(formatter.parse(roles.getEndDate()));
					provisionDetailsResponseASBO = provisionDetailsDao
							.provisionDetailsUpdate(provDetailsRequestASBO);
					provId = provisionDetailsResponseASBO.getProvId();
					LOGGER.info("Prov details ------------------------"+provisionDetailsResponseASBO.toString());

				}else if(roles.getAction().equalsIgnoreCase("D")){
					provDetailsRequestASBO.setProvAction(roles.getAction() + "R");
					roles.setEndDate(formatter.format(currentDate));
					LOGGER.info("End Date :  " + roles.getEndDate());
					provDetailsRequestASBO.setProvModifiedDate(currentDate);
					provisionDetailsResponseASBO = provisionDetailsDao
							.provisionDetailsUpdate(provDetailsRequestASBO);
					provId = provisionDetailsResponseASBO.getProvId();
					LOGGER.info("Prov details ------------------------"+provisionDetailsResponseASBO.toString());
				}


				if((roles.getStartDate()!=null && !roles.getStartDate().isEmpty()) && (roles.getAction().equalsIgnoreCase("A"))){
					LOGGER.info("Prov details for start Date------------------------" + roles.getStartDate());
					provDetailsRequestASBO.setProvAction(roles.getAction() + "R");
					provDetailsRequestASBO.setProvModifiedDate(formatter.parse(roles.getStartDate()));

					LOGGER.info("Prov modified date ------------------------   " + provDetailsRequestASBO.getProvModifiedDate());
					provisionDetailsResponseASBO = provisionDetailsDao
							.provisionDetailsUpdate(provDetailsRequestASBO);
					provId = provisionDetailsResponseASBO.getProvId();
					LOGGER.info("Prov details ------------------------"+provisionDetailsResponseASBO.toString());
				}



				if(provisionDetailsResponseASBO.getResultStatus().equalsIgnoreCase("S")&& provId!=null){
					LOGGER.info("Inserting in APPandRoleMap -------------" );
					insertInAppRole(roles , getRoleDetailsRequestASBO ,provDetailsRequestASBO, provId , requestId);
				}
			}
			catch (ParseException e) {
				LOGGER.info("Error" + e);
				LOGGER.error(e.getStackTrace(),e);
			}
		}  
		getRoleDetailsResponseASBO.setRequestId(requestId.toString());

		return getRoleDetailsResponseASBO;
	}


	private void insertInAppRole(RoleDetailsASBO roles, UpdateRoleDetailsASBO getRoleDetailsRequestASBO, ProvDetailsRequestASBO provDetailsRequestASBO, Long provId, Long requestId) throws ParseException {
		boolean xlFlag = true;

		AppRoleMapRequestASBO appRoleMapRequestASBO = new AppRoleMapRequestASBO();
		appRoleMapRequestASBO.setCreatedBy(getRoleDetailsRequestASBO
				.getRequestBy());
		appRoleMapRequestASBO.setModifiedBy(getRoleDetailsRequestASBO
				.getRequestBy());
		appRoleMapRequestASBO.setProvId(provId.toString());
		appRoleMapRequestASBO.setRequestId(requestId.toString());
		appRoleMapRequestASBO.setRoleId(roles.getRoleId());
		appRoleMapRequestASBO.setUserId(getRoleDetailsRequestASBO
				.getUserId());
		appRoleMapRequestASBO.setActionDo(roles.getAction());
		appRoleMapRequestASBO.setAppId(roles.getAppId());
		appRoleMapRequestASBO.setBranchId(roles.getOfficeCode());
		appRoleMapRequestASBO.setRoleName(roles.getRoleName());
		if(null!=roles.getEndDate() && !roles.getEndDate().isEmpty()){
			appRoleMapRequestASBO.setEndDate(formatter.parse(roles.getEndDate()));
		}
		if(null!=roles.getStartDate() && !roles.getStartDate().isEmpty()){
			appRoleMapRequestASBO.setStartDate(formatter.parse(roles.getStartDate()));
		}else{
			appRoleMapRequestASBO.setStartDate(currentDate);
		}
		AppRoleDao roalDao = new AppRoleDaoImpl();
		LOGGER.info("Updating in App role map-------------------------");
		AppRoleMapResponseASBO appRoleMapResponseASBO =roalDao.appRoleMap(appRoleMapRequestASBO);

		LOGGER.info("Role update status for role ID "+ appRoleMapRequestASBO.toString());


		if(appRoleMapResponseASBO.getStatus().equalsIgnoreCase("S")){
			if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("FINANCIALS")&& (appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("A"))){	
				LOGGER.info("LDIF file check---------------------------------");
				roalDao.appldifRoleMap(appRoleMapRequestASBO);
			}

			if(null!=roles.getStartDate() && !roles.getStartDate().isEmpty()){
				LOGGER.info("Start Date :  "  + formatter.parse(roles.getStartDate())   +   "     Default Date :  "   + formatter.parse(formatter.format(currentDate)) );
				if(!formatter.parse(roles.getStartDate()).after(formatter.parse(formatter.format(currentDate)))){
					updateInOIDAndUMS(appRoleMapRequestASBO,provId,xlFlag,roles,provDetailsRequestASBO,requestId,getRoleDetailsRequestASBO);
				}else{
					LOGGER.info("Future start date for OID and UMS ----------  "  + roles.getStartDate());
				}
			}
			if(null!=roles.getEndDate() && !roles.getEndDate().isEmpty()){
				LOGGER.info("Start Date :  "  + formatter.parse(roles.getEndDate())   +   "     Default Date :  "   + formatter.parse(formatter.format(currentDate)) );
				if(!formatter.parse(roles.getEndDate()).after(formatter.parse(formatter.format(currentDate)))){
					updateInOIDAndUMS(appRoleMapRequestASBO,provId,xlFlag,roles,provDetailsRequestASBO,requestId,getRoleDetailsRequestASBO);
				}else{
					LOGGER.info("Future end date for OID and UMS ----------  "  + roles.getEndDate());
				}
			}

		}

	}

		private void updateInOIDAndUMS(AppRoleMapRequestASBO appRoleMapRequestASBO, Long provId, boolean xlFlag, RoleDetailsASBO roles, ProvDetailsRequestASBO provDetailsRequestASBO, Long requestId, UpdateRoleDetailsASBO getRoleDetailsRequestASBO) {
			//OID integration starts----------------------------------------------------------------------------------
			if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("OID")){
				LOGGER.info("calling OID service------------");
				pushToOID(requestId,provId, xlFlag);
			}
	
			if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("UMS")){
				LOGGER.info("Updating recodrds for UMS application-----------------");
				EntityManager entityManager = DBUtil.getEntityManager();
				try{
					if(appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("A"))
					{
	
						Query userQuery= entityManager.createNativeQuery("select * from ums_usr_mst where uum_usr_id=:userId");
						userQuery.setParameter("userId", appRoleMapRequestASBO.getUserId());
						List masterList=userQuery.getResultList();
						LOGGER.info("User master List---------  "+masterList.size());
						Iterator iter=masterList.iterator();
						String firstName=null;
						String lastName=null;
						String email=null;
	
						while (iter.hasNext()) {
							Object[] masterData= (Object[]) iter.next();
							firstName=masterData[3].toString();
							lastName=masterData[5].toString();
							email=masterData[16].toString();
						}
						LOGGER.info("email--------"+email);
	
						Query query= entityManager.createNativeQuery("select count(1) from ums_umsusr_access uua where uua.uua_usr_id=:userId and (uua.uua_end_date is null or uua.uua_end_date > sysdate) ");
						query.setParameter("userId",appRoleMapRequestASBO.getUserId());
						int count =((Number)query.getSingleResult()).intValue();
	
						entityManager.getTransaction().begin();
						LOGGER.info("count-----------"+count);
						if(count==0)
						{
							query= entityManager.createNativeQuery("insert into ums_umsusr_access(uua_usr_id, uua_branch_id,uua_applicaion,uua_created_by,uua_created_date,uua_modified_by,uua_modified_date,uua_start_date,uua_end_date, uua_first_name, uua_last_name, uua_email)"
									+" values(?1, ?2, 'NIAADMIN', ?3, sysdate, ?4, sysdate,sysdate, null, ?5,?6,?7)");
							query.setParameter(1,provDetailsRequestASBO.getProvUserId());
							query.setParameter(2,provDetailsRequestASBO.getProvBranchId());
							query.setParameter(3,provDetailsRequestASBO.getProvCreatedBy());
							query.setParameter(4,provDetailsRequestASBO.getProvModifiedBy());
							query.setParameter(5,firstName);
							query.setParameter(6,lastName);
							query.setParameter(7,email);
							query.executeUpdate();
	
							LOGGER.info("ums_umsusr_umsrole_map -----------------------");
	
							query= entityManager.createNativeQuery("insert into ums_umsusr_umsrole_map(uuum_usr_id,uuum_role_id,uuum_start_date,uuum_end_date,uuum_created_by,uuum_created_date,uuum_modified_by,uuum_modified_date)"
									+"values(?1,'NIAADMIN',sysdate,null,?2,sysdate,?3,sysdate)");
							query.setParameter(1,provDetailsRequestASBO.getProvUserId());
							query.setParameter(2,provDetailsRequestASBO.getProvModifiedBy());
							query.setParameter(3,provDetailsRequestASBO.getProvModifiedBy());
							query.executeUpdate();
						}
						entityManager.getTransaction().commit();
					}
	
					AcknowledgeDao ackDao=new AcknowledgeDaoImpl();
					AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
					acknowledgeASBO.setProvId(provId.toString());
					acknowledgeASBO.setProvDate(new Date());
					acknowledgeASBO.setRemark("Successfully Done");
					acknowledgeASBO.setStatus("S");
					acknowledgeASBO.setBranchId(roles.getOfficeCode());
					//acknowledgeASBO.setEndDate(null==roles.getEndDate()?null:formatter.parse(roles.getEndDate()));
					ackDao.acknowledgeRequest(acknowledgeASBO,xlFlag);
				}catch(Exception e)
				{
					LOGGER.error("error", e);
					LOGGER.error(e.getStackTrace(),e);
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
			}
			
			
			if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("JIRA")){
	            pushToJIRA(appRoleMapRequestASBO,xlFlag);
	                
	        }
			LOGGER.info("Hi in code------------"+appRoleMapRequestASBO.getAppId());
			if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("WORKFLOW")){
				LOGGER.info("Hi in code------------");
				pushToWorkflow(appRoleMapRequestASBO, xlFlag);
			}
		}

	public void pushToOID(Long requestId,Long provId, boolean xlFlag)
	{
		URL url = null;
		HttpsURLConnection connection = null;
		try {
			url = new URL(UtilProperties.getOIDServiceUrl());
			StringBuilder response = new StringBuilder();

			connection= (HttpsURLConnection) url.openConnection();

			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			outputStreamWriter.write("{\"requestID\":"+requestId+",\"provisionID\":"+provId+"}");
			outputStreamWriter.flush();
			outputStreamWriter.close();
			LOGGER.info("--Response from oid service------------"+connection.getResponseCode());
			if (connection.getResponseCode() == 200) {
					BufferedReader br =null;
				try{
						br= new BufferedReader(new InputStreamReader((connection.getInputStream())));               
					String s=null;
					while ((s=br.readLine()) != null) {
						response.append(s);
					}
				}catch(Exception e){
	        		LOGGER.error(e.getStackTrace(),e);
	        	}finally{
	        		if(null!=br)
	        		br.close();
	        	}
				LOGGER.info("-ServiceResponse -"+response);
				JsonElement jelement = new JsonParser().parse(response.toString());
				JsonObject jobject = jelement.getAsJsonObject();
				AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
				acknowledgeASBO.setProvId(provId.toString());
				acknowledgeASBO.setProvDate(new Date());
				acknowledgeASBO.setRemark(jobject.get("statusMessage").getAsString());
				acknowledgeASBO.setStatus(jobject.get("statusCode").getAsString());
				AcknowledgeDao ackDao=new AcknowledgeDaoImpl();
				ackDao.acknowledgeRequest(acknowledgeASBO,xlFlag);
			}
		} catch (Exception e) {
			LOGGER.info("Error :" + e);
			LOGGER.error(e.getStackTrace(),e);

		}
	}


	@Override
	public GetRoleDetailsResponseASBO updateXLUserRolesDetails(
			UpdateRoleDetailsASBO getRoleDetailsRequestASBO) {
		LOGGER.info("Reached inside RoleDaoImpl");
		AcknowledgeDao ackDao=new AcknowledgeDaoImpl();
		EntityManager entityManager = DBUtil.getEntityManager();
		boolean xlFlag = false;

		GetRoleDetailsResponseASBO getRoleDetailsResponseASBO = new GetRoleDetailsResponseASBO();
		LOGGER.info("in Update Role Details DAO----------------------------"
				+ getRoleDetailsRequestASBO.toString());
		Long requestId = 0L;
		Long provId;
		/* Request Master Update */
		Date date = new Date();

		try {
			LOGGER.info("EntityManager created inside ");
			entityManager.getTransaction().begin();
			LOGGER.info("transaction begin");
			RoleRequestMaster roleRequestMaster = new RoleRequestMaster();
			roleRequestMaster.setModifyBy(getRoleDetailsRequestASBO
					.getRequestBy());
			roleRequestMaster.setModifydate(date);
			roleRequestMaster.setReason(getRoleDetailsRequestASBO.getReason());
			roleRequestMaster.setRequestBy(getRoleDetailsRequestASBO
					.getRequestBy());
			roleRequestMaster.setRequestDate(date);
			entityManager.persist(roleRequestMaster);
			LOGGER.info("value persist");
			entityManager.getTransaction().commit();
			LOGGER.info("transaction commit");
			requestId = roleRequestMaster.getRequestId();
			getRoleDetailsResponseASBO.setResultStatus("S");
			getRoleDetailsResponseASBO
			.setStatusMessage("Request Master Updated");
			LOGGER.info("requestId generated--------------------" + requestId);
			entityManager.clear();
		} catch (Exception ex) {
			getRoleDetailsResponseASBO.setResultStatus("F");
			getRoleDetailsResponseASBO
			.setStatusMessage("Request Master Update Failed");
			LOGGER.info("Error" + ex);
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


		for (RoleDetailsASBO roles : getRoleDetailsRequestASBO.getUserRoles()) {
			LOGGER.info(roles.toString());
			ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
			provDetailsRequestASBO.setProvAction(roles.getAction() + "R");
			provDetailsRequestASBO.setProvApplicationId(roles.getAppId());
			provDetailsRequestASBO.setProvStatus("R");
			provDetailsRequestASBO.setProvBranchId(roles.getBranchId());
			provDetailsRequestASBO.setProvCreatedBy(getRoleDetailsRequestASBO.getRequestBy());
			provDetailsRequestASBO.setProvRoleId(roles.getRoleId());
			provDetailsRequestASBO.setProvUserId(roles.getUserId());
			provDetailsRequestASBO.setProvModifiedDate(new Date());
			provDetailsRequestASBO.setProvModifiedBy(getRoleDetailsRequestASBO.getRequestBy());
			provDetailsRequestASBO.setProvRequestId(requestId);
			LOGGER.info("Prov details insert-upload XL------------------");
			ProvisionDetailsResponseASBO asbo = provisionDetailsDao
					.provisionDetailsUpdate(provDetailsRequestASBO);
			provId = asbo.getProvId();
			LOGGER.info("Prov details asbo.getResultStatus()------------------------"+asbo.getResultStatus());
			if (asbo.getResultStatus().equalsIgnoreCase("S")) {
				AppRoleMapRequestASBO appRoleMapRequestASBO = new AppRoleMapRequestASBO();
				appRoleMapRequestASBO.setCreatedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				appRoleMapRequestASBO.setModifiedBy(getRoleDetailsRequestASBO
						.getRequestBy());
				appRoleMapRequestASBO.setProvId(provId.toString());
				appRoleMapRequestASBO.setRequestId(requestId.toString());
				appRoleMapRequestASBO.setRoleId(roles.getRoleId());
				appRoleMapRequestASBO.setUserId(roles
						.getUserId());
				appRoleMapRequestASBO.setStartDate(new Date());
				appRoleMapRequestASBO.setBranchId(roles.getBranchId());
				appRoleMapRequestASBO.setActionDo(roles.getAction());
				appRoleMapRequestASBO.setAppId(roles.getAppId());
				appRoleMapRequestASBO.setRoleName(roles.getRoleName());
				AppRoleDao roalDao = new AppRoleDaoImpl();
				LOGGER.info("Updateing in App role map-------------------------");
				AppRoleMapResponseASBO appRoleMapResponseASBO =roalDao.appRoleMap(appRoleMapRequestASBO);

				LOGGER.info("Role update successfull for role ID "+ appRoleMapRequestASBO.toString());
				if(appRoleMapResponseASBO.getStatus().equalsIgnoreCase("S"))
				{
					if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("FINANCIALS")&& (appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("A"))){	
						LOGGER.info("LDIF file check---------------------------------");
						roalDao.appldifRoleMap(appRoleMapRequestASBO);
					}
					//OID integration starts----------------------------------------------------------------------------------
					if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("OID")){
						LOGGER.info("calling OID service for XL------------");
						pushToOID(requestId,provId,xlFlag);
					}

				}
				if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("UMS")){
					LOGGER.info("Updating recodrds for UMS application-----------------");
					EntityManager entityManagerUMS = DBUtil.getEntityManager();
					try{
						if(appRoleMapRequestASBO.getActionDo().equalsIgnoreCase("A"))
						{

							Query userQuery= entityManagerUMS.createNativeQuery("select * from ums_usr_mst where uum_usr_id=:userId");
							userQuery.setParameter("userId", appRoleMapRequestASBO.getUserId());
							List masterList=userQuery.getResultList();
							LOGGER.info("User master List---------"+masterList);
							Iterator iter=masterList.iterator();
							String firstName=null;
							String lastName=null;
							String email=null;

							while (iter.hasNext()) {
								Object[] masterData= (Object[]) iter.next();
								firstName=masterData[3].toString();
								lastName=masterData[5].toString();
								email=masterData[16].toString();
							}
							LOGGER.info("email--------"+email);

							Query query= entityManagerUMS.createNativeQuery("select count(1) from ums_umsusr_access uua where uua.uua_usr_id=:userId and uua.uua_end_date is null");
							query.setParameter("userId",appRoleMapRequestASBO.getUserId());
							int count =((Number)query.getSingleResult()).intValue();

							entityManagerUMS.getTransaction().begin();
							LOGGER.info("count-----------"+count);
							if(count==0)
							{
								query= entityManagerUMS.createNativeQuery("insert into ums_umsusr_access(uua_usr_id, uua_branch_id,uua_applicaion,uua_created_by,uua_created_date,uua_modified_by,uua_modified_date,uua_start_date,uua_end_date, uua_first_name, uua_last_name, uua_email)"
										+" values(?1, ?2, 'NIAADMIN', ?3, sysdate, ?4, sysdate,sysdate, null, ?5,?6,?7)");
								query.setParameter(1,provDetailsRequestASBO.getProvUserId());
								query.setParameter(2,provDetailsRequestASBO.getProvBranchId());
								query.setParameter(3,provDetailsRequestASBO.getProvCreatedBy());
								query.setParameter(4,provDetailsRequestASBO.getProvModifiedBy());
								query.setParameter(5,firstName);
								query.setParameter(6,lastName);
								query.setParameter(7,email);
								query.executeUpdate();

								LOGGER.info("ums_umsusr_umsrole_map -----------------------");

								query= entityManagerUMS.createNativeQuery("insert into ums_umsusr_umsrole_map(uuum_usr_id,uuum_role_id,uuum_start_date,uuum_end_date,uuum_created_by,uuum_created_date,uuum_modified_by,uuum_modified_date)"
										+"values(?1,'NIAADMIN',sysdate,null,?2,sysdate,?3,sysdate)");
								query.setParameter(1,provDetailsRequestASBO.getProvUserId());
								query.setParameter(2,provDetailsRequestASBO.getProvModifiedBy());
								query.setParameter(3,provDetailsRequestASBO.getProvModifiedBy());
								query.executeUpdate();
							}
							entityManagerUMS.getTransaction().commit();
						}


						AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
						acknowledgeASBO.setProvId(provId.toString());
						acknowledgeASBO.setProvDate(new Date());
						acknowledgeASBO.setRemark("Successfully Done");
						acknowledgeASBO.setStatus("S");
						ackDao.acknowledgeRequest(acknowledgeASBO,xlFlag);
					}catch(Exception e)
					{
						LOGGER.error("error", e);
						LOGGER.error(e.getStackTrace(),e);
					}finally{
						try{
							if(null!=entityManagerUMS){
								entityManagerUMS.close();
							}
						}
						catch(Exception e){
							LOGGER.error(e.getStackTrace(),e);

						}
					}
				}
				
				//JIRA Integration
				if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("JIRA")){
				    pushToJIRA(appRoleMapRequestASBO,xlFlag);
				}
				LOGGER.info("Hi in code------------"+appRoleMapRequestASBO.getAppId());
				if(appRoleMapRequestASBO.getAppId().equalsIgnoreCase("WORKFLOW")){
					LOGGER.info("Hi in code------------");
					pushToWorkflow(appRoleMapRequestASBO, xlFlag);
				}
			}   
		}  

		ackDao.mailUpdateRoleXL(requestId.toString());

		getRoleDetailsResponseASBO.setRequestId(requestId.toString());

		return getRoleDetailsResponseASBO;
	}


	@Override
	public void getUmsAndOidReq() {
		boolean xlFlag = true;
		EntityManager entityManager = null;
		try {
			entityManager = DBUtil.getEntityManager();

			TypedQuery<ProvisionDetails> provDtls= entityManager.createQuery("from ProvisionDetails where provApplicationId in ('OID','UMS','JIRA') and provStatus='R' and provAction in ('AR', 'DR') and provModifiedDate <= sysdate ",ProvisionDetails.class);

			List<ProvisionDetails> provList= provDtls.getResultList();

			LOGGER.info("Data Length for Add and Update: " + provList.size());
			if (!provList.isEmpty()) {

				for (ProvisionDetails provisionDetails : provList) {
					if(provisionDetails.getProvApplicationId().equalsIgnoreCase("OID")){
						LOGGER.info("calling OID service for batch update------------");
						pushToOID(Long.valueOf(provisionDetails.getProvRequestId()),provisionDetails.getProvId(),xlFlag);
					}

					if(provisionDetails.getProvAction().equalsIgnoreCase("AR")&& provisionDetails.getProvApplicationId().equalsIgnoreCase("UMS")){
						LOGGER.info("Inside UMS update for batch ------------");
						Query userQuery= entityManager.createNativeQuery("select * from ums_usr_mst where uum_usr_id=:userId");
						userQuery.setParameter("userId", provisionDetails.getProvUserId());

						List masterList=userQuery.getResultList();
						LOGGER.info("User master List---------  "+masterList.size());
						Iterator iter=masterList.iterator();
						String firstName=null;
						String lastName=null;
						String email=null;

						while (iter.hasNext()) {
							Object[] masterData= (Object[]) iter.next();
							firstName=masterData[3].toString();
							lastName=masterData[5].toString();
							email=masterData[16].toString();
						}
						LOGGER.info("email--------"+email);

						Query query= entityManager.createNativeQuery("select count(1) from ums_umsusr_access uua where uua.uua_usr_id=:userId and (uua.uua_end_date is null or uua.uua_end_date > sysdate) ");
						query.setParameter("userId", provisionDetails.getProvUserId());
						int count =((Number)query.getSingleResult()).intValue();

						entityManager.getTransaction().begin();
						LOGGER.info("count-----------"+count);
						if(count==0)
						{
							query= entityManager.createNativeQuery("insert into ums_umsusr_access(uua_usr_id, uua_branch_id,uua_applicaion,uua_created_by,uua_created_date,uua_modified_by,uua_modified_date,uua_start_date,uua_end_date, uua_first_name, uua_last_name, uua_email)"
									+" values(?1, ?2, 'NIAADMIN', ?3, sysdate, ?4, sysdate,sysdate, null, ?5,?6,?7)");
							query.setParameter(1, provisionDetails.getProvUserId());
							query.setParameter(2, provisionDetails.getProvBranchId());
							query.setParameter(3, provisionDetails.getProvModifiedBy());
							query.setParameter(4, provisionDetails.getProvModifiedBy());
							query.setParameter(5,firstName);
							query.setParameter(6,lastName);
							query.setParameter(7,email);
							query.executeUpdate();

							LOGGER.info("ums_umsusr_umsrole_map -----------------------");

							query= entityManager.createNativeQuery("insert into ums_umsusr_umsrole_map(uuum_usr_id,uuum_role_id,uuum_start_date,uuum_end_date,uuum_created_by,uuum_created_date,uuum_modified_by,uuum_modified_date)"
									+"values(?1,'NIAADMIN',sysdate,null,?2,sysdate,?3,sysdate)");
							query.setParameter(1, provisionDetails.getProvUserId());
							query.setParameter(2, provisionDetails.getProvModifiedBy());
							query.setParameter(3, provisionDetails.getProvModifiedBy());
							query.executeUpdate();
						}
						entityManager.getTransaction().commit();

						AcknowledgeDao ackDao=new AcknowledgeDaoImpl();
						AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
						acknowledgeASBO.setProvId(provisionDetails.getProvId().toString());
						acknowledgeASBO.setProvDate(new Date());
						acknowledgeASBO.setRemark("Successfully Done");
						acknowledgeASBO.setStatus("S");
						acknowledgeASBO.setBranchId(provisionDetails.getProvBranchId());
						ackDao.acknowledgeRequest(acknowledgeASBO,xlFlag);

					}
					//JIRA Integration
					if(provisionDetails.getProvApplicationId().equalsIgnoreCase("JIRA")){
                        LOGGER.info("calling OID service for batch update------------");
                        
                        AppRoleMapRequestASBO appRoleMapRequestASBO=new AppRoleMapRequestASBO();
                        appRoleMapRequestASBO.setUserId(provisionDetails.getProvUserId());
                        appRoleMapRequestASBO.setRoleId(provisionDetails.getProvRoleId());
                        if(provisionDetails.getProvAction().equalsIgnoreCase("AR")){
                        appRoleMapRequestASBO.setActionDo("A");
                        }else if(provisionDetails.getProvAction().equalsIgnoreCase("DR")){
                            appRoleMapRequestASBO.setActionDo("D");
                        }
                        pushToJIRA(appRoleMapRequestASBO,xlFlag);
                    }
				}
			}

		} catch (Exception e) {
			LOGGER.info("Exception in getProvisionDetails" + e.getMessage());
			LOGGER.error(e.getStackTrace(),e);
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
	}
	public void pushToJIRA(AppRoleMapRequestASBO appRoleMapRequestASBO,boolean xlFlag)
	{
	    String userId=appRoleMapRequestASBO.getUserId();
	    LOGGER.info("User Id  came for Jira Application-----"+appRoleMapRequestASBO.toString()); 
	    // If action is add for Jira Application
	    
	    LOGGER.info("{\"userid\":\""+appRoleMapRequestASBO.getUserId()+"\",\"roleName\":\""+appRoleMapRequestASBO.getRoleId()+"\",\"action\":\""+appRoleMapRequestASBO.getActionDo()+"\"}");
	        URL url = null;
	        HttpsURLConnection connection = null;
	        try {
	            url = new URL(UtilProperties.getJiraServiceUrl());
	            LOGGER.info(UtilProperties.getJiraServiceUrl());
	            StringBuilder response = new StringBuilder();

	            connection= (HttpsURLConnection) url.openConnection();

	            connection.setDoOutput(true);
	            connection.setRequestMethod("POST");
	            connection.setRequestProperty("Content-Type", "application/json");

	            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
	            outputStreamWriter.write("{\"userid\":\""+appRoleMapRequestASBO.getUserId()+"\",\"roleName\":\""+appRoleMapRequestASBO.getRoleId()+"\",\"action\":\""+appRoleMapRequestASBO.getActionDo()+"\"}");
	            outputStreamWriter.flush();
	            outputStreamWriter.close();
	            LOGGER.info("--Response from JIRA service------------"+connection.getResponseCode());
	            if (connection.getResponseCode() == 200) {
	                    BufferedReader br =null;
	                try{
	                        br= new BufferedReader(new InputStreamReader((connection.getInputStream())));               
	                    String s=null;
	                    while ((s=br.readLine()) != null) {
	                        response.append(s);
	                    }
	                }catch(Exception e){
	                    LOGGER.error(e.getStackTrace(),e);
	                }finally{
	                    if(null!=br)
	                    { br.close();}
	                }
	                LOGGER.info("-ServiceResponse -"+response);
	                JsonElement jelement = new JsonParser().parse(response.toString());
	                JsonObject jobject = jelement.getAsJsonObject();

	                AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
	                acknowledgeASBO.setProvId(appRoleMapRequestASBO.getProvId().toString());
	                acknowledgeASBO.setProvDate(new Date());
	                acknowledgeASBO.setRemark(jobject.get("statusMessage").getAsString());
	                acknowledgeASBO.setStatus(jobject.get("resultStatus").getAsString());
	                AcknowledgeDao ackDao=new AcknowledgeDaoImpl();
	                ackDao.acknowledgeRequest(acknowledgeASBO,xlFlag);
	            }
	        } catch (Exception e) {
	            LOGGER.info("Error :" + e);
	            LOGGER.error(e.getStackTrace(),e);
	        }
	}
	
	public void pushToWorkflow(AppRoleMapRequestASBO appRoleMapRequestASBO, boolean xlFlag){
            String userId = appRoleMapRequestASBO.getUserId();
            EntityManager entityManager = DBUtil.getEntityManager();
            Query userQuery = entityManager.createNativeQuery("select * from ums_usr_mst where uum_usr_id=:userId");
            userQuery.setParameter("userId",userId);
            List masterList = userQuery.getResultList();
            LOGGER.info("User master List---------  " + masterList.size());
            Iterator iter = masterList.iterator();
            String designation = null;
            while (iter.hasNext()) {
                Object[] masterData = (Object[])iter.next();
                designation = masterData[30].toString();
            }
            LOGGER.info("User Id  came for Jira Application-----" + appRoleMapRequestASBO.toString());
            
            appRoleMapRequestASBO.setRoleName(getRoleName(appRoleMapRequestASBO.getRoleId()));
            LOGGER.info("{\"userId\":\"" + appRoleMapRequestASBO.getUserId() + "\",\"officeCode\":\"" + appRoleMapRequestASBO.getBranchId() + "\",\"role\":\"" + appRoleMapRequestASBO.getRoleName() + "\",\"status\":" + true + ",\"designation\":\"" + designation + "\",\"action\":\"" + appRoleMapRequestASBO.getActionDo()+"R" + "\",\"userRemark\":\"User Details Added\"}");            
            URL url = null;
            HttpURLConnection connection = null;
            try {
                StringBuilder response;
               
                    BufferedReader br;
                    url = new URL(UtilProperties.getWorkFlowServiceUrl());
                    LOGGER.info(UtilProperties.getWorkFlowServiceUrl());
                    response = new StringBuilder();
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setDoOutput(true);
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
                    outputStreamWriter.write("{\"userId\":\"" + appRoleMapRequestASBO.getUserId() + "\",\"officeCode\":\"" + appRoleMapRequestASBO.getBranchId() + "\",\"role\":\"" + appRoleMapRequestASBO.getRoleName() + "\",\"status\":" + true + ",\"designation\":\"" + designation + "\",\"action\":\"" + appRoleMapRequestASBO.getActionDo()+"R" + "\",\"userRemark\":\"User Details Added\"}");
                    outputStreamWriter.flush();
                    outputStreamWriter.close();
                    LOGGER.info("--Response from Workflow service------------" + connection.getResponseCode());
                    if (connection.getResponseCode() == 200)
                    {
                     
                            br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                            String s = null;
                            while ((s = br.readLine()) != null) {
                            	LOGGER.info("-------"+br.readLine());
                                response.append(s);
                     
                }
                    }
                LOGGER.info("-ServiceResponse -" + response);
                JsonElement jelement = new JsonParser().parse(response.toString());
                JsonObject jobject = jelement.getAsJsonObject();
                AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
                acknowledgeASBO.setProvId(appRoleMapRequestASBO.getProvId().toString());
                acknowledgeASBO.setProvDate(new Date());
                acknowledgeASBO.setRemark(jobject.get("statusMessage").getAsString());
                acknowledgeASBO.setStatus(jobject.get("resultStatus").getAsString());
                AcknowledgeDaoImpl ackDao = new AcknowledgeDaoImpl();
                ackDao.acknowledgeRequest(acknowledgeASBO, xlFlag);
            }
            catch (Exception e) {
                LOGGER.info("Error :" + e);
                LOGGER.error(e.getStackTrace());
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
        }
	public String getRoleName(String roleId) {
        EntityManager entityManager = DBUtil.getEntityManager();
        String roleName="";
        try{
             LOGGER.info("User Id  came for Jira Application-----"+roleId);
             LOGGER.info("User "+ roleId +" Role ID");
                     //Call UMS DataBase to get Information of User 
                     Query userJiraQuery= entityManager.createNativeQuery("select URM_ROLE_NAME from UMS_ROLE_MST where URM_ROLE_ID=:roleId");
                     userJiraQuery.setParameter("roleId", roleId);
                     List userList=userJiraQuery.getResultList();
                     LOGGER.info("User master List get from UMS DB for create User in Jira---------"+userList);
                    if(!userList.isEmpty()){
                        roleName=userList.get(0).toString();
                    }
                    LOGGER.info("Value of getCreateJiraUserRequestDetailsobject --"+roleName);
        }catch (Exception ex) {
            LOGGER.info("Error" + ex);
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
        return roleName;
    }

}