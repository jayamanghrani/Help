/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.request;

import com.tcs.docstore.asbo.alfresco.AlfrescoInputs;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 738566
 *
 */
public class DocSearchRequestASBO extends DocStoreRequestObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6291395251988972142L;
	
	private AlfrescoInputs alfrescoInput;

	/**
	 * @return the alfrescoInput
	 */
	public AlfrescoInputs getAlfrescoInput() {
		return alfrescoInput;
	}

	/**
	 * @param alfrescoInput the alfrescoInput to set
	 */
	public void setAlfrescoInput(AlfrescoInputs alfrescoInput) {
		this.alfrescoInput = alfrescoInput;
	}
	
	
	

}
