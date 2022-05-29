package com.tcs.umsapp.search.so.response;

import java.util.List;

import com.tcs.umsapp.search.commons.UserPermissionDetails;
import com.tcs.umsapp.search.vo.cmo.UmsappResponseObject;

public class PermissionResponseSO extends UmsappResponseObject {

	
	/**
     * 
     */
    private static final long serialVersionUID = -1989573725128218415L;
    private List<UserPermissionDetails> userpermissiondetails;



	/**
     * @return the userpermissiondetails
     */
    public List<UserPermissionDetails> getUserpermissiondetails() {
        return userpermissiondetails;
    }



    /**
     * @param userpermissiondetails the userpermissiondetails to set
     */
    public void setUserpermissiondetails(
            List<UserPermissionDetails> userpermissiondetails) {
        this.userpermissiondetails = userpermissiondetails;
    }



    @Override
	public String toString() {
		return "PermissionResponseSO [userpermissiondetails="
				+ userpermissiondetails + "]";
	}
	

}
