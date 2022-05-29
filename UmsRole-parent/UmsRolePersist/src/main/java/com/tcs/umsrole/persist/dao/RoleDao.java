package com.tcs.umsrole.persist.dao;

import com.tcs.umsrole.request.UpdateRoleDetailsASBO;
import com.tcs.umsrole.response.GetRoleDetailsResponseASBO;

public interface RoleDao {
	
	public GetRoleDetailsResponseASBO updateUserRolesDetails(UpdateRoleDetailsASBO getRoleDetailsRequestASBO);
	public GetRoleDetailsResponseASBO updateXLUserRolesDetails(UpdateRoleDetailsASBO getRoleDetailsRequestASBO);
	public void getUmsAndOidReq();

}
