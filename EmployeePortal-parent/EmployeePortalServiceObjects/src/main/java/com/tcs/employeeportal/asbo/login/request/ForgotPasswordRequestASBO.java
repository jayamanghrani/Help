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
package com.tcs.employeeportal.asbo.login.request;

import com.tcs.employeeportal.asbo.alfresco.AlfrescoInputs;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;

// TODO: Auto-generated Javadoc
/**
 * The Class ForgotPasswordRequestASBO.
 */
public class ForgotPasswordRequestASBO extends EmployeePortalRequestObject{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4723790081389995164L;
	
	/** The user id. */
	private String userId;
	
	/** The dob. */
	private String dob;
	
	/** The password. */
/*	private String password;
	
	*//** The alfresco input. */
	private AlfrescoInputs alfrescoInput;
	

	/** The flag. *//*
	private String flag;

	
	*//** The otp. *//*
	private String otp;
	
	*//** The first name. *//*
	private String firstName;
	
	*//** The last name. *//*
	private String lastName;
	
	*//** The email id. *//*
	private String emailId;
	
	*//** The mobile no. *//*
	private String mobileNo;
	*/

	/**
	 * Gets the user id.
	 *
	 * @return the userId
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * @return the alfrescoInput
	 */
	public AlfrescoInputs getAlfrescoInput() {
		return alfrescoInput;
	}

	/**
	 * @param alfrescoInput the alfrescoInput to set
	 */
	public void setAlfrescoInput(AlfrescoInputs alfrescoInput) {
		this.alfrescoInput = alfrescoInput;
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
