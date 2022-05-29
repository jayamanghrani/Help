package com.tcs.umsapp.search.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class UserRoleSearchRequestASBO extends UmsappRequestObject {

    private static final long serialVersionUID = 1991582433307625516L;

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
		return "UserRoleSearchRequestASBO [userId=" + userId + ", branchId="
				+ branchId + "]";
	}

}
