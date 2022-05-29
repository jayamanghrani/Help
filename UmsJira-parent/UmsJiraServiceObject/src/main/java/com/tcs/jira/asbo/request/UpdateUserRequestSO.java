/**
 * 
 */
package com.tcs.jira.asbo.request;

/**
 * @author 926814
 *
 */
public class UpdateUserRequestSO {

    public String userId;
    public String name;
    public String city;
    public String mobileNo;
    public String branchId;
    public String issueId;
    public String action;
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }
    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }
    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * @return the city
     */
    public String getCity() {
        return city;
    }
    /**
     * @param city the city to set
     */
    public void setCity(String city) {
        this.city = city;
    }
    /**
     * @return the mobileNo
     */
    public String getMobileNo() {
        return mobileNo;
    }
    /**
     * @param mobileNo the mobileNo to set
     */
    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }
    /**
     * @return the branchId
     */
    public String getBranchId() {
        return branchId;
    }
    /**
     * @param branchId the branchId to set
     */
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }
    /**
     * @return the issueId
     */
    public String getIssueId() {
        return issueId;
    }
    /**
     * @param issueId the issueId to set
     */
    public void setIssueId(String issueId) {
        this.issueId = issueId;
    }
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "UpdateUserRequestSO [userId=" + userId + ", name=" + name
                + ", city=" + city + ", mobileNo=" + mobileNo + ", branchId="
                + branchId + ", issueId=" + issueId + ", action=" + action
                + "]";
    }
   
    
}
