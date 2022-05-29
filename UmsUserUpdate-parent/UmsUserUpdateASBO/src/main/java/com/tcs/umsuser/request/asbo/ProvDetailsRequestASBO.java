/**
 * 
 */
package com.tcs.umsuser.request.asbo;


/**
 * @author 926814
 *
 */
public class ProvDetailsRequestASBO {
    
    
    private String provUserId;
    private String provApplicationId;
    private String provRoleId;
    private String provAction;
    private String provStatus;
    private Long   provRequestId;
    private String provCreatedBy;
    private String provModifiedBy;
    private String provBranchId;

    public String getProvUserId() {
        return provUserId;
    }
    public void setProvUserId(String provUserId) {
        this.provUserId = provUserId;
    }
    public String getProvApplicationId() {
        return provApplicationId;
    }
    public void setProvApplicationId(String provApplicationId) {
        this.provApplicationId = provApplicationId;
    }
    public String getProvRoleId() {
        return provRoleId;
    }
    public void setProvRoleId(String provRoleId) {
        this.provRoleId = provRoleId;
    }
    public String getProvAction() {
        return provAction;
    }
    public void setProvAction(String provAction) {
        this.provAction = provAction;
    }
    public String getProvStatus() {
        return provStatus;
    }
    public void setProvStatus(String provStatus) {
        this.provStatus = provStatus;
    }
    public Long getProvRequestId() {
        return provRequestId;
    }
    public void setProvRequestId(Long provRequestId) {
        this.provRequestId = provRequestId;
    }
    public String getProvCreatedBy() {
        return provCreatedBy;
    }
    public void setProvCreatedBy(String provCreatedBy) {
        this.provCreatedBy = provCreatedBy;
    }
    public String getProvModifiedBy() {
        return provModifiedBy;
    }
    public void setProvModifiedBy(String provModifiedBy) {
        this.provModifiedBy = provModifiedBy;
    }
    public String getProvBranchId() {
        return provBranchId;
    }
    public void setProvBranchId(String provBranchId) {
        this.provBranchId = provBranchId;
    }
   
    @Override
    public String toString() {
        return "ProvDetailsRequestASBO [provUserId=" + provUserId
                + ", provApplicationId=" + provApplicationId + ", provRoleId="
                + provRoleId + ", provAction=" + provAction + ", provStatus="
                + provStatus + ", provRequestId=" + provRequestId
                + ", provCreatedBy=" + provCreatedBy + ", provModifiedBy="
                + provModifiedBy + ", provBranchId=" + provBranchId + "]";
    }


}
