package com.tcs.umsrole.request;

public class RoleDetailsASBO {
	private String roleId;
    private String appId;
    private String officeCode;
    private String startDate;
    private String endDate;
    private String action;
    private String appName;
    private String roleName;
    private String userId;
    private String branchId;
    
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


    public String getRoleId() {
        return roleId;
    }
    
    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getAction() {
        return action;
    }
    public void setAction(String action) {
        this.action = action;
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

	@Override
	public String toString() {
		return "RoleDetailsASBO [roleId=" + roleId + ", appId=" + appId
				+ ", officeCode=" + officeCode + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", action=" + action + ", appName="
				+ appName + ", roleName=" + roleName + ", userId=" + userId
				+ ", branchId=" + branchId + "]";
	}
    
}