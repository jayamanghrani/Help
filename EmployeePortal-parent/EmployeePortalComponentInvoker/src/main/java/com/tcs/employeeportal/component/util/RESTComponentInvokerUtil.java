/**
 * 
 */
package com.tcs.employeeportal.component.util;

import com.tcs.employeeportal.config.utils.UtilProperties;

/**
 * @author 738566
 *
 */
public class RESTComponentInvokerUtil {
	
	/** The Constant LDAP_HOSTNAME. */
	public static final String LDAP_HOSTNAME = UtilProperties.getLdapHostName();

	/** The Constant LDAP_PORT. */
	public static final String LDAP_PORT = UtilProperties.getLdapPort();

	/** The Constant LDAP_USERNAME. */
	public static final String LDAP_USERNAME = UtilProperties.getLdapUserName();

	/** The Constant LDAP_PASSWORD. */
	public static final String LDAP_PASSWORD = UtilProperties.getLdapPassword();

/*	*//** The Constant policyDetailsURL. *//*
	public static final String breakinDetails = "http://"
			+ PropertiesUtil.getConfigData("ipAddress") + ":"
			+ PropertiesUtil.getConfigData("portNumber")
			+ "/EmployeePortal/rest/breakIn/getBreakinDetails";


	*//** The Constant JPS_LOCATION. *//*
	public static final String JPS_LOCATION = PropertiesUtil
			.getConfigData("sitJpsLocation");

	*//** The Constant OIMOID_URL. *//*
	public static final String OIMOID_URL = PropertiesUtil
			.getConfigData("oimoidUrl");

	*//** The Constant LDAP_HOSTNAME. *//*
	public static final String LDAP_HOSTNAME = PropertiesUtil
			.getConfigData("ldapHostName");

	*//** The Constant LDAP_PORT. *//*
	public static final String LDAP_PORT = PropertiesUtil
			.getConfigData("ldapPort");

	*//** The Constant LDAP_USERNAME. *//*
	public static final String LDAP_USERNAME = PropertiesUtil
			.getConfigData("ldapUserName");

	*//** The Constant LDAP_PASSWORD. *//*
	public static final String LDAP_PASSWORD = PropertiesUtil
			.getConfigData("ldapPassword");*/


	/**
	 * Instantiates a new REST component invoker util.
	 */
	private RESTComponentInvokerUtil() {

	}

}
