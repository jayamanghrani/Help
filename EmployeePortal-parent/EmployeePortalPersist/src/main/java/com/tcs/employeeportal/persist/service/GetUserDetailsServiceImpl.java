package com.tcs.employeeportal.persist.service;

import org.apache.log4j.Logger;
import com.tcs.employeeportal.db.asbo.request.GetUserDetailsDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.GetUserDetialsDBResponseASBO;
import com.tcs.employeeportal.persist.dao.GetUserDetailsDao;
import com.tcs.employeeportal.persist.dao.GetUserDetailsDaoImpl;
import com.tcs.employeeportal.persist.entities.TEmployeeDetail;

public class GetUserDetailsServiceImpl implements GetUserDetailsService{
	
	/** The Constant LOGGER. .... */
	private static final Logger LOGGER = Logger.getLogger(GetUserDetailsServiceImpl.class);

	/** The forgot password dao. */
	private GetUserDetailsDao getUserDetailsDao;

	/**
	 * Instantiates a new forgot password service impl.
	 */
	public GetUserDetailsServiceImpl() {
		getUserDetailsDao = new GetUserDetailsDaoImpl();
	}
	@Override
	public GetUserDetialsDBResponseASBO getUserDetails(
			GetUserDetailsDBRequestASBO getUserDetailsDBRequestASBO) {
		GetUserDetialsDBResponseASBO getUserDetialsDBResponseASBO = new GetUserDetialsDBResponseASBO();
		String userId = getUserDetailsDBRequestASBO.getUserId();
			try {
				TEmployeeDetail tEmployeeDetail = (TEmployeeDetail) getUserDetailsDao
						.getUserDetails(userId);
			
				if(tEmployeeDetail!=null){
					getUserDetialsDBResponseASBO.setEmailId(tEmployeeDetail.getTEmpEmailId());
					getUserDetialsDBResponseASBO.setMobileNumber(tEmployeeDetail.getTEmpMobileNo().toString());
					getUserDetialsDBResponseASBO.setUserId(tEmployeeDetail.getTEmpLoginId());
					getUserDetialsDBResponseASBO.setBranch(tEmployeeDetail.getTEmpBranchCode());
					}
			} catch (Exception e) {
				LOGGER.error(e.getStackTrace());
				//e.printStackTrace();
			}
				return getUserDetialsDBResponseASBO;
	}
}
