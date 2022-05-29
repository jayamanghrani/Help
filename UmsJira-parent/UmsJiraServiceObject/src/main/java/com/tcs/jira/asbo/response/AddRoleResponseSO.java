package com.tcs.jira.asbo.response;

import com.tcs.jira.vo.cmo.UmsJiraResponseObject;

public class AddRoleResponseSO extends UmsJiraResponseObject{
    private String resultStatus;
    private String statusMessage;
    public String getResultStatus() {
        return resultStatus;
    }
    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }
    public String getStatusMessage() {
        return statusMessage;
    }
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AddRoleResponseSO [resultStatus=" + resultStatus
                + ", statusMessage=" + statusMessage + "]";
    }
    
    
}
