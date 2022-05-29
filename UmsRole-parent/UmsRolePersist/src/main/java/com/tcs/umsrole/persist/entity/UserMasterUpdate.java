package com.tcs.umsrole.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="UMS_USR_MST")
public class UserMasterUpdate {
	
	@Id
	@Column(name = "UUM_USR_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private String userId;
	
	@Column(name = "UUM_PERMISSION_LIST")
	private String permissionList;
	
	@Column(name = "UUM_MODIFIED_BY")
	private String umsmodifiedBy;
	
	@Column(name = "UUM_MODIFIED_DATE")
	@Temporal(TemporalType.DATE)
	private Date umsmodifiedDate;
	
	@Column(name = "UUM_END_DATE")
	@Temporal(TemporalType.DATE)
	private Date umsendDate;
	
	@Column(name = "UUM_STATUS")
	private String uumsStatus;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(String permissionList) {
		this.permissionList = permissionList;
	}

	public String getModifiedBy() {
		return umsmodifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.umsmodifiedBy = modifiedBy;
	}

	public Date getModifiedDate() {
		return umsmodifiedDate;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.umsmodifiedDate = modifiedDate;
	}

	public Date getEndDate() {
		return umsendDate;
	}

	public void setEndDate(Date endDate) {
		this.umsendDate = endDate;
	}

	public String getUumsStatus() {
		return uumsStatus;
	}

	public void setUumsStatus(String uumsStatus) {
		this.uumsStatus = uumsStatus;
	}
	
	
	
	

}
