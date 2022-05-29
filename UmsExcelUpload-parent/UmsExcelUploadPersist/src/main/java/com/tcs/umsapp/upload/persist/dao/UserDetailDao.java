package com.tcs.umsapp.upload.persist.dao;

import java.util.List;
import java.util.Map;


public interface UserDetailDao {

	public String getLoginUserBranch(String loginUserId);
	
	public List<String> getAppNameList();
	
	public Map<String, String> getRoleNameList();
	
	public List<String> getAppAccessList(String user);

	public List<String> getAppId(String application);

	public List<String> getRoleId(String AppId, String role);

	
}
