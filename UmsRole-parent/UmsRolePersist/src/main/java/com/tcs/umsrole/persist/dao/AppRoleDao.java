package com.tcs.umsrole.persist.dao;

import com.tcs.umsrole.request.AppRoleMapRequestASBO;
import com.tcs.umsrole.response.AppDetailsResponseASBO;
import com.tcs.umsrole.response.AppRoleMapResponseASBO;



public interface AppRoleDao {

	public AppRoleMapResponseASBO appRoleMap(AppRoleMapRequestASBO appRoleMapRequestASBO);

	public AppDetailsResponseASBO appldifRoleMap(AppRoleMapRequestASBO appRoleMapRequestASBO);
}
