package com.tcs.umsrole.request;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class GetRoleDetailsRequestASBO extends UmsRoleRequestObject{
		
	private String userId;
	private String requestedBy;
	private String modifyBy;
	private String reason;
	private String PL;
	private String provUserId;
	private String provApplicationId;
	private String provRoleId;
	private String provAction;
	private String provStatus;
	private String provRequestId;
	private String provCreatedBy;
	private String provModifiedBy;
	private String provBranchId;
	private String permissionList;
	private String umsmodifiedBy;
	private String umsmodifiedDate;
	private String umsendDate;
	private String uumsStatus;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getRequestedBy() {
		return requestedBy;
	}
	public void setRequestedBy(String requestedBy) {
		this.requestedBy = requestedBy;
	}
	public String getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getPL() {
		return PL;
	}
	public void setPL(String pL) {
		PL = pL;
	}
	public String getProvUserId() {
		return provUserId;
	}
	public void setProvUserId(String provUserId) {
		this.provUserId = provUserId;
	}
	public String getProvApplicationId() {
		return provApplicationId;
	}
	public void setProvApplicationId(String provApplicationId) {
		this.provApplicationId = provApplicationId;
	}
	public String getProvRoleId() {
		return provRoleId;
	}
	public void setProvRoleId(String provRoleId) {
		this.provRoleId = provRoleId;
	}
	public String getProvAction() {
		return provAction;
	}
	public void setProvAction(String provAction) {
		this.provAction = provAction;
	}
	public String getProvStatus() {
		return provStatus;
	}
	public void setProvStatus(String provStatus) {
		this.provStatus = provStatus;
	}
	public String getProvRequestId() {
		return provRequestId;
	}
	public void setProvRequestId(String provRequestId) {
		this.provRequestId = provRequestId;
	}
	public String getProvCreatedBy() {
		return provCreatedBy;
	}
	public void setProvCreatedBy(String provCreatedBy) {
		this.provCreatedBy = provCreatedBy;
	}
	public String getProvModifiedBy() {
		return provModifiedBy;
	}
	public void setProvModifiedBy(String provModifiedBy) {
		this.provModifiedBy = provModifiedBy;
	}
	public String getProvBranchId() {
		return provBranchId;
	}
	public void setProvBranchId(String provBranchId) {
		this.provBranchId = provBranchId;
	}
	public String getPermissionList() {
		return permissionList;
	}
	public void setPermissionList(String permissionList) {
		this.permissionList = permissionList;
	}
	public String getUmsmodifiedBy() {
		return umsmodifiedBy;
	}
	public void setUmsmodifiedBy(String umsmodifiedBy) {
		this.umsmodifiedBy = umsmodifiedBy;
	}
	public String getUmsmodifiedDate() {
		return umsmodifiedDate;
	}
	public void setUmsmodifiedDate(String umsmodifiedDate) {
		this.umsmodifiedDate = umsmodifiedDate;
	}
	public String getUmsendDate() {
		return umsendDate;
	}
	public void setUmsendDate(String umsendDate) {
		this.umsendDate = umsendDate;
	}
	public String getUumsStatus() {
		return uumsStatus;
	}
	public void setUumsStatus(String uumsStatus) {
		this.uumsStatus = uumsStatus;
	}
    @Override
    public String toString() {
        return "GetRoleDetailsRequestASBO [userId=" + userId + ", requestedBy="
                + requestedBy + ", modifyBy=" + modifyBy + ", reason=" + reason
                + ", PL=" + PL + ", provUserId=" + provUserId
                + ", provApplicationId=" + provApplicationId + ", provRoleId="
                + provRoleId + ", provAction=" + provAction + ", provStatus="
                + provStatus + ", provRequestId=" + provRequestId
                + ", provCreatedBy=" + provCreatedBy + ", provModifiedBy="
                + provModifiedBy + ", provBranchId=" + provBranchId
                + ", permissionList=" + permissionList + ", umsmodifiedBy="
                + umsmodifiedBy + ", umsmodifiedDate=" + umsmodifiedDate
                + ", umsendDate=" + umsendDate + ", uumsStatus=" + uumsStatus
                + "]";
    }

	
}
