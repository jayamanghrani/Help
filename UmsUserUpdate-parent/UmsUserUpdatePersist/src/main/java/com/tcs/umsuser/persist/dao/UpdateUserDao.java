
package com.tcs.umsuser.persist.dao;

import java.sql.SQLException;
import java.util.List;

import com.tcs.umsuser.request.asbo.PendingUserTempDetail;
import com.tcs.umsuser.request.asbo.ProvDetailsRequestASBO;
import com.tcs.umsuser.request.asbo.UpdateJiraUserRequestSO;
import com.tcs.umsuser.request.asbo.UpdateUserRequestASBO;
import com.tcs.umsuser.response.asbo.ProvisionDetailsResponseASBO;
import com.tcs.umsuser.response.asbo.RoleUpdateResponseASBO;

public interface UpdateUserDao {
	public RoleUpdateResponseASBO updateUser(UpdateUserRequestASBO updateUserRequestASBO);
	public List<PendingUserTempDetail> updateTempToMst(long recordId);
	public boolean deleteUserMaster(ProvDetailsRequestASBO provDetailsRequestASBO, ProvisionDetailsResponseASBO provisionDetailsResponseASBO,UpdateJiraUserRequestSO jiraUserRequestSO)throws SQLException;
	public int checkUserExist(String userId)throws SQLException;
	public boolean insertNewUser(PendingUserTempDetail userDetail);
	public boolean updateUserDetails(PendingUserTempDetail userDetail) throws SQLException;
	public int updateUsrTempStatus(String action, long recordId) throws SQLException;
	public void updateErrorStatus(long recordId, int errorCode, String message, String action);
	
	
}
