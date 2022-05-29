package com.tcs.umsapp.search.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    public static boolean validateSpecialChar(String inputString) {
        boolean flag = true;
        Pattern pattern = Pattern.compile(UtilConstants.validateSpecialChar);
        if (!pattern.matcher(inputString).matches()) {
            flag = false;
        }
        return flag;
    }

    public static boolean validatePassword(String inputString) {
        boolean flag = true;
        Pattern pattern = Pattern.compile(UtilConstants.validatePass);
        if (!pattern.matcher(inputString).matches()) {
            flag = false;
        }
        return flag;
    }

    public static boolean validateName(String name) {
        boolean flag = false;
        Pattern pattern = Pattern.compile(UtilConstants.validateAlpha);
        if (pattern.matcher(name).matches()) {
            flag = true;
        }
        return flag;
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


    public static boolean validateBranchID(String branchID) {
        boolean flag = false;
        Pattern pattern = Pattern.compile(UtilConstants.validateNum);
        if (pattern.matcher(branchID).matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean validateApplication(String applicationID) {
        boolean flag = false;
        Pattern pattern = Pattern.compile(UtilConstants.validateAlpha);
        if (pattern.matcher(applicationID).matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean validateRoleID(String roleID) {
        boolean flag = false;
        Pattern pattern = Pattern.compile(UtilConstants.validateAlphaNum);
        if (pattern.matcher(roleID).matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean validateList(List list) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return true;
    }

    public static boolean validateRequestID(String requestId) {
        boolean flag = false;
        if (requestId == null || requestId.isEmpty()) {
            return flag;
        }
        Pattern pattern = Pattern.compile(UtilConstants.validateNum);
        if (pattern.matcher(requestId).matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean validatRequestStatus(String requestStatus) {
        boolean flag = false;
        if (requestStatus == null || requestStatus.isEmpty()) {
            return flag;
        }
        Pattern pattern = Pattern.compile(UtilConstants.validateAlpha);
        if (pattern.matcher(requestStatus).matches()) {
            flag = true;
        }
        return flag;
    }

    public static boolean validateDate(String requestDate) {
        boolean flag = false;
        if (requestDate == null || requestDate.isEmpty()) {
            return flag;
        }
        Pattern pattern = Pattern.compile(UtilConstants.validateDate);
        if (pattern.matcher(requestDate).matches()) {
            flag = true;
        } else {
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
        if (date.compareTo(new Date()) > 0) {
            LOGGER.error("Date provided exceeds current date : "
                    + date.toString());
            flag = false;
        }
        return flag;
    }
}