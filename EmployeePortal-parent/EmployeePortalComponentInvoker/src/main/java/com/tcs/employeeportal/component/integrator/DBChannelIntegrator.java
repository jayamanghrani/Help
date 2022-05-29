/**
 * 
 */
package com.tcs.employeeportal.component.integrator;

import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;


/**
 * @author 738566
 *
 */
/**
 * The Class DBChannelIntegrator.
 */
public class DBChannelIntegrator extends ChannelIntegrator {

	
	/* (non-Javadoc)
	 * @see com.tcs.bancsins.integration.component.integrator.ChannelIntegrator#execute(com.tcs.bancsins.integration.vo.cmo.BaNCSIntegrationRequestObject)
	 */
	@Override
	public EmployeePortalResponseObject execute(EmployeePortalRequestObject requestVO) throws IntegrationTechnicalException {
		System.out.println("Inside DBChannelIntegrator");
		return super.dBInvoker(requestVO);
	}

}
