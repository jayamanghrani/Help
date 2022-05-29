package com.tcs.umsrole.vo.ldif;

import com.tcs.umsrole.vo.util.UtilProperties;


public class LdifUtil {

	/** The Constant LDAP_HOSTNAME. */
	public static final String LDAP_HOSTNAME = UtilProperties.getLDAP_HOSTNAME();
	  public static final String LDAP_PORT = UtilProperties.getLDAP_PORT();
	/** The Constant LDAP_USERNAME. */
	  public static final String LDAP_USERNAME = UtilProperties.getLDAP_USERNAME();

	/** The Constant LDAP_PASSWORD. */
	  public static final String LDAP_PASSWORD = UtilProperties.getLDAP_PASSWORD();
	/** The Constant LDAP_Target_hostname. */
		public static final String LDAP_Target_HOSTNAME=UtilProperties.getLDAP_Target_HOSTNAME();
	/** The Constant LDAP_Target_PORT. */
		public static final String LDAP_Target_PORT = UtilProperties.getLDAP_Target_PORT();
	/** The Constant LDAP_Target_USERNAME. */
		public static final String LDAP_Target_USERNAME = UtilProperties.getLDAP_Target_USERNAME();
	
		public static final String LDAP_Target_PATH=UtilProperties.getLDAP_Target_PATH();
	/** The Constant LDAP_Target_PASSWORD. */
		public static final String LDAP_Target_PASSWORD =UtilProperties.getLDAP_Target_PASSWORD();
	
		
		public static final String SUCCESS = "Account  Successfully";
	
	public static final String FAILED = "Account  Failed";
	
	public static final String NO_ERROR = "0";
	
	public static final String ERROR = "1";
	
	
	
}
