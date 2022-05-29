package com.tcs.umsrole.request;

import java.util.List;
import java.util.Map;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class UserAppAndRoleAccessDetails extends UmsRoleRequestObject{
	
	private Map<String, List<UserRoleDetails>> appAndRoleAccessDetails ;

	public Map<String, List<UserRoleDetails>> getAppAndRoleAccessDetails() {
		return appAndRoleAccessDetails;
	}

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
