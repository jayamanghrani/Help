package com.tcs.umsapp.upload.commons;

import java.util.List;
import java.util.Map;



public class UserAppAndRoleAccessDetails {

	
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
