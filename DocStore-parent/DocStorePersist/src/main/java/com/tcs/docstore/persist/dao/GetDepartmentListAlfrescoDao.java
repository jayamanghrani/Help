/**
 * 
 */
package com.tcs.docstore.persist.dao;

import java.util.List;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;

/**
 * @author 585226
 *
 */
public interface GetDepartmentListAlfrescoDao {
	
	
	public List getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList);

}
