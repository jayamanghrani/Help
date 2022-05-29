package com.tcs.umsapp.search.commons;

public class PermissionDetailsCommmon {
    private String plID;
    private String plName;
    private String plDesc;
    private String stDate;
    private String endDate;
    private String createdBY;
    private String createdDate;
    private String modifiedBY;
    private String modifiedDate;

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
    public String getStDate() {
        return stDate;
    }

    /**
     * @param stDate
     *            the stDate to set
     */
    public void setStDate(String stDate) {
        this.stDate = stDate;
    }

    /**
     * @return the endDate
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param endDate
     *            the endDate to set
     */
    public void setEndDate(String endDate) {
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
    public String getCreatedDate() {
        return createdDate;
    }

    /**
     * @param createdDate
     *            the createdDate to set
     */
    public void setCreatedDate(String createdDate) {
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
    public String getModifiedDate() {
        return modifiedDate;
    }

    /**
     * @param modifiedDate
     *            the modifiedDate to set
     */
    public void setModifiedDate(String modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
	     * 
	     */
    @Override
    public String toString() {
        return "PermissionDetails [plID=" + plID + ", plName=" + plName
                + ", plDesc=" + plDesc + ", stDate=" + stDate + ", endDate="
                + endDate + ", createdBY=" + createdBY + ", createdDate="
                + createdDate + ", modifiedBY=" + modifiedBY
                + ", modifiedDate=" + modifiedDate + "]";
    }

}
