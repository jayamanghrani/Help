/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class GetListOfDepartmentsASBO extends DocStoreRequestObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -644736599783348174L;
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
