/**
 * 
 */
package com.tcs.docstore.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.tcs.docstore.alfresco.asbo.request.SearchSpecificDetailsRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;
import com.tcs.docstore.transformation.alfresco.transformers.GetEmployeeDocumentsTransformer;

/**
 * @author 585226
 *
 */
public class AlfrescoMatchAllHelperController {
	
	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");
	
	public GetContentResponseASBO searchDocumentMatchAll(SearchSpecificDetailsRequestASBO getSearchSepecificDetailsASBO,GetContentResponseASBO getContentResponseASBO,GetHRMSDetailsDBResponseASBO	hrmsdbresponseasbo,String authCM){
		
		// MATCH FOR SELECT aLL OF THE FOLLOWING  CODE
		
		// The following objects and variable are needed
		GetEmployeeDocumentsTransformer getEmployeeDocumentsTransformer = new GetEmployeeDocumentsTransformer();
		GetContentResponseASBO getContentResponseFilteredASBO = new GetContentResponseASBO();
		List<ContentData> filteredContentDataList = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListOne = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListTwo = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListThree = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListFwdone = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListFwdTwo = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListAllDept = new ArrayList<ContentData>();
		


		List<ContentData> contentListToBeforwarded = new ArrayList<ContentData>();


		// confidential matching code starts only Chief Manager or HOITAdmin Can view Confidential Files
		// confidential files are filtered in the below code
		
		if(!getContentResponseASBO.getContentDataList().isEmpty()){
			LOGGER.info("ABCDEF  getContentResponseASBO.getContentDataList().size() the original code for searchDocument the list is --->"+getContentResponseASBO.getContentDataList().size());
			if(authCM.equalsIgnoreCase("validCM")){
				LOGGER.debug("the user is a chief manager");
				for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
					ContentData contentDataListtwo = new ContentData();
					contentDataListtwo =getContentResponseASBO.getContentDataList().get(i);
					filteredContentDataListFwdTwo.add(contentDataListtwo);

				}			
			}
			else{
				
				LOGGER.info("the user is NOT a chief manager NOT  a HOIT Admin");
				for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
					if(getContentResponseASBO.getContentDataList().get(i).getConfidential().equalsIgnoreCase("true")){
						LOGGER.debug("confidentail doc view not allowed for this user-->"+hrmsdbresponseasbo.getUserID());

					}
					else{
						ContentData contentDataListtwo = new ContentData();
						contentDataListtwo =getContentResponseASBO.getContentDataList().get(i);
						filteredContentDataListFwdTwo.add(contentDataListtwo);


					}
				}


			}
		}
		// confidential matching code ends

		// content list for department not of that of the user visible to all marked
		//departmental filtering done here this is the FUNDAMENTAL ASPECT of document search DO NOT Even THINK of CHANGING THIS !!!
		if(!filteredContentDataListFwdTwo.isEmpty()){
			LOGGER.info("ABCDEF  for visible to code *******Major******** step 1");
			List<ContentData> filteredContentDataListAllDeptTest = new ArrayList<ContentData>();
			ContentData contentDataListAllDept = new ContentData();
			ContentData contentDataListAllDeptone = new ContentData();
			ContentData contentDataListAllDepttwo = new ContentData();
			if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("ALL")){
				LOGGER.debug("ABCDEF  for visible to code *******Major******** search ALL step 2");
				for(int i=0 ; i<filteredContentDataListFwdTwo.size();i++){
					if(filteredContentDataListFwdTwo.get(i).getVisibleTo().equalsIgnoreCase("All") || filteredContentDataListFwdTwo.get(i).getDepartmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
						contentDataListAllDept=filteredContentDataListFwdTwo.get(i);
						filteredContentDataListAllDept.add(contentDataListAllDept);

					}
					
				}
				LOGGER.info(filteredContentDataListAllDept.size());
				
}
			else if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("RECENTDOCUMENTS")){
			
				for(int j=0;j<filteredContentDataListFwdTwo.size();j++){
					// user departmenet search visible to aLl added 07 oct 2016
					if(filteredContentDataListFwdTwo.get(j).getDepartmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName()) || filteredContentDataListFwdTwo.get(j).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName()) || filteredContentDataListFwdTwo.get(j).getVisibleTo().equalsIgnoreCase("All")){
						contentDataListAllDepttwo=filteredContentDataListFwdTwo.get(j);
						filteredContentDataListAllDept.add(contentDataListAllDepttwo);
					}
				}
				
				LOGGER.info("in recent doc------------"+filteredContentDataListAllDept.size());
			}
			else{
				LOGGER.info("ABCDEF  for visible to code *******Major******** search sOMe department step 2");
				// this code is for if the user not from his department search for doc from other department then marked visible to all will be shown to him
				if(!getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
					
					for(int i=0;i<filteredContentDataListFwdTwo.size();i++){
						if(filteredContentDataListFwdTwo.get(i).getDepartmentName().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName())){
							contentDataListAllDeptone=filteredContentDataListFwdTwo.get(i);
							filteredContentDataListAllDeptTest.add(contentDataListAllDeptone);
						}
						
				}


					// this code is for visible to sorting after getting all the documents of the selected department checking for visible to attribute
					for(int j=0;j<filteredContentDataListAllDeptTest.size();j++){
						if(filteredContentDataListAllDeptTest.get(j).getVisibleTo().equalsIgnoreCase("All") || filteredContentDataListAllDeptTest.get(j).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
							contentDataListAllDepttwo=filteredContentDataListAllDeptTest.get(j);
							filteredContentDataListAllDept.add(contentDataListAllDepttwo);

						}
					}
						

					}
				else{
					// user departmenet search
					for(int j=0;j<filteredContentDataListFwdTwo.size();j++){
						// user departmenet search visible to aLl added 07 oct 2016
						if(filteredContentDataListFwdTwo.get(j).getDepartmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName()) || filteredContentDataListFwdTwo.get(j).getVisibleTo().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName())){
							contentDataListAllDepttwo=filteredContentDataListFwdTwo.get(j);
							filteredContentDataListAllDept.add(contentDataListAllDepttwo);
						}
					
				}

			}
		}
		}
		LOGGER.info("ABCDEF  for visible to code *******Major********  step 3--->size//>" +filteredContentDataListAllDept.size());
		// content list for department not that of the user
		
		// Code For HO IT ADMIN  This person can view all files confidential as well as non confidential accross all departments STARTS
		
		if(hrmsdbresponseasbo.getRoleOID().equalsIgnoreCase("HOITAdmin")){
			

			for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
				// Fundamental part for department seach applied to HO IT ADmin as well 
				if(getContentResponseASBO.getContentDataList().get(i).getDepartmentName().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName())){
				ContentData contentDataListHOItAdmin = new ContentData();
				contentDataListHOItAdmin =getContentResponseASBO.getContentDataList().get(i);
				filteredContentDataList.add(contentDataListHOItAdmin);
				}
				if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("All")){
					if(getContentResponseASBO.getContentDataList().get(i).getVisibleTo().equalsIgnoreCase("All")){
					ContentData contentDataListHOItAdmin = new ContentData();
					contentDataListHOItAdmin =getContentResponseASBO.getContentDataList().get(i);
					filteredContentDataList.add(contentDataListHOItAdmin);
					}
					
				}
	
				if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("RECENTDOCUMENTS"))
				{
					ContentData contentDataListHOItAdmin = new ContentData();
					contentDataListHOItAdmin =getContentResponseASBO.getContentDataList().get(i);
					filteredContentDataList.add(contentDataListHOItAdmin);
				}

			}		
			
		}
		
		else{
		// Code for creating a final transmitting ahead	
		if(!filteredContentDataListAllDept.isEmpty()){
			for(int i=0;i<filteredContentDataListAllDept.size();i++){
				ContentData filterDepartmentNamecontentdata = new ContentData();
				filterDepartmentNamecontentdata=filteredContentDataListAllDept.get(i);
				filteredContentDataList.add(filterDepartmentNamecontentdata);
			}
		}
		}
		LOGGER.info("AlfrescoIntegrationController filteredContentDataListOne size is BEFORE ---> "+filteredContentDataListOne.size());

		LOGGER.info("AlfrescoIntegrationController filteredContentDataList size is ---> "+filteredContentDataList.size());

		if (!getSearchSepecificDetailsASBO.getFileName().isEmpty()){// searches the document depending on the file name		Priority one File Name			
			LOGGER.debug(" AlfrescoIntegrationController inside the file name search"+getSearchSepecificDetailsASBO.getFileName().toString());

			for(int i=0;i<filteredContentDataList.size();i++){
				String filteredFileName =filteredContentDataList.get(i).getDocName();
				int index = filteredFileName.toUpperCase().indexOf(getSearchSepecificDetailsASBO.getFileName().toUpperCase());
				ContentData filterDepartmentNamecontentdata = new ContentData();
				if (index != -1){
					LOGGER.debug("AlfrescoIntegrationController file namee match found for the requested resource");
					filterDepartmentNamecontentdata=filteredContentDataList.get(i); // filtering the list within itself
					LOGGER.debug("AlfrescoIntegrationController file namee match found for the getDocName"+filteredContentDataList.get(i).getDocName());
					filteredContentDataListOne.add(filterDepartmentNamecontentdata);
					LOGGER.debug("size in---------------------------------------------------------------------------------e"+filteredContentDataList.size());

				}
				else{
					LOGGER.info("AlfrescoIntegrationController the name does not match");
					//		filteredContentDataList.remove(i);
					//		getContentResponseASBO.getContentDataList().remove(i);
				}
			}


		}
		LOGGER.info("AlfrescoIntegrationController filteredContentDataListOne size is AFTER ---> "+filteredContentDataListOne.size());

		if (!getSearchSepecificDetailsASBO.getFileName().isEmpty()){
			if(filteredContentDataListOne.isEmpty()){
				contentListToBeforwarded=filteredContentDataListOne;
			}
		}
		else{
			filteredContentDataListOne=filteredContentDataList;
		}
		LOGGER.info("AlfrescoIntegrationController filteredContentDataListOne step  1--> "+filteredContentDataListOne.size());


		// document type search for example type are approval / CR /Circular /etc /etc

		if(!getSearchSepecificDetailsASBO.getDocumentType().isEmpty()){  //SEARCH BY THE document type 
			LOGGER.info("inside the search by getDocumentType by");

			for(int i=0;i<filteredContentDataListOne.size();i++){
				ContentData filterDepartmentNamecontentdata = new ContentData();
				if(getSearchSepecificDetailsASBO.getDocumentType().equalsIgnoreCase(filteredContentDataListOne.get(i).getDocumentType())){
					LOGGER.debug("AlfrescoIntegrationControllermatch found for the issued by category getDocumentType by");
					filterDepartmentNamecontentdata=filteredContentDataListOne.get(i); // filtering the list within itself
					//	filteredContentDataList.add(filterDepartmentNamecontentdata);
					filteredContentDataListTwo.add(filterDepartmentNamecontentdata);
				}
			}

		}


		if(!getSearchSepecificDetailsASBO.getDocumentType().isEmpty()){
			if (filteredContentDataListTwo.isEmpty()){
				contentListToBeforwarded=filteredContentDataListTwo;
			}
		}
		else{
			filteredContentDataListTwo=filteredContentDataListOne;
		}

		LOGGER.info("AlfrescoIntegrationController filteredContentDataListTwo step2 SIze is --> "+filteredContentDataListTwo.size());


		if (!getSearchSepecificDetailsASBO.getIssuedBy().isEmpty()){ // sorts the document issued by  who  for example NIA HO/IRDA 
			LOGGER.info("AlfrescoIntegrationController inside the search by issued by filteredContentDataList.size()"+filteredContentDataList.size());
			for(int i=0;i<filteredContentDataListTwo.size();i++){
				ContentData filterIssuedBycontentdata = new ContentData();
				if(getSearchSepecificDetailsASBO.getIssuedBy().equalsIgnoreCase(filteredContentDataListTwo.get(i).getIssuedBy())){
					LOGGER.debug("AlfrescoIntegrationController match found for the issued by category issued by-->"+filteredContentDataListTwo.get(i).getIssuedBy());
					filterIssuedBycontentdata=filteredContentDataListTwo.get(i); // filtering the list within itself
					//	filteredContentDataList.add(filterIssuedBycontentdata);
					filteredContentDataListThree.add(filterIssuedBycontentdata);
				}
				else{
					//		filteredContentDataListTwo.remove(i);
					LOGGER.info("AlfrescoIntegrationController the unwanted item is now removed getIssuedBy-->"+filteredContentDataListTwo.get(i).getIssuedBy());

				}
			}
		}

		if(!getSearchSepecificDetailsASBO.getIssuedBy().isEmpty()){
			if (filteredContentDataListThree.isEmpty()){
				contentListToBeforwarded=filteredContentDataListThree;
			}
		}
		else{
			filteredContentDataListThree=filteredContentDataListTwo;
		}

		LOGGER.info("AlfrescoIntegrationController filteredContentDataListThree step  3--> "+filteredContentDataListThree.size());
		LOGGER.info("AlfrescoIntegrationController getSearchSepecificDetailsASBO.getSearchBy() --> "+getSearchSepecificDetailsASBO.getSearchBy());
		// Date search begins

		if (getSearchSepecificDetailsASBO.getSearchBy().equalsIgnoreCase("PDR")){  //SEARCH BY THE PUBLISH DATE RANGE
			LOGGER.info("AlfrescoIntegrationController DATE AlfrescoIntegrationController inside the getDocPublishDateStart search filteredContentDataListThree"+filteredContentDataListThree.size());
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for(int i=0;i<filteredContentDataListThree.size();i++){
				ContentData filterpublishdatecontentdata = new ContentData();
				try {

					String datepublishActual =filteredContentDataListThree.get(i).getPublishDate();
					String searchPublishStartDate =getSearchSepecificDetailsASBO.getDocPublishDateStart();
					String searchPublishEndDate =getSearchSepecificDetailsASBO.getDocPublishDateEnd();
					Date SearchpublishdateStart = sdf.parse(searchPublishStartDate);
					Date SearchpublishdateEnd = sdf.parse(searchPublishEndDate);
					Date datepublishOriginal= sdf.parse(datepublishActual);
					LOGGER.debug("SearchpublishdateStart PDR"+SearchpublishdateStart);
					LOGGER.debug("AlfrescoIntegrationController DATE SearchpublishdateEnd PDR "+SearchpublishdateEnd);
					LOGGER.debug("AlfrescoIntegrationController DATE datepublishOriginal PDR"+datepublishOriginal);
					if(SearchpublishdateStart.before(datepublishOriginal) && SearchpublishdateEnd.after(datepublishOriginal) || SearchpublishdateStart.equals(datepublishOriginal) || SearchpublishdateEnd.equals(datepublishOriginal)){
						LOGGER.debug(" AlfrescoIntegrationController DATE AlfrescoIntegrationControllerthe date is within the requested parameters for SEARCH ALL");
						LOGGER.debug("AlfrescoIntegrationController DATE  the date is within the requested parameters SEARCH ALL");
						filterpublishdatecontentdata=filteredContentDataListThree.get(i);
						//		filteredContentDataList.add(filterpublishdatecontentdata);
						contentListToBeforwarded.add(filterpublishdatecontentdata);
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					LOGGER.error(e.getStackTrace());
					e.printStackTrace();

				}
			}

		}


		if (getSearchSepecificDetailsASBO.getSearchBy().equalsIgnoreCase("UDR")){  //SEARCH BY THE UPLOAD DATE RANGE

			if (getSearchSepecificDetailsASBO.getDocUploadDateEnd()!=null && getSearchSepecificDetailsASBO.getDocUploadDateStart()!=null){
				LOGGER.info("AlfrescoIntegrationController DATE inside the getDocUploadDateEnd search search all");
				//	SimpleDateFormat sdfuploadall = new SimpleDateFormat("yyyy-MM-dd"); mm/dd/yyyy
				SimpleDateFormat sdfuploadall = new SimpleDateFormat("dd/MM/yyyy");

				for(int i=0;i<filteredContentDataListThree.size();i++){
					ContentData filterIssuedDatecontentdata = new ContentData();
					String dateUploadActualSearchAll =filteredContentDataListThree.get(i).getUploadDate();
					String searchUploadStartDateSearchAll =getSearchSepecificDetailsASBO.getDocUploadDateStart();
					String searchUploadEndDateSearchAll =getSearchSepecificDetailsASBO.getDocUploadDateEnd();
					try {
						Date SearchAllUploaddateStart = sdfuploadall.parse(searchUploadStartDateSearchAll);
						Date SearchAllUploaddateEnd = sdfuploadall.parse(searchUploadEndDateSearchAll);
						Date dateUploadOriginalAll= sdfuploadall.parse(dateUploadActualSearchAll);
						LOGGER.debug("AlfrescoIntegrationController SearchAllUploaddateStart UDR"+SearchAllUploaddateStart);
						LOGGER.debug("AlfrescoIntegrationController SearchAllUploaddateEnd UDR"+SearchAllUploaddateEnd);
						LOGGER.debug("AlfrescoIntegrationController dateUploadOriginalAll UDR"+dateUploadOriginalAll);
						if(SearchAllUploaddateStart.before(dateUploadOriginalAll) && SearchAllUploaddateEnd.after(dateUploadOriginalAll) || SearchAllUploaddateStart.equals(dateUploadOriginalAll) || SearchAllUploaddateEnd.equals(dateUploadOriginalAll)){
							LOGGER.debug("AlfrescoIntegrationController DATE match found for the upload parameters search all");
							filterIssuedDatecontentdata=filteredContentDataListThree.get(i);
							//	filteredContentDataList.add(filterIssuedDatecontentdata);
							contentListToBeforwarded.add(filterIssuedDatecontentdata);
						}
						} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LOGGER.error(e.getStackTrace());
					}


				}

			}

		}

		LOGGER.info("in between checking the size of the contentListToBeforwarded  "+contentListToBeforwarded.size());
	//	replacingURL(contentListToBeforwarded);
		getContentResponseFilteredASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getFilteredContentResponse(contentListToBeforwarded,getSearchSepecificDetailsASBO);
		return getContentResponseFilteredASBO;

	
	}

/*	private void replacingURL(List<ContentData> contentListToBeforwarded) {
		// TODO Auto-generated method stub
		for(int i=0;i<contentListToBeforwarded.size();i++){
			String orgurl = contentListToBeforwarded.get(i).getDocUrl();
			String newUrl =orgurl.replace(UtilProperties.getAlfWebURL(), UtilProperties.getAlfEmpURL());
			if(newUrl!=null && newUrl.endsWith(",")){
				newUrl= newUrl.substring(0, newUrl.length()-1);
			}
			contentListToBeforwarded.get(i).setDocUrl(newUrl);
		}
	}*/

	}
