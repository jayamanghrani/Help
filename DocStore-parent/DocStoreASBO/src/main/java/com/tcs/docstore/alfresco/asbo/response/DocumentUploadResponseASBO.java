/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.response;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class DocumentUploadResponseASBO extends DocStoreResponseObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7428771120583983077L;
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
