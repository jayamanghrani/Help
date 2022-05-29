package com.tcs.jira.vo.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);

    private static String jiraUserId;
    private static String jiraPassword;
    private static String jiraGetUserURL;
    private static String jiraUserCreationURL;
    private static String jiraCreateIssueURL;
    private static String jiraUserAssignedToGroupURL;
    private static String jiraUserRemoveFromGroupURL;
    private static String jiraUserDisableURL;
    private static String jiraGetIssueURL;
    private static String jiraUpdateIssueURL;
    private static String createIssueProjectId;
    private static String createIssueTypeId;
    private static String userNameCustomAttr;
    private static String userIdCustomAttr;
    private static String phoneNoCustomAttr;
    private static String locationCustomAttr;
    private static String companyCustomAttr;
    private static String officeCodeCustomAttr;
    private static String jiraCheckIssueURL;
    /**
     * @return the jiraUserId
     */
    public static String getJiraUserId() {
        return jiraUserId;
    }

    /**
     * @param jiraUserId the jiraUserId to set
     */
    public static void setJiraUserId(String jiraUserId) {
        UtilProperties.jiraUserId = jiraUserId;
    }

    /**
     * @return the jiraPassword
     */
    public static String getJiraPassword() {
        return jiraPassword;
    }



    /**
     * @param jiraPassword the jiraPassword to set
     */
    public static void setJiraPassword(String jiraPassword) {
        UtilProperties.jiraPassword = jiraPassword;
    }



    /**
     * @return the jiraGetUserURL
     */
    public static String getJiraGetUserURL() {
        return jiraGetUserURL;
    }



    /**
     * @param jiraGetUserURL the jiraGetUserURL to set
     */
    public static void setJiraGetUserURL(String jiraGetUserURL) {
        UtilProperties.jiraGetUserURL = jiraGetUserURL;
    }



    /**
     * @return the jiraUserCreationURL
     */
    public static String getJiraUserCreationURL() {
        return jiraUserCreationURL;
    }



    /**
     * @param jiraUserCreationURL the jiraUserCreationURL to set
     */
    public static void setJiraUserCreationURL(String jiraUserCreationURL) {
        UtilProperties.jiraUserCreationURL = jiraUserCreationURL;
    }



    /**
     * @return the jiraCreateIssueURL
     */
    public static String getJiraCreateIssueURL() {
        return jiraCreateIssueURL;
    }



    /**
     * @param jiraCreateIssueURL the jiraCreateIssueURL to set
     */
    public static void setJiraCreateIssueURL(String jiraCreateIssueURL) {
        UtilProperties.jiraCreateIssueURL = jiraCreateIssueURL;
    }



    /**
     * @return the jiraUserAssignedToGroupURL
     */
    public static String getJiraUserAssignedToGroupURL() {
        return jiraUserAssignedToGroupURL;
    }



    /**
     * @param jiraUserAssignedToGroupURL the jiraUserAssignedToGroupURL to set
     */
    public static void setJiraUserAssignedToGroupURL(
            String jiraUserAssignedToGroupURL) {
        UtilProperties.jiraUserAssignedToGroupURL = jiraUserAssignedToGroupURL;
    }



    /**
     * @return the jiraUserRemoveFromGroupURL
     */
    public static String getJiraUserRemoveFromGroupURL() {
        return jiraUserRemoveFromGroupURL;
    }



    /**
     * @param jiraUserRemoveFromGroupURL the jiraUserRemoveFromGroupURL to set
     */
    public static void setJiraUserRemoveFromGroupURL(
            String jiraUserRemoveFromGroupURL) {
        UtilProperties.jiraUserRemoveFromGroupURL = jiraUserRemoveFromGroupURL;
    }



    /**
     * @return the jiraUserDisableURL
     */
    public static String getJiraUserDisableURL() {
        return jiraUserDisableURL;
    }



    /**
     * @param jiraUserDisableURL the jiraUserDisableURL to set
     */
    public static void setJiraUserDisableURL(String jiraUserDisableURL) {
        UtilProperties.jiraUserDisableURL = jiraUserDisableURL;
    }



    /**
     * @return the createIssueProjectId
     */
    public static String getCreateIssueProjectId() {
        return createIssueProjectId;
    }



    /**
     * @param createIssueProjectId the createIssueProjectId to set
     */
    public static void setCreateIssueProjectId(String createIssueProjectId) {
        UtilProperties.createIssueProjectId = createIssueProjectId;
    }



    /**
     * @return the createIssueTypeId
     */
    public static String getCreateIssueTypeId() {
        return createIssueTypeId;
    }



    /**
     * @param createIssueTypeId the createIssueTypeId to set
     */
    public static void setCreateIssueTypeId(String createIssueTypeId) {
        UtilProperties.createIssueTypeId = createIssueTypeId;
    }



    /**
     * @return the userNameCustomAttr
     */
    public static String getUserNameCustomAttr() {
        return userNameCustomAttr;
    }

    /**
     * @param userNameCustomAttr the userNameCustomAttr to set
     */
    public static void setUserNameCustomAttr(String userNameCustomAttr) {
        UtilProperties.userNameCustomAttr = userNameCustomAttr;
    }

    /**
     * @return the userIdCustomAttr
     */
    public static String getUserIdCustomAttr() {
        return userIdCustomAttr;
    }

    /**
     * @param userIdCustomAttr the userIdCustomAttr to set
     */
    public static void setUserIdCustomAttr(String userIdCustomAttr) {
        UtilProperties.userIdCustomAttr = userIdCustomAttr;
    }

    /**
     * @return the phoneNoCustomAttr
     */
    public static String getPhoneNoCustomAttr() {
        return phoneNoCustomAttr;
    }

    /**
     * @param phoneNoCustomAttr the phoneNoCustomAttr to set
     */
    public static void setPhoneNoCustomAttr(String phoneNoCustomAttr) {
        UtilProperties.phoneNoCustomAttr = phoneNoCustomAttr;
    }

    /**
     * @return the locationCustomAttr
     */
    public static String getLocationCustomAttr() {
        return locationCustomAttr;
    }

    /**
     * @param locationCustomAttr the locationCustomAttr to set
     */
    public static void setLocationCustomAttr(String locationCustomAttr) {
        UtilProperties.locationCustomAttr = locationCustomAttr;
    }

    /**
     * @return the companyCustomAttr
     */
    public static String getCompanyCustomAttr() {
        return companyCustomAttr;
    }

    /**
     * @param companyCustomAttr the companyCustomAttr to set
     */
    public static void setCompanyCustomAttr(String companyCustomAttr) {
        UtilProperties.companyCustomAttr = companyCustomAttr;
    }

    /**
     * @return the officeCodeCustomAttr
     */
    public static String getOfficeCodeCustomAttr() {
        return officeCodeCustomAttr;
    }

    /**
     * @param officeCodeCustomAttr the officeCodeCustomAttr to set
     */
    public static void setOfficeCodeCustomAttr(String officeCodeCustomAttr) {
        UtilProperties.officeCodeCustomAttr = officeCodeCustomAttr;
    }

    
    /**
     * @return the jiraGetIssueURL
     */
    public static String getJiraGetIssueURL() {
        return jiraGetIssueURL;
    }

    /**
     * @param jiraGetIssueURL the jiraGetIssueURL to set
     */
    public static void setJiraGetIssueURL(String jiraGetIssueURL) {
        UtilProperties.jiraGetIssueURL = jiraGetIssueURL;
    }

    /**
     * @return the jiraUpdateIssueURL
     */
    public static String getJiraUpdateIssueURL() {
        return jiraUpdateIssueURL;
    }

    /**
     * @param jiraUpdateIssueURL the jiraUpdateIssueURL to set
     */
    public static void setJiraUpdateIssueURL(String jiraUpdateIssueURL) {
        UtilProperties.jiraUpdateIssueURL = jiraUpdateIssueURL;
    }
    
    

    /**
     * @return the jiraCheckIssueURL
     */
    public static String getJiraCheckIssueURL() {
        return jiraCheckIssueURL;
    }

    /**
     * @param jiraCheckIssueURL the jiraCheckIssueURL to set
     */
    public static void setJiraCheckIssueURL(String jiraCheckIssueURL) {
        UtilProperties.jiraCheckIssueURL = jiraCheckIssueURL;
    }

    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("jiraUserId") != null) {
            	jiraUserId = properties
                        .getProperty("jiraUserId");
            }
            if (properties.getProperty("jiraPassword") != null) {
            	jiraPassword =new String(Base64.getDecoder().decode(properties.getProperty("jiraPassword")));
            }
            if (properties.getProperty("jiraGetUserURL") != null) {
                jiraGetUserURL = properties
                        .getProperty("jiraGetUserURL");
            }
            if (properties.getProperty("jiraUserCreationURL") != null) {
            	jiraUserCreationURL = properties
                        .getProperty("jiraUserCreationURL");
            }
          
            if (properties.getProperty("jiraCreateIssueURL") != null) {
            	jiraCreateIssueURL = properties
                        .getProperty("jiraCreateIssueURL");
            }
          
            if (properties.getProperty("jiraUserAssignedToGroupURL") != null) {
                jiraUserAssignedToGroupURL = properties
                        .getProperty("jiraUserAssignedToGroupURL");
            }
            if (properties.getProperty("jiraUserRemoveFromGroupURL") != null) {
                jiraUserRemoveFromGroupURL = properties
                        .getProperty("jiraUserRemoveFromGroupURL");
            }
            if (properties.getProperty("jiraUserDisableURL") != null) {
                jiraUserDisableURL = properties
                        .getProperty("jiraUserDisableURL");
            }
            
            if (properties.getProperty("jiraGetIssueURL") != null) {
                jiraGetIssueURL = properties
                        .getProperty("jiraGetIssueURL");
            }
            
            if (properties.getProperty("jiraUpdateIssueURL") != null) {
                jiraUpdateIssueURL = properties
                        .getProperty("jiraUpdateIssueURL");
            }
            if (properties.getProperty("createIssueProjectId") != null) {
            	createIssueProjectId = properties
                        .getProperty("createIssueProjectId");
            }   
            if (properties.getProperty("createIssueTypeId") != null) {
            	createIssueTypeId = properties
                        .getProperty("createIssueTypeId");
            }
            
            if (properties.getProperty("userNameCustomAttr") != null) {
                userNameCustomAttr = properties
                        .getProperty("userNameCustomAttr");
            }
            if (properties.getProperty("userIdCustomAttr") != null) {
                userIdCustomAttr = properties
                        .getProperty("userIdCustomAttr");
            }
            if (properties.getProperty("phoneNoCustomAttr") != null) {
                phoneNoCustomAttr = properties
                        .getProperty("phoneNoCustomAttr");
            }
            if (properties.getProperty("locationCustomAttr") != null) {
                locationCustomAttr = properties
                        .getProperty("locationCustomAttr");
            }
            if (properties.getProperty("companyCustomAttr") != null) {
                companyCustomAttr = properties
                        .getProperty("companyCustomAttr");
            }
            if (properties.getProperty("officeCodeCustomAttr") != null) {
                officeCodeCustomAttr = properties
                        .getProperty("officeCodeCustomAttr");
            }     
            if (properties.getProperty("jiraCheckIssueURL") != null) {
                jiraCheckIssueURL = properties
                        .getProperty("jiraCheckIssueURL");
            }     
        }

        catch (Exception e) {
            LOGGER.error("Unable to load properties :" + e.getMessage(), e);

        }
    }
}