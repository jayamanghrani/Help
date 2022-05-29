package com.tcs.umsapp.smsmail.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {

    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);
    private static String emailIP;
    private static String emailPort;
    private static String fromID;
    private static String defaultText;
    private static String proxyHost;
    private static String port;
    private static String userId;
    private static String password;
    private static String smsSenderId;
    private static String smsProviderUrl;
    private static String mailSubject;
    private static String defaultSubject;
    private static String mailSuccessMessage;
    private static String failureMessage;
    private static String smsSuccess;
    private static String smsFailed;

    public static String getEmailIP() {
        return emailIP;
    }

    public static void setEmailIP(String emailIP) {
        UtilProperties.emailIP = emailIP;
    }

    public static String getEmailPort() {
        return emailPort;
    }

    public static void setEmailPort(String emailPort) {
        UtilProperties.emailPort = emailPort;
    }

    public static String getFromID() {
        return fromID;
    }

    public static void setFromID(String fromID) {
        UtilProperties.fromID = fromID;
    }

    public static String getDefaultText() {
        return defaultText;
    }

    public static void setDefaultText(String defaultText) {
        UtilProperties.defaultText = defaultText;
    }

    public static String getProxyHost() {
        return proxyHost;
    }

    public static void setProxyHost(String proxyHost) {
        UtilProperties.proxyHost = proxyHost;
    }

    public static String getPort() {
        return port;
    }

    public static void setPort(String port) {
        UtilProperties.port = port;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String userId) {
        UtilProperties.userId = userId;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        UtilProperties.password = password;
    }

    public static String getSmsProviderUrl() {
        return smsProviderUrl;
    }

    public static void setSmsProviderUrl(String smsProviderUrl) {
        UtilProperties.smsProviderUrl = smsProviderUrl;
    }

    public static String getMailSubject() {
        return mailSubject;
    }

    public static void setMailSubject(String mailSubject) {
        UtilProperties.mailSubject = mailSubject;
    }

    public static String getDefaultSubject() {
        return defaultSubject;
    }

    public static void setDefaultSubject(String defaultSubject) {
        UtilProperties.defaultSubject = defaultSubject;
    }

    public static String getMailSuccessMessage() {
        return mailSuccessMessage;
    }

    public static void setMailSuccessMessage(String mailSuccessMessage) {
        UtilProperties.mailSuccessMessage = mailSuccessMessage;
    }

    public static String getFailureMessage() {
        return failureMessage;
    }

    public static void setFailureMessage(String failureMessage) {
        UtilProperties.failureMessage = failureMessage;
    }

    public static String getSmsSuccess() {
        return smsSuccess;
    }

    public static void setSmsSuccess(String smsSuccess) {
        UtilProperties.smsSuccess = smsSuccess;
    }

    public static String getSmsFailed() {
        return smsFailed;
    }

    public static void setSmsFailed(String smsFailed) {
        UtilProperties.smsFailed = smsFailed;
    }

    public static Logger getLogger() {
        return LOGGER;
    }

    /**
     * @return the smsSenderId
     */
    public static String getSmsSenderId() {
        return smsSenderId;
    }

    /**
     * @param smsSenderId the smsSenderId to set
     */
    public static void setSmsSenderId(String smsSenderId) {
        UtilProperties.smsSenderId = smsSenderId;
    }
    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("emailIP") != null) {
                emailIP = properties.getProperty("emailIP");
            }

            if (properties.getProperty("emailPort") != null) {
                emailPort = properties.getProperty("emailPort");
            }

            if (properties.getProperty("fromID") != null) {
                fromID = properties.getProperty("fromID");
            }

            if (properties.getProperty("defaultText") != null) {
                defaultText = properties.getProperty("defaultText");
            }

            if (properties.getProperty("proxyHost") != null) {
                proxyHost = properties.getProperty("proxyHost");
            }

            if (properties.getProperty("port") != null) {
                port = properties.getProperty("port");
            }

            if (properties.getProperty("userId") != null) {
                userId = properties.getProperty("userId");
            }
            if (properties.getProperty("password") != null) {
                password = new String(Base64.getDecoder().decode(properties.getProperty("password")));
            }

            if (properties.getProperty("smsSenderId") != null) {
                smsSenderId = properties.getProperty("smsSenderId");
            }
            if (properties.getProperty("smsProviderUrl") != null) {
                smsProviderUrl = properties.getProperty("smsProviderUrl");
            }
            if (properties.getProperty("mailSubject") != null) {
                mailSubject = properties.getProperty("mailSubject");
            }
            if (properties.getProperty("defaultSubject") != null) {
                defaultSubject = properties.getProperty("defaultSubject");
            }
            if (properties.getProperty("mailSuccessMessage") != null) {
                mailSuccessMessage = properties
                        .getProperty("mailSuccessMessage");
            }
            if (properties.getProperty("failureMessage") != null) {
                failureMessage = properties.getProperty("failureMessage");
            }
            if (properties.getProperty("smsSuccess") != null) {
                smsSuccess = properties.getProperty("smsSuccess");
            }
            if (properties.getProperty("smsFailed") != null) {
                smsFailed = properties.getProperty("smsFailed");
            }
        } catch (Exception e) {
            LOGGER.error("Unable to load properties :" + e.getStackTrace(), e);
        }
    }



}