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
public class GetListOfIssuedByResponseASBO  extends DocStoreResponseObject{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<String> IssuedByList;

	/**
	 * @return the issuedByList
	 */
	public List<String> getIssuedByList() {
		return IssuedByList;
	}

	/**
	 * @param issuedByList the issuedByList to set
	 */
	public void setIssuedByList(List<String> issuedByList) {
		IssuedByList = issuedByList;
	}

}
