package com.tcs.umsrole.vo.ldif;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.log4j.Logger;

import com.tcs.umsrole.vo.bean.LdapFileBean;


public class CreateLdapFile {

	private static final Logger LOGGER = Logger
			.getLogger(CreateLdapFile.class);

	public File create(LdapFileBean ldapbean, String userID) {
		LOGGER.info("inside CreateLdapFile---- --- ");
		String srNo=userID; 
		File outputFile = 
                new File((new StringBuilder()).append("output_oid_").append(srNo).append(".ldif").toString());
		 FileWriter writer;
		try {
			LOGGER.info("File name ---- --- "+outputFile);
			  writer = new FileWriter(outputFile, true);
			   BufferedWriter bufferedWriter = new BufferedWriter(writer);
			   
			   bufferedWriter.write("dn:"); 
			   bufferedWriter.write(ldapbean.getDc());       
	           bufferedWriter.newLine();
	           
	           bufferedWriter.write("givenname:"); 
			   bufferedWriter.write(ldapbean.getGivenname());       
	           bufferedWriter.newLine();
	            
	           bufferedWriter.write("orclactivestartdate:"); 
			   bufferedWriter.write(ldapbean.getOrclactivestartdate());       
	           bufferedWriter.newLine();
			   
	           bufferedWriter.write("modifytimestamp:"); 
			   bufferedWriter.write(ldapbean.getModifytimestamp());       
	           bufferedWriter.newLine();
			   
	           for(String objectclassString: ldapbean.getObjectClass()){
	        	   LOGGER.info("objectclassString ---- --- "+objectclassString);
	        	   bufferedWriter.write("objectclass:"); 
				   bufferedWriter.write(objectclassString);       
		           bufferedWriter.newLine();
	           }
	           for(String authpasswordoidString:ldapbean.getAuthpassword_oid())
	           {
	        	   LOGGER.info("authpasswordoidString ---- --- "+authpasswordoidString);
		           bufferedWriter.write("authpassword;oid:"); 
				   bufferedWriter.write(authpasswordoidString);       
		           bufferedWriter.newLine();
		         }
	             for(String authpasswordorclcommonpwdString:ldapbean.getAuthpassword_orclcommonpwd())
	              {
	            LOGGER.info("authpasswordorclcommonpwdString ---- --- "+authpasswordorclcommonpwdString);
		        bufferedWriter.write("authpassword;orclcommonpwd:"); 
				bufferedWriter.write(authpasswordorclcommonpwdString);       
		        bufferedWriter.newLine();
		           }	             
	            bufferedWriter.write("userpassword:"); 
				bufferedWriter.write(ldapbean.getUserpassword());       
		        bufferedWriter.newLine();
		           
		        bufferedWriter.write("createtimestamp:"); 
				bufferedWriter.write(ldapbean.getCreatetimestamp());       
		        bufferedWriter.newLine();
			    
		        bufferedWriter.write("ou:"); 
				bufferedWriter.write(ldapbean.getOu());       
		        bufferedWriter.newLine();
		        bufferedWriter.write("uid:"); 
				bufferedWriter.write(ldapbean.getUid());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("cn:"); 
				bufferedWriter.write(ldapbean.getCn());       
		        bufferedWriter.newLine();
		        
		       bufferedWriter.write("orclpassword:"); 
				bufferedWriter.write(ldapbean.getOrclpassword());       
		        bufferedWriter.newLine();
		        

		        bufferedWriter.write("pwdchangedtime:"); 
				bufferedWriter.write(ldapbean.getPwdchangedtime());       
		        bufferedWriter.newLine();
		        

		        bufferedWriter.write("orclguid:"); 
				bufferedWriter.write(ldapbean.getOrclguid());       
		        bufferedWriter.newLine();
		        

		        bufferedWriter.write("modifiersname:"); 
				bufferedWriter.write(ldapbean.getModifiersname());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("creatorsname:"); 
				bufferedWriter.write(ldapbean.getCreatorsname());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("orclnormdn:"); 
				bufferedWriter.write(ldapbean.getOrclnormdn());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("obpasswordchangeflag:"); 
				bufferedWriter.write(ldapbean.getObpasswordchangeflag());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("sn:"); 
				bufferedWriter.write(ldapbean.getSn());       
		        bufferedWriter.newLine();
		        
		        bufferedWriter.write("oblogintrycount:"); 
				bufferedWriter.write(ldapbean.getOblogintrycount());       
		        bufferedWriter.newLine();
			
		        bufferedWriter.write("orcldefaultprofilegroup:"); 
		    	bufferedWriter.write(ldapbean.getOrcldefaultprofilegroup());       
		    	bufferedWriter.newLine();
		    	
		    	 bufferedWriter.close();
		    	 writer.close();
		    		        
		} catch (IOException e) {
			LOGGER.error(e.getStackTrace(),e);
		}
		return outputFile;
	}
	
}
