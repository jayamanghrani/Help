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
@Table(name="UMS_PERM_LIST_HISTORY")

	


public class PermListHistoryUpdate {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "PERM_LIST_HISTORY_ID")
	@SequenceGenerator(sequenceName = "SEQ_PERM_LIST_HISTORY_ID", allocationSize = 1, name = "PERM_LIST_HISTORY_ID")
	@Column(name = "UPLH_REC_ID", unique = true)
	private Long permReocrdId;
	
	@Column(name = "UPLH_USR_ID")
	private String permUserId; 
	
	@Column(name = "UPLH_PL_ID")
	private String permPlId;
	
	@Column(name = "UPLH_PROV_ID")
	private String permProvisionId;   
	
	@Column(name = "UPLH_CREATED_BY")
	private String permCreatedBy;
	
	@Column(name = "UPLH_CREATED_DATE")
	@Temporal(TemporalType.DATE)
	private Date permCreatedDate;
	
	@Column(name = "UPLH_START_DATE")
	@Temporal(TemporalType.DATE)
	private Date permStartDate;
	
	@Column(name = "UPLH_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date permEndDate;

	public PermListHistoryUpdate(String permUserId, String permPlId, String permProvisionId, String permCreatedBy,
			Date permCreatedDate,Date permStartDate, Date permEndDate) {
		super();
		this.permUserId = permUserId;
		this.permPlId = permPlId;
		this.permProvisionId = permProvisionId;
		this.permCreatedBy = permCreatedBy;
		this.permCreatedDate = permCreatedDate;
		this.permStartDate = permStartDate;
		this.permEndDate = permEndDate;
		
	}
	public PermListHistoryUpdate(){
		super();
		
	}

	public String getPermUserId() {
		return permUserId;
	}

	public void setPermUserId(String permUserId) {
		this.permUserId = permUserId;
	}

	public String getPermPlId() {
		return permPlId;
	}

	public void setPermPlId(String permPlId) {
		this.permPlId = permPlId;
	}

	public String getPermProvisionId() {
		return permProvisionId;
	}

	public void setPermProvisionId(String permProvisionId) {
		this.permProvisionId = permProvisionId;
	}

	public String permCreatedDate() {
		return permCreatedBy;
	}

	public void permCreatedDate(Date permCreatedDate) {
		this.permCreatedDate = permCreatedDate;
	}
	
	public String getPermCreatedBy() {
		return permCreatedBy;
	}

	public void setPermCreatedBy(String permCreatedBy) {
		this.permCreatedBy = permCreatedBy;
	}
	

	public Date getPermStartDate() {
		return permStartDate;
	}

	public void setPermStartDate(Date permStartDate) {
		this.permStartDate = permStartDate;
	}

	public Date getPermEndDate() {
		return permEndDate;
	}

	public void setPermEndDate(Date permEndDate) {
		this.permEndDate = permEndDate;
	}
}
