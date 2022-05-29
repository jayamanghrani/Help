package com.tcs.umsuser.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

public class UtilProperties {
    private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);
    public static String oidUrl;
    public static String oidAddUrl;
    public static String osbUrl;
    public static String ackUrl;
    public static String jiraUpdateUrl;
    public static int Hour_schedular;
    public static int Minute_schedular;
    public static String roleUpdateUrl;
    public static String workflowServiceUrl;
    
	public static int getHour_schedular() {
		return Hour_schedular;
	}

	public static void setHour_schedular(int hour_schedular) {
		Hour_schedular = hour_schedular;
	}

	public static int getMinute_schedular() {
		return Minute_schedular;
	}

	public static void setMinute_schedular(int minute_schedular) {
		Minute_schedular = minute_schedular;
	}

	public static String getAckUrl() {
		return ackUrl;
	}

	public static void setAckUrl(String ackUrl) {
		UtilProperties.ackUrl = ackUrl;
	}

	public static String getOidAddUrl() {
		return oidAddUrl;
	}

	public static void setOidAddUrl(String oidAddUrl) {
		UtilProperties.oidAddUrl = oidAddUrl;
	}

	public static String getOidUrl() {
		return oidUrl;
	}

	public static void setOidUrl(String oidUrl) {
		UtilProperties.oidUrl = oidUrl;
	}

	public static String getOsbUrl() {
		return osbUrl;
	}

	public static void setOsbUrl(String osbUrl) {
		UtilProperties.osbUrl = osbUrl;
	}

	/**
     * @return the jiraUpdateUrl
     */
    public static String getJiraUpdateUrl() {
        return jiraUpdateUrl;
    }

    /**
     * @param jiraUpdateUrl the jiraUpdateUrl to set
     */
    public static void setJiraUpdateUrl(String jiraUpdateUrl) {
        UtilProperties.jiraUpdateUrl = jiraUpdateUrl;
    }

    /**
     * @return the roleUpdateUrl
     */
    public static String getRoleUpdateUrl() {
        return roleUpdateUrl;
    }

    /**
     * @param roleUpdateUrl the roleUpdateUrl to set
     */
    public static void setRoleUpdateUrl(String roleUpdateUrl) {
        UtilProperties.roleUpdateUrl = roleUpdateUrl;
    }

    
    /**
     * @return the workflowServiceUrl
     */
    public static String getWorkflowServiceUrl() {
        return workflowServiceUrl;
    }

    /**
     * @param workflowServiceUrl the workflowServiceUrl to set
     */
    public static void setWorkflowServiceUrl(String workflowServiceUrl) {
        UtilProperties.workflowServiceUrl = workflowServiceUrl;
    }

    public static void load(String path) {
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream(new File(path)));

            if (properties.getProperty("oidUrl") != null) {
            	oidUrl = properties.getProperty("oidUrl");
            }
            if (properties.getProperty("osbUrl") != null) {
                osbUrl = properties.getProperty("osbUrl");
            }  
            if (properties.getProperty("oidAddUrl") != null) {
            	oidAddUrl = properties.getProperty("oidAddUrl");
            }
            if (properties.getProperty("ackUrl") != null) {
            	ackUrl = properties.getProperty("ackUrl");
            }
            if (properties.getProperty("Hour_schedular") != null) {
            	Hour_schedular = Integer.parseInt(properties.getProperty("Hour_schedular"));
            }
            if (properties.getProperty("Minute_schedular") != null) {
            	Minute_schedular = Integer.parseInt(properties.getProperty("Minute_schedular"));
            }
            
            if (properties.getProperty("jiraUpdateUrl") != null) {
                jiraUpdateUrl = properties.getProperty("jiraUpdateUrl");
            }
            
            if (properties.getProperty("roleUpdateUrl") != null) {
                roleUpdateUrl = properties.getProperty("roleUpdateUrl");
            }
            
            if (properties.getProperty("workflowServiceUrl") != null) {
                workflowServiceUrl = properties.getProperty("workflowServiceUrl");
            }
        }

        catch (Exception e) {
            e.printStackTrace();
            LOGGER.error("Unable to load properties :" + e.getMessage(), e);

        }
    }
}