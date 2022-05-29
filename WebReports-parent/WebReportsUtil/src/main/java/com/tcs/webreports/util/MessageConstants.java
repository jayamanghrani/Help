package com.tcs.webreports.util;

import com.tcs.webreports.config.utils.PropertiesUtil;

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
	
	public static final String SPECIAL_CHAR_VALIDATION_MESSAGE = PropertiesUtil
			.getMessage("specialCharError");
	
	public static final String INPUT_REQUIRED_MESSAGE = PropertiesUtil
			.getMessage("inputRequiredMessage");
	
	public static final String MAX_LENGTH_ERROR = PropertiesUtil
			.getMessage("maxLengthError");
	
	public static final String USER_ID_INVALID_EXCEPTION= PropertiesUtil
			.getMessage("inValidUserIdMessage");
	
	public static final String INVALID_AGE_MESSAGE= PropertiesUtil
			.getMessage("inValidAgeMessage");
	
	public static final String INVALID_PASSWORD_FORMAT_ERROR= PropertiesUtil
			.getMessage("passwordFormatError");
	
	public static final String MIN_LENGTH_ERROR = PropertiesUtil
			.getMessage("minLengthError");
}
