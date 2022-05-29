package com.tcs.umsoid.vo.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Base64;
import java.util.Properties;

import org.apache.log4j.Logger;



public class UtilProperties {
private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

private static  String LDAP_HOSTNAME;
private static  String LDAP_PORT ;
private static  String LDAP_USERNAME;
private static  String LDAP_PASSWORD ;

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

public static void load(String path)
{
  Properties properties = new Properties();
  try
  {
    properties.load(new FileInputStream(new File(path)));

    if (properties.getProperty("LDAP_HOSTNAME") != null) {
    	LDAP_HOSTNAME = properties.get("LDAP_HOSTNAME").toString();
    }

    if (properties.getProperty("LDAP_PORT") != null) {
    	LDAP_PORT = properties.getProperty("LDAP_PORT").toString();
    }

    if (properties.getProperty("LDAP_USERNAME") != null) {
    	LDAP_USERNAME = properties.getProperty("LDAP_USERNAME").toString();
    }
   

    if (properties.getProperty("LDAP_PASSWORD") != null) {
    	LDAP_PASSWORD = properties.getProperty("LDAP_PASSWORD").toString();
    }
  }
  
  catch (Exception e)
  {
  	e.printStackTrace();
    LOGGER.error("Unable to load properties :"+e.getMessage(), e);
  	
  }
}
	 
	 
	

}
