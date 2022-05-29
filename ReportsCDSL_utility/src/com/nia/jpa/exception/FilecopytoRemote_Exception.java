package com.nia.jpa.exception;

import javax.xml.bind.JAXBException;

import com.nia.helper.Constants;
import com.nia.helper.ResourceManager;

public class FilecopytoRemote_Exception extends DaoException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FilecopytoRemote_Exception(String message)
	{
		super(message);
		String signature=Constants.signature+Constants.signature_constant;
		try {
			ResourceManager.getInstance().sendMailOnError(Constants.salutation
					+ message + signature);
		} catch (Mail_Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public FilecopytoRemote_Exception(String message, String errorCode, String errorMsg, boolean isClientHandle, Throwable cause) {
		super(message,cause);
		super.ErrorCode = errorCode;
		super.ErrorMsg = errorMsg;
		super.IsClientHandle = isClientHandle;
	}


}
