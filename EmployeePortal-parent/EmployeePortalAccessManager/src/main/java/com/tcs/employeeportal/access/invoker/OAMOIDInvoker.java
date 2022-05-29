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
package com.tcs.employeeportal.access.invoker;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;

import javax.naming.NamingException;
import javax.naming.directory.BasicAttribute;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.ModificationItem;

import oracle.security.am.asdk.AccessClient;
import org.apache.log4j.Logger;

import com.tcs.employeeportal.access.util.OAMOIDUtil;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.login.asbo.request.ChangePasswordOAMOIDRequestASBO;
import com.tcs.employeeportal.login.asbo.request.ForgotPasswordOAMOIDRequestASBO;
import com.tcs.employeeportal.login.asbo.response.ChangePasswordOAMOIDResponseASBO;
import com.tcs.employeeportal.login.asbo.response.ForgotPasswordOAMOIDResponseASBO;
import com.tcs.employeeportal.util.UtilConstants;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;


/**
 * The Class OAMOIDInvoker.
 * 
 * @author 738566
 */
public class OAMOIDInvoker {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");

	/** The access client. */
	private AccessClient accessClient;

	/** The m_config location. */
	private String m_configLocation;
	
	/** The orcldefaultprofilegroup. */
	private String orcldefaultprofilegroup = null;
	
	/** The group name. */
	private String groupName = null;

	/**
	 * Instantiates a new OAMOID invoker.
	 */
	public OAMOIDInvoker() {
		super();
	//	this.m_configLocation = OAMOIDUtil.JPS_LOCATION;
	}

	/**
	 * Gets the initial context.
	 * 
	 * @param hostname
	 *            the hostname
	 * @param port
	 *            the port
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @return the initial context
	 * @throws NamingException
	 *             the naming exception
	 */
	private DirContext getInitialContext(String hostname, int port,
			String username, String password) throws NamingException {
		String providerURL = "ldap://" + hostname + ":" + port;

		Properties props = new Properties();
		props.put("java.naming.factory.initial",
				"com.sun.jndi.ldap.LdapCtxFactory");

		props.put("java.naming.provider.url", providerURL);
		if ((username != null) && (!username.equals(""))) {
			props.put("java.naming.security.authentication", "simple");
			props.put("java.naming.security.principal", "cn=" + username);

			props.put("java.naming.security.credentials", password == null ? ""
					: (Object) password);
		}

		return new InitialDirContext(props);
	}
	 
 	/**
 	 * Gets the valid initial context.
 	 *
 	 * @param hostname the hostname
 	 * @param port the port
 	 * @param username the username
 	 * @param password the password
 	 * @return the valid initial context
 	 * @throws NamingException the naming exception
 	 */
 	private DirContext getValidInitialContext(String hostname, int port, String username, String password) throws NamingException
	  {
	    String providerURL = "ldap://" + hostname + ":" + port;

	    Properties props = new Properties();
	    props.put("java.naming.factory.initial", "com.sun.jndi.ldap.LdapCtxFactory");

	    props.put("java.naming.provider.url", providerURL);
	    if ((username != null) && (!username.equals(""))) {
	      props.put("java.naming.security.authentication", "simple");
	      props.put("java.naming.security.principal", username);
	      props.put("java.naming.security.credentials", password == null ? "" : password);
	    }

	    return new InitialDirContext(props);
	  }



	/**
	 * Invoke oidoim.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the employee portal response vo
	 * @throws IntegrationTechnicalException
	 *             the integration technical exception
	 */
	public EmployeePortalResponseObject invokeOIDOAM(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		String eventId = requestVO.getHeader().getEventID();

			if(UtilConstants.FORGOT_PASSWORD.equalsIgnoreCase(eventId)){
			return forgotPassword(requestVO);
		}
			else if (UtilConstants.CHANGE_PASSWORD.equalsIgnoreCase(eventId)) {
				return changePassword(requestVO);
			}
		return null;
	}
	
	/**
	 * Forgot password.
	 *
	 * @param requestVO the request vo
	 * @return the employeeportal response object
	 * @throws IntegrationTechnicalException the integration technical exception
	 */
	private EmployeePortalResponseObject forgotPassword(
			EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {
		

		ForgotPasswordOAMOIDResponseASBO responseASBO = new ForgotPasswordOAMOIDResponseASBO();
		String errorMessage = "failure";
		String error = "";
		String errorCode="1";
		ForgotPasswordOAMOIDRequestASBO requestASBO = null;
		if (requestVO instanceof ForgotPasswordOAMOIDRequestASBO) {
			requestASBO = (ForgotPasswordOAMOIDRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate).replace(' ', 'T')
				.concat("Z");
		ModificationItem[] mods = new ModificationItem[5];
		mods[0] = new ModificationItem(2, new BasicAttribute("userpassword",
				requestASBO.getPassword()));
		mods[1] = new ModificationItem(2, new BasicAttribute(
				"obpasswordcreationdate", currentDate));
		mods[2] = new ModificationItem(2, new BasicAttribute(
				"obpasswordchangeflag", "true"));
		mods[3] = new ModificationItem(2, new BasicAttribute(
				"oblogintrycount", "0"));
		mods[4] = new ModificationItem(2, new BasicAttribute(
				"oblockouttime", "0"));

		LOGGER.info(mods);
		DirContext context = null;
		try {
	
			String userIndentification=requestASBO.getUserId();

			context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME,
					Integer.parseInt(OAMOIDUtil.LDAP_PORT),
					OAMOIDUtil.LDAP_USERNAME,
					OAMOIDUtil.LDAP_PASSWORD);
			context.modifyAttributes("cn=" + userIndentification
					+ ",cn=users,dc=newindia,dc=co,dc=in", mods);
			errorMessage = UtilConstants.FORGOT_PASSWORD_SUCCESSMSG;
			errorCode="0";
			responseASBO.setUserId(userIndentification);
			responseASBO.setPassword(requestASBO.getPassword());
			
		} catch (NamingException e) {
			error = e.getMessage();
			LOGGER.error(e.getMessage(), e);
		//	errorMessage = error;
			errorMessage = "Failed";
			errorCode="1";
			
			LOGGER.info(errorMessage);
			try {
				
				if(null != context) {
					context.close();
				}
			} catch (NamingException e1) {
				LOGGER.error(e1.getMessage(), e1);
			}
		} 
	
		try {
		
			if(null != context) {
				context.close();
			}
		} catch (NamingException e) {
			
			LOGGER.error(e.getMessage(), e);
		}
		responseASBO.setStatusMessage(errorMessage);
		responseASBO.setStatusCode(errorCode);
		return responseASBO;
	
	}

	
	/**
	 * Change password.
	 * 
	 * @param requestVO
	 *            the request vo
	 * @return the integration response object
	 * @throws IntegrationTechnicalException
	 *             the integration technical exception
	 */
	private EmployeePortalResponseObject changePassword(
			EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		LOGGER.info("Entering changePassword()");
		long start = System.currentTimeMillis();
		ChangePasswordOAMOIDResponseASBO responseASBO = new ChangePasswordOAMOIDResponseASBO();
		String errorMessage = "failure";
		String errorCode = "1";
		String error = "";
		ChangePasswordOAMOIDRequestASBO requestASBO = null;
		if (requestVO instanceof ChangePasswordOAMOIDRequestASBO) {
			requestASBO = (ChangePasswordOAMOIDRequestASBO) requestVO;
		} else {
			throw new IntegrationTechnicalException();
		}

		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate).replace(' ', 'T')
				.concat("Z");
		ModificationItem[] mods = new ModificationItem[4];
		mods[0] = new ModificationItem(2, new BasicAttribute("userpassword",
				requestASBO.getNewPassword()));
		mods[1] = new ModificationItem(2, new BasicAttribute(
				"obpasswordcreationdate", currentDate));
		mods[2] = new ModificationItem(2, new BasicAttribute(
				"obpasswordchangeflag", "false"));
		mods[3] = new ModificationItem(2, new BasicAttribute(
				"oblogintrycount", "0"));

		// System.out.println(mods);
		DirContext context = null;
		DirContext validContext = null;
		try {
		
			context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME,
					Integer.parseInt(OAMOIDUtil.LDAP_PORT),
					OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);
			String usrName = "cn=" + requestASBO.getUserId()
					+ ",cn=Users,dc=newindia,dc=co,dc=in";
			try {
				validContext = getValidInitialContext(OAMOIDUtil.LDAP_HOSTNAME,
						Integer.parseInt(OAMOIDUtil.LDAP_PORT), usrName,
						requestASBO.getOldPassword());

				LOGGER.debug("validContext: " + validContext);
			} catch (Exception e) {
				error = e.getMessage();
				LOGGER.error(error);
				if(error.contains("49")){
					errorMessage = UtilConstants.ERROR_INVALIDCRED_CHANGEPASSWORD;
				}
				else{
				errorMessage = UtilConstants.ERROR_MSG_CHANGEPASSWORD;
				}
				try {
					context.close();
				} catch (NamingException e1) {
					//error=e1.getMessage();
					LOGGER.error(e1.getMessage(), e1);
				}
				responseASBO
						.setStatusMessage(errorMessage);
				responseASBO.setStatusCode("1");
				return responseASBO;
			}
			
			context.modifyAttributes("cn=" + requestASBO.getUserId()
					+ ",cn=users,dc=newindia,dc=co,dc=in", mods);

			errorMessage = UtilConstants.SUCCESS_MSG_CHANGEPASSWORD;
			errorCode = "0";
		} catch (NamingException e) {
			LOGGER.error(error);
			error = e.getMessage();
			errorCode = "1";
			if(error.contains("49")){
				errorMessage = UtilConstants.ERROR_INVALIDCRED_CHANGEPASSWORD;
			}
			else{
			errorMessage = UtilConstants.ERROR_MSG_CHANGEPASSWORD;
			}

			try {
				context.close();
			} catch (NamingException e1) {
				LOGGER.error(e1.getMessage(), e1);
			}
		}
		
		try {
			context.close();
		} catch (NamingException e) {
			LOGGER.error(e.getMessage(), e);
		}
		responseASBO.setStatusMessage(errorMessage);
		responseASBO.setStatusCode(errorCode);
		long end = System.currentTimeMillis();
		LOGGER.info("Exiting changePassword()");
		LOGGER.info("Time taken to changePassword() : " + (end - start) + "ms");
		return responseASBO;
	}

	
	/**
	 * To insert last login time in OID
	 * @param lastLoginDt, userid
	 * @return
	 * @throws IntegrationTechnicalException
	 */
	public void lastLoginTime(String userid, String tempLoginDate)
			throws IntegrationTechnicalException { 
		LOGGER.info("Entering lastLoginTime()"+tempLoginDate);
		long start = System.currentTimeMillis();
/*
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",
				Locale.ENGLISH);
		Date sysDate = new Date();
		String currentDate = dateFormat.format(sysDate).replace(' ', 'T')
				.concat("Z");*/
		ModificationItem[] mods = new ModificationItem[1];
		mods[0] = new ModificationItem(2, new BasicAttribute(
				"previousLoginTime", tempLoginDate));

		// System.out.println(mods);
		DirContext context = null;
		try {
			context = getInitialContext(OAMOIDUtil.LDAP_HOSTNAME,
					Integer.parseInt(OAMOIDUtil.LDAP_PORT),
					OAMOIDUtil.LDAP_USERNAME, OAMOIDUtil.LDAP_PASSWORD);

			context.modifyAttributes("cn=" + userid
					+ ",cn=users,dc=newindia,dc=co,dc=in", mods);

		} catch (NamingException e) {
			try {
				context.close();
			} catch (NamingException e1) {
				LOGGER.error(e1.getMessage(), e1);
			}
		}
		try {
			context.close();
		} catch (NamingException e) {
			LOGGER.error(e.getMessage(), e);
		}

		long end = System.currentTimeMillis();
		LOGGER.info("Exiting lastLoginTime()");
		LOGGER.info("Time taken to lastLoginTime() : " + (end - start) + "ms");

	}
}
