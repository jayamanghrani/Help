/**
 * 
 */
package com.tcs.docstore.db.asbo.response;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class DocUploadDBResponseASBO  extends DocStoreResponseObject {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String responseOfUpload;

	/**
	 * @return the responseOfUpload
	 */
	public String getResponseOfUpload() {
		return responseOfUpload;
	}

	/**
	 * @param responseOfUpload the responseOfUpload to set
	 */
	public void setResponseOfUpload(String responseOfUpload) {
		this.responseOfUpload = responseOfUpload;
	}
}
