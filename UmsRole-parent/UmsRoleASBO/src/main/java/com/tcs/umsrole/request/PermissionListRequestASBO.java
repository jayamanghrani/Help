package com.tcs.umsrole.request;

import java.util.Date;

public class PermissionListRequestASBO {
	
	private String userId;
	private String permissionListId;
	private String provisionId;
	private String permissionCreatedBy;
	private Date permissionendDate;
	private String userpermissionList;
	private String usermasterModifiedBy;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPermissionListId() {
		return permissionListId;
	}
	public void setPermissionListId(String permissionListId) {
		this.permissionListId = permissionListId;
	}
	public String getProvisionId() {
		return provisionId;
	}
	public void setProvisionId(String provisionId) {
		this.provisionId = provisionId;
	}
	public String getPermissionCreatedBy() {
		return permissionCreatedBy;
	}
	public void setPermissionCreatedBy(String permissionCreatedBy) {
		this.permissionCreatedBy = permissionCreatedBy;
	}
	public Date getpermissionendDate() {
		return permissionendDate;
	}
	public void setpermissionendDate(Date permissionendDate) {
		this.permissionendDate = permissionendDate;
	}
	public String getUserpermissionList() {
		return userpermissionList;
	}
	public void setUserpermissionList(String userpermissionList) {
		this.userpermissionList = userpermissionList;
	}
	public String getUsermasterModifiedBy() {
		return usermasterModifiedBy;
	}
	public void setUsermasterModifiedBy(String usermasterModifiedBy) {
		this.usermasterModifiedBy = usermasterModifiedBy;
	}

}
