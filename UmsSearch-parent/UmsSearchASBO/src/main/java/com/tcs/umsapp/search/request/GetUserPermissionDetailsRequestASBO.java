package com.tcs.umsapp.search.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class GetUserPermissionDetailsRequestASBO extends UmsappRequestObject {

    private static final long serialVersionUID = -4662629643177021288L;
    private String userID;
    private String plID;
    private String plName;
    private String branchID;

    /**
     * 
     * @param userID
     * @param plID
     * @param plName
     * @param branchID
     */
    public GetUserPermissionDetailsRequestASBO(String userID, String plID,
            String plName, String branchID) {
        super();
        this.userID = userID;
        this.plID = plID;
        this.plName = plName;
        this.branchID = branchID;
    }

    public GetUserPermissionDetailsRequestASBO() {

    }

    /**
     * @return the userID
     */
    public String getUserID() {
        return userID;
    }

    /**
     * @param userID
     *            the userID to set
     */
    public void setUserID(String userID) {
        this.userID = userID;
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
     * @return the branchID
     */
    public String getBranchID() {
        return branchID;
    }

    /**
     * @param branchID
     *            the branchID to set
     */
    public void setBranchID(String branchID) {
        this.branchID = branchID;
    }

}
