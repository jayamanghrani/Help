package com.tcs.employeeportal.component.integrator;

import com.tcs.employeeportal.component.invoker.EmployeeDBInvoker;
import com.tcs.employeeportal.exception.cmo.IntegrationTechnicalException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class LoginIntegrator {

	
	protected EmployeePortalResponseObject dBInvoker(EmployeePortalRequestObject requestVO)
			throws IntegrationTechnicalException {
		// call bancs DB service
		System.out.println("Inside EmployeePortalResponseObject");
		return new EmployeeDBInvoker().invokeDB(requestVO);

	}
}
