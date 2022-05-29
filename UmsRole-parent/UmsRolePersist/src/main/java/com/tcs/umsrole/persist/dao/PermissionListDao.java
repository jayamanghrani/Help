package com.tcs.umsrole.persist.dao;

import com.tcs.umsrole.request.PermissionListRequestASBO;
import com.tcs.umsrole.response.PermissionListResponseASBO;

public interface PermissionListDao {

	public PermissionListResponseASBO permissionListUpdate(PermissionListRequestASBO permissionListRequestASBO);
}
