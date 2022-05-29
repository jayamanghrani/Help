/**
 * 
 */
package com.tcs.docstore.log.cmo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @author 738566
 *
 */
public class LoggerRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8414946542533912683L;

	/** The entry time. */
	private Date entryTime;

	/** The method. */
	private String method;

	/** The co relation id. */
	private String coRelationId;

	/** The headers. */
	private List<LoggerHeader> headers;

	/** The response json. */
	private String requestJson;
	
	private Map<String, String[]> parameterMap;
	
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
	 * Gets the method.
	 *
	 * @return the method
	 */
	public String getMethod() {
		return method;
	}

	/**
	 * Sets the method.
	 *
	 * @param method            the method to set
	 */
	public void setMethod(String method) {
		this.method = method;
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
	 * Gets the request json.
	 *
	 * @return the requestJson
	 */
	public String getRequestJson() {
		return requestJson;
	}

	/**
	 * Sets the request json.
	 *
	 * @param requestJson            the requestJson to set
	 */
	public void setRequestJson(String requestJson) {
		this.requestJson = requestJson;
	}

	
	/**
	 * @return the parameterMap
	 */
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}

	/**
	 * @param parameterMap the parameterMap to set
	 */
	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LoggerRequest [entryTime=");
		builder.append(entryTime);
		builder.append(", method=");
		builder.append(method);
		/*builder.append(", coRelationId=");
		builder.append(coRelationId);*/
		builder.append(", headers=");
		builder.append(headers);
		builder.append(", requestJson=");
		builder.append(requestJson);
		builder.append(", parameterMap=");
		builder.append(parameterMap);
		builder.append("]");
		return builder.toString();
	}

}

