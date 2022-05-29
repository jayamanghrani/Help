package com.tcs.umsapp.osb.db.request;

import java.util.Date;

public class OSBDBRequestASBO {
	
	private Long provisionId;
	
	private String provisionStatus;
	
	private Date provisionDate;
	
	private String errorNo;
	
	private String errorDec;
	
	private String remark;

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

	public Date getProvisionDate() {
		return provisionDate;
	}

	public void setProvisionDate(Date provisionDate) {
		this.provisionDate = provisionDate;
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

	@Override
	public String toString() {
		return "OSBDBRequestASBO [provisionId=" + provisionId
				+ ", provisionStatus=" + provisionStatus + ", provisionDate="
				+ provisionDate + ", errorNo=" + errorNo + ", errorDec="
				+ errorDec + ", remark=" + remark + "]";
	}
	
	
	

}
