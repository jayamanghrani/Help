package com.tcs.employeeportal.persist.service;

import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;

public interface InfoPolicyService {
	
	public GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetails(GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO);
	
	public InfoPolicyAcceptDBResponseASBO getInfoAcceptPolicy(InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO);

}
