package com.nia.jpa.exception;

public class batch_job_status_daoImpl_Exception extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public batch_job_status_daoImpl_Exception(String message)
	{
		super(message);
	}

	public batch_job_status_daoImpl_Exception(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
