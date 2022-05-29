/**
 * 
 */
package com.tcs.employeeportal.exception.cmo;

import org.apache.log4j.Logger;
;

/**
 * @author 738566
 *
 */
public class JsonErrorHandler implements IExceptionHandler {
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(JsonErrorHandler.class);

	@Override
	public void handle(ErrorVO errorVO, String className) {

		LOGGER.info(errorVO);

	}

}
