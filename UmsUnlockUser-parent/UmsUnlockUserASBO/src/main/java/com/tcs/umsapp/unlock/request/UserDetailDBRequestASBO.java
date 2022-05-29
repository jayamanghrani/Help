package com.tcs.umsapp.unlock.request;

import com.tcs.umsapp.general.vo.cmo.UmsappRequestObject;

public class UserDetailDBRequestASBO extends UmsappRequestObject {


	private static final long serialVersionUID = 3950403389450206838L;
	
	private String userId;
	private String password;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "userDetailDBRequestASBO [userId=" + userId + ", password="
				+ password + "]";
	}

	
	
	
	

}
