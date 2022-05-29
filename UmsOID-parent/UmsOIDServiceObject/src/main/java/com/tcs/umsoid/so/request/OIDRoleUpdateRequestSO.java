package com.tcs.umsoid.so.request;

import com.tcs.umsoid.vo.cmo.UmsappRequestObject;

public class OIDRoleUpdateRequestSO extends UmsappRequestObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2757863057835346451L;
	private String requestID;
	private String provisionID;
	public OIDRoleUpdateRequestSO() {
		super();
	}
	public OIDRoleUpdateRequestSO(String requestID, String provisionID) {
		super();
		this.requestID = requestID;
		this.provisionID = provisionID;
	}
	public String getRequestID() {
		return requestID;
	}
	public void setRequestID(String requestID) {
		this.requestID = requestID;
	}
	public String getProvisionID() {
		return provisionID;
	}
	public void setProvisionID(String provisionID) {
		this.provisionID = provisionID;
	}
	@Override
	public String toString() {
		return "OIDRoleUpdateRequestSO [requestID=" + requestID
				+ ", provisionID=" + provisionID + "]";
	}

}
