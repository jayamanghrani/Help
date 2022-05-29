package com.tcs.umsapp.osb.response;

public class AcknowledgeRequestSO {

	private String provId;

	private String status;

	private String provDate;

	private String sqlCode;

	private String sqlMessage;

	private String remark;

	public String getProvId() {
		return provId;
	}

	public void setProvId(String provId) {
		this.provId = provId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getProvDate() {
		return provDate;
	}

	public void setProvDate(String provDate) {
		this.provDate = provDate;
	}

	public String getSqlCode() {
		return sqlCode;
	}

	public void setSqlCode(String sqlCode) {
		this.sqlCode = sqlCode;
	}

	public String getSqlMessage() {
		return sqlMessage;
	}

	public void setSqlMessage(String sqlMessage) {
		this.sqlMessage = sqlMessage;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String toString() {
		return "AcknowledgeRequestSO [provId=" + provId + ", status=" + status
				+ ", provDate=" + provDate + ", sqlCode=" + sqlCode
				+ ", sqlMessage=" + sqlMessage + ", remark=" + remark + "]";
	}
	
	


	
	
	
	
}
