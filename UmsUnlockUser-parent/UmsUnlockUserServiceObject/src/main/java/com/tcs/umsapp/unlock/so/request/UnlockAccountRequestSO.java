package com.tcs.umsapp.unlock.so.request;

import com.tcs.umsapp.general.vo.cmo.UmsappRequestObject;

public class UnlockAccountRequestSO extends UmsappRequestObject{

	private static final long serialVersionUID = -5226916919138131940L;
	
	private String userId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
