package com.nia.jpa.exception;

public class ProgramHoldDaoException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ProgramHoldDaoException(String message)
	{
		super(message);
	}

	public ProgramHoldDaoException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
