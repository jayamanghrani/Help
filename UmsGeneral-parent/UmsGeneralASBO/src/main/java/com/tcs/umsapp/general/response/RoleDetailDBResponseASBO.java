package com.tcs.umsapp.general.response;

import java.util.concurrent.ConcurrentMap;

import com.tcs.umsapp.general.vo.cmo.UmsappResponseObject;

public class RoleDetailDBResponseASBO extends UmsappResponseObject{

	private static final long serialVersionUID = -5525293076822693108L;
	ConcurrentMap<String, ConcurrentMap<String,Long>> roleList;
	ConcurrentMap<Long , String> nonPrivilageRoleList;
	ConcurrentMap<String, String> applicationList;
	ConcurrentMap<String, String> jiraRoleList;
	
	public ConcurrentMap<Long , String> getNonPrivilageRoleList() {
		return nonPrivilageRoleList;
	}
	public void setNonPrivilageRoleList(
			ConcurrentMap<Long , String> nonPrivilageRoleList) {
		this.nonPrivilageRoleList = nonPrivilageRoleList;
	}
	public ConcurrentMap<String, ConcurrentMap<String, Long>> getRoleList() {
		return roleList;
	}
	public void setRoleList(
			ConcurrentMap<String, ConcurrentMap<String, Long>> roleList) {
		this.roleList = roleList;
	}
	public ConcurrentMap<String, String> getApplicationList() {
		return applicationList;
	}
	public void setApplicationList(ConcurrentMap<String, String> applicationList) {
		this.applicationList = applicationList;
	}
    /**
     * @return the jiraRoleList
     */
    public ConcurrentMap<String, String> getJiraRoleList() {
        return jiraRoleList;
    }
    /**
     * @param jiraRoleList the jiraRoleList to set
     */
    public void setJiraRoleList(ConcurrentMap<String, String> jiraRoleList) {
        this.jiraRoleList = jiraRoleList;
    }
	

	
	
	
}
