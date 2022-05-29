package com.tcs.umsapp.search.util;

public class UtilConstants {
    /** The Constant LOGGER. */
    /** The Constant ERROR_CODE_NOUSERID_Exception. */
    public static final int ERROR_CODE_NOUSERID_Exception = 961;
    /** The Constant ERROR_CODE_TechnichalException. */
    public static final int ERROR_CODE_TechnichalException = 950;
    /** The Constant ERROR_CODE_TransformationException. */
    public static final int ERROR_CODE_TransformationException = 901;
    /** The Constant ERROR_CODE_RUNTIME_TechnichalException. */
    public static final int ERROR_CODE_RUNTIME_TECHNICHALEXCEPTION = 952;

    public static final int ERROR_CODE_PARSING_ERROR = 962;

    /* User for User Search Service */
    public static final String GET_SEARCH_DETAIL = "getUserDetail";
    public static final String GET_Permission_DETAIL = "getUserPermissionDetail";
    public static final String GET_USER_Permission_SEARCH_DETAIL = "getUserPermissionSearchDetail";
    public static final String GET_USER_ROLE_SEARCH_DETAIL = "getUserRoleDetail";
    public static final String GET_xls_ROLE_SEARCH_DETAIL = "getUserRoleDetailXLS";
    public static final String GET_REQUEST_TRACK_DETAIL = "getRequestTrackerDetailPost";
    public static final String GET_REQUEST_TRACK_APP_ROLE_DETAIL = "getRequestTrackerAppRoleDetailPost";
    public static final String GET_REQUEST_TRACKER_XLS = "getRequestTrackerXLS";

    /* Regex patterns for Validation Util */
    public static final String validateSpecialChar = "[^;#']+";
    public static final String validateNum = "^[0-9]+$";
    public static final String validateAlpha = "^[a-zA-Z]+$";
    public static final String validateAlphaNum = "^[a-zA-Z_0-9_]+$";
    public static final String validateDate = "([0-9]{2})(-)([0-9]{2})(-)([0-9]{4})";
    public static final String validatePass = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
    public static final String datePattern = "dd-MM-yyyy";
    public static final String dateFormatterPattern = "yyyy-MM-dd HH:mm:ss.S";
    public static final String dateDisplay = "dd-MMM-yyyy";

    /**
     * Instantiates a new util contants.
     */
    private UtilConstants() {
    }
}