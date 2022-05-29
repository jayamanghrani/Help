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
package com.tcs.employeeportal.login.asbo.request;

import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;



/**
 * The Class ChangePasswordOAMOIDRequestASBO.
 * 
 * @author 738566
 */
public class ChangePasswordOAMOIDRequestASBO extends EmployeePortalRequestObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1160916675743122766L;

	/** The flag. */
	private String flag;

	/** The old password. */
	private String oldPassword;

	/** The new password. */
	private String newPassword;
	
	/** The user id. */
	private String userId;

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
	 * @param flag            the flag to set
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Gets the old password.
	 *
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * Sets the old password.
	 *
	 * @param oldPassword            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * Gets the new password.
	 *
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * Sets the new password.
	 *
	 * @param newPassword            the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}


	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ChangePasswordOAMOIDRequestASBO [flag=");
		builder.append(flag);
		builder.append(", oldPassword=");
		builder.append(oldPassword);
		builder.append(", newPassword=");
		builder.append(newPassword);
		builder.append("]");
		return builder.toString();
	}

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId the new user id
	 */
	public void setUserId(String userId) {
		this.userId = userId;
	}

}
