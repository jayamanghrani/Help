/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;
import com.tcs.docstore.persist.dao.GetHRMSDetailsDao;
import com.tcs.docstore.persist.dao.GetHRMSDetailsDaoImpl;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class GetHRMSDetailsServiceImpl implements GetHRMSDetailsService{
	
	private GetHRMSDetailsDao gethrmsdetailsdao;
	
	public GetHRMSDetailsServiceImpl(){
		gethrmsdetailsdao = new GetHRMSDetailsDaoImpl();
		
	}

	@Override
	public DocStoreResponseObject getHrmsDetailsList(GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo) {
		// TODO Auto-generated method stub
		GetHRMSDetailsDBResponseASBO gethrmsdbreqasbo = new GetHRMSDetailsDBResponseASBO();
		gethrmsdbreqasbo = gethrmsdetailsdao.gethrmsdetailsList(gethrmsdetailsdbreqasbo);
		return gethrmsdbreqasbo;
	}

}
