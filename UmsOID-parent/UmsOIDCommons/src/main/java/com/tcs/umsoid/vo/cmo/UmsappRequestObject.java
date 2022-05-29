package com.tcs.umsoid.vo.cmo;

import java.io.Serializable;




/**
 * @author 738566
 *
 */
public class UmsappRequestObject implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3093760799316763686L;
	/** The header. */
	private Header header;

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
