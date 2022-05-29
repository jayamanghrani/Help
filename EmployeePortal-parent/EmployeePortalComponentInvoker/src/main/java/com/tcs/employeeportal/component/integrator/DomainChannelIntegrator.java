package com.tcs.employeeportal.component.integrator;

import com.tcs.employeeportal.component.integrator.ChannelIntegrator;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class DomainChannelIntegrator extends ChannelIntegrator {

	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.component.integrator.ChannelIntegrator#execute(com.tcs.bancsins.integration.vo.cmo.BaNCSIntegrationRequestObject)
	 */
	@Override
	public EmployeePortalResponseObject execute(EmployeePortalRequestObject request)
			throws IntegrationTechnicalException {
		return cacheManagerInvoker(request);
	}

}