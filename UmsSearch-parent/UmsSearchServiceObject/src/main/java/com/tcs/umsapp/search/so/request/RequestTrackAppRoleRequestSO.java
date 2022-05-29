package com.tcs.umsapp.search.so.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class RequestTrackAppRoleRequestSO extends UmsappRequestObject {

	private static final long serialVersionUID = -6436389882425352087L;

	private String requestId;
	private String userId;
	private String status;
	
	public String getRequestId() {
		return requestId;
	}
	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "RequestTrackAppRoleRequestSO [requestId=" + requestId
				+ ", userId=" + userId + ", status=" + status + "]";
	}
	
	
	
	

}
