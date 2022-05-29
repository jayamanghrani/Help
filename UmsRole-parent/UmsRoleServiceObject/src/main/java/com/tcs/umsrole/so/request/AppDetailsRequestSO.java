package com.tcs.umsrole.so.request;

import java.util.List;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class AppDetailsRequestSO extends UmsRoleRequestObject{
    private String userID;
    private String applicationID;
    private String applicationName;
    private String applicationDesc;
    private List<RoleDetailsRequestSO> roleDetailsRequestSO;
    private String actionDo;
	public String getUserID() {
		return userID;
	}
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getApplicationID() {
		return applicationID;
	}
	public void setApplicationID(String applicationID) {
		this.applicationID = applicationID;
	}
	public String getApplicationName() {
		return applicationName;
	}
	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}
	public String getApplicationDesc() {
		return applicationDesc;
	}
	public void setApplicationDesc(String applicationDesc) {
		this.applicationDesc = applicationDesc;
	}
	public List<RoleDetailsRequestSO> getRoleDetailsRequestSO() {
		return roleDetailsRequestSO;
	}
	public void setRoleDetailsRequestSO(
			List<RoleDetailsRequestSO> roleDetailsRequestSO) {
		this.roleDetailsRequestSO = roleDetailsRequestSO;
	}
	public String getActionDo() {
		return actionDo;
	}
	public void setActionDo(String actionDo) {
		this.actionDo = actionDo;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppDetailsRequestSO [userID=");
		builder.append(userID);
		builder.append(", applicationID=");
		builder.append(applicationID);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", applicationDesc=");
		builder.append(applicationDesc);
		builder.append(", roleDetailsRequestSO=");
		builder.append(roleDetailsRequestSO);
		builder.append(", actionDo=");
		builder.append(actionDo);
		builder.append("]");
		return builder.toString();
	}
    
    
    
    
}
