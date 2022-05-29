/**
 * 
 */
package com.tcs.webreports.vo.cmo;

import java.io.Serializable;
import com.tcs.webreports.vo.cmo.Header;
/**
 * @author 738566
 *
 */
public class WebReportsResponseObject implements Serializable {

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

