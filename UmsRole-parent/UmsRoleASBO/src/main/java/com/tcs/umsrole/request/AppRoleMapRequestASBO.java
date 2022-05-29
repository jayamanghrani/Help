package com.tcs.umsrole.request;

import java.util.Date;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class AppRoleMapRequestASBO extends UmsRoleRequestObject{
	
	private String userId;
	private String appId;
	private String requestId;
	private String roleId;
	private String provId;
	private String createdBy;
	private String modifiedBy;
	private String actionDo;
	private String branchId;
	private Date endDate;
	private Date startDate;
	private String roleName;
	
	
    public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public String getBranchId() {
		return branchId;
	}
	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
	public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getAppId() {
        return appId;
    }
    public void setAppId(String appId) {
        this.appId = appId;
    }
    public String getRequestId() {
        return requestId;
    }
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public String getModifiedBy() {
        return modifiedBy;
    }
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    public String getActionDo() {
        return actionDo;
    }
    public void setActionDo(String actionDo) {
        this.actionDo = actionDo;
    }
    public String getProvId() {
        return provId;
    }
    public void setProvId(String provId) {
        this.provId = provId;
    }
    public String getRoleId() {
        return roleId;
    }
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }
    
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	@Override
	public String toString() {
		return "AppRoleMapRequestASBO [userId=" + userId + ", appId=" + appId
				+ ", requestId=" + requestId + ", roleId=" + roleId
				+ ", provId=" + provId + ", createdBy=" + createdBy
				+ ", modifiedBy=" + modifiedBy + ", actionDo=" + actionDo
				+ ", branchId=" + branchId + ", endDate=" + endDate
				+ ", startDate=" + startDate + "]";
	}
 
	

}
