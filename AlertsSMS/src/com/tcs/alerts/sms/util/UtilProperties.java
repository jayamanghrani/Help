/**
 * 
 */
package com.tcs.alerts.sms.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;


/**
 * @author 738566
 *
 */
public class UtilProperties {

	  private static String proxyPassword;
	  private static String proxyHost;
	  private static String proxyServerPort;
	  private static String proxyUser;
	  private static String userid;
	  private static String password;
	  private static String encode_format;
	  private static String version;
	  private static String sms_provider_url;
	  private static String logPath;
	  private static String sender;
	  private static String server;
	  /**
	 * @return the server
	 */
	public static String getServer() {
		return server;
	}
	/**
	 * @param server the server to set
	 */
	public static void setServer(String server) {
		UtilProperties.server = server;
	}

	private static String recipients;
	  private static String filePath;
	  
	  
	/**
	 * @return the filePath
	 */
	public static String getFilePath() {
		return filePath;
	}
	/**
	 * @param filePath the filePath to set
	 */
	public static void setFilePath(String filePath) {
		UtilProperties.filePath = filePath;
	}
	/**
	 * @return the recipients
	 */
	public static String getRecipients() {
		return recipients;
	}
	/**
	 * @param recipients the recipients to set
	 */
	public static void setRecipients(String recipients) {
		UtilProperties.recipients = recipients;
	}
	/**
	 * @return the sender
	 */
	public static String getSender() {
		return sender;
	}
	/**
	 * @param sender the sender to set
	 */
	public static void setSender(String sender) {
		UtilProperties.sender = sender;
	}
	/**
	 * @return the logPath
	 */
	public static String getLogPath() {
		return logPath;
	}
	/**
	 * @param logPath the logPath to set
	 */
	public static void setLogPath(String logPath) {
		UtilProperties.logPath = logPath;
	}
	/**
	 * @return the proxyPassword
	 */
	public static String getProxyPassword() {
		return proxyPassword;
	}
	/**
	 * @param proxyPassword the proxyPassword to set
	 */
	public static void setProxyPassword(String proxyPassword) {
		UtilProperties.proxyPassword = proxyPassword;
	}
	/**
	 * @return the proxyHost
	 */
	public static String getProxyHost() {
		return proxyHost;
	}
	/**
	 * @param proxyHost the proxyHost to set
	 */
	public static void setProxyHost(String proxyHost) {
		UtilProperties.proxyHost = proxyHost;
	}
	/**
	 * @return the proxyServerPort
	 */
	public static String getProxyServerPort() {
		return proxyServerPort;
	}
	/**
	 * @param proxyServerPort the proxyServerPort to set
	 */
	public static void setProxyServerPort(String proxyServerPort) {
		UtilProperties.proxyServerPort = proxyServerPort;
	}
	/**
	 * @return the proxyUser
	 */
	public static String getProxyUser() {
		return proxyUser;
	}
	/**
	 * @param proxyUser the proxyUser to set
	 */
	public static void setProxyUser(String proxyUser) {
		UtilProperties.proxyUser = proxyUser;
	}
	/**
	 * @return the userid
	 */
	public static String getUserid() {
		return userid;
	}
	/**
	 * @param userid the userid to set
	 */
	public static void setUserid(String userid) {
		UtilProperties.userid = userid;
	}
	/**
	 * @return the password
	 */
	public static String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public static void setPassword(String password) {
		UtilProperties.password = password;
	}
	/**
	 * @return the encode_format
	 */
	public static String getEncode_format() {
		return encode_format;
	}
	/**
	 * @param encode_format the encode_format to set
	 */
	public static void setEncode_format(String encode_format) {
		UtilProperties.encode_format = encode_format;
	}
	/**
	 * @return the version
	 */
	public static String getVersion() {
		return version;
	}
	/**
	 * @param version the version to set
	 */
	public static void setVersion(String version) {
		UtilProperties.version = version;
	}
	/**
	 * @return the sms_provider_url
	 */
	public static String getSms_provider_url() {
		return sms_provider_url;
	}
	/**
	 * @param sms_provider_url the sms_provider_url to set
	 */
	public static void setSms_provider_url(String sms_provider_url) {
		UtilProperties.sms_provider_url = sms_provider_url;
	}
	  
	  public static void load(/*String path*/)
	  {
	    Properties properties = new Properties();
	    try
	    {
	    	//properties.load(UIDProperties.class.getResourceAsStream("/biometric.properties"));
	    //  properties.load(new FileInputStream(new File(path)));
	    	System.out.println("inside load");
	     // properties.load(new FileInputStream(new File("D:\\eclipse_workspace\\AlertsSMS\\src\\com\\tcs\\alerts\\sms\\util\\config.properties")));
	    //	properties.load(new FileInputStream(new File("/uatapps/config.properties")));
	    	properties.load(new FileInputStream(new File("config.properties")));
	      if (properties.getProperty("logPath") != null) {
	          logPath = properties.getProperty("logPath").toString();
	          System.out.println(logPath);
	        }
	        if (properties.getProperty("proxyPassword") != null)
	        {
	        	proxyPassword = properties.getProperty("proxyPassword").toString();
	        }
	        if (properties.getProperty("proxyHost") != null)
	        {
	          proxyHost = properties.getProperty("proxyHost").toString();
	        }
	        if (properties.getProperty("proxyServerPort") != null)
	        {
	          proxyServerPort = properties.getProperty("proxyServerPort").toString();
	        }
	        if (properties.getProperty("proxyUser") != null)
	        {
	          proxyUser = properties.getProperty("proxyUser").toString();
	        }
	        if (properties.getProperty("encode_format") != null)
	        {
	          encode_format = properties.getProperty("encode_format").toString();
	        }
	        if (properties.getProperty("userid") != null)
	        {
	          userid = properties.getProperty("userid").toString();
	        }
	        if (properties.getProperty("password") != null)
	        {
	          password = properties.getProperty("password").toString();
	        }
	        if (properties.getProperty("sms_provider_url") != null)
	        {
	          sms_provider_url = properties.getProperty("sms_provider_url").toString();
	        }
//	        if (properties.getProperty("version") != null)
//	        {
//	        	version = properties.getProperty("version").toString();
//	        }
	        if (properties.getProperty("sender") != null)
	        {
	        	sender = properties.getProperty("sender").toString();
	        }
	        if (properties.getProperty("server") != null)
	        {
	        	server = properties.getProperty("server").toString();
	        }
	        if (properties.getProperty("recipients") != null)
	        {
	        	recipients = properties.getProperty("recipients").toString();
	        }
	        if (properties.getProperty("filePath") != null)
	        {
	        	filePath = properties.getProperty("filePath").toString();
	        }
	        
	      System.out.println("Properties loaded successfully");
	    }
	    catch (Exception e)
	    {
	    	e.printStackTrace();
	   //   Log.biom_Log.error("Unable to load properties :"+e.getMessage(), e);
	    	
	    }
	  }
	  
	 /* public static void main(String args[]){
		  load();
	  }*/
}
