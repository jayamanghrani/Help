/**
 * 
 */
package com.tcs.docstore.transformation.email.transformers;

import org.apache.log4j.Logger;

import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.email.asbo.request.EmailServiceRequestASBO;

/**
 * @author 738566
 *
 */
public class EmailTransformer {

	/** The Constant LOGGER. .. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	/** The get content alfresco request asbo. */
	GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;

	/** The get content alfresco response asbo. */
	GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO;
	
	/** The email service request asbo. */
	EmailServiceRequestASBO emailServiceRequestASBO;
	
	
	/**
	 * Instantiates a new email transformer.
	 */
	public EmailTransformer() {
		emailServiceRequestASBO = new EmailServiceRequestASBO();
		
	}


}
