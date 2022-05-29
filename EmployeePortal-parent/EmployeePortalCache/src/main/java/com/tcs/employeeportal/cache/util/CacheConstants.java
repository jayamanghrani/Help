package com.tcs.employeeportal.cache.util;

import com.tcs.employeeportal.config.utils.UtilProperties;

public class CacheConstants {
	
	/** The Constant KEY_SEPARATOR. */
	public static final String KEY_SEPARATOR = "_";

	/** The Constant LOB_KEY. */
	public static final String LOB_KEY = "lob";

	/** The Constant CHANNEL_ROLE_PREFIX. */
	public static final String CHANNEL_ROLE_PREFIX = "cr";

	/** The Constant CITY_PREFIX. */
	//public static final String CITY_PREFIX = "city";

	/** The Constant MAKE_PREFIX. */
	public static final String MAKE_PREFIX = "make";

	/** The Constant MESSAGES_KEY. */
	//public static final String MESSAGES_KEY = "messages";

	/** The Constant ALFRESCO_URL. */
	//alfrescoIp
	
	// PropertiesUtil p = new PropertiesUtil();
	public static final String ALFRESCO_URL = UtilProperties.getAlfrescoIp() + "/EmployeePortalCacheREST/rest/alfresco/getContent";
	
	/** The Constant ALFRESCO_URL. */
	/*public static final String ALFRESCO_URL = "http://"
			+ PropertiesUtil.getConfigProperty("alfrescoIp") + ":"
			+ PropertiesUtil.getConfigProperty("alfrescoPort")	
			+ "/alfresco/service/nia/getAllContent";*/

	/** The Constant ALFRESCO_LOCAL_URL. *//*
	public static final String ALFRESCO_LOCAL_URL = "http://"
			+ PropertiesUtil.getConfigData("localIp") + ":"
			+ PropertiesUtil.getConfigData("localPort")
			+ "//TestBaNCSService/rest/alfresco/content";*/

	/** The Constant CAROUSEL_CONTENT. */
	public static final String CAROUSEL_CONTENT = "Carousel";

	/** The Constant ADDITIONAL_LINKS_CONTENT. */
	public static final String ADDITIONAL_LINKS_CONTENT = "AdditionalLinks";

	/** The Constant TERMS_CONDITIONS_CONTENT. */
	public static final String INSTRUCTION_TO_USER = "InstructionToUser";

	/** The Constant DISCLAIMERS_CONTENT. */
	public static final String EXECUTIVE_MSG = "ExecutiveMsg";

	/** The Constant SMS_CONTENT. */
	public static final String SMS_CONTENT = "SMS";

	/** The Constant FAQ_CONTENT. */
	public static final String FAQ_CONTENT = "FAQ";

	/** The Constant EMAIL_CONTENT. */
	public static final String EMAIL_CONTENT = "Email";
	
	/** The Constant EMP_SMS. */
	public static final String EMP_SMS = "EmpSMS";

	/** The Constant EMP_EMAIL. */
	public static final String EMP_EMAIL = "EmpEmail";

	/** The Constant DOCUMENTS_CONTENT. */
	public static final String EMPLOYEE_DOCUMENT = "EmployeeDocument";

	/** The Constant LATEST_NEWS_CONTENT. */
	public static final String LATEST_NEWS_CONTENT = "LatestNews";

	/** The Constant WHATS_NEW_CONTENT. */
	public static final String WHATS_NEW_CONTENT = "WhatsNew";

	/** The Constant POLICY_KEY_FEATURE_CONTENT. */
	public static final String POLICY_KEY_FEATURE_CONTENT = "PolicyKeyFeature";

	/** The Constant ABOUT_US_CONTENT. */
	public static final String ABOUT_US_CONTENT = "AboutUs";

	/** The Constant NOTIFICATION_CONTENT. */
	public static final String NOTIFICATION_CONTENT = "Notification";
	
	/** The Constant HELP. */
	//public static final String HELP= "Help";
	
	/** The Constant DISCLAIMER_PAGE. */
	//public static final String DISCLAIMER_PAGE= "DisclaimerPage";
	
	/** The Constant PRIVACYPOLICY. */
	//public static final String PRIVACYPOLICY= "PrivacyPolicy";
	
	public static String CONTENT = "EMP_CONTENT";
//	public static String CONTENT = "CONTENT";
	
	//public static final String DISCOUNT_MSG= "DiscountMsg"; 

	/**
	 * Instantiates a new cache constants.
	 */
	private CacheConstants() {
	}


}
