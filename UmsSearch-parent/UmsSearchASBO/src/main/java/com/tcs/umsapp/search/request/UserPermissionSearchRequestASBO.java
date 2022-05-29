package com.tcs.umsapp.search.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class UserPermissionSearchRequestASBO extends UmsappRequestObject {

    private static final long serialVersionUID = 1991582433307625516L;

    private String userId;

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

    @Override
    public String toString() {
        return "UserPermissionSearchRequestASBO [userId=" + this.userId + "]";
    }

}
