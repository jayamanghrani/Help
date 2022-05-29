package com.tcs.umsuser.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.umsuser.exception.cmo.ErrorVO;
import com.tcs.umsuser.persist.dao.ProvisionDetailsDao;
import com.tcs.umsuser.persist.dao.ProvisionDetailsDaoImpl;
import com.tcs.umsuser.persist.dao.UpdateUserDao;
import com.tcs.umsuser.persist.dao.UpdateUserDaoImpl;
import com.tcs.umsuser.request.UpdateUserRequestSO;
import com.tcs.umsuser.request.asbo.PendingUserTempDetail;
import com.tcs.umsuser.request.asbo.ProvDetailsRequestASBO;
import com.tcs.umsuser.request.asbo.UpdateJiraUserRequestSO;
import com.tcs.umsuser.request.asbo.UpdateUserRequestASBO;
import com.tcs.umsuser.response.asbo.ProvisionDetailsResponseASBO;
import com.tcs.umsuser.response.asbo.RoleUpdateResponseASBO;
import com.tcs.umsuser.util.UtilConstants;
import com.tcs.umsuser.util.ValidationUtil;
import com.tcs.umsuser.vo.cmo.UmsResponseObject;

public class UpdateUserIntegrationController {
private static final Logger LOGGER = Logger.getLogger(UpdateUserIntegrationController.class);
    
	
	UpdateUserDao updateUserDao=new UpdateUserDaoImpl();
	
    public UmsResponseObject updateUser(UpdateUserRequestSO requestSO) {
    	LOGGER.info("Inside updateUser");

    	UpdateUserRequestASBO updateUserRequestASBO = new UpdateUserRequestASBO();
    	RoleUpdateResponseASBO responseObject= new RoleUpdateResponseASBO();

    	updateUserRequestASBO.setUserId(requestSO.getUserId());
    	updateUserRequestASBO.setTitle(requestSO.getTitle());
    	updateUserRequestASBO.setFirstName(requestSO.getFirstName());
    	updateUserRequestASBO.setMiddleName(requestSO.getMiddleName());
    	updateUserRequestASBO.setLastName(requestSO.getLastName());
    	updateUserRequestASBO.setBranchId(requestSO.getBranchId());
    	updateUserRequestASBO.setGender(requestSO.getGender());
    	
    	if(null != requestSO.getDob() && !requestSO.getDob().isEmpty()){
    		updateUserRequestASBO.setDob(ValidationUtil.validateDate(requestSO.getDob()));
    	}else{
    		updateUserRequestASBO.setDob(null);
    	}
    	updateUserRequestASBO.setAddr1(requestSO.getAddr1());
    	updateUserRequestASBO.setAddr2(requestSO.getAddr2());
    	updateUserRequestASBO.setAddr3(requestSO.getAddr3());
    	updateUserRequestASBO.setCity(requestSO.getCity());
    	updateUserRequestASBO.setState(requestSO.getState());
    	updateUserRequestASBO.setCountry(requestSO.getCountry());
    	updateUserRequestASBO.setPin(requestSO.getPin());
    	updateUserRequestASBO.setEmail(requestSO.getEmail());
    	updateUserRequestASBO.setPhoneNo(requestSO.getPhoneNo());
    	updateUserRequestASBO.setMobileNo(requestSO.getMobileNo());
    	updateUserRequestASBO.setExtension(requestSO.getExtension());
    	updateUserRequestASBO.setIpPhone(requestSO.getIpPhone());
    	if(null != requestSO.getStartDate() && !requestSO.getStartDate().isEmpty()){
    		updateUserRequestASBO.setStartDate(ValidationUtil.validateDate(requestSO.getStartDate()));
    	}else{
    		updateUserRequestASBO.setStartDate(new Date());
    	}
    	if(null != requestSO.getEndDate() && !requestSO.getEndDate().isEmpty()){
    		updateUserRequestASBO.setEndDate(ValidationUtil.validateDate(requestSO.getEndDate()));
    	}else{
    		updateUserRequestASBO.setEndDate(null);
    	}
    	
    	updateUserRequestASBO.setSupervisorId(requestSO.getSupervisorId());
    	updateUserRequestASBO.setDesignation(requestSO.getDesignation());
    	updateUserRequestASBO.setCreatedBy(requestSO.getCreatedBy());
    	
    	if(null != requestSO.getCreatedDate() && !requestSO.getCreatedDate().isEmpty()){
    		updateUserRequestASBO.setCreatedDate(ValidationUtil.validateDate(requestSO.getCreatedDate()));
    	}else{
    		updateUserRequestASBO.setCreatedDate(new Date());
    	}
    	
    	updateUserRequestASBO.setModifiedBy(requestSO.getModifiedBy());
    	
    	if(null != requestSO.getModifiedDate() && !requestSO.getModifiedDate().isEmpty()){
    		updateUserRequestASBO.setModifiedDate(ValidationUtil.validateDate(requestSO.getModifiedDate()));
    	}else{
    		updateUserRequestASBO.setModifiedDate(new Date());
    	}
    	
    	updateUserRequestASBO.setStatus(requestSO.getStatus());
    	
    	if(null != requestSO.getEffectiveStartDate() && !requestSO.getEffectiveStartDate().isEmpty()){
    		updateUserRequestASBO.setEffectiveStartDate(ValidationUtil.validateDate(requestSO.getEffectiveStartDate()));
    	}else{
    		updateUserRequestASBO.setEffectiveStartDate(new Date());
    	}
    	
    	updateUserRequestASBO.setFlagAction(requestSO.getFlagAction());
    	
    	LOGGER.info("SO to ASBO conversion done");

       try{
        responseObject = updateUserDao.updateUser(updateUserRequestASBO);
        LOGGER.info("--------------   Details inserted successfully from Temp to Mst -------------" +  responseObject.toString());
        if(responseObject.getStatusCode().equalsIgnoreCase("S")){
        	
        	responseObject=updtaeFromTempToMaster(responseObject);
        }
       }catch(Exception e){
		    LOGGER.error("Error Occured");
		    LOGGER.error(e.getStackTrace(),e);
		   	ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TECHNICHALEXCEPTION);
			errorVO.setErrorMessage(UtilConstants.Fail_message);
			return errorVO;  
       }
 
        return responseObject;
    }

    
    public RoleUpdateResponseASBO updtaeFromTempToMaster(RoleUpdateResponseASBO inputRes){
    	RoleUpdateResponseASBO responseObject =new RoleUpdateResponseASBO();

        LOGGER.info("Starting Schedular method ---------");
        List<PendingUserTempDetail> updateTempToMst = updateUserDao.updateTempToMst(inputRes.getRequestId());
        
        if(!updateTempToMst.isEmpty()){
        	String action=null;
        	
        	for(PendingUserTempDetail userDetail : updateTempToMst){
				try {
			    	boolean flagToUpdateStatus=false;
					
						LOGGER.info("Checking start for ----------------   " + userDetail.getUserId());
					if((null != userDetail.getStatus() && (userDetail.getStatus().equalsIgnoreCase("D")||(userDetail.getStatus().equalsIgnoreCase("I"))))){
						action =UtilConstants.Action_delete;
						flagToUpdateStatus=deleteUserDetails(userDetail);

						}else{
							LOGGER.info("Checking for existing user ---------");
							int userCount=updateUserDao.checkUserExist(userDetail.getUserId());
							
							if(userCount == 0){
								action =UtilConstants.Action_new;
								flagToUpdateStatus=updateUserDao.insertNewUser(userDetail);
								
							}else if(userCount > 0){
								action =UtilConstants.Action_update;
								flagToUpdateStatus=updateUserDao.updateUserDetails(userDetail);
							}	
						}
						if(flagToUpdateStatus){
							int updateUsrTempStatus = updateUserDao.updateUsrTempStatus(action,userDetail.getRecordId());
							if(updateUsrTempStatus>0){
						    	LOGGER.info("Status updated for UMS_USR_TEMP_REC");
						    	responseObject.setStatusCode(UtilConstants.Success_code);
						        responseObject.setStatusMessage(UtilConstants.Success_message);
						    }else{
						    	responseObject.setStatusCode(UtilConstants.Fail_code);
								responseObject.setStatusMessage(UtilConstants.Fail_message);
						    }
						}else{
							responseObject.setStatusCode(UtilConstants.Fail_code);
							responseObject.setStatusMessage(UtilConstants.Fail_message);
							LOGGER.info("Operation failed hence not updating temp record status ---------");
						}

				} catch (SQLException se) {
					responseObject.setStatusCode(UtilConstants.Fail_code);
					responseObject.setStatusMessage(UtilConstants.Fail_message);
					LOGGER.error("SQL Error : " + se);
					LOGGER.info("Record ID :  " + userDetail.getRecordId() + "     Error Code:  " + se.getErrorCode() +"               " +   se.getMessage());
					if(userDetail.getRecordId()!=0L){
						updateUserDao.updateErrorStatus(userDetail.getRecordId() , se.getErrorCode(), se.getMessage(),action);
					}
					LOGGER.error(se.getStackTrace(),se);
				}
        	}
        }
        	else{
        	LOGGER.info("No pending updates available");
        }
        return responseObject;
    }
    
    
    
    
    
    
    


	private boolean deleteUserDetails(PendingUserTempDetail userDetail) throws SQLException {
		boolean statusFlag=true;
		LOGGER.info("Inside delete user details method ");
		List<String> userApplicationList =new ArrayList<String>();
		UpdateJiraUserRequestSO jiraUserRequestSO=new UpdateJiraUserRequestSO();
		ProvisionDetailsDao provisionDetailsDao = new ProvisionDetailsDaoImpl();
		ProvDetailsRequestASBO provDetailsRequestASBO = new ProvDetailsRequestASBO();
		provDetailsRequestASBO.setProvUserId(userDetail.getUserId());
		provDetailsRequestASBO.setProvAction(UtilConstants.Delete_user);
		provDetailsRequestASBO.setProvBranchId(userDetail.getBranchId());
		provDetailsRequestASBO.setProvModifiedBy(userDetail.getModifiedBy());
		
		userApplicationList = provisionDetailsDao.getUserAppId(userDetail.getUserId());
		ProvisionDetailsResponseASBO provisionDetailsResponseASBO = new ProvisionDetailsResponseASBO();
		if(!userApplicationList.isEmpty()){
			for(String appId : userApplicationList){
				provDetailsRequestASBO.setProvApplicationId(appId);
				if(appId.equalsIgnoreCase("OID")){
					provisionDetailsResponseASBO = provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
				}
				if(appId.equalsIgnoreCase("JIRA")){
				    jiraUserRequestSO.setAction("DU");
				    jiraUserRequestSO.setBranchId(userDetail.getBranchId());
				    //jiraUserRequestSO.setCity(userDetail.getCity());
				    jiraUserRequestSO.setMobileNo(userDetail.getMobile());
				    jiraUserRequestSO.setUserId(userDetail.getUserId());
				    jiraUserRequestSO.setName(userDetail.getFirstName()+" "+userDetail.getMiddlename()+" "+userDetail.getLastName());
				    
                    provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
                }else
					provisionDetailsDao.provisionDetailsUpdate(provDetailsRequestASBO);
			}
		}
		statusFlag=updateUserDao.deleteUserMaster(provDetailsRequestASBO , provisionDetailsResponseASBO,jiraUserRequestSO);    
		
		return statusFlag;
	}
	
	
	
}
