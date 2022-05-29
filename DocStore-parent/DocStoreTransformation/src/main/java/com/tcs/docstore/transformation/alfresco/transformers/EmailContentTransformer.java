/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.docstore.transformation.alfresco.transformers;


import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.cache.util.CacheConstants;


// TODO: Auto-generated Javadoc
/**
 * The Class EmailContentTransformer.
 */
public class EmailContentTransformer {
	
	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;
	
	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO;
	
	/**
	 * Instantiates a new email content transformer.
	 */
	public EmailContentTransformer(){
		this.getContentAlfrescoRequestASBO = new GetAlfrecoContentRequestASBO();
	}



}
