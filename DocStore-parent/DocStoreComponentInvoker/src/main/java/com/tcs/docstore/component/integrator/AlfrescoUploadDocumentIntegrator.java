/**
 * 
 */
package com.tcs.docstore.component.integrator;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.CmisObject;
import org.apache.chemistry.opencmis.client.api.Document;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.ContentStream;
import org.apache.chemistry.opencmis.commons.enums.BindingType;
import org.apache.chemistry.opencmis.commons.enums.VersioningState;
import org.apache.chemistry.opencmis.commons.impl.dataobjects.ContentStreamImpl;
import org.apache.log4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.StopWatch;
import org.springframework.web.client.RestTemplate;

import com.tcs.docstore.cache.util.CacheConstants;
import com.tcs.docstore.config.utils.UtilProperties;
import com.tcs.docstore.db.asbo.request.DocUploadDBRequestASBO;
import com.tcs.docstore.exception.cmo.IntegrationTechnicalException;
import com.tcs.docstore.persist.dao.GetSendDocMailDetailsDao;
import com.tcs.docstore.persist.dao.GetSendDocMailDetailsDaoImpl;
import com.tcs.docstore.vo.cmo.DocStoreResponseObject;

/**
 * @author 585226
 *
 */
public class AlfrescoUploadDocumentIntegrator extends DocStoreResponseObject{

	String flag=null;
	boolean confidentailFlag=false;
	private static final Logger LOGGER = Logger.getLogger(AlfrescoUploadDocumentIntegrator.class);


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DocStoreResponseObject uploadDocumentAlf(DocUploadDBRequestASBO acudreq){

		return null;
	}



	//	Session session;
	// This session was previously static
	public    Session createSession() {

		LOGGER.info("Step 1 in the class Session createSession()");
		String alfrescoUrl =UtilProperties.getAlfrescoUploadPath();
		Map<String, String> sessionMap = new HashMap<String, String>();
		sessionMap.put(SessionParameter.USER,UtilProperties.getAlfrescoAdmin());
		sessionMap.put(SessionParameter.PASSWORD,UtilProperties.getAlfrescoPwd());
		sessionMap.put(SessionParameter.ATOMPUB_URL, alfrescoUrl);
		sessionMap.put(SessionParameter.BINDING_TYPE,BindingType.ATOMPUB.value());
		LOGGER.debug("Step 2  in the class Session createSession()");
		sessionMap.put(SessionParameter.OBJECT_FACTORY_CLASS,"org.alfresco.cmis.client.impl.AlfrescoObjectFactoryImpl");
		LOGGER.debug("Step 3  in the class Session createSession()");
		SessionFactory factory = SessionFactoryImpl.newInstance();
		LOGGER.debug("Step 4  in the class Session createSession()");
		Session session = factory.getRepositories(sessionMap).get(0).createSession();
		LOGGER.debug("Step 5  in the class Session createSession() and the session object is"+session.toString());
		LOGGER.info("Connected to repository:" + session.getRepositoryInfo().getName());
		//OperationContext ctx = session.createOperationContext();
		//ctx.setCacheEnabled(false);
		//session.setDefaultContext(ctx);       
		return session;
	}

	// this method was intially static 
	public  String createFaqContent(DocUploadDBRequestASBO acudreq,Session sessionObject,String fileExtension) {
		Date uploadDate =null ;
		Date  publishDate=null;

		LOGGER.info("AlfrescoUploadDocumentIntegrator getConfidentialStatus-->"+acudreq.getConfidentialStatus());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getDeptName-->"+acudreq.getDeptName());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getDocDesc-->"+acudreq.getDocDesc());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getDocPublishDate-->"+acudreq.getDocPublishDate());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getDocType-->"+acudreq.getDocType());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getDocUploadDate-->"+acudreq.getDocUploadDate());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getFileName-->"+acudreq.getFileName());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getFilePath-->"+acudreq.getFilePath());
		LOGGER.info("AlfrescoUploadDocumentIntegrator getIssuedBy-->"+acudreq.getIssuedBy());
		LOGGER.info("AlfrescoUploadDocumentIntegrator get Visible to -->"+acudreq.getVisibleTo());
		
		LOGGER.info("AlfrescoUploadDocumentIntegrator the File Extension is -->"+fileExtension);

		//CmisObject parentFolder = session.getObject(parentFolderId);
		if(sessionObject.equals(null)){
			LOGGER.info("the session object obtained is null");
		}
		else {
			LOGGER.info("the session is nt null");
		}

		// String path = "workspace://SpacesStore/36c4fcd3-16a3-4574-8393-455c7f009aae"
		LOGGER.info("the session obtained is"+sessionObject);  // 
		LOGGER.info("the doc path in alfrsco is -->"+UtilProperties.getAlfrescoSitePath());
		CmisObject object = sessionObject.getObjectByPath(UtilProperties.getAlfrescoSitePath());
		String parentFolderId=object.getId().toString();
		LOGGER.info("the parent folder is --->"+parentFolderId);
		// parentFolderId = "workspace://SpacesStore/6caaa5b3-ce90-4dc4-a13a-78eb8d87bc94";

		Folder parentFolder = (Folder) sessionObject.getObject(parentFolderId);
		//String contentString = "EmpDoc3";
		//   String name = "EmpDocTesting4.txt";
		String name = acudreq.getFileName();
		String mimeType="application/pdf";
		if(fileExtension.equalsIgnoreCase("pdf")){
		 mimeType = "application/pdf";
		}
		
		if(fileExtension.equalsIgnoreCase("tif") || fileExtension.equalsIgnoreCase("tiff")){
			 mimeType = "application/tiff";
		}
		
		if(fileExtension.equalsIgnoreCase("xlx")){
			 mimeType = "application/excel";
			}
		
		if(fileExtension.equalsIgnoreCase("xlsx")){
			 mimeType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
			}
		
		if(fileExtension.equalsIgnoreCase("doc")){
			 mimeType = "application/msword";
			}
		
		if(fileExtension.equalsIgnoreCase("docx")){
			 mimeType = "application/vnd.openxmlformats-officedocument.wordprocessingml.document";
			}
		
		if(fileExtension.equalsIgnoreCase("ppt")){
			 mimeType = "application/mspowerpoint";
			}
		
		if(fileExtension.equalsIgnoreCase("pptx")){
			 mimeType = "application/vnd.openxmlformats-officedocument.presentationml.presentation";
			}
		
		if(fileExtension.equalsIgnoreCase("jpeg")){
			 mimeType = "image/jpeg";
			}
		
		if(fileExtension.equalsIgnoreCase("jpg")){
			 mimeType = "image/jpeg";
			}
		if(fileExtension.equalsIgnoreCase("odt")){
			 mimeType = "application/vnd.oasis.opendocument.text";
			}
		if(fileExtension.equalsIgnoreCase("ods")){
			 mimeType = "application/vnd.oasis.opendocument.spreadsheet";
			}
		if(fileExtension.equalsIgnoreCase("odf")){
			 mimeType = "application/vnd.oasis.opendocument.formula";
			}
		if(fileExtension.equalsIgnoreCase("odp")){
			 mimeType = "application/vnd.oasis.opendocument.presentation";
			}
		LOGGER.info("AlfrescoUploadDocumentIntegrator the MIME tYPE Extension is -->"+mimeType);
		Map<String, Object> properties = new HashMap<String, Object>();

		/*	@SuppressWarnings("deprecation")
		Date d = new Date(acudreq.getDocUploadDate());
		Date dissueDate = new Date(acudreq.getDocPublishDate());*/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		String dupload= acudreq.getDocUploadDate();
		String dpublish=acudreq.getDocPublishDate();
		try {
			 uploadDate = sdf.parse(dupload);
			  publishDate =sdf.parse(dpublish);
			  LOGGER.info("the uploadDate is --->"+uploadDate);
			  LOGGER.info("the publishDate is --->"+publishDate);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			LOGGER.info(e1.getStackTrace());
		}
		
		
		Calendar now = Calendar.getInstance();   // Gets the current date and time
		int year = now.get(Calendar.YEAR);      // The current year as an int
		if(acudreq.getConfidentialStatus().equalsIgnoreCase("Yes")){
			confidentailFlag=true;
		}
		//properties.put(PropertyIds.OBJECT_TYPE_ID,"D:TINS:documentEmployee,P:TINS:displayName,P:TINS:deptName,P:TINS:docType,P:TINS:prodList,P:TINS:issuedBy,P:TINS:empKeyWord,P:TINS:deploymentDate,P:TINS:confidential,P:TINS:userId,");
		properties.put(PropertyIds.OBJECT_TYPE_ID,"D:TINS:documentEmployee,P:TINS:empKeyWord,P:TINS:displayName,P:TINS:confidential,P:TINS:userId,P:TINS:issuedBy,P:TINS:docType,P:TINS:prodList,P:TINS:deptName,");
		properties.put(PropertyIds.NAME, acudreq.getFileName());
		properties.put("TINS:displayName", acudreq.getFileName());
		properties.put("TINS:issueDate",publishDate );
		/*properties.put("TINS:signOffDate",acudreq.getDocUploadDate() );
		properties.put("TINS:deploymentDate",acudreq.getDocUploadDate() );*/ // commented as they were not gregorian calender dates
		properties.put("TINS:signOffDate",uploadDate );
		properties.put("TINS:deploymentDate",uploadDate );
		properties.put("TINS:year",year );
		properties.put("TINS:contentType", "EmployeeDocument");	
		properties.put("TINS:deptName", acudreq.getDeptName());
		properties.put("TINS:docType", acudreq.getDocType());
		properties.put("TINS:prodList", "EmpDocTesting4");
		properties.put("TINS:issuedBy", acudreq.getIssuedBy());
		properties.put("TINS:empKeyWord", acudreq.getDocDesc()); // KeyWords who kept them!!!!
		properties.put("TINS:userId", acudreq.getUserIDUploader());  // UserID needed for the upload ??
		properties.put("TINS:confidential",confidentailFlag);     
		properties.put("TINS:visibleTo",acudreq.getVisibleTo());

		// content
		try {
			//	File f= new File("C:\\Users\\585226\\Desktop\\hck.txt");
			File f= new File(acudreq.getFilePath());
			InputStream stream = new FileInputStream(f); 
			//byte[] content = contentString.getBytes();
			//InputStream stream = new ByteArrayInputStream(content);
			ContentStream contentStream =
					new ContentStreamImpl(name, BigInteger.valueOf(f.length()), mimeType, stream);
			LOGGER.info("the content stream is--->"+contentStream);
			Document Doc = parentFolder.createDocument(properties, contentStream, VersioningState.MAJOR);

			LOGGER.info("Content Uploaded:"+Doc.getId());
	//		Thread.sleep(20000); 
			flag="Success";
	//		invokeAlfrescoCacheClearingService("http://10.10.3.250:7008/alfresco/s/nia/cacheClearanceForEmployeeDocument"); PropertiesUtil.getConfigProperty("CacheClearingURL")
			
			Thread t1=new Thread(new Runnable() {
			     public void run() {
			    	 try {
			    		 int sleepTime=Integer.parseInt(UtilProperties.getAlfrescoCallTime());
						Thread.sleep(sleepTime);
					} catch (InterruptedException e) {
						
						e.printStackTrace();
						LOGGER.error(e.getStackTrace());
					}
			    
			    	 String CacheClearingURL=UtilProperties.getCacheClearingURL();
			    	 try {
						invokeAlfrescoCacheClearingService(CacheClearingURL);
						LOGGER.info("Alfresco cache call done");
					} catch (Exception e) {
					
						e.printStackTrace();
						LOGGER.error(e.getStackTrace());
					}
			     }
			});  
			t1.start();
			
			if(flag.equalsIgnoreCase("success")){
				
				GetSendDocMailDetailsDao gsdmdd = new GetSendDocMailDetailsDaoImpl();
				List acessDetails = new ArrayList<String>();
				acessDetails=	gsdmdd.sendDocumentUpplodMailDetails(acudreq.getUserIDUploader());
				DocumentEmailIntegrator di = new DocumentEmailIntegrator();
				
				String str = (String) acessDetails.get(0);
				ArrayList<String> aList= new ArrayList<String>(Arrays.asList(str.split(",")));
				LOGGER.debug("the array list size is--->"+aList.size());
				
				
				if(acessDetails.get(1).equals("Y") && (acudreq.getDocType().equalsIgnoreCase("IRDA Communications") || acudreq.getDocType().equalsIgnoreCase("Exposure Drafts"))){
					LOGGER.debug("Mail can be sent to this user");
					for(int i=0;i<aList.size();i++)
					{
						LOGGER.info(" -->"+aList.get(i));
						di.DocEmailInvoker(aList.get(i),acudreq);
					}
					
				}
				else{
					LOGGER.info("not valid user to send mail ");
				}
				
				
				
			}
			return flag;


		} catch (Exception e) {
			flag="Failure";
			e.printStackTrace();
		}
		return flag;
	}



	private static void invokeAlfrescoCacheClearingService(String myURL) throws Exception {
		LOGGER.info("inside the cache creating service calling mechainsm");
		URL obj = new URL(myURL);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		//	con.setRequestProperty("User-Agent", USER_AGENT);

		int responseCode = con.getResponseCode();
		LOGGER.info("\nSending 'GET' request to URL : " + myURL);
		LOGGER.info("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
				new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		//print result
		LOGGER.info(response.toString());
	}

	
	public String invokeClient(String json)
			throws IntegrationTechnicalException {
		String errorMsg = null;
		String URL = CacheConstants.ALFRESCO_URL;
		RestTemplate restTemplate = new RestTemplate();
		String result = null;
		LOGGER.info("invoking alfresco service...");
		LOGGER.info("URL:" + URL);
		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();

		try {
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);
			messageConverters.add(new StringHttpMessageConverter());
			messageConverters.add(new MappingJackson2HttpMessageConverter());
			restTemplate.setMessageConverters(messageConverters);
			HttpEntity<Object> entity = new HttpEntity<Object>(json, headers);
			HttpComponentsClientHttpRequestFactory rf = new HttpComponentsClientHttpRequestFactory();
			restTemplate.setRequestFactory(rf);
			StopWatch stopWatch = new StopWatch();
			stopWatch.start();
			result = restTemplate.postForObject(URL, entity, String.class);
			stopWatch.stop();
			LOGGER.debug(" CACHE OUTPUT :: " + result);
	
		} catch (Exception e) {
			LOGGER.error("alfresco invocation failed", e);
			e.printStackTrace();
			throw new IntegrationTechnicalException(e);
		}

		return result;
	}

}
