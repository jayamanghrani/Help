/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.response;

import java.util.List;

import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class GetDepartmentNameResponseASBO extends DocStoreResponseObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
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
