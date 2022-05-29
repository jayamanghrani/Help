/**
 * 
 */
package com.tcs.docstore.component.integrator;

import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class DocumentEmailIntegrator  extends ChannelIntegrator  {
	public DocStoreResponseObject execute(String mailDetail,DocUploadDBRequestASBO dudbreasbo)
			throws IntegrationTechnicalException {
		
		return super.DocEmailInvoker(mailDetail,dudbreasbo);
	}

	@Override
	public DocStoreResponseObject execute(
			DocStoreRequestObject request)
			throws IntegrationTechnicalException {
		// TODO Auto-generated method stub
		return null;
	}
}
