/**
 * 
 */
package com.tcs.employeeportal.persist.service;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public interface GetTickerValuesService {

	EmployeePortalResponseObject getTickerValues(TickerDBRequestASBO getTickerValues);

}
