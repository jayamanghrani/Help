package com.tcs.umsoid.persist.dao;

import java.util.List;

import com.tcs.umsoid.common.UserRoleDetails;
import com.tcs.umsoid.request.UserLdapDetailRequestASBO;
import com.tcs.umsoid.response.UserLdapDetailResponseASBO;
import com.tcs.umsoid.vo.bean.OIDUserDetails;
import com.tcs.umsoid.vo.bean.ProvisionDetails;

public interface UserRoleOidDao {

	List<UserRoleDetails> ldifRevokeRolesMapManagement(UserLdapDetailRequestASBO getuserLdapDetailRequestASBO);

	List<ProvisionDetails> getProvisionRequestList(UserLdapDetailRequestASBO userLdapDetailRequestASBO);

	boolean setActionStatus(OIDUserDetails userDetails, String provisionAction);

}
