package com.tcs.webreports.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
public class ValidationUtil {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class);
	
	
//static String validateUserId="^(?=.*[a-zA-Z])(?=.{5,30})(?=.*[0-9])[a-zA-Z0-9_$]+$";
	static String validateUserId="^(?=.*[a-zA-Z])(?=.{4,15})(?=.*[a-zA-Z0-9])[a-zA-Z0-9_$]+$";
	static String validatePassword="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+_!])(?=\\S+$).{8,15}$";
    static String partyCodeValidation="^(?=.*[a-zA-Z])(?=.{4,15})(?=.*[0-9])[a-zA-Z0-9]+$";
    /**
	 * validates user inputs null
	 * @param inputString
	 * @return
	 */
	public static boolean isNotNull(String inputString) {
		boolean flag = true;
		if (inputString.equals("")) {
			flag = false;
		}
		return flag;
	}
	
	/**
	 * Validation for FromDate and ToDate.
	 * @param startDate
	 * @param endDate
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static String reportDateValidation(String startDate, String endDate, int time)
	{
		String message="";
		long diff= 0l;
		int days=0;
		if(isNotNull(startDate) && isNotNull(endDate))
		{
			SimpleDateFormat myFormat = new SimpleDateFormat(UtilConstants.DATE_FORMAT);
			java.util.Date date1;
			try {
				date1 = myFormat.parse(startDate);
				java.util.Date date2 = myFormat.parse(endDate);
				LOGGER.info("date1" + date1);
				LOGGER.info("date2" + date2);
			     diff = date2.getTime() - date1.getTime();
			     days = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			     
			} catch (ParseException e) {
				message=e.getMessage();
				LOGGER.error(e.getMessage());
				return message;
			}
		     LOGGER.info("days and diff"+diff +days );
		     if(days>time)
		     {
		    message= "Difference of toDate and fromDate should be less than equals to 30 days";
		     }
		     else
		     {
		    	 message= "pass";
		     }
		}
		    else 
		    {
		    	message="toDate and fromDate can not be empty";
		    }
		LOGGER.info(message);
			return message;
		    }


	
	/**
	 * Validation for User Id.
	 * @param userId
	 * @return
	 */
	public static String userIdvalidation(String userId) 
	{
		String message="";
		if(userId.equals("") || userId==null)
		{
			message="UserId cannot be empty";
		}
		else if(userId.matches(validateUserId) )
		{
			
			message="pass";
		}
		else
		{
			message="Invalid UserId";
		}
		LOGGER.info(message);
		return message;
	}
	
	/**
	 * Validation for password.
	 * @param password
	 * @return
	 */
	public static String passwordValidation(String password) 
	{
		String message="";
		if(password.equals("") || password==null)
		{
			message="Password cannot be empty";
		}
		
		else if( password.matches(validatePassword) )
		{
			message="pass";
		}
	else
		{
			message="Password should meet the password policy";
		}
		
		LOGGER.info(message);
		return message;
		}
	
	/**
	 * For the validation of part code.
	 * @param partyCode
	 * @return
	 */
	public static String partyCodeValidation(String partyCode) 
	{

	String message="";
	if(partyCode==null || partyCode.equals(""))
	{
		message="Party Code cannot be null";
	}
//	else if(partyCode.matches(partyCodeValidation))	
//	{
//		partyCode.toUpperCase();
//		message="pass";
//	}
	else
	{
		message="pass";
//		message="Invalid Party Code";
	}
	LOGGER.info(message);
	return message;
	}
	
	/**
	 * Validate report name.
	 * @param reportName
	 * @return
	 */
	public static String reportNameValidation(String reportName) 
	{
	   String message="";
		if(reportName==null || reportName.equals(""))
		{
			message="Selecet the appropriate name of report";
		}
		else
		{
			message="pass";
		}
		LOGGER.info(message);
	return message;
	}
}