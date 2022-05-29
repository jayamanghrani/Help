/**
 * 
 */
package com.tcs.docstore.alfresco.asbo.response;

import java.util.List;

import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class DocSearchResponseASBO extends DocStoreResponseObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3727681539319996096L;
	
	private List<ContentData> contentDataList;

	/**
	 * @return the contentDataList
	 */
	public List<ContentData> getContentDataList() {
		return contentDataList;
	}

	/**
	 * @param contentDataList the contentDataList to set
	 */
	public void setContentDataList(List<ContentData> contentDataList) {
		this.contentDataList = contentDataList;
	}
	

}
