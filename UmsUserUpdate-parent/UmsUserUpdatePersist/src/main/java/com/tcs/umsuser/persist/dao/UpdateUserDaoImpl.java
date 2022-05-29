package com.tcs.umsuser.persist.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.tcs.umsuser.exception.cmo.ErrorVO;
import com.tcs.umsuser.exception.cmo.ValidationException;
import com.tcs.umsuser.persist.DBUtil;
import com.tcs.umsuser.persist.entities.UmsTempRec;
import com.tcs.umsuser.persist.entities.UmsUserAppMap;
import com.tcs.umsuser.persist.entities.UserMasterUpdate;
import com.tcs.umsuser.persist.query.Queries;
import com.tcs.umsuser.request.AcknowledgementRequestSO;
import com.tcs.umsuser.request.GetRoleRequestSO;
import com.tcs.umsuser.request.asbo.PendingUserTempDetail;
import com.tcs.umsuser.request.asbo.ProvDetailsRequestASBO;
import com.tcs.umsuser.request.asbo.UpdateJiraUserRequestSO;
import com.tcs.umsuser.request.asbo.UpdateUserRequestASBO;
import com.tcs.umsuser.request.asbo.UserDetailForOID;
import com.tcs.umsuser.response.asbo.ProvisionDetailsResponseASBO;
import com.tcs.umsuser.response.asbo.RoleUpdateResponseASBO;
import com.tcs.umsuser.util.UtilConstants;
import com.tcs.umsuser.util.UtilProperties;
import com.tcs.umsuser.util.ValidationUtil;

public class UpdateUserDaoImpl implements UpdateUserDao{
	  private static final Logger LOGGER = Logger.getLogger(UpdateUserDaoImpl.class);
	
	@Override
	public RoleUpdateResponseASBO updateUser(UpdateUserRequestASBO requestSO){
		LOGGER.info("Inside UpdateUserDaoImpl --------- ");
		RoleUpdateResponseASBO responseObject= new RoleUpdateResponseASBO();

			EntityManager entityManager=null;
			try {
			entityManager=DBUtil.getEntityManager();
			entityManager.getTransaction().begin();
			
			UmsTempRec tempRec=new UmsTempRec();
			tempRec.setUserId(requestSO.getUserId());
			tempRec.setTitle(requestSO.getTitle());
			tempRec.setFirstName(requestSO.getFirstName());
			tempRec.setMiddlename(requestSO.getMiddleName());
			tempRec.setLastName(requestSO.getLastName());
			tempRec.setBranchId(requestSO.getBranchId());
			tempRec.setGender(requestSO.getGender().equalsIgnoreCase("U")?"M":requestSO.getGender());
			tempRec.setDob(requestSO.getDob());
			tempRec.setAddr1(requestSO.getAddr1());
			tempRec.setAddr2(requestSO.getAddr2());
			tempRec.setAddr3(requestSO.getAddr3());
			tempRec.setCity(requestSO.getCity());
			tempRec.setState(requestSO.getState());
			tempRec.setCountry(requestSO.getCountry());
			tempRec.setPin(replaceCharacters(requestSO.getPin(),"[ ,.]","000000"));
			tempRec.setEmail(requestSO.getEmail());
			tempRec.setPhoneNo(replaceCharacters(requestSO.getPhoneNo(),"[ -/,.&+]","000000"));
			tempRec.setMobile(replaceCharacters(requestSO.getMobileNo(),"[ -/,.&+]","0000000000"));
			tempRec.setExtension(requestSO.getExtension());
			tempRec.setIpPhone(requestSO.getIpPhone());
			tempRec.setStartDate(requestSO.getStartDate());
			tempRec.setEndDate(requestSO.getEndDate());
			tempRec.setSupervisorId(replaceCharacters(requestSO.getSupervisorId(),"[ ]",null));
			tempRec.setDesignation(requestSO.getDesignation());
			
			tempRec.setCreatedBy(requestSO.getCreatedBy()==null?"HRMS": requestSO.getCreatedBy());
			
			tempRec.setCreatedDate(new Date());
			tempRec.setModifiedBy(requestSO.getModifiedBy()==null?"HRMS": requestSO.getModifiedBy());
			tempRec.setModifiedDate(requestSO.getModifiedDate());
			tempRec.setStatus(requestSO.getStatus());
			tempRec.setRecStatus("U");
			tempRec.setRecCreatedBy(UtilConstants.App_HRMS);
			tempRec.setRecCreatedDate(new Date());
			tempRec.setRecModifiedBy(UtilConstants.App_HRMS);
			tempRec.setRecModifiedDate(new Date());
			tempRec.setFlagAction(requestSO.getFlagAction());

			entityManager.persist(tempRec);
			entityManager.getTransaction().commit();
			
			responseObject.setRequestId(tempRec.getRecordId());
			responseObject.setStatusCode(UtilConstants.Success_code);
	        responseObject.setStatusMessage(UtilConstants.Success_message);
			
			LOGGER.info("User Detail  :  " + tempRec.toString());

			} catch (Exception e) {
				   responseObject.setStatusCode(UtilConstants.Fail_code);
		           responseObject.setStatusMessage(UtilConstants.Fail_message);
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
			return responseObject;
		}
	
	public String replaceCharacters(String value,String regex,String defaultValue)
	{
	    if(null!=value)
	    {
	        value=value.replaceAll(regex, "").trim();
	        value=value.isEmpty()?defaultValue:value;
	    }else
	    {
	        value=defaultValue;
	    }
	    return value;
	}
	
	
	@Override
	public List<PendingUserTempDetail> updateTempToMst(long recordId){
		LOGGER.info("inside temp to master update method ---------------------");
		List<PendingUserTempDetail> pendingList = new ArrayList<PendingUserTempDetail>();
		EntityManager entityManager=null;
		Query query = null;
		try {
		entityManager=DBUtil.getEntityManager();
		
		if(Long.valueOf(recordId)!=null){
			query =entityManager.createNativeQuery(Queries.getPendingUserTempDetails);
			query.setParameter("recordId", recordId);
		}else{
			query =entityManager.createNativeQuery(Queries.getPendingUserTempDetailsForBatch);
		}
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		LOGGER.info("DB Response List Size : " + resultList.size());
		if(!resultList.isEmpty()){
			DateFormat inputFormat = new SimpleDateFormat(UtilConstants.Update_datePattern);
			
			for(Object[] obj : resultList){
				PendingUserTempDetail pendingUserTempDetail = new PendingUserTempDetail();
				
				pendingUserTempDetail.setUserId(checkEmptyString(obj[0]));
				pendingUserTempDetail.setTitle(checkEmptyString(obj[1]));
				pendingUserTempDetail.setFirstName(checkEmptyString(obj[2]));
				pendingUserTempDetail.setMiddlename(checkEmptyString(obj[3]));
				pendingUserTempDetail.setLastName(checkEmptyString(obj[4]));
				pendingUserTempDetail.setBranchId(checkEmptyString(obj[5]));
				pendingUserTempDetail.setGender(checkEmptyString(obj[6]));
				if(obj[7]!=null){
					Date date = inputFormat.parse(obj[7].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setDob(dd);
				}
				pendingUserTempDetail.setAddr1(checkEmptyString(obj[8]));
				pendingUserTempDetail.setAddr2(checkEmptyString(obj[9]));
				pendingUserTempDetail.setAddr3(checkEmptyString(obj[10]));
				pendingUserTempDetail.setCity(checkEmptyString(obj[11]));
				pendingUserTempDetail.setState(checkEmptyString(obj[12]));
				pendingUserTempDetail.setCountry(checkEmptyString(obj[13]));
				pendingUserTempDetail.setPin(checkEmptyString(obj[14]));
				pendingUserTempDetail.setEmail(checkEmptyString(obj[15]));
				pendingUserTempDetail.setPhoneNo(checkEmptyString(obj[16]));
				pendingUserTempDetail.setMobile(checkEmptyString(obj[17]));
				pendingUserTempDetail.setExtension(checkEmptyString(obj[18]));
				pendingUserTempDetail.setIpPhone(checkEmptyString(obj[19]));
				if(obj[20]!=null){
					Date date = inputFormat.parse(obj[20].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setStartDate(dd);
				}
				if(obj[21]!=null){
					Date date = inputFormat.parse(obj[21].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setEndDate(dd);
				}
				pendingUserTempDetail.setSupervisorId(checkEmptyString(obj[22]));
				pendingUserTempDetail.setDesignation(checkEmptyString(obj[23]));
				pendingUserTempDetail.setCreatedBy(checkEmptyString(obj[24]));
				if(obj[25]!=null){
					Date date = inputFormat.parse(obj[25].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setCreatedDate(dd);
				}
				pendingUserTempDetail.setModifiedBy(checkEmptyString(obj[26]));
				if(obj[27]!=null){
					Date date = inputFormat.parse(obj[27].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setModifiedDate(dd);
				}
				pendingUserTempDetail.setStatus(checkEmptyString(obj[28]));
				if(obj[29]!=null){
					Date date = inputFormat.parse(obj[29].toString());
					java.sql.Date dd= new java.sql.Date(date.getTime());
					pendingUserTempDetail.setEffectiveStartDate(dd);
				}
				pendingUserTempDetail.setFlagAction(checkEmptyString(obj[30]));
				pendingUserTempDetail.setRecordId(Long.parseLong(obj[31].toString()));
				pendingList.add(pendingUserTempDetail);
			}
			
			
		}else{
			LOGGER.info("No Data Found ------------");
		}
		
		}catch(Exception e){
			LOGGER.error("Error : " +e);
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
		LOGGER.info("Pending List Size : " + pendingList.size());
		return pendingList;
	}
	
	public String checkEmptyString(Object value){	
		return value!=null?value.toString():null;
	}
	

	@Override
	public boolean deleteUserMaster(ProvDetailsRequestASBO provDetailsRequestASBO , ProvisionDetailsResponseASBO provisionDetailsResponseASBO,UpdateJiraUserRequestSO jiraUserRequestSO) throws SQLException{
		
		boolean statusFlag=true;
		LOGGER.info("Updating UMS USR ROLE MAP  -------------");
		LOGGER.info("Modified_by = " + provDetailsRequestASBO.getProvModifiedBy() +"  ---   User Id = "+ provDetailsRequestASBO.getProvUserId());
		
		Query query = null;
		int updateResponse = 0;
		EntityManager entityManager=null;
		try {
			entityManager=DBUtil.getEntityManager();
			query = entityManager.createNativeQuery(Queries.updateUmsUsrRole);
			query.setParameter("modifiedBy", provDetailsRequestASBO.getProvModifiedBy());
			query.setParameter("userId", provDetailsRequestASBO.getProvUserId());
			
			entityManager.getTransaction().begin();
			updateResponse = query.executeUpdate();
			entityManager.getTransaction().commit();
			
			if(updateResponse>0){
				LOGGER.info("UMS USR ROLE MAP updated successfully -------   " + updateResponse);
			}else{
				LOGGER.info("Nothing to update for UMS USR ROLE MAP---- !!!!!");
			}
			updateResponse=0;
			LOGGER.info("Updating UMS USR APP MAP  -------------");
			query = entityManager.createNativeQuery(Queries.updateUmsUsrAppl);
			query.setParameter("modifiedBy", provDetailsRequestASBO.getProvModifiedBy());
			query.setParameter("userId", provDetailsRequestASBO.getProvUserId());
			
			entityManager.getTransaction().begin();
			updateResponse = query.executeUpdate();
			entityManager.getTransaction().commit();
			
			if(updateResponse>0){
				LOGGER.info("UMS USR APP MAP updated successfully -------   " + updateResponse);
			}else{
				LOGGER.info("Nothing to update for UMS USR APP MAP---- !!!!!");
			}
			updateResponse=0;
			LOGGER.info("Updating UMS USR MST  -------------");
			query = entityManager.createNativeQuery(Queries.updateUmsUsrMst);
			query.setParameter("modifiedBy", provDetailsRequestASBO.getProvModifiedBy());
			query.setParameter("userId", provDetailsRequestASBO.getProvUserId());
			
			entityManager.getTransaction().begin();
			updateResponse = query.executeUpdate();
			entityManager.getTransaction().commit();
			
			if(updateResponse>0){
				LOGGER.info("UMS USR MST updated successfully -------   " + updateResponse);				
				pushToOID(null, provisionDetailsResponseASBO.getProvId());
				callJIRAForUserUpdate(jiraUserRequestSO);
				
			}else{
				LOGGER.info("Nothing to update for UMS USR MST---- !!!!!");
			}
			
		}catch(TransactionRequiredException tre){
			statusFlag=false;
			LOGGER.info("Error Transaction Required : "+ tre);
			LOGGER.error(tre.getStackTrace(),tre);
			return statusFlag;
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
		return statusFlag;
	}

	@Override
	public int checkUserExist(String userId) throws SQLException{
		LOGGER.info("Checking for User ID  :  "+ userId);
		EntityManager entityManager=null;
		int userCount=0;
		try {
			entityManager= DBUtil.getEntityManager();
			Query userSearch=entityManager.createNativeQuery(Queries.checkUserExist);
			userSearch.setParameter("userId", userId);
			
			userCount=((Number)userSearch.getSingleResult()).intValue();
						
			if(userCount>0){
				LOGGER.info("User available in UUM -------   " + userCount);
			}else{
				LOGGER.info("User NOT present in UUM---- !!!!!");
			}
			
			
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
		return userCount;
	}

	@Override
	public boolean insertNewUser(PendingUserTempDetail userDetail)  {
		boolean statusFlag=true;
		LOGGER.info("Inside insert new User method-----------");
		EntityManager entityManager=null;
	
		try {
			entityManager= DBUtil.getEntityManager();
			
			
			UserMasterUpdate insertNewUser = new UserMasterUpdate();
			
			try{
				if(userDetail.getUserId()!=null&&!userDetail.getUserId().isEmpty()){		
					insertNewUser.setUserId(userDetail.getUserId());
					insertNewUser.setLoginId(userDetail.getUserId());
				}else{
					LOGGER.error("Invalid User ID: "+userDetail.getUserId());
					throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"UserID ");
				}
				if(userDetail.getTitle()!=null&&!userDetail.getTitle().isEmpty()){		
					insertNewUser.setTitle(userDetail.getTitle());
				}else{
					insertNewUser.setTitle("Mr");
				}
				
				if(userDetail.getFirstName()!=null&&!userDetail.getFirstName().isEmpty()){		
					insertNewUser.setFirstName(userDetail.getFirstName());
				}else{
					insertNewUser.setFirstName(" ");
				}
				if(userDetail.getLastName()!=null&&!userDetail.getLastName().isEmpty()){		
					insertNewUser.setLastName(userDetail.getLastName());
				}else{
					insertNewUser.setLastName(" ");
				}
				if(userDetail.getGender()!=null&&!userDetail.getGender().isEmpty()){		
					insertNewUser.setGender(userDetail.getGender());
		    	}else{
		    		insertNewUser.setGender("M");
		    	}
				if(userDetail.getAddr1()!=null&&!userDetail.getAddr1().isEmpty()){		
					insertNewUser.setAddr1(userDetail.getAddr1());
		    	}else{
		    		insertNewUser.setAddr1(" ");
		    	}
				
				if(userDetail.getBranchId()!=null&&!userDetail.getBranchId().isEmpty()){		
					insertNewUser.setBranchId(userDetail.getBranchId());
		    	}else{
		    		insertNewUser.setBranchId(" ");
		    	}
				
				if(userDetail.getDesignation()!=null&&!userDetail.getDesignation().isEmpty()){		
					insertNewUser.setDesignation(userDetail.getDesignation());
		    	}else{
		    		insertNewUser.setDesignation(" ");
		    	}
				
				if(userDetail.getModifiedBy()!=null&&!userDetail.getModifiedBy().isEmpty()){		
					insertNewUser.setModifiedBy(userDetail.getModifiedBy());
		    	}else{
		    		insertNewUser.setModifiedBy("UMS");
		    	}

				if(userDetail.getDob()!=null){
					insertNewUser.setDob(new java.util.Date(userDetail.getDob().getTime()));
				}else{
					insertNewUser.setDob(null);
				}
				if(userDetail.getStartDate()!=null){
					insertNewUser.setStartDate(new java.util.Date(userDetail.getStartDate().getTime()));
				}else{
					insertNewUser.setStartDate(new Date());
				}
				if(userDetail.getEndDate()!=null){
					insertNewUser.setEndDate(new java.util.Date(userDetail.getEndDate().getTime()));
				}else{
					insertNewUser.setEndDate(null);
				}
				insertNewUser.setMiddleName(userDetail.getMiddlename()==null?null:userDetail.getMiddlename());
				insertNewUser.setAddr2(userDetail.getAddr2()==null?null:userDetail.getAddr2());
				insertNewUser.setAddr3(userDetail.getAddr3()==null?null:userDetail.getAddr3());
				insertNewUser.setCity(userDetail.getCity()==null?null:userDetail.getCity());
				insertNewUser.setState(userDetail.getState()==null?null:userDetail.getState());
				insertNewUser.setCountry(userDetail.getCountry()==null?null:userDetail.getCountry());
				insertNewUser.setPin(userDetail.getPin()==null?null:userDetail.getPin());
				insertNewUser.setEmail(userDetail.getEmail()==null?null:userDetail.getEmail());
				insertNewUser.setPhoneNo(userDetail.getPhoneNo()==null?null:userDetail.getPhoneNo());
				insertNewUser.setMobile(userDetail.getMobile()==null?null:userDetail.getMobile());
				insertNewUser.setExtension(userDetail.getExtension()==null?null:userDetail.getExtension());
				insertNewUser.setIpPhone(userDetail.getIpPhone()==null?null:userDetail.getIpPhone());
				insertNewUser.setCreatedBy(userDetail.getCreatedBy()==null?" ":userDetail.getCreatedBy());
				insertNewUser.setSupervisiorId(userDetail.getSupervisorId()==null?null:userDetail.getSupervisorId());
				insertNewUser.setUserTypeId("7");
				insertNewUser.setStatus("E");
				insertNewUser.setCreatedDate(new Date());
				insertNewUser.setModifiedDate(new Date());
				insertNewUser.setPwdChangeDate(new Date());
				
				Calendar calendar=new GregorianCalendar();
				calendar.add(Calendar.DATE, 30);
				insertNewUser.setPwdExpiryDate(calendar.getTime());
				
				
				entityManager.getTransaction().begin();
				entityManager.persist(insertNewUser);
				entityManager.getTransaction().commit();
		
			
				LOGGER.info("User inserted successfully in UUM -------   " );
				long umsUsrAppMapId = insertInPortalDB(userDetail);
				if(umsUsrAppMapId != 0L){
					UmsUserAppMap updateUserAppMap = entityManager.find(UmsUserAppMap.class, umsUsrAppMapId);
	
					if(updateUserAppMap != null){
						LOGGER.info("Updating for : ------    "+ umsUsrAppMapId);
						entityManager.getTransaction().begin();
						updateUserAppMap.setApplApprovedStatus("Y");
						entityManager.merge(updateUserAppMap);
						entityManager.getTransaction().commit();
						entityManager.clear();
					}
				}
				LOGGER.info("Inserting record in prov details table  ");
				ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();
				ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
				ProvisionDetailsResponseASBO provisionDetailsResponseASBO = new ProvisionDetailsResponseASBO();
				provDetailsRequestASBO.setProvUserId(userDetail.getUserId());
				provDetailsRequestASBO.setProvApplicationId(UtilConstants.App_PORTALDB);
				provDetailsRequestASBO.setProvAction(UtilConstants.Update_user);
				provDetailsRequestASBO.setProvBranchId(userDetail.getBranchId());
				provDetailsRequestASBO.setProvModifiedBy(userDetail.getModifiedBy());
				
				provisionDetailsResponseASBO = provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
				
				LOGGER.info("Prov Response : "+ provisionDetailsResponseASBO.toString() );
			
				
				//Add users default roles in UMS CR 3536_F implementation umcomment below code when CR will go live
				/*SimpleDateFormat iso_8601_formatter = new SimpleDateFormat("yyyy-MM-dd");
				 List<String> removedBranches=new ArrayList();
				GetRoleRequestSO getRoleRequestSO=new GetRoleRequestSO();
				List <UpdateRoleRequestSO> requestSOs=new ArrayList<>();
				UpdateRoleRequestSO roles=new UpdateRoleRequestSO();
				getRoleRequestSO.setBranchId(userDetail.getBranchId());
				getRoleRequestSO.setUserId(userDetail.getUserId());
				getRoleRequestSO.setReason("Roles assigned at New User Creation");
				getRoleRequestSO.setRequestBy(userDetail.getCreatedBy());
				getRoleRequestSO.setRemovedBranches(removedBranches);
				roles=new UpdateRoleRequestSO();
				roles.setAppId(UtilConstants.App_OID);
				roles.setRoleId(Integer.parseInt(UtilConstants.OID_HRMS));
				roles.setOfficeCode(userDetail.getBranchId());
				roles.setStartDate(iso_8601_formatter.format(new Date()));
				roles.setEndDate(null);
				requestSOs.add(roles);
				
				roles=new UpdateRoleRequestSO();
                roles.setAppId(UtilConstants.App_OID);
                roles.setRoleId(Integer.parseInt(UtilConstants.OID_JIRA));
                roles.setOfficeCode(userDetail.getBranchId());
                roles.setStartDate(iso_8601_formatter.format(new Date()));
                roles.setEndDate(null);
                requestSOs.add(roles);
                
				roles=new UpdateRoleRequestSO();
				roles.setAppId(UtilConstants.App_HRMS);
                roles.setRoleId(Integer.parseInt(UtilConstants.HRMS_EOPP));
                roles.setOfficeCode(userDetail.getBranchId());
                roles.setStartDate(iso_8601_formatter.format(new Date()));
                roles.setEndDate(null);
                requestSOs.add(roles);
                roles=new UpdateRoleRequestSO();
                roles.setAppId(UtilConstants.App_HRMS);
                roles.setRoleId(Integer.parseInt(UtilConstants.HRMS_PTPT100));
                roles.setOfficeCode(userDetail.getBranchId());
                roles.setStartDate(iso_8601_formatter.format(new Date()));
                roles.setEndDate(null);
                requestSOs.add(roles);
                roles=new UpdateRoleRequestSO();
                roles.setAppId(UtilConstants.App_HRMS);
                roles.setRoleId(Integer.parseInt(UtilConstants.HRMS_NIA_EMPLOYEE));
                roles.setOfficeCode(userDetail.getBranchId());
                roles.setStartDate(iso_8601_formatter.format(new Date()));
                roles.setEndDate(null);
                requestSOs.add(roles);
                roles=new UpdateRoleRequestSO();
                roles.setAppId(UtilConstants.App_JIRA);
                roles.setRoleId(Integer.parseInt(UtilConstants.JIRA_UTKARSH_ENDUSER));
                roles.setOfficeCode(userDetail.getBranchId());
                roles.setStartDate(iso_8601_formatter.format(new Date()));
                roles.setEndDate(null);
                requestSOs.add(roles);
                
                getRoleRequestSO.setUserRoles(requestSOs);
                
                assignDefaultRoles(getRoleRequestSO);*/
				
				
		}catch(ValidationException ve){
			statusFlag=false;
					LOGGER.info("Update Failed for user ID :"+ userDetail.getUserId());
					LOGGER.info("Error :"+ ve);
					LOGGER.error(ve.getStackTrace(),ve);
					ErrorVO errorVO = new ErrorVO();
					errorVO.setErrorCode(ve.getErrorCode());
					errorVO.setErrorMessage("Invalid value for the field : " + ve.getField());
					return statusFlag;
		}
				
				
				
			
		}catch(Exception e){
			statusFlag=false;
			LOGGER.error(e.getStackTrace(),e);
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage(UtilConstants.Error_message);
			return statusFlag;
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
		return statusFlag;
	}

	private long insertInPortalDB(PendingUserTempDetail userDetail) throws SQLException{
		LOGGER.info("Now inserting user detail in PORTALDB ---------");
		long appMapId=0L;
		EntityManager entityManager=null;
		Query appCountQuery =null;
		try {
			entityManager= DBUtil.getEntityManager();
			
		    appCountQuery=entityManager.createNativeQuery(Queries.insertInPortalDB);
			appCountQuery.setParameter("userId", userDetail.getUserId());
		    
			int appCount =((Number)appCountQuery.getSingleResult()).intValue();

			LOGGER.info("PORTALDB map status : --------   "+ appCount);
			
			if(appCount==0){
				
				entityManager.getTransaction().begin();
				
				UmsUserAppMap umsUserAppMap = new UmsUserAppMap();
				umsUserAppMap.setUserId(userDetail.getUserId());
				umsUserAppMap.setApplicationId(UtilConstants.App_PORTALDB);
				umsUserAppMap.setStartDate(new Date());
				umsUserAppMap.setCreatedBy(userDetail.getModifiedBy());
				umsUserAppMap.setModifiedBy(userDetail.getModifiedBy());
				LOGGER.info("Update request for ------------- : " + umsUserAppMap);
				entityManager.persist(umsUserAppMap);
				entityManager.getTransaction().commit();
				
				appMapId = umsUserAppMap.getAppMapId();
				LOGGER.info("App map ID : ----------- " + appMapId);
			}
			
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
		
		return appMapId;
	}

	@Override
	public boolean updateUserDetails(PendingUserTempDetail newUserDetail){
		LOGGER.info("Inside Update User Details--------------  "+ newUserDetail);
		boolean statusFlag=true;
	
		boolean title_flg = false , first_name_flg= false, middle_name_flg =false,last_name_flg=false ,gender_flg=false ,
				addr1_flg=false,addr2_flg=false ,addr3_flg=false, city_flg=false, state_flg=false,country_flg=false ,dob_flg=false ,
				pin_flg=false , email_flg=false,phone_no_flg=false,mobile_flg=false,extension_flg=false, ip_phone_flg=false , 
				branch_id_flag=false, supervisor_id_flag=false , designation_flag=false,
				changeFlag=false;

		EntityManager entityManager=null;
		
		

		try {
			entityManager= DBUtil.getEntityManager();
			UserMasterUpdate existingUserDetails = new UserMasterUpdate();
			//Query userCheck = entityManager.createQuery(Queries.getExistingUserDetails);
			TypedQuery<UserMasterUpdate> userCheck = entityManager.createQuery(Queries.getExistingUserDetails, UserMasterUpdate.class);
			userCheck.setParameter("userId", ValidationUtil.validateString(newUserDetail.getUserId()));
			
			List<UserMasterUpdate> resultList = userCheck.getResultList();
			LOGGER.info("Response Size :" + resultList.size());
			if(!resultList.isEmpty()){

			existingUserDetails = resultList.get(0);

			if((existingUserDetails.getTitle()!=null)&&(newUserDetail.getTitle()!=null)){
				if(!existingUserDetails.getTitle().equalsIgnoreCase(newUserDetail.getTitle())){
				changeFlag=true; title_flg=true;
				LOGGER.info("Change Detected in  Title:  " + newUserDetail.getTitle());
				}
			}else if((existingUserDetails.getTitle()==null)&&(newUserDetail.getTitle()==null)){
			
			}else{
				changeFlag=true; title_flg=true;
				LOGGER.info("Change Detected in  Title:  " + newUserDetail.getTitle());
			}
			
			if((existingUserDetails.getFirstName()!=null)&&(newUserDetail.getFirstName()!=null)){
				if(!existingUserDetails.getFirstName().equalsIgnoreCase(newUserDetail.getFirstName())){
				changeFlag=true; first_name_flg=true;
				LOGGER.info("Change Detected in  FirstName:  " + newUserDetail.getFirstName());
				}
			}else if((existingUserDetails.getFirstName()==null)&&(newUserDetail.getFirstName()==null)){
			
			}else{
				changeFlag=true; first_name_flg=true;
				LOGGER.info("Change Detected in  FirstName:  " + newUserDetail.getFirstName());
			}

			if((existingUserDetails.getMiddleName()!=null)&&(newUserDetail.getMiddlename()!=null)){
				if(!existingUserDetails.getMiddleName().equalsIgnoreCase(newUserDetail.getMiddlename())){
				changeFlag=true; middle_name_flg=true;
				LOGGER.info("Change Detected in  MiddleName:  " + newUserDetail.getMiddlename());
				}
			}else if((existingUserDetails.getMiddleName()==null)&&(newUserDetail.getMiddlename()==null)){
			
			}else{
				changeFlag=true; middle_name_flg=true;
				LOGGER.info("Change Detected in  MiddleName:  " + newUserDetail.getMiddlename());
			}
			
			if((existingUserDetails.getLastName()!=null)&&(newUserDetail.getLastName()!=null)){
				if(!existingUserDetails.getLastName().equalsIgnoreCase(newUserDetail.getLastName())){
				changeFlag=true; last_name_flg=true;
				LOGGER.info("Change Detected in  LastName:  " + newUserDetail.getLastName());
				}
			}else if((existingUserDetails.getLastName()==null)&&(newUserDetail.getLastName()==null)){
			
			}else{
				changeFlag=true; last_name_flg=true;
				LOGGER.info("Change Detected in  LastName:  " + newUserDetail.getLastName());
			}

			if((existingUserDetails.getDob()!=null)&&(newUserDetail.getDob()!=null)){
				if(!existingUserDetails.getDob().toString().equalsIgnoreCase(newUserDetail.getDob().toString())){
				changeFlag=true; dob_flg=true;
				LOGGER.info("Change Detected in  Dob:  " + newUserDetail.getDob());
				}
			}else if((existingUserDetails.getDob()==null)&&(newUserDetail.getDob()==null)){
			
			}else{
				changeFlag=true; dob_flg=true;
				LOGGER.info("Change Detected in  Dob:  " + newUserDetail.getDob());
			}

			if((existingUserDetails.getGender()!=null)&&(newUserDetail.getGender()!=null)){
				if(!existingUserDetails.getGender().equalsIgnoreCase(newUserDetail.getGender())){
				changeFlag=true; gender_flg=true;
				LOGGER.info("Change Detected in  Gender:  " + newUserDetail.getGender());
				}
			}else if((existingUserDetails.getGender()==null)&&(newUserDetail.getGender()==null)){
			
			}else{
				changeFlag=true; gender_flg=true;
				LOGGER.info("Change Detected in  Gender:  " + newUserDetail.getGender());
			}
			
			if((existingUserDetails.getAddr1()!=null)&&(newUserDetail.getAddr1()!=null)){
				if(!existingUserDetails.getAddr1().equalsIgnoreCase(newUserDetail.getAddr1())){
				changeFlag=true; addr1_flg=true;
				LOGGER.info("Change Detected in  Add1:  " + newUserDetail.getAddr1());
				}
			}else if((existingUserDetails.getAddr1()==null)&&(newUserDetail.getAddr1()==null)){
			
			}else{
				changeFlag=true; addr1_flg=true;
				LOGGER.info("Change Detected in  Add1:  " + newUserDetail.getAddr1());
			}
			
			if((existingUserDetails.getAddr2()!=null)&&(newUserDetail.getAddr2()!=null)){
				if(!existingUserDetails.getAddr2().equalsIgnoreCase(newUserDetail.getAddr2())){
				changeFlag=true; addr2_flg=true;
				LOGGER.info("Change Detected in  Add2 :  " + newUserDetail.getAddr2());
				}
			}else if((existingUserDetails.getAddr2()==null)&&(newUserDetail.getAddr2()==null)){
			
			}else{
				changeFlag=true; addr2_flg=true;
				LOGGER.info("Change Detected in  Add2 :  " + newUserDetail.getAddr2());
			}
			
			if((existingUserDetails.getAddr3()!=null)&&(newUserDetail.getAddr3()!=null)){
				if(!existingUserDetails.getAddr3().equalsIgnoreCase(newUserDetail.getAddr3())){
				changeFlag=true; addr3_flg=true;
				LOGGER.info("Change Detected in  Add3:  " + newUserDetail.getAddr3());
				}
			}else if((existingUserDetails.getAddr3()==null)&&(newUserDetail.getAddr3()==null)){
			
			}else{
				changeFlag=true; addr3_flg=true;
				LOGGER.info("Change Detected in  Add3:  " + newUserDetail.getAddr3());
			}
			
			if((existingUserDetails.getCity()!=null)&&(newUserDetail.getCity()!=null)){
				if(!existingUserDetails.getCity().equalsIgnoreCase(newUserDetail.getCity())){
				changeFlag=true; city_flg=true;
				LOGGER.info("Change Detected in  City:  " + newUserDetail.getCity());
				}
			}else if((existingUserDetails.getCity()==null)&&(newUserDetail.getCity()==null)){
			
			}else{
				changeFlag=true; city_flg=true;
				LOGGER.info("Change Detected in  City:  " + newUserDetail.getCity());
			}
			
			if((existingUserDetails.getState()!=null)&&(newUserDetail.getState()!=null)){
				if(!existingUserDetails.getState().equalsIgnoreCase(newUserDetail.getState())){
				changeFlag=true; state_flg=true;
				LOGGER.info("Change Detected in  State:  " + newUserDetail.getState());
				}
			}else if((existingUserDetails.getState()==null)&&(newUserDetail.getState()==null)){
			
			}else{
				changeFlag=true; state_flg=true;
				LOGGER.info("Change Detected in  State:  " + newUserDetail.getState());
			}
			
			if((existingUserDetails.getCountry()!=null)&&(newUserDetail.getCountry()!=null)){
				if(!existingUserDetails.getCountry().equalsIgnoreCase(newUserDetail.getCountry())){
				changeFlag=true; country_flg=true;
				LOGGER.info("Change Detected in  Country:  " + newUserDetail.getCountry());
				}
			}else if((existingUserDetails.getCountry()==null)&&(newUserDetail.getCountry()==null)){
			
			}else{
				changeFlag=true; country_flg=true;
				LOGGER.info("Change Detected in  Country:  " + newUserDetail.getCountry());
			}
			
			if((existingUserDetails.getPin()!=null)&&(newUserDetail.getPin()!=null)){
				if(!existingUserDetails.getPin().equalsIgnoreCase(newUserDetail.getPin())){
				changeFlag=true; pin_flg=true;
				LOGGER.info("Change Detected in  Pin:  " + newUserDetail.getPin());
				}
			}else if((existingUserDetails.getPin()==null)&&(newUserDetail.getPin()==null)){
			
			}else{
				changeFlag=true; pin_flg=true;
				LOGGER.info("Change Detected in  Pin:  " + newUserDetail.getPin());
			}
			
			if((existingUserDetails.getEmail()!=null)&&(newUserDetail.getEmail()!=null)){
				if(!existingUserDetails.getEmail().equalsIgnoreCase(newUserDetail.getEmail())){
				changeFlag=true; email_flg=true;
				LOGGER.info("Change Detected in  Email :  " + newUserDetail.getEmail());
				}
			}else if((existingUserDetails.getEmail()==null)&&(newUserDetail.getEmail()==null)){
			
			}else{
				changeFlag=true; email_flg=true;
				LOGGER.info("Change Detected in  Email :  " + newUserDetail.getEmail());
			}
			
			if((existingUserDetails.getPhoneNo()!=null)&&(newUserDetail.getPhoneNo()!=null)){
				if(!existingUserDetails.getPhoneNo().equalsIgnoreCase(newUserDetail.getPhoneNo())){
				changeFlag=true; phone_no_flg=true;
				LOGGER.info("Change Detected in  PhoneNo:  " + newUserDetail.getPhoneNo());
				}
			}else if((existingUserDetails.getPhoneNo()==null)&&(newUserDetail.getPhoneNo()==null)){
			
			}else{
				changeFlag=true; phone_no_flg=true;
				LOGGER.info("Change Detected in  PhoneNo:  " + newUserDetail.getPhoneNo());
			}
			
			if((existingUserDetails.getMobile()!=null)&&(newUserDetail.getMobile()!=null)){
				if(!existingUserDetails.getMobile().equalsIgnoreCase(newUserDetail.getMobile())){
				changeFlag=true; mobile_flg=true;
				LOGGER.info("Change Detected in  Mobile :  " + newUserDetail.getMobile());
				}
			}else if((existingUserDetails.getMobile()==null)&&(newUserDetail.getMobile()==null)){
			
			}else{
				changeFlag=true; mobile_flg=true;
				LOGGER.info("Change Detected in  Mobile :  " + newUserDetail.getMobile());
			}
			
			if((existingUserDetails.getExtension()!=null)&&(newUserDetail.getExtension()!=null)){
				if(!existingUserDetails.getExtension().equalsIgnoreCase(newUserDetail.getExtension())){
				changeFlag=true; extension_flg=true;
				LOGGER.info("Change Detected in  Extension :  " + newUserDetail.getExtension());
				}
			}else if((existingUserDetails.getExtension()==null)&&(newUserDetail.getExtension()==null)){
			
			}else{
				changeFlag=true; extension_flg=true;
				LOGGER.info("Change Detected in  Extension :  " + newUserDetail.getExtension());
			}
			
			if((existingUserDetails.getIpPhone()!=null)&&(newUserDetail.getIpPhone()!=null)){
				if(!existingUserDetails.getIpPhone().equalsIgnoreCase(newUserDetail.getIpPhone())){
				changeFlag=true; ip_phone_flg=true;
				LOGGER.info("Change Detected in  IpPhone :  " + newUserDetail.getIpPhone());
				}
			}else if((existingUserDetails.getIpPhone()==null)&&(newUserDetail.getIpPhone()==null)){
			
			}else{
				changeFlag=true; ip_phone_flg=true;
				LOGGER.info("Change Detected in  IpPhone :  " + newUserDetail.getIpPhone());
			}
			
			if((existingUserDetails.getBranchId()!=null)&&(newUserDetail.getBranchId()!=null)){
				if(!existingUserDetails.getBranchId().equalsIgnoreCase(newUserDetail.getBranchId())){
				changeFlag=true; branch_id_flag=true;
				LOGGER.info("Change Detected in  BranchId :  " + newUserDetail.getBranchId());
				}
			}else if((existingUserDetails.getBranchId()==null)&&(newUserDetail.getBranchId()==null)){
			
			}else{
				changeFlag=true; branch_id_flag=true;
				LOGGER.info("Change Detected in  BranchId :  " + newUserDetail.getBranchId());
			}
			
			if((existingUserDetails.getSupervisiorId()!=null)&&(newUserDetail.getSupervisorId()!=null)){
				if(!existingUserDetails.getSupervisiorId().equalsIgnoreCase(newUserDetail.getSupervisorId())){
				changeFlag=true; supervisor_id_flag=true;
				LOGGER.info("Change Detected in  SupervisorID:  " + newUserDetail.getSupervisorId());
				}
			}else if((existingUserDetails.getSupervisiorId()==null)&&(newUserDetail.getSupervisorId()==null)){
			
			}else{
				changeFlag=true; supervisor_id_flag=true;
				LOGGER.info("Change Detected in  SupervisorID:  " + newUserDetail.getSupervisorId());
			}
			
			if((existingUserDetails.getDesignation()!=null)&&(newUserDetail.getDesignation()!=null)){
				if(!existingUserDetails.getDesignation().equalsIgnoreCase(newUserDetail.getDesignation())){
				changeFlag=true; designation_flag=true;
				LOGGER.info("Change Detected in  Designation :  " + newUserDetail.getDesignation());
				}
			}else if((existingUserDetails.getDesignation()==null)&&(newUserDetail.getDesignation()==null)){
			
			}else{
				changeFlag=true; designation_flag=true;
				LOGGER.info("Change Detected in  Designation :  " + newUserDetail.getDesignation());
			}
			
			if((existingUserDetails.getModifiedBy()!=null)&&(newUserDetail.getModifiedBy()!=null)){
				if(!existingUserDetails.getModifiedBy().equalsIgnoreCase(newUserDetail.getModifiedBy())){
				changeFlag=true; 
				LOGGER.info("Change Detected in  ModifiedBy:  " + newUserDetail.getModifiedBy());
				}
			}else if((existingUserDetails.getModifiedBy()==null)&&(newUserDetail.getModifiedBy()==null)){
			
			}else{
				changeFlag=true;
				LOGGER.info("Change Detected in  ModifiedBy:  " + newUserDetail.getModifiedBy());
			}
		
			
			if(existingUserDetails.getStatus()!=null){
				if(existingUserDetails.getStatus().equalsIgnoreCase("D")){
				changeFlag=true; 
				LOGGER.info("Change Detected in  Status:  " + newUserDetail.getStatus());
				}
				}
			}

			if(changeFlag){

				LOGGER.info("Change Detected -----------  Now updating records for user :   " + newUserDetail.getUserId());
				entityManager.getTransaction().begin();
				
				try{
					if(newUserDetail.getUserId()!=null&&!newUserDetail.getUserId().isEmpty()){		
						existingUserDetails.setUserId(newUserDetail.getUserId());
					}else{
						LOGGER.error("Invalid User ID: "+newUserDetail.getUserId());
						throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"UserID ");
					}
					
					if(newUserDetail.getTitle()!=null&&!newUserDetail.getTitle().isEmpty()){		
						existingUserDetails.setTitle(newUserDetail.getTitle());
					}else{
						existingUserDetails.setTitle(" ");
					}
					
					if(newUserDetail.getFirstName()!=null&&!newUserDetail.getFirstName().isEmpty()){		
						existingUserDetails.setFirstName(newUserDetail.getFirstName());
					}else{
						existingUserDetails.setFirstName(" ");
						}
					if(newUserDetail.getLastName()!=null&&!newUserDetail.getLastName().isEmpty()){		
						existingUserDetails.setLastName(newUserDetail.getLastName());
					}else{
						existingUserDetails.setLastName(" ");
					}
					if(newUserDetail.getGender()!=null&&!newUserDetail.getGender().isEmpty()){		
						existingUserDetails.setGender(newUserDetail.getGender());
			    	}else{
			    		existingUserDetails.setGender(" ");
			    	}
					if(newUserDetail.getAddr1()!=null&&!newUserDetail.getAddr1().isEmpty()){		
						existingUserDetails.setAddr1(newUserDetail.getAddr1());
			    	}else{
			    		existingUserDetails.setAddr1(" ");
			    	}
					
					if(newUserDetail.getBranchId()!=null&&!newUserDetail.getBranchId().isEmpty()){		
						existingUserDetails.setBranchId(newUserDetail.getBranchId());
			    	}else{
			    		existingUserDetails.setBranchId(" ");
			    	}
					
					if(newUserDetail.getDesignation()!=null&&!newUserDetail.getDesignation().isEmpty()){		
						existingUserDetails.setDesignation(newUserDetail.getDesignation());
			    	}else{
			    		existingUserDetails.setDesignation(" ");
			    	}
					
					if(newUserDetail.getModifiedBy()!=null&&!newUserDetail.getModifiedBy().isEmpty()){		
						existingUserDetails.setModifiedBy(newUserDetail.getModifiedBy());
			    	}else{
			    		existingUserDetails.setModifiedBy("UMS");
			    	}
					
					if(newUserDetail.getDob()!=null){
						existingUserDetails.setDob(new java.util.Date(newUserDetail.getDob().getTime()));
					}else{
						existingUserDetails.setDob(null);
					}
					
					existingUserDetails.setMiddleName(newUserDetail.getMiddlename()==null?null:newUserDetail.getMiddlename());
					existingUserDetails.setAddr2(newUserDetail.getAddr2()==null?null:newUserDetail.getAddr2());
					existingUserDetails.setAddr3(newUserDetail.getAddr3()==null?null:newUserDetail.getAddr3());
					existingUserDetails.setCity(newUserDetail.getCity()==null?null:newUserDetail.getCity());
					existingUserDetails.setState(newUserDetail.getState()==null?null:newUserDetail.getState());
					existingUserDetails.setCountry(newUserDetail.getCountry()==null?null:newUserDetail.getCountry());
					existingUserDetails.setPin(newUserDetail.getPin()==null?"000000":newUserDetail.getPin());
					existingUserDetails.setEmail(newUserDetail.getEmail()==null?null:newUserDetail.getEmail());
					existingUserDetails.setPhoneNo(newUserDetail.getPhoneNo()==null?"000000":newUserDetail.getPhoneNo());
					existingUserDetails.setMobile(newUserDetail.getMobile()==null?"0000000000":newUserDetail.getMobile());
					existingUserDetails.setExtension(newUserDetail.getExtension()==null?null:newUserDetail.getExtension());
					existingUserDetails.setIpPhone(newUserDetail.getIpPhone()==null?null:newUserDetail.getIpPhone());
					existingUserDetails.setModifiedDate(new Date());
					existingUserDetails.setSupervisiorId(newUserDetail.getSupervisorId()==null?null:newUserDetail.getSupervisorId());
					existingUserDetails.setEndDate(null);
					existingUserDetails.setStatus("E");
				
					entityManager.merge(existingUserDetails);
					entityManager.getTransaction().commit();
					
				}catch(ValidationException ve){
					statusFlag =false;
					LOGGER.info("Update Failed for user ID :"+ newUserDetail.getUserId());
					LOGGER.info("Error :"+ ve);
					LOGGER.error(ve.getStackTrace(),ve);
					ErrorVO errorVO = new ErrorVO();
					errorVO.setErrorCode(ve.getErrorCode());
					errorVO.setErrorMessage("Invalid value for the field : " + ve.getField());
					return statusFlag;
				}
					
				LOGGER.info("Update successfull for user ID :" + newUserDetail.getUserId()); 
				
			}
			
			LOGGER.info("Now sending the request to all application to which User is associated");
			if(null==newUserDetail.getModifiedBy()&newUserDetail.getModifiedBy().isEmpty()){
				newUserDetail.setModifiedBy("UMS");
			}
			
			updateAllApplication(title_flg,first_name_flg,middle_name_flg,last_name_flg ,gender_flg,
					addr1_flg,addr2_flg ,addr3_flg, city_flg, state_flg,country_flg,dob_flg,
					pin_flg , email_flg,phone_no_flg,mobile_flg,extension_flg, ip_phone_flg, 
					branch_id_flag, supervisor_id_flag , designation_flag, changeFlag , newUserDetail);
			
			
			LOGGER.info("Branch Id change flag : ---    "+ branch_id_flag);
			if(branch_id_flag){
				LOGGER.info("Inside Revoke role method for OID and Portal");
				LOGGER.info("Calling OID service --------------");
				callOIDService(newUserDetail.getUserId());
				//JIRA call for All role revoke.
				UpdateJiraUserRequestSO jiraUserRequestSO=new UpdateJiraUserRequestSO();
				 jiraUserRequestSO.setAction("BU");
                 jiraUserRequestSO.setBranchId(newUserDetail.getBranchId());
                 //jiraUserRequestSO.setCity(userDetail.getCity());
                 jiraUserRequestSO.setMobileNo(newUserDetail.getMobile());
                 jiraUserRequestSO.setUserId(newUserDetail.getUserId());
                 jiraUserRequestSO.setName(newUserDetail.getFirstName()+" "+newUserDetail.getMiddlename()+" "+newUserDetail.getLastName());
				 callJIRAForUserUpdate(jiraUserRequestSO);
				
				LOGGER.info("Revoking all roles except HRMS from UMS map tables------------");
				
				RevokeAllRolesDao revokeAllRolesDao = new RevokeAllRolesDaoImpl();
				revokeAllRolesDao.revokeRoles(newUserDetail.getUserId(),newUserDetail.getModifiedBy());
			}
			
		}catch(Exception pe){
			statusFlag=false;
			LOGGER.error(pe.getStackTrace(),pe);
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("Error occured");
			return statusFlag;
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
		
		LOGGER.info("Returning from update user method----------");
		return statusFlag;	
	}

	
	private void updateAllApplication(boolean title_flg,
			boolean first_name_flg, boolean middle_name_flg,
			boolean last_name_flg, boolean gender_flg, boolean addr1_flg,
			boolean addr2_flg, boolean addr3_flg, boolean city_flg,
			boolean state_flg, boolean country_flg, boolean dob_flg,
			boolean pin_flg, boolean email_flg, boolean phone_no_flg,
			boolean mobile_flg, boolean extension_flg, boolean ip_phone_flg,
			boolean branch_id_flag, boolean supervisor_id_flag,
			boolean designation_flag, boolean changeFlag,
			PendingUserTempDetail newUserDetail) throws SQLException {
		
		ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();
		ProvisionDetailsResponseASBO provisionDetailsResponseASBO =new ProvisionDetailsResponseASBO();
		List<String> userApplicationList = provisionDetailsDao.getUserAppId(newUserDetail.getUserId());
		LOGGER.info("All application list size : "+ userApplicationList.size()); 
		
		if(!userApplicationList.isEmpty()){
			for(String appId : userApplicationList){
				try{
					if (appId.equalsIgnoreCase(UtilConstants.App_IIMS) && (title_flg || first_name_flg  || middle_name_flg || last_name_flg || addr1_flg || addr2_flg || addr3_flg || pin_flg || email_flg || phone_no_flg || branch_id_flag )){
						if(branch_id_flag){
						provisionDetailsResponseASBO=updateProvDetailsBranchChange(newUserDetail,appId);
						}else{
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
						}
					}else if(appId.equalsIgnoreCase(UtilConstants.App_PRINTING) && (first_name_flg || middle_name_flg || last_name_flg || branch_id_flag )){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_HELPDESK) && (first_name_flg  || middle_name_flg || last_name_flg || branch_id_flag || email_flg || phone_no_flg || branch_id_flag || mobile_flg || extension_flg || ip_phone_flg)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_CRM) && (first_name_flg  || middle_name_flg || last_name_flg || branch_id_flag || email_flg || phone_no_flg || branch_id_flag || mobile_flg || supervisor_id_flag || designation_flag)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_PORTALDB) && (first_name_flg || middle_name_flg || last_name_flg || dob_flg || addr1_flg || addr2_flg || addr3_flg || branch_id_flag || city_flg || country_flg || pin_flg || designation_flag || title_flg || email_flg || mobile_flg)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}
					else if(appId.equalsIgnoreCase(UtilConstants.App_OID) && (first_name_flg  || middle_name_flg || last_name_flg || branch_id_flag || email_flg || designation_flag)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
						
						if(provisionDetailsResponseASBO.getResultStatus().equalsIgnoreCase("S")){
							LOGGER.info("Calling OID :" + provisionDetailsResponseASBO.toString());
							pushToOID(null, provisionDetailsResponseASBO.getProvId());
						}
						
					}else if(appId.equalsIgnoreCase(UtilConstants.App_REPORTS) && (first_name_flg || last_name_flg || branch_id_flag)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_FINANCIALS) && branch_id_flag){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_PORTALCFT) && (first_name_flg || last_name_flg || branch_id_flag)){
						provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
					}else if(appId.equalsIgnoreCase(UtilConstants.App_WORKFLOW) && (first_name_flg || last_name_flg || branch_id_flag)){
                        provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,appId);
                        if(provisionDetailsResponseASBO.getResultStatus().equalsIgnoreCase("S")){
                            LOGGER.info("Calling Workflow Service :" + provisionDetailsResponseASBO.toString());
                            pushToWorkflow(newUserDetail.getUserId(), provisionDetailsResponseASBO.getProvId());
                        }
                    }
					
				}
				catch(Exception e){
					LOGGER.info("Error :  "+ e);
					LOGGER.error(e.getStackTrace(),e);
				}	
				LOGGER.info("Provision Response Details  :::   "+ appId + "    --     " +     provisionDetailsResponseASBO.toString());
			}
			//code added for JIRA Utkarsh end user update
			if((first_name_flg  || middle_name_flg || last_name_flg || branch_id_flag || mobile_flg || city_flg)){
                provisionDetailsResponseASBO=updateProvisionDetails(newUserDetail,UtilConstants.App_JIRA);
                if(provisionDetailsResponseASBO.getResultStatus().equalsIgnoreCase("S")){
                    LOGGER.info("Calling JIRA for Update :" + provisionDetailsResponseASBO.toString());
                   //JIRA Call for UPDATE issue
                    UpdateJiraUserRequestSO jiraUserRequestSO=new UpdateJiraUserRequestSO();
                    jiraUserRequestSO.setAction("UU");
                    jiraUserRequestSO.setBranchId(newUserDetail.getBranchId());
                    jiraUserRequestSO.setMobileNo(newUserDetail.getMobile());
                    jiraUserRequestSO.setUserId(newUserDetail.getUserId());
                    jiraUserRequestSO.setName(newUserDetail.getFirstName()+" "+newUserDetail.getMiddlename()+" "+newUserDetail.getLastName());
                    callJIRAForUserUpdate(jiraUserRequestSO);
                }
            }
		}
	}

	
	private ProvisionDetailsResponseASBO updateProvisionDetails(PendingUserTempDetail newUserDetail, String appId)throws SQLException {
		LOGGER.info("Updating for App ID :   ------  "+ appId);
		ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
		ProvisionDetailsResponseASBO provisionDetailsResponseASBO =new ProvisionDetailsResponseASBO();
		ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();
		
		provDetailsRequestASBO.setProvUserId(newUserDetail.getUserId());
		provDetailsRequestASBO.setProvApplicationId(appId);
		provDetailsRequestASBO.setProvRoleId(null);
		provDetailsRequestASBO.setProvAction(UtilConstants.Update_user);
		provDetailsRequestASBO.setProvBranchId(newUserDetail.getBranchId());
		provDetailsRequestASBO.setProvModifiedBy(newUserDetail.getModifiedBy());
		
		provisionDetailsResponseASBO=provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
		return provisionDetailsResponseASBO;
	}

	private ProvisionDetailsResponseASBO updateProvDetailsBranchChange(PendingUserTempDetail newUserDetail, String appId)throws SQLException {
		LOGGER.info("Updating for App ID :   ------  "+ appId);
		ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
		ProvisionDetailsResponseASBO provisionDetailsResponseASBO =new ProvisionDetailsResponseASBO();
		ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();
		
		provDetailsRequestASBO.setProvUserId(newUserDetail.getUserId());
		provDetailsRequestASBO.setProvApplicationId(appId);
		provDetailsRequestASBO.setProvRoleId(null);
		provDetailsRequestASBO.setProvAction(UtilConstants.Update_Branch);
		provDetailsRequestASBO.setProvBranchId(newUserDetail.getBranchId());
		provDetailsRequestASBO.setProvModifiedBy(newUserDetail.getModifiedBy());
		
		provisionDetailsResponseASBO=provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
		return provisionDetailsResponseASBO;
	}
	
	@Override
	public int updateUsrTempStatus(String action, long recordId)throws SQLException {
		LOGGER.info("Inside update temp status method--------");
		int updateUserTempStatus =0;
		EntityManager entityManager=null;
		Query query =null;
		
		try{
				entityManager= DBUtil.getEntityManager();
				entityManager.getTransaction().begin();
			    query=entityManager.createNativeQuery(Queries.updateUserTempStatus);
			    query.setParameter("action", action);
			    query.setParameter("temp_rec_id", recordId);
			    
			    updateUserTempStatus = query.executeUpdate();
			    entityManager.getTransaction().commit();
		}catch(Exception e){
			LOGGER.info("Error :  "+ e);
			LOGGER.error(e.getStackTrace(),e);
		}
		return updateUserTempStatus;
	}

	@Override
	public void updateErrorStatus(long recordId, int errorCode, String message, String action) {
		
		LOGGER.info("Inside error update msg--------");
		EntityManager entityManager=null;
		Query query =null;
		
		try{
			entityManager= DBUtil.getEntityManager();
			entityManager.getTransaction().begin();
		    query=entityManager.createNativeQuery(Queries.errorUpdate);
		    query.setParameter("temp_rec_id", recordId);
		    query.setParameter("sqlErrcode", errorCode);
		    query.setParameter("sqlErrMsg", message);
		    query.setParameter("action", action);
		    
		    int executeUpdate = query.executeUpdate();
		    if(executeUpdate>0)
		    	LOGGER.info("Status updated for SQL error---------");
		    
		    entityManager.getTransaction().commit();
		    
		}catch(Exception e){
			LOGGER.error("Error "+ e);
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
		
	}
	
	public void callOIDService(String userId){
		
		try {
			StringBuilder response = new StringBuilder();
			UserDetailForOID userDetailForOID = new UserDetailForOID();
			URL url = new URL(UtilProperties.oidUrl);
			HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            userDetailForOID.setUserID(userId);
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper
                    .writeValueAsString(userDetailForOID);
            LOGGER.info("JSON String convert:--------- " + requestJson);
            
            outputStreamWriter.write(requestJson);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            
            LOGGER.info("--Response from revoke all oid service------------"+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                   BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                   String s=null;
                   while ((s=br.readLine()) != null) {
                       response.append(s);
                   }   
                   LOGGER.info("ServiceResponse -----     "+response);
            }
            
		} catch (MalformedURLException e) {
			LOGGER.error("Error  : "+ e);
			LOGGER.error(e.getStackTrace(),e);
		} catch (IOException e) {
			LOGGER.error("Error  : "+ e); 
			LOGGER.error(e.getStackTrace(),e);
		}
		
		LOGGER.info("Returning from OID Service call------------");


	}
	
	public void pushToOID(Long requestId,Long provId)
    {
        URL url = null;
        HttpsURLConnection connection = null;
        try {
            url = new URL(UtilProperties.getOidAddUrl());
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
                   BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
                   String s=null;
                   while ((s=br.readLine()) != null) {
                       response.append(s);
                   }
                   
                   
                   
                   
                   LOGGER.info("-ServiceResponse -"+response);
                   JsonElement jelement = new JsonParser().parse(response.toString());
                   JsonObject jobject = jelement.getAsJsonObject();
                   
                   AcknowledgementRequestSO acknowledgeASBO = new AcknowledgementRequestSO();
                   acknowledgeASBO.setProvId(String.valueOf(provId));
                   acknowledgeASBO.setRemark(jobject.get("statusMessage").getAsString());
                   acknowledgeASBO.setSqlCode("");
                   acknowledgeASBO.setSqlMessage("");
                   acknowledgeASBO.setStatus(jobject.get("statusCode").getAsString());
                   sendAcknowledgeRequest(acknowledgeASBO);
               }
        } catch (Exception e) {
            LOGGER.info("Error :" + e);
            LOGGER.error(e.getStackTrace(),e);
           
        }
    }
	
	
	public void sendAcknowledgeRequest(AcknowledgementRequestSO osbRequestSO) {
        LOGGER.info("Inside Ack send  : "
                + osbRequestSO.toString());

        URL url = null;
        HttpsURLConnection connection = null;
        try {

            url = new URL(UtilProperties.getAckUrl());

            connection = (HttpsURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    connection.getOutputStream());
            ObjectMapper mapper = new ObjectMapper();
            String requestJson = mapper
                    .writeValueAsString(osbRequestSO);
            LOGGER.info("JSON String convert: " + requestJson);
            outputStreamWriter.write(requestJson);
            outputStreamWriter.flush();
            outputStreamWriter.close();
            LOGGER.info("--Response from ack service------------  "+connection.getResponseCode());
            if (connection.getResponseCode() == 200) {
                LOGGER.info("Response received successfully From Acknowledge Service -------------  ");
            } else {
                LOGGER.info("Connection failed to Acknowledge Service");
                throw new RuntimeException("Failed : HTTP error code : "
                        + connection.getResponseCode());
            }

        } catch (Exception e) {
            LOGGER.info("Error :" + e.getMessage());
            LOGGER.error(e.getStackTrace(),e);

        }
	}
public void callJIRAForUserUpdate(UpdateJiraUserRequestSO jiraUserRequestSO){
    try {
        StringBuilder response = new StringBuilder();
        URL url = new URL(UtilProperties.getJiraUpdateUrl());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper
                .writeValueAsString(jiraUserRequestSO);
        
        LOGGER.info("JSON String convert:--------- " + requestJson);
        
        outputStreamWriter.write(requestJson);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        
        LOGGER.info("--Response from revoke all oid service------------"+connection.getResponseCode());
        if (connection.getResponseCode() == 200) {
               BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
               String s=null;
               while ((s=br.readLine()) != null) {
                   response.append(s);
               }   
               LOGGER.info("ServiceResponse -----     "+response);
        }
        
    } catch (MalformedURLException e) {
        LOGGER.error("Error  : "+ e);
        LOGGER.error(e.getStackTrace(),e);
    } catch (IOException e) {
        LOGGER.error("Error  : "+ e); 
        LOGGER.error(e.getStackTrace(),e);
    }
    
    LOGGER.info("Returning from OID Service call------------");

}

public void assignDefaultRoles(GetRoleRequestSO getRoleRequestSO){
    try {
        StringBuilder response = new StringBuilder();
        URL url = new URL(UtilProperties.getRoleUpdateUrl());
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper
                .writeValueAsString(getRoleRequestSO);
        
        LOGGER.info("JSON String convert:--------- " + requestJson);
        
        outputStreamWriter.write(requestJson);
        outputStreamWriter.flush();
        outputStreamWriter.close();
        
        LOGGER.info("--Response Add roles service------------"+connection.getResponseCode());
        if (connection.getResponseCode() == 200) {
               BufferedReader br = new BufferedReader(new InputStreamReader((connection.getInputStream())));               
               String s=null;
               while ((s=br.readLine()) != null) {
                   response.append(s);
               }   
               LOGGER.info("ServiceResponse -----     "+response);
        }
        
    } catch (MalformedURLException e) {
        LOGGER.error("Error  : "+ e);
        LOGGER.error(e.getStackTrace(),e);
    } catch (IOException e) {
        LOGGER.error("Error  : "+ e); 
        LOGGER.error(e.getStackTrace(),e);
    }
    
    LOGGER.info("Returning from OID Service call------------");

}

public void pushToWorkflow(String userId,Long provId)
{
    EntityManager entityManager = DBUtil.getEntityManager();
    Query userQuery = entityManager.createNativeQuery("select UUM_BRANCH_ID,UUM_DESIGNATION from ums_usr_mst where uum_usr_id=:userId");
    userQuery.setParameter("userId",userId);
    List masterList = userQuery.getResultList();
    LOGGER.info("User master List---------  " + masterList.size());
    Iterator iter = masterList.iterator();
    String designation = null;
    String officeCode = null;
    while (iter.hasNext()) {
        Object[] masterData = (Object[])iter.next();
        officeCode= masterData[0].toString();
        designation = masterData[1].toString();
    }
    LOGGER.info("User Id  came for Workflow Application-----" + userId);
    
    //appRoleMapRequestASBO.setRoleName(getRoleName(appRoleMapRequestASBO.getRoleId()));
   // LOGGER.info("{\"userId\":\"" + appRoleMapRequestASBO.getUserId() + "\",\"officeCode\":\"" + appRoleMapRequestASBO.getBranchId() + "\",\"role\":\"" + appRoleMapRequestASBO.getRoleName() + "\",\"status\":" + true + ",\"designation\":\"" + designation + "\",\"action\":\"" + appRoleMapRequestASBO.getActionDo()+"R" + "\",\"userRemark\":\"User Details Added\"}");            
    URL url = null;
    HttpURLConnection connection = null;
    try {
        StringBuilder response;
       
            BufferedReader br;
            url = new URL(UtilProperties.getWorkflowServiceUrl());
            LOGGER.info(UtilProperties.getWorkflowServiceUrl());
            response = new StringBuilder();
            connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
            outputStreamWriter.write("{\"userId\":\"" + userId + "\",\"officeCode\":\"" + officeCode + "\",\"role\":\"NA\",\"status\":" + true + ",\"designation\":\"" + designation + "\",\"action\":\"UU\",\"userRemark\":\"User Details Added\"}");
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
        AcknowledgementRequestSO acknowledgeASBO = new AcknowledgementRequestSO();
        acknowledgeASBO.setProvDate(new Date().toString());
        acknowledgeASBO.setRemark(jobject.get("statusMessage").getAsString());
        acknowledgeASBO.setStatus(jobject.get("resultStatus").getAsString());
        sendAcknowledgeRequest(acknowledgeASBO);
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

}
