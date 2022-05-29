/**
 * 
 */
package com.tcs.webreports.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import org.apache.log4j.Logger;

/**
 * @author 738566
 *
 */
public class UtilConstants {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(UtilConstants.class);
	
	/** The Constant CONFIG_PATH. */
	public static final String CONFIG_PATH ="/wls/oracle_12c/middleware/oracle_home/user_projects/domains/config/reportsConfig/reportsConfig.properties";
	public static final String REPORT_ABSOLUTE_PATH ="/wls/oracle_12c/middleware/oracle_home/user_projects/domains/config/reportsConfig/reports.properties";
	public static final String CHANNEL_REPORTS_MAPPING_PATH ="/wls/oracle_12c/middleware/oracle_home/user_projects/domains/config/reportsConfig/Channel_and_Associated_Reports.xlsx";
	// JDBC connection constants 
	/** The Constant DRIVER. */
//	public static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	/*public static final String CONFIG_PATH ="C:/Users/738796/Desktop/config.properties";
	public static final String REPORT_ABSOLUTE_PATH ="D:/WebReports_3July17/reports.properties";
	public static final String CHANNEL_REPORTS_MAPPING_PATH ="D:/WebReports_3July17/Channel_and_Associated_Reports.xlsx";*/

	/** The Constant URL. *//*
	public static final String URL = "jdbc:oracle:thin:@"
			+ PropertiesUtil.getConfigData("oracleDbIp") + ":"
			+ PropertiesUtil.getConfigData("oracleDbPort") + ":orcl";*/

	/** The Constant VALIDATE_URL. */
//	public static final String VALIDATE_URL = "validateUrl";
	/** The Constant AUTHENTICATE. */
	public static final String AUTHENTICATE ="authenticate";
	/** The Constant AUTHENTICATE. */
	public static final String ISLOGGEDIN ="isLoggedIn";
	
	/** The Constant HO_IT_ADMIN. */
	public static final String HO_IT_ADMIN = "HOITAdmin";

	/** The Constant login. */
	public static final String LOGIN = "login";
	
	/** The Constant FORGOT_PASSWORD. */
//	public static final String FORGOT_PASSWORD = "forgotPassword";
//	
//	/** The Constant CHANGE_PASSWORD. */
//	public static final String CHANGE_PASSWORD = "changePassword";
//	
//	public static final String PWD_DETAILS = "pwddetails";
	
	/** The Constant MOBILEAPP_SMS. */
	public static final String MOBILEAPP_SMS="Dear User, To download the helpline app please click on the below link {appLink}";
	
	/** The Constant FORGOT_PASSWORD_SMS. */
	public static final String FORGOT_PASSWORD_SMS="This is with reference to your request on www.niaonline.co.in for your forgotten password, dated {currentDate}.\n User ID : {userName} ; Password: {password}";
	
	public static final String FORGOT_PASSWORD_SUCCESSMSG ="Your Password is successfully reset and sent to your registered email ID and mobile number. Please try to login after 10 Mins.";
	/** The Constant FORGOT_PASSWORD_EAMIL. */
	public static final String FORGOT_PASSWORD_EMAIL="Dear Employee,\nThis is with reference to your request on www.niaonline.co.in for your forgotten password, dated {forgotPasswordDate}.\nPlease find below your details: User ID: {UserId} ; Password: {password}.\nWe recommend you to change your password regularly for security reasons.\nWarm Regards,\nTeam NIA\n";
	
	/** The Constant FORGOT_PASSWORD_USERID_ERROR. */
	public static final String FORGOT_PASSWORD_USERID_ERROR = "User ID does not exist";
	
	/** The Constant FORGOT_PASSWORD_DOB_ERROR. */
	public static final String FORGOT_PASSWORD_DOB_ERROR = "Invalid credentials";
	
	/** The Constant FORGOT_PASSWORD_ERROR. */
	public static final String FORGOT_PASSWORD_ERROR = "Error in updating password for the request";
		
	/** The Constant DATE_FORMAT. */
	public static final String DATE_FORMAT = "dd/MM/yyyy";
	
	/** The Constant NOSOP. */
	public static final int NOSOP = 0;

	/** The Constant SERR. */
	public static final int SERR = 1;

	/** The Constant WARN. */
	public static final int WARN = 2;

	/** The Constant INFO. */
	public static final int INFO = 3;

	/** The Constant DEBUG. */
	public static final int DEBUG = 4;

	/** The Constant ERROR_CODE_INTERNAL_ERROR. */
	public static final int ERROR_CODE_INTERNAL_ERROR = 500;

	/** The Constant STATUS_CODE_OK. */
	public static final int STATUS_CODE_OK = 200;

	/** The Constant ERROR_CODE_TransformationException. */
	public static final int ERROR_CODE_TransformationException = 901;

	/** The Constant ERROR_CODE_MANDATORYFIELD_TransformationException. */
	public static final int ERROR_CODE_MANDATORYFIELD_TransformationException = 902;

	/** The Constant ERROR_CODE_INCOMPATIBLEDATA_TransformationException. */
	public static final int ERROR_CODE_INCOMPATIBLEDATA_TransformationException = 903;

	/** The Constant ERROR_TYPE_TechnichalException. */
	public static final String ERROR_TYPE_TechnichalException = "TechnichalException";

	/** The Constant ERROR_TYPE_TransformationException. */
	public static final String ERROR_TYPE_TransformationException = "TransformationException";

	/** The Constant ERROR_TYPE_Exception. */
	public static final String ERROR_TYPE_Exception = "OtherException";

	/** The Constant ERROR_CODE_TechnichalException. */
	public static final int ERROR_CODE_TechnichalException = 950;

	/** The Constant ERROR_CODE_TIMEOUT_TechnichalException. */
	public static final int ERROR_CODE_TIMEOUT_TechnichalException = 951;

	/** The Constant ERROR_CODE_RUNTIME_TechnichalException. */
	public static final int ERROR_CODE_RUNTIME_TechnichalException = 952;

	/** The Constant ERROR_CODE_NORESPONSE_TechnichalException. */
	public static final int ERROR_CODE_NORESPONSE_TechnichalException = 953;
	
	/** The Constant ERROR_CODE_NODATA_TechnichalException. */
	public static final int ERROR_CODE_NODATA_TechnicalException = 954; 
	
	/** The Constant ERROR_CODE_NODATA_TechnichalException. */
	public static final int ERROR_CODE_RUNTIME_BANCS_ERROR = 955;
	
	public static final int ERROR_CODE_CUSTOM_NO_PAN_NUMBER = 956;
	
	public static final int ERROR_MORE_NO_OF_RECORDS = 957;
	
    public static final int ERROR_CODE_BANCS_CLIENT_ERROR = 958;
	
	public static final int ERROR_CODE_BANCS_SERVER_ERROR = 959;
	
	public static final int ERROR_CODE_BANCS_TIMEOUT_ERROR = 960;
	
	/** The Constant ERROR_CODE_NOUSERID_Exception. */
	public static final int ERROR_CODE_NOUSERID_Exception = 961;

	public static final int ERROR_CODE_PARSING_ERROR = 962;

	/** The Constant ERROR_MSG_CHANGEPASSWORD. */
	public static final String ERROR_MSG_CHANGEPASSWORD = "Error updating password";

	/** The Constant SUCCESS_MSG_CHANGEPASSWORD. */
	public static final String SUCCESS_MSG_CHANGEPASSWORD = "Password Changed Successfully";
	
	/** The Constant ERROR_INVALIDCRED_CHANGEPASSWORD. */
	public static final String ERROR_INVALIDCRED_CHANGEPASSWORD = "Incorrect old password";
	
	public static final int PASSWORD_MAXLENGTH = 12;
	public static final int USER_ID_MAXLENGTH=15;
	public static final int PASSWORD_MINLENGTH=8;
	/** The Constant GET_USER_DETAILS. */
	public static final String GET_USER_DETAILS = "getUserDetails";
	
	/** The Constant ADMIN_USER. */
	public static final String ADMIN_USER = "ADMIN";
	
	/** The Constant INVALID_CHANNEL. */
	public static final String INVALID_CHANNEL = "Invalid channel";
	
	/**
	 * For Random Password generation
	 */
	private static final String ALPHA_CAPS 	= "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static final String ALPHA 	= "abcdefghijklmnopqrstuvwxyz";
	private static final String NUM 	= "0123456789";
	private static final String SPL_CHARS	= "!@#$&";

	
	/** The Constant GET_CHANNEL_REPORTS. */
	public static final String GET_CHANNEL_REPORTS="getChannelReports";
	
	/** The Constant GET_REPORT. */
	public static final String GET_REPORT = "getReport";
	/** The Constant LOGOUT. */
	public static final String LOGOUT = "logout";
	/** The Constant GET_REPORT_PARAMETER. */
	public static final String GET_REPORT_PARAMETER = "getReportParameter"; 
	/** The Constant GET_REPORT_CHANNEL_MAPPING. */
	public static final String GET_REPORT_CHANNEL_MAPPING = "getReportChannelMapping"; 
	/**
	 * Instantiates a new util contants.
	 */
	private UtilConstants() {
	}

	/**
	 * Creates the random no.
	 *
	 * @param i the i
	 * @return the string
	 */
	public static String createRandomNo(int i) {
		Random random = new Random(System.nanoTime());
		int otpNo = 0;
		otpNo = random.nextInt(10000000);
		String strRandom = String.valueOf(otpNo);
		while (strRandom.length() < i) {
			createRandomNo(i);
		}
		String strOTP = strRandom.substring(0, i);
		return strOTP;
	}

	/**
	 * Gets the date.
	 *
	 * @param date the date
	 * @return the date
	 */
	public static Date getDate(String date) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				UtilConstants.DATE_FORMAT);
		Date dtDate = null;
		try {
			dtDate = simpleDateFormat.parse(date);
		} catch (ParseException e) {
			dtDate = new Date();
		}
		return dtDate;
	}

	/**
	 * @param minLen
	 * @param maxLen
	 * @param noOfCAPSAlpha
	 * @param noOfDigits
	 * @param noOfSplChars
	 * @return
	 */
	public static char[] generatePswd(int minLen, int maxLen, int noOfCAPSAlpha, 
			int noOfDigits, int noOfSplChars) {
		LOGGER.info("Inside Random Pwd Generator");
		/*if(minLen > maxLen)
			throw new IllegalArgumentException("Min. Length > Max. Length!");
		if( (noOfCAPSAlpha + noOfDigits + noOfSplChars) > minLen )
			throw new IllegalArgumentException
			("Min. Length should be atleast sum of (CAPS, DIGITS, SPL CHARS) Length!");*/
		Random rnd = new Random();
		int len = rnd.nextInt(maxLen - minLen + 1) + minLen;
		char[] pswd = new char[len];
		int index = 0;
		for (int i = 0; i < noOfCAPSAlpha; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = ALPHA_CAPS.charAt(rnd.nextInt(ALPHA_CAPS.length()));
		}
		for (int i = 0; i < noOfDigits; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = NUM.charAt(rnd.nextInt(NUM.length()));
		}
		for (int i = 0; i < noOfSplChars; i++) {
			index = getNextIndex(rnd, len, pswd);
			pswd[index] = SPL_CHARS.charAt(rnd.nextInt(SPL_CHARS.length()));
		}
		for(int i = 0; i < len; i++) {
			if(pswd[i] == 0) {
				pswd[i] = ALPHA.charAt(rnd.nextInt(ALPHA.length()));
			}
		}
		return pswd;
	}
	
	
	/**
	 * @param rnd
	 * @param len
	 * @param pswd
	 * @return
	 */
	private static int getNextIndex(Random rnd, int len, char[] pswd) {
		int index = rnd.nextInt(len);
		while(pswd[index = rnd.nextInt(len)] != 0);
		return index;
	}
	
	public static String getRandomFileName()
	{
		
		 String uuid =	UUID.randomUUID().toString().substring(0,8);
		 Date date = new Date();
		 SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhmmss");
		 String formattedDate = sdf.format(date);
		 LOGGER.info(formattedDate);
		 String randomName = uuid+"_"+formattedDate;
		 LOGGER.info(randomName);
		return randomName;
	}
	/*public static void main(String args[])
	{
		UtilConstants util = new UtilConstants();
		util.getRandomFileName();
		
	}*/
}

