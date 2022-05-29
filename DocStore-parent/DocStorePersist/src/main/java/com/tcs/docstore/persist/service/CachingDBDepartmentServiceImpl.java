/**
 * 
 */
package com.tcs.docstore.persist.service;

import java.util.List;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.persist.dao.GetDepartmentListAlfrescoDao;
import com.tcs.docstore.persist.dao.GetDepartmentListAlfrescoDaoImpl;

/**
 * @author 585226
 *
 */
public class CachingDBDepartmentServiceImpl implements CachingDBDepartmentService{
	
	private GetDepartmentListAlfrescoDao  gafldao;
	
	public CachingDBDepartmentServiceImpl(){
		gafldao = new GetDepartmentListAlfrescoDaoImpl();
	}

	@Override
	public List<String> getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList) {
		// TODO Auto-generated method stub
		gafldao.getDepartmentList(getDepartmentList);
		return gafldao.getDepartmentList(getDepartmentList);
	}

}
