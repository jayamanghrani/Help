package com.tcs.umsapp.search.request;

import java.util.List;

import com.tcs.umsapp.search.vo.cmo.UmsappRequestObject;

public class RequestTrackXLSRequestASBO extends UmsappRequestObject {

    private static final long serialVersionUID = -6436389882425352087L;

    private List<UserRequestIdASBO> UserRequestId;

    /**
     * @return the userRequestId
     */
    public List<UserRequestIdASBO> getUserRequestId() {
        return UserRequestId;
    }

    /**
     * @param userRequestId
     *            the userRequestId to set
     */
    public void setUserRequestId(List<UserRequestIdASBO> userRequestId) {
        UserRequestId = userRequestId;
    }

    @Override
    public String toString() {
        return "RequestTrackXLSRequestASBO ()";
    }

}