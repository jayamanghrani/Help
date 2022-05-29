package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.RequestTrackDetailXLS;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class RequestTrackXLSResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = 7797039514667482443L;

    private List<List<RequestTrackDetailXLS>> statusList;

    /**
     * @return the statusList
     */
    public List<List<RequestTrackDetailXLS>> getStatusList() {
        return statusList;
    }

    /**
     * @param statusList
     *            the statusList to set
     */
    public void setStatusList(List<List<RequestTrackDetailXLS>> statusList) {
        this.statusList = statusList;
    }

    @Override
    public String toString() {
        return "RequestTrackXLSResponseASBO [statusList=" + statusList + "]";
    }

}