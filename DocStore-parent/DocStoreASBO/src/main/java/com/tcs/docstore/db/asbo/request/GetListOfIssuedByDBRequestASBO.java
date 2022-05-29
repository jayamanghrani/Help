/**
 * 
 */
package com.tcs.docstore.db.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class GetListOfIssuedByDBRequestASBO  extends DocStoreRequestObject{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @return the selectIssue
	 */
	public String getSelectIssue() {
		return selectIssue;
	}

	/**
	 * @param selectIssue the selectIssue to set
	 */
	public void setSelectIssue(String selectIssue) {
		this.selectIssue = selectIssue;
	}

	private String selectIssue;

}
