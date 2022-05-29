package com.tcs.umsoid.exception.cmo;

public class IntegrationTechnicalException extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8249896028353426339L;

	public IntegrationTechnicalException() {
		super();
	}

	/**
	 * 
	 * @param reason
	 */
	public IntegrationTechnicalException(String reason) {
		super(reason);
	}

	/**
	 * 
	 * @param cause
	 */
	public IntegrationTechnicalException(Throwable cause) {
		super(cause);
	}

	/**
	 * 
	 * @param reason
	 * @param cause
	 */
	public IntegrationTechnicalException(String reason, Throwable cause) {
		super(reason, cause);
	}
}



