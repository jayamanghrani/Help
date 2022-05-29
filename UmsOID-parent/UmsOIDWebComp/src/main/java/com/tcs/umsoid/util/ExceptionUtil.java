package com.tcs.umsoid.util;

import org.apache.log4j.Logger;

import com.tcs.umsoid.exception.cmo.ErrorVO;
import com.tcs.umsoid.exception.cmo.IntegrationTechnicalException;
import com.tcs.umsoid.exception.cmo.IntegrationTransformationException;
import com.tcs.umsoid.vo.cmo.Header;

public class ExceptionUtil {

    /** The logger. */
    private static final Logger LOGGER = Logger.getLogger("umsOIDLogger");

    public static ErrorVO getTechnicalErrorVO(Header header, int errorCode,
            Exception exception) {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(errorCode);
        return errorVO;
    }

    /**
     * Gets the header missing error from OAM
     * 
     * @param header
     * @param errorCode
     * @param exception
     * @return
     */
    public static ErrorVO getUserIDErrorVO(Exception exception) {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
        errorVO.setErrorMessage("The user is not authenticated");
        return errorVO;
    }

    public static ErrorVO getConnectionRefusedErrorVO(Header header,
            int errorCode, Exception ex) {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(errorCode);
        errorVO.setErrorMessage(ex.getMessage());
        LOGGER.error(header.getEventID() + " " + ex.getMessage(), ex);
        return errorVO;
    }

    public static ErrorVO getApplicationIdErrorVO(
            IntegrationTechnicalException exception) {
        ErrorVO errorVO = new ErrorVO();
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

        errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
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
            String typeOfTransformation, Exception exception) {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
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
        errorVO.setErrorCode(UtilConstants.ERROR_CODE_PARSING_ERROR);
        errorVO.setErrorMessage("Error Parsing date frmat");
        return errorVO;
    }

}
