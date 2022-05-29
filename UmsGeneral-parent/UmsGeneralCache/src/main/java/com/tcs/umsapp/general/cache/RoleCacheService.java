package com.tcs.umsapp.general.cache;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.tcs.umsapp.general.persist.dao.RoleDetailDao;
import com.tcs.umsapp.general.persist.dao.RoleDetailDaoImpl;
import com.tcs.umsapp.general.response.RoleDetailDBResponseASBO;


public class RoleCacheService {

	private static final Logger LOGGER = Logger
			.getLogger(RoleCacheService.class);
	
	public static ConcurrentMap<String, ConcurrentMap<String,Long>> roleList = new ConcurrentHashMap<String, ConcurrentMap<String,Long>>();
	public static ConcurrentMap<Long , String> nonPrivilageRoleList = new ConcurrentHashMap<Long , String>();
	public static ConcurrentMap<String, String> appList = new ConcurrentHashMap<String, String>();

	static Timer timer = null;
	static long delay = 5000; 
	static long refreshInerval = 21600000; // 6 Hour
	
	static TimerTask task = new TimerTask() {
	      @Override
	      public void run() {
	        
	    	  DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	    	  Date date = new Date();
	    	  RoleCacheService.getRoleDetailFromDB();
	          LOGGER.info("The caching is renewed for Role Cacheing : " + dateFormat.format(date));

	      }
	    };
	    
	    static {
	  	   timer = new Timer();
	 	   timer.schedule(task, delay,refreshInerval);
	 	   LOGGER.info("Timer Scheduler created");
	  }
	    


	public RoleDetailDBResponseASBO cacheRoleDetails(String userId){
		
		RoleDetailDao roleDetailDao = new RoleDetailDaoImpl();
		String branchId = roleDetailDao.getBranchId(userId);
		
		LOGGER.info("Branch Id : " + branchId);
		 
		 LOGGER.debug("cacheRoleDetail called");
		 RoleDetailDBResponseASBO roleDetailDBResponseASBO = new RoleDetailDBResponseASBO();
		 
		 if(roleList == null || appList == null || roleList.isEmpty() || appList.isEmpty() || nonPrivilageRoleList==null || nonPrivilageRoleList.isEmpty()){
			 LOGGER.debug("Call DB for role list");
			 getRoleDetailFromDB();
			 //HO user has branch ID 10000 and HO user has access to all roles
			 if(branchId.equalsIgnoreCase("100000")){
				 roleDetailDBResponseASBO.setRoleList(roleList);
			 }else{
				 //admin user can see only roles which are non privelaged and JIRA roles pertaining to that RO 
				 roleDetailDBResponseASBO.setRoleList(roleList);
				 roleDetailDBResponseASBO.setNonPrivilageRoleList(nonPrivilageRoleList);
				 //added to get JIRA application list from UMS_JIRA_RO_MAPPING table
				 roleDetailDBResponseASBO.setJiraRoleList(roleDetailDao.getRoleForJira(branchId));
			 }
			 
			 roleDetailDBResponseASBO.setApplicationList(appList);
			 return roleDetailDBResponseASBO;
		 }
		 else{
			 LOGGER.debug("Use Cache copy for Result");
			//HO user has branch ID 10000 and HO user has access to all roles
			 if(branchId.equalsIgnoreCase("100000")){
				 roleDetailDBResponseASBO.setRoleList(roleList);
			 }else{
			   //admin user can see only roles which are non privelaged and JIRA roles pertaining to that RO 
				 roleDetailDBResponseASBO.setRoleList(roleList);
				 roleDetailDBResponseASBO.setNonPrivilageRoleList(nonPrivilageRoleList);
				 //added to get JIRA application list from UMS_JIRA_RO_MAPPING table
				 roleDetailDBResponseASBO.setJiraRoleList(roleDetailDao.getRoleForJira(branchId));
			 }
			 roleDetailDBResponseASBO.setApplicationList(appList);
			 return roleDetailDBResponseASBO;
		 }
		 
		 
		 
	 } 
	
	public static void getRoleDetailFromDB(){
		RoleDetailDao roleDetailDao = new RoleDetailDaoImpl();
		roleList = roleDetailDao.getRoleDetailList();
		nonPrivilageRoleList = roleDetailDao.getNonPrivilageRoleList();
		appList = roleDetailDao.getApplicationNameList();
		LOGGER.info("Cache Renewed through Request");
		LOGGER.info("Role List: " + roleList.size());
		LOGGER.info("Application List: " + appList.size());
		LOGGER.info("Non Priviledge Role List: " + nonPrivilageRoleList.size());
	}
	    
}
