package com.tcs.umsapp.search.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class RequestTrackDBReqeustASBO extends UmsappRequestObject {

    private static final long serialVersionUID = 1L;

    private String requestId;
    private String requestBy;
    private String userId;
    private String requestStatus;
    private String application;
    private String requestDateFrom;
    private String requestDateTo;

    public String getRequestId() {
        return requestId;
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
     * @return the requestStatus
     */
    public String getRequestStatus() {
        return requestStatus;
    }

    /**
     * @param requestStatus
     *            the requestStatus to set
     */
    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
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
     * @return the requestDateFrom
     */
    public String getRequestDateFrom() {
        return requestDateFrom;
    }

    /**
     * @param requestDateFrom
     *            the requestDateFrom to set
     */
    public void setRequestDateFrom(String requestDateFrom) {
        this.requestDateFrom = requestDateFrom;
    }

    /**
     * @return the requestDateTo
     */
    public String getRequestDateTo() {
        return requestDateTo;
    }

    /**
     * @param requestDateTo
     *            the requestDateTo to set
     */
    public void setRequestDateTo(String requestDateTo) {
        this.requestDateTo = requestDateTo;
    }

    /**
     * @param requestId
     *            the requestId to set
     */
    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "RequestTrackDBReqeustASBO [requestId=" + requestId
                + ", requestBy=" + requestBy + ", userId=" + userId
                + ", requestStatus=" + requestStatus + ", application="
                + application + ", requestDateFrom=" + requestDateFrom
                + ", requestDateTo=" + requestDateTo + "]";
    }

}
