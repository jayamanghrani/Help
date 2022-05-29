package com.tcs.umsapp.unlock.persist.dao;

import com.tcs.umsapp.unlock.request.UserDetailDBRequestASBO;
import com.tcs.umsapp.unlock.response.UserDetailDBResponseASBO;

public interface UserInfoDao {

	UserDetailDBResponseASBO getUserInfo(UserDetailDBRequestASBO userDetailDBRequestASBO);

}
