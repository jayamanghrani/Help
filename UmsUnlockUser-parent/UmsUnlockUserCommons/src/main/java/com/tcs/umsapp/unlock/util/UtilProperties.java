package com.tcs.umsapp.unlock.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);
    private static String emailUrl;
    private static String smsUrl;
    private static String mailFilter;
    private static String ldapHostName;
    private static String ldapPort;
    private static String userName;
    private static String password;
    private static String fromId;
    private static String emailSubject;

    public static String getEmailUrl() {
        return emailUrl;
    }


    public static void setEmailUrl(String emailUrl) {
        UtilProperties.emailUrl = emailUrl;
    }


    public static String getSmsUrl() {
        return smsUrl;
    }


    public static void setSmsUrl(String smsUrl) {
        UtilProperties.smsUrl = smsUrl;
    }


    public static String getMailFilter() {
        return mailFilter;
    }


    public static void setMailFilter(String mailFilter) {
        UtilProperties.mailFilter = mailFilter;
    }


    public static String getLdapHostName() {
        return ldapHostName;
    }


    public static void setLdapHostName(String ldapHostName) {
        UtilProperties.ldapHostName = ldapHostName;
    }


    public static String getLdapPort() {
        return ldapPort;
    }


    public static void setLdapPort(String ldapPort) {
        UtilProperties.ldapPort = ldapPort;
    }


    public static String getUserName() {
        return userName;
    }


    public static void setUserName(String userName) {
        UtilProperties.userName = userName;
    }


    public static String getPassword() {
        return password;
    }


    public static void setPassword(String password) {
        UtilProperties.password = password;
    }


    /**
     * @return the fromId
     */
    public static String getFromId() {
        return fromId;
    }


    /**
     * @param fromId the fromId to set
     */
    public static void setFromId(String fromId) {
        UtilProperties.fromId = fromId;
    }


    /**
     * @return the emailSubject
     */
    public static String getEmailSubject() {
        return emailSubject;
    }


    /**
     * @param emailSubject the emailSubject to set
     */
    public static void setEmailSubject(String emailSubject) {
        UtilProperties.emailSubject = emailSubject;
    }


    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("smsUrl") != null) {
                smsUrl = properties.getProperty("smsUrl");
            }
            if (properties.getProperty("emailUrl") != null) {
                emailUrl = properties.getProperty("emailUrl");
            }
            if (properties.getProperty("mailFilter") != null) {
                mailFilter = properties.getProperty("mailFilter");
            }
            if (properties.getProperty("ldapHostName") != null) {
                ldapHostName = properties.getProperty("ldapHostName");
            }
            if (properties.getProperty("ldapPort") != null) {
                ldapPort = properties.getProperty("ldapPort");
            }
            if (properties.getProperty("userName") != null) {
                userName = properties.getProperty("userName");
            }
            if (properties.getProperty("password") != null) {
                password = properties.getProperty("password");
            }
            if (properties.getProperty("fromId") != null) {
                fromId = properties.getProperty("fromId");
            }
            if (properties.getProperty("emailSubject") != null) {
                emailSubject = properties.getProperty("emailSubject");
            }
           
        }

        catch (Exception e) {
            LOGGER.error("Unable to load properties :" + e.getMessage(), e);

        }
    }
}