package com.tcs.umsrole.persist.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tcs.umsrole.persist.DBUtil;
import com.tcs.umsrole.persist.entity.PermListHistoryUpdate;
import com.tcs.umsrole.persist.entity.ProvisionDetails;
import com.tcs.umsrole.persist.entity.ProvisionLog;
import com.tcs.umsrole.request.AcknowledgeASBO;
import com.tcs.umsrole.request.MailContentRequest;
import com.tcs.umsrole.vo.util.UtilProperties;


public class AcknowledgeDaoImpl implements AcknowledgeDao{
	private static final Logger LOGGER = Logger.getLogger(AcknowledgeDaoImpl.class);

	@Override
	public void acknowledgeRequest(
			AcknowledgeASBO acknowledgeASBO, boolean workflowFlag) {
		EntityManager entityManager = DBUtil.getEntityManager();
		Date currentDate =new Date();
		try {

			if("S".equalsIgnoreCase(acknowledgeASBO.getStatus())||"F".equalsIgnoreCase(acknowledgeASBO.getStatus()))
			{  
				entityManager.getTransaction().begin();
				LOGGER.info("Prov details updated -----------------"+acknowledgeASBO.getStatus());
				Query query= entityManager.createQuery("update ProvisionDetails set provStatus=:provStatus,provDate=sysdate,provModifiedDate=sysdate where provId=:provId");
				query.setParameter("provStatus", acknowledgeASBO.getStatus());
				//              query.setParameter("provDate", acknowledgeASBO.getProvDate());
				query.setParameter("provId", Long.parseLong(acknowledgeASBO.getProvId()));


				int stat= query.executeUpdate();

				entityManager.getTransaction().commit();

				LOGGER.info("Prov dtls update status count -----------------"+stat);
				LOGGER.info("Prov details updated for -----------------"+acknowledgeASBO.getProvId());

				LOGGER.info("Prov Logs update start -----------------");
				entityManager.getTransaction().begin(); 
				ProvisionLog provisionLog=new ProvisionLog();
				provisionLog.setProvisionId(Long.valueOf(acknowledgeASBO.getProvId()));
				provisionLog.setProvisionStatus(acknowledgeASBO.getStatus());
				provisionLog.setErrorNo(acknowledgeASBO.getSqlCode());
				provisionLog.setErrorDec(acknowledgeASBO.getSqlMessage());
				provisionLog.setRemark(acknowledgeASBO.getRemark());
				provisionLog.setCreatedBy("UMS");
				provisionLog.setCreatedDate(currentDate);
				provisionLog.setModifyBy("UMS");
				provisionLog.setModifyDate(currentDate);

				entityManager.persist(provisionLog);
				entityManager.getTransaction().commit();

				TypedQuery<ProvisionDetails> provDtls= entityManager.createQuery("from ProvisionDetails where provId=:provId",ProvisionDetails.class);
				provDtls.setParameter("provId", Long.parseLong(acknowledgeASBO.getProvId()));
				List<ProvisionDetails> provList= provDtls.getResultList();
				LOGGER.info("Provisions List size for update :  " + provList.size());
				if(!provList.isEmpty()&& "S".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "AR".equalsIgnoreCase(provList.get(0).getProvAction()))
				{      
					entityManager.getTransaction().begin();
					Query roleMapQuery= entityManager.createQuery("update UmsRoleMap set roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate where roleMapProvId=:roleMapProvId and (roleMapEndDate is null or roleMapEndDate > sysdate) and branchId=:branchId ");
					roleMapQuery.setParameter("roleMapModifiedBy", provList.get(0).getProvModifiedBy());
					roleMapQuery.setParameter("roleMapProvId", acknowledgeASBO.getProvId());
					roleMapQuery.setParameter("branchId", provList.get(0).getProvBranchId());
					int uurmCount = roleMapQuery.executeUpdate();
					LOGGER.info("-------Updated UURM ---  "  + acknowledgeASBO.getProvId() +"      "+ uurmCount ); 

					Query applMapQuery= entityManager.createQuery("update UmsApplicationMap set appMapApprovedStatus='Y',appMapModifiedBy=:appMapModifiedBy,appMapModifiedDate=sysdate where appMapUserId=:appMapUserId and appMapappId=:appMapappId and (appMapEndDate is null or appMapEndDate > sysdate) ");
					applMapQuery.setParameter("appMapModifiedBy", provList.get(0).getProvModifiedBy());
					applMapQuery.setParameter("appMapUserId", provList.get(0).getProvUserId());
					applMapQuery.setParameter("appMapappId", provList.get(0).getProvApplicationId());
					int uuamCount = applMapQuery.executeUpdate();
					entityManager.getTransaction().commit();
					LOGGER.info("-------Updated UUAM for AR---------------   "+acknowledgeASBO.getProvId() + "      "  + uuamCount);
					//call mail update user --------------

					if(workflowFlag){
						mailUpdateRole(provList);
					}                      
				}else if(!provList.isEmpty()&& "S".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "DR".equalsIgnoreCase(provList.get(0).getProvAction()))
				{
					entityManager.getTransaction().begin();
					Query roleMap= entityManager.createQuery("update UmsRoleMap set roleMapProvId=:roleMapProvId,roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate,roleMapEndDate=sysdate where roleMapUserId=:roleMapUserId and roleMapApproveStatus='N' and roleMapRoleId=:roleMapRoleId and branchId=:branchId ");
					roleMap.setParameter("roleMapProvId", acknowledgeASBO.getProvId());
					roleMap.setParameter("roleMapModifiedBy", provList.get(0).getProvModifiedBy());
					roleMap.setParameter("roleMapUserId", provList.get(0).getProvUserId());
					roleMap.setParameter("roleMapRoleId", provList.get(0).getProvRoleId());
					roleMap.setParameter("branchId", provList.get(0).getProvBranchId());
					int i=roleMap.executeUpdate();
					LOGGER.info("Updated UURM status for DR -------"+i);
					Query userQuery= entityManager.createNativeQuery("select count(*) from ums_usr_role_map uurm, ums_usr_appl_map uuam,ums_role_mst urm where uurm.uurm_usr_id = :userId and (uurm.uurm_end_date is null or uurm.uurm_end_date > sysdate) and uurm.uurm_appl_approved_status = 'Y' and uurm.uurm_usr_id = uuam.uuam_usr_id and uuam.uuam_appl_id = :applId and (uuam.uuam_end_date IS NULL OR uuam.uuam_end_date > sysdate) and urm.urm_role_id = uurm.uurm_role_id and urm.urm_appl_id = uuam.uuam_appl_id and urm.urm_status='E'");
					userQuery.setParameter("userId", provList.get(0).getProvUserId());
					userQuery.setParameter("applId", provList.get(0).getProvApplicationId());
//					userQuery.setParameter("branchId", provList.get(0).getProvBranchId());
					int count =((Number)userQuery.getSingleResult()).intValue();

					LOGGER.info("count--------  "+count);

					if(count==0)
					{ 
						Query applMap= entityManager.createQuery("update UmsApplicationMap set appMapEndDate=sysdate,appMapModifiedBy=:appMapModifiedBy,appMapModifiedDate=sysdate where appMapUserId=:appMapUserId and appMapappId=:appMapappId");
						applMap.setParameter("appMapModifiedBy", provList.get(0).getProvModifiedBy());
						applMap.setParameter("appMapUserId", provList.get(0).getProvUserId());
						applMap.setParameter("appMapappId", provList.get(0).getProvApplicationId());
						applMap.executeUpdate();

					}
					entityManager.getTransaction().commit();
					if(workflowFlag){
						mailUpdateRole(provList);
					}                      

				}else if(!provList.isEmpty()&& "F".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "AR".equalsIgnoreCase(provList.get(0).getProvAction()))
				{
					entityManager.getTransaction().begin();
					Query roleMap= entityManager.createQuery("update UmsRoleMap set roleMapEndDate=sysdate,roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate where roleMapProvId=:roleMapProvId and branchId =:branchId");
					roleMap.setParameter("roleMapModifiedBy", provList.get(0).getProvModifiedBy());
					roleMap.setParameter("roleMapProvId", acknowledgeASBO.getProvId());
					roleMap.setParameter("branchId", provList.get(0).getProvBranchId());
					roleMap.executeUpdate();

					Query userQuery= entityManager.createNativeQuery("select count(*)from ums_usr_role_map uurm,ums_usr_appl_map uuam,ums_role_mst urm where uurm.uurm_usr_id = :userId and (uurm.uurm_end_date is null or uurm.uurm_end_date > sysdate) and uurm.uurm_usr_id = uuam.uuam_usr_id and uuam.uuam_appl_id = :applId and ( uuam.uuam_end_date is null or uuam.uuam_end_date > sysdate) and urm.urm_role_id = uurm.uurm_role_id and urm.urm_appl_id = uuam.uuam_appl_id and urm.urm_status='E' ");
					userQuery.setParameter("userId", provList.get(0).getProvUserId());
					userQuery.setParameter("applId", provList.get(0).getProvApplicationId());
//					userQuery.setParameter("branchId", provList.get(0).getProvBranchId());
					int count =((Number)userQuery.getSingleResult()).intValue();

					LOGGER.info("count "+count);
					if(count==0)
					{ 
						Query applMap= entityManager.createQuery("update UmsApplicationMap set appMapEndDate=sysdate,appMapModifiedBy=:appMapModifiedBy,appMapModifiedDate=sysdate,appMapApprovedStatus='Y' where appMapUserId=:appMapUserId and appMapappId=:appMapappId and (appMapEndDate is null or appMapEndDate > sysdate)");
						applMap.setParameter("appMapModifiedBy", provList.get(0).getProvModifiedBy());
						applMap.setParameter("appMapUserId", provList.get(0).getProvUserId());
						applMap.setParameter("appMapappId", provList.get(0).getProvApplicationId());
						applMap.executeUpdate();

					}
					entityManager.getTransaction().commit();
					if(workflowFlag){
						mailUpdateRole(provList);
					}                      
				}else if(!provList.isEmpty()&& "F".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "DR".equalsIgnoreCase(provList.get(0).getProvAction()))
				{
					entityManager.getTransaction().begin();
					Query roleMap= entityManager.createQuery("update UmsRoleMap set roleMapEndDate=sysdate,roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate where roleMapUserId=:roleMapUserId and roleMapRoleId=:roleMapRoleId and roleMapApproveStatus='N' and branchId =:branchId");
					roleMap.setParameter("roleMapModifiedBy", provList.get(0).getProvModifiedBy());
					roleMap.setParameter("roleMapRoleId", provList.get(0).getProvRoleId());
					roleMap.setParameter("roleMapUserId",provList.get(0).getProvUserId());
					roleMap.setParameter("branchId", provList.get(0).getProvBranchId());

					roleMap.executeUpdate();
					entityManager.getTransaction().commit();
					if(workflowFlag){
						mailUpdateRole(provList);
					}                      
				}else if(!provList.isEmpty()&& "F".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "AP".equalsIgnoreCase(provList.get(0).getProvAction()))
				{
					entityManager.getTransaction().begin();
					TypedQuery<PermListHistoryUpdate> permission= entityManager.createQuery("from PermListHistoryUpdate where permProvisionId=:permProvisionId",PermListHistoryUpdate.class);
					permission.setParameter("permProvisionId", provList.get(0).getProvModifiedBy());
					List<PermListHistoryUpdate> list=permission.getResultList();

					Query master= entityManager.createQuery("update UserMasterUpdate set permissionList=:permissionList,umsmodifiedBy=:umsmodifiedBy,umsmodifiedDate=sysdate where userId=:userId and umsendDate=null and uumsStatus='E'");
					master.setParameter("permissionList", list.get(0).getPermPlId());
					master.setParameter("umsmodifiedBy", provList.get(0).getProvModifiedBy());
					master.setParameter("userId",provList.get(0).getProvUserId());
					master.executeUpdate();

					entityManager.getTransaction().commit();
					if(workflowFlag){
						mailUpdateRole(provList);
					}                      
				}else if(!provList.isEmpty()&& "S".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "DB".equalsIgnoreCase(provList.get(0).getProvAction()))
                {
				    TypedQuery<ProvisionDetails> provListforRoleUpdate= entityManager.createQuery("from ProvisionDetails where provRequestId=:provRequestId and provStatus='D'",ProvisionDetails.class);
				    provListforRoleUpdate.setParameter("provRequestId", provList.get(0).getProvRequestId());
	                List<ProvisionDetails> provisioList= provListforRoleUpdate.getResultList();
	                for (Iterator iterator = provisioList.iterator(); iterator
                            .hasNext();) {
                        ProvisionDetails provisionDetails = (ProvisionDetails) iterator
                                .next();
                        entityManager.getTransaction().begin();
                    
                        query= entityManager.createQuery("update ProvisionDetails set provStatus=:provStatus,provDate=sysdate,provModifiedDate=sysdate where provId=:provId");
        				query.setParameter("provStatus", acknowledgeASBO.getStatus());
        				//              query.setParameter("provDate", acknowledgeASBO.getProvDate());
        				query.setParameter("provId", provisionDetails.getProvId());
        				int stat1= query.executeUpdate();
        				LOGGER.info("Prov dtls update status count -----------------"+stat1);
        				LOGGER.info("Prov details updated for -----------------"+acknowledgeASBO.getProvId());

        				LOGGER.info("Prov Logs update start -----------------");
        				
        				provisionLog=new ProvisionLog();
        				provisionLog.setProvisionId(Long.valueOf(acknowledgeASBO.getProvId()));
        				provisionLog.setProvisionStatus(acknowledgeASBO.getStatus());
        				provisionLog.setErrorNo(acknowledgeASBO.getSqlCode());
        				provisionLog.setErrorDec(acknowledgeASBO.getSqlMessage());
        				provisionLog.setRemark(acknowledgeASBO.getRemark());
        				provisionLog.setCreatedBy("UMS");
        				provisionLog.setCreatedDate(currentDate);
        				provisionLog.setModifyBy("UMS");
        				provisionLog.setModifyDate(currentDate);
        				entityManager.persist(provisionLog);
				    
                    Query roleMap= entityManager.createQuery("update UmsRoleMap set roleMapProvId=:roleMapProvId,roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate,roleMapEndDate=sysdate where roleMapUserId=:roleMapUserId and roleMapApproveStatus='N' and roleMapRoleId=:roleMapRoleId and branchId=:branchId ");
                    roleMap.setParameter("roleMapProvId", String.valueOf(provisionDetails.getProvId()));
                    roleMap.setParameter("roleMapModifiedBy", provisionDetails.getProvModifiedBy());
                    roleMap.setParameter("roleMapUserId", provisionDetails.getProvUserId());
                    roleMap.setParameter("roleMapRoleId", provisionDetails.getProvRoleId());
                    roleMap.setParameter("branchId", provisionDetails.getProvBranchId());
                    int i=roleMap.executeUpdate();
                    LOGGER.info("Updated UURM status for DR -------"+i);
                    Query userQuery= entityManager.createNativeQuery("select count(*) from ums_usr_role_map uurm, ums_usr_appl_map uuam,ums_role_mst urm where uurm.uurm_usr_id = :userId and (uurm.uurm_end_date is null or uurm.uurm_end_date > sysdate) and uurm.uurm_appl_approved_status = 'Y' and uurm.uurm_usr_id = uuam.uuam_usr_id and uuam.uuam_appl_id = :applId and (uuam.uuam_end_date IS NULL OR uuam.uuam_end_date > sysdate) and urm.urm_role_id = uurm.uurm_role_id and urm.urm_appl_id = uuam.uuam_appl_id and urm.urm_status='E'");
                    userQuery.setParameter("userId", provisionDetails.getProvUserId());
                    userQuery.setParameter("applId", provisionDetails.getProvApplicationId());
//                  userQuery.setParameter("branchId", provList.get(0).getProvBranchId());
                    int count =((Number)userQuery.getSingleResult()).intValue();

                    LOGGER.info("count--------  "+count);

                    if(count==0)
                    { 
                        Query applMap= entityManager.createQuery("update UmsApplicationMap set appMapEndDate=sysdate,appMapModifiedBy=:appMapModifiedBy,appMapModifiedDate=sysdate where appMapUserId=:appMapUserId and appMapappId=:appMapappId");
                        applMap.setParameter("appMapModifiedBy", provisionDetails.getProvModifiedBy());
                        applMap.setParameter("appMapUserId", provisionDetails.getProvUserId());
                        applMap.setParameter("appMapappId", provisionDetails.getProvApplicationId());
                        applMap.executeUpdate();

                    }
                    entityManager.getTransaction().commit();
//                    if(workflowFlag){
//                        mailUpdateRole(provList);
//                    }                      

	                    }
	                }else if(!provList.isEmpty()&& "F".equalsIgnoreCase(acknowledgeASBO.getStatus()) && "DB".equalsIgnoreCase(provList.get(0).getProvAction()))
	                {
	                    TypedQuery<ProvisionDetails> provListforRoleUpdate= entityManager.createQuery("from ProvisionDetails where provRequestId=:provRequestId and provStatus='D'",ProvisionDetails.class);
	                    provListforRoleUpdate.setParameter("provRequestId", provList.get(0).getProvRequestId());
	                    List<ProvisionDetails> provisioList= provListforRoleUpdate.getResultList();
	                    for (Iterator iterator = provisioList.iterator(); iterator
	                            .hasNext();) {
	                        ProvisionDetails provisionDetails = (ProvisionDetails) iterator
	                                .next();
	                        
	                        entityManager.getTransaction().begin();
	                        
	                        query= entityManager.createQuery("update ProvisionDetails set provStatus=:provStatus,provDate=sysdate,provModifiedDate=sysdate where provId=:provId");
	        				query.setParameter("provStatus", acknowledgeASBO.getStatus());
	        				//              query.setParameter("provDate", acknowledgeASBO.getProvDate());
	        				query.setParameter("provId", provisionDetails.getProvId());
	        				int stat1= query.executeUpdate();
	        				LOGGER.info("Prov dtls update status count -----------------"+stat1);
	        				LOGGER.info("Prov details updated for -----------------"+acknowledgeASBO.getProvId());

	        				LOGGER.info("Prov Logs update start -----------------");
	        				
	        				provisionLog=new ProvisionLog();
	        				provisionLog.setProvisionId(Long.valueOf(acknowledgeASBO.getProvId()));
	        				provisionLog.setProvisionStatus(acknowledgeASBO.getStatus());
	        				provisionLog.setErrorNo(acknowledgeASBO.getSqlCode());
	        				provisionLog.setErrorDec(acknowledgeASBO.getSqlMessage());
	        				provisionLog.setRemark(acknowledgeASBO.getRemark());
	        				provisionLog.setCreatedBy("UMS");
	        				provisionLog.setCreatedDate(currentDate);
	        				provisionLog.setModifyBy("UMS");
	        				provisionLog.setModifyDate(currentDate);
	        				entityManager.persist(provisionLog);
	        				
	                        LOGGER.info("finally updating role status for deleted branch----------------------"+provisionDetails.getProvRoleId());
	                 
	                    Query roleMap= entityManager.createQuery("update UmsRoleMap set roleMapEndDate=sysdate,roleMapApproveStatus='Y',roleMapModifiedBy=:roleMapModifiedBy,roleMapModifiedDate=sysdate where roleMapUserId=:roleMapUserId and roleMapRoleId=:roleMapRoleId and roleMapApproveStatus='N' and branchId =:branchId");
	                    roleMap.setParameter("roleMapModifiedBy", provisionDetails.getProvModifiedBy());
	                    roleMap.setParameter("roleMapRoleId", provisionDetails.getProvRoleId());
	                    roleMap.setParameter("roleMapUserId",provisionDetails.getProvUserId());
	                    roleMap.setParameter("branchId", provisionDetails.getProvBranchId());

	                    roleMap.executeUpdate();
	                    entityManager.getTransaction().commit();
//	                    if(workflowFlag){
//	                        mailUpdateRole(provList);
//	                    }  
	                    }
	                }

			}


			if(!"S".equalsIgnoreCase(acknowledgeASBO.getStatus())&& !"F".equalsIgnoreCase(acknowledgeASBO.getStatus()))
			{
				entityManager.getTransaction().begin();
				ProvisionLog provisionLog=new ProvisionLog();
				provisionLog.setProvisionId(Long.valueOf(acknowledgeASBO.getProvId()));
				provisionLog.setProvisionStatus(acknowledgeASBO.getStatus());
				provisionLog.setErrorNo(acknowledgeASBO.getSqlCode());
				provisionLog.setErrorDec(acknowledgeASBO.getSqlMessage());
				provisionLog.setRemark("Invalid MailID");
				provisionLog.setCreatedBy("UMS");
				provisionLog.setCreatedDate(new Date());
				provisionLog.setModifyBy("UMS");
				provisionLog.setModifyDate(new Date());
				entityManager.persist(provisionLog);
				entityManager.getTransaction().commit();
			}
			entityManager.clear();
		} catch (Exception ex) {
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
	}



	public void sendMail(String to, String body, String fromId,String subject) {
		MailContentRequest mailContentRequest = new MailContentRequest();
		mailContentRequest.setRecepient(to);
		mailContentRequest.setCc("");
		mailContentRequest.setSender(fromId);
		mailContentRequest.setSubject(subject);
		mailContentRequest.setBody(body);

		LOGGER.info("Mail body: " + mailContentRequest);

		BufferedReader br =null;
		URL url=null;
		HttpsURLConnection connection=null;
		try{

			url = new URL(UtilProperties.getEmailUrl());
			connection= (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/json");

			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
			ObjectMapper mapper = new ObjectMapper();
			String requestJson = mapper.writeValueAsString(mailContentRequest);
			LOGGER.info("JSON String convert: " + requestJson);
			outputStreamWriter.write(requestJson);
			outputStreamWriter.flush();
			outputStreamWriter.close();

			if (connection.getResponseCode() == 200) {
				br = new BufferedReader(new InputStreamReader(connection.getInputStream()));               
				String s=null;
				while ((s=br.readLine()) != null) {
					//LOGGER.info("Response From Mail Service: " + s);

				}               
			}
		}catch(Exception e){
			LOGGER.info("Error :" + e.getMessage());
			LOGGER.error(e.getStackTrace(),e);
		}finally{
			try {
				if(null!=br)
				br.close();
			} catch (IOException e) {
				LOGGER.error(e.getStackTrace(),e);
			}
		}
	}


	public void mailUpdateRole(List<ProvisionDetails> provList)
	{
		EntityManager entityManager = DBUtil.getEntityManager();
		Query query= entityManager.createQuery("select count(1) from ProvisionDetails where provRequestId=:provRequestId");
		query.setParameter("provRequestId", provList.get(0).getProvRequestId());
		int provCount =((Number)query.getSingleResult()).intValue();

		Query roleMapCount= entityManager.createQuery("select count(1) from UmsRoleMap where roleMapRequestId=:roleMapRequestId and roleMapApproveStatus='Y' ");
		roleMapCount.setParameter("roleMapRequestId", provList.get(0).getProvRequestId());
		int roleCount =((Number)roleMapCount.getSingleResult()).intValue();

		if(provCount==roleCount)
		{
			Query userQuery= entityManager.createNativeQuery("select upd.upd_usr_id,urqm.urqm_request_id,urqm.urqm_reason,urqm.urqm_requested_by,uam.uam_appl_name,urm.urm_role_name,decode(upd.upd_prov_action,'AR' ,'Addition','DR', 'Revoke'),decode(upper(upd.upd_prov_status),'S','Success','F','Failed'),to_char(upd.upd_prov_date,'Dy dd-Mon-yyyy hh:mi:ss'),"
					+ ""+"upl.upl_remark,uum.uum_first_name||' ' || uum.uum_middle_name|| ' ' || uum.uum_last_name FullName,uua.uua_first_name||' ' || uua.uua_last_name "+
					"  ,uum.uum_email from ums_prov_dtls upd,ums_role_mst urm,ums_appl_mst uam,ums_request_mst urqm,"+
					"ums_prov_log upl,ums_usr_mst uum,ums_umsusr_access uua where "+
					"urqm.urqm_request_id=:requestId and   upd.upd_appl_id = uam.uam_appl_id and   "+
					"upd.upd_role_id = urm.urm_role_id and   upd.upd_prov_id =upl.upl_prov_id and   upd.upd_request_id = urqm.urqm_request_id and  "+
					" uum.uum_usr_id = upd.upd_usr_id and   uua.uua_usr_id = upd.upd_created_by and uum.uum_end_date is null  "+
					"and uum.uum_status = 'E' and urm.urm_status='E'");
			userQuery.setParameter("requestId", provList.get(0).getProvRequestId());
			List appRoleList=userQuery.getResultList();
			Iterator iter=appRoleList.iterator();

			LOGGER.info("Mail details list : " + appRoleList.toString());

			StringBuilder sb=new StringBuilder();
			
			String toId ;


			List<List<String>> roleDataList = new ArrayList<List<String>>();

			List<String> tRoleData;

			while(iter.hasNext()){
				Object[] rolesData= (Object[]) iter.next();
				tRoleData = new ArrayList<String>();
				tRoleData.add(rolesData[0].toString());
				tRoleData.add(rolesData[1].toString());
				tRoleData.add(rolesData[2].toString());
				tRoleData.add(rolesData[3].toString());
				tRoleData.add(rolesData[4].toString());
				tRoleData.add(rolesData[5].toString());
				tRoleData.add(rolesData[6].toString());
				tRoleData.add(rolesData[7].toString());
				tRoleData.add(rolesData[8].toString());
				tRoleData.add(rolesData[9].toString());
				tRoleData.add(rolesData[10].toString());
				tRoleData.add(rolesData[11].toString());
				tRoleData.add(rolesData[12].toString());

				roleDataList.add(tRoleData);
			}


			LOGGER.info("User Role Data Map " + roleDataList.toString());


			sb.append("<p>Your status for change in role & resposibility is as below:</p>\n")
			.append("<p>SR No:"+roleDataList.get(0).get(0)+" "+roleDataList.get(0).get(10))
			.append("</p>\n")
			.append("<p>Request ID: "+roleDataList.get(0).get(1))
			.append("</p>\n")
			.append("<p>Requested By: "+roleDataList.get(0).get(3) +" "+roleDataList.get(0).get(11))
			.append("</p>\n")
			.append("<p>Request Reason: "+roleDataList.get(0).get(2)+"</p>\n");

			for(List<String> role : roleDataList){
				sb.append("<p>Application Name: "+role.get(4)+"</p>\n")
				.append("<p>Requested Role Name: "+role.get(5)+"</p>\n")
				.append("<p>Action: "+role.get(6)+"\n")
				.append("Status: "+role.get(7)+"</p>\n")
				.append("<p>Status Remark: "+role.get(9)+"</p>\n");
			}

			sb.append("<p>Above request was initiated by SR No. "+roleDataList.get(0).get(3)+" "+roleDataList.get(0).get(11)+"</p>");

			LOGGER.info("Mail Content : " + sb.toString());

			toId=roleDataList.get(0).get(12).toString();
//			reqBy=roleDataList.get(0).get(3).toString();
//			reqName=roleDataList.get(0).get(11).toString();

			String fromId=UtilProperties.getFromId();
			String subject=UtilProperties.getSubjectForUpdateRole();
			sendMail(toId,sb.toString(),fromId,subject);



		}
	}






	public void mailUpdateRoleXL(String requestId)
	{
		EntityManager entityManager = DBUtil.getEntityManager();
		try{
		Query query= entityManager.createQuery("select count(1) from ProvisionDetails where provRequestId=:provRequestId");
		query.setParameter("provRequestId", requestId);
		int provCount =((Number)query.getSingleResult()).intValue();

		Query roleMapCount= entityManager.createQuery("select count(1) from UmsRoleMap where roleMapRequestId=:roleMapRequestId and roleMapApproveStatus='Y' ");
		roleMapCount.setParameter("roleMapRequestId", requestId);
		int roleCount =((Number)roleMapCount.getSingleResult()).intValue();

		if(provCount==roleCount)
		{
			Query userQuery= entityManager.createNativeQuery("select upd.upd_usr_id,urqm.urqm_request_id,urqm.urqm_reason,urqm.urqm_requested_by,uam.uam_appl_name,urm.urm_role_name,decode(upd.upd_prov_action,'AR' ,'Addition','DR', 'Revoke'),decode(upper(upd.upd_prov_status),'S','Success','F','Failed'),to_char(upd.upd_prov_date,'Dy dd-Mon-yyyy hh:mi:ss'),"
					+ ""+"upl.upl_remark,uum.uum_first_name||' ' || uum.uum_middle_name|| ' ' || uum.uum_last_name FullName,uua.uua_first_name||' ' || uua.uua_last_name "+
					"  ,uum.uum_email from ums_prov_dtls upd,ums_role_mst urm,ums_appl_mst uam,ums_request_mst urqm,"+
					"ums_prov_log upl,ums_usr_mst uum,ums_umsusr_access uua where "+
					"urqm.urqm_request_id=:requestId and   upd.upd_appl_id = uam.uam_appl_id and   "+
					"upd.upd_role_id = urm.urm_role_id and   upd.upd_prov_id =upl.upl_prov_id and   upd.upd_request_id = urqm.urqm_request_id and  "+
					" uum.uum_usr_id = upd.upd_usr_id and   uua.uua_usr_id = upd.upd_created_by and uum.uum_end_date is null  "+
					"and uum.uum_status = 'E' and urm.urm_status='E'");
			userQuery.setParameter("requestId", requestId);
			List appRoleList=userQuery.getResultList();
			Iterator iter=appRoleList.iterator();

			LOGGER.info("Mail details list : " + appRoleList.toString());

			Map<String,List<List<String>>> roleDataMap = new HashMap<String,List<List<String>>>();

			List<String> tRoleData;

			while(iter.hasNext()){
				Object[] rolesData= (Object[]) iter.next();
				tRoleData = new ArrayList<String>();
				tRoleData.add(rolesData[0].toString());
				tRoleData.add(rolesData[1].toString());
				tRoleData.add(rolesData[2].toString());
				tRoleData.add(rolesData[3].toString());
				tRoleData.add(rolesData[4].toString());
				tRoleData.add(rolesData[5].toString());
				tRoleData.add(rolesData[6].toString());
				tRoleData.add(rolesData[7].toString());
				tRoleData.add(rolesData[8].toString());
				tRoleData.add(rolesData[9].toString());
				tRoleData.add(rolesData[10].toString());
				tRoleData.add(rolesData[11].toString());
				tRoleData.add(rolesData[12].toString());

				if(roleDataMap.containsKey(rolesData[0])){
					roleDataMap.get(rolesData[0].toString()).add(tRoleData);
				}else{
					List<List<String>> userRolesList =  new ArrayList<List<String>>();
					userRolesList.add(tRoleData);
					roleDataMap.put(rolesData[0].toString(),userRolesList);
				}
			}


			LOGGER.info("User Role Data Map " + roleDataMap.toString());

			StringBuilder sb=new StringBuilder();
			int i=0;
			String toId;


			for(String user : roleDataMap.keySet()){
				sb.append("<p>Your status for change in role & resposibility is as below:</p>\n");
				sb.append("<p>SR No:"+user+" "+roleDataMap.get(user).get(0).get(10)).append("</p>\n");
				sb.append("<p>Request ID: "+roleDataMap.get(user).get(0).get(1)).append("</p>\n")
				.append("<p>Requested By: "+roleDataMap.get(user).get(0).get(3) +" "+roleDataMap.get(user).get(0).get(11)).append("</p>\n")
				.append("<p>Request Reason: "+roleDataMap.get(user).get(0).get(2)+"</p>\n");

				for(List<String> rolesData : roleDataMap.get(user)){
					sb.append("<p>Application Name: "+rolesData.get(4)+"</p>\n").
					append("<p>Requested Role Name: "+rolesData.get(5)+"</p>\n").
					append("<p>Action: "+rolesData.get(6)+"\n").append("Status: "+rolesData.get(7)+"</p>\n").
					append("<p>Status Remark: "+rolesData.get(9)+"</p>\n");
				}
				sb.append("<p>Above request was initiated by SR No. "+roleDataMap.get(user).get(0).get(3)+" "+roleDataMap.get(user).get(0).get(11)+"</p>");

				LOGGER.info("Mail Content : " + sb.toString());

				toId=roleDataMap.get(user).get(0).get(12).toString();

				String fromId=UtilProperties.getFromId();
				String subject=UtilProperties.getSubjectForUpdateRole();
				sendMail(toId,sb.toString(),fromId,subject);
				sb.setLength(0);
			}



		}
	} catch (Exception ex) {
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
}
}