package com.tcs.umsrole.persist.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;



@Entity
@Table(name="UMS_PROV_LOG")
public class ProvisionLog {

	@Id
	@GenericGenerator(name="REQ_LOG_SEQ_DT", strategy="com.tcs.umsrole.persist.entity.StringKeyGenerator")
	@GeneratedValue(generator = "REQ_LOG_SEQ_DT")
	@SequenceGenerator(sequenceName = "SEQ_UMS_PROV_LOG_ID", allocationSize = 1, name = "REQ_LOG_SEQ_DT")
	@Column(name = "UPL_LOG_ID", nullable = false)
	private String logId;
	
	@Column(name = "UPL_PROV_ID")
	private Long provisionId;
	
	@Column(name = "UPL_STATUS")
	private String provisionStatus;
	
	@Column(name = "UPL_ERR_NO")
	private String errorNo;
	
	@Column(name = "UPL_ERR_DESC")
	private String errorDec;
	
	@Column(name = "UPL_REMARK")
	private String remark;
	
	@Column(name = "UPL_CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPL_CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPL_MODIFIED_BY")
	private String modifyBy;
	
	@Column(name = "UPL_MODIFIED_DATE")
	private Date modifyDate;

	public String getLogId() {
		return logId;
	}

	public void setLogId(String logId) {
		this.logId = logId;
	}

	public Long getProvisionId() {
		return provisionId;
	}

	public void setProvisionId(Long provisionId) {
		this.provisionId = provisionId;
	}

	public String getProvisionStatus() {
		return provisionStatus;
	}

	public void setProvisionStatus(String provisionStatus) {
		this.provisionStatus = provisionStatus;
	}

	public String getErrorNo() {
		return errorNo;
	}

	public void setErrorNo(String errorNo) {
		this.errorNo = errorNo;
	}

	public String getErrorDec() {
		return errorDec;
	}

	public void setErrorDec(String errorDec) {
		this.errorDec = errorDec;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	@Override
	public String toString() {
		return "ProvisionLog [logId=" + logId + ", provisionId=" + provisionId
				+ ", provisionStatus=" + provisionStatus + ", errorNo="
				+ errorNo + ", errorDec=" + errorDec + ", remark=" + remark
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate + "]";
	} 
	
	
	
	
}
