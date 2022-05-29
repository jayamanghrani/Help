package com.nia.jpa.exception;

public class OfMonitoringDaoException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OfMonitoringDaoException(String message)
	{
		super(message);
	}

	public OfMonitoringDaoException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
