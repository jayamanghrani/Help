package com.nia.jpa.exception;

public class Job_monitor_Exception extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public Job_monitor_Exception(String message)
	{
		super(message);
	}

	public Job_monitor_Exception(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
