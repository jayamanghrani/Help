package com.tcs.docstore.util;

import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Pattern;

import com.tcs.docstore.exception.cmo.ErrorVO;
public class ValidationUtil {
	
	static String validateSpecialChar="[^;#']+";
	static String validateNumber="\\d+";
	
	
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
	 * validates numeric input
	 * @param inputString user input
	 * @return
	 */
	public static boolean validateNumber(String inputString) {
		boolean flag = true;
		Pattern pattern = Pattern.compile(validateNumber);
		if (!pattern.matcher(inputString).matches()) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * calculates date difference in days
	 * @param first input date
	 * @param last input date
	 * @return
	 * @throws ParseException
	 */
	public static int compareDates(String first, String last)
			throws ParseException {

		Calendar cal1 = new GregorianCalendar();
		Calendar cal2 = new GregorianCalendar();
		cal1.setTime(UtilConstants.getDate(first));
		cal2.setTime(UtilConstants.getDate(last));
		return daysBetween(cal1.getTime(), cal2.getTime());
	}

	public static int daysBetween(Date d1, Date d2) {
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
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
		
		if(!ValidationUtil.validateNumber(userid))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
			errorVO.setErrorMessage(MessageConstants.USER_ID_MISS_EXCEPTION);
			return errorVO;
		}
		
		if(!ValidationUtil.validateSpecialChar(firstname))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.SPECIAL_CHAR_VALIDATION_MESSAGE , firstname));
			return errorVO;
		}
		
		if(!ValidationUtil.validateSpecialChar(memberOf))
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
	public static ErrorVO validateFields(String inputString,String inputLabel,boolean isMandatory) {
		ErrorVO errorVO = new ErrorVO();
        if(isMandatory)
        {
        	if(isNotNull(inputString))
        	{
        		if (!validateSpecialChar(inputString))
        		{
        			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
        			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.SPECIAL_CHAR_VALIDATION_MESSAGE , inputLabel));
        			return errorVO;
        			
        		}
        	}else
        	{
        		errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
    			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.INPUT_REQUIRED_MESSAGE , inputLabel));
    			return errorVO;
        	}
        	
        }else
        {
        	if(isNotNull(inputString))
        	{
        		if (!validateSpecialChar(inputString))
        		{
        			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
        			errorVO.setErrorMessage(MessageFormat.format(MessageConstants.SPECIAL_CHAR_VALIDATION_MESSAGE , inputLabel));
        			return errorVO;
        			
        		}
        	}
        }
		return errorVO;
	}
}
