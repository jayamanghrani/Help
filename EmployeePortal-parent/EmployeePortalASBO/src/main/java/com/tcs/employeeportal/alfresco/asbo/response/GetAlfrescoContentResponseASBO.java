package com.tcs.employeeportal.alfresco.asbo.response;

import java.util.List;

import com.tcs.employeeportal.asbo.alfresco.ContentDataASBO;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

public class GetAlfrescoContentResponseASBO extends EmployeePortalResponseObject{
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -3499181507469040035L;

	/** The content data list. */
	private List<ContentDataASBO> contentDataList;

	/**
	 * Gets the content data list.
	 *
	 * @return the contentDataList
	 */
	public List<ContentDataASBO> getContentDataList() {
		return contentDataList;
	}

	/**
	 * Sets the content data list.
	 *
	 * @param contentDataList            the contentDataList to set
	 */
	public void setContentDataList(List<ContentDataASBO> contentDataList) {
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
		builder.append("GetContentAlfrescoResponseASBO [contentDataList=");
		builder.append(contentDataList);
		builder.append("]");
		return builder.toString();
	}

}
