package com.tcs.umsapp.search.persist.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ums_usr_mst")

public class UserPermission {
	public UserPermission() {
		super();
	}
	@Id
	@Column(name = "uum_usr_id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String userId;
	@Column(name = "uum_permission_list")
    private String UserPermissionList;
	/** 
	 * 
	 * @param userId
	 * @param userPermissionList
	 */
	public UserPermission(String userId, String userPermissionList) {
		super();
		this.userId = userId;
		UserPermissionList = userPermissionList;
	}
	@Override
	public String toString() {
		return "UserPermission [userID=" + userId + ", UserPermissionList="
				+ UserPermissionList + "]";
	}
    /**
     * @return the userId
     */
    public String getUserId() {
        return userId;
    }
    /**
     * @param userId the userId to set
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }
    /**
     * @return the userPermissionList
     */
    public String getUserPermissionList() {
        return UserPermissionList;
    }
    /**
     * @param userPermissionList the userPermissionList to set
     */
    public void setUserPermissionList(String userPermissionList) {
        UserPermissionList = userPermissionList;
    }
	
	
	

}
