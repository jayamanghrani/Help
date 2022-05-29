package com.tcs.umsapp.search.commons;

public class UserIdDetails {

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
        return "UserIdDetails [userId=" + userId + "]";
    }

}
