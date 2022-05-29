package com.tcs.umsapp.search.persist.dao;

import com.tcs.umsapp.search.request.*;
import com.tcs.umsapp.serach.response.*;

public interface RequestTrackDao {

	public RequestTrackDBResponseASBO getRequestTrackDetails(RequestTrackDBReqeustASBO requestTrackDBReqeustASBO, String loginUserBranchId);

	public RequestTrackAppRoleDBResponseASBO getRequestTrackAppRoleDetails(RequestTrackAppRoleDBRequestASBO requestTrackAppRoleDBRequestASBO);
	public RequestTrackXLSResponseASBO getRequestTrackXLS(RequestTrackXLSRequestASBO requestTrackXLSRequestASBO);
	
	public String getLoginUserBranch(String loginUserId);
	
}
