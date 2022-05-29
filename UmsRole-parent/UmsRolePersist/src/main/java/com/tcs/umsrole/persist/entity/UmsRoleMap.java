package com.tcs.umsrole.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "UMS_USR_ROLE_MAP")
public class UmsRoleMap {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "UMS_EMP_ROLE_MAP_ID")
    @SequenceGenerator(sequenceName = "SEQ_UMS_EMP_ROLE_MAP_ID", allocationSize = 1, name = "UMS_EMP_ROLE_MAP_ID")
    @Column(name = "UURM_ROLE_MAP_ID", unique = true)
    private Long roleMapId;

    @Column(name = "UURM_USR_ID")
    private String roleMapUserId;

    @Column(name = "UURM_ROLE_ID")
    private String roleMapRoleId;

    @Column(name = "UURM_START_DATE")
    @Temporal(TemporalType.DATE)
    private Date roleMapStartDate;

    @Column(name = "UURM_END_DATE")
    @Temporal(TemporalType.DATE)
    private Date roleMapEndDate;

    @Column(name = "UURM_REQUEST_ID")
    private String roleMapRequestId;

    @Column(name = "UURM_CREATED_BY")
    private String roleMapCreatedBy;

    @Column(name = "UURM_CREATED_DATE")
    @Temporal(TemporalType.DATE)
    private Date roleMapCreatedDate;

    @Column(name = "UURM_MODIFIED_BY")
    private String roleMapModifiedBy;

    @Column(name = "UURM_MODIFIED_DATE")
    @Temporal(TemporalType.DATE)
    private Date roleMapModifiedDate;

    @Column(name = "UURM_PROV_ID")
    private String roleMapProvId;
    
    @Column(name = "UURM_BRANCH_ID")
    private String  branchId;

    @Column(name = "UURM_APPL_APPROVED_STATUS")
    private String roleMapApproveStatus;

    public UmsRoleMap() {
        super();

    }

    public Long getRoleMapId() {
        return roleMapId;
    }

    public void setRoleMapId(Long roleMapId) {
        this.roleMapId = roleMapId;
    }

    public String getRoleMapUserId() {
        return roleMapUserId;
    }

    public void setRoleMapUserId(String roleMapUserId) {
        this.roleMapUserId = roleMapUserId;
    }

    public String getRoleMapRoleId() {
        return roleMapRoleId;
    }

    public void setRoleMapRoleId(String roleMapRoleId) {
        this.roleMapRoleId = roleMapRoleId;
    }

    public Date getRoleMapStartDate() {
        return roleMapStartDate;
    }

    public void setRoleMapStartDate(Date roleMapStartDate) {
        this.roleMapStartDate = roleMapStartDate;
    }

    public Date getRoleMapEndDate() {
        return roleMapEndDate;
    }

    public void setRoleMapEndDate(Date roleMapEndDate) {
        this.roleMapEndDate = roleMapEndDate;
    }

    public String getRoleMapRequestId() {
        return roleMapRequestId;
    }

    public void setRoleMapRequestId(String roleMapRequestId) {
        this.roleMapRequestId = roleMapRequestId;
    }

    public String getRoleMapCreatedBy() {
        return roleMapCreatedBy;
    }

    public void setRoleMapCreatedBy(String roleMapCreatedBy) {
        this.roleMapCreatedBy = roleMapCreatedBy;
    }

    public Date getRoleMapCreatedDate() {
        return roleMapCreatedDate;
    }

    public void setRoleMapCreatedDate(Date roleMapCreatedDate) {
        this.roleMapCreatedDate = roleMapCreatedDate;
    }

    public String getRoleMapModifiedBy() {
        return roleMapModifiedBy;
    }

    public void setRoleMapModifiedBy(String roleMapModifiedBy) {
        this.roleMapModifiedBy = roleMapModifiedBy;
    }

    public Date getRoleMapModifiedDate() {
        return roleMapModifiedDate;
    }

    public void setRoleMapModifiedDate(Date roleMapModifiedDate) {
        this.roleMapModifiedDate = roleMapModifiedDate;
    }

    public String getRoleMapProvId() {
        return roleMapProvId;
    }

    public void setRoleMapProvId(String roleMapProvId) {
        this.roleMapProvId = roleMapProvId;
    }

    public String getRoleMapApproveStatus() {
        return roleMapApproveStatus;
    }

    public void setRoleMapApproveStatus(String roleMapApproveStatus) {
        this.roleMapApproveStatus = roleMapApproveStatus;
    }
	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
