/**
 * 
 */
package com.tcs.employeeportal.exception.cmo;

/**
 * @author 738566
 *
 */
/**
 * The Class IntegrationTransformationException.
 */
public final class IntegrationTransformationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6611932528444101816L;

	/**
	 * Instantiates a new integration technichal exception.
	 */

	public IntegrationTransformationException() {
		super();
	}

	/**
	 * Instantiates a new integration transformation exception.
	 *
	 * @param reason the reason
	 */
	public IntegrationTransformationException(String reason) {
		super(reason);
	}

	/**
	 * Instantiates a new integration transformation exception.
	 *
	 * @param cause the cause
	 */
	public IntegrationTransformationException(Throwable cause) {
		super(cause);
	}

	/**
	 * Instantiates a new integration transformation exception.
	 *
	 * @param reason the reason
	 * @param cause the cause
	 */
	public IntegrationTransformationException(String reason, Throwable cause) {
		super(reason, cause);
	}

}
