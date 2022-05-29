package com.tcs.umsuser.request;

import java.util.ArrayList;
import java.util.List;

public class GetRoleRequestSO {
    private String userId;
    private String reason;
    private String branchId;
    private String requestBy;
    private List<String> removedBranches;
    private List<UpdateRoleRequestSO> userRoles=new ArrayList<>();
    
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
 

	public List<String> getRemovedBranches() {
		return removedBranches;
	}

	public void setRemovedBranches(List<String> removedBranches) {
		this.removedBranches = removedBranches;
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

    public List<UpdateRoleRequestSO> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<UpdateRoleRequestSO> userRoles) {
        this.userRoles = userRoles;
    }

    public String getRequestBy() {
        return requestBy;
    }

    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    
    
 }
