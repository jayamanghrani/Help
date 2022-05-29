package com.tcs.umsrole.vo.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class ValidationUtil {
	
	/**
	 * 
	 */
	private ValidationUtil() {
	}

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(ValidationUtil.class);
	
	
	/**
	 * validates special characters
	 * @param inputString user input
	 * @return
	 */
	public static boolean validateSpecialChar(String inputString) {
		boolean flag = true;
		 Pattern pattern = Pattern.compile(UtilConstants.validateSpecialChar);
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
		 Pattern pattern = Pattern.compile(UtilConstants.validatePass);
		if (!pattern.matcher(inputString).matches()) {
			flag = false;
		}
		return flag;
	}

	
	public static boolean isNotNull(String inputString) {
		boolean flag = true;
		if (inputString.equals("")) {
			flag = false;
		}
		return flag;
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


	/**
	 * 
	 * Validates that names are strings
	 * 
	 * @param name
	 * @return
	 */
	public static boolean validateName(String name){
		boolean flag = false;
		Pattern pattern = Pattern.compile(UtilConstants.validateAlpha);
		if (pattern.matcher(name).matches()) {
			flag = true;
		}
		return flag;
	}

	public static boolean validateUserID(String userID){
		boolean flag = false;
		if(userID==null||userID.isEmpty()){
			return flag;
		}
		Pattern pattern = Pattern.compile(UtilConstants.validateNum);
		if (pattern.matcher(userID).matches()) {
			flag = true;
		}
		return flag;
	}

	/**
	 * Validates branch ID
	 * @param branchID
	 * @return
	 */
	public static boolean validateBranchID(String branchID){
		boolean flag = false;
		Pattern pattern = Pattern.compile(UtilConstants.validateNum);
		if (pattern.matcher(branchID).matches()) {
			flag = true;
		}
		return flag;
	}

	public static boolean validateApplication(String applicationID){
		boolean flag = false;
		Pattern pattern = Pattern.compile(UtilConstants.validateAlpha);
		if (pattern.matcher(applicationID).matches()) {
			flag = true;
		}
		return flag;
	}

	public static boolean validateRoleID(String roleID){
		boolean flag = false;
		Pattern pattern = Pattern.compile(UtilConstants.validateAlphaNum);
		if (pattern.matcher(roleID).matches()) {
			flag = true;
		}
		return flag;
	}

	public static boolean validateList(List list) {
		if(list==null||list.isEmpty()){
			return false;
		}
		return true;
	}


	public static boolean validateDate(String requestDate) {
		boolean flag = false;
		if(requestDate==null||requestDate.isEmpty()){
			return flag;
		}
		Pattern pattern = Pattern.compile(UtilConstants.validateDate);
		if (pattern.matcher(requestDate).matches()) {
			flag = true;
		}else{
			return flag;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(UtilConstants.datePattern);
		Date date = null;
		try {
			date = sdf.parse(requestDate);
		} catch (ParseException e) {
			LOGGER.error(e.toString());
			flag = false;
			return flag;
		}
		if(date.compareTo(new Date())>0){
			LOGGER.error("Date provided exceeds current date : "+date.toString());
			flag = false;
		}
		return flag;
	}

	public static boolean validateRemark(String reason) {
		if(reason==null||reason.isEmpty()){
			return false;
		}
		return true;
	}

	
	
}
