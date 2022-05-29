package com.tcs.umsapp.search.util;

import com.tcs.umsapp.search.exception.cmo.ErrorVO;

public class ExceptionUtil {
    /** The logger. */

    /**
     * 
     * @param errorCode
     * @param exception
     * @return
     */
    public static ErrorVO getTechnicalErrorVO(int errorCode) {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(errorCode);
        errorVO.setErrorMessage("Unable to process");
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
    public static ErrorVO getUserIDErrorVO() {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
        errorVO.setErrorMessage("The user is not authenticated");
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
    public static ErrorVO getParseErrorVO() {
        ErrorVO errorVO = new ErrorVO();
        errorVO.setErrorCode(UtilConstants.ERROR_CODE_PARSING_ERROR);
        errorVO.setErrorMessage("Error Parsing date frmat");
        return errorVO;
    }
}
