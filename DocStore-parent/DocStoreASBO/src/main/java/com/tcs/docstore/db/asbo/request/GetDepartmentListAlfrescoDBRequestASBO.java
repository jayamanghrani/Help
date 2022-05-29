/**
 * 
 */
package com.tcs.docstore.db.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class GetDepartmentListAlfrescoDBRequestASBO extends DocStoreRequestObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String listDepartments;

	/**
	 * @return the listDepartments
	 */
	public String getListDepartments() {
		return listDepartments;
	}

	/**
	 * @param listDepartments the listDepartments to set
	 */
	public void setListDepartments(String listDepartments) {
		this.listDepartments = listDepartments;
	}

}
