package com.tcs.umsapp.search.commons;

public class RequestTrackDetailXLS {

    private String requestId;
    private String userId;
    private String branchId;
    private String requestBy;
    private String requestDate;
    private String requestReason;
    private String application;
    private String role;
    private String action;
    private String remark;
    private String status;
    private String provisionDate;

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

    /**
     * @return the application
     */
    public String getApplication() {
        return application;
    }

    /**
     * @param application
     *            the application to set
     */
    public void setApplication(String application) {
        this.application = application;
    }

    /**
     * @return the role
     */
    public String getRole() {
        return role;
    }

    /**
     * @param role
     *            the role to set
     */
    public void setRole(String role) {
        this.role = role;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action
     *            the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     *            the remark to set
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the provisionDate
     */
    public String getProvisionDate() {
        return provisionDate;
    }

    /**
     * @param provisionDate
     *            the provisionDate to set
     */
    public void setProvisionDate(String provisionDate) {
        this.provisionDate = provisionDate;
    }

    @Override
    public String toString() {
        return "RequestTrackDetailXLS [requestId=" + requestId + ", userId="
                + userId + ", branchId=" + branchId + ", requestBy="
                + requestBy + ", requestDate=" + requestDate
                + ", requestReason=" + requestReason + ", application="
                + application + ", role=" + role + ", action=" + action
                + ", remark=" + remark + ", status=" + status
                + ", provisionDate=" + provisionDate + "]";
    }

    /**
     * 
     * @param requestId
     * @param userId
     * @param branchId
     * @param requestBy
     * @param requestDate
     * @param requestReason
     * @param application
     * @param role
     * @param action
     * @param remark
     * @param status
     * @param provisionDate
     */
    public RequestTrackDetailXLS(String requestId, String userId,
            String branchId, String requestBy, String requestDate,
            String requestReason, String application, String role,
            String action, String remark, String status, String provisionDate) {
        super();
        this.requestId = requestId;
        this.userId = userId;
        this.branchId = branchId;
        this.requestBy = requestBy;
        this.requestDate = requestDate;
        this.requestReason = requestReason;
        this.application = application;
        this.role = role;
        this.action = action;
        this.remark = remark;
        this.status = status;
        this.provisionDate = provisionDate;
    }

}