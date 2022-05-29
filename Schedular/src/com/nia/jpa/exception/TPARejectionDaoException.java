package com.nia.jpa.exception;

public class TPARejectionDaoException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TPARejectionDaoException(String message)
	{
		super(message);
	}

	public TPARejectionDaoException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
