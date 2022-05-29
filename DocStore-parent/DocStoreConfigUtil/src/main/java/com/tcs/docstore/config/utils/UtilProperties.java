package com.tcs.docstore.config.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.apache.log4j.Logger;

/**
 * @author 738566
 *
 */
public class UtilProperties {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(UtilProperties.class);
	
	private static String 	connectionType	;
	private static String 	emailIP	;
	private static String 	emailPort	;
	private static String 	emailId	;
	private static String 	fromID	;
	private static String 	defaultText	;
	private static String 	alfrescoIp	;
	private static String 	ldapHostName	;
	private static String 	ldapPort	;
	private static String 	ldapUserName	;
	private static String 	ldapPassword	;
	private static String 	mobile	;
	private static String 	UI_TIME_FORMAT	;
	private static String 	PROXY_HOST	;
	private static String 	PROXY_PASSWORD	;
	private static String 	PROXY_SERVER_PORT	;
	private static String 	PROXY_USER	;
	private static String 	USER_ID	;
	private static String 	PASSWORD	;
	private static String 	ENCODE_FORMAT	;
	private static String 	VERSION	;
	private static String 	SMS_PROVIDER_URL	;
	private static String 	HRMSDSName	;
	private static String 	ReportsDS	;
	private static String 	alfrescoAdmin	;
	private static String 	alfrescoPwd	;
	private static String 	dataSourceUrl	;
	private static String 	fileUploadPath	;
	private static String 	alfrescoUploadPath	;
	private static String 	dataSourceName	;
	private static String 	alfrescoSitePath	;
	private static String 	alfrescoCallTime	;
	private static String 	alfWebURL	;
	private static String 	alfEmpURL	;
	private static String 	successUploadURL	;
	private static String 	failureUploadURL	;
	private static String 	unAuthorisedUploadURL	;
	private static String 	cacheClearingURL	;
	private static String 	jdbcUrl	;
	private static String 	jdbcUserName	;
	private static String 	jdbcPassword	;
    private static String  validationErrorURL;

	/**
	 * @return the jdbcUrl
	 */
	public static String getJdbcUrl() {
		return jdbcUrl;
	}


	/**
	 * @param jdbcUrl the jdbcUrl to set
	 */
	public static void setJdbcUrl(String jdbcUrl) {
		UtilProperties.jdbcUrl = jdbcUrl;
	}


	/**
	 * @return the jdbcUserName
	 */
	public static String getJdbcUserName() {
		return jdbcUserName;
	}


	/**
	 * @param jdbcUserName the jdbcUserName to set
	 */
	public static void setJdbcUserName(String jdbcUserName) {
		UtilProperties.jdbcUserName = jdbcUserName;
	}


	/**
	 * @return the jdbcPassword
	 */
	public static String getJdbcPassword() {
		return jdbcPassword;
	}


	/**
	 * @param jdbcPassword the jdbcPassword to set
	 */
	public static void setJdbcPassword(String jdbcPassword) {
		UtilProperties.jdbcPassword = jdbcPassword;
	}


	/**
	 * @return the connectionType
	 */
	public static String getConnectionType() {
		return connectionType;
	}

	
	/**
	 * @param connectionType the connectionType to set
	 */
	public static void setConnectionType(String connectionType) {
		UtilProperties.connectionType = connectionType;
	}


	/**
	 * @return the emailIP
	 */
	public static String getEmailIP() {
		return emailIP;
	}


	/**
	 * @param emailIP the emailIP to set
	 */
	public static void setEmailIP(String emailIP) {
		UtilProperties.emailIP = emailIP;
	}


	/**
	 * @return the emailPort
	 */
	public static String getEmailPort() {
		return emailPort;
	}


	/**
	 * @param emailPort the emailPort to set
	 */
	public static void setEmailPort(String emailPort) {
		UtilProperties.emailPort = emailPort;
	}


	/**
	 * @return the emailId
	 */
	public static String getEmailId() {
		return emailId;
	}


	/**
	 * @param emailId the emailId to set
	 */
	public static void setEmailId(String emailId) {
		UtilProperties.emailId = emailId;
	}


	/**
	 * @return the fromID
	 */
	public static String getFromID() {
		return fromID;
	}


	/**
	 * @param fromID the fromID to set
	 */
	public static void setFromID(String fromID) {
		UtilProperties.fromID = fromID;
	}


	/**
	 * @return the defaultText
	 */
	public static String getDefaultText() {
		return defaultText;
	}


	/**
	 * @param defaultText the defaultText to set
	 */
	public static void setDefaultText(String defaultText) {
		UtilProperties.defaultText = defaultText;
	}


	/**
	 * @return the alfrescoIp
	 */
	public static String getAlfrescoIp() {
		return alfrescoIp;
	}


	/**
	 * @param alfrescoIp the alfrescoIp to set
	 */
	public static void setAlfrescoIp(String alfrescoIp) {
		UtilProperties.alfrescoIp = alfrescoIp;
	}


	/**
	 * @return the ldapHostName
	 */
	public static String getLdapHostName() {
		return ldapHostName;
	}


	/**
	 * @param ldapHostName the ldapHostName to set
	 */
	public static void setLdapHostName(String ldapHostName) {
		UtilProperties.ldapHostName = ldapHostName;
	}


	/**
	 * @return the ldapPort
	 */
	public static String getLdapPort() {
		return ldapPort;
	}


	/**
	 * @param ldapPort the ldapPort to set
	 */
	public static void setLdapPort(String ldapPort) {
		UtilProperties.ldapPort = ldapPort;
	}


	/**
	 * @return the ldapUserName
	 */
	public static String getLdapUserName() {
		return ldapUserName;
	}


	/**
	 * @param ldapUserName the ldapUserName to set
	 */
	public static void setLdapUserName(String ldapUserName) {
		UtilProperties.ldapUserName = ldapUserName;
	}


	/**
	 * @return the ldapPassword
	 */
	public static String getLdapPassword() {
		return ldapPassword;
	}


	/**
	 * @param ldapPassword the ldapPassword to set
	 */
	public static void setLdapPassword(String ldapPassword) {
		UtilProperties.ldapPassword = ldapPassword;
	}


	/**
	 * @return the mobile
	 */
	public static String getMobile() {
		return mobile;
	}


	/**
	 * @param mobile the mobile to set
	 */
	public static void setMobile(String mobile) {
		UtilProperties.mobile = mobile;
	}


	/**
	 * @return the uI_TIME_FORMAT
	 */
	public static String getUI_TIME_FORMAT() {
		return UI_TIME_FORMAT;
	}


	/**
	 * @param uI_TIME_FORMAT the uI_TIME_FORMAT to set
	 */
	public static void setUI_TIME_FORMAT(String uI_TIME_FORMAT) {
		UI_TIME_FORMAT = uI_TIME_FORMAT;
	}


	/**
	 * @return the pROXY_HOST
	 */
	public static String getPROXY_HOST() {
		return PROXY_HOST;
	}


	/**
	 * @param pROXY_HOST the pROXY_HOST to set
	 */
	public static void setPROXY_HOST(String pROXY_HOST) {
		PROXY_HOST = pROXY_HOST;
	}


	/**
	 * @return the pROXY_PASSWORD
	 */
	public static String getPROXY_PASSWORD() {
		return PROXY_PASSWORD;
	}


	/**
	 * @param pROXY_PASSWORD the pROXY_PASSWORD to set
	 */
	public static void setPROXY_PASSWORD(String pROXY_PASSWORD) {
		PROXY_PASSWORD = pROXY_PASSWORD;
	}


	/**
	 * @return the pROXY_SERVER_PORT
	 */
	public static String getPROXY_SERVER_PORT() {
		return PROXY_SERVER_PORT;
	}


	/**
	 * @param pROXY_SERVER_PORT the pROXY_SERVER_PORT to set
	 */
	public static void setPROXY_SERVER_PORT(String pROXY_SERVER_PORT) {
		PROXY_SERVER_PORT = pROXY_SERVER_PORT;
	}


	/**
	 * @return the pROXY_USER
	 */
	public static String getPROXY_USER() {
		return PROXY_USER;
	}


	/**
	 * @param pROXY_USER the pROXY_USER to set
	 */
	public static void setPROXY_USER(String pROXY_USER) {
		PROXY_USER = pROXY_USER;
	}


	/**
	 * @return the uSER_ID
	 */
	public static String getUSER_ID() {
		return USER_ID;
	}


	/**
	 * @param uSER_ID the uSER_ID to set
	 */
	public static void setUSER_ID(String uSER_ID) {
		USER_ID = uSER_ID;
	}


	/**
	 * @return the pASSWORD
	 */
	public static String getPASSWORD() {
		return PASSWORD;
	}


	/**
	 * @param pASSWORD the pASSWORD to set
	 */
	public static void setPASSWORD(String pASSWORD) {
		PASSWORD = pASSWORD;
	}


	/**
	 * @return the eNCODE_FORMAT
	 */
	public static String getENCODE_FORMAT() {
		return ENCODE_FORMAT;
	}


	/**
	 * @param eNCODE_FORMAT the eNCODE_FORMAT to set
	 */
	public static void setENCODE_FORMAT(String eNCODE_FORMAT) {
		ENCODE_FORMAT = eNCODE_FORMAT;
	}


	/**
	 * @return the vERSION
	 */
	public static String getVERSION() {
		return VERSION;
	}


	/**
	 * @param vERSION the vERSION to set
	 */
	public static void setVERSION(String vERSION) {
		VERSION = vERSION;
	}


	/**
	 * @return the sMS_PROVIDER_URL
	 */
	public static String getSMS_PROVIDER_URL() {
		return SMS_PROVIDER_URL;
	}


	/**
	 * @param sMS_PROVIDER_URL the sMS_PROVIDER_URL to set
	 */
	public static void setSMS_PROVIDER_URL(String sMS_PROVIDER_URL) {
		SMS_PROVIDER_URL = sMS_PROVIDER_URL;
	}


	/**
	 * @return the hRMSDSName
	 */
	public static String getHRMSDSName() {
		return HRMSDSName;
	}


	/**
	 * @param hRMSDSName the hRMSDSName to set
	 */
	public static void setHRMSDSName(String hRMSDSName) {
		HRMSDSName = hRMSDSName;
	}


	/**
	 * @return the reportsDS
	 */
	public static String getReportsDS() {
		return ReportsDS;
	}


	/**
	 * @param reportsDS the reportsDS to set
	 */
	public static void setReportsDS(String reportsDS) {
		ReportsDS = reportsDS;
	}


	/**
	 * @return the alfrescoAdmin
	 */
	public static String getAlfrescoAdmin() {
		return alfrescoAdmin;
	}


	/**
	 * @param alfrescoAdmin the alfrescoAdmin to set
	 */
	public static void setAlfrescoAdmin(String alfrescoAdmin) {
		UtilProperties.alfrescoAdmin = alfrescoAdmin;
	}


	/**
	 * @return the alfrescoPwd
	 */
	public static String getAlfrescoPwd() {
		return alfrescoPwd;
	}


	/**
	 * @param alfrescoPwd the alfrescoPwd to set
	 */
	public static void setAlfrescoPwd(String alfrescoPwd) {
		UtilProperties.alfrescoPwd = alfrescoPwd;
	}


	/**
	 * @return the dataSourceUrl
	 */
	public static String getDataSourceUrl() {
		return dataSourceUrl;
	}


	/**
	 * @param dataSourceUrl the dataSourceUrl to set
	 */
	public static void setDataSourceUrl(String dataSourceUrl) {
		UtilProperties.dataSourceUrl = dataSourceUrl;
	}


	/**
	 * @return the fileUploadPath
	 */
	public static String getFileUploadPath() {
		return fileUploadPath;
	}


	/**
	 * @param fileUploadPath the fileUploadPath to set
	 */
	public static void setFileUploadPath(String fileUploadPath) {
		UtilProperties.fileUploadPath = fileUploadPath;
	}


	/**
	 * @return the alfrescoUploadPath
	 */
	public static String getAlfrescoUploadPath() {
		return alfrescoUploadPath;
	}


	/**
	 * @param alfrescoUploadPath the alfrescoUploadPath to set
	 */
	public static void setAlfrescoUploadPath(String alfrescoUploadPath) {
		UtilProperties.alfrescoUploadPath = alfrescoUploadPath;
	}


	/**
	 * @return the dataSourceName
	 */
	public static String getDataSourceName() {
		return dataSourceName;
	}


	/**
	 * @param dataSourceName the dataSourceName to set
	 */
	public static void setDataSourceName(String dataSourceName) {
		UtilProperties.dataSourceName = dataSourceName;
	}


	/**
	 * @return the alfrescoSitePath
	 */
	public static String getAlfrescoSitePath() {
		return alfrescoSitePath;
	}


	/**
	 * @param alfrescoSitePath the alfrescoSitePath to set
	 */
	public static void setAlfrescoSitePath(String alfrescoSitePath) {
		UtilProperties.alfrescoSitePath = alfrescoSitePath;
	}


	/**
	 * @return the alfrescoCallTime
	 */
	public static String getAlfrescoCallTime() {
		return alfrescoCallTime;
	}


	/**
	 * @param alfrescoCallTime the alfrescoCallTime to set
	 */
	public static void setAlfrescoCallTime(String alfrescoCallTime) {
		UtilProperties.alfrescoCallTime = alfrescoCallTime;
	}


	/**
	 * @return the alfWebURL
	 */
	public static String getAlfWebURL() {
		return alfWebURL;
	}


	/**
	 * @param alfWebURL the alfWebURL to set
	 */
	public static void setAlfWebURL(String alfWebURL) {
		UtilProperties.alfWebURL = alfWebURL;
	}


	/**
	 * @return the alfEmpURL
	 */
	public static String getAlfEmpURL() {
		return alfEmpURL;
	}


	/**
	 * @param alfEmpURL the alfEmpURL to set
	 */
	public static void setAlfEmpURL(String alfEmpURL) {
		UtilProperties.alfEmpURL = alfEmpURL;
	}


	/**
	 * @return the successUploadURL
	 */
	public static String getSuccessUploadURL() {
		return successUploadURL;
	}


	/**
	 * @param successUploadURL the successUploadURL to set
	 */
	public static void setSuccessUploadURL(String successUploadURL) {
		UtilProperties.successUploadURL = successUploadURL;
	}


	/**
	 * @return the failureUploadURL
	 */
	public static String getFailureUploadURL() {
		return failureUploadURL;
	}


	/**
	 * @param failureUploadURL the failureUploadURL to set
	 */
	public static void setFailureUploadURL(String failureUploadURL) {
		UtilProperties.failureUploadURL = failureUploadURL;
	}


	/**
	 * @return the unAuthorisedUploadURL
	 */
	public static String getUnAuthorisedUploadURL() {
		return unAuthorisedUploadURL;
	}


	/**
	 * @param unAuthorisedUploadURL the unAuthorisedUploadURL to set
	 */
	public static void setUnAuthorisedUploadURL(String unAuthorisedUploadURL) {
		UtilProperties.unAuthorisedUploadURL = unAuthorisedUploadURL;
	}


	/**
	 * @return the cacheClearingURL
	 */
	public static String getCacheClearingURL() {
		return cacheClearingURL;
	}


	/**
	 * @param cacheClearingURL the cacheClearingURL to set
	 */
	public static void setCacheClearingURL(String cacheClearingURL) {
		UtilProperties.cacheClearingURL = cacheClearingURL;
	}



	/**
	 * @return the validationErrorURL
	 */
	public static String getValidationErrorURL() {
		return validationErrorURL;
	}


	/**
	 * @param errorURL the errorURL to set
	 */
	public static void setValidationErrorURL(String validationErrorURL) {
		UtilProperties.validationErrorURL = validationErrorURL;
	}


	public static void load(String path)
	  {
	    Properties properties = new Properties();
	    try
	    {
	  //  properties.load(new FileInputStream(new File("D:\\EmployeePortalRepository\\EmployeePortalRepository\\DocStore-parent\\DocStoreConfigUtil\\src\\main\\resources\\config.properties")));
	      properties.load(new FileInputStream(new File(path)));
      
	      if (properties.getProperty("connectionType") != null) {
	    	  connectionType = properties.get("connectionType").toString();
	      }

	      if (properties.getProperty("emailIP") != null) {
	    	  emailIP = properties.getProperty("emailIP").toString();
	      }
	     
	        if (properties.getProperty("emailPort") != null) {
	        	emailPort = properties.getProperty("emailPort").toString();
	        }
	        if (properties.getProperty("emailId") != null) {
	        	emailId = properties.getProperty("emailId").toString();
	          }
	        if (properties.getProperty("fromID") != null) {
	        	fromID = properties.getProperty("fromID").toString();
	          }
	        if (properties.getProperty("defaultText") != null) {
	        	defaultText = properties.getProperty("defaultText").toString();
	          }
	        if (properties.getProperty("alfrescoIp") != null) {
	        	alfrescoIp = properties.getProperty("alfrescoIp").toString();
	          }
	        if (properties.getProperty("ldapHostName") != null) {
	        	ldapHostName = properties.getProperty("ldapHostName").toString();
	          }
	        if (properties.getProperty("ldapPort") != null) {
	        	ldapPort = properties.getProperty("ldapPort").toString();
	          }
	        if (properties.getProperty("ldapUserName") != null) {
	        	ldapUserName = properties.getProperty("ldapUserName").toString();
	          }
	        if (properties.getProperty("ldapPassword") != null) {
	        	ldapPassword = properties.getProperty("ldapPassword").toString();
	          }
	        if (properties.getProperty("mobile") != null) {
	        	mobile = properties.getProperty("mobile").toString();
	          }
	        if (properties.getProperty("UI_TIME_FORMAT") != null) {
	        	UI_TIME_FORMAT = properties.getProperty("UI_TIME_FORMAT").toString();
	          }
	        if (properties.getProperty("PROXY_HOST") != null) {
	        	PROXY_HOST = properties.getProperty("PROXY_HOST").toString();
	          }
	        if (properties.getProperty("PROXY_PASSWORD") != null) {
	        	PROXY_PASSWORD = properties.getProperty("PROXY_PASSWORD").toString();
	          }
	        if (properties.getProperty("PROXY_SERVER_PORT") != null) {
	        	PROXY_SERVER_PORT = properties.getProperty("PROXY_SERVER_PORT").toString();
	          }
	        if (properties.getProperty("PROXY_USER") != null) {
	        	PROXY_USER = properties.getProperty("PROXY_USER").toString();
	          }
	        if (properties.getProperty("USER_ID") != null) {
	        	USER_ID = properties.getProperty("USER_ID").toString();
	          }
	        if (properties.getProperty("PASSWORD") != null) {
	        	PASSWORD = properties.getProperty("PASSWORD").toString();
	          }
	        if (properties.getProperty("ENCODE_FORMAT") != null) {
	        	ENCODE_FORMAT = properties.getProperty("ENCODE_FORMAT").toString();
	          }
	        if (properties.getProperty("VERSION") != null) {
	        	VERSION = properties.getProperty("VERSION").toString();
	          }
	        if (properties.getProperty("SMS_PROVIDER_URL") != null) {
	        	SMS_PROVIDER_URL = properties.getProperty("SMS_PROVIDER_URL").toString();
	          }
	        if (properties.getProperty("HRMSDSName") != null) {
	        	HRMSDSName = properties.getProperty("HRMSDSName").toString();
	          }
	        if (properties.getProperty("ReportsDS") != null) {
	        	ReportsDS = properties.getProperty("ReportsDS").toString();
	          }
	        if (properties.getProperty("alfrescoAdmin") != null) {
	        	alfrescoAdmin = properties.getProperty("alfrescoAdmin").toString();
	          }
	        if (properties.getProperty("alfrescoPwd") != null) {
	        	alfrescoPwd = properties.getProperty("alfrescoPwd").toString();
	          }
	        if (properties.getProperty("dataSourceUrl") != null) {
	        	dataSourceUrl = properties.getProperty("dataSourceUrl").toString();
	          }
	        if (properties.getProperty("fileUploadPath") != null) {
	        	fileUploadPath = properties.getProperty("fileUploadPath").toString();
	          }
	        if (properties.getProperty("alfrescoUploadPath") != null) {
	        	alfrescoUploadPath = properties.getProperty("alfrescoUploadPath").toString();
	          }
	        if (properties.getProperty("dataSourceName") != null) {
	        	dataSourceName = properties.getProperty("dataSourceName").toString();
	          }
	        if (properties.getProperty("alfrescoSitePath") != null) {
	        	alfrescoSitePath = properties.getProperty("alfrescoSitePath").toString();
	          }
	        if (properties.getProperty("alfrescoCallTime") != null) {
	        	alfrescoCallTime = properties.getProperty("alfrescoCallTime").toString();
	          }
	        if (properties.getProperty("alfWebURL") != null) {
	        	alfWebURL = properties.getProperty("alfWebURL").toString();
	          }
	        if (properties.getProperty("alfEmpURL") != null) {
	        	alfEmpURL = properties.getProperty("alfEmpURL").toString();
	          }
	        if (properties.getProperty("successUploadURL") != null) {
	        	successUploadURL = properties.getProperty("successUploadURL").toString();
	          }
	        if (properties.getProperty("failureUploadURL") != null) {
	        	failureUploadURL = properties.getProperty("failureUploadURL").toString();
	          }
	        if (properties.getProperty("unAuthorisedUploadURL") != null) {
	        	unAuthorisedUploadURL = properties.getProperty("unAuthorisedUploadURL").toString();
	          }
	        if (properties.getProperty("cacheClearingURL") != null) {
	        	cacheClearingURL = properties.getProperty("cacheClearingURL").toString();
	          }
	        if (properties.getProperty("jdbcUrl") != null) {
	        	jdbcUrl = properties.getProperty("jdbcUrl").toString();
	          }
	        if (properties.getProperty("jdbcUserName") != null) {
	        	jdbcUserName = properties.getProperty("jdbcUserName").toString();
	          }
	        if (properties.getProperty("jdbcPassword") != null) {
	        	jdbcPassword = properties.getProperty("jdbcPassword").toString();
	          }
	     	     
	     	if (properties.getProperty("validationErrorURL") != null) {
	     		validationErrorURL = properties.getProperty("validationErrorURL").toString();
	          }    
	    }
	    
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	      LOGGER.info("Unable to load properties :"+e.getMessage(), e);
	    	
	    }
}
	
	
/*	public static void main(String args[]) {
		load("wtwet");
	}*/
}