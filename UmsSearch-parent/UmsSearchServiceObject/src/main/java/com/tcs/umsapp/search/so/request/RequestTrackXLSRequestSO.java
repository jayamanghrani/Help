package com.tcs.umsapp.search.so.request;

import java.util.List;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;


public class RequestTrackXLSRequestSO extends UmsappRequestObject {

	private static final long serialVersionUID = -6436389882425352087L;
	
	
	private List<UserRequestId> UserRequestId;

	
	/**
     * @return the userRequestId
     */
    public List<UserRequestId> getUserRequestId() {
        return UserRequestId;
    }


    /**
     * @param userRequestId the userRequestId to set
     */
    public void setUserRequestId(List<UserRequestId> userRequestId) {
        UserRequestId = userRequestId;
    }


    @Override
	public String toString() {
		return "RequestTrackXLSRequestSO [UserRequestId=" + UserRequestId + "]";
	}
}