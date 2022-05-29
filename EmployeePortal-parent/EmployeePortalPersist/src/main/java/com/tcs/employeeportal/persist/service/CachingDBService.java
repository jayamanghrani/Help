/**
 * 
 */
package com.tcs.employeeportal.persist.service;

import java.util.List;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;

/**
 * @author 585226
 *
 */
public interface CachingDBService {

	List<String> getTickerValues(TickerDBRequestASBO getTickerValues) throws Exception;

}
