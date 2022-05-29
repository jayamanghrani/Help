package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.PermissionDetailsCommmon;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserPermissionResponseASBO extends UmsappResponseObject {

    private static final long serialVersionUID = -5124191866802190620L;

    private List<PermissionDetailsCommmon> details;

    /**
     * @return the details
     */
    public List<PermissionDetailsCommmon> getDetails() {
        return details;
    }

    /**
     * @param details
     *            the details to set
     */
    public void setDetails(List<PermissionDetailsCommmon> details) {
        this.details = details;
    }

}
