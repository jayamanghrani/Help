package com.tcs.umsapp.search.commons;

import java.util.Date;

public class UserPermissionDetails {
    private String plID;
    private String plName;
    private String plDesc;
    private Date stDate;
    private Date endDate;
    private String createdBY;
    private Date createdDate;
    private String modifiedBY;
    private Date modifiedDate;

    public UserPermissionDetails() {

    }

    @Override
    public String toString() {
        return "UserPermissionDetails [plID=" + plID + ", plName=" + plName
                + ", plDesc=" + plDesc + ", stDate=" + stDate + ", endDate="
                + endDate + ", createdBY=" + createdBY + ", createdDate="
                + createdDate + ", modifiedBY=" + modifiedBY
                + ", modifiedDate=" + modifiedDate + "]";
    }

    /**
     * @param plID
     * @param plName
     * @param plDesc
     * @param stDate
     * @param endDate
     * @param createdBY
     * @param createdDate
     * @param modifiedBY
     * @param modifiedDate
     */
    public UserPermissionDetails(String plID, String plName, String plDesc,
            Date stDate, Date endDate, String createdBY, Date createdDate,
            String modifiedBY, Date modifiedDate) {
        super();
        this.plID = plID;
        this.plName = plName;
        this.plDesc = plDesc;
        this.stDate = stDate;
        this.endDate = endDate;
        this.createdBY = createdBY;
        this.createdDate = createdDate;
        this.modifiedBY = modifiedBY;
        this.modifiedDate = modifiedDate;
    }

    /**
     * @return the plID
     */
    public String getPlID() {
        return plID;
    }

    /**
     * @param plID
     *            the plID to set
     */
    public void setPlID(String plID) {
        this.plID = plID;
    }

    /**
     * @return the plName
     */
    public String getPlName() {
        return plName;
    }

    /**
     * @param plName
     *            the plName to set
     */
    public void setPlName(String plName) {
        this.plName = plName;
    }

    /**
     * @return the plDesc
     */
    public String getPlDesc() {
        return plDesc;
    }

    /**
     * @param plDesc
     *            the plDesc to set
     */
    public void setPlDesc(String plDesc) {
        this.plDesc = plDesc;
    }

    /**
     * @return the stDate
     */
    public Date getStDate() {
        return stDate;
    }

    /**
     * @param stDate
     *            the stDate to set
     */
    public void setStDate(Date stDate) {
        this.stDate = stDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the createdBY
     */
    public String getCreatedBY() {
        return createdBY;
    }

    /**
     * @param createdBY
     *            the createdBY to set
     */
    public void setCreatedBY(String createdBY) {
        this.createdBY = createdBY;
    }

    /**
     * @return the createdDate
     */
    public Date getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * @return the modifiedBY
     */
    public String getModifiedBY() {
        return modifiedBY;
    }

    /**
     * @param modifiedBY
     *            the modifiedBY to set
     */
    public void setModifiedBY(String modifiedBY) {
        this.modifiedBY = modifiedBY;
    }

    /**
     * @return the modifiedDate
     */
    public Date getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate
     *            the modifiedDate to set
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

}
