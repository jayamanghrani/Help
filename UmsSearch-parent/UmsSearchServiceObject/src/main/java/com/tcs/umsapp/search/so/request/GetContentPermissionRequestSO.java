package com.tcs.umsapp.search.so.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class GetContentPermissionRequestSO extends UmsappRequestObject {

    private static final long serialVersionUID = -4662629643177021288L;
    private String userID;
    private String plID;
    private String plName;
    private String branchId;

    public String getBranchId() {
        return branchId;
    }

    public String getPlID() {
        return plID;
    }

    public String getPlName() {
        return plName;
    }

    public String getUserID() {
        return userID;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setPlID(String plID) {
        this.plID = plID;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "GetContentPermissionRequestSO [userID=" + userID + ", plID="
                + plID + ", plName=" + plName + ", branchId=" + branchId + "]";
    }

}
