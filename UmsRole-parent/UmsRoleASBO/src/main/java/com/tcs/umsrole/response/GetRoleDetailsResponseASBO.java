package com.tcs.umsrole.response;

import com.tcs.umsrole.vo.cmo.UmsRoleResponseObject;

public class GetRoleDetailsResponseASBO extends UmsRoleResponseObject {

    private static final long serialVersionUID = -5124191866802190620L;

    /** The result. */
    private String resultStatus;
    private String statusMessage;
    private String requestId;

    public String getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(String resultStatus) {
        this.resultStatus = resultStatus;
    }

    /**
     * @return the statusMessage
     */
    public String getStatusMessage() {
        return statusMessage;
    }

    /**
     * @param statusMessage
     *            the statusMessage to set
     */
    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    /**
     * @return the requestId
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * @param requestId
     *            the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
