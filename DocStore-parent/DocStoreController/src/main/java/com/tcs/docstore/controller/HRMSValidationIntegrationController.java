/**
 * 
 */
package com.tcs.docstore.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.apache.log4j.Logger;
import com.tcs.docstore.persist.service.GetDocUploadUserAccessService;
import com.tcs.docstore.persist.service.GetDocUploadUserAccessServiceImpl;

/**
 * @author 585226
 *
 */
public class HRMSValidationIntegrationController {
	private static final Logger LOGGER = Logger.getLogger(HRMSValidationIntegrationController.class);
	private GetDocUploadUserAccessService getuploaduseraccess;
	private String accessUpload ="N";
	
	public HRMSValidationIntegrationController (){
		getuploaduseraccess = new GetDocUploadUserAccessServiceImpl();
		
	}

	public String getValidateUploadUser(String group, String getDepartmentName) {
		// TODO Auto-generated method stub
		String str=group;
		String flag="fail";
		ArrayList<String> aList= new ArrayList<String>(Arrays.asList(str.split(",")));
		if(getDepartmentName.equalsIgnoreCase("ALL")){
			accessUpload= "Upload";
		}
		else{
		for(int i=0;i<aList.size();i++)
		{
		   LOGGER.info(" -->"+aList.get(i));
		    accessUpload = getuploaduseraccess.validateUploadOfUser(aList.get(i), getDepartmentName);
		   if(accessUpload.equalsIgnoreCase("Y")){
			  LOGGER.debug("the iternation number is --->"+i);
			   accessUpload= "Upload";
			   break;
			   
		   }
		}
		}
		LOGGER.info("the final print out is"+accessUpload);
		return accessUpload;
	}



	
	
}
