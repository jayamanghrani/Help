/**
 * 
 */
package com.tcs.employeeportal.transformation.alfresco.transformers;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.employeeportal.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.employeeportal.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.employeeportal.asbo.alfresco.ContentData;
import com.tcs.employeeportal.config.utils.UtilProperties;
import com.tcs.employeeportal.exception.cmo.IntegrationTransformationException;
import com.tcs.employeeportal.vo.cmo.EmployeePortalResponseObject;

/**
 * @author 738566
 *
 */
public class GetExecutiveMsgTransformer {
	
	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getAlfrescoContentRequestASBO;
	
	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO;
	
	/** The get content request asbo. */
	private GetContentRequestASBO getContentRequestASBO;
	
	/** The get content response asbo. */
	private GetContentResponseASBO getContentResponseASBO;
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("empPortalLogger");
	
	/**
	 * Instantiates a new gets the GetExecutiveMsgTransformer.
	 */
	public GetExecutiveMsgTransformer(){
		this.getContentResponseASBO = new GetContentResponseASBO();
	}

	/**
	 * Transform response.
	 *
	 * @param getContentAlfrescoResponseASBO the get content alfresco response asbo
	 * @return the employeeportal response vo
	 * @throws IntegrationTransformationException the integration transformation exception
	 */
	public EmployeePortalResponseObject transformResponse(
			GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
					throws IntegrationTransformationException {
		this.getAlfrescoContentResponseASBO = getContentAlfrescoResponseASBO;

		this.getContentResponseASBO
		.setHeader(this.getAlfrescoContentResponseASBO.getHeader());
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
		
		
		for (com.tcs.employeeportal.asbo.alfresco.ContentDataASBO contentData : getAlfrescoContentResponseASBO.getContentDataList()) {
			ContentData data = getCMBOContentData(contentData);
			if(data!=null){
			contentDataList.add(data);
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
			com.tcs.employeeportal.asbo.alfresco.ContentDataASBO contentData) {
		ContentData data = new ContentData();
		String docURL=null;
	
					data.setContent(contentData.getContent());
					data.setDescription(contentData.getDescription());
					data.setDocName(contentData.getDocName());
					/*if(contentData.getDocUrl()!=null){
						docURL=contentData.getDocUrl().replace(PropertiesUtil.getConfigProperty("alfWebURL"), PropertiesUtil.getConfigProperty("alfEmpURL"));
						LOGGER.debug("docURL--"+docURL);
						}*/
					
					//Added for alfresco url change
					if(contentData.getDocUrl()!=null){
						docURL=contentData.getDocUrl();
					//	docURL=contentData.getDocUrl().replace(UtilProperties.getAlfWebURL(), UtilProperties.getAlfEmpURL());
					if(docURL.endsWith(",")){
						docURL=docURL.substring(0, docURL.length()-1);
					}
					LOGGER.debug("docURL--"+docURL);
						}
					data.setDocUrl(docURL);
					data.setImageContentURL(contentData.getImageContentURL());
					data.setImageLink(contentData.getImageLink());
					data.setLabel(contentData.getLabel());
					data.setNewsRefId(contentData.getNewsRefId());
					data.setOrderNum(contentData.getOrderNum());
					data.setImageText(contentData.getImageText());
					data.setContentStartDate(contentData.getContentStartDate());
					data.setContentEndDate(contentData.getContentEndDate());
					data.setModifiedDate(contentData.getModifiedDate());
					//
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
					data.setDisplayType(contentData.getDisplayType());
					data.setTitle(contentData.getTitle());
			//		data.setGroup(groups);
			//		data.setChannel(channels);
				
		//	}
			
			
			/*	}
		else{
			data=null;
			System.out.println("NO ALFRESCO CONTENT");
		}*/
		
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
