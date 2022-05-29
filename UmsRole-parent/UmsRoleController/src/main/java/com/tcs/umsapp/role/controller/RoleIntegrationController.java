package com.tcs.umsapp.role.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HttpsURLConnection;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.umsrole.exception.cmo.ErrorVO;
import com.tcs.umsrole.exception.cmo.ValidationException;
import com.tcs.umsrole.persist.dao.AcknowledgeDao;
import com.tcs.umsrole.persist.dao.AcknowledgeDaoImpl;
import com.tcs.umsrole.persist.dao.RoleDao;
import com.tcs.umsrole.persist.dao.RoleDaoImpl;
import com.tcs.umsrole.request.AcknowledgeASBO;
import com.tcs.umsrole.request.RoleDetailsASBO;
import com.tcs.umsrole.request.UpdateRoleDetailsASBO;
import com.tcs.umsrole.request.UserAppAndRoleAccessDetails;
import com.tcs.umsrole.request.UserRoleDetails;
import com.tcs.umsrole.response.GetRoleDetailsResponseASBO;
import com.tcs.umsrole.so.request.AcknowledgeRequestSo;
import com.tcs.umsrole.so.request.GetRoleRequestSO;
import com.tcs.umsrole.so.request.UpdateRoleRequestSO;
import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;
import com.tcs.umsrole.vo.util.UtilConstants;
import com.tcs.umsrole.vo.util.UtilProperties;
import com.tcs.umsrole.vo.util.ValidationUtil;

public class RoleIntegrationController {

    private static final Logger LOGGER = Logger
            .getLogger(RoleIntegrationController.class);
    private RoleDao roleDao;
    private AcknowledgeDao acknowledgeDao;
    private GetRoleDetailsResponseASBO getRoleDetailsResponseASBO;

    public RoleIntegrationController() {

        roleDao = new RoleDaoImpl();
        acknowledgeDao = new AcknowledgeDaoImpl();

        LOGGER.info("Reached inside role integration controller");
    }

    public UmsRoleResponseObject processUpdateRole(
            GetRoleRequestSO getRoleRequestSO) {
        LOGGER.info("Reached in processGetRole integration controller");
        
		try {
			if(!ValidationUtil.validateRemark(getRoleRequestSO.getReason()) || !ValidationUtil.validateSpecialChar(getRoleRequestSO.getReason())){
				LOGGER.error("Invalid R : "+getRoleRequestSO.getReason());
				throw new ValidationException(UtilConstants.ERROR_CODE_TransformationException,"Invalid Remark : " + getRoleRequestSO.getReason());
			}
		}catch(ValidationException ve){
			LOGGER.error(ve.getStackTrace(),ve);
			ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(ve.getErrorCode());
			errorVO.setErrorMessage(ve.getField());
			return errorVO;
		}

        
        UpdateRoleDetailsASBO getRoleDetailsRequestASBO = new UpdateRoleDetailsASBO();
        getRoleDetailsRequestASBO.setPermission(getRoleRequestSO
                .getPermissionName());
        getRoleDetailsRequestASBO.setpLId(getRoleRequestSO.getpLId());
        getRoleDetailsRequestASBO.setReason(getRoleRequestSO.getReason());
        getRoleDetailsRequestASBO.setRemovedBranches(getRoleRequestSO.getRemovedBranches());
        getRoleDetailsRequestASBO.setRequestBy(getRoleRequestSO.getRequestBy());
        List<RoleDetailsASBO> compareRoles = compareRoles(getRoleRequestSO);
        
        LOGGER.info("Compare list Size  "+ compareRoles.size());
        if(!compareRoles.isEmpty() ||getRoleRequestSO.getpLId()!= null){
        LOGGER.info("final Roles List " + compareRoles);
        getRoleDetailsRequestASBO.setUserRoles(compareRoles);

        getRoleDetailsRequestASBO.setUserId(getRoleRequestSO.getUserId());
        LOGGER.info("user id------------------------"
                + getRoleRequestSO.getUserId());
        getRoleDetailsRequestASBO.setBranchId(getRoleRequestSO.getBranchId());

        getRoleDetailsResponseASBO = roleDao
                .updateUserRolesDetails(getRoleDetailsRequestASBO);

        LOGGER.info("Role Updated ---------- ");

        return getRoleDetailsResponseASBO;
        }else
        {
        	ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_NO_ROLE_TO_UPDATE_CODE);
			errorVO.setErrorMessage(UtilConstants.ERROR_CODE_NO_ROLE_TO_UPDATE_MSG);
			return errorVO;
        }
        
    }

    private List<RoleDetailsASBO> compareRoles(GetRoleRequestSO getRoleRequestSO) {
        List<RoleDetailsASBO> allRoles = new ArrayList<>();
        URL url = null;
        HttpsURLConnection connection = null;
        try {
            url = new URL(UtilProperties.getUserRoleSearchUrl());
            StringBuilder response = new StringBuilder();

            connection = (HttpsURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/json");

            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    connection.getOutputStream());
            outputStreamWriter.write("{\"userId\":"
                    + getRoleRequestSO.getUserId() + "}");
            outputStreamWriter.flush();
            outputStreamWriter.close();
            if (connection.getResponseCode() == 200) {
            	 BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                String s ;
	                while ((s = br.readLine())!=null) {
	                    response.append(s);
	                }
	                LOGGER.info("-ServiceResponse -" + connection.getResponseCode());
	                if(null!=br)
            			br.close();
            		
            }
            Gson gson = new Gson();
            UserAppAndRoleAccessDetails userAppAndRoleAccessDetails = gson.fromJson(response.toString(),
                    UserAppAndRoleAccessDetails.class);
            List<UserRoleDetails> exstingRoles = new ArrayList<>();
            List<UserRoleDetails> assignedRoles = new ArrayList<>();
            Set<Entry<String, List<UserRoleDetails>>> entrySet = userAppAndRoleAccessDetails.getAppAndRoleAccessDetails().entrySet();
            
            for (Entry<String, List<UserRoleDetails>> entry : entrySet) {
                for (UserRoleDetails userRoleDetails : entry.getValue()) {
                	if(userRoleDetails.getStatus().equals("S")){
	                    if(null==userRoleDetails.getOfficeCode() || userRoleDetails.getOfficeCode().isEmpty())
	                    	userRoleDetails.setOfficeCode(getRoleRequestSO.getBranchId());
	                    exstingRoles.add(userRoleDetails);
                	}
                }
            }

            LOGGER.info("Exsting Roles------------" + exstingRoles.size());

            for (Iterator<UpdateRoleRequestSO> it = getRoleRequestSO.getUserRoles().iterator(); it.hasNext();) {
                UpdateRoleRequestSO roleDetails = it.next();
                UserRoleDetails newRoles= new UserRoleDetails();

                newRoles.setAppId(roleDetails.getAppId());
                newRoles.setRoleId(roleDetails.getRoleId());
                if(null==roleDetails.getOfficeCode() || roleDetails.getOfficeCode().isEmpty())
                	newRoles.setOfficeCode(getRoleRequestSO.getBranchId());
                else
                	newRoles.setOfficeCode(roleDetails.getOfficeCode());
                newRoles.setStartDate(roleDetails.getStartDate());
                newRoles.setEndDate(roleDetails.getEndDate());
                
                assignedRoles.add(newRoles);
            }
            
            LOGGER.info("Assigned Roles------------" + assignedRoles.size());

            Collection<UserRoleDetails> similar = new HashSet<>();
            Collection<UserRoleDetails> addRole = new HashSet<>();
            Collection<UserRoleDetails> deleteRole = new HashSet<>();
           
           
            for (UserRoleDetails userExstingRoles : exstingRoles) {
            	boolean similarMatch =false;
            	boolean idMatch=false;
            	boolean officeCodeMatch=false;
            	boolean stDateMatch=false;
            	boolean endDateMatch=false;
				for (UserRoleDetails userAssignedRole : assignedRoles) {
					int compareTo = userExstingRoles.compareTo(userAssignedRole);
					
					if(compareTo==0){
						similar.add(userAssignedRole);
						similarMatch=true;
						break;
					}
					else if(compareTo==4){
						endDateMatch=true;
						userExstingRoles.setEndDate(userAssignedRole.getEndDate());
						break;
					}
					else if(compareTo==3){
						stDateMatch=true;
					}
					else if(compareTo==2){
						officeCodeMatch=true;				
					}
					else if(compareTo==1){
						idMatch=true;
					}
				}
				if(!similarMatch){
					if(endDateMatch){
						deleteRole.add(userExstingRoles);
					}else if(stDateMatch){
						addRole.add(userExstingRoles);
					}else if(officeCodeMatch){
						deleteRole.add(userExstingRoles);
					}else if(idMatch){
						deleteRole.add(userExstingRoles);
					}
				}
				
			}
            
            for (UserRoleDetails userAssignedRole : assignedRoles) {
            	boolean idMatch=false;
            	
            	for (UserRoleDetails similarRoles : similar) {
            		int compareTo = userAssignedRole.compareTo(similarRoles);
            		if(compareTo==0){
            			idMatch=true;
            			break;
					}
            	}

            	if(!idMatch){
            		for (UserRoleDetails deleteRoles : deleteRole) {
                		int compareTo = userAssignedRole.compareTo(deleteRoles);
                		if(compareTo==0){
                			idMatch=true;
                			break;
    					}
                	}
            	}
            	
            	if(!idMatch){
            		LOGGER.info("\\\\----------    "+userAssignedRole.toString());
            		addRole.add(userAssignedRole);
            	}
            }
            
            
            LOGGER.info("Similar Roles  " + similar.size());
            LOGGER.info("AR " + addRole);
            LOGGER.info("DR " + deleteRole);
            LOGGER.info("AR " + addRole.size());
            LOGGER.info("DR " + deleteRole.size());

            // for add role

            for (UserRoleDetails roleToAdd : addRole) {
//                for (Iterator it = getRoleRequestSO.getUserRoles().iterator(); it.hasNext();) {
//                    UpdateRoleRequestSO roleDetails = (UpdateRoleRequestSO) it.next();
//                    if (roleDetails.getRoleId().equals(roleId.getRoleId())) {
                        RoleDetailsASBO role = new RoleDetailsASBO();
                        role.setAppId(roleToAdd.getAppId());
                        role.setRoleId(roleToAdd.getRoleId());
                        role.setOfficeCode(roleToAdd.getOfficeCode());
                        role.setStartDate(roleToAdd.getStartDate());
                        role.setEndDate(roleToAdd.getEndDate());
                        role.setAction("A");
                        allRoles.add(role);
                    }
	
            
            // for delete role
            for (UserRoleDetails roleToDelete : deleteRole) {
                            RoleDetailsASBO role = new RoleDetailsASBO();
                            role.setAction("D");
                            role.setAppId(roleToDelete.getAppId());
                            role.setRoleId(roleToDelete.getRoleId());
                            role.setOfficeCode(roleToDelete.getOfficeCode());
                            role.setStartDate(roleToDelete.getStartDate());
                            role.setEndDate(roleToDelete.getEndDate());
                            allRoles.add(role);
                        }


            
            LOGGER.info("All roles to modify : "+ allRoles.size());
            
            LOGGER.info("All roles : " + allRoles);

        } catch (IOException e) {
            LOGGER.error(e.getStackTrace(),e);
        }
       return allRoles;
    }
    
    
    
    
    public void getOIDAndUMSRequest(){
    	roleDao.getUmsAndOidReq();
    }

    public UmsRoleResponseObject acknowledgeRequest(
            AcknowledgeRequestSo acknowledgeRequestSo) {
        LOGGER.info("Reached in processGetRole integration controller");

        AcknowledgeASBO acknowledgeASBO = new AcknowledgeASBO();
        acknowledgeASBO.setProvId(acknowledgeRequestSo.getProvId());
        acknowledgeASBO.setProvDate(new Date());
        acknowledgeASBO.setRemark(acknowledgeRequestSo.getRemark());
        acknowledgeASBO.setSqlCode(acknowledgeRequestSo.getSqlCode());
        acknowledgeASBO.setSqlMessage(acknowledgeRequestSo.getSqlMessage());
        acknowledgeASBO.setStatus(acknowledgeRequestSo.getStatus());
        LOGGER.info("before return statement of acknowledgeASBO");

        acknowledgeDao.acknowledgeRequest(acknowledgeASBO , true);
        return getRoleDetailsResponseASBO;
    }

    public UmsRoleResponseObject updateXLRole(GetRoleRequestSO getRoleRequestSO) {
        LOGGER.info("Reached in processGetRole integration controller");
       
        LOGGER.info(getRoleRequestSO.toString());
        UpdateRoleDetailsASBO getRoleDetailsRequestASBO = new UpdateRoleDetailsASBO();
        getRoleDetailsRequestASBO.setPermission(getRoleRequestSO
                .getPermissionName());
        getRoleDetailsRequestASBO.setpLId(getRoleRequestSO.getpLId());
        getRoleDetailsRequestASBO.setReason(getRoleRequestSO.getReason());
        getRoleDetailsRequestASBO.setRequestBy(getRoleRequestSO.getRequestBy());
        
        List<RoleDetailsASBO> userRoles = new ArrayList<>();

        getRoleRequestSO.getUserRoles().stream().forEach(roleRequestSO ->{
        	
            RoleDetailsASBO asbo = new RoleDetailsASBO();
            asbo.setAction(roleRequestSO.getAction());
            asbo.setAppId(roleRequestSO.getAppId());
            asbo.setAppName(roleRequestSO.getAppName());
            asbo.setRoleId(roleRequestSO.getRoleId());
            asbo.setRoleName(roleRequestSO.getRoleName());
            asbo.setUserId(roleRequestSO.getUserId());
            asbo.setBranchId(roleRequestSO.getBranchId());
            asbo.setOfficeCode(roleRequestSO.getBranchId());
            
            LOGGER.info(asbo.toString());
            userRoles.add(asbo);
        });
        
        
        LOGGER.info("final Roles List " + userRoles.toString());
        getRoleDetailsRequestASBO.setUserRoles(userRoles);
        getRoleDetailsRequestASBO.setReason("Upload Excel");
        LOGGER.info("user id------------------------"
                + getRoleDetailsRequestASBO.getUserId());
        LOGGER.info("values set in getRoleDetailsRequestASBO" + getRoleDetailsRequestASBO.toString());
        
        
        getRoleDetailsResponseASBO = roleDao.updateXLUserRolesDetails(getRoleDetailsRequestASBO);

        LOGGER.info("before return statement of getRoleDetailsResponseASBO");

        return getRoleDetailsResponseASBO;
    }

}
