package com.tcs.umsoid.ldap;

import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.umsoid.common.UserRoleDetails;
import com.tcs.umsoid.persist.dao.UserRoleOidDao;
import com.tcs.umsoid.persist.dao.UserRoleOidDaoImp;
import com.tcs.umsoid.request.UserLdapDetailRequestASBO;
import com.tcs.umsoid.response.UserLdapDetailResponseASBO;
import com.tcs.umsoid.so.request.OIDRoleUpdateRequestSO;
import com.tcs.umsoid.so.request.UserLdapDetailsRequestSO;
import com.tcs.umsoid.vo.bean.OIDUserDetails;
import com.tcs.umsoid.vo.bean.ProvisionDetails;
import com.tcs.umsoid.vo.cmo.UmsappResponseObject;
import com.tcs.umsoid.vo.ldap.OIDGroupUtil;

public class UserOidMapIntegrationController {

    private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");
    private UserRoleOidDao userRoleOidDao;

    public UserOidMapIntegrationController() {
        userRoleOidDao = new UserRoleOidDaoImp();
    }

    public UmsappResponseObject ldifRevokeRolesMapManagement(
            UserLdapDetailsRequestSO getuserLdapDetailsRequestSO) {
        LOGGER.info("Reached inside ----------------            "  + getuserLdapDetailsRequestSO.toString());
        UserLdapDetailRequestASBO getuserLdapDetailRequestASBO = new UserLdapDetailRequestASBO();
        UserLdapDetailResponseASBO getUserLdapDetailResponseASBO =new UserLdapDetailResponseASBO();

        getuserLdapDetailRequestASBO.setUserID(getuserLdapDetailsRequestSO
                .getUserID());

        List<UserRoleDetails> userDetailList = userRoleOidDao.ldifRevokeRolesMapManagement(getuserLdapDetailRequestASBO);
         

			OIDGroupUtil oidService = new OIDGroupUtil();
			boolean checkAllRolesDeleted = true;
			for(UserRoleDetails userRolecode :userDetailList){
				ProvisionDetails provisionDetails = new ProvisionDetails();
				provisionDetails.setRoleCode(userRolecode.getApplicationRoleCode());
				provisionDetails.setUserID(userRolecode.getUserId());
				LOGGER.info(" Inside loop RoleCode- -- "+userRolecode.getApplicationRoleCode());
				OIDUserDetails userDeleteRole = oidService.deleteRole(provisionDetails);
				if(!userDeleteRole.getStatus().equals("S")){
					checkAllRolesDeleted=false;
				}
			}
			
			if(checkAllRolesDeleted){
				getUserLdapDetailResponseASBO.setStatusMessage("User Role deleted successfully.");
				getUserLdapDetailResponseASBO.setStatusCode("S");
			} else {
		    	getUserLdapDetailResponseASBO.setStatusMessage("Failed to delete user role.");
		    	getUserLdapDetailResponseASBO.setStatusCode("F");
		    }

        LOGGER.info("values set in getUserLdapDetailResponseASBO-----"
                + getUserLdapDetailResponseASBO.toString());
        
        
        return getUserLdapDetailResponseASBO;
    }


    public UmsappResponseObject mapOIDRequestAction(
            OIDRoleUpdateRequestSO oidRoleUpdateRequestSO) {
        UserLdapDetailRequestASBO userLdapDetailRequestASBO = new UserLdapDetailRequestASBO();
        UserLdapDetailResponseASBO userLdapDetailResponseASBO = new UserLdapDetailResponseASBO();

        userLdapDetailRequestASBO.setRequestID(oidRoleUpdateRequestSO
                .getRequestID());
        userLdapDetailRequestASBO.setProvisionID(oidRoleUpdateRequestSO
                .getProvisionID());

        LOGGER.info("Values set in ASBO : " + userLdapDetailRequestASBO);

        // Get details for provision request from database
        List<ProvisionDetails> provReqList = userRoleOidDao
                .getProvisionRequestList(userLdapDetailRequestASBO);
        LOGGER.info(provReqList);

        if (provReqList.isEmpty()) {
            userLdapDetailResponseASBO.setStatusCode("F");
            userLdapDetailResponseASBO
                    .setStatusMessage("No Provision Details found for request in DB.");
            LOGGER.error("No Provision Details found for request in DB");
            return userLdapDetailResponseASBO;
        }

        OIDGroupUtil oidService = new OIDGroupUtil();
        OIDUserDetails userDetails;
        for (ProvisionDetails provReq : provReqList) {
            LOGGER.info("Provision Action : " + provReq.getProvisionAction());
            switch (provReq.getProvisionAction()) {
            case "AR":
                LOGGER.info("---Executing Add Role Action---");
                userDetails = oidService.addRole(provReq);

                userLdapDetailResponseASBO.setStatusCode(userDetails
                        .getStatus());
                if (userDetails.getStatus().equals("S")) {
                    userLdapDetailResponseASBO
                            .setStatusMessage("User Role added successfully.");
                } else {
                    userLdapDetailResponseASBO
                            .setStatusMessage("Failed to add user role.");
                }

                if (userRoleOidDao.setActionStatus(userDetails,
                        provReq.getProvisionAction())) {
                    if (userDetails.getStatus().equals("S")) {
                        userLdapDetailResponseASBO
                                .setStatusMessage("User Role added successfully.");
                    } else {
                        userLdapDetailResponseASBO
                                .setStatusMessage("Failed to add user role.");
                    }
                } else {
                    userLdapDetailResponseASBO
                            .setStatusMessage("User Role updated successfully. Failed to update DB.");
                }
                break;
            case "DR":
                LOGGER.info("---Executing Delete Role Action---");
                userDetails = oidService.deleteRole(provReq);
                userLdapDetailResponseASBO.setStatusCode(userDetails
                        .getStatus());
                if (userRoleOidDao.setActionStatus(userDetails,
                        provReq.getProvisionAction())) {
                    if (userDetails.getStatus().equals("S")) {
                        userLdapDetailResponseASBO
                                .setStatusMessage("User Role deleted successfully.");
                    } else {
                        userLdapDetailResponseASBO
                                .setStatusMessage("Failed to delete user role.");
                    }
                } else {
                    userLdapDetailResponseASBO
                            .setStatusMessage("User Role delete successfully. Failed to update DB.");
                }
                break;
            case "UU":
                LOGGER.info("---Executing Update User Action---");
                userDetails = oidService.updateUser(provReq);
                userLdapDetailResponseASBO.setStatusCode(userDetails
                        .getStatus());
                if (userRoleOidDao.setActionStatus(userDetails,
                        provReq.getProvisionAction())) {
                    if (userDetails.getStatus().equals("S")) {
                        userLdapDetailResponseASBO
                                .setStatusMessage("User updated successfully.");
                    } else {
                        userLdapDetailResponseASBO
                                .setStatusMessage("Failed to update user.");
                    }
                } else {
                    userLdapDetailResponseASBO
                            .setStatusMessage("User updated successfully. Failed to update DB.");
                }
                break;
            case "DU":
                LOGGER.info("---Executing Disable User Action---");
                userDetails = oidService.disableUser(provReq);
                userLdapDetailResponseASBO.setStatusCode(userDetails
                        .getStatus());
                if (userRoleOidDao.setActionStatus(userDetails,
                        provReq.getProvisionAction())) {
                    if (userDetails.getStatus().equals("S")) {
                        userLdapDetailResponseASBO
                                .setStatusMessage("User disabled successfully.");
                    } else {
                        userLdapDetailResponseASBO
                                .setStatusMessage("Failed to disable user.");
                    }
                } else {
                    userLdapDetailResponseASBO
                            .setStatusMessage("User disabled successfully. Failed to update DB.");
                }
                break;
            default:
                LOGGER.error("Unmapped provision action found. No action will be taken.");
                userLdapDetailResponseASBO.setStatusCode("F");
                userLdapDetailResponseASBO
                        .setStatusMessage("Unmapped provision action found.");
            }
        }

        return userLdapDetailResponseASBO;
    }

}
