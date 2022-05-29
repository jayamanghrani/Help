/**
 * 
 */
package com.tcs.docstore.db.asbo.response;

import java.util.List;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class GetDepartmentListDBResponseASBO  extends DocStoreResponseObject{
	
	private List<String> getDepartmentNameList;

	/**
	 * @return the getDepartmentNameList
	 */
	public List<String> getGetDepartmentNameList() {
		return getDepartmentNameList;
	}

	/**
	 * @param getDepartmentNameList the getDepartmentNameList to set
	 */
	public void setGetDepartmentNameList(List<String> getDepartmentNameList) {
		this.getDepartmentNameList = getDepartmentNameList;
	}
}
