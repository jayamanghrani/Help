package com.tcs.umsapp.search.commons;


public class UserRoleDetailsXLS {

    private String userId;
    private String branchId;
    private String firstName;
    private String lastName;
    private String roleName;
    private String applicationName;
    private String roleId;
    private String appId;
    private String status;

    /**
     * 
     * @param userId
     * @param branchId
     * @param firstName
     * @param lastName
     * @param roleName
     * @param applicationName
     * @param roleId
     * @param appId
     * @param status
     */
    public UserRoleDetailsXLS(String userId, String branchId, String firstName,
            String lastName, String roleName, String applicationName,
            String roleId, String appId, String status) {
        super();
        this.userId = userId;
        this.branchId = branchId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.roleName = roleName;
        this.applicationName = applicationName;
        this.roleId = roleId;
        this.appId = appId;
        this.status = status;
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
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName
     *            the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName
     *            the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the roleName
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     *            the roleName to set
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return the applicationName
     */
    public String getApplicationName() {
        return applicationName;
    }

    /**
     * @param applicationName
     *            the applicationName to set
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }

    /**
     * @return the roleId
     */
    public String getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     *            the roleId to set
     */
    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    /**
     * @return the appId
     */
    public String getAppId() {
        return appId;
    }

    /**
     * @param appId
     *            the appId to set
     */
    public void setAppId(String appId) {
        this.appId = appId;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status
     *            the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "UserRoleDetailsXLS [userId=" + userId + ", branchId="
                + branchId + ", firstName=" + firstName + ", lastName="
                + lastName + ", roleName=" + roleName + ", applicationName="
                + applicationName + ", roleId=" + roleId + ", appId=" + appId
                + ", status=" + status + "]";
    }

}
