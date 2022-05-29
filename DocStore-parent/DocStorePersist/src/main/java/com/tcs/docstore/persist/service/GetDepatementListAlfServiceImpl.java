/**
 * 
 */
package com.tcs.docstore.persist.service;

import java.util.ArrayList;
import java.util.List;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetDepartmentListDBResponseASBO;
import com.tcs.docstore.persist.dao.GetDepartmentListAlfrescoDao;
import com.tcs.docstore.persist.dao.GetDepartmentListAlfrescoDaoImpl;
import com.tcs.docstore.persist.entities.TDocdetail;

/**
 * @author 585226
 *
 */
public class GetDepatementListAlfServiceImpl implements GetDepatementListAlfService {
	
	private GetDepartmentListAlfrescoDao getdepartmentlistalfdao;
	private GetDepartmentListDBResponseASBO getdepartmentListDBResponseASBO;
	
	
	public GetDepatementListAlfServiceImpl(){
		getdepartmentlistalfdao= new GetDepartmentListAlfrescoDaoImpl();
		getdepartmentListDBResponseASBO = new GetDepartmentListDBResponseASBO();
	}
	
	@SuppressWarnings("unchecked")
	public GetDepartmentListDBResponseASBO getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList){

		//GetDepartmentListDBResponseASBO getdepartmentListDBResponseASBO = null;
		List<String> getdepartmentLists;
		
		getdepartmentLists = new ArrayList<String>();

		getdepartmentLists = getdepartmentlistalfdao.getDepartmentList(getDepartmentList);

		getdepartmentListDBResponseASBO = transformFromDbResponse(getdepartmentLists);
		
		return  getdepartmentListDBResponseASBO;
	}

	private GetDepartmentListDBResponseASBO transformFromDbResponse(List<String> getdepartmentLists) {
		// TODO Auto-generated method stub
		getdepartmentListDBResponseASBO.setGetDepartmentNameList(getdepartmentLists);
		return 	getdepartmentListDBResponseASBO;
		
	}

}
