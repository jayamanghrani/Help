package com.tcs.docstore.util;

import com.tcs.docstore.config.utils.PropertiesUtil;

public class MessageConstants {

	/** The Constant APP_ID_REQUIRED. */
	public static final String APP_ID_REQUIRED = PropertiesUtil
			.getMessage("appIdRequired");

	/** The Constant APP_ID_MISSING. */
	public static final String APP_ID_MISSING = PropertiesUtil
			.getMessage("appIdMissing");

	/** The Constant TRANSFORMATION_EXCEPTION. */
	public static final String TRANSFORMATION_EXCEPTION = PropertiesUtil
			.getMessage("transformationException");

	/** The Constant TECHNICAL_EXCEPTION. */
	public static final String TECHNICAL_EXCEPTION = PropertiesUtil
			.getMessage("technicalExcetion");

	
	/** The Constant USER_ID_MISS_EXCEPTION. */
	public static final String USER_ID_MISS_EXCEPTION = PropertiesUtil
			.getMessage("USER IS NOT AUTHENTICATED");
	
	
	/** The Constant REQUEST_TRANSFORMATION_FAILED. */
	public static final String REQUEST_TRANSFORMATION_FAILED = PropertiesUtil
			.getMessage("requestTransformationFailed");

	/** The Constant RESPONSE_TRANSFORMATION_FAILED. */
	public static final String RESPONSE_TRANSFORMATION_FAILED = PropertiesUtil
			.getMessage("responseTransformationFailed");

	/** The Constant CACEH_SERVICE_INVALCATION_FAILED. */
	public static final String CACEH_SERVICE_INVALCATION_FAILED = PropertiesUtil
			.getMessage("cacheServiceInvocationFailed");

	public static final String APPLICATION_EXCEPTION_MESSAGE = PropertiesUtil
			.getMessage("applicationExceptionMessage");
	
	/** The Constant SPECIAL_CHAR_VALIDATION_MESSAGE. */
	public static final String SPECIAL_CHAR_VALIDATION_MESSAGE = PropertiesUtil
			.getMessage("specialCharError");
	
	/** The Constant INPUT_REQUIRED_MESSAGE. */
	public static final String INPUT_REQUIRED_MESSAGE = PropertiesUtil
			.getMessage("inputRequiredMessage");

	/** The Constant MIN_DATE_RANGE_COMPARE_ERROR. */
	public static final String MIN_DATE_RANGE_COMPARE_ERROR = PropertiesUtil
			.getMessage("minDateRangeCompareError");
	
	/** The Constant MAX_DATE_RANGE_COMPARE_RANGE. */
	public static final String MAX_DATE_RANGE_COMPARE_RANGE = PropertiesUtil
			.getMessage("maxDateRangeCompareMessage");
	
	/** The Constant DATE_PARSE_ERROR. */
	public static final String DATE_PARSE_ERROR = PropertiesUtil
			.getMessage("dateParseError");
	
	/** The Constant DATE_REQUIRED_SELECTION. */
	public static final String DATE_REQUIRED_SELECTION = PropertiesUtil
			.getMessage("dateRequiredMessage");
	
	/** The Constant MATCH_METHOD_INVALID. */
	public static final String MATCH_METHOD_INVALID = PropertiesUtil
			.getMessage("matchMethodInvalid");
}
