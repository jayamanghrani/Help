package com.tcs.umsrole.so.request;

public class RoleDetailsRequestSO {
    private String roleID;
    private String roleName;
    private String roleDesc;
    private String applStatus;
    private String rolePrivilege;
	public String getRoleID() {
		return roleID;
	}
	public void setRoleID(String roleID) {
		this.roleID = roleID;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleDesc() {
		return roleDesc;
	}
	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}
	public String getApplStatus() {
		return applStatus;
	}
	public void setApplStatus(String applStatus) {
		this.applStatus = applStatus;
	}
	public String getRolePrivilege() {
		return rolePrivilege;
	}
	public void setRolePrivilege(String rolePrivilege) {
		this.rolePrivilege = rolePrivilege;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RoleDetailsRequestSO [roleID=");
		builder.append(roleID);
		builder.append(", roleName=");
		builder.append(roleName);
		builder.append(", roleDesc=");
		builder.append(roleDesc);
		builder.append(", applStatus=");
		builder.append(applStatus);
		builder.append(", rolePrivilege=");
		builder.append(rolePrivilege);
		builder.append("]");
		return builder.toString();
	}
    
    
}
