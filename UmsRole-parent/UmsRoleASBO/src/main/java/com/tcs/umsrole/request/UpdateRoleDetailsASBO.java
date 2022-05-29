package com.tcs.umsrole.request;

import java.util.ArrayList;
import java.util.List;


public class UpdateRoleDetailsASBO {
    private String userId;
    private String reason;
    private String branchId;
    private String pLId;
    private String permission;
    private String requestBy;
    private List<String> removedBranches;
    
    
    
    public List<String> getRemovedBranches() {
		return removedBranches;
	}

	public void setRemovedBranches(List<String> removedBranches) {
		this.removedBranches = removedBranches;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    public String getpLId() {
        return pLId;
    }

    public void setpLId(String pLId) {
        this.pLId = pLId;
    }
    

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }


    private List<RoleDetailsASBO> userRoles=new ArrayList<>();

    public List<RoleDetailsASBO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleDetailsASBO> userRoles) {
        this.userRoles = userRoles;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }


}
