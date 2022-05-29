/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.employeeportal.persist.service;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.employeeportal.db.asbo.request.ForgotPasswordDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.ForgotPasswordDBResponseASBO;
import com.tcs.employeeportal.persist.dao.GetUserDetailsDao;
import com.tcs.employeeportal.persist.dao.GetUserDetailsDaoImpl;
import com.tcs.employeeportal.persist.entities.TEmployeeDetail;
import com.tcs.employeeportal.util.UtilConstants;


/**
 * The Class ForgotPasswordServiceImpl.
 */
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	
	/** The Constant LOGGER. .... */
	private static final Logger LOGGER = Logger.getLogger(ForgotPasswordServiceImpl.class);

	/** The forgot password dao. */
	private GetUserDetailsDao detailsDao;

	/**
	 * Instantiates a new forgot password service impl.
	 */
	public ForgotPasswordServiceImpl() {
		detailsDao = new GetUserDetailsDaoImpl();
	}

	/* (non-Javadoc)
	 * @see com.tcs.employeeportal.persist.service.ForgotPasswordService#getUserDetails(com.tcs.employeeportal.db.asbo.request.ForgotPasswordDBRequestASBO)
	 */
	@Override
	public ForgotPasswordDBResponseASBO getUserDetails(
			ForgotPasswordDBRequestASBO forgotPasswordDBRequestASBO) {
		ForgotPasswordDBResponseASBO forgotPasswordDBResponseASBO = new ForgotPasswordDBResponseASBO();
		String userId = forgotPasswordDBRequestASBO.getUserId();
		
			String password=getPassword();
		
			Date dob = UtilConstants.getDate(forgotPasswordDBRequestASBO.getDob());
			LOGGER.debug("dob==="+dob);
			try {
				TEmployeeDetail tEmployeeDetail = (TEmployeeDetail) detailsDao
						.getUserDetails(userId);
			
				if(tEmployeeDetail!=null){
					
					if(dob.compareTo(tEmployeeDetail.getTEmpDob())==0){
				forgotPasswordDBResponseASBO.setEmailId(tEmployeeDetail.getTEmpEmailId());
				forgotPasswordDBResponseASBO.setMobileNo(tEmployeeDetail.getTEmpMobileNo().toString());
				forgotPasswordDBResponseASBO.setUserId(tEmployeeDetail.getTEmpLoginId());
				forgotPasswordDBResponseASBO.setFirstName(tEmployeeDetail.getTEmpFirstName());
				forgotPasswordDBResponseASBO.setLastName(tEmployeeDetail.getTEmpLastName());
				forgotPasswordDBResponseASBO.setPassword(password);
				forgotPasswordDBResponseASBO.setStatusCode("0");
					}
					else{
						forgotPasswordDBResponseASBO.setStatusCode("1");
						forgotPasswordDBResponseASBO.setStatusMessage(UtilConstants.FORGOT_PASSWORD_DOB_ERROR);
						}
				}
				else {
					forgotPasswordDBResponseASBO.setStatusCode("1");
					forgotPasswordDBResponseASBO.setStatusMessage(UtilConstants.FORGOT_PASSWORD_USERID_ERROR);
				}
				return forgotPasswordDBResponseASBO;
			} catch (Exception e) {
				LOGGER.error(e.getStackTrace());
				//e.printStackTrace();
				forgotPasswordDBResponseASBO.setStatusCode("1");
				forgotPasswordDBResponseASBO.setStatusMessage(UtilConstants.FORGOT_PASSWORD_ERROR);
				return forgotPasswordDBResponseASBO;
			}
			
			//return forgotPasswordDBResponseASBO;
	}

	/**
	 * @return
	 */
	private String getPassword() {
		int noOfCAPSAlpha = 1;
		int noOfDigits = 1;
		int noOfSplChars = 1;
		int minLen = 8;
		int maxLen = 8;
		char[] pswd=null;
	
			pswd = UtilConstants.generatePswd(minLen, maxLen,
					noOfCAPSAlpha, noOfDigits, noOfSplChars);
				
			String rndpwd=new String(pswd);
		return rndpwd;	
	}



}
