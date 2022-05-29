package com.tcs.umsapp.upload.controller;



import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tcs.umsapp.upload.persist.dao.UserDetailDao;
import com.tcs.umsapp.upload.persist.dao.UserDetailDaoImpl;



public class ApplicationRoleController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(ApplicationRoleController.class);

	private List<String> applicationList;
	private Map<String , String> roleList;
	private UserDetailDao userDetailDao;
	
	public ApplicationRoleController() {
		super();
		LOGGER.info("Inside ApplicationRoleController.ApplicationRoleController");
		userDetailDao = new UserDetailDaoImpl();
		applicationList = userDetailDao.getAppNameList();
		roleList = userDetailDao.getRoleNameList();
		
		LOGGER.info("Application and Role Name List collected.");
	}

	public List<String> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<String> applicationList) {
		this.applicationList = applicationList;
	}

	public Map<String, String> getRoleList() {
		return roleList;
	}

	public void setRoleList(Map<String, String> roleList) {
		this.roleList = roleList;
	}

	
	

}
