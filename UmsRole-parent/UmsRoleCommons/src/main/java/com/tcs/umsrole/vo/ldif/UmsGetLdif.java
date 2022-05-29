package com.tcs.umsrole.vo.ldif;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;
import com.tcs.umsrole.vo.bean.LdapFileBean;



public class UmsGetLdif {

	private static final Logger LOGGER = Logger
			.getLogger(UmsGetLdif.class);
	
	long start = System.currentTimeMillis();
	String errorMessage = LdifUtil.FAILED;
	String errorCode = LdifUtil.ERROR;
	String status="checked";
    
	boolean checkstatus;
    
    
	public boolean getLdifFile(String userID) throws Exception {
		LOGGER.info("inside getLdifFile --- ");
		boolean result=false;
		boolean result_transfer=false;
		SelectLdapEntry selLdapEntry=new SelectLdapEntry();
		CreateLdapFile  createldapfile=new CreateLdapFile();
		
		
	  try{
	        LdapFileBean ldapbean=selLdapEntry.getData(userID);
	 	    LOGGER.info(" Value of ldapbean inside getLdifFile --- "+ldapbean);
	   
	 	    
	 	     if(null !=ldapbean){
	 	    LOGGER.info(" Value of ldapbean bean is not null inside getLdifFile --- ");
            File ldapFile=createldapfile.create(ldapbean,userID);
            LOGGER.info("Ldap File has been created  --- "+userID);
            
             result=ldapFile.canWrite();
            LOGGER.info("calling  ldifFile.canWrite() attrmap---"+result);
            LOGGER.info("Step 4 after ldif file calling transfer now");
            
            result_transfer=transferLdapFile(ldapFile);
             LOGGER.info("calling  transferLdapFile result_transfer value ---"+result_transfer);
            
             if(result_transfer=true){
            	 result=ldapFile.delete();
            	 LOGGER.info("calling  delete value result ---"+result);
             }
             else{
            	 result=false;
             }
           //  result=ldapFile.delete();
            // LOGGER.info("calling  delete file  attrmap---"+result);
	 	      }
	 	     else{
	 	    	result=false;
	 	       LOGGER.info(" Value of ldapbean bean is null inside getLdifFile --- ");
	 	     }
	    }
		  catch(NullPointerException e) 
	      { 
			  LOGGER.info("NullPointerException Caught for LdapFileBean in UmsGetLdif"); 
	      }
	 	     return result;
	 	
	}

	private boolean transferLdapFile(File ldapFile)  {
		 LOGGER.info(" Inside Ldap File transfer" );
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		String remoteDirPath=LdifUtil.LDAP_Target_PATH;
		JSch jsch = new JSch();
		String filename = ldapFile.getName();
		boolean result =true;
		try {
			session = jsch.getSession(LdifUtil.LDAP_Target_USERNAME,LdifUtil.LDAP_Target_HOSTNAME,Integer.parseInt(LdifUtil.LDAP_Target_PORT));
			session.setPassword(LdifUtil.LDAP_Target_PASSWORD);
			
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			 LOGGER.info("SFTP Channel Contected " );
			channelSftp = (ChannelSftp)channel;
			 LOGGER.info("Value of remoteDirPath---"+remoteDirPath );
			if(null != remoteDirPath)
				channelSftp.cd(remoteDirPath);
			else
				channelSftp.cd(remoteDirPath);
		
            
				channelSftp.put(new FileInputStream(ldapFile), filename);
			
            LOGGER.info("File transfered successfully to host--."+filename);
		
            result =true;
		} catch (NumberFormatException |JSchException |FileNotFoundException  |SftpException e) {
		    LOGGER.error("Error Found"+e.getStackTrace());
		    LOGGER.info("Error :" + e.getMessage());
			result =false;
		}

		
		return result;
	}


	}
	

	
	

