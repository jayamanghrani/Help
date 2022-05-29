package com.tcs.umsrole.response;

import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;

public class PermissionListResponseASBO extends UmsRoleResponseObject{
    
    private int response;
private String status;
    public int getResponse() {
        return response;
    }

    public void setResponse(int response) {
        this.response = response;
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
