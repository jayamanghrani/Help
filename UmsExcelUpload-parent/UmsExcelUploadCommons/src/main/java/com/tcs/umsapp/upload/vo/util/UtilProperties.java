package com.tcs.umsapp.upload.vo.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);

    public static String LDAP_HOSTNAME;
    public static String LDAP_PORT;
    public static String LDAP_USERNAME;
    public static String LDAP_PASSWORD;
    public static String LDAP_Target_HOSTNAME;
    public static String LDAP_Target_PORT;
    public static String LDAP_Target_USERNAME;
    public static String LDAP_Target_PATH;
    public static String LDAP_Target_PASSWORD;
    public static String emailUrl;
    public static String fromId;
    public static String subjectForUpdateRole;
    public static String OIDServiceUrl;
    public static String OSBIntegrationURL;
    public static String userRoleSearchUrl;

    public static String getLDAP_HOSTNAME() {
        return LDAP_HOSTNAME;
    }

    public static void setLDAP_HOSTNAME(String lDAP_HOSTNAME) {
        UtilProperties.LDAP_HOSTNAME = lDAP_HOSTNAME;
    }

    public static String getLDAP_PORT() {
        return LDAP_PORT;
    }

    public static void setLDAP_PORT(String lDAP_PORT) {
        UtilProperties.LDAP_PORT = lDAP_PORT;
    }

    public static String getLDAP_USERNAME() {
        return LDAP_USERNAME;
    }

    public static void setLDAP_USERNAME(String lDAP_USERNAME) {
        UtilProperties.LDAP_USERNAME = lDAP_USERNAME;
    }

    public static String getLDAP_PASSWORD() {
        return LDAP_PASSWORD;
    }

    public static void setLDAP_PASSWORD(String lDAP_PASSWORD) {
        UtilProperties.LDAP_PASSWORD = lDAP_PASSWORD;
    }

    public static String getLDAP_Target_HOSTNAME() {
        return LDAP_Target_HOSTNAME;
    }

    public static void setLDAP_Target_HOSTNAME(String lDAP_Target_HOSTNAME) {
        UtilProperties.LDAP_Target_HOSTNAME = lDAP_Target_HOSTNAME;
    }

    public static String getLDAP_Target_PORT() {
        return LDAP_Target_PORT;
    }

    public static void setLDAP_Target_PORT(String lDAP_Target_PORT) {
        UtilProperties.LDAP_Target_PORT = lDAP_Target_PORT;
    }

    public static String getLDAP_Target_USERNAME() {
        return LDAP_Target_USERNAME;
    }

    public static void setLDAP_Target_USERNAME(String lDAP_Target_USERNAME) {
        UtilProperties.LDAP_Target_USERNAME = lDAP_Target_USERNAME;
    }

    public static String getLDAP_Target_PATH() {
        return LDAP_Target_PATH;
    }

    public static void setLDAP_Target_PATH(String lDAP_Target_PATH) {
        UtilProperties.LDAP_Target_PATH = lDAP_Target_PATH;
    }

    public static String getLDAP_Target_PASSWORD() {
        return LDAP_Target_PASSWORD;
    }

    public static void setLDAP_Target_PASSWORD(String lDAP_Target_PASSWORD) {
        UtilProperties.LDAP_Target_PASSWORD = lDAP_Target_PASSWORD;
    }

    public static String getEmailUrl() {
        return emailUrl;
    }

    public static void setEmailUrl(String emailUrl) {
        UtilProperties.emailUrl = emailUrl;
    }

    public static String getFromId() {
        return fromId;
    }

    public static void setFromId(String fromId) {
        UtilProperties.fromId = fromId;
    }

    public static String getSubjectForUpdateRole() {
        return subjectForUpdateRole;
    }

    public static void setSubjectForUpdateRole(String subjectForUpdateRole) {
        UtilProperties.subjectForUpdateRole = subjectForUpdateRole;
    }

    public static String getOIDServiceUrl() {
        return OIDServiceUrl;
    }

    public static void setOIDServiceUrl(String oIDServiceUrl) {
        OIDServiceUrl = oIDServiceUrl;
    }

    public static String getOSBIntegrationURL() {
        return OSBIntegrationURL;
    }

    public static void setOSBIntegrationURL(String oSBIntegrationURL) {
        OSBIntegrationURL = oSBIntegrationURL;
    }


    /**
     * @return the userRoleSearchUrl
     */
    public static String getUserRoleSearchUrl() {
        return userRoleSearchUrl;
    }

    /**
     * @param userRoleSearchUrl the userRoleSearchUrl to set
     */
    public static void setUserRoleSearchUrl(String userRoleSearchUrl) {
        UtilProperties.userRoleSearchUrl = userRoleSearchUrl;
    }

    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("LDAP_HOSTNAME") != null) {
                LDAP_HOSTNAME = properties.getProperty("LDAP_HOSTNAME");
            }

            if (properties.getProperty("LDAP_PORT") != null) {
                LDAP_PORT = properties.getProperty("LDAP_PORT");
            }

            if (properties.getProperty("LDAP_USERNAME") != null) {
                LDAP_USERNAME = properties.getProperty("LDAP_USERNAME");
            }

            if (properties.getProperty("LDAP_PASSWORD") != null) {
                LDAP_PASSWORD = properties.getProperty("LDAP_PASSWORD");
            }

            if (properties.getProperty("LDAP_Target_HOSTNAME") != null) {
                LDAP_Target_HOSTNAME = properties
                        .getProperty("LDAP_Target_HOSTNAME");
            }

            if (properties.getProperty("LDAP_Target_PORT") != null) {
                LDAP_Target_PORT = properties.getProperty("LDAP_Target_PORT");
            }

            if (properties.getProperty("LDAP_Target_USERNAME") != null) {
                LDAP_Target_USERNAME = properties
                        .getProperty("LDAP_Target_USERNAME");
            }
            if (properties.getProperty("LDAP_Target_PATH") != null) {
                LDAP_Target_PATH = properties.getProperty("LDAP_Target_PATH");
            }

            if (properties.getProperty("LDAP_Target_PASSWORD") != null) {
                LDAP_Target_PASSWORD = properties
                        .getProperty("LDAP_Target_PASSWORD");
            }

            if (properties.getProperty("emailUrl") != null) {
                emailUrl = properties.getProperty("emailUrl");
            }

            if (properties.getProperty("fromId") != null) {
                fromId = properties.getProperty("fromId");
            }

            if (properties.getProperty("subjectForUpdateRole") != null) {
                subjectForUpdateRole = properties
                        .getProperty("subjectForUpdateRole");
            }

            if (properties.getProperty("OIDServiceUrl") != null) {
                OIDServiceUrl = properties.getProperty("OIDServiceUrl");
            }

            if (properties.getProperty("OSBIntegrationURL") != null) {
                OSBIntegrationURL = properties.getProperty("OSBIntegrationURL");
            }
            
            if (properties.getProperty("userRoleSearchUrl") != null) {
                userRoleSearchUrl = properties.getProperty("userRoleSearchUrl");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to load properties :" + e.getMessage(), e);

        }
    }
}