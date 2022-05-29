package com.tcs.umsapp.search.commons;

public class UserRoleInPendingDetail {

    private String appName;
    private String roleName;
    private String appId;
    private String roleId;
    private String status;

    /**
     * 
     * @param appName
     * @param roleName
     * @param appId
     * @param roleId
     * @param status
     */
    public UserRoleInPendingDetail(String appName, String roleName,
            String appId, String roleId, String status) {
        super();
        this.appName = appName;
        this.roleName = roleName;
        this.appId = appId;
        this.roleId = roleId;
        this.status = status;
    }

    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }

    /**
     * @param appName
     *            the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
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

    /**
	 * 
	 */
    // For key in Map
    public int hashCode() {
        int hashcode = 0;
        hashcode = appId.hashCode() * roleId.hashCode();
        return hashcode;
    }

    /**
	 * 
	 */
    public boolean equals(Object obj) {
        if (obj instanceof UserRoleInPendingDetail) {
            UserRoleInPendingDetail dtls = (UserRoleInPendingDetail) obj;
            return (dtls.appId.equals(this.appId) && dtls.roleId == this.roleId);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "[appName=" + appName + ", roleName=" + roleName + ", appId="
                + appId + ", roleId=" + roleId + ", status=" + status + "]";
    }

}
