package com.tcs.umsapp.search.so.response;

import java.util.List;

import com.tcs.umsapp.search.commons.RequestTrackAppRoleDetail;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class RequestTrackAppRoleResponseSO extends UmsappResponseObject{

	
	/**
     * 
     */
    private static final long serialVersionUID = -5129225943488955144L;
    List<RequestTrackAppRoleDetail> list;
    /**
     * @return the list
     */
    public List<RequestTrackAppRoleDetail> getList() {
        return list;
    }
    /**
     * @param list the list to set
     */
    public void setList(List<RequestTrackAppRoleDetail> list) {
        this.list = list;
    }

	
}
