/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.request;

import com.tcs.docstore.vo.cmo.DocStoreRequestObject;

/**
 * @author 585226
 *
 */
public class GetListOfIssuedByRequestASBO extends DocStoreRequestObject{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3947178839146486426L;
	
	
	private String selectIssue;


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
	

}
