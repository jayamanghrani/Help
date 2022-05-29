package com.nia.jpa.exception;

public class ReverseFeedDaoException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ReverseFeedDaoException(String message)
	{
		super(message);
	}

	public ReverseFeedDaoException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
