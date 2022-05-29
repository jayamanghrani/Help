package com.tcs.umsapp.upload.commons;

public class AppRoleId {
	
	String appId;
	String roleId;
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public AppRoleId(String appId, String roleId) {
		super();
		this.appId = appId;
		this.roleId = roleId;
	}
	@Override
	public String toString() {
		return "AppRoleId [appId=" + appId + ", roleId=" + roleId + "]";
	}

}
