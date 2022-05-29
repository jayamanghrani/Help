/**
 * 
 */
package com.tcs.docstore.vo.cmo;

import java.io.Serializable;

import com.tcs.docstore.vo.cmo.Header;

/**
 * @author 738566
 *
 */
public class DocStoreResponseObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4613185484840532712L;
	/** The Constant serialVersionUID. */



	/** The header. */
	private Header				header;

	/**
	 * Gets the header.
	 * 
	 * @return the header
	 */
	public Header getHeader() {
			return header;
	}

	/**
	 * Sets the header.
	 * 
	 * @param header
	 *            the header to set
	 */
	public void setHeader(Header header) {
		this.header = header;
		
	}

}

