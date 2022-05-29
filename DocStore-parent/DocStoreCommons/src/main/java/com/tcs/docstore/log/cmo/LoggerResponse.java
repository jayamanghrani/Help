/**
 * 
 */
package com.tcs.docstore.log.cmo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


/**
 * @author 738566
 *
 */
public class LoggerResponse implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 5418118047210427247L;

	/** The entry time. */
	private Date entryTime;
	
	/** The co relation id. */
	private String coRelationId;
	
	/** The headers. */
	private List<LoggerHeader> headers;
	
	/** The response json. */
	private String responseJson;
	
	/** The processing time. */
	private String processingTime;

	/**
	 * Gets the entry time.
	 *
	 * @return the entryTime
	 */
	public Date getEntryTime() {
		return entryTime;
	}

	/**
	 * Sets the entry time.
	 *
	 * @param entryTime            the entryTime to set
	 */
	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	/**
	 * Gets the co relation id.
	 *
	 * @return the coRelationId
	 */
	public String getCoRelationId() {
		return coRelationId;
	}

	/**
	 * Sets the co relation id.
	 *
	 * @param coRelationId            the coRelationId to set
	 */
	public void setCoRelationId(String coRelationId) {
		this.coRelationId = coRelationId;
	}

	/**
	 * Gets the headers.
	 *
	 * @return the headers
	 */
	public List<LoggerHeader> getHeaders() {
		return headers;
	}

	/**
	 * Sets the headers.
	 *
	 * @param headers            the headers to set
	 */
	public void setHeaders(List<LoggerHeader> headers) {
		this.headers = headers;
	}

	/**
	 * Gets the response json.
	 *
	 * @return the responseJson
	 */
	public String getResponseJson() {
		return responseJson;
	}

	/**
	 * Sets the response json.
	 *
	 * @param responseJson            the responseJson to set
	 */
	public void setResponseJson(String responseJson) {
		this.responseJson = responseJson;
	}

	/**
	 * Gets the processing time.
	 *
	 * @return the processingTime
	 */
	public String getProcessingTime() {
		return processingTime;
	}

	/**
	 * Sets the processing time.
	 *
	 * @param processingTime            the processingTime to set
	 */
	public void setProcessingTime(String processingTime) {
		this.processingTime = processingTime;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoggerResponse [entryTime=");
		builder.append(entryTime);
		/*builder.append(", coRelationId=");
		builder.append(coRelationId);*/
		builder.append(", headers=");
		builder.append(headers);
		builder.append(", responseJson=");
		builder.append(responseJson);
		builder.append(", processingTime=");
		builder.append(processingTime);
		builder.append("]");
		return builder.toString();
	}

}

