/**
 * 
 */
package com.tcs.docstore.email.asbo.request;

import java.util.Map;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 738566
 *
 */
public class EmailServiceRequestASBO extends DocStoreRequestObject{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5504503188906619388L;
	
	/** The data. */
	private Map<String, String> data;

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public Map<String, String> getData() {
		return data;
	}

	/**
	 * Sets the data.
	 *
	 * @param data the data to set
	 */
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	
	
}
