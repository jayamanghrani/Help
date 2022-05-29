package com.tcs.umsuser.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    
    public static String validateString(String requestDate) {
    	
    	if(requestDate.isEmpty() || requestDate==null){
    		return null;
    	}
    	return requestDate;
    }
    
    public static boolean validateUserID(String userID) {
        boolean flag = false;
        if (userID == null || userID.isEmpty()) {
            return flag;
        }
        Pattern pattern = Pattern.compile(UtilConstants.validateNum);
        if (pattern.matcher(userID).matches()) {
            flag = true;
        }
        return flag;
    }
    
    
    @SuppressWarnings("null")
	public static Date validateDate(String requestDate) {
    	Date date = null;
        if (requestDate == null && requestDate.isEmpty()) {
            return null;
        }
        else{
	        SimpleDateFormat sdf = new SimpleDateFormat(UtilConstants.datePattern);
	        
	        try {
	        	int indexOf = requestDate.indexOf(':', 24);
	        	if(indexOf>0){
	    	    	String formatedDate=requestDate.substring(0,indexOf)+requestDate.substring(indexOf+1);    	    	
	    	    	date = sdf.parse(formatedDate);
	    	    }else{
	            date = sdf.parse(requestDate);
	    	    }
	           
	        } catch (ParseException e) {
	            LOGGER.error(e.toString());
	            return null;
	        }
	        if (date.compareTo(new Date()) > 0) {
	            LOGGER.error("Date provided exceeds current date : "
	                    + date.toString());
	        }
	        return date;
        }
    }
}