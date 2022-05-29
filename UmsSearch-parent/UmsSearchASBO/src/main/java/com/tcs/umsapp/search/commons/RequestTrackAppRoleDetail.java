package com.tcs.umsapp.search.commons;

import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class RequestTrackAppRoleDetail extends UmsappResponseObject {

    private static final long serialVersionUID = -5288275480153693388L;
    private String application;
    private String role;
    private String action;
    private String branchId;
    private String status;
    private String provisionDate;
    private String remark;

  
    public RequestTrackAppRoleDetail(String application, String role,
            String action, String status, String provisionDate, String remark, String branchId) {
        super();
        this.application = application;
        this.role = role;
        this.action = action;
        this.status = status;
        this.provisionDate = provisionDate;
        this.remark = remark;
        this.branchId=branchId;
    }


	public String getApplication() {
		return application;
	}


	public void setApplication(String application) {
		this.application = application;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getAction() {
		return action;
	}


	public void setAction(String action) {
		this.action = action;
	}


	public String getBranchId() {
		return branchId;
	}


	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public String getProvisionDate() {
		return provisionDate;
	}


	public void setProvisionDate(String provisionDate) {
		this.provisionDate = provisionDate;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	@Override
	public String toString() {
		return "RequestTrackAppRoleDetail [application=" + application
				+ ", role=" + role + ", action=" + action + ", branchId="
				+ branchId + ", status=" + status + ", provisionDate="
				+ provisionDate + ", remark=" + remark + "]";
	}

    
}
