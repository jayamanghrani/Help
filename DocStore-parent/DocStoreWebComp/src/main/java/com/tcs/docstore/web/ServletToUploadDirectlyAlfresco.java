package com.tcs.docstore.web;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;

import com.tcs.docstore.component.integrator.AlfrescoUploadDocumentIntegrator;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.controller.HRMSValidationIntegrationController;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.exception.cmo.ErrorVO;
import com.tcs.docstore.util.MessageConstants;
import com.tcs.docstore.util.UtilConstants;
import com.tcs.docstore.util.ValidationUtil;

/**
 * Servlet implementation class ServletToUploadDirectlyAlfresco
 */
@WebServlet("/ServletToUploadDirectlyAlfresco")
public class ServletToUploadDirectlyAlfresco extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Session session1;
	private boolean isMultipart;
	private String filePath;
	private int maxFileSize =UtilConstants.MAX_FILE_SIZE; // The file more than 10 Mb will not be uploaded.
	private int maxMemSize = 40 * 1024;
	private File file ;
	String flag = null;
	DocUploadDBRequestASBO acudreq =new DocUploadDBRequestASBO();
	HashMap<String, String> uploadfilemap = new HashMap<String, String>();
	private static final Logger LOGGER = Logger.getLogger(ServletToUploadDirectlyAlfresco.class);
	private static final int ERR = 0;
	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ServletToUploadDirectlyAlfresco() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init( ){
		// Get the file location where it would be stored.
		filePath =UtilProperties.getFileUploadPath(); 

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		String ValidateHRMSFlag= null;
		String SuccessURL= UtilProperties.getSuccessUploadURL();
		String failURL = UtilProperties.getFailureUploadURL();
		String unAuthorisedURL = UtilProperties.getUnAuthorisedUploadURL();
		//added to redirect to validation error page
		String errorURL = UtilProperties.getValidationErrorURL();
		isMultipart = ServletFileUpload.isMultipartContent(request);

		if( !isMultipart ){
	
		}
		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Location to save data that is larger than maxMemSize.
		//    factory.setRepository(new File("c:\\temp"));
		factory.setRepository(new File(UtilProperties.getFileUploadPath()));

		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax( maxFileSize );
		
		String fileExtension =null;
		ErrorVO errorVO=new ErrorVO();
		String errorUploadURL=null;
		try{ 
			// Parse the request to get file items.
			List fileItems = upload.parseRequest(request);
			for(int i=0;i<fileItems.size();i++){

				LOGGER.info("the file items are "+fileItems.get(i).toString());
			}
			// Process the uploaded file items
			Iterator i = fileItems.iterator();

			while ( i.hasNext () ) 
			{
				FileItem fi = (FileItem)i.next();
				if ( !fi.isFormField () )	
				{
					// Get the uploaded file parameters
					String fieldName = fi.getFieldName();
					String fileName = fi.getName();
					fileExtension = fileName.substring(fileName.indexOf(".")+1, fileName.length());
					String fileName2=fileName.substring(fileName.lastIndexOf('\\')+1);
					String fileNameNew=fileName2.substring(0, fileName2.lastIndexOf('.'));
					acudreq.setFileName(fileName);
					String contentType = fi.getContentType();
					boolean isInMemory = fi.isInMemory();
					long sizeInBytes = fi.getSize();
					// Write the file
					String uploadFileName = null;
					if (fileName.lastIndexOf('\\') >= 0){
						uploadFileName = fileName.substring(fileName.lastIndexOf('\\')+1);
			            LOGGER.debug("inside the if statement ---->"+uploadFileName);
			            acudreq.setFileName(uploadFileName);
			            
					} else {
						uploadFileName = fileName;
			            LOGGER.debug("inside the ELSE statement ---->"+uploadFileName);
			          } 
					//file name validation
					errorVO=ValidationUtil.validateFields(uploadFileName, "File Name", true);
					if(errorVO.getErrorCode()!=0)
					{
						errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
						response.sendRedirect(errorUploadURL); 
						return;
					}
					 file = new File( filePath +   uploadFileName);
					fi.write( file ) ;
					acudreq.setFilePath(file.toString());
					LOGGER.debug("inside after IF ELSE statement ---->"+(filePath+file));
					LOGGER.debug("inside after IF ELSE file.toString() ---->"+file.toString());
				}

				else{
					String fieldName = fi.getFieldName();
					//    String fileName = fi.getName(); use this for only multipart values
					String valueField =fi.getString();
					LOGGER.debug("the filedname is "+i+"----"+fieldName+"----"+"****"+valueField);

					uploadfilemap.put(fieldName, valueField);
				}
			}
			//user input validation starts
			errorVO=ValidationUtil.validateFields(uploadfilemap.get("uploaddepartmentname"), "Department Name", true);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL); 
				return;
			}
			
			errorVO=ValidationUtil.validateFields(uploadfilemap.get("description"), "Description", true);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL);
				return;
			}
			
			if(!ValidationUtil.isNotNull(uploadfilemap.get("docpublishdate")))
			{
				errorUploadURL=MessageFormat.format(errorURL ,MessageFormat.format(MessageConstants.INPUT_REQUIRED_MESSAGE , "Document Publist Date"));
				response.sendRedirect(errorUploadURL); 
				return;
			}

			if(!ValidationUtil.isNotNull(uploadfilemap.get("docuploaddate")))
			{
				errorUploadURL=MessageFormat.format(errorURL , MessageFormat.format(MessageConstants.INPUT_REQUIRED_MESSAGE ,"Document Upload Date"));
				response.sendRedirect(errorUploadURL); 
				return;
			}
			
			errorVO=ValidationUtil.validateFields(uploadfilemap.get("uploaddocumenttype"), "Document Type", true);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL);
				return;
			}
			
			errorVO=ValidationUtil.validateFields(uploadfilemap.get("uploadissuedby"), "Issued By", true);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL);
				return;
			}
			
			errorVO=ValidationUtil.validateFields(uploadfilemap.get("uploaddepartmentnameVisibleTo"), "Visible to", true);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL);
				return;
			}
			
			acudreq.setConfidentialStatus(uploadfilemap.get("confidential"));
			/*if(!(acudreq.getConfidentialStatus().trim().equalsIgnoreCase("Yes")||acudreq.getConfidentialStatus().trim().equalsIgnoreCase("No")))
			{
				errorUploadURL=MessageFormat.format(errorURL , MessageFormat.format(MessageConstants.INPUT_REQUIRED_MESSAGE ,"document confidential status"));
				response.sendRedirect(errorUploadURL);
			}*/
			//user input validation ends
			acudreq.setDeptName(uploadfilemap.get("uploaddepartmentname"));
			acudreq.setDocDesc(uploadfilemap.get("description"));
			acudreq.setDocPublishDate(uploadfilemap.get("docpublishdate"));
			acudreq.setDocType(uploadfilemap.get("uploaddocumenttype"));
			acudreq.setDocUploadDate(uploadfilemap.get("docuploaddate"));
			acudreq.setIssuedBy(uploadfilemap.get("uploadissuedby"));
			acudreq.setVisibleTo(uploadfilemap.get("uploaddepartmentnameVisibleTo"));
			
			if(acudreq.getVisibleTo().equalsIgnoreCase("All")){
				acudreq.setConfidentialStatus("No");
			}

			LOGGER.debug(" the asbo is getConfidentialStatus " +acudreq.getConfidentialStatus());
			LOGGER.debug(" the asbo is getDeptName " +acudreq.getDeptName());
			LOGGER.debug(" the asbo is getDocDesc " +acudreq.getDocDesc());

			LOGGER.debug(" the asbo is getFileName " +acudreq.getFileName());
			LOGGER.debug(" the asbo is getFilePath " +acudreq.getFilePath());
			LOGGER.debug(" the asbo is visible to ############################################## " +acudreq.getVisibleTo());
			// Validating the users from HRMS/OAM
			String memberOf=null;
			String firstname=null;;
			String userid=null;
			String pattern1 = "cn=";
			String pattern2 = ",cn=groups";

			memberOf = request.getHeader("memberOf");
			firstname = request.getHeader("firstname");
			userid = request.getHeader("userid");
            
			//validates user header
			errorVO=ValidationUtil.validateUserHeader(userid, firstname, memberOf);
			if(errorVO.getErrorCode()!=0)
			{
				errorUploadURL=MessageFormat.format(errorURL , errorVO.getErrorMessage());
				response.sendRedirect(errorUploadURL);
				return;
			}
			LOGGER.debug("inside the HRMS Validation for Doc Upload  the memberof is --->"+memberOf);
			LOGGER.debug("inside the HRMS Validation for Doc Upload  the firstname is --->"+firstname);
			LOGGER.debug("inside the HRMS Validation for Doc Upload  the userid is --->"+userid);
			String result = memberOf.replaceAll("\\\\","");
			Pattern p = Pattern.compile(Pattern.quote(pattern1) + "(.*?)" + Pattern.quote(pattern2));
			Matcher m = p.matcher(result);
			String group="";
			while (m.find()) {
				LOGGER.debug(m.group(1));
				if(!(m.group(1).equalsIgnoreCase("employee")))
					group=group.concat(m.group(1)+",");
			}
			LOGGER.debug("group--"+group);
			LOGGER.debug("ServletToUploadDirectlyAlfresco Groups are -->"+group);

			HRMSValidationIntegrationController hrmsvaliintercontroller = new HRMSValidationIntegrationController();
			// Validating the users from HRMS Ends
			ValidateHRMSFlag="Upload";// changes need to be done here
			if(!ValidateHRMSFlag.equalsIgnoreCase("Upload")){
				
				LOGGER.debug("the failed url after HRMS VAlidation URL is -->"+unAuthorisedURL);
				response.sendRedirect(unAuthorisedURL); 
				return;
			}
			else{

				acudreq.setUserIDUploader(userid);
				// calling to alfresco begins 
				AlfrescoUploadDocumentIntegrator audi =new AlfrescoUploadDocumentIntegrator();
				session1=audi.createSession();
				LOGGER.debug("Inside the integration controller the session object is!"+session1.toString());
				LOGGER.debug("Inside the integration controller the session object is!"+session1.toString());
				flag=audi.createFaqContent(acudreq,session1,fileExtension);


				if(flag.equalsIgnoreCase("Success")){
					LOGGER.debug("insdide the success flag");
					response.sendRedirect(SuccessURL);
					return;
				}
				if(flag.equalsIgnoreCase("Failure")){
					response.sendRedirect(failURL); 
					return;
				}
			}

		}catch(Exception ex) {
			ex.printStackTrace();
			LOGGER.error("the file upload error stack trace"+ex.toString());
			
			LOGGER.error(ex.getMessage());
		}
	}

}
