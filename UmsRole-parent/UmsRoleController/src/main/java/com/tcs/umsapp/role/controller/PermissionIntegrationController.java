package com.tcs.umsapp.role.controller;



import org.apache.log4j.Logger;

import com.tcs.umsrole.persist.dao.PermissionListDao;
import com.tcs.umsrole.persist.dao.PermissionListDaoImpl;
import com.tcs.umsrole.request.PermissionListRequestASBO;
import com.tcs.umsrole.response.PermissionListResponseASBO;
import com.tcs.umsrole.so.request.PermissionListMmtRequestSO;
import com.tcs.umsrole.vo.cmo.Header;
import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;

public class PermissionIntegrationController {
	
	private static final Logger LOGGER = Logger
			.getLogger(PermissionIntegrationController.class);

	private PermissionListDao permissionListDao;
	
	
	public PermissionIntegrationController(){
		permissionListDao= new PermissionListDaoImpl();
		LOGGER.info("reached inside PermissionIntegrationController");
	}
	
	public UmsRoleResponseObject getPermission(PermissionListMmtRequestSO permissionListMmtRequestSO){
		LOGGER.info("reached inside getPermission in PermissionIntegrationController");
		PermissionListResponseASBO permissionListResponseASBO;
		Header header = permissionListMmtRequestSO.getHeader();
		
		PermissionListRequestASBO permissionListRequestASBO = new PermissionListRequestASBO();
		
		permissionListRequestASBO.setUserId(permissionListMmtRequestSO.getUserId());
		permissionListRequestASBO.setProvisionId(permissionListMmtRequestSO.getProvisionId());
		permissionListRequestASBO.setPermissionListId(permissionListMmtRequestSO.getPermissionListId());
		permissionListRequestASBO.setPermissionCreatedBy(permissionListMmtRequestSO.getPermissionCreatedBy());
		permissionListRequestASBO.setpermissionendDate(permissionListMmtRequestSO.getpermissionendDate());
		
		LOGGER.info("values set in permissionListRequestASBO");
		
		permissionListResponseASBO = permissionListDao.permissionListUpdate(permissionListRequestASBO);
		permissionListResponseASBO.setHeader(header);
		
		LOGGER.info("before return statement of permissionListRequestASBO");
		
		
		return permissionListResponseASBO;
		
	}
}
  