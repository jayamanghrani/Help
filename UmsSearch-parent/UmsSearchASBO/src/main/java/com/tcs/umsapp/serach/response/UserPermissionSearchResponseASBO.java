package com.tcs.umsapp.serach.response;

import java.util.List;

import com.tcs.umsapp.search.commons.UserSearchPermission;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class UserPermissionSearchResponseASBO extends UmsappResponseObject {

    /**
     * 
     */
    private static final long serialVersionUID = -1579633861545653309L;
    private List<UserSearchPermission> userpermission;

    @Override
    public String toString() {
        return "UserPermissionSearchResponseASBO [userpermission="
                + this.userpermission + "]";
    }

    /**
     * @return the userpermission
     */
    public List<UserSearchPermission> getUserpermission() {
        return userpermission;
    }

    /**
     * @param userpermission
     *            the userpermission to set
     */
    public void setUserpermission(List<UserSearchPermission> userpermission) {
        this.userpermission = userpermission;
    }

}
