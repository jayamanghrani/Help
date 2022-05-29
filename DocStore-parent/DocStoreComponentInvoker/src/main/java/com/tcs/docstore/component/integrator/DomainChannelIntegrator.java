package com.tcs.docstore.component.integrator;

import com.tcs.docstore.component.integrator.ChannelIntegrator;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

public class DomainChannelIntegrator extends ChannelIntegrator {

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.component.integrator.ChannelIntegrator#execute(com.tcs.bancsins.integration.vo.cmo.BaNCSIntegrationRequestObject)
	 */
	@Override
	public DocStoreResponseObject execute(DocStoreRequestObject request)
			throws IntegrationTechnicalException {
		return cacheManagerInvoker(request);
	}

}