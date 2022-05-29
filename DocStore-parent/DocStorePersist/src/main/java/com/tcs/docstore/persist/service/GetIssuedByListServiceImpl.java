/**
 * 
 */
package com.tcs.docstore.persist.service;

import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetListOfIssuedByDBResponseASBO;
import com.tcs.docstore.persist.dao.GetIssuedByListDao;
import com.tcs.docstore.persist.dao.GetIssuedByListDaoImpl;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class GetIssuedByListServiceImpl  implements GetIssuedByListService{
	
	private GetIssuedByListDao getissuedbylistdao;

	
	public GetIssuedByListServiceImpl(){
		getissuedbylistdao = new GetIssuedByListDaoImpl();
	}
	

	@Override
	public DocStoreResponseObject getIssuedBy(
			GetListOfIssuedByDBRequestASBO getIssuedByList) {
		// TODO Auto-generated method stub
		GetListOfIssuedByDBResponseASBO getlistissueddbrespasbo = new GetListOfIssuedByDBResponseASBO();

		getlistissueddbrespasbo = getissuedbylistdao.getIssuedByList(getIssuedByList);
		return getlistissueddbrespasbo;
	}

}
