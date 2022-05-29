
package com.tcs.umsuser.request;

public class UpdateRoleRequestSO {

    private Integer roleId;
    private String appId;
    private String officeCode;
    private String startDate;
    private String endDate;
    
    
	
	/**
     * @return the roleId
     */
    public Integer getRoleId() {
        return roleId;
    }
    /**
     * @param roleId the roleId to set
     */
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
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
}