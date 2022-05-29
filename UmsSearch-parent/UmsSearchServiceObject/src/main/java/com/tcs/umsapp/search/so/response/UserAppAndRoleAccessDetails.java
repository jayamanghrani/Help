package com.tcs.umsapp.search.so.response;

import java.util.List;
import java.util.Map;

import com.tcs.umsapp.search.commons.UserRoleDetails;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserAppAndRoleAccessDetails extends UmsappResponseObject{
	
	/**
     * 
     */
    private static final long serialVersionUID = 1L;
    private Map<String, List<UserRoleDetails>> appAndRoleAccessDetails ;
    /** 
     * 
     * @return
     */
	public Map<String, List<UserRoleDetails>> getAppAndRoleAccessDetails() {
		return appAndRoleAccessDetails;
	}
	/** 
	 * 
	 * @param appAndRoleAccessDetails
	 */
	public void setAppAndRoleAccessDetails(
			Map<String, List<UserRoleDetails>> appAndRoleAccessDetails) {
		this.appAndRoleAccessDetails = appAndRoleAccessDetails;
	}

	@Override
	public String toString() {
		return "UserAppAndRoleAccessDetails [appAndRoleAccessDetails="
				+ appAndRoleAccessDetails + "]";
	}

	
	
	

}
