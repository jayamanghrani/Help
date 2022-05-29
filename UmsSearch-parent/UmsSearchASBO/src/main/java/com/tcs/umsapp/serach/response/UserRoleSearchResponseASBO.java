package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.UserRoleDetails;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserRoleSearchResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = 7797039514667482443L;

    private List<UserRoleDetails> statusList;

    /**
     * @return the statusList
     */
    public List<UserRoleDetails> getStatusList() {
        return statusList;
    }

    /**
     * @param statusList
     *            the statusList to set
     */
    public void setStatusList(List<UserRoleDetails> statusList) {
        this.statusList = statusList;
    }

}
