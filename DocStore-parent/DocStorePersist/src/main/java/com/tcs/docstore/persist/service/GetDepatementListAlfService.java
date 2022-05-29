/**
 * 
 */
package com.tcs.docstore.persist.service;

import java.util.List;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetDepartmentListDBResponseASBO;

/**
 * @author 585226
 *
 */
public interface GetDepatementListAlfService {
	
	public GetDepartmentListDBResponseASBO getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList);

}
