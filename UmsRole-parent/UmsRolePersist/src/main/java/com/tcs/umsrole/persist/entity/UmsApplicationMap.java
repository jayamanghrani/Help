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
@Table(name="UMS_USR_APPL_MAP")

public class UmsApplicationMap {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "UMS_EMP_APPL_MAP_ID")
	@SequenceGenerator(sequenceName = "SEQ_UMS_EMP_APPL_MAP_ID", allocationSize = 1, name = "UMS_EMP_APPL_MAP_ID")
	@Column(name = "UUAM_APPL_MAP_ID", unique = true)
	private Long appMapId;
	
	@Column(name = "UUAM_USR_ID")
	private String appMapUserId;
	
	@Column(name = "UUAM_APPL_ID")
	private String appMapappId;
	
	@Column(name = "UUAM_START_DATE")
	  @Temporal(TemporalType.DATE)
	private Date appMapStartDate;
	
	@Column(name = "UUAM_END_DATE")
    @Temporal(TemporalType.DATE)
	private Date appMapEndDate;
	
	@Column(name = "UUAM_REQUEST_ID")
	private String appMapRequestId;
	
	@Column(name = "UUAM_CREATED_BY")
	private String appMapCreatedBy;
	
	@Column(name = "UUAM_CREATED_DATE")
	  @Temporal(TemporalType.DATE)
	private Date appMapCreatedDate;
	
	@Column(name = "UUAM_MODIFIED_BY")
	private String appMapModifiedBy;
	
	@Column(name = "UUAM_MODIFIED_DATE")
	  @Temporal(TemporalType.DATE)
	private Date appMapModifiedDate;
	
	@Column(name = "UUAM_APPL_APPROVED_STATUS")
	private String appMapApprovedStatus;

	public UmsApplicationMap(){
		
		super();
	}

	public Long getAppMapId() {
		return appMapId;
	}

	public void setAppMapId(Long appMapId) {
		this.appMapId = appMapId;
	}

	public String getAppMapUserId() {
		return appMapUserId;
	}

	public void setAppMapUserId(String appMapUserId) {
		this.appMapUserId = appMapUserId;
	}

	public String getAppMapappId() {
		return appMapappId;
	}

	public void setAppMapappId(String appMapappId) {
		this.appMapappId = appMapappId;
	}

	public Date getAppMapStartDate() {
		return appMapStartDate;
	}

	public void setAppMapStartDate(Date appMapStartDate) {
		this.appMapStartDate = appMapStartDate;
	}

	public Date getAppMapEndDate() {
		return appMapEndDate;
	}

	public void setAppMapEndDate(Date appMapEndDate) {
		this.appMapEndDate = appMapEndDate;
	}

	public String getAppMapRequestId() {
		return appMapRequestId;
	}

	public void setAppMapRequestId(String appMapRequestId) {
		this.appMapRequestId = appMapRequestId;
	}

	public String getAppMapCreatedBy() {
		return appMapCreatedBy;
	}

	public void setAppMapCreatedBy(String appMapCreatedBy) {
		this.appMapCreatedBy = appMapCreatedBy;
	}

	public Date getAppMapCreatedDate() {
		return appMapCreatedDate;
	}

	public void setAppMapCreatedDate(Date appMapCreatedDate) {
		this.appMapCreatedDate = appMapCreatedDate;
	}

	public String getAppMapModifiedBy() {
		return appMapModifiedBy;
	}

	public void setAppMapModifiedBy(String appMapModifiedBy) {
		this.appMapModifiedBy = appMapModifiedBy;
	}

	public Date getAppMapModifiedDate() {
		return appMapModifiedDate;
	}

	public void setAppMapModifiedDate(Date appMapModifiedDate) {
		this.appMapModifiedDate = appMapModifiedDate;
	}

	public String getAppMapApprovedStatus() {
		return appMapApprovedStatus;
	}

	public void setAppMapApprovedStatus(String appMapApprovedStatus) {
		this.appMapApprovedStatus = appMapApprovedStatus;
	}
	
	
}
