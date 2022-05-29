package com.tcs.umsrole.request;

import java.util.List;

public class ProvisionDetailsRequestASBO {
	
	private List<String> provUserId;
	private List<String> provApplicationId;
	private List<String> provRoleId;
	private List<String> provAction;
	private List<String> provStatus;
	private List<String> provRequestId;
	private List<String> provCreatedBy;
	private List<String> provModifiedBy;
	private List<String> provBranchId;
	private String PL;
	public List<String> getProvUserId() {
		return provUserId;
	}
	public void setProvUserId(List<String> provUserId) {
		this.provUserId = provUserId;
	}
	public List<String> getProvApplicationId() {
		return provApplicationId;
	}
	public void setProvApplicationId(List<String> provApplicationId) {
		this.provApplicationId = provApplicationId;
	}
	public List<String> getProvRoleId() {
		return provRoleId;
	}
	public void setProvRoleId(List<String> provRoleId) {
		this.provRoleId = provRoleId;
	}
	public List<String> getProvAction() {
		return provAction;
	}
	public void setProvAction(List<String> provAction) {
		this.provAction = provAction;
	}
	public List<String> getProvStatus() {
		return provStatus;
	}
	public void setProvStatus(List<String> provStatus) {
		this.provStatus = provStatus;
	}
	public List<String> getProvRequestId() {
		return provRequestId;
	}
	public void setProvRequestId(List<String> provRequestId) {
		this.provRequestId = provRequestId;
	}
	public List<String> getProvCreatedBy() {
		return provCreatedBy;
	}
	public void setProvCreatedBy(List<String> provCreatedBy) {
		this.provCreatedBy = provCreatedBy;
	}
	public List<String> getProvModifiedBy() {
		return provModifiedBy;
	}
	public void setProvModifiedBy(List<String> provModifiedBy) {
		this.provModifiedBy = provModifiedBy;
	}
	public List<String> getProvBranchId() {
		return provBranchId;
	}
	public void setProvBranchId(List<String> provBranchId) {
		this.provBranchId = provBranchId;
	}
	public String getPL() {
		return PL;
	}
	public void setPL(String pL) {
		PL = pL;
	}
	@Override
	public String toString() {
		return "ProvisionDetailsRequestASBO [provUserId=" + provUserId
				+ ", provApplicationId=" + provApplicationId + ", provRoleId="
				+ provRoleId + ", provAction=" + provAction + ", provStatus="
				+ provStatus + ", provRequestId=" + provRequestId
				+ ", provCreatedBy=" + provCreatedBy + ", provModifiedBy="
				+ provModifiedBy + ", provBranchId=" + provBranchId + ", PL="
				+ PL + "]";
	}


}
