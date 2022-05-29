package com.tcs.employeeportal.util;

import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
import org.apache.log4j.Logger;
import com.tcs.employeeportal.exception.cmo.ErrorVO;

public class ValidationUtil {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class);
	
	
	static String validateSpecialChar="[^;#']+";
	static String validateUserId="^[a-zA-Z_0-9_]+$";
	static String validatePassword="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
	
	/**
	 * validates special characters
	 * @param inputString user input
	 * @return
	 */
	public static boolean validateSpecialChar(String inputString) {
		boolean flag = true;
		 Pattern pattern = Pattern.compile(validateSpecialChar);
		if (!pattern.matcher(inputString).matches()) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * validates Password
	 * @param inputString user input
	 * @return
	 */
	public static boolean validatePassword(String inputString) {
		boolean flag = true;
		 Pattern pattern = Pattern.compile(validatePassword);
		if (!pattern.matcher(inputString).matches()) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * validates user header
	 * @param userid
	 * @param firstname user name
	 * @param memberOf
	 * @return
	 */
	public static ErrorVO validateUserHeader(String userid,String firstname,String memberOf)
	{
		ErrorVO errorVO = new ErrorVO();
		
		if(!isUserIdValid(userid))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
			errorVO.setErrorMessage(MessageConstants.USER_ID_INVALID_EXCEPTION);
			return errorVO;
		}
		
		if(!validateSpecialChar(firstname))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.SPECIAL_CHAR_VALIDATION_MESSAGE , firstname));
			return errorVO;
		}
		
		if(!validateSpecialChar(memberOf))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.SPECIAL_CHAR_VALIDATION_MESSAGE , memberOf));
			return errorVO;
		}
		return errorVO;
	}
	
	public static boolean isNotNull(String inputString) {
		boolean flag = true;
		if (inputString.equals("")) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * validates user inputs
	 * @param inputString
	 * @param inputLabel
	 * @param isMandatory
	 * @return
	 */
	public static ErrorVO isValidPassword(String inputString,String inputLabel,int maxlen,int minlen) {
		ErrorVO errorVO = new ErrorVO();
        	if(isNotNull(inputString))
        	{ 
        		if(!isMinLengthValid(inputString,minlen))
        		{
        			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
        			errorVO.setErrorMessage(MessageConstants.MIN_LENGTH_ERROR);
        			return errorVO;
        		}
        		if(!isMaxLengthValid(inputString,maxlen))
        		{
        			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
        			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.MAX_LENGTH_ERROR , inputLabel));
        			return errorVO;
        		}
        		if (!validatePassword(inputString))
        		{
        			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
        			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.INVALID_PASSWORD_FORMAT_ERROR , inputLabel));
        			return errorVO;
        		}
        	}else
        	{
        		errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
    			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.INPUT_REQUIRED_MESSAGE , inputLabel));
    			return errorVO;
        	}
		return errorVO;
	}
	
	/**
	 * validates user inputs length
	 * @param length
	 * @param maxlen
	 * @return
	 */
	public static boolean isMaxLengthValid(String length,int maxlen)
	{
		boolean flag=true;
		if(length.length()>maxlen)
		{
			flag=false;
		}
		return flag;
		
	}
	
	/**
	 * validates user inputs length
	 * @param length
	 * @param minlen
	 * @return
	 */
	public static boolean isMinLengthValid(String length,int minlen)
	{
		boolean flag=true;
		if(length.length()<minlen)
		{
			flag=false;
		}
		return flag;
		
	}
	/**
	 * validates user ID
	 * @param inputString
	 * @return
	 */
	public static boolean isUserIdValid(String inputString)
	{
		boolean flag = true;
		 Pattern pattern = Pattern.compile(validateUserId);
		if (!pattern.matcher(inputString).matches()) {
			flag = false;
		}
		return flag;
		
	}
	
	/**
	 * validates age of user
	 * @param bdate
	 * @return
	 */
	public static boolean isAgeValid(String bdate)
	{
		boolean flag=false;
		
		Calendar alloweddob = Calendar.getInstance();
        //subtracting year from Date 
		alloweddob.add(Calendar.YEAR, -18);
        
        DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        
        Calendar userdob  = Calendar.getInstance();
        try {
			userdob.setTime(df.parse(bdate));
		} catch (ParseException e) {
			LOGGER.error(e.getMessage());
		}
        
        Date minbday=alloweddob.getTime();
        Date userbday=userdob.getTime();
      
        if(userbday.before(minbday))
       {
    	   flag=true;
       }
		return flag;
	}
}
