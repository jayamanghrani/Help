package com.tcs.umsapp.osb.request;

public class OSBRequestSO {

	private Long provisionId;

	private String provisionStatus;

	private String provisionDate;

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

	public String getProvisionDate() {
		return provisionDate;
	}

	public void setProvisionDate(String provisionDate) {
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
		return "OSBRequestSO [provisionId=" + provisionId
				+ ", provisionStatus=" + provisionStatus + ", provisionDate="
				+ provisionDate + ", errorNo=" + errorNo + ", errorDec="
				+ errorDec + ", remark=" + remark + "]";
	}
	
	

}
