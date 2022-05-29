/**
 * 
 */
package com.tcs.docstore.persist.service;

import java.util.List;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;

/**
 * @author 585226
 *
 */
public interface CachingDBDepartmentService {

	List<String> getDepartmentList(GetDepartmentListAlfrescoDBRequestASBO getDepartmentList);

}
