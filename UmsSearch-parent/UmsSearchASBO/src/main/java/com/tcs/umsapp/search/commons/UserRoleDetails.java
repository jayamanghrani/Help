package com.tcs.umsapp.search.commons;

public class UserRoleDetails {

	private String roleName;
    private String applicationName;
    private String roleId;
    private String appId;
    private String status;
    private String officeCode;
    private String startDate;
    private String endDate;
    
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
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	@Override
	public String toString() {
		return "UserRoleDetails [roleName=" + roleName + ", applicationName="
				+ applicationName + ", roleId=" + roleId + ", appId=" + appId
				+ ", status=" + status + ", officeCode=" + officeCode
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
    
	
  
  

}
