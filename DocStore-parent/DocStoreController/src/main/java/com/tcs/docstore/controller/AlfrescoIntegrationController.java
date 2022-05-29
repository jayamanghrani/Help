/********************************************************************************
 * Copyright (c) 2013-2015, TATA Consultancy Services Limited (TCSL)
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without 
 * modification, are not permitted.
 * 
 * Neither the name of TCSL nor the names of its contributors may be 
 * used to endorse or promote products derived from this software without 
 * specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" 
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE 
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE 
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE 
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR 
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF 
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS 
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN 
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
 * POSSIBILITY OF SUCH DAMAGE.
 *******************************************************************************/
package com.tcs.docstore.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.docstore.alfresco.asbo.request.DocSearchRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.GetAlfrecoContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.GetContentRequestASBO;
import com.tcs.docstore.alfresco.asbo.request.SearchSpecificDetailsRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetAlfrescoContentResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetContentResponseASBO;
import com.tcs.docstore.asbo.alfresco.ContentData;
import com.tcs.docstore.asbo.emp.request.GetHRMSDetailsRequestASBO;
import com.tcs.docstore.component.integrator.DBChannelIntegrator;
import com.tcs.docstore.component.integrator.DomainChannelIntegrator;
import com.tcs.docstore.controller.util.ControllerUtil;
import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;
import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.exception.cmo.IntegrationTransformationException;
import com.tcs.docstore.transformation.alfresco.transformers.GetAlfrescoContentTransformer;
import com.tcs.docstore.transformation.alfresco.transformers.GetEmployeeDocumentsTransformer;
import com.tcs.docstore.util.ExceptionUtil;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;
import com.tcs.docstore.vo.cmo.Header;

// TODO: Auto-generated Javadoc
/**
 * The Class AlfrescoIntegrationController.
 * 
 * @author 376448
 */
public class AlfrescoIntegrationController {

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger("docStoreLogger");

	/** The gson. */
	private static Gson gson;

	static {
		gson = ControllerUtil.getGson();
	}

	/** The domain channel integrator. */
	private DomainChannelIntegrator domainChannelIntegrator;
	private DBChannelIntegrator dbChannelIntegrator;
	/**
	 * Instantiates a new alfresco integration controller.
	 */
	public AlfrescoIntegrationController() {
		domainChannelIntegrator = new DomainChannelIntegrator();
		dbChannelIntegrator = new DBChannelIntegrator();
	}

	/**
	 * Gets the single instance of DomainIntegrationProcessController.
	 * 
	 * @return single instance of DomainIntegrationProcessController
	 */
	public static synchronized DomainChannelIntegrator getInstance() {
		DomainChannelIntegrator instance = null;
		if (instance == null) {

			instance = new DomainChannelIntegrator();

		}

		return instance;

	}

	/**
	 * Process get content.
	 * 
	 * @param getContentRequestASBO
	 *            the get content request asbo
	 * @return the EmployeePortal response vo
	 */
	public DocStoreResponseObject processGetContent(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing Alfresco Request Get ProcessContent");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		DocStoreRequestObject employeeRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();
		try {
			employeeRequestObject = getAlfrecoContentTransformer.transformRequest(getContentRequestASBO);
			LOGGER.info("After Tranformer" +employeeRequestObject.getHeader());

		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getAlfrescoContentResponseASBO = null;

		try {
			getAlfrescoContentResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(employeeRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getAlfrescoContentResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getAlfrescoContentResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);

		}
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			//errorVO.setApplicationId(getAlfrescoContentResponseASBO.getHeader()
			//	.getApplicationId());
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}


	/**
	 * Process carousel.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the ba ncs integration response object
	 */
	public DocStoreResponseObject processCarousel(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing Carousel");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		DocStoreRequestObject docStoreRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			docStoreRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(docStoreRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			//errorVO.setApplicationId(getContentResponseASBO.getHeader()
			//	.getApplicationId());
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}

	/**
	 * Process latestUpdates.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public DocStoreResponseObject processlatestUpdates(
			GetContentRequestASBO getContentRequestASBO) {
		LOGGER.info("Processing latestUpdates");
		GetAlfrescoContentTransformer getAlfrecoContentTransformer = new GetAlfrescoContentTransformer();
		DocStoreRequestObject docStoreRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=getContentRequestASBO.getHeader();

		try {
			docStoreRequestObject = getAlfrecoContentTransformer
					.transformRequest(getContentRequestASBO);
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		// call cache service
		GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

		try {
			getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator
					.execute(docStoreRequestObject);
		} catch (IntegrationTechnicalException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

		} catch (Exception e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
		}

		getContentAlfrescoResponseASBO.setHeader(getContentRequestASBO
				.getHeader());

		try {
			getContentResponseASBO = (GetContentResponseASBO) getAlfrecoContentTransformer
					.transformResponse(getContentAlfrescoResponseASBO);
		} catch (IntegrationTransformationException e) {
			LOGGER.error(e.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "response", e);
		} 
		if(getContentResponseASBO.getContentDataList().size() > 0) {
			return getContentResponseASBO;
		} else {
			ErrorVO errorVO = new ErrorVO();
			//errorVO.setApplicationId(getContentResponseASBO.getHeader()
			//	.getApplicationId());
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
			errorVO.setErrorMessage("No Data found");
			errorVO.setReason("No Content Data found");
			LOGGER.info("No Content Data found");
			return errorVO;
		}

	}


/****Code added for old docs****/
	
	/**
	 * Process documents.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public DocStoreResponseObject processOldDocuments(
			DocSearchRequestASBO docSearchRequestASBO) {
		LOGGER.info("Processing old documents");
		GetEmployeeDocumentsTransformer getEmployeeDocumentsTransformer = new GetEmployeeDocumentsTransformer();
		DocStoreRequestObject docStoreRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		Header header=docSearchRequestASBO.getHeader();

		LOGGER.info("the header user ID is "+header.getEmployeeId());



			try {
				docStoreRequestObject = getEmployeeDocumentsTransformer.transformRequest(docSearchRequestASBO);
				docStoreRequestObject.getHeader().setEventID(UtilConstants.ALFRESCO_GET_CONTENT);
                //Validation starts
				ErrorVO error=getEmployeeDocumentsTransformer.validateUserRequest(docSearchRequestASBO);
				if( error.getErrorCode()==901)
				{
					LOGGER.error(error.getErrorMessage());
					return error;
				}
				//Validation ends
			//	getAlfrescoContentRequestASBO= (GetAlfrecoContentRequestASBO)getEmployeeDocumentsTransformer.transformRequest(docSearchRequestASBO);
			} catch (IntegrationTransformationException exception) {
				LOGGER.error(exception.getStackTrace());
				return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
			}
			// call cache service
			GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

			try {
				getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator.execute(docStoreRequestObject);
				LOGGER.debug("CACHE OUTPUT EMP DOC : "+ new Gson().toJson(getContentAlfrescoResponseASBO));
				if(getContentAlfrescoResponseASBO.getContentDataList().size() > 0) {
					getContentResponseASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getOldDocContentResponseTransform(docSearchRequestASBO, getContentAlfrescoResponseASBO);
					// 585226 added null pointert check to remove the exception in logs	
					if(getContentResponseASBO.getContentDataList() == null){
						getContentResponseASBO.setResult("No documents found within search parameters");
						LOGGER.debug("the  getContentResponseASBO is null here ");
						}
						else{
					if(getContentResponseASBO.getContentDataList().size()==0){
						getContentResponseASBO.setResult("No documents found within search parameters");
						
					}
						}

					}
			
			} catch (IntegrationTechnicalException e) {
				e.printStackTrace();
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			} catch (Exception e) {
				e.printStackTrace();
				LOGGER.error(e.getStackTrace());
				LOGGER.error(e.getMessage());
			//	LOGGER.info(e.printStackTrace()+"");
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
			}


			return getContentResponseASBO;
	}

	/****Code added for old docs****/


	/**
	 * Process documents.
	 *
	 * @param getContentRequestASBO the get content request asbo
	 * @return the employee portal response object
	 */
	public DocStoreResponseObject processDocuments(
			DocSearchRequestASBO docSearchRequestASBO) {
		LOGGER.info("Processing documents");
		LOGGER.info("step 2 in searching the user id"+docSearchRequestASBO.getHeader().getEmployeeId());
		GetEmployeeDocumentsTransformer getEmployeeDocumentsTransformer = new GetEmployeeDocumentsTransformer();
		DocStoreRequestObject docStoreRequestObject = null;
		GetContentResponseASBO getContentResponseASBO = null;
		GetContentResponseASBO getContentResponseFilteredASBO = null;
		SearchSpecificDetailsRequestASBO getSearchSepecificDetailsASBO = new SearchSpecificDetailsRequestASBO();
		GetAlfrecoContentRequestASBO getAlfrescoContentRequestASBO = null;
		Header header=docSearchRequestASBO.getHeader();

		LOGGER.info("the header user ID is "+header.getEmployeeId());
		String flag = null;


		// validating from HRMS DB
		GetHRMSDetailsDBResponseASBO	hrmsdbresponseasbo= new GetHRMSDetailsDBResponseASBO();
		GetHRMSDetailsRequestASBO gethrmsreqasbo =  new GetHRMSDetailsRequestASBO();
		gethrmsreqasbo.setHeader(header);
		gethrmsreqasbo.setUserID(header.getEmployeeId());




		getHRMSRolefromOID(docSearchRequestASBO.getHeader(),gethrmsreqasbo); // this method set the group to EmployeeDocAdmin IF THE USER HAS SEARCH ACCESS

		GetHRMSDetailsDBRequestASBO gethrmsdbreqasbo =getEmployeeDocumentsTransformer.transformHRMSRequest(gethrmsreqasbo);

			// FOR RO/DO/BO users the max length 7 and above,as of now the maxlength for userID is 5 digit
		try {
			
			if(gethrmsdbreqasbo.getUserID().length() >6 ){
				LOGGER.info(" RO/DO/BO user tried to access  the ID is"+gethrmsdbreqasbo.getUserID());
				hrmsdbresponseasbo.setDepartmentName("All");
				hrmsdbresponseasbo.setRoleOID("NOrOLE");;
				hrmsdbresponseasbo.setPosition("NOPosition");
				hrmsdbresponseasbo.setUserID(gethrmsdbreqasbo.getUserID());
			}
			else{
			hrmsdbresponseasbo =(GetHRMSDetailsDBResponseASBO)dbChannelIntegrator.execute(gethrmsdbreqasbo);
			}
		} catch (IntegrationTechnicalException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.error(e1.getStackTrace());
		}
       // Resolved HRMS issue for document Search-------------------------------------
        if(hrmsdbresponseasbo.getRoleOID()==null){
        	ErrorVO errorVO = new ErrorVO();
			errorVO.setErrorCode(UtilConstants.ERROR_CODE_NOUSERID_Exception);
			errorVO.setErrorMessage("User is Not Mapped to HRMS");
			errorVO.setReason("User is Not Mapped to HRMS");
			LOGGER.info("User is Not Mapped to HRMS");
			return errorVO;
        }
        //---------------End------------------------------------------------------------
		if(hrmsdbresponseasbo.getRoleOID().equalsIgnoreCase(UtilConstants.HO_IT_ADMIN)){
			flag="success";
		}
		else {
			flag=validateUserFromHRMS(hrmsdbresponseasbo,docSearchRequestASBO);
		}

		//if the flag is invalid then we the user does not have acces to the document
		if (flag.equalsIgnoreCase("failure")){
			List<ContentData> hrmsFaluredatalist = new ArrayList<ContentData>();
			LOGGER.info("the user is NOT validated from HRMS");

			//		getContentResponseFilteredASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getFilteredContentResponse(hrmsFaluredatalist,getSearchSepecificDetailsASBO);
			getContentResponseFilteredASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getHRMSFilteredContentResponse(hrmsFaluredatalist,getSearchSepecificDetailsASBO);
			return getContentResponseFilteredASBO;
		}


		// Else block means the user can view the documents
		else{
			LOGGER.info("the user is validated from HRMS");

			try {
				docStoreRequestObject = getEmployeeDocumentsTransformer.transformRequest(docSearchRequestASBO);
				docStoreRequestObject.getHeader().setEventID(UtilConstants.ALFRESCO_GET_CONTENT);

				getAlfrescoContentRequestASBO= (GetAlfrecoContentRequestASBO)getEmployeeDocumentsTransformer.transformAlfrescoDocSearchRequest(docSearchRequestASBO);
				//validation part for search form starts here-------
				
				ErrorVO error=getEmployeeDocumentsTransformer.validateUserRequest(docSearchRequestASBO);
				if( error.getErrorCode()==901)
				{
					LOGGER.error(error.getErrorMessage());
					return error;
				}
				
		} catch (IntegrationTransformationException exception) {
			LOGGER.error(exception.getStackTrace());
			return ExceptionUtil.getTransformationErrorVO(header, "request", exception);
		}
		
		//End of validation code for Search page--
			// call cache service
			GetAlfrescoContentResponseASBO getContentAlfrescoResponseASBO = null;

			try {
				getContentAlfrescoResponseASBO = (GetAlfrescoContentResponseASBO) domainChannelIntegrator.execute(docStoreRequestObject);// what happens here 585226 not aware
			//	LOGGER.debug("CACHE OUTPUT EMP DOC : "+ new Gson().toJson(getContentAlfrescoResponseASBO));
			} catch (IntegrationTechnicalException e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_RUNTIME_TechnichalException, e);

			} catch (Exception e) {
				LOGGER.error(e.getStackTrace());
				return ExceptionUtil.getTechnicalErrorVO(header, UtilConstants.ERROR_CODE_NODATA_TechnicalException, e);
			}

			getContentAlfrescoResponseASBO.setHeader(docSearchRequestASBO.getHeader());

			// why was this code commented before??

			getContentResponseASBO = (GetContentResponseASBO) getEmployeeDocumentsTransformer.getContentResponseTransform(getContentAlfrescoResponseASBO); 
			//		getSearchSepecificDetailsASBO = new SearchSpecificDetailsRequestASBO();
			getContentResponseFilteredASBO = new GetContentResponseASBO();   // Filtered Search List
			getSearchSepecificDetailsASBO.setDepatmentName(docSearchRequestASBO.getAlfrescoInput().getDeptName());
			getSearchSepecificDetailsASBO.setDocPublishDateEnd(docSearchRequestASBO.getAlfrescoInput().getDocpublishdateend());//publish END date
			getSearchSepecificDetailsASBO.setDocPublishDateStart(docSearchRequestASBO.getAlfrescoInput().getDocpublishdatestart()); //publish START date
			getSearchSepecificDetailsASBO.setDocumentType(docSearchRequestASBO.getAlfrescoInput().getDocType());
			getSearchSepecificDetailsASBO.setDocUploadDateEnd(docSearchRequestASBO.getAlfrescoInput().getDocuploaddateend());// upload date END date
			getSearchSepecificDetailsASBO.setDocUploadDateStart(docSearchRequestASBO.getAlfrescoInput().getDocuploaddatestart());// upload date START
			getSearchSepecificDetailsASBO.setFileName(docSearchRequestASBO.getAlfrescoInput().getDocName());
			getSearchSepecificDetailsASBO.setIssuedBy(docSearchRequestASBO.getAlfrescoInput().getIssuedBy());
			getSearchSepecificDetailsASBO.setSearchBy(docSearchRequestASBO.getAlfrescoInput().getSearchbytype()); // search by upload or publish date second radio button
			getSearchSepecificDetailsASBO.setMatchMethod(docSearchRequestASBO.getAlfrescoInput().getMatchMethod());// match the method if all needs to be matched or any of following
			//	getSearchSepecificDetailsASBO.setIsConfidential(docSearchRequestASBO.getAlfrescoInput().getIs);
			// why was this above code commented before??

			LOGGER.debug("the sysout of the document pages is "+getContentResponseASBO);
			if(getContentResponseASBO.getContentDataList().size() > 0) {

				if(getSearchSepecificDetailsASBO.getMatchMethod().equalsIgnoreCase("ALL")){
					// MATCH FOR SELECT aLL OF THE FOLLOWING  CODE
					
					String authCM =chiefManagerValidation(hrmsdbresponseasbo,docSearchRequestASBO);
					AlfrescoMatchAllHelperController matchallhelpControl = new AlfrescoMatchAllHelperController();
					getContentResponseFilteredASBO=	matchallhelpControl.searchDocumentMatchAll(getSearchSepecificDetailsASBO,getContentResponseASBO,hrmsdbresponseasbo,authCM);
					return getContentResponseFilteredASBO;

				}
				else{
					// MATCH FOR SELECT ANY OF THE FOLLOWING CODE
					LOGGER.info("**************\\\\\\\\\\inside match any of the following////***********");
					String authCM =chiefManagerValidation(hrmsdbresponseasbo,docSearchRequestASBO);
					AlfrescoMatchAnyHelperController  matchAny = new AlfrescoMatchAnyHelperController();
					getContentResponseFilteredASBO=matchAny.searchDocumentMatchAnyDetails(getSearchSepecificDetailsASBO,getContentResponseASBO,hrmsdbresponseasbo,authCM);
					return getContentResponseFilteredASBO;
				}

			} else {
				ErrorVO errorVO = new ErrorVO();
				//errorVO.setApplicationId(getContentResponseASBO.getHeader()
				//	.getApplicationId());
				errorVO.setErrorCode(UtilConstants.ERROR_CODE_TransformationException);
				errorVO.setErrorMessage("No Data found");
				errorVO.setReason("No Content Data found");
				LOGGER.info("No Content Data found");
				return errorVO;
			}

		}

	}

	private void getHRMSRolefromOID(Header header, GetHRMSDetailsRequestASBO gethrmsreqasbo) {
		// TODO Auto-generated method stub
		String str=header.getGroups();
		String flagHOITAdmin="unauthorized";
		ArrayList<String> aList= new ArrayList<String>(Arrays.asList(str.split(",")));
		for(int i=0;i<aList.size();i++)
		{
			LOGGER.info(" -->"+aList.get(i));
		}
		gethrmsreqasbo.setRoleOID("Others");
		for(int i=0;i<aList.size();i++){

			if(aList.get(i).equalsIgnoreCase(UtilConstants.HO_IT_ADMIN)){
				gethrmsreqasbo.setRoleOID(UtilConstants.HO_IT_ADMIN);
				flagHOITAdmin="authorized";
				break;
			}

		}

	}

	private String validateUserFromHRMS(GetHRMSDetailsDBResponseASBO hrmsdbresponseasbo, DocSearchRequestASBO docSearchRequestASBO) {
		String flag = null;
		if(hrmsdbresponseasbo.getUserID().equalsIgnoreCase(docSearchRequestASBO.getHeader().getEmployeeId())){
			if(hrmsdbresponseasbo.getDepartmentName().equalsIgnoreCase(docSearchRequestASBO.getAlfrescoInput().getDeptName()) || docSearchRequestASBO.getAlfrescoInput().getDeptName().equalsIgnoreCase("ALL")){
				LOGGER.debug("the department name is hrmsdbresponseasbo.getDepartmentName() "+hrmsdbresponseasbo.getDepartmentName());
				LOGGER.info("the department name is docSearchRequestASBO.getAlfrescoInput().getDeptName() "+docSearchRequestASBO.getAlfrescoInput().getDeptName());
				flag = "success";

				return flag;

			}
			else{
				LOGGER.debug("the department name else is hrmsdbresponseasbo.getDepartmentName() "+hrmsdbresponseasbo.getDepartmentName());
				LOGGER.info("the department name else is docSearchRequestASBO.getAlfrescoInput().getDeptName() "+docSearchRequestASBO.getAlfrescoInput().getDeptName());

				//flag ="failure";
				flag = "success";
				return flag;
			}
		}

		else{
			LOGGER.info("the department names else is hrmsdbresponseasbo.getDepartmentName() "+hrmsdbresponseasbo.getDepartmentName());
			LOGGER.info("the department name  else is docSearchRequestASBO.getAlfrescoInput().getDeptName() "+docSearchRequestASBO.getAlfrescoInput().getDeptName());

		//	flag ="failure";
			flag = "success";
			return flag;
		}

	}
	
	private String chiefManagerValidation(GetHRMSDetailsDBResponseASBO hrmsdbresponseasbo, DocSearchRequestASBO docSearchRequestASBO){
		if(hrmsdbresponseasbo.getPosition().equalsIgnoreCase("Chief Manager")){
			if(hrmsdbresponseasbo.getDepartmentName().equalsIgnoreCase(docSearchRequestASBO.getAlfrescoInput().getDeptName())){
				return "validCM";
			}
			else{
				return "inValidCm";
			}
		}
		else{
			return "inValidCm";
		}
	}
	
	

}
