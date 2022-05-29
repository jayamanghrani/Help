package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.UserRoleDetailsXLS;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserRoleExcelResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = 7797039514667482443L;

    private List<List<UserRoleDetailsXLS>> statusList;

    /**
     * @return the statusList
     */
    public List<List<UserRoleDetailsXLS>> getStatusList() {
        return statusList;
    }

    /**
     * @param statusList
     *            the statusList to set
     */
    public void setStatusList(List<List<UserRoleDetailsXLS>> statusList) {
        this.statusList = statusList;
    }

}
