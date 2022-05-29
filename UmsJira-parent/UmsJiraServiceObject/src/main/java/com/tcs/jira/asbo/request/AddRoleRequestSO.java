package com.tcs.jira.asbo.request;

import com.tcs.jira.vo.cmo.UmsJiraRequestObject;

public class AddRoleRequestSO extends UmsJiraRequestObject{

    public String userid;
    public String roleName;
    public String action;
    

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

   
    
}
