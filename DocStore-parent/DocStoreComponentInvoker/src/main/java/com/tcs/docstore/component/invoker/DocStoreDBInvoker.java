package com.tcs.docstore.component.invoker;

import org.apache.log4j.Logger;

import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.request.LoginDBRequestASBO;
import com.tcs.docstore.db.asbo.response.LoginDBResponseASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.persist.service.GetDepatementListAlfService;
import com.tcs.docstore.persist.service.GetDepatementListAlfServiceImpl;
import com.tcs.docstore.persist.service.GetHRMSDetailsService;
import com.tcs.docstore.persist.service.GetHRMSDetailsServiceImpl;
import com.tcs.docstore.persist.service.GetIssuedByListService;
import com.tcs.docstore.persist.service.GetIssuedByListServiceImpl;
import com.tcs.docstore.persist.service.LoginService;
import com.tcs.docstore.persist.service.LoginServiceImpl;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 738566
 *
 */
public class DocStoreDBInvoker {

	private static final Logger LOGGER = Logger.getLogger(DocStoreDBInvoker.class);

	private LoginService loginService;
	private GetDepatementListAlfService getDepartmentlistservice;
	private GetHRMSDetailsService gethrmsdetailservice;
	private GetIssuedByListService getissuedbylistservice;

	//	private BreakinDBRequestASBO breakinDBRequestASBO;

	public DocStoreDBInvoker(){

		loginService= new LoginServiceImpl();
		getDepartmentlistservice =  new GetDepatementListAlfServiceImpl();
		gethrmsdetailservice = new GetHRMSDetailsServiceImpl();
		getissuedbylistservice = new GetIssuedByListServiceImpl();
	}

	public DocStoreResponseObject invokeDB(
			DocStoreRequestObject requestVO)
					throws IntegrationTechnicalException {
		LOGGER.info("Inside dBInvoker---" +requestVO.getHeader().getEventID());


		 if(UtilConstants.LOGIN.equalsIgnoreCase(requestVO.getHeader().getEventID())){
			 LOGGER.info("Inside dBInvoker LOGIN");
			return login(requestVO);
		}

		// below added to get the list of departments for alfresco doc upload 
		else if (UtilConstants.GET_DEPARTMENT_LIST.equalsIgnoreCase(requestVO
				.getHeader().getEventID())) {
			LOGGER.info("Inside dBInvoker GET_DEPARTMENT_LIST");
			return getDepartMentList(requestVO);

		}
		
		// below added to get the list of departments for alfresco doc upload 
		else if (UtilConstants.GET_ISSUED_BY.equalsIgnoreCase(requestVO
				.getHeader().getEventID())) {
			LOGGER.info("Inside dBInvoker GET_ISSUED_BY");
			return getIssuedByList(requestVO);

		}
		
		
		//below code is for connection to HRMS and then getting the details of the user employee
		else if (UtilConstants.ALFRESCO_GET_SEARCH_DOCUMENT.equalsIgnoreCase(requestVO
				.getHeader().getEventID())) {
			LOGGER.info("Inside dBInvoker ALFRESCO_GET_SEARCH_DOCUMENT");
			return getEmployeeRoleDepartment(requestVO);
		}

		else {
			return null;
		}

	}


	private DocStoreResponseObject login(
			DocStoreRequestObject requestVO) {

		LoginDBRequestASBO loginDBRequestASBO= null;
		LoginDBResponseASBO loginDBResponseASBO=null;
		if (requestVO instanceof LoginDBRequestASBO) {
			loginDBRequestASBO = (LoginDBRequestASBO) requestVO;
		} else {
			try {
				throw new IntegrationTechnicalException();
			} catch (IntegrationTechnicalException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		loginDBResponseASBO= loginService.getUserName(loginDBRequestASBO);

		return loginDBResponseASBO;
	}


	
	private DocStoreResponseObject getDepartMentList(DocStoreRequestObject requestVO) throws IntegrationTechnicalException{
		LOGGER.info("###Inside getDepartMentList  1###");

		GetDepartmentListAlfrescoDBRequestASBO getDepartmentList = null;

		if (requestVO instanceof DocStoreRequestObject) { 

			try{

				getDepartmentList =  (GetDepartmentListAlfrescoDBRequestASBO) requestVO;
			}
			catch(Exception e){
				e.printStackTrace();
			}
			LOGGER.info("###Inside getDepartMentList  3###");
		} 
		else {

			throw new IntegrationTechnicalException();

		}

		LOGGER.info("just before the calling of service class getDepartMentList");
		// Call DB Method and set response
		return getDepartmentlistservice.getDepartmentList(getDepartmentList);
	}

	
	private DocStoreResponseObject getEmployeeRoleDepartment(
			DocStoreRequestObject requestvo) {
		// TODO Auto-generated method stub
		LOGGER.info("inside the db invoker to access the department and role details");
		GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo = new GetHRMSDetailsDBRequestASBO();
		if (requestvo instanceof DocStoreRequestObject){
			gethrmsdetailsdbreqasbo =(GetHRMSDetailsDBRequestASBO)requestvo;
		}

	//	return gettickervalueservice.getTickerValues(requestVO);
		return gethrmsdetailservice.getHrmsDetailsList(gethrmsdetailsdbreqasbo);
	}

	private DocStoreResponseObject getIssuedByList(
			DocStoreRequestObject requestVO) throws IntegrationTechnicalException {
		// TODO Auto-generated method stub
		LOGGER.info("###Inside getIssuedByList  1###");

		GetListOfIssuedByDBRequestASBO getIssuedByList = null;
		//System.out.println("###Inside updateManagePortalDetails ###"+((ManagePortalDBRequestASBO) requestVO).getDbrequestStatus());

		if (requestVO instanceof DocStoreRequestObject) { 

			try{

				getIssuedByList =  (GetListOfIssuedByDBRequestASBO) requestVO;
			}
			catch(Exception e){
				e.printStackTrace();
			}
		} 
		else {

			throw new IntegrationTechnicalException();

		}

		LOGGER.info("just before the calling of service class getIssuedByList");
		// Call DB Method and set response
		return getissuedbylistservice.getIssuedBy(getIssuedByList);
	}


}

