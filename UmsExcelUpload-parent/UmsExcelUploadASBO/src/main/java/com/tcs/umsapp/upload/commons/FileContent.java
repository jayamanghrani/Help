package com.tcs.umsapp.upload.commons;
public class FileContent {
	private String userId;
	private String branchId; 
    private String appName;
    private String roleName;
    private String action;
    private String appId;
    private String roleId;
	 
	

	public FileContent(String userId,String branchId, String appName, String roleName,
			String action, String appId, String roleId) {
		super();
		this.userId = userId;
		this.branchId = branchId;
		this.appName = appName;
		this.roleName = roleName;
		this.action = action;
		this.appId = appId;
		this.roleId = roleId;
		
	}
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
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


	@Override
	public String toString() {
		return "FileContent [userId=" + userId + ", branchId=" + branchId
				+ ", appName=" + appName + ", roleName=" + roleName
				+ ", action=" + action + ", appId=" + appId + ", roleId="
				+ roleId + "]";
	}

	
}
 