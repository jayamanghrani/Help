package com.nia.jpa.exception;

import javax.xml.bind.JAXBException;

import com.nia.helper.ResourceManager;

public class SignPayment_Exception extends DaoException {
	
	private static final long serialVersionUID = 1L;

	public SignPayment_Exception(String message)
	{
		super(message);
		try {
			ResourceManager.getInstance().sendMailOnError(message);
		} catch (Mail_Exception ex) {
			// TODO Auto-generated catch block

		} catch (JAXBException ex) {
			// TODO Auto-generated catch block
			
		}
	}

	public SignPayment_Exception(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}

}
