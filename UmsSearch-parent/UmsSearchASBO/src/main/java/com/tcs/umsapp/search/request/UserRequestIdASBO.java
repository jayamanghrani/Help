package com.tcs.umsapp.search.request;

import java.io.Serializable;

public class UserRequestIdASBO implements Serializable {

    private static final long serialVersionUID = -6436389882425352087L;

    private String userId;

    private String requestId;

    private String branchId;

    private String requestBy;

    private String requestDate;

    private String requestReason;

    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
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

    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }

    /**
     * @param branchId
     *            the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    /**
     * @return the requestBy
     */
    public String getRequestBy() {
        return requestBy;
    }

    /**
     * @param requestBy
     *            the requestBy to set
     */
    public void setRequestBy(String requestBy) {
        this.requestBy = requestBy;
    }

    /**
     * @return the requestDate
     */
    public String getRequestDate() {
        return requestDate;
    }

    /**
     * @param requestDate
     *            the requestDate to set
     */
    public void setRequestDate(String requestDate) {
        this.requestDate = requestDate;
    }

    /**
     * @return the requestReason
     */
    public String getRequestReason() {
        return requestReason;
    }

    /**
     * @param requestReason
     *            the requestReason to set
     */
    public void setRequestReason(String requestReason) {
        this.requestReason = requestReason;
    }

    @Override
    public String toString() {
        return "('" + userId + "','" + requestId + "')";
    }

}