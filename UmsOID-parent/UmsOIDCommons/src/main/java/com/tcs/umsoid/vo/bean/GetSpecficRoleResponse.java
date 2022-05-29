package com.tcs.umsoid.vo.bean;

public class GetSpecficRoleResponse {
	String Stauts;
	String ErrorCode;
	String ErrrorMessage;
	

	/**
	 * @param errorCode
	 * @param errrorMessage
	 */


	
	
	/**
	 * @return the errorCode
	 */
	public String getErrorCode() {
		return ErrorCode;
	}

	/**
	 * @param stauts
	 */
	public GetSpecficRoleResponse(String stauts) {
		super();
		Stauts = stauts;
	}

	/**
	 * @param stauts
	 * @param errorCode
	 * @param errrorMessage
	 */
	public GetSpecficRoleResponse(String stauts, String errorCode,
			String errrorMessage) {
		super();
		Stauts = stauts;
		ErrorCode = errorCode;
		ErrrorMessage = errrorMessage;
	}

	public GetSpecficRoleResponse() {
	}

	/**
	 * @param errorCode the errorCode to set
	 */
	public void setErrorCode(String errorCode) {
		ErrorCode = errorCode;
	}

	/**
	 * @return the errrorMessage
	 */
	public String getErrrorMessage() {
		return ErrrorMessage;
	}

	/**
	 * @param errrorMessage the errrorMessage to set
	 */
	public void setErrrorMessage(String errrorMessage) {
		ErrrorMessage = errrorMessage;
	}

	/**
	 * @return the stauts
	 */
	public String getStauts() {
		return Stauts;
	}

	/**
	 * @param stauts the stauts to set
	 */
	public void setStauts(String stauts) {
		Stauts = stauts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	
	

}
