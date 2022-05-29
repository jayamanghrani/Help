package com.tcs.jira.asbo.response;

import java.util.ArrayList;
import java.util.List;

public class GetUsersRolesResponse {

    private List<String> roles=new ArrayList<>();
    private String status;
    /**
     * @return the roles
     */
    public List<String> getRoles() {
        return roles;
    }
    /**
     * @param roles the roles to set
     */
    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }
    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
