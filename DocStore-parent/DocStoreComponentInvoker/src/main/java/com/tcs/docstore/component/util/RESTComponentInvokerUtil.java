/**
 * 
 */
package com.tcs.docstore.component.util;

import com.tcs.docstore.config.utils.UtilProperties;

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


	/**
	 * Instantiates a new REST component invoker util.
	 */
	private RESTComponentInvokerUtil() {

	}

}
