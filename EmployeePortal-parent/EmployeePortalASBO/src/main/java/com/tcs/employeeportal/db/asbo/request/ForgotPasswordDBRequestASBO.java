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
package com.tcs.employeeportal.db.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordDBRequestASBO.
 */
public class ForgotPasswordDBRequestASBO extends EmployeePortalRequestObject{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4400582001700636790L;
	
	/** The user id. */
	private String userId;
	
	/** The dob. */
	private String dob;
	
	/** The flag. */
	private String flag;
	
	/** The email id. */
	private String emailId;

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the flag.
	 *
	 * @return the flag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Sets the flag.
	 *
	 * @param flag the new flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the userId to set
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * Gets the dob.
	 *
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * Sets the dob.
	 *
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}
  
	
	
}
