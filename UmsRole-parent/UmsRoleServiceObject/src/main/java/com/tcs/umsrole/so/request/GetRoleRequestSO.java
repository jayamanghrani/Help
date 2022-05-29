package com.tcs.umsrole.so.request;

import java.util.ArrayList;
import java.util.List;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class GetRoleRequestSO extends UmsRoleRequestObject {
    private String userId;
    private String reason;
    private String branchId;
    private String pLId;
    private String permissionName;
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

    public String getpLId() {
        return pLId;
    }

    public void setpLId(String pLId) {
        this.pLId = pLId;
    }

     

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
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

    @Override
    public String toString() {
        return "GetRoleRequestSO [userId=" + userId + ", reason=" + reason
                + ", branchId=" + branchId + ", pLId=" + pLId
                + ", premissionName=" + permissionName + ", requestBy="
                + requestBy + ", userRoles=" + userRoles + "]";
    }
    
 }
