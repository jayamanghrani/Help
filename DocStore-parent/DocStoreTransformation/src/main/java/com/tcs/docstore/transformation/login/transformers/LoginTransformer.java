/**
 * 
 */
package com.tcs.docstore.transformation.login.transformers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.asbo.alfresco.AlfrescoInputs;
import com.tcs.docstore.asbo.alfresco.ContentDataASBO;
import com.tcs.docstore.asbo.login.request.LoginRequestASBO;
import com.tcs.docstore.asbo.login.response.LoginResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class LoginTransformer {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
	
	/** The get content alfresco request asbo. */
	private GetAlfrecoContentRequestASBO getAlfrecoContentRequestASBO;
	
	/** The get content alfresco response asbo. */
	private GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO;
		
	/** Login ASBO request*/
	private LoginRequestASBO loginRequestASBO;
	
	/** Login ASBO response*/
	private LoginResponseASBO loginResponseASBO;
	
	public LoginTransformer(){

		loginResponseASBO= new LoginResponseASBO();
	}

	/**
	 * Transform login request.
	 * @param loginRequestASBO
	 * @return
	 */
	public DocStoreResponseObject transformLoginAddLink(GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO)throws IntegrationTransformationException {
	
		LOGGER.info("response tranformer transformLoginAddLink request method");
		this.getAlfrescoContentResponseASBO = getAlfrescoContentResponseASBO;
		loginResponseASBO.setHeader(getAlfrescoContentResponseASBO.getHeader());
		List<String> items = Arrays.asList(getAlfrescoContentResponseASBO.getHeader().getGroups().split("\\s*,\\s*"));
		List<AlfrescoInputs> groupList= new ArrayList<AlfrescoInputs>();
		
		LOGGER.debug("items////"+new Gson().toJson(items));
		LOGGER.debug("getAlfrescoContentResponseASBO.getContentDataList().size()////"+getAlfrescoContentResponseASBO.getContentDataList().size());
		ContentDataASBO contentData = null;

		try {
				
				for (int i = 0; i < getAlfrescoContentResponseASBO.getContentDataList().size(); i++) {
					LOGGER.info("getAlfrescoContentResponseASBO.getContentDataList()---"+getAlfrescoContentResponseASBO.getContentDataList().get(i));
					AlfrescoInputs group = new AlfrescoInputs();
					if(getAlfrescoContentResponseASBO.getContentDataList().get(i).getChannel().contains("[EMPLOYEE]"))
					{
						LOGGER.info("getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup()----"+getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup());
						if(getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup()==null/* || getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup().isEmpty()*/){
							contentData = getAlfrescoContentResponseASBO.getContentDataList().get(i);
							LOGGER.info("Inside group nullcontentData----"+contentData);
							group.setAdditionalLink(getAlfrescoContentResponseASBO.getContentDataList().get(i).getAdditionalLink());
							group.setLabel(getAlfrescoContentResponseASBO.getContentDataList().get(i).getLabel());
							group.setOrderNumber(Integer.toString(getAlfrescoContentResponseASBO.getContentDataList().get(i).getOrderNum()));
							groupList.add(group);

						}
					else 
						{
						LOGGER.info("Inside group else nullcontentData----"+contentData);
				
							for(int j=0; j<items.size();j++) {
							if (/*getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup()==null ||*/ getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup().toUpperCase().contains(items.get(j).toUpperCase()))
					{
						LOGGER.debug("(items.get(j)"+items.get(j));
						contentData = getAlfrescoContentResponseASBO.getContentDataList().get(i);
						LOGGER.debug("contentData----"+contentData);
						LOGGER.debug("geting group 1"+getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup());
						LOGGER.debug("geting group ---->"+group.toString());
						LOGGER.debug("geting group 2 after if"+getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup());// omkar added 2
						group.setGroup(getAlfrescoContentResponseASBO.getContentDataList().get(i).getGroup());
						group.setAdditionalLink(getAlfrescoContentResponseASBO.getContentDataList().get(i).getAdditionalLink());
						group.setLabel(getAlfrescoContentResponseASBO.getContentDataList().get(i).getLabel());
						group.setOrderNumber(Integer.toString(getAlfrescoContentResponseASBO.getContentDataList().get(i).getOrderNum()));
						groupList.add(group);
						break;
					}
							}	
					
							}
						
				}
					else{
						group=null;
					}
					loginResponseASBO.setCwissLinks(groupList);
					
				}
				
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
			groupList=null;
		}

		LOGGER.info("-----groupList-----"+ new Gson().toJson(groupList));
		return loginResponseASBO;

	}
	
	}

