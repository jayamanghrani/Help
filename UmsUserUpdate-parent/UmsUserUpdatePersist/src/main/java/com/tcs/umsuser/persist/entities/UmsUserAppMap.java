package com.tcs.umsuser.persist.entities;

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
@Table(name="UMS_USR_APPL_MAP")
public class UmsUserAppMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UMS_APP_MAP_ID")
	@SequenceGenerator(sequenceName = "seq_ums_emp_appl_map_id", allocationSize = 1, name = "UMS_APP_MAP_ID")
	@Column(name = "UUAM_APPL_MAP_ID" )
	private long appMapId;
	
	@Column(name = "UUAM_USR_ID")
	private String userId;
	
	@Column(name = "UUAM_APPL_ID")
	private String applicationId;
	
	@Column(name = "UUAM_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	
	@Column(name = "UUAM_REQUEST_ID")
	private String requestId;
	
	@Column(name = "UUAM_CREATED_BY")
	private String createdBy;
	
	@Column(name = "UUAM_MODIFIED_BY")
	private String modifiedBy;
	
	@Column(name ="UUAM_APPL_APPROVED_STATUS")
	private String applApprovedStatus;
	
	
	public long getAppMapId() {
		return appMapId;
	}
	public void setAppMapId(long appMapId) {
		this.appMapId = appMapId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	
	public String getApplApprovedStatus() {
		return applApprovedStatus;
	}
	public void setApplApprovedStatus(String applApprovedStatus) {
		this.applApprovedStatus = applApprovedStatus;
	}
	@Override
	public String toString() {
		return "UmsUserAppMap [appMapId=" + appMapId + ", userId=" + userId
				+ ", applicationId=" + applicationId + ", startDate="
				+ startDate + ", requestId=" + requestId + ", createdBy="
				+ createdBy + ", modifiedBy=" + modifiedBy
				+ ", applApprovedStatus=" + applApprovedStatus + "]";
	}
	

	
	
}
