package com.nia.jpa.exception;

public class MessageCountDaoException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MessageCountDaoException(String message)
	{
		super(message);
	}

	public MessageCountDaoException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
