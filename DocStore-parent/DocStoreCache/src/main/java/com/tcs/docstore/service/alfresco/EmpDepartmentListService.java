/**
 * 
 */
package com.tcs.docstore.service.alfresco;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.log4j.Logger;

import com.tcs.docstore.cache.model.DepartmentListValue;
import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.persist.service.CachingDBDepartmentService;
import com.tcs.docstore.persist.service.CachingDBDepartmentServiceImpl;
/**
 * @author 585226s
 *
 */
public class EmpDepartmentListService {

	 public static  ConcurrentMap<String, List<String>> cacheingDepartmentList=new  ConcurrentHashMap<String, List<String>>();
	/*public EmpDepartmentListService(){
		cacheingDepartmentList =new  ConcurrentHashMap<String, List<String>>();
	}*/
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
	public  ConcurrentMap<String, List<String>> cacheDepartmentList(GetDepartmentListAlfrescoDBRequestASBO gadrasbo ) {
		
		CachingDBDepartmentService cachingDeptService = new CachingDBDepartmentServiceImpl();
		DepartmentListValue dlv = new DepartmentListValue();
		List<String> departmentValuesList = null;
		if(cacheingDepartmentList!=null){
			if(cacheingDepartmentList.get(gadrasbo.getListDepartments())!=null){
				return cacheingDepartmentList;
			}
			else{
				departmentValuesList=cachingDeptService.getDepartmentList(gadrasbo);
				cacheingDepartmentList.put(gadrasbo.getListDepartments(), departmentValuesList);
				return cacheingDepartmentList;
			}
			
			
		}
		
		else{
			
			departmentValuesList=cachingDeptService.getDepartmentList(gadrasbo);
			cacheingDepartmentList.put(gadrasbo.getListDepartments(), departmentValuesList);
			return cacheingDepartmentList;
			
		}
		
	//	return null;
		
	
	}
}
