package com.tcs.umsapp.search.commons;

public class UserSearchPermission {
    private String userId;
    private String UserPermissionList;

    @Override
    public String toString() {
        return "UserSearchPermission [userId=" + userId
                + ", UserPermissionList=" + UserPermissionList + "]";
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
     * @return the userPermissionList
     */
    public String getUserPermissionList() {
        return UserPermissionList;
    }

    /**
     * @param userPermissionList
     *            the userPermissionList to set
     */
    public void setUserPermissionList(String userPermissionList) {
        UserPermissionList = userPermissionList;
    }

}
