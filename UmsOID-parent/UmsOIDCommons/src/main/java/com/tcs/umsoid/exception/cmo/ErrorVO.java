package com.tcs.umsoid.exception.cmo;

import com.tcs.umsoid.vo.cmo.UmsappResponseObject;





public class ErrorVO extends UmsappResponseObject{
	
	private static final long serialVersionUID = 1937354457314985475L;

	/** The channel id. */
	private String applicationId;

	/** The method name. */
	private String methodName;

	/** The exception type. */
	private String exceptionType;

	/** The device name. */
	private String deviceName;

	/** The error code. */
	private int errorCode;

	/** The error message. */
	private String errorMessage;

	/** The reason. */
	private String reason;

	/**
	 * @return the applicationId
	 */
	public String getApplicationId() {
		return applicationId;
	}

	/**
	 * @param applicationId
	 *            the applicationId to set
	 */
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}

	/**
	 * @return the methodName
	 */
	public String getMethodName() {
		return methodName;
	}

	/**
	 * @param methodName
	 *            the methodName to set
	 */
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	/**
	 * @return the exceptionType
	 */
	public String getExceptionType() {
		return exceptionType;
	}

	/**
	 * @param exceptionType
	 *            the exceptionType to set
	 */
	public void setExceptionType(String exceptionType) {
		this.exceptionType = exceptionType;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName
	 *            the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	/**
	 * @return the reason
	 */
	public String getReason() {
		return reason;
	}

	/**
	 * @param reason
	 *            the reason to set
	 */
	public void setReason(String reason) {
		this.reason = reason;
	}

	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ErrorVO [applicationId=");
		builder.append(applicationId);
		builder.append(", methodName=");
		builder.append(methodName);
		builder.append(", exceptionType=");
		builder.append(exceptionType);
		builder.append(", deviceName=");
		builder.append(deviceName);
		builder.append(", errorCode=");
		builder.append(errorCode);
		builder.append(", errorMessage=");
		builder.append(errorMessage);
		builder.append(", reason=");
		builder.append(reason);
		builder.append("]");
		return builder.toString();
	}


}
