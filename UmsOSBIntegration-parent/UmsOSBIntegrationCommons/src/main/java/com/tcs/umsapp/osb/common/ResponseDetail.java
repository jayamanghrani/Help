package com.tcs.umsapp.osb.common;

public class ResponseDetail {

	
	private String provisionId;
	private String status;
	private String provisionDate;
	private String errorNum;
	private String errorMsg;
	private String comments;
	
	public ResponseDetail(){
		
	}
	
	
	
	public ResponseDetail(String provisionId, String status,
			String provisionDate, String errorNum, String errorMsg,
			String comments) {
		super();
		this.provisionId = provisionId;
		this.status = status;
		this.provisionDate = provisionDate;
		this.errorNum = errorNum;
		this.errorMsg = errorMsg;
		this.comments = comments;
	}
	
	
	public String getProvisionId() {
		return provisionId;
	}
	public void setProvisionId(String provisionId) {
		this.provisionId = provisionId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProvisionDate() {
		return provisionDate;
	}
	public void setProvisionDate(String provisionDate) {
		this.provisionDate = provisionDate;
	}
	public String getErrorNum() {
		return errorNum;
	}
	public void setErrorNum(String errorNum) {
		this.errorNum = errorNum;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}


	@Override
	public String toString() {
		return "ResponseDetail [provisionId=" + provisionId + ", status="
				+ status + ", provisionDate=" + provisionDate + ", errorNum="
				+ errorNum + ", errorMsg=" + errorMsg + ", comments="
				+ comments + "]";
	}
	
	
	
}
