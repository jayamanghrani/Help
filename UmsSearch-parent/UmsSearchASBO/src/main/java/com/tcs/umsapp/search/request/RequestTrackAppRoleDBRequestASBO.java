package com.tcs.umsapp.search.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class RequestTrackAppRoleDBRequestASBO extends UmsappRequestObject {

    private static final long serialVersionUID = -1481748569332784164L;

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
	@Override
	public String toString() {
		return "RequestTrackAppRoleDBRequestASBO [requestId=" + requestId
				+ ", userId=" + userId + ", status=" + status + "]";
	}

    
}
