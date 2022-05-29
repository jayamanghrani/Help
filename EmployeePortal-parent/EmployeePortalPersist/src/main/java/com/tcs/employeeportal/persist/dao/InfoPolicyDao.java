package com.tcs.employeeportal.persist.dao;

import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;

public interface InfoPolicyDao {
	
	public GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetails(GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO);
	
	public InfoPolicyAcceptDBResponseASBO getInfoAcceptPolicy(InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO);

}
