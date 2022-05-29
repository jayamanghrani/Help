/**
 * 
 */
package com.tcs.docstore.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

import com.tcs.docstore.alfresco.asbo.request.GetListOfDepartmentsASBO;
import com.tcs.docstore.alfresco.asbo.request.GetListOfIssuedByRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.DocumentUploadResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetDepartmentNameResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetListOfIssuedByResponseASBO;
import com.tcs.docstore.asbo.emp.request.AlfrescoRequestASBO;
import com.tcs.docstore.component.integrator.AlfrescoUploadDocumentIntegrator;
import com.tcs.docstore.component.integrator.DBChannelIntegrator;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetHRMSDetailsDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetDepartmentListDBResponseASBO;
import com.tcs.docstore.db.asbo.response.GetHRMSDetailsDBResponseASBO;
import com.tcs.docstore.db.asbo.response.GetListOfIssuedByDBResponseASBO;
import com.tcs.docstore.persist.dao.GetHRMSDetailsDao;
import com.tcs.docstore.persist.dao.GetHRMSDetailsDaoImpl;
import com.tcs.docstore.transformation.alfresco.transformers.AlfrescoUploadTransformer;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;


/**
 * @author 585226
 *
 */
public class AlfrescoUploadIntegrationController {

	//	private AlfrescoUploadDocumentIntegrator audi;
	private DBChannelIntegrator dbChannelIntegrator;
	public AlfrescoUploadIntegrationController(){
	
		dbChannelIntegrator = new DBChannelIntegrator();
	}

	private static final Logger LOGGER = Logger.getLogger(AlfrescoUploadIntegrationController.class);
	public  DocStoreResponseObject uploadDocument (AlfrescoRequestASBO requestASBO){

		Session session1;
		String flag=null;

		AlfrescoUploadDocumentIntegrator audi =new AlfrescoUploadDocumentIntegrator();
		
		session1=audi.createSession();
		LOGGER.info("Inside the integration controller the session object is!"+session1.toString());
		LOGGER.debug("Inside the integration controller the session object is!"+session1.toString());
		AlfrescoUploadTransformer aut = new AlfrescoUploadTransformer();
		DocUploadDBRequestASBO acudreq =	(DocUploadDBRequestASBO) aut.transformUploadDocument(requestASBO);
		LOGGER.info("before the alfresco upload document integrator!");
		
		DocumentUploadResponseASBO  durasbo = new DocumentUploadResponseASBO();
		try{
	
			durasbo.setResponseOfUpload(flag);
		}
		catch(Exception e){

			LOGGER.error(e.getStackTrace());
		}
		LOGGER.info("after  the alfresco upload document integrator!");
		return durasbo;
	}


	private void uploadDocToServer(DocUploadDBRequestASBO acudreq) throws Exception {
		// TODO Auto-generated method stub

		File file ;    
		File fileDir ;
		String filePath="";
		String uploadFileName="";
		String errorMsg="";
		FileItem fi = null;
		file = new File( filePath );
		fi.write( file );



	}


	public  DocStoreResponseObject getDepartmentList (GetListOfDepartmentsASBO requestASBO, String userID){
		LOGGER.debug("getDepartmentList");
		//	TestDocUpload tdu = new TestDocUpload();
		//	AlfrescoUploadDocumentIntegrator audi = new AlfrescoUploadDocumentIntegrator();
		//	Docupload du = new Docupload();

		String memberOfGroup = requestASBO.getHeader().getGroups();

		String valUser = getRolefromOID(memberOfGroup);

		GetDepartmentListDBResponseASBO getdepartmentListdbResponseasbo= new GetDepartmentListDBResponseASBO();
		GetDepartmentNameResponseASBO getdepartmentnameresponseasbo = new GetDepartmentNameResponseASBO();
		AlfrescoUploadTransformer aut1 = new AlfrescoUploadTransformer();
		
		GetDepartmentListAlfrescoDBRequestASBO gadr =	(GetDepartmentListAlfrescoDBRequestASBO) aut1.transformDepartmentName(requestASBO);
		LOGGER.info("before the getDepartmentList!");

		if(valUser.equalsIgnoreCase("authorized")){

			try{
				//	audi.uploadDocumentAlf(acudreq);

				// DB call changed to caching call
				getdepartmentListdbResponseasbo = (GetDepartmentListDBResponseASBO)dbChannelIntegrator.execute(gadr);

			}
			catch(Exception e){

				LOGGER.error(e.getStackTrace());
			}

			getdepartmentnameresponseasbo = (GetDepartmentNameResponseASBO) aut1.transformDepartmentNameList(getdepartmentListdbResponseasbo);

			LOGGER.info("after  the alfresco upload document integrator!");
			return getdepartmentnameresponseasbo;
		}


		else{
			LOGGER.info("The user is not a HO IT Admin user so show him only one department!");
			LOGGER.debug("getDepartmentList the user is not a HO IT Admin user so show him only one department");
			GetHRMSDetailsDao ghdd = new GetHRMSDetailsDaoImpl();
			GetHRMSDetailsDBRequestASBO gethrmsdetailsdbreqasbo = new GetHRMSDetailsDBRequestASBO();
			GetHRMSDetailsDBResponseASBO gthrmsdbrespasbo = new GetHRMSDetailsDBResponseASBO();
			gethrmsdetailsdbreqasbo.setHeader(requestASBO.getHeader());
			gethrmsdetailsdbreqasbo.setUserID(userID);
			gthrmsdbrespasbo=	ghdd.gethrmsdetailsList(gethrmsdetailsdbreqasbo);
			List<String> listUsersDepartMent = new ArrayList<String>();
		//	listUsersDepartMent.set(0, gthrmsdbrespasbo.getDepartmentName());
			listUsersDepartMent.add(gthrmsdbrespasbo.getDepartmentName());
			getdepartmentListdbResponseasbo.setGetDepartmentNameList(listUsersDepartMent);
			getdepartmentnameresponseasbo = (GetDepartmentNameResponseASBO) aut1.transformDepartmentNameList(getdepartmentListdbResponseasbo);
			LOGGER.debug("getDepartmentList the user is not a HO IT Admin user so show him only one department");

			LOGGER.info("after  the alfresco upload document integrator!");
			return getdepartmentnameresponseasbo;

		}

	}

	
	// This service gives the list of all the departments for the user for searching purpose
	public  DocStoreResponseObject getSearchedDepartmentList (GetListOfDepartmentsASBO requestASBO, String userID){
	   	 LOGGER.debug("getSearchedDepartmentList");
		//	TestDocUpload tdu = new TestDocUpload();
		//	AlfrescoUploadDocumentIntegrator audi = new AlfrescoUploadDocumentIntegrator();
		//	Docupload du = new Docupload();

		GetDepartmentListDBResponseASBO getdepartmentListdbResponseasbo= new GetDepartmentListDBResponseASBO();
		GetDepartmentNameResponseASBO getdepartmentnameresponseasbo = new GetDepartmentNameResponseASBO();
		AlfrescoUploadTransformer aut1 = new AlfrescoUploadTransformer();
		
		GetDepartmentListAlfrescoDBRequestASBO gadr =	(GetDepartmentListAlfrescoDBRequestASBO) aut1.transformDepartmentName(requestASBO);
		LOGGER.info("before the getDepartmentList!");

		

			try{
				//	audi.uploadDocumentAlf(acudreq);

				// DB call changed to caching call
				getdepartmentListdbResponseasbo = (GetDepartmentListDBResponseASBO)dbChannelIntegrator.execute(gadr);

			}
			catch(Exception e){

				LOGGER.error(e.getStackTrace());
			}

			getdepartmentnameresponseasbo = (GetDepartmentNameResponseASBO) aut1.transformDepartmentNameList(getdepartmentListdbResponseasbo);

			LOGGER.info("after  the alfresco upload document integrator!");
			return getdepartmentnameresponseasbo;
		
	}
	// Added for new Docstore Starts

	public String  getRolefromOID(String memberOfGroup) {
		// TODO Auto-generated method stub
		//	String str=header.getGroups();
		String flagHOITAdmin="unauthorized";
		ArrayList<String> aList= new ArrayList<String>(Arrays.asList(memberOfGroup.split(",")));
		for(int i=0;i<aList.size();i++)
		{
			LOGGER.info(" -->"+aList.get(i));
		}
		//	gethrmsreqasbo.setRoleOID("Others");
		for(int i=0;i<aList.size();i++){

			if(aList.get(i).equalsIgnoreCase(UtilConstants.HO_IT_ADMIN)){
				//		gethrmsreqasbo.setRoleOID(UtilConstants.HO_IT_ADMIN);
				flagHOITAdmin="authorized";
				break;
			}

		}

		return flagHOITAdmin;
	}


	// Added for New Doc Store Ends 3 Oct 2016

	public DocStoreResponseObject getIssuedBy(
			GetListOfIssuedByRequestASBO requestASBO) {
		GetListOfIssuedByDBResponseASBO getIssuedBydbResponseasbo= new GetListOfIssuedByDBResponseASBO();
		GetListOfIssuedByResponseASBO getIssuedByresponseasbo = new GetListOfIssuedByResponseASBO();
		AlfrescoUploadTransformer aut2 = new AlfrescoUploadTransformer();
		GetListOfIssuedByDBRequestASBO getIssuedByDbreqasno =	(GetListOfIssuedByDBRequestASBO) aut2.transformIssuedByRequestName(requestASBO);
		LOGGER.info("before the getDepartmentList!");
		try{
			//	audi.uploadDocumentAlf(acudreq);

			// DB call changed to caching call
			getIssuedBydbResponseasbo = (GetListOfIssuedByDBResponseASBO)dbChannelIntegrator.execute(getIssuedByDbreqasno);

		}
		catch(Exception e){

			LOGGER.error(e.getStackTrace());
		}

		getIssuedByresponseasbo = (GetListOfIssuedByResponseASBO) aut2.transformIssuedByList(getIssuedBydbResponseasbo);

		LOGGER.info("after  the alfresco upload document integrator!");
		return getIssuedByresponseasbo;
	}

}
