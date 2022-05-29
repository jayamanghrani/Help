package com.tcs.employeeportal.persist.service;

import com.tcs.employeeportal.db.asbo.request.GetInfoPolicyDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.request.InfoPolicyAcceptDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetInfoPolicyDetailsDBResponseASBO;
import com.tcs.employeeportal.db.asbo.response.InfoPolicyAcceptDBResponseASBO;
import com.tcs.employeeportal.persist.dao.InfoPolicyDao;
import com.tcs.employeeportal.persist.dao.InfoPolicyDaoImpl;

public class InfoPolicyServiceImpl implements InfoPolicyService{
	

	private InfoPolicyDao infoPolicyDao;
	
	/**
	 * Instantiates a new info policy service impl.
	 */
	public InfoPolicyServiceImpl(){
		infoPolicyDao = new InfoPolicyDaoImpl();
	}
	
	
	public GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetails(GetInfoPolicyDetailsDBRequestASBO getInfoPolicyDetailsDBRequestASBO) {
		// TODO Auto-generated method stub
		GetInfoPolicyDetailsDBResponseASBO getInfoPolicyDetailsDBResponseASBO = new GetInfoPolicyDetailsDBResponseASBO();
		try {
				getInfoPolicyDetailsDBResponseASBO = infoPolicyDao.getInfoPolicyDetails(getInfoPolicyDetailsDBRequestASBO);
						
			
				if(getInfoPolicyDetailsDBResponseASBO!=null){
					getInfoPolicyDetailsDBResponseASBO.setUserId(getInfoPolicyDetailsDBResponseASBO.getUserId());
					System.out.println("getInfoPolicyDetailsDBResponseASBO" + getInfoPolicyDetailsDBResponseASBO);
					getInfoPolicyDetailsDBResponseASBO.setFirstName(getInfoPolicyDetailsDBResponseASBO.getFirstName());
					getInfoPolicyDetailsDBResponseASBO.setLastName(getInfoPolicyDetailsDBResponseASBO.getLastName());
				
					}
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			System.out.println("Inside getInfoPolicyDetails getInfoPolicyDetailsDBResponseASBO"   + getInfoPolicyDetailsDBResponseASBO);
				return getInfoPolicyDetailsDBResponseASBO;

	}


	@Override
	public InfoPolicyAcceptDBResponseASBO getInfoAcceptPolicy(
			InfoPolicyAcceptDBRequestASBO infoPolicyAcceptDBRequestASBO) {
		// TODO Auto-generated method stub
	InfoPolicyAcceptDBResponseASBO infoPolicyAcceptDBResponseASBO = new InfoPolicyAcceptDBResponseASBO();
	try
	{
		infoPolicyAcceptDBResponseASBO = infoPolicyDao.getInfoAcceptPolicy(infoPolicyAcceptDBRequestASBO);
	
	if(infoPolicyAcceptDBResponseASBO!=null)
	{
		infoPolicyAcceptDBResponseASBO.setStatus(infoPolicyAcceptDBResponseASBO.getStatus());
	}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	System.out.println("Inside getInfoAcceptPolicy infoPolicyAcceptDBResponseASBO"   + infoPolicyAcceptDBResponseASBO);
	return infoPolicyAcceptDBResponseASBO;
	}

}
	
