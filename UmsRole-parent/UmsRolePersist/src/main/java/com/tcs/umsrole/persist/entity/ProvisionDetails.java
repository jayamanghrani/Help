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
@Table(name="UMS_PROV_DTLS")
public class ProvisionDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UMS_PROV_DTLS_ID")
	@SequenceGenerator(sequenceName = "SEQ_UMS_PROV_DTLS_ID", allocationSize = 1, name = "UMS_PROV_DTLS_ID")
	@Column(name = "UPD_PROV_ID" )
	private Long provId;
	
	public Long getProvId() {
        return provId;
    }


    public void setProvId(Long provId) {
        this.provId = provId;
    }


    @Column(name = "UPD_USR_ID")
	private String provUserId;
	
	@Column(name = "UPD_APPL_ID")
	private String provApplicationId;
	
	@Column(name = "UPD_ROLE_ID")
	private String provRoleId;
	
	@Column(name = "UPD_PROV_ACTION")
	private String provAction;
	
	@Column(name = "UPD_PROV_STATUS")
	private String provStatus;
	
	@Column(name = "UPD_PROV_DATE")
	@Temporal(TemporalType.DATE)
	private Date provDate;
	
	@Column(name = "UPD_REQUEST_ID")
	private String provRequestId;
	
	@Column(name = "UPD_CREATED_BY")
	private String provCreatedBy;
	
	@Column(name = "UPD_CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date provCreatedDate;
	
	@Column(name = "UPD_MODIFIED_BY")
	private String provModifiedBy;
	
	@Column(name = "UPD_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date provModifiedDate;
	
	@Column(name = "UPD_BRANCH_ID")
	private String provBranchId;


    
	
//	public ProvisionDetails(String provUserId,String provApplicationId,String provRoleId,
//			String provAction, String provStatus,Date provDate, String provRequestId,String provCreatedBy,
//			Date provCreatedDate,String provModifiedBy, Date provModifiedDate, String provBranchId){
//			super();
//			
//			this.provUserId = provUserId;
//			this.provApplicationId = provApplicationId;
//			this.provRoleId= provRoleId;
//			this.provAction = provAction;
//			this.provStatus = provStatus;
//			this.provDate = provDate;
//			this.provRequestId = provRequestId;
//			this.provCreatedBy = provCreatedBy;
//			this.provCreatedDate = provCreatedDate;
//			this.provModifiedBy = provModifiedBy;
//			this.provModifiedDate = provModifiedDate;
//			this.provBranchId = provBranchId;
//
//
//			
//	}

	
	public ProvisionDetails(){
		
		super();
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

	public Date getProvDate() {
		return provDate;
	}

	public void setProvDate(Date provDate) {
		this.provDate = provDate;
	}

	public String getProvRequestId() {
		return provRequestId;
	}

	public void setProvRequestId(String provRequestId) {
		this.provRequestId = provRequestId;
	}

	public String getProvCreatedBy() {
		return provCreatedBy;
	}

	public void setProvCreatedBy(String provCreatedBy) {
		this.provCreatedBy = provCreatedBy;
	}

	public Date getProvCreatedDate() {
		return provCreatedDate;
	}

	public void setProvCreatedDate(Date provCreatedDate) {
		this.provCreatedDate = provCreatedDate;
	}

	public String getProvModifiedBy() {
		return provModifiedBy;
	}

	public void setProvModifiedBy(String provModifiedBy) {
		this.provModifiedBy = provModifiedBy;
	}

	public Date getProvModifiedDate() {
		return provModifiedDate;
	}

	public void setProvModifiedDate(Date provModifiedDate) {
		this.provModifiedDate = provModifiedDate;
	}

	public String getProvBranchId() {
		return provBranchId;
	}

	public void setProvBranchId(String provBranchId) {
		this.provBranchId = provBranchId;
	}


	public String getProvUserId() {
        return provUserId;
    }


    @Override
	public String toString() {
		return "ProvisionDetails [provId=" + provId + ", provUserId="
				+ provUserId + ", provApplicationId=" + provApplicationId
				+ ", provRoleId=" + provRoleId + ", provAction=" + provAction
				+ ", provStatus=" + provStatus + ", provDate=" + provDate
				+ ", provRequestId=" + provRequestId + ", provCreatedBy="
				+ provCreatedBy + ", provCreatedDate=" + provCreatedDate
				+ ", provModifiedBy=" + provModifiedBy + ", provModifiedDate="
				+ provModifiedDate + ", provBranchId=" + provBranchId + "]";
	}
	
	
	
	
	
}
