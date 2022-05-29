package com.tcs.umsrole.vo.util;




public class UtilConstants {/** The Constant LOGGER. */
	
	/** The Constant CONFIG_PATH. */
	public static final String CONFIG_PATH ="/wls_config/user_projects/application/app_config/roleConfig.properties";
	public static final String GET_SEARCH_DETAIL = "getContent";
	public static final String GET_REQUEST_TRACK_DETAIL = "getRequestTrackerDetailPost";
	
	/** The Constant ERROR_CODE_INTERNAL_ERROR. */
	public static final int ERROR_CODE_INTERNAL_ERROR = 500;

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
	
	/** The Constant ERROR_CODE_NOUSERID_Exception. */
	public static final int ERROR_CODE_NOUSERID_Exception = 961;

	public static final int ERROR_CODE_PARSING_ERROR = 962;
	
	public static final int ERROR_CODE_NO_ROLE_TO_UPDATE_CODE = 904;
	
	public static final String ERROR_CODE_NO_ROLE_TO_UPDATE_MSG = "No roles updated";
	
	/* Regex patterns for Validation Util */
	public static final String validateSpecialChar="[^;#']+";
	public static final String validateNum="^[0-9]+$";
	public static final String validateAlpha="^[a-zA-Z]+$";
	public static final String validateAlphaNum="^[a-zA-Z_0-9_]+$";
	public static final String validateDate="([0-9]{2})(-)([0-9]{2})(-)([0-9]{4})";
	public static final String validatePass="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$";
	public static final String datePattern = "dd-MM-yyyy";

	/**
	 * Instantiates a new util contants.
	 */
	private UtilConstants() {
	}

}




