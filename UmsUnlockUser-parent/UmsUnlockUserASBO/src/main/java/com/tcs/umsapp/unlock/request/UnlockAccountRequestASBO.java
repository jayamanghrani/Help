package com.tcs.umsapp.unlock.request;

import com.tcs.umsapp.general.vo.cmo.UmsappRequestObject;

public class UnlockAccountRequestASBO extends UmsappRequestObject{
		
	private static final long serialVersionUID = -2334777244157874036L;
	
	private String userId;	
	
	private String newPassword;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	@Override
	public String toString() {
		return "unlockAccountRequestASBO [userId=" + userId + ", newPassword="
				+ newPassword + "]";
	}
	
	
	
	
}
