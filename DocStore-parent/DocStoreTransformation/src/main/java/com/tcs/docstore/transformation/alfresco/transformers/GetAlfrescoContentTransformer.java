package com.tcs.docstore.transformation.alfresco.transformers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import  com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;

public class GetAlfrescoContentTransformer {
	
	
	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getAlfrescoContentRequestASBO;
	
	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO;
	
	/** The get content request asbo. */
	private GetContentRequestASBO getContentRequestASBO;
	
	/** The get content response asbo. */
	private GetContentResponseASBO getContentResponseASBO;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
	
	/**
	 * Instantiates a new gets the content transformer.
	 */
	public GetAlfrescoContentTransformer() {
		this.getAlfrescoContentRequestASBO = new GetAlfrecoContentRequestASBO();
		this.getContentResponseASBO = new GetContentResponseASBO();
		this.getAlfrescoContentResponseASBO = new GetAlfrescoContentResponseASBO();
	}

	/**
	 * Transform request.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the EmployeePortal request vo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public DocStoreRequestObject transformRequest(
			GetContentRequestASBO getContentRequestASBO)
					throws IntegrationTransformationException {
		LOGGER.debug("Inside transformRequest");
		this.getContentRequestASBO = getContentRequestASBO;

		this.getAlfrescoContentRequestASBO.setHeader(this.getContentRequestASBO
				.getHeader());
		transformRequest();
		return this.getAlfrescoContentRequestASBO;
	}

	/**
	 * Transform request.
	 */
	private void transformRequest() {
		getAlfrescoContentRequestASBO.setChannel(getContentRequestASBO
				.getAlfrescoInput().getChannel());
		getAlfrescoContentRequestASBO.setTypeOfContent(getContentRequestASBO
				.getAlfrescoInput().getTypeOfContent());
		LOGGER.debug("Inside transformRequest---"+getAlfrescoContentRequestASBO.getTypeOfContent());
	}

	/**
	 * Transform response.
	 *
	 * @param getContentAlfrescoResponseASBO the get content alfresco response asbo
	 * @return the employeeportal response vo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public DocStoreResponseObject transformResponse(
			GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
					throws IntegrationTransformationException {
		this.getAlfrescoContentResponseASBO = getContentAlfrescoResponseASBO;
		LOGGER.debug("inside the EmployeePortalResponseObject transformResponse===size is"+getContentAlfrescoResponseASBO.getContentDataList().size());
		this.getContentResponseASBO.setHeader(this.getAlfrescoContentResponseASBO.getHeader());
		transformResponse();
		return this.getContentResponseASBO;
	}

	

	/**
	 * Transform response.
	 */
	private void transformResponse() {
		getContentResponseASBO.setContentDataList(getCMBOContentDataList());
	}
	
	
	/**
	 * Gets the CMBO content data list.
	 *
	 * @return the CMBO content data list
	 */
	private List<ContentData> getCMBOContentDataList() {
		List<ContentData> contentDataList = new ArrayList<ContentData>();
		
		
		for (com.tcs.docstore.asbo.alfresco.ContentDataASBO contentData : getAlfrescoContentResponseASBO.getContentDataList()) {
			ContentData data = getCMBOContentData(contentData);
			if(data !=null){
				LOGGER.debug("inside the List<ContentData> getCMBOContentDataList()===size is"+getAlfrescoContentResponseASBO.getContentDataList().size());
			
			contentDataList.add(data);
			LOGGER.debug("inside the List<ContentData> contentDataList() SIZE "+contentDataList.size());
			}
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
			com.tcs.docstore.asbo.alfresco.ContentDataASBO contentData) {
		ContentData data = null;
		if(contentData.getChannel()!=null){
			LOGGER.debug("Inside IF---");
			List<String> channels = getParts(contentData.getChannel());

		for(int i=0;i<channels.size();i++){
				
				if(channels.get(i).contains("EMPLOYEE") ){
					 data = new ContentData();
					 String imageURL=null;
					data.setContent(contentData.getContent());
					data.setDescription(contentData.getDescription());
					data.setDocName(contentData.getDocName());
					data.setDocUrl(contentData.getDocUrl());
					if(contentData.getImageContentURL()!=null){
					imageURL=contentData.getImageContentURL();
				//	imageURL=contentData.getImageContentURL().replace(UtilProperties.getAlfWebURL(), UtilProperties.getAlfEmpURL());
					if(imageURL.endsWith(",")){
						imageURL=imageURL.substring(0, imageURL.length()-1);
					}
					LOGGER.debug("imageURL--"+imageURL);
					}
					data.setImageContentURL(imageURL);
					data.setImageLink(contentData.getImageLink());
					data.setLabel(contentData.getLabel());
					data.setNewsRefId(contentData.getNewsRefId());
					data.setOrderNum(contentData.getOrderNum());
					data.setImageText(contentData.getImageText());
				
					if (null != contentData.getDocSize()) {
					
					
						String newDocSize=contentData.getDocSize().replace(",","");
						
						
						LOGGER.debug("Double.parseDouble(docSize)"+newDocSize);
						Double bytes = Double.parseDouble(newDocSize) / 1000;
												
							if (bytes > 1000) {
								data.setDocSize(String.format("%.2f", bytes / 1000) + " MB");
							} else {
								data.setDocSize(String.format("%.2f", bytes) + " KB");
							}
						}

					
					data.setPublishDate(contentData.getIssueDate());
			//		data.setPublishStartDate(contentData.getPublishStartDate());
					data.setUploadDate(contentData.getDeploymentDate());
		//			data.setPublishStartDate(contentData.getPublishStartDate());
					data.setDisplayType(contentData.getDisplayType());
					data.setTitle(contentData.getTitle());
					data.setContentEndDate(contentData.getContentEndDate());
					data.setContentStartDate(contentData.getContentStartDate());
					data.setModifiedDate(contentData.getModifiedDate());
			//		data.setGroup(groups);
					data.setChannel(channels);
				}
				else{
					//data=null;
					LOGGER.info("NO ALFRESCO CONTENT for Employee Portal");
				}
			}
			
			}
	//	}
		else{
			//data=null;
			LOGGER.info("NO ALFRESCO CONTENT");
		}
		
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
