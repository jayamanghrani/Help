/**
 * 
 */
package com.tcs.employeeportal.persist.dao;

import java.util.List;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public interface GetTickerValuesDao {

	EmployeePortalResponseObject getTickerValues(TickerDBRequestASBO getTickerValues);

	List<String> getTickerListValues(TickerDBRequestASBO getTickerValues) throws Exception;

}
