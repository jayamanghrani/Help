package com.tcs.docstore.transformation.alfresco.transformers;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.docstore.alfresco.asbo.request.DocSearchRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.SearchSpecificDetailsRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.DocSearchResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.asbo.alfresco.ContentDataASBO;
import com.tcs.docstore.asbo.emp.request.GetHRMSDetailsRequestASBO;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.util.MessageConstants;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.util.ValidationUtil;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

public class GetEmployeeDocumentsTransformer
{
  private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
  private GetAlfrecoContentRequestASBO getAlfrescoContentRequestASBO;
  private GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO;
  private DocSearchRequestASBO docSearchRequestASBO;
  private DocSearchResponseASBO docSearchResponseASBO;
  private GetContentResponseASBO getContentResponseASBO;
  private GetHRMSDetailsDBRequestASBO gethrmsdbreqasbo;

  public GetEmployeeDocumentsTransformer()
  {
    this.getAlfrescoContentRequestASBO = new GetAlfrecoContentRequestASBO();
    this.docSearchResponseASBO = new DocSearchResponseASBO();
    this.getAlfrescoContentResponseASBO = new GetAlfrescoContentResponseASBO();
    this.getContentResponseASBO = new GetContentResponseASBO();
    this.gethrmsdbreqasbo = new GetHRMSDetailsDBRequestASBO();
  }

  public DocStoreRequestObject transformRequest(DocSearchRequestASBO docSearchRequestASBO)
    throws IntegrationTransformationException
  {
    this.docSearchRequestASBO = docSearchRequestASBO;

    this.getAlfrescoContentRequestASBO.setHeader(this.docSearchRequestASBO
      .getHeader());
    transformRequest();
    return this.getAlfrescoContentRequestASBO;
  }

  private void transformRequest()
  {
    this.getAlfrescoContentRequestASBO.setChannel(this.docSearchRequestASBO.getAlfrescoInput().getChannel());

    this.getAlfrescoContentRequestASBO.setTypeOfContent(this.docSearchRequestASBO.getAlfrescoInput().getTypeOfContent());

    this.getAlfrescoContentRequestASBO.setIssuedBy(this.docSearchRequestASBO.getAlfrescoInput().getIssuedBy());
    this.getAlfrescoContentRequestASBO.setKeyWord(this.docSearchRequestASBO.getAlfrescoInput().getKeyWord());
  }

  public DocStoreResponseObject transformResponse(GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
    throws IntegrationTransformationException
  {
    this.getAlfrescoContentResponseASBO = getContentAlfrescoResponseASBO;

    this.docSearchResponseASBO.setHeader(this.getAlfrescoContentResponseASBO.getHeader());
    transformResponse();
    return this.docSearchResponseASBO;
  }

  private void transformResponse()
  {
    this.docSearchResponseASBO.setContentDataList(getCMBOContentDataList());
  }

  private List<ContentData> getCMBOContentDataList()
  {
    List<ContentData> contentDataList = new ArrayList<ContentData>();

    for (ContentDataASBO contentData : this.getAlfrescoContentResponseASBO.getContentDataList()) {
      ContentData data = getCMBOContentData(contentData);
      if (data != null) {
        contentDataList.add(data);
      }
    }
    return contentDataList;
  }

  private ContentData getCMBOContentData(ContentDataASBO contentData)
  {
    ContentData data = null;

    LOGGER.debug("Inside contentData" + new Gson().toJson(contentData));

    if (contentData.getChannel() != null) {
      LOGGER.debug("Inside IF---");
      List<String> channels = getParts(contentData.getChannel());

      for (int i = 0; i < channels.size(); i++) {
        if (((String)channels.get(i)).contains("EMPLOYEE")) {
          data = new ContentData();
          data.setContent(contentData.getContent());
          data.setDescription(contentData.getDescription());
          data.setDocName(contentData.getDocName());
          data.setDocUrl(contentData.getDocUrl());
          data.setImageContentURL(contentData.getImageContentURL());
          data.setImageLink(contentData.getImageLink());
          data.setLabel(contentData.getLabel());
          data.setNewsRefId(contentData.getNewsRefId());
          data.setOrderNum(contentData.getOrderNum());
          data.setImageText(contentData.getImageText());
          data.setDescOldEmp(contentData.getDescOldEmp());

          if (contentData.getDocSize() != null)
          {
            String newDocSize = contentData.getDocSize().replace(",", "");

            LOGGER.debug("Double.parseDouble(docSize)" + newDocSize);
            Double bytes = Double.valueOf(Double.parseDouble(newDocSize) / 1000.0D);

            if (bytes.doubleValue() > 1000.0D)
              data.setDocSize(String.format("%.2f", new Object[] { Double.valueOf(bytes.doubleValue() / 1000.0D) }) + " MB");
            else {
              data.setDocSize(String.format("%.2f", new Object[] { bytes }) + " KB");
            }

          }

          data.setPublishDate(contentData.getIssueDate());

          data.setDisplayType(contentData.getDisplayType());
          data.setTitle(contentData.getTitle());

          data.setChannel(channels);
        }
        else
        {
          LOGGER.info("NO ALFRESCO CONTENT for Employee Portal");
        }

      }

    }
    else
    {
      LOGGER.info("NO ALFRESCO CONTENT");
    }

    return data;
  }

  private List<String> getParts(String arrayData)
  {
    List<String> parts = new ArrayList<String>();
    String[] initialParts = arrayData.split("\\[");
    String[] secondParts = initialParts[1].split("\\]");
    String[] finalParts = secondParts[0].split(", ");
    for (int i = 0; i < finalParts.length; i++) {
      parts.add(finalParts[i]);
    }
    return parts;
  }

  public GetContentResponseASBO getContentResponseTransform(GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
  {
    List<ContentData> contentDataLists = new ArrayList<ContentData>();
    for (int i = 0; i < getContentAlfrescoResponseASBO.getContentDataList().size(); i++) {
      ContentData sd = new ContentData();
      sd.setAdditionalLink(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getAdditionalLink());

      sd.setContent(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getContent());
      sd.setDescription(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getEmpKeyWords());
      sd.setDescOldEmp(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDescOldEmp());

      sd.setDepartmentName(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDeptName());
      sd.setDisplayType(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDisplayType());
      sd.setDocMimeType(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocMimeType());
      sd.setDocName(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocName());
      sd.setDocSize(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocSize());
      sd.setDocUrl(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocUrl());

      sd.setModifiedDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getModifiedDate());
      sd.setPublishDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getIssueDate());

      sd.setContentStartDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getContentStartDate());
      sd.setContentEndDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getContentEndDate());

      sd.setDocumentType(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocType());
      sd.setIssuedBy(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getIssuedBy());
      sd.setUploadDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDeploymentDate());
      sd.setConfidential(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getConfidential());
      sd.setVisibleTo(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getVisibleTo());
      contentDataLists.add(sd);
    }
    this.getContentResponseASBO.setContentDataList(contentDataLists);

    return this.getContentResponseASBO;
  }

  public GetContentResponseASBO getOldDocContentResponseTransform(DocSearchRequestASBO docSearchRequestASBO, GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO)
  {
    try
    {
      for (int i = 0; i < getContentAlfrescoResponseASBO.getContentDataList().size(); i++);
      List<ContentData> all = new ArrayList<ContentData>();

      LOGGER.debug("contentDataLists.size()====" + getContentAlfrescoResponseASBO.getContentDataList().size());
      for (int i = 0; i < getContentAlfrescoResponseASBO.getContentDataList().size(); i++)
      {
        ContentData sd = new ContentData();
        sd.setDeptName(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDeptName());
        sd.setDisplayType(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDisplayType());
        sd.setDocMimeType(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocMimeType());
        sd.setDocName(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocName());
        sd.setDocSize(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocSize());
        sd.setDocUrl(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDocUrl());
        sd.setDescOldEmp(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getDescOldEmp());
        sd.setIssuedBy(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getIssuedBy());
        sd.setUploadDate(((ContentDataASBO)getContentAlfrescoResponseASBO.getContentDataList().get(i)).getUploadDate());
        all.add(sd);
      }

      /****Logic for Match all of the following starts*******/
      
      if (docSearchRequestASBO.getAlfrescoInput().getMatchMethod().equalsIgnoreCase("ALL")) {
        LOGGER.debug("Match all method selected@@");

        LOGGER.debug("inside all=========");

        List<ContentData> match1 = new ArrayList<ContentData>();
        List<ContentData> match2 = new ArrayList<ContentData>();
        List<ContentData> match3 = new ArrayList<ContentData>();
        List<ContentData> match4 = new ArrayList<ContentData>();
        List<ContentData> match5 = new ArrayList<ContentData>();

        /****Logic for Match all of the following - Document Name*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDocName().length() != 0)
        {
          String inputDocName=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDocName());
          LOGGER.debug("match1 are docSearchRequestASBO.getAlfrescoInput().getDocName()" + inputDocName);
          LOGGER.debug("the size of all the documents is --> " + all.size());

          for (int i = 0; i < all.size(); i++) {
            String filteredFileName = null;
            if(!((ContentData)all.get(i)).getDocName().isEmpty() )
            {
            filteredFileName = removeSpaces(((ContentData)all.get(i)).getDocName());
            if(!inputDocName.isEmpty() && filteredFileName.contains(inputDocName))
            {
            ContentData sd = new ContentData();
            LOGGER.debug("the document name is matched here!! for match all of the following");
            sd = (ContentData)all.get(i);
            match1.add(sd);
          }
            }
          }
          LOGGER.debug("the size of all the documents for document name is --> " + match1.size());
        }
        else {
        	match1=null;
        }
   
        /****Logic for Match all of the following - Department Name*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDeptName().length() != 0)
        {
        	String inputDeptName=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDeptName());
          LOGGER.debug("the departmnet to search is -->" + inputDeptName);
          if(match1!=null && match1.size()!=0){
        	  for (int p = 0; p < match1.size(); p++)
              {
            	  LOGGER.debug("Inside For loop of departments ");
            	  if(!((ContentData)match1.get(p)).getDeptName().isEmpty())
            	  {
            	  String filteredDept = removeSpaces(((ContentData)match1.get(p)).getDeptName()); 
                  if(!inputDeptName.isEmpty() && filteredDept.contains(inputDeptName))
                  {
                  ContentData sd = new ContentData();
                  LOGGER.debug("the department name is matched here!! for match all of the following");
                  sd = (ContentData)match1.get(p);
                  match2.add(sd);
                  LOGGER.debug("Added sd dept ");
                  }
            	  }

                  }
        	  
          }
          else {
          for (int p = 0; p < all.size(); p++)
          {
        	  LOGGER.debug("Inside For loop of departments ");
        	  if(!((ContentData)all.get(p)).getDeptName().isEmpty())
        	  {
        	  String filteredDept = removeSpaces(((ContentData)all.get(p)).getDeptName()); 
              if(!inputDeptName.isEmpty() && filteredDept.contains(inputDeptName))
              {
              ContentData sd = new ContentData();
              LOGGER.debug("the department name is matched here!! for match all of the following");
              sd = (ContentData)all.get(p);
              match2.add(sd);
              LOGGER.debug("Added sd dept ");
              }
        	  }

              }
          }
         /* if(match1!=null && match1.size()!=0){
        	  LOGGER.debug("Inside retain");
        	  match2.retainAll(match1);
          }*/
          LOGGER.info("match2==="+match2.size());

            }
            else
            {
            	if(match1!=null && match1.size()!=0) {
              match2 = match1;
            }
            	else {
            		match2=null;
            	}
            }  
        LOGGER.info("the size of all the documents for dept name is --> " + match2.size());
        
        /****Logic for Match all of the following - Issued By*******/
        if (docSearchRequestASBO.getAlfrescoInput().getIssuedBy().length() != 0)
        {
        	String inputIssuedBy=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getIssuedBy());
        	LOGGER.debug("the getIssuedBy to search is -->" + inputIssuedBy);
        	if(match2.size()!=0 && match2!=null) {
        		 for (int p = 0; p < match2.size(); p++)
                 {
                 	if(!((ContentData)match2.get(p)).getIssuedBy().isEmpty())
                 	{
               	  String filteredIssuedBy = removeSpaces(((ContentData)match2.get(p)).getIssuedBy());
               	  LOGGER.debug("the filteredIssuedBy to search is -->" + filteredIssuedBy);
                     if(!inputIssuedBy.isEmpty() && filteredIssuedBy.contains(inputIssuedBy))
                     {
                     ContentData sd = new ContentData();
                     LOGGER.debug("the issued by is matched here!! for match all of the following");
                     sd = (ContentData)match2.get(p);
                     match3.add(sd);
                   }
                 	}

                     }
        	}
        	else {
        		if ((docSearchRequestASBO.getAlfrescoInput().getDeptName().length() != 0)&& (match2.size() == 0)) {
					LOGGER.debug("No Match found for Keyword search");
					LOGGER.info("No Match found for Keyword search");
					match3 = null;
            	 }else
            	 {
            for (int p = 0; p < all.size(); p++)
            {
            	if(!((ContentData)all.get(p)).getIssuedBy().isEmpty())
            	{
          	  String filteredIssuedBy = removeSpaces(((ContentData)all.get(p)).getIssuedBy());
          	  LOGGER.debug("the filteredIssuedBy to search is -->" + filteredIssuedBy);
                if(!inputIssuedBy.isEmpty() && filteredIssuedBy.contains(inputIssuedBy))
                {
                ContentData sd = new ContentData();
                LOGGER.debug("the issued by is matched here!! for match all of the following");
                sd = (ContentData)all.get(p);
                match3.add(sd);
              }
            	}

                }
            	 }
        	}
            //LOGGER.debug("the issued by is matched here  size of match3 for match all of the following"+match3.size());
           /* if(match2.size()!=0 && match2!=null){
            	LOGGER.debug("inside issued by if--");
          	  match3.retainAll(match2);
            }*/

              }
              else
              {
              	if(match2.size()!=0 && match2!=null) {
              		LOGGER.debug("inside issued by if else--");
                match3 = match2;
              }
              	else {
              		match3=null;
              	}
              }     	
        if(match3!=null){
        LOGGER.debug("the size of all the documents for issuedby is --> " + match3.size());   	
        }
        /****Logic for Match all of the following - Description*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDescOldEmp().length() != 0)
        {
        	String inputDesc=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDescOldEmp());
        	LOGGER.debug("the getDescOldEmp to search is -->" + inputDesc);
        	if(match3.size()!=0 && match3!=null){
        		for (int p = 0; p < match3.size(); p++)
                {
                	if(!((ContentData)match3.get(p)).getDescOldEmp().isEmpty())
                	{
              	  String filteredDescOldEmp = removeSpaces(((ContentData)match3.get(p)).getDescOldEmp());
              	  LOGGER.debug("the filteredDescOldEmp to search is -->" + filteredDescOldEmp);
                    if(!inputDesc.isEmpty() && filteredDescOldEmp.contains(inputDesc))
                    {
                    ContentData sd = new ContentData();
                    LOGGER.debug("the DescOldEmp is matched here!! for match all of the following");
                    sd = (ContentData)match3.get(p);
                    match4.add(sd);
                  }
                	}

                    }
        	}
        	else {
            for (int p = 0; p < all.size(); p++)
            {
            	if(!((ContentData)all.get(p)).getDescOldEmp().isEmpty())
            	{
          	  String filteredDescOldEmp = removeSpaces(((ContentData)all.get(p)).getDescOldEmp());
          	  LOGGER.debug("the filteredDescOldEmp to search is -->" + filteredDescOldEmp);
                if(!inputDesc.isEmpty() && filteredDescOldEmp.contains(inputDesc))
                {
                ContentData sd = new ContentData();
                LOGGER.debug("the DescOldEmp is matched here!! for match all of the following");
                sd = (ContentData)all.get(p);
                match4.add(sd);
              }
            	}

                }
            LOGGER.debug("the DescOldEmp is matched here  size of match4 for match all of the following"+match4.size());
        	}
           /* if(match3.size()!=0 && match3!=null){
            	LOGGER.debug("inside DescOldEmp if--");
          	  match4.retainAll(match3);
            }*/
            LOGGER.debug("the DescOldEmp is matched here  size of match4 for match all of the following again "+match4.size());
              }
              else
              {
              	if(match3!=null && match3.size()!=0) {
              		LOGGER.debug("inside issued by if else--");
                match4 = match3;
              }
              	else {
              		match4=null;
              	}
              }    
        
        /****Logic for Match all of the following - Document upload Date*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart().length() != 0)
        {
        	
        	LOGGER.debug("the getDocuploaddatestart to search is -->" + docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart());
        	Date uploadDateFrom = UtilConstants.getDate(docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart());
            Date uploadDateTo = UtilConstants.getDate(docSearchRequestASBO.getAlfrescoInput().getDocuploaddateend());
            if(match4.size()!=0 && match4!=null) {
            	 for (int p = 0; p < match4.size(); p++)
                 {
                 	if(!((ContentData)match4.get(p)).getUploadDate().equalsIgnoreCase(null))
                 	{
                 	Date uploadDate = UtilConstants.getDate(((ContentData)match4.get(p)).getUploadDate());
                     LOGGER.debug("the Actual uploadDate date is --->" + uploadDate);
                     if ((uploadDateFrom.after(uploadDate)) || (uploadDateTo.before(uploadDate)))
                       continue;
               	    ContentData sd = new ContentData();
                     LOGGER.debug("the getDocuploaddatestart is matched here!! for match all of the following");
                     sd = (ContentData)match4.get(p);
                     match5.add(sd);
                 	}
                 }
            }
            else {
            	
            	if ((docSearchRequestASBO.getAlfrescoInput().getDescOldEmp().length() != 0)&& (match4.size() == 0)) {
					LOGGER.debug("No Match found for Keyword search");
					LOGGER.info("No Match found for Keyword search");
					match5 = null;
            	 }else
            	 {
            	
            for (int p = 0; p < all.size(); p++)
            {
            	if(!((ContentData)all.get(p)).getUploadDate().equalsIgnoreCase(null))
            	{
            	Date uploadDate = UtilConstants.getDate(((ContentData)all.get(p)).getUploadDate());
                LOGGER.debug("the Actual uploadDate date is --->" + uploadDate);
                if ((uploadDateFrom.after(uploadDate)) || (uploadDateTo.before(uploadDate)))
                  continue;
          	    ContentData sd = new ContentData();
                LOGGER.debug("the getDocuploaddatestart is matched here!! for match all of the following");
                sd = (ContentData)all.get(p);
                match5.add(sd);
            	}
            }
           /* if(match4.size()!=0 && match4!=null){
          	  match5.retainAll(match4);
            }*/

              }
            }
        }
              else
              {
              	if(match4!=null && match4.size()!=0) {
                match5 = match4;
              }
              	else {
              		match5=null;
              	}
              }     	
         
        if ((match5!=null) && (match5.size()!=0)) {
          LOGGER.debug("the size of match 5 is ---> " + match5.size());
          replacingURL(match5);
          sortByUploadDateAsc(match5);
          this.getContentResponseASBO.setContentDataList(match5);

          LOGGER.debug("inside all ends=========");
        }

      }
      
      /****Logic for Match all of the following ends*******/
      
      /****Logic for Match any of the following starts*******/
      else
      {
        LOGGER.info("match any of the following selected");

        Set<ContentData> anyList = new HashSet<ContentData>();
        List<ContentData> matchAny1 = new ArrayList<ContentData>();
        
        /****Logic for Match any of the following - Document name*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDocName().length() != 0)
        {
          String inputDocName=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDocName());
          LOGGER.debug("match1 are docSearchRequestASBO.getAlfrescoInput().getDocName()" + inputDocName);
          LOGGER.debug("the size of inputDocName is --> " + inputDocName.length());

          for (int i = 0; i < all.size(); i++) {
            String filteredFileName = null;
            if(!((ContentData)all.get(i)).getDocName().isEmpty())
            {
            filteredFileName = removeSpaces(((ContentData)all.get(i)).getDocName());
            if(!inputDocName.isEmpty() && filteredFileName.contains(inputDocName))
            {
            ContentData sd = new ContentData();
            LOGGER.debug("the document name is matched here!! for match any of the following"+filteredFileName);
            sd = (ContentData)all.get(i);
            anyList.add(sd);
            }
          }
          }
        }

        /****Logic for Match any of the following - Department Name*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDeptName().length() != 0)
        {
        	String inputDeptName=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDeptName());
          LOGGER.debug("the department to search is -->" + inputDeptName);
          for (int p = 0; p < all.size(); p++)
          {
        	  LOGGER.debug("Inside For loop of departments ");
        	  if(!((ContentData)all.get(p)).getDeptName().isEmpty())
        	  {
        	  String filteredDept = removeSpaces(((ContentData)all.get(p)).getDeptName()); 
              if(!inputDeptName.isEmpty() && filteredDept.contains(inputDeptName))
              {
              LOGGER.debug("the matched department is -->" + ((ContentData)all.get(p)).getDeptName());
              ContentData sd = new ContentData();
              sd = (ContentData)all.get(p);
              anyList.add(sd);
            }
            }
          }

        }

        /****Logic for Match any of the following - Issued By*******/
        if (docSearchRequestASBO.getAlfrescoInput().getIssuedBy().length() != 0)
        {
        	String inputIssued=removeSpaces(docSearchRequestASBO.getAlfrescoInput().getIssuedBy());
        	  for (int p = 0; p < all.size(); p++)
              {
              	if(!((ContentData)all.get(p)).getIssuedBy().isEmpty())
              	{
            	  String filteredIssuedBy = removeSpaces(((ContentData)all.get(p)).getIssuedBy());
            	  LOGGER.debug("the filteredIssuedBy to search is -->" + filteredIssuedBy);
                  if(!inputIssued.isEmpty() && filteredIssuedBy.contains(inputIssued))
                  {
                	  ContentData sd = new ContentData();
                	  sd = (ContentData)all.get(p);
                	  anyList.add(sd);
            }
          }
        }
        }
        
        /****Logic for Match any of the following - Description*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDescOldEmp().length() != 0)
        {
        	String inputDesc= removeSpaces(docSearchRequestASBO.getAlfrescoInput().getDescOldEmp());
        	LOGGER.debug("the inputDesc to search is -->" + inputDesc);
        	 for (int p = 0; p < all.size(); p++)
             {
             	if(!((ContentData)all.get(p)).getDescOldEmp().isEmpty())
             	{
           	  String filteredDescOldEmp = removeSpaces(((ContentData)all.get(p)).getDescOldEmp());
           	  LOGGER.debug("the filteredDescOldEmp to search is -->" + filteredDescOldEmp);
                 if(!inputDesc.isEmpty() && filteredDescOldEmp.contains(inputDesc))
                 {
                ContentData sd = new ContentData();
                sd = (ContentData)all.get(p);
                anyList.add(sd);
            }
          }
        }
        }
        
        /****Logic for Match any of the following - Upload Date*******/
        if (docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart().length() != 0)
        {
          LOGGER.debug("match uplod start ane end date here");
          Date uploadDateFrom = UtilConstants.getDate(docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart());
          Date uploadDateTo = UtilConstants.getDate(docSearchRequestASBO.getAlfrescoInput().getDocuploaddateend());
          LOGGER.debug("the start date is --->" + uploadDateFrom);
          LOGGER.debug("the END date is --->" + uploadDateTo);

          for (int i = 0; i < all.size(); i++) {
        	  if(!((ContentData)all.get(i)).getUploadDate().equalsIgnoreCase(null))
        	  {
            Date uploadDate = UtilConstants.getDate(((ContentData)all.get(i)).getUploadDate());
            LOGGER.debug("the Actual uploadDate date is --->" + uploadDate);
            if ((uploadDateFrom.after(uploadDate)) || (uploadDateTo.before(uploadDate)))
              continue;
            ContentData sd = new ContentData();
            sd = (ContentData)all.get(i);
            anyList.add(sd);
          }
          }
        }
        LOGGER.info("Size of anyList"+anyList.size());
        matchAny1.addAll(anyList);
        replacingURL(matchAny1);
        sortByUploadDateAsc(matchAny1);
       
        this.getContentResponseASBO.setContentDataList(matchAny1);
      }
      /****Logic for Match any of the following ends*******/
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }

    return this.getContentResponseASBO;
  }

  private void replacingURL(List<ContentData> matchAny1) {
	// TODO Auto-generated method stub
	// TODO Auto-generated method stub
			for(int i=0;i<matchAny1.size();i++){
				String orgurl = matchAny1.get(i).getDocUrl();
			//	String newUrl =orgurl.replaceAll(UtilProperties.getAlfWebURL(), UtilProperties.getAlfEmpURL());
				if(orgurl!=null && orgurl.endsWith(",")){
					orgurl= orgurl.substring(0, orgurl.length()-1);
				}
				matchAny1.get(i).setDocUrl(orgurl);
				
			}
  }
  
  private String removeSpaces(String value) {
	  String newValue=value.replaceAll("\\s+","").toUpperCase();
	  return newValue;
  }

public GetContentResponseASBO getFilteredContentResponse(List<ContentData> contentDatalist, SearchSpecificDetailsRequestASBO getSearchSepecificDetailsASBO)
  {
    ContentData filtercontentdata = new ContentData();
    List<ContentData> filteredContentDataList = new ArrayList<ContentData>();
    GetContentResponseASBO gcrespasbo = new GetContentResponseASBO();
    if (getSearchSepecificDetailsASBO.equals(null))
    {
     
      LOGGER.info("NO match found for the requested resource");
      return gcrespasbo;
    }

    if (contentDatalist.size() == 0) {
      filtercontentdata.setStatusOfDocSearch("No document found within the search parameters");
      LOGGER.info("No document found within the search parameters");
      filteredContentDataList.add(filtercontentdata);
      gcrespasbo.setContentDataList(filteredContentDataList);
      return gcrespasbo;
    }

    LOGGER.info("GetEmployeeDocumentsTransformer else contentDatalist.size()! " + contentDatalist.size());
    for (int i = 0; i < contentDatalist.size(); i++) {
    	LOGGER.debug("match found for the requested resource" + ((ContentData)contentDatalist.get(i)).getDepartmentName());
      filtercontentdata = (ContentData)contentDatalist.get(i);
      filteredContentDataList.add(filtercontentdata);
    }

    sortByUploadDateAsc(filteredContentDataList);
    gcrespasbo.setContentDataList(filteredContentDataList);
    LOGGER.info("GetEmployeeDocumentsTransformer filteredContentDataList.size()! " + filteredContentDataList.size());
    return gcrespasbo;
  }

  private void sortByUploadDateAsc(List<ContentData> filteredContentDataList)
  {
  	Collections.sort(filteredContentDataList, new Comparator<ContentData>() {
  	    @Override
  	    public int compare(ContentData o1, ContentData o2) {
  	    	
  	    	 SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
  	    	 String department1=null;
  	    	 String department2=null;
  	    	 if(o1.getDeptName()==null && o2.getDeptName()==null)
  	    	 {
  	    		department1=o1.getDepartmentName();
  	    		department2=o2.getDepartmentName();
  	    	 }else
  	    	 {
  	    		department1=o1.getDeptName();
  	    		department2=o2.getDeptName();
  	    	 }
  	    	 
  	        int result = department1.compareTo(department2);
  	      Date dateone;
  	      Date datetwo;
		try {
			
			dateone = sdf.parse(o1.getUploadDate());
			datetwo = sdf.parse(o2.getUploadDate());
  	        if (result == 0) {
  	            result = dateone.compareTo(datetwo);
  	        } else {
  	            result = -result;
  	        }
		} catch (ParseException e) {
			 LOGGER.error(e.getStackTrace());
			e.printStackTrace();
		}
          
  	        return result;
  	    }
  	});
	    
	  LOGGER.info("List content are "+filteredContentDataList);
	  
 /*   int maxSize = filteredContentDataList.size() - 1;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
      for (int i = 0; i < maxSize; i++) {
        int index = i;
        LOGGER.info("entered : " + ((ContentData)filteredContentDataList.get(index)).getUploadDate());
        for (int j = i + 1; j < filteredContentDataList.size(); j++) {
        	LOGGER.info("comparing to : " + ((ContentData)filteredContentDataList.get(j)).getUploadDate());
          Date dateone = sdf.parse(((ContentData)filteredContentDataList.get(index)).getUploadDate());
          Date datetwo = sdf.parse(((ContentData)filteredContentDataList.get(j)).getUploadDate());
          if (dateone.compareTo(datetwo) > 0) {
            index = j;
          }
        }
        Collections.swap(filteredContentDataList, index, i);
      }
    }
    catch (Exception e) {
      LOGGER.error(e.getStackTrace());
      e.printStackTrace();
    }*/
  }

  public GetHRMSDetailsDBRequestASBO transformHRMSRequest(GetHRMSDetailsRequestASBO gethrmsreqasbo)
  {
    this.gethrmsdbreqasbo.setHeader(gethrmsreqasbo.getHeader());
    this.gethrmsdbreqasbo.setUserID(gethrmsreqasbo.getUserID());
    this.gethrmsdbreqasbo.setRoleOID(gethrmsreqasbo.getRoleOID());

    return this.gethrmsdbreqasbo;
  }

  public GetAlfrecoContentRequestASBO transformAlfrescoDocSearchRequest(DocSearchRequestASBO docSearchRequestASBO2)
  {
    return null;
  }

  public GetContentResponseASBO getHRMSFilteredContentResponse(List<ContentData> hrmsFaluredatalist, SearchSpecificDetailsRequestASBO getSearchSepecificDetailsASBO)
  {
    GetContentResponseASBO gcrespasbo = new GetContentResponseASBO();
    ContentData filtercontentdata = new ContentData();
    List<ContentData> filteredContentDataList = new ArrayList<ContentData>();
    if (hrmsFaluredatalist.size() == 0) {
      filtercontentdata.setStatusOfDocSearch("You are not allowed to view documents for this department");
      filteredContentDataList.add(filtercontentdata);
      gcrespasbo.setContentDataList(filteredContentDataList);
      return gcrespasbo;
    }

    return null;
  }
  
  /**
	 * Validates user inputs/Server side validation.
	 *
	 * @param docSearchRequestASBO user request param
	 * @return the errorVO error status
	 */
  public ErrorVO validateUserRequest(
			DocSearchRequestASBO docSearchRequestASBO) {
		ErrorVO errorVO = new ErrorVO();

		if(!(docSearchRequestASBO.getAlfrescoInput().getMatchMethod().equalsIgnoreCase("ALL")||docSearchRequestASBO.getAlfrescoInput().getMatchMethod().trim().equalsIgnoreCase("OTHERS")))
		{
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage(MessageConstants.MATCH_METHOD_INVALID);
			return errorVO;
		}
		
		errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getDeptName(),"Department Name",true);
		if(errorVO.getErrorCode()!=0)
		{
			return errorVO;
		}
		
		errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getDocName(),"File Name",false);
		if(errorVO.getErrorCode()!=0)
		{
			return errorVO;
		}
		
		errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getIssuedBy(),"Issued By",false);
		if(errorVO.getErrorCode()!=0)
		{
			return errorVO;
		}
		if(docSearchRequestASBO.getAlfrescoInput().getTypeOfContent().equals("EmployeeDocument")){
			
			errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getDocType(),"Document type",false);
		
		if(errorVO.getErrorCode()!=0)
		{
			return errorVO;
		}
		
		errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getSearchbytype(),"Search by",true);
		
		if(errorVO.getErrorCode()!=0)
		{
			return errorVO;
		}
		
		if (docSearchRequestASBO.getAlfrescoInput().getSearchbytype()
				.equalsIgnoreCase("UDR")) {
			if (ValidationUtil.isNotNull(docSearchRequestASBO.getAlfrescoInput()
					.getDocuploaddatestart()) && ValidationUtil.isNotNull(docSearchRequestASBO
					.getAlfrescoInput().getDocuploaddateend())) {
				/*try {
					int days = ValidationUtil.compareDates(docSearchRequestASBO
							.getAlfrescoInput().getDocuploaddatestart(),
							docSearchRequestASBO.getAlfrescoInput()
									.getDocuploaddateend());
					if (days < 0) {
						errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
						errorVO.setErrorMessage(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
						LOGGER.error(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
						return errorVO;
					}

					if (days > 365) {
						errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
						errorVO.setErrorMessage(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
						LOGGER.error(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
						return errorVO;
					}
				} catch (ParseException e) {
					errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
					errorVO.setErrorMessage(MessageConstants.DATE_PARSE_ERROR);
					LOGGER.error(MessageConstants.DATE_PARSE_ERROR);
					return errorVO;
				}*/

			} else {
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage(MessageConstants.DATE_REQUIRED_SELECTION);
				LOGGER.error(MessageConstants.DATE_REQUIRED_SELECTION);
				return errorVO;
			}

		} else if (docSearchRequestASBO.getAlfrescoInput().getSearchbytype()
				.equalsIgnoreCase("PDR")) {
			if (ValidationUtil.isNotNull(docSearchRequestASBO.getAlfrescoInput()
					.getDocpublishdatestart()) && ValidationUtil.isNotNull(docSearchRequestASBO
					.getAlfrescoInput().getDocpublishdateend())) {
				/*try {
					int days = ValidationUtil.compareDates(docSearchRequestASBO
							.getAlfrescoInput().getDocpublishdatestart(),
							docSearchRequestASBO.getAlfrescoInput()
									.getDocpublishdateend());
					if (days < 0) {
						errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
						errorVO.setErrorMessage(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
						LOGGER.error(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
						return errorVO;
					}

					if (days > 365) {
						errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
						errorVO.setErrorMessage(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
						LOGGER.error(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
						return errorVO;
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
					errorVO.setErrorMessage(MessageConstants.DATE_PARSE_ERROR);
					LOGGER.error(MessageConstants.DATE_PARSE_ERROR);
					return errorVO;
				}*/

			} else {
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage(MessageConstants.DATE_REQUIRED_SELECTION);
				LOGGER.error(MessageConstants.DATE_REQUIRED_SELECTION);
				return errorVO;
			}
		}
		}
		
		if(docSearchRequestASBO.getAlfrescoInput().getTypeOfContent().equals("OldEmployeeDocument")){
			
			errorVO=ValidationUtil.validateFields(docSearchRequestASBO.getAlfrescoInput().getDescOldEmp(),"Keyword",false);
			if(errorVO.getErrorCode()!=0)
			{
				return errorVO;
			}
			
		if (ValidationUtil.isNotNull(docSearchRequestASBO.getAlfrescoInput()
				.getDocuploaddatestart()) && ValidationUtil.isNotNull(docSearchRequestASBO
				.getAlfrescoInput().getDocuploaddateend())) {
			/*try {
				int days = ValidationUtil.compareDates(docSearchRequestASBO
						.getAlfrescoInput().getDocuploaddatestart(),
						docSearchRequestASBO.getAlfrescoInput()
								.getDocuploaddateend());
				if (days < 0) {
					errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
					errorVO.setErrorMessage(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
					LOGGER.error(MessageConstants.MIN_DATE_RANGE_COMPARE_ERROR);
					return errorVO;
				}

				if (days > 365) {
					errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
					errorVO.setErrorMessage(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
					LOGGER.error(MessageConstants.MAX_DATE_RANGE_COMPARE_RANGE);
					return errorVO;
				}
			} catch (ParseException e) {
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage(MessageConstants.DATE_PARSE_ERROR);
				LOGGER.error(MessageConstants.DATE_PARSE_ERROR);
				return errorVO;
			}*/

		}
		}
		
		return errorVO;
	}
  
}