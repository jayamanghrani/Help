/**
 * 
 */
package com.tcs.employeeportal.transformation.alfresco.transformers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.tcs.employeeportal.asbo.alfresco.CarouselDateComparator;
import com.tcs.employeeportal.asbo.alfresco.ContentData;
import com.tcs.employeeportal.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import  com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalRequestObject;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 376448
 *
 */
public class AdditionAllLinksTransformer {
	

	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getContentAlfrescoRequestASBO;
	
	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO;
	
	/** The get content request asbo. */
	private GetContentRequestASBO getContentRequestASBO;
	
	/** The get content response asbo. */
	private GetContentResponseASBO getContentResponseASBO;

	/**
	 * Instantiates a new gets the content transformer.
	 */
	public AdditionAllLinksTransformer() {
		this.getContentAlfrescoRequestASBO = new GetAlfrecoContentRequestASBO();
		this.getContentResponseASBO = new GetContentResponseASBO();
	}

	/**
	 * Transform request.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal request vo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalRequestObject transformRequest(
			GetContentRequestASBO getContentRequestASBO)
					throws IntegrationTransformationException {
		this.getContentRequestASBO = getContentRequestASBO;

		this.getContentAlfrescoRequestASBO.setHeader(this.getContentRequestASBO
				.getHeader());
		transformRequest();
		return this.getContentAlfrescoRequestASBO;
	}

	/**
	 * Transform request.
	 */
	private void transformRequest() {
		getContentAlfrescoRequestASBO.setChannel(getContentRequestASBO
				.getAlfrescoInput().getChannel());
		getContentAlfrescoRequestASBO.setKeyWord(getContentRequestASBO
				.getAlfrescoInput().getKeyWord());
		getContentAlfrescoRequestASBO.setLanguage(getContentRequestASBO
				.getAlfrescoInput().getLanguage());
		getContentAlfrescoRequestASBO.setNewsReferenceId(getContentRequestASBO
				.getAlfrescoInput().getNewsReferenceId());
		getContentAlfrescoRequestASBO.setProcess(getContentRequestASBO
				.getAlfrescoInput().getProcess());
		getContentAlfrescoRequestASBO.setTypeOfContent(getContentRequestASBO
				.getAlfrescoInput().getTypeOfContent());
	}

	/**
	 * Transform response.
	 *
	 * @param getContentAlfrescoResponseASBO the get content alfresco response asbo
	 * @return the employee portal response vo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalResponseObject transformResponse(
			GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
					throws IntegrationTransformationException {
		this.getContentAlfrescoResponseASBO = getContentAlfrescoResponseASBO;

		this.getContentResponseASBO
		.setHeader(this.getContentAlfrescoResponseASBO.getHeader());
		transformResponse();
		return this.getContentResponseASBO;
	}

	/**
	 * Transform response.
	 */
	private void transformResponse() {
		List<ContentData> contentData = getCMBOContentDataList();
		Collections.sort(contentData);
		if(null != contentData.get(0).getModifiedDate()) {
			Collections.sort(contentData, new CarouselDateComparator());
		}
		getContentResponseASBO.setContentDataList(contentData);
	}

	/**
	 * Gets the CMBO content data list.
	 *
	 * @return the CMBO content data list
	 */
	private List<ContentData> getCMBOContentDataList() {
		List<ContentData> contentDataList = new ArrayList<ContentData>();
		for (com.tcs.employeeportal.asbo.alfresco.ContentDataASBO contentData : getContentAlfrescoResponseASBO
				.getContentDataList()) {
			ContentData data = getCMBOContentData(contentData);
			contentDataList.add(data);
		}
		return contentDataList;
	}

	/**
	 * Gets the CMBO content data.
	 *
	 * @param contentData the content data
	 * @return the CMBO content data
	 */
	private ContentData getCMBOContentData(
			com.tcs.employeeportal.asbo.alfresco.ContentDataASBO contentData) {
		ContentData data = new ContentData();

		//data.setAccordionHeader(contentData.getAccordionHeader());
		data.setAdditionalLink(contentData.getAdditionalLink());
		//data.setAnswer(contentData.getAnswer());
		if(contentData.getChannel()!=null){
			List<String> channels = getParts(contentData.getChannel());
			data.setChannel(channels);
		}
		if(null != contentData.getModifiedDate()){
			data.setModifiedDate(contentData.getModifiedDate());
		}
		data.setContent(contentData.getContent());
		data.setDescription(contentData.getDescription());
		data.setDocName(contentData.getDocName());
		data.setDocUrl(contentData.getDocUrl());
		data.setImageContentURL(contentData.getImageContentURL());
		data.setImageLink(contentData.getImageLink());
		data.setLabel(contentData.getLabel());
		data.setNewsRefId(contentData.getNewsRefId());
		data.setOrderNum(contentData.getOrderNum());
		if(contentData.getProcess()!=null){
			data.setProcess(getParts(contentData.getProcess()));
		}
		
	//	data.setPublishDate(contentData.getIssuedBy());
		data.setPublishDate(contentData.getIssueDate());
		data.setUploadDate(contentData.getDeploymentDate());
	//	data.setPublishStartDate(contentData.getPublishStartDate());
		//data.setQuestion(contentData.getQuestion());
		data.setTitle(contentData.getTitle());
		data.setDisplayType(contentData.getDisplayType());
		return data;
	}

	/**
	 * Gets the parts.
	 *
	 * @param arrayData the array data
	 * @return the parts
	 */
	private List<String> getParts(String arrayData){
		List<String> parts = new ArrayList<String>();
		String[] initialParts = arrayData.split("\\[");
		String[] secondParts = initialParts[1].split("\\]");
		String[] finalParts = secondParts[0].split(", ");
		for(int i=0;i<finalParts.length;i++){
			parts.add(finalParts[i]);
		}
		return parts;
	}


}
