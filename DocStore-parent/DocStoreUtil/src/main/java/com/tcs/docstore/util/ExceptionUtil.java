package com.tcs.docstore.util;

import org.apache.log4j.Logger;

import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.vo.cmo.Header;

/**
 * The Class ExceptionUtil.
 * 
 * @author 738566
 */
/**
 * @author 738566
 *
 */
public class ExceptionUtil {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(ExceptionUtil.class);


	/**
	 * Gets the technical error vo.
	 * 
	 * @param header
	 *            the header
	 * @param errorCode
	 *            the error code
	 * @param exception
	 *            the exception
	 * @return the technical error vo
	 */
	public static ErrorVO getTechnicalErrorVO(Header header, int errorCode,
			Exception exception) {
		ErrorVO errorVO = new ErrorVO();
	//	errorVO.setApplicationId(header.getApplicationId());
		errorVO.setErrorCode(errorCode);
		errorVO.setErrorMessage(MessageConstants.APPLICATION_EXCEPTION_MESSAGE);
		return errorVO;
	}
	


	/**
	 * Gets the header missing error from OAM
	 * @param header
	 * @param errorCode
	 * @param exception
	 * @return
	 */
	public static ErrorVO getUserIDErrorVO(Exception exception) {
		ErrorVO errorVO = new ErrorVO();
	//	errorVO.setApplicationId(header.getApplicationId());
	//	errorVO.setErrorCode(errorCode);
	//	errorVO.setErrorMessage(exception.getMessage());
		errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
		errorVO.setErrorMessage("The user is not authenticated");
		/*if (errorCode == UtilConstants.ERROR_CODE_TechnichalException) {
			LOGGER.error(header.getEventID() + " "
					+ MessageConstants.USER_ID_MISS_EXCEPTION, exception);
		} else if (errorCode == UtilConstants.ERROR_CODE_NOUSERID_Exception) {
			LOGGER.info(header.getEventID()
					+ " No response from cache.", exception);
		}
		*/
		return errorVO;
	}
	
	
	public static ErrorVO getConnectionRefusedErrorVO(Header header, int errorCode, Exception ex) {
		ErrorVO errorVO = new ErrorVO();
		//errorVO.setApplicationId(header.getApplicationId());
		errorVO.setErrorCode(errorCode);
		errorVO.setErrorMessage(ex.getMessage());
		LOGGER.error(header.getEventID() + " " + ex.getMessage(), ex);
		return errorVO;
	}
	
	

	public static ErrorVO getApplicationIdErrorVO(
			IntegrationTechnicalException exception) {
		ErrorVO errorVO = new ErrorVO();
	//	errorVO.setApplicationId(null);
		errorVO.setErrorCode(UtilConstants.ERROR_CODE_TechnichalException);
		errorVO.setErrorMessage("Application ID is required");
		errorVO.setReason("Application ID is missing as part of header");
		LOGGER.info("Application ID is missing as part of header", exception);
		return errorVO;
	}
	
	/**
	 * Gets the transformation error vo.
	 * 
	 * @param header
	 *            the header
	 * @param typeOfTransformation
	 *            the type of transformation
	 * @param exception
	 *            the exception
	 * @return the transformation error vo
	 */
	public static ErrorVO getTransformationErrorVO(Header header,
			String typeOfTransformation,
			IntegrationTransformationException exception) {
		ErrorVO errorVO = new ErrorVO();

		/*errorVO.setApplicationId(header.getApplicationId());*/
		errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
		errorVO.setErrorMessage(MessageConstants.APPLICATION_EXCEPTION_MESSAGE);
		if ("request".equalsIgnoreCase(typeOfTransformation)) {
			LOGGER.error(header.getEventID() + " "
					+ MessageConstants.REQUEST_TRANSFORMATION_FAILED, exception);
		} else if ("repsonse".equalsIgnoreCase(typeOfTransformation)) {
			LOGGER.error(header.getEventID() + " "
					+ MessageConstants.RESPONSE_TRANSFORMATION_FAILED,
					exception);
		}
		return errorVO;
	}
	
	/**
	 * Gets the transformation error vo.
	 * 
	 * @param header
	 *            the header
	 * @param typeOfTransformation
	 *            the type of transformation
	 * @param exception
	 *            the exception
	 * @return the transformation error vo
	 */
	
	public static ErrorVO getTransformationErrorVOException(Header header,
			String typeOfTransformation,
			Exception exception) {
		ErrorVO errorVO = new ErrorVO();

		/*errorVO.setApplicationId(header.getApplicationId());*/
		errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
		errorVO.setErrorMessage(MessageConstants.APPLICATION_EXCEPTION_MESSAGE);
		if ("request".equalsIgnoreCase(typeOfTransformation)) {
			LOGGER.error(header.getEventID() + " "
					+ MessageConstants.REQUEST_TRANSFORMATION_FAILED, exception);
		} else if ("repsonse".equalsIgnoreCase(typeOfTransformation)) {
			LOGGER.error(header.getEventID() + " "
					+ MessageConstants.RESPONSE_TRANSFORMATION_FAILED,
					exception);
		}
		return errorVO;
	}
	

	/**
	 * Gets the parsing error vo.
	 * 
	 * @param header
	 *            the header
	 * @param errorCode
	 *            the error code
	 * @param exception
	 *            the exception
	 * @return the technical error vo
	 */
	public static ErrorVO getParseErrorVO(Exception exception) {
		ErrorVO errorVO = new ErrorVO();
	//	errorVO.setApplicationId(header.getApplicationId());
		errorVO.setErrorCode(UtilConstants.ERROR_CODE_PARSING_ERROR);
		errorVO.setErrorMessage("Error Parsing date frmat");
		return errorVO;
	}
	

}
