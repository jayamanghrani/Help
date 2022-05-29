package com.tcs.umsapp.general.persist.dao;

import java.util.concurrent.ConcurrentMap;

public interface RoleDetailDao {
    public ConcurrentMap<String, ConcurrentMap<String, Long>> getRoleDetailList();

    public ConcurrentMap<String, String> getApplicationNameList();

	public ConcurrentMap<Long , String> getNonPrivilageRoleList();

	public String getBranchId(String userId);
	
	public ConcurrentMap<String, String> getRoleForJira(String branchId);
	
}
