package com.tcs.docstore.alfresco.asbo.response;

import java.util.List;

import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

public class GetContentResponseASBO extends DocStoreResponseObject {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 8518293422189859003L;

	/** The content data list. */
	private List<ContentData> contentDataList;
	
	/** The result. */
	private String result;
	
//	private List<ContentDataASBO> contentDataASBOList;

	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Gets the content data list.
	 *
	 * @return the contentDataList
	 */
	public List<ContentData> getContentDataList() {
		return contentDataList;
	}

	/**
	 * Sets the content data list.
	 *
	 * @param contentDataList            the contentDataList to set
	 */
	public void setContentDataList(List<ContentData> contentDataList) {
		this.contentDataList = contentDataList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("GetContentResponseASBO [contentDataList=");
		builder.append(contentDataList);
		builder.append("]");
		return builder.toString();
	}
}
