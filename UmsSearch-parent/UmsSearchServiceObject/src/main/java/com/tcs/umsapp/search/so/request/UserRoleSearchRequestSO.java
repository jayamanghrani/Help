package com.tcs.umsapp.search.so.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class UserRoleSearchRequestSO extends UmsappRequestObject {

	private static final long serialVersionUID = -4239696593185400279L;
	
	private String userId;
	private String branchId;


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
		return "UserRoleSearchRequestSO [userId=" + userId + ", branchId="
				+ branchId + "]";
	}

}
