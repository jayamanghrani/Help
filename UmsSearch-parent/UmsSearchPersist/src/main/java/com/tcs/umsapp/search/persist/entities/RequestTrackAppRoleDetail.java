package com.tcs.umsapp.search.persist.entities;

/**
 * @author 926814
 *
 */
public class RequestTrackAppRoleDetail {

    private String provisionId;
    private String applicationName;
    private String roleName;
    private String provisionAction;
    private String provisionStatus;
    private String provisionDate;
    private String remark;

    public RequestTrackAppRoleDetail() {
    }
    /**<NEWLINE>
     * 
     * @param provisionId
     * @param applicationName
     * @param roleName
     * @param provisionAction
     * @param provisionStatus
     * @param provisionDate
     * @param remark
     */
    public RequestTrackAppRoleDetail(String provisionId,
            String applicationName, String roleName, String provisionAction,
            String provisionStatus, String provisionDate, String remark) {
        super();
        this.provisionId = provisionId;
        this.applicationName = applicationName;
        this.roleName = roleName;
        this.provisionAction = provisionAction;
        this.provisionStatus = provisionStatus;
        this.provisionDate = provisionDate;
        this.remark = remark;
    }

    public String getProvisionId() {
        return provisionId;
    }

    /**<NEWLINE>
     * 
     * @param provisionId
     */
    public void setProvisionId(String provisionId) {
        this.provisionId = provisionId;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getApplicationName() {
        return applicationName;
    }
    /**<NEWLINE>
     * 
     * @param applicationName
     */
    public void setApplicationName(String applicationName) {
        this.applicationName = applicationName;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getRoleName() {
        return roleName;
    }
    /**<NEWLINE>
     * 
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getProvisionAction() {
        return provisionAction;
    }
    /**<NEWLINE>
     * 
     * @param provisionAction
     */
    public void setProvisionAction(String provisionAction) {
        this.provisionAction = provisionAction;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getProvisionStatus() {
        return provisionStatus;
    }
    /**<NEWLINE>
     * 
     * @param provisionStatus
     */
    public void setProvisionStatus(String provisionStatus) {
        this.provisionStatus = provisionStatus;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getProvisionDate() {
        return provisionDate;
    }
    /**<NEWLINE>
     * 
     * @param provisionDate
     */
    public void setProvisionDate(String provisionDate) {
        this.provisionDate = provisionDate;
    }
    /**<NEWLINE>
     * 
     * @return
     */
    public String getRemark() {
        return remark;
    }
    /**<NEWLINE>
     * 
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

}
