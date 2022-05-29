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
public class AlfrescoMatchAnyHelperController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	public GetContentResponseASBO searchDocumentMatchAnyDetails(SearchSpecificDetailsRequestASBO getSearchSepecificDetailsASBO,GetContentResponseASBO getContentResponseASBO,GetHRMSDetailsDBResponseASBO	hrmsdbresponseasbo,String authCM){

		// MATCH FOR SELECT ANY OF THE FOLLOWING CODE
		LOGGER.info("**************\\\\\\\\\\inside match any of the following////***********");

		GetEmployeeDocumentsTransformer getEmployeeDocumentsTransformer = new GetEmployeeDocumentsTransformer();
		GetContentResponseASBO getContentResponseFilteredASBO = new GetContentResponseASBO();
		List<ContentData> filteredContentDataListMatchAny = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListMatch = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListAllDept = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListLatest = new ArrayList<ContentData>();
		List<ContentData> filteredContentDataListNew = new ArrayList<ContentData>();
		List<ContentData> freshContentDataList = new ArrayList<ContentData>();
		List<ContentData> finalizingList = new ArrayList<ContentData>();
		List<ContentData> freshContentDataListanyone = new ArrayList<ContentData>();  // for filtering the unwanted departments
		List<ContentData> freshContentDataListanytwo = new ArrayList<ContentData>();  // for the confidential documents

		// checking if the document is confidential or not
		if(!getContentResponseASBO.getContentDataList().isEmpty()){
			if(authCM.equalsIgnoreCase("validCM")){
				LOGGER.info("The user is a chief manager");
				for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
					ContentData contentDataListanytwo = new ContentData();
					contentDataListanytwo =getContentResponseASBO.getContentDataList().get(i);
					freshContentDataListanytwo.add(contentDataListanytwo);

				}			
			}
			else{
				for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
					if(getContentResponseASBO.getContentDataList().get(i).getConfidential().equalsIgnoreCase("true")){
						LOGGER.debug("This is a confidential document and user is not authorised so removing the doc");
					}
					else{
						LOGGER.debug("This is NOT a confidential document and user is  authorised so adding the doc");
						ContentData contentDataListanytwo = new ContentData();
						contentDataListanytwo =getContentResponseASBO.getContentDataList().get(i);
						freshContentDataListanytwo.add(contentDataListanytwo);
					}
				}

			}
		}

		// content list for department not of that of the user visible to all marked
		//departmental filtering done here this is the FUNDAMENTAL ASPECT of document search DO NOT Even THINK of CHANGING THIS !!!
		if(!freshContentDataListanytwo.isEmpty()){
			List<ContentData> filteredContentDataListAllDeptTest = new ArrayList<ContentData>();


			if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("ALL")){
				for(int i=0 ; i<freshContentDataListanytwo.size();i++){
					if(freshContentDataListanytwo.get(i).getVisibleTo().equalsIgnoreCase("All") || freshContentDataListanytwo.get(i).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName()) || freshContentDataListanytwo.get(i).getDepartmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
					/*if(freshContentDataListanytwo.get(i).getVisibleTo().equalsIgnoreCase("All")){*/
	
						ContentData contentDataListAllDept = new ContentData();
						contentDataListAllDept=freshContentDataListanytwo.get(i);
						filteredContentDataListAllDept.add(contentDataListAllDept);
					}
					}
			}
			else{
				// this code is for if the user not from his department search for doc from other department then marked visible to all will be shown to him
				if(!getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
					LOGGER.info("MATCH ANY  for visible to MATCH ANY code *******Major********  step 3--->size//>" +filteredContentDataListAllDept.size());

					for(int i=0;i<freshContentDataListanytwo.size();i++){
						if(freshContentDataListanytwo.get(i).getDepartmentName().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName()) || freshContentDataListanytwo.get(i).getVisibleTo().equalsIgnoreCase("All") || freshContentDataListanytwo.get(i).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
							ContentData contentDataListAllDeptone = new ContentData();
							contentDataListAllDeptone=freshContentDataListanytwo.get(i);
							filteredContentDataListAllDeptTest.add(contentDataListAllDeptone);
						}
						
					}
					LOGGER.info("MATCH ANY documents not from users department the size of list is //>" +filteredContentDataListAllDeptTest.size());
					// this code is for visible to sorting after getting all the documents of the selected department checking for visible to attribute
					for(int j=0;j<filteredContentDataListAllDeptTest.size();j++){
						if(filteredContentDataListAllDeptTest.get(j).getVisibleTo().equalsIgnoreCase("All") || filteredContentDataListAllDeptTest.get(j).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
							ContentData contentDataListAllDepttwo = new ContentData();
							contentDataListAllDepttwo=filteredContentDataListAllDeptTest.get(j);
							filteredContentDataListAllDept.add(contentDataListAllDepttwo);
						}

					}
					LOGGER.info("MATCH ANY documents not from users department the size of list is BUT ALLOWED VISIBLE  //>" +filteredContentDataListAllDept.size());
				}
				// this code is for if the user FROM his department search for doc from other department then marked visible to all will be shown to him
				else{
					// user departmenet search
					for(int j=0;j<freshContentDataListanytwo.size();j++){
						//search as per the visible to also added
						if(freshContentDataListanytwo.get(j).getDepartmentName().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())||freshContentDataListanytwo.get(j).getVisibleTo().equalsIgnoreCase("All")||freshContentDataListanytwo.get(j).getVisibleTo().equalsIgnoreCase(hrmsdbresponseasbo.getDepartmentName())){
							ContentData contentDataListAllDepttwo = new ContentData();
							contentDataListAllDepttwo=freshContentDataListanytwo.get(j);
							filteredContentDataListAllDept.add(contentDataListAllDepttwo);
						}
					}
					LOGGER.info("MATCH ANY docuemnts FROM users department the size of list is BUT ALLOWED VISIBLE  //>" +filteredContentDataListAllDept.size());
				}
			}
		}

		// content list for department not that of the user

		LOGGER.info("MATCH ANY  for visible to MATCH ANY code *******Major********  step 3--->size//>" +filteredContentDataListAllDept.size());
		// adding the content data list to the list to be manipulated and sent forward


		if(hrmsdbresponseasbo.getRoleOID().equalsIgnoreCase("HOITAdmin")){
			LOGGER.info("This is a HO ITAdmin users");
			for(int i=0;i<getContentResponseASBO.getContentDataList().size();i++){
				// Fundamental part for department seach applied to HO IT ADmin as well 
//commented by Kalpana for any search issue				
				/*if(getContentResponseASBO.getContentDataList().get(i).getDepartmentName().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName()) ||
						getContentResponseASBO.getContentDataList().get(i).getVisibleTo().equalsIgnoreCase("All")){*/
							if(getContentResponseASBO.getContentDataList().get(i).getDepartmentName().equalsIgnoreCase(getSearchSepecificDetailsASBO.getDepatmentName())){
					ContentData contentDataListHOItAdmin = new ContentData();
					contentDataListHOItAdmin =getContentResponseASBO.getContentDataList().get(i);
					filteredContentDataListMatchAny.add(contentDataListHOItAdmin);
					filteredContentDataListNew.add(contentDataListHOItAdmin);
				}
				if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase("All")){
					if(getContentResponseASBO.getContentDataList().get(i).getVisibleTo().equalsIgnoreCase("All")){
						ContentData contentDataListHOItAdmin = new ContentData();
						contentDataListHOItAdmin =getContentResponseASBO.getContentDataList().get(i);
						filteredContentDataListMatchAny.add(contentDataListHOItAdmin);
						filteredContentDataListNew.add(contentDataListHOItAdmin);
					}
				}
				
			}		

		}
		else{
			LOGGER.info("This is a normal user flow so go as normal flow do not show un necessary details");
			if(!filteredContentDataListAllDept.isEmpty()){
				for(int i=0;i<filteredContentDataListAllDept.size();i++){
					LOGGER.debug("AlfrescoIntegrationController---Filtering 3");
					ContentData filterDepartmentNamecontentdata = new ContentData();
					filterDepartmentNamecontentdata=filteredContentDataListAllDept.get(i);
					filteredContentDataListMatchAny.add(filterDepartmentNamecontentdata);
					filteredContentDataListNew.add(filterDepartmentNamecontentdata);
				}

			}
			LOGGER.info("MatchAny Size of list is after HOITAdmin filtering"+filteredContentDataListMatchAny.size());
		}



		if (!getSearchSepecificDetailsASBO.getFileName().isEmpty()){// searches the document depending on the file name					
			LOGGER.debug("inside the file name search"+getSearchSepecificDetailsASBO.getFileName().toString());
			LOGGER.debug("the size of the filtered data list inside the file name search "+filteredContentDataListMatchAny.size());
			filteredContentDataListMatch = filteredContentDataListMatchAny;
			for(int i=0;i<filteredContentDataListMatch.size();i++){

				String filteredContentDataListMatchAnyFileName = filteredContentDataListMatchAny.get(i).getDocName();
				int index1 = filteredContentDataListMatchAnyFileName.toUpperCase().indexOf(getSearchSepecificDetailsASBO.getFileName().toUpperCase());
				if (index1 != -1){
					LOGGER.debug("file name match found for the requested resource any of the following ");
					ContentData fileNamecontentdata = new ContentData();
					if(filteredContentDataListMatchAny.get(i)!= null){
						fileNamecontentdata=filteredContentDataListMatchAny.get(i);
						//	filteredContentDataListNew.add(fileNamecontentdata);
						freshContentDataList.add(fileNamecontentdata);
						filteredContentDataListMatchAny.remove(i); // remove the item after the match is found or it will give a duplicate entry
					}
				}
			}

		}



		if(!getSearchSepecificDetailsASBO.getDocumentType().isEmpty()){  //SEARCH BY THE document type 
			LOGGER.info("inside the search by getDocumentType by any of the following");
			filteredContentDataListMatch = filteredContentDataListMatchAny;
			for(int i=0;i<filteredContentDataListMatch.size();i++){
				if(getSearchSepecificDetailsASBO.getDocumentType().equalsIgnoreCase(filteredContentDataListMatch.get(i).getDocumentType())){
					ContentData fileDocumentType = new ContentData();
					fileDocumentType=filteredContentDataListMatch.get(i);
					//		filteredContentDataListNew.add(fileDocumentType);
					freshContentDataList.add(fileDocumentType);
					filteredContentDataListMatchAny.remove(i); // remove the item after the match is found or it will give a duplicate entry
				}
			}

		}

		if (!getSearchSepecificDetailsASBO.getIssuedBy().isEmpty()){ // sorts the document issued by the who NIA HO wagiare wagaire
			filteredContentDataListMatch = filteredContentDataListMatchAny;
			LOGGER.info("inside the search by issued by any of the following");
			for(int i=0;i<filteredContentDataListMatch.size();i++){
				if(getSearchSepecificDetailsASBO.getIssuedBy().equalsIgnoreCase(filteredContentDataListMatch.get(i).getIssuedBy())){
					//if(getSearchSepecificDetailsASBO.getDepatmentName().equalsIgnoreCase(filteredContentDataListMatch.get(i).getDepatmentName()))
					{
						ContentData fileIssuedByType = new ContentData();
						LOGGER.debug("match found for the issued by category issued by any of the following-->"+filteredContentDataListMatch.get(i).getIssuedBy());
						fileIssuedByType=filteredContentDataListMatch.get(i);
						//	filteredContentDataListNew.add(fileIssuedByType);
						freshContentDataList.add(fileIssuedByType);
						filteredContentDataListMatchAny.remove(i); // remove the item after the match is found or it will give a duplicate entry
					}
				}
			}
		}


		if (getSearchSepecificDetailsASBO.getSearchBy().equalsIgnoreCase("PDR")){  //SEARCH BY THE PUBLISH DATE RANGE
			filteredContentDataListMatch = filteredContentDataListMatchAny;

			LOGGER.info("PDR DATE inside the getDocPublishDateStart search any of the following step 1"+filteredContentDataListMatch.size());

			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			for(int i=0;i<filteredContentDataListMatch.size();i++){
				try {
					LOGGER.debug("PDR DATE loop execution count -->"+i);
					String datepublishActual =filteredContentDataListMatch.get(i).getPublishDate();
					String searchPublishStartDate =getSearchSepecificDetailsASBO.getDocPublishDateStart();
					String searchPublishEndDate =getSearchSepecificDetailsASBO.getDocPublishDateEnd();
					Date SearchpublishdateStart = sdf.parse(searchPublishStartDate);
					Date SearchpublishdateEnd = sdf.parse(searchPublishEndDate);
					Date datepublishOriginal= sdf.parse(datepublishActual);
					LOGGER.debug("AlfrescoIntegrationController DATESearchpublishdateStart PDR"+SearchpublishdateStart);
					LOGGER.debug("AlfrescoIntegrationController DATE SearchpublishdateEnd PDR"+SearchpublishdateEnd);
					LOGGER.debug("AlfrescoIntegrationController DATE datepublishOriginal PDR" +datepublishOriginal);
					if(SearchpublishdateStart.before(datepublishOriginal) ||  SearchpublishdateStart.equals(datepublishOriginal)){
						if ( SearchpublishdateEnd.after(datepublishOriginal) || SearchpublishdateEnd.equals(datepublishOriginal)){
							{
								LOGGER.debug("AlfrescoIntegrationController DATE the date is within the requested parameters   PDR");
								ContentData fileDateSearchType = new ContentData();
								fileDateSearchType=filteredContentDataListMatch.get(i);
								freshContentDataList.add(fileDateSearchType);
							}
						}
					}
					
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					LOGGER.error(e.getStackTrace());
				}



			}
			
			LOGGER.info("PDR advance DATE loop execution count --> freshContentDataList.size()"+freshContentDataList.size());

			for(int pi=0;pi<freshContentDataList.size();pi++){
				
				LOGGER.debug("PDR advance DATE loop execution count -->"+pi);
				String datepublishActualOne =freshContentDataList.get(pi).getPublishDate();
				String searchPublishStartDateOne =getSearchSepecificDetailsASBO.getDocPublishDateStart();
				String searchPublishEndDateOne =getSearchSepecificDetailsASBO.getDocPublishDateEnd();
				Date SearchpublishdateStartOne;
				try {
					SearchpublishdateStartOne = sdf.parse(searchPublishStartDateOne);
					Date SearchpublishdateEndOne = sdf.parse(searchPublishEndDateOne);
					Date datepublishOriginalOne= sdf.parse(datepublishActualOne);
					if(SearchpublishdateStartOne.before(datepublishOriginalOne) || SearchpublishdateStartOne.equals(datepublishOriginalOne)){

						if(SearchpublishdateEndOne.after(datepublishOriginalOne) || SearchpublishdateEndOne.equals(datepublishOriginalOne)){

							ContentData fileDateSearchTypeNew = new ContentData();
							fileDateSearchTypeNew=freshContentDataList.get(pi);
							finalizingList.add(fileDateSearchTypeNew);

						}
					}
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			
			LOGGER.info("AlfrescoIntegrationController PUBLISH DATE FINALIZING LIST Size is "+finalizingList.size());
		}

		if (getSearchSepecificDetailsASBO.getSearchBy().equalsIgnoreCase("UDR")){  //SEARCH BY THE UPLOAD DATE RANGE
			filteredContentDataListMatch = filteredContentDataListMatchAny;
			LOGGER.info("UDR DATE inside the getDocPublishDateStart search any of the following"+filteredContentDataListMatch.size());
			if (getSearchSepecificDetailsASBO.getDocUploadDateEnd()!=null && getSearchSepecificDetailsASBO.getDocUploadDateStart()!=null){
				LOGGER.debug("AlfrescoIntegrationController DATE inside the getDocUploadDateEnd search any of the following");
				//		SimpleDateFormat sdfupload = new SimpleDateFormat("dd/mm/yyyy");
				SimpleDateFormat sdfupload = new SimpleDateFormat("dd/MM/yyyy");
				for(int i=0;i<filteredContentDataListMatch.size();i++){
					String dateUploadActual =filteredContentDataListMatch.get(i).getUploadDate();
					String searchUploadStartDate =getSearchSepecificDetailsASBO.getDocUploadDateStart();
					String searchUploadEndDate =getSearchSepecificDetailsASBO.getDocUploadDateEnd();
					LOGGER.debug("AlfrescoIntegrationController DATE inside the getDocUploadDateEnd search any of the dateUploadActual UDR"+dateUploadActual);
					LOGGER.debug("AlfrescoIntegrationController DATE inside the getDocUploadDateEnd search any of the searchUploadStartDate UDR"+searchUploadStartDate);
					LOGGER.debug("AlfrescoIntegrationController DATE inside the getDocUploadDateEnd search any of the searchUploadEndDate UDR"+searchUploadEndDate);
					try {
						Date SearchUploaddateStart = sdfupload.parse(searchUploadStartDate);
						Date SearchUploaddateEnd = sdfupload.parse(searchUploadEndDate);
						Date dateUploadOriginal= sdfupload.parse(dateUploadActual);
						//					if((SearchUploaddateStart.before(dateUploadOriginal) && SearchUploaddateEnd.after(dateUploadOriginal)) || SearchUploaddateStart.equals(dateUploadOriginal)  || SearchUploaddateEnd.equals(dateUploadOriginal)){
						if(SearchUploaddateStart.before(dateUploadOriginal) || SearchUploaddateStart.equals(dateUploadOriginal) ){ 
							if(SearchUploaddateEnd.after(dateUploadOriginal) ||  SearchUploaddateEnd.equals(dateUploadOriginal)){

								ContentData fileDateSearchType = new ContentData();
								LOGGER.debug("AlfrescoIntegrationController DATE match found for the upload parameters");
								fileDateSearchType=filteredContentDataListMatch.get(i);
								freshContentDataList.add(fileDateSearchType);
				
							}
						}

					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						LOGGER.error(e.getStackTrace());
					}


				}

				LOGGER.info("udr advance DATE loop execution count --> freshContentDataList.size()"+freshContentDataList.size());
				
				
				for(int pi=0 ;pi<freshContentDataList.size();pi++){
					LOGGER.debug("INSIDE  advance DATE loop execution count --> freshContentDataList.size()"+freshContentDataList.size());
					String dateUploadActualOne =freshContentDataList.get(pi).getUploadDate();
					String searchUploadStartDateOne =getSearchSepecificDetailsASBO.getDocUploadDateStart();
					String searchUploadEndDateOne =getSearchSepecificDetailsASBO.getDocUploadDateEnd();
				
					try {
						Date SearchUploaddateStartTwo = sdfupload.parse(searchUploadStartDateOne);
						Date SearchUploaddateEndTwo = sdfupload.parse(searchUploadEndDateOne);
						Date dateUploadOriginalTwo= sdfupload.parse(dateUploadActualOne);
						if (SearchUploaddateStartTwo.before(dateUploadOriginalTwo) || SearchUploaddateStartTwo.equals(dateUploadOriginalTwo)){
							if(SearchUploaddateEndTwo.after(dateUploadOriginalTwo) || SearchUploaddateEndTwo.equals(dateUploadOriginalTwo)){
								ContentData fileDateSearchTypeNew = new ContentData();
								fileDateSearchTypeNew=freshContentDataList.get(pi);
								finalizingList.add(fileDateSearchTypeNew);

							}
							
						}
						
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				
				LOGGER.info("AlfrescoIntegrationController UDR FINALIZING LIST Size is "+finalizingList.size());
			}

		}


		if(finalizingList.isEmpty()){
			getContentResponseFilteredASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getFilteredContentResponse(filteredContentDataListLatest,getSearchSepecificDetailsASBO);
			
			finalizingList =null;
		}
		else{
		//	replacingURL(finalizingList);
			getContentResponseFilteredASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getFilteredContentResponse(finalizingList,getSearchSepecificDetailsASBO);
			filteredContentDataListNew=null;
			finalizingList =null;

		}
	
		return getContentResponseFilteredASBO;



	}

//	private void replacingURL(List<ContentData> finalizingList) {
//		
//		// TODO Auto-generated method stub
//		for(int i=0;i<finalizingList.size();i++){
//			String orgurl = finalizingList.get(i).getDocUrl();
//			String newUrl =orgurl.replace(UtilProperties.getAlfWebURL(), UtilProperties.getAlfEmpURL());
//			if(newUrl!=null && newUrl.endsWith(",")){
//				newUrl= newUrl.substring(0, newUrl.length()-1);
//			}
//			finalizingList.get(i).setDocUrl(newUrl);
//		}
//		
//	}

}
