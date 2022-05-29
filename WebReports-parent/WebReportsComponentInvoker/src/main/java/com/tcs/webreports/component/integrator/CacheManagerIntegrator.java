package com.tcs.webreports.component.integrator;

import com.tcs.webreports.exception.cmo.IntegrationTechnicalException;
import com.tcs.webreports.vo.cmo.WebReportsRequestObject;
import com.tcs.webreports.vo.cmo.WebReportsResponseObject;

public class CacheManagerIntegrator extends ChannelIntegrator{

	@Override
	public WebReportsResponseObject execute(WebReportsRequestObject request)
			throws IntegrationTechnicalException {
		return super.cacheManagerInvoker(request);
	}

}
