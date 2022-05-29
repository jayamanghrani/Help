package com.tcs.umsoid.vo.util;


public class LdifUtil {

    /** The Constant LDAP_HOSTNAME. */
    public static final String LDAP_HOSTNAME = UtilProperties
            .getLDAP_HOSTNAME();

    /** The Constant LDAP_PORT. */
    public static final String LDAP_PORT = UtilProperties.getLDAP_PORT();
    /** The Constant LDAP_USERNAME. */
    public static final String LDAP_USERNAME = UtilProperties
            .getLDAP_USERNAME();

    /** The Constant LDAP_PASSWORD. */
    public static final String LDAP_PASSWORD = UtilProperties
            .getLDAP_PASSWORD();


    public static final String SUCCESS = "Modified with Users";

    public static final String FAILED = "Users not Modified";

    public static final String NO_ERROR = "0";

    public static final String ERROR = "1";

}
