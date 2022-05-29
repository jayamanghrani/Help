package com.nia.jpa.exception;

import java.io.IOException;

import javax.xml.bind.JAXBException;

import com.nia.helper.ResourceManager;

public class NoFileFoundException extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoFileFoundException(String message)
	{
		super(message);
		try {			
			ResourceManager.getInstance().sendMailOnError(message);
			ResourceManager.getInstance().sendMailOnSuccess();
		} catch (Mail_Exception e) {
			// TODO Auto-generated catch block
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public NoFileFoundException(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
