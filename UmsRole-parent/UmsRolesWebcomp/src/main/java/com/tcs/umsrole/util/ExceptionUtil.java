package com.tcs.umsrole.util;


import org.apache.log4j.Logger;

import com.tcs.umsrole.exception.cmo.ErrorVO;
import com.tcs.umsrole.exception.cmo.IntegrationTechnicalException;
import com.tcs.umsrole.exception.cmo.IntegrationTransformationException;
import com.tcs.umsrole.vo.cmo.Header;
import com.tcs.umsrole.vo.util.UtilConstants;











public class ExceptionUtil {

	/** The logger. */
	private static final Logger LOGGER = Logger.getLogger(ExceptionUtil.class);
	
	/**
	 * Gets the header missing error from OAM
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
	
	
	public static ErrorVO getConnectionRefusedErrorVO(Header header, int errorCode, Exception ex) {
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
	
}

