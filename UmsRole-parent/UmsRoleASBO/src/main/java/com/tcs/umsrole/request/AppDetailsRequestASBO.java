package com.tcs.umsrole.request;
import java.util.List;

import com.tcs.umsrole.vo.cmo.UmsRoleRequestObject;

public class AppDetailsRequestASBO extends UmsRoleRequestObject{
    private String userID;
    private String applicationID;
    private String applicationName;
    private String applicationDesc;
    private List<RoleDetailsRequestASBO> roleDetailsRequestASBO;
    private String actionDo;
    
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AppDetailsRequestASBO [userID=");
		builder.append(userID);
		builder.append(", applicationID=");
		builder.append(applicationID);
		builder.append(", applicationName=");
		builder.append(applicationName);
		builder.append(", applicationDesc=");
		builder.append(applicationDesc);
		builder.append(", roleDetailsRequestSO=");
		builder.append(roleDetailsRequestASBO);
		builder.append(", actionDo=");
		builder.append(actionDo);
		builder.append("]");
		return builder.toString();
	}
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
	public List<RoleDetailsRequestASBO> getRoleDetailsRequestASBO() {
		return roleDetailsRequestASBO;
	}
	public void setRoleDetailsRequestASBO(
			List<RoleDetailsRequestASBO> roleDetailsRequestASBO) {
		this.roleDetailsRequestASBO = roleDetailsRequestASBO;
	}
	public String getActionDo() {
		return actionDo;
	}
	public void setActionDo(String actionDo) {
		this.actionDo = actionDo;
	}

}
