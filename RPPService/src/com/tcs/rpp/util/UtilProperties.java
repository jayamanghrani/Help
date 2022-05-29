/**
 * 
 */
package com.tcs.rpp.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * @author 738566
 *
 */
public class UtilProperties {

    private static String loggerPath;
    private static String uploadPayFileUrl;
    private static String rppResponseUrl;
    private static String rppUploadStatusUrl;
    private static String ssoId;
    private static String secreteKey;
    private static String proxyHost;
    private static String proxyPort;
    private static String proxyType;
    private static String reverseFilePath;
    private static String payFilePath;
    private static String mailFrom;
    private static String password;
    private static String receipentList;
    private static String messageSubject;
    private static String smtpHost;
    private static String smtpPort;
    private static String smtpAuth;
    private static String sslEnable;
    private static String messageSignature;

    /**
     * @return the loggerPath
     */
    public static String getLoggerPath() {
        return loggerPath;
    }

    /**
     * @param loggerPath
     *            the loggerPath to set
     */
    public static void setLoggerPath(String loggerPath) {
        UtilProperties.loggerPath = loggerPath;
    }

    /**
     * @return the uploadPayFileUrl
     */
    public static String getUploadPayFileUrl() {
        return uploadPayFileUrl;
    }

    /**
     * @param uploadPayFileUrl
     *            the uploadPayFileUrl to set
     */
    public static void setUploadPayFileUrl(String uploadPayFileUrl) {
        UtilProperties.uploadPayFileUrl = uploadPayFileUrl;
    }

    /**
     * @return the rppResponseUrl
     */
    public static String getRppResponseUrl() {
        return rppResponseUrl;
    }

    /**
     * @param rppResponseUrl
     *            the rppResponseUrl to set
     */
    public static void setRppResponseUrl(String rppResponseUrl) {
        UtilProperties.rppResponseUrl = rppResponseUrl;
    }

    /**
     * @return the ssoId
     */
    public static String getSsoId() {
        return ssoId;
    }

    /**
     * @param ssoId
     *            the ssoId to set
     */
    public static void setSsoId(String ssoId) {
        UtilProperties.ssoId = ssoId;
    }

    /**
     * @return the secreteKey
     */
    public static String getSecreteKey() {
        return secreteKey;
    }

    /**
     * @param secreteKey
     *            the secreteKey to set
     */
    public static void setSecreteKey(String secreteKey) {
        UtilProperties.secreteKey = secreteKey;
    }

    /**
     * @return the proxyHost
     */
    public static String getProxyHost() {
        return proxyHost;
    }

    /**
     * @param proxyHost
     *            the proxyHost to set
     */
    public static void setProxyHost(String proxyHost) {
        UtilProperties.proxyHost = proxyHost;
    }

    /**
     * @return the proxyPort
     */
    public static String getProxyPort() {
        return proxyPort;
    }

    /**
     * @return the proxyType
     */
    public static String getProxyType() {
        return proxyType;
    }

    /**
     * @param proxyType
     *            the proxyType to set
     */
    public static void setProxyType(String proxyType) {
        UtilProperties.proxyType = proxyType;
    }

    /**
     * @param proxyPort
     *            the proxyPort to set
     */
    public static void setProxyPort(String proxyPort) {
        UtilProperties.proxyPort = proxyPort;
    }

    /**
     * @return the reverseFilePath
     */
    public static String getReverseFilePath() {
        return reverseFilePath;
    }

    /**
     * @param reverseFilePath
     *            the reverseFilePath to set
     */
    public static void setReverseFilePath(String reverseFilePath) {
        UtilProperties.reverseFilePath = reverseFilePath;
    }

    /**
     * @return the payFilePath
     */
    public static String getPayFilePath() {
        return payFilePath;
    }

    /**
     * @param payFilePath
     *            the payFilePath to set
     */
    public static void setPayFilePath(String payFilePath) {
        UtilProperties.payFilePath = payFilePath;
    }

    /**
     * @return the mailFrom
     */
    public static String getMailFrom() {
        return mailFrom;
    }

    /**
     * @param mailFrom
     *            the mailFrom to set
     */
    public static void setMailFrom(String mailFrom) {
        UtilProperties.mailFrom = mailFrom;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

    /**
     * @param password
     *            the password to set
     */
    public static void setPassword(String password) {
        UtilProperties.password = password;
    }

    /**
     * @return the receipentList
     */
    public static String getReceipentList() {
        return receipentList;
    }

    /**
     * @param receipentList
     *            the receipentList to set
     */
    public static void setReceipentList(String receipentList) {
        UtilProperties.receipentList = receipentList;
    }

    /**
     * @return the messageSubject
     */
    public static String getMessageSubject() {
        return messageSubject;
    }

    /**
     * @param messageSubject
     *            the messageSubject to set
     */
    public static void setMessageSubject(String messageSubject) {
        UtilProperties.messageSubject = messageSubject;
    }

    /**
     * @return the smtpHost
     */
    public static String getSmtpHost() {
        return smtpHost;
    }

    /**
     * @param smtpHost
     *            the smtpHost to set
     */
    public static void setSmtpHost(String smtpHost) {
        UtilProperties.smtpHost = smtpHost;
    }

    /**
     * @return the smtpPort
     */
    public static String getSmtpPort() {
        return smtpPort;
    }

    /**
     * @param smtpPort
     *            the smtpPort to set
     */
    public static void setSmtpPort(String smtpPort) {
        UtilProperties.smtpPort = smtpPort;
    }

    /**
     * @return the smtpAuth
     */
    public static String getSmtpAuth() {
        return smtpAuth;
    }

    /**
     * @param smtpAuth
     *            the smtpAuth to set
     */
    public static void setSmtpAuth(String smtpAuth) {
        UtilProperties.smtpAuth = smtpAuth;
    }

    /**
     * @return the sslEnable
     */
    public static String getSslEnable() {
        return sslEnable;
    }

    /**
     * @param sslEnable
     *            the sslEnable to set
     */
    public static void setSslEnable(String sslEnable) {
        UtilProperties.sslEnable = sslEnable;
    }

    /**
     * @return the messageSignature
     */
    public static String getMessageSignature() {
        return messageSignature;
    }

    /**
     * @param messageSignature
     *            the messageSignature to set
     */
    public static void setMessageSignature(String messageSignature) {
        UtilProperties.messageSignature = messageSignature;
    }

    /**
     * @return the rppUploadStatusUrl
     */
    public static String getRppUploadStatusUrl() {
        return rppUploadStatusUrl;
    }

    /**
     * @param rppUploadStatusUrl the rppUploadStatusUrl to set
     */
    public static void setRppUploadStatusUrl(String rppUploadStatusUrl) {
        UtilProperties.rppUploadStatusUrl = rppUploadStatusUrl;
    }
    
    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("loggerPath") != null) {
                loggerPath = properties.getProperty("loggerPath");
            }

            if (properties.getProperty("uploadPayFileUrl") != null) {
                uploadPayFileUrl = properties.getProperty("uploadPayFileUrl");
            }

            if (properties.getProperty("rppResponseUrl") != null) {
                rppResponseUrl = properties.getProperty("rppResponseUrl");
            }
            
            if (properties.getProperty("rppUploadStatusUrl") != null) {
                rppUploadStatusUrl = properties.getProperty("rppUploadStatusUrl");
            }
            
            if (properties.getProperty("ssoId") != null) {
                ssoId = properties.getProperty("ssoId");
            }
            if (properties.getProperty("secreteKey") != null) {
                secreteKey = properties.getProperty("secreteKey");
            }

            if (properties.getProperty("proxyHost") != null) {
                proxyHost = properties.getProperty("proxyHost");
            }

            if (properties.getProperty("proxyPort") != null) {
                proxyPort = properties.getProperty("proxyPort");
            }
            if (properties.getProperty("proxyType") != null) {
                proxyType = properties.getProperty("proxyType");
            }
            if (properties.getProperty("reverseFilePath") != null) {
                reverseFilePath = properties.getProperty("reverseFilePath");
            }
            if (properties.getProperty("payFilePath") != null) {
                payFilePath = properties.getProperty("payFilePath");
            }

            if (properties.getProperty("mailFrom") != null) {
                mailFrom = properties.getProperty("mailFrom");
            }

            if (properties.getProperty("password") != null) {
                password = properties.getProperty("password");
            }
            if (properties.getProperty("receipentList") != null) {
                receipentList = properties.getProperty("receipentList");
            }
            if (properties.getProperty("messageSubject") != null) {
                messageSubject = properties.getProperty("messageSubject");
            }
            if (properties.getProperty("smtpHost") != null) {
                smtpHost = properties.getProperty("smtpHost");
            }
            if (properties.getProperty("smtpPort") != null) {
                smtpPort = properties.getProperty("smtpPort");
            }
            if (properties.getProperty("smtpAuth") != null) {
                smtpAuth = properties.getProperty("smtpAuth");
            }
            if (properties.getProperty("sslEnable") != null) {
                sslEnable = properties.getProperty("sslEnable");
            }
            if (properties.getProperty("messageSignature") != null) {
                messageSignature = properties.getProperty("messageSignature");
            }

        } catch (Exception e) {
            e.printStackTrace();
            Log.rpp_Log
                    .error("Unable to load properties :" + e.getMessage(), e);

        }
    }

   
}