package com.tcs.umsapp.search.so.response;

import java.util.List;

import com.tcs.umsapp.search.commons.RequestTrackDetail;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class RequestTrackDetailResponseSO extends UmsappResponseObject{

	private static final long serialVersionUID = 8546033446427215430L;

	
	private List<RequestTrackDetail> list;


    /**
     * @return the list
     */
    public List<RequestTrackDetail> getList() {
        return list;
    }


    /**
     * @param list the list to set
     */
    public void setList(List<RequestTrackDetail> list) {
        this.list = list;
    }


}
