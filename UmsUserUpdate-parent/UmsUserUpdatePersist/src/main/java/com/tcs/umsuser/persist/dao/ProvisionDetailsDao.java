package com.tcs.umsuser.persist.dao;

import java.sql.SQLException;
import java.util.List;

import com.tcs.umsuser.request.asbo.ProvDetailsRequestASBO;
import com.tcs.umsuser.response.asbo.ProvisionDetailsResponseASBO;



public interface ProvisionDetailsDao {

	public ProvisionDetailsResponseASBO provisionDetailsUpdate(ProvDetailsRequestASBO provDetailsRequestASBO)throws SQLException;

	public List<String> getUserAppId(String userId) throws SQLException;
}
