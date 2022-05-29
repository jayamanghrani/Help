package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.RequestTrackAppRoleDetail;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class RequestTrackAppRoleDBResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = 5946302851749199815L;

    private List<RequestTrackAppRoleDetail> list;

    /**
     * @return the list
     */
    public List<RequestTrackAppRoleDetail> getList() {
        return list;
    }

    /**
     * @param list
     *            the list to set
     */
    public void setList(List<RequestTrackAppRoleDetail> list) {
        this.list = list;
    }

}
