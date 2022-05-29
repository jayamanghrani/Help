package com.tcs.umsapp.search.commons;

import java.io.Serializable;

/*This bean is for mapping result set of service: requestTrackerDetail*/

public class RequestTrackDetail implements Serializable {

    private static final long serialVersionUID = 1L;

    private String requestId;
    private String userId;
    private String branchId;
    private String requestBy;
    private String requestDate;
    private String requestReason;

    /**
	 * 
	 */
    public RequestTrackDetail() {

    }

    /**
     * 
     * @param requestId
     * @param userId
     * @param branchId
     * @param requestBy
     * @param requestDate
     * @param requestReason
     */
    public RequestTrackDetail(String requestId, String userId, String branchId,
            String requestBy, String requestDate, String requestReason) {
        super();
        this.requestId = requestId;
        this.userId = userId;
        this.branchId = branchId;
        this.requestBy = requestBy;
        this.requestDate = requestDate;
        this.requestReason = requestReason;
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

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();
        builder.append("RequestTrackDetail [requestId=");
        builder.append(requestId);
        builder.append(", userId=");
        builder.append(userId);
        builder.append(", branchId=");
        builder.append(branchId);
        builder.append(", requestBy=");
        builder.append(requestBy);
        builder.append(", requestDate=");
        builder.append(requestDate);
        builder.append(", requestReason=");
        builder.append(requestReason);
        builder.append("]");

        return super.toString();
    }

}
