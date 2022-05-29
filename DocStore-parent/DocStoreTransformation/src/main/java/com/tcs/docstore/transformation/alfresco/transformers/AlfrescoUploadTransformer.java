/**
 * 
 */
package com.tcs.docstore.transformation.alfresco.transformers;

//import com.tcs.docstore.alfresco.asbo.request.GetAlfDepartmentRequestASBO;
import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.tcs.docstore.alfresco.asbo.request.GetListOfDepartmentsASBO;
import com.tcs.docstore.alfresco.asbo.request.GetListOfIssuedByRequestASBO;
import com.tcs.docstore.alfresco.asbo.response.GetDepartmentNameResponseASBO;
import com.tcs.docstore.alfresco.asbo.response.GetListOfIssuedByResponseASBO;
import com.tcs.docstore.asbo.emp.request.AlfrescoRequestASBO;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetDepartmentListAlfrescoDBRequestASBO;
import com.tcs.docstore.db.asbo.request.GetListOfIssuedByDBRequestASBO;
import com.tcs.docstore.db.asbo.response.GetDepartmentListDBResponseASBO;
import com.tcs.docstore.db.asbo.response.GetListOfIssuedByDBResponseASBO;
import com.tcs.docstore.vo.cmo.DocStoreRequestObject;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class AlfrescoUploadTransformer {
	
	private static final Logger LOGGER = Logger.getLogger(AlfrescoUploadTransformer.class);
	
	private AlfrescoRequestASBO alfrescorequestasbo;
	private DocUploadDBRequestASBO  audbrasbo;
	private GetListOfDepartmentsASBO getlistofdeapartment;
	private GetDepartmentListAlfrescoDBRequestASBO gadn;
	private GetDepartmentNameResponseASBO getdepartmentnamerspasbo;
	
	
public AlfrescoUploadTransformer(){
	this.alfrescorequestasbo = new AlfrescoRequestASBO();
	audbrasbo = new DocUploadDBRequestASBO();
	getlistofdeapartment = new GetListOfDepartmentsASBO();
	gadn = new GetDepartmentListAlfrescoDBRequestASBO();
	getdepartmentnamerspasbo = new GetDepartmentNameResponseASBO();
}

public DocStoreRequestObject transformUploadDocument(AlfrescoRequestASBO alfrescorequestasbo){
	
	transformUploadRequest(alfrescorequestasbo);
	
		return audbrasbo;
}

public DocUploadDBRequestASBO transformUploadRequest(AlfrescoRequestASBO alfrescorequestasbo){
	
	//DocUploadDBRequestASBO  audbrasbo = new DocUploadDBRequestASBO();
	
	audbrasbo.setConfidentialStatus(alfrescorequestasbo.getConfidentialStatus());
	audbrasbo.setDeptName(alfrescorequestasbo.getDeptName());
	audbrasbo.setDocDesc(alfrescorequestasbo.getDocDesc());
	audbrasbo.setDocPublishDate(alfrescorequestasbo.getDocPublishDate());
	audbrasbo.setDocType(alfrescorequestasbo.getDocType());
	audbrasbo.setDocUploadDate(alfrescorequestasbo.getDocUploadDate());
	audbrasbo.setFileName(alfrescorequestasbo.getFileName());
	audbrasbo.setFilePath(alfrescorequestasbo.getFilePath());
	audbrasbo.setIssuedBy(alfrescorequestasbo.getIssuedBy());
	
	audbrasbo.setHeader(alfrescorequestasbo.getHeader());
	
	LOGGER.debug("Alfresco audbrasbo--"+new Gson().toJson(audbrasbo));
	
	return audbrasbo;
	
}

public GetDepartmentListAlfrescoDBRequestASBO transformDepartmentName(GetListOfDepartmentsASBO requestASBO){
	
	gadn = transformDepartmentNames(requestASBO);
	LOGGER.debug("inside EmployeePortalRequestObject transformDepartmentName");
	return gadn;
	
}

public GetDepartmentListAlfrescoDBRequestASBO transformDepartmentNames(GetListOfDepartmentsASBO requestASBO){

	gadn.setHeader(requestASBO.getHeader());
	gadn.setListDepartments(requestASBO.getListDepartments());
	LOGGER.info("inside EmployeePortalRequestObject transformDepartmentNamesss***");
	return gadn;
	
}

public DocStoreResponseObject transformDepartmentNameList(GetDepartmentListDBResponseASBO requestASBO){
	LOGGER.debug("inside RESPONSE requestASBO***");
	
	transformfromdbDepartmentNames(requestASBO);
	
	return getdepartmentnamerspasbo;
	
}

public void transformfromdbDepartmentNames(GetDepartmentListDBResponseASBO requestASBO){
	
	for(int i=0;i<requestASBO.getGetDepartmentNameList().size();i++){
		
		LOGGER.debug("the transformfromdbDepartmentNames----->"+requestASBO.getGetDepartmentNameList().get(i));
		
	}
	java.util.Collections.sort(requestASBO.getGetDepartmentNameList());
	getdepartmentnamerspasbo.setGetDepartmentNameList(requestASBO.getGetDepartmentNameList());;
	
}

public GetListOfIssuedByDBRequestASBO transformIssuedByRequestName(
		GetListOfIssuedByRequestASBO requestASBO) {
	GetListOfIssuedByDBRequestASBO getListOfissueddbreqabo = new GetListOfIssuedByDBRequestASBO();
	getListOfissueddbreqabo.setHeader(requestASBO.getHeader());
	getListOfissueddbreqabo.setSelectIssue(requestASBO.getSelectIssue());
	// TODO Auto-generated method stub
	return getListOfissueddbreqabo;
}

public GetListOfIssuedByResponseASBO transformIssuedByList(
		GetListOfIssuedByDBResponseASBO getIssuedBydbResponseasbo) {
	GetListOfIssuedByResponseASBO glibyrespasbo = new GetListOfIssuedByResponseASBO();
	glibyrespasbo.setHeader(getIssuedBydbResponseasbo.getHeader());
	glibyrespasbo.setIssuedByList(getIssuedBydbResponseasbo.getIssuedByList());
	
	// TODO Auto-generated method stub
	return glibyrespasbo;
}


}
