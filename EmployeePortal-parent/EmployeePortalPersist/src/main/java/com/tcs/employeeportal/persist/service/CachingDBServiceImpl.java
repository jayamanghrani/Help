/**
 * 
 */
package com.tcs.employeeportal.persist.service;

import java.util.List;

import com.tcs.employeeportal.db.asbo.request.TickerDBRequestASBO;
import com.tcs.employeeportal.persist.dao.GetTickerValuesDao;
import com.tcs.employeeportal.persist.dao.GetTickerValuesDaoImpl;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 585226
 *
 */
public class CachingDBServiceImpl implements CachingDBService {
	private GetTickerValuesDao gettickervaluedao;
	public CachingDBServiceImpl (){
		gettickervaluedao = new GetTickerValuesDaoImpl();
	}

	@Override
	public List<String> getTickerValues(TickerDBRequestASBO getTickerValues) throws Exception {
		return gettickervaluedao.getTickerListValues(getTickerValues);
	}

}
