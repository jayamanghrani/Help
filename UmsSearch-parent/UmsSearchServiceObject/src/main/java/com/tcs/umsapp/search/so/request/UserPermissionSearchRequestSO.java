package com.tcs.umsapp.search.so.request;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class UserPermissionSearchRequestSO extends UmsappRequestObject{

	/**
     * 
     */
    private static final long serialVersionUID = -1037844809132472070L;
    private String userId;



	/**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }



    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }



    @Override
	public String toString() {
		return "UserPermissionSearchRequestSO [userId=" + this.userId  + "]";
	}
	
}
