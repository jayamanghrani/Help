package com.tcs.umsapp.search.persist.dao;

import com.tcs.umsapp.search.request.GetUserPermissionDetailsRequestASBO;
import com.tcs.umsapp.search.request.GetUserSearchDetailsRequestASBO;
import com.tcs.umsapp.search.request.UserPermissionSearchRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestASBO;
import com.tcs.umsapp.search.request.UserRoleSearchRequestXLSASBO;
import com.tcs.umsapp.search.so.response.UserOfficeCodeDetails;
import com.tcs.umsapp.search.so.response.UserSearchDetailsSO;
import com.tcs.umsapp.serach.response.UserPermissionResponseASBO;
import com.tcs.umsapp.serach.response.UserPermissionSearchResponseASBO;
import com.tcs.umsapp.serach.response.UserRoleExcelResponseASBO;
import com.tcs.umsapp.serach.response.UserRoleSearchResponseASBO;
import com.tcs.umsapp.serach.response.UserSearchDetailsResponseASBO;

public interface UserDetailDao {
	
	 public UserSearchDetailsResponseASBO getUserDetailsDB(
	            GetUserSearchDetailsRequestASBO getUserSearchDetailsRequestASBO );

	    public UserRoleSearchResponseASBO getUserRoleDetailsDB(
	            UserRoleSearchRequestASBO userRoleSearchRequestASBO);

	    public UserPermissionResponseASBO getUserPermissionDetailsDB(
	            GetUserPermissionDetailsRequestASBO getUserPermissionDetailsRequestASBO);

	    public UserPermissionSearchResponseASBO getUserSearchPermissionDetailsDB(
	            UserPermissionSearchRequestASBO getUserPermissionSearchRequestASBO);

	    public UserRoleExcelResponseASBO getUserRoleDetailXLSDB(
	            UserRoleSearchRequestXLSASBO userRoleSearchRequestASBO);

	    public UserSearchDetailsSO getLoginUserDetails(String loginId);
	    
		public UserOfficeCodeDetails getOfficeCodeDetails(String branchId);

		public UserRoleSearchResponseASBO getUserFinanceRoleDetailsDB(
				UserRoleSearchRequestASBO userRoleSearchRequestASBO);

}
