package com.tcs.umsapp.search.so.request;

import java.util.List;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class UserRoleSearchRequestXLSSO extends UmsappRequestObject {

    private static final long serialVersionUID = -4239696593185400279L;

    private List<String> userId;

    /**
     * @return the userId
     */
    public List<String> getUserId() {
        return userId;
    }

    /**
     * @param userId
     *            the userId to set
     */
    public void setUserId(List<String> userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserRoleSearchRequestXLSSO [userId=" + userId + "]";
    }

}