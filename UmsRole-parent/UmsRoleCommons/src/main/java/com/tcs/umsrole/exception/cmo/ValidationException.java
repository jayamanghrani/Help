package com.tcs.umsrole.exception.cmo;

public class ValidationException extends Exception {
	private static final long serialVersionUID = -6567981584818024921L;

	private final int errorCode;
	private final String field;
	
	public ValidationException(int errorCode, String field) {
		super();
		this.errorCode = errorCode;
		this.field = field;
	}
	public int getErrorCode() {
		return errorCode;
	}
//	public void setErrorCode(int errorCode) {
//		this.errorCode = errorCode;
//	}
	public String getField() {
		return field;
	}
//	public void setField(String field) {
//		this.field = field;
//	}
	
	
	
	
	
}
