/**
 * 
 */
package com.tcs.employeeportal.persist.service;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.db.asbo.response.TickerDBResponseASBO;
import com.tcs.employeeportal.persist.dao.GetTickerValuesDao;
import com.tcs.employeeportal.persist.dao.GetTickerValuesDaoImpl;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public class GetTickerValuesServiceImpl implements GetTickerValuesService {
	
	private GetTickerValuesDao gettickervalueDao;
	private TickerDBResponseASBO tickerdbrespasbo;
	public GetTickerValuesServiceImpl(){
		gettickervalueDao = new GetTickerValuesDaoImpl();
		tickerdbrespasbo = new TickerDBResponseASBO();
	}

	@Override
	public EmployeePortalResponseObject getTickerValues(
			TickerDBRequestASBO getTickerValues) {
		//gettickervalueDao.getTickerValues(getTickerValues);
		// TODO Auto-generated method stub
		
		
		return gettickervalueDao.getTickerValues(getTickerValues);
	}

}
