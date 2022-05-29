package com.tcs.umsapp.upload.commons;

public class UserRoleDetails {
	
	private String roleName;
	private String applicationName;
	private String roleId;
	private String appId;
	private String status;
	
	public UserRoleDetails(String roleName, String applicationName,
			String roleId, String appId, String status) {
		super();
		this.roleName = roleName;
		this.applicationName = applicationName;
		this.roleId = roleId;
		this.appId = appId;
		this.status = status;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "UserRoleDetails [roleName=" + roleName + ", applicationName="
				+ applicationName + ", roleId=" + roleId + ", appId=" + appId
				+ ", status=" + status + "]";
	}
	
	

	
}
