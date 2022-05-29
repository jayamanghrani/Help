package com.nia.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class SftpUtility {

	private static SftpUtility instance;
	protected static final Logger logger = Logger.getLogger( SftpUtility.class );
	protected static final Logger infoLogger = Logger.getLogger("infoLogger");
	public static synchronized SftpUtility getInstance()
	{
		if (instance == null)
		{
			instance = new SftpUtility();
		}
		return instance;
	}



	public SftpUtility()
	{

	}


	public boolean copyFileToRemoteDir(String sftpHost,int sftpPort, String sftpUser, String sftpPassword, 
			String localFilePath, String remoteDirPath, boolean deletefile, int retryCount,int retryAttemptsAllowed){
		return moveFileToDir(sftpHost, sftpPort,sftpUser,sftpPassword,localFilePath, remoteDirPath,  
				deletefile,retryCount,retryAttemptsAllowed);
	}

	public boolean moveFileToDir(String sftpHost,int sftpPort, String sftpUser, 
			String sftpPassword,String localFilePath, String remoteDirPath, boolean isDelete,
			int retryCount,int retryAttemptsAllowed){
		boolean returnResult = false;
		/*boolean deleteSuccess = false;*/
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		String files="";
		int fcount = 0;
		
		String remotepath="/NIA GSTR -1 FY 2020-21"+remoteDirPath;
		try {
			
			logger.info("Establishing Jsch session");
			JSch jsch = new JSch();

			session = jsch.getSession(sftpUser,sftpHost,sftpPort);

			session.setPassword(sftpPassword);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.connect();
			channel = session.openChannel("sftp");
			channel.connect();
			channelSftp = (ChannelSftp)channel;
			logger.info("SFTP session established....");
			
			

			if(null != remotepath)
				channelSftp.cd(remotepath);
			else
				channelSftp.cd(remotepath);

			/* Added by Pawan for Reading directory for all files on 14-05-2015 [Start] */
			File folder = new File(localFilePath);
			
			logger.info("folder.listFile"+folder.listFiles());
			int fileEntryCount = folder.listFiles().length;
			
			logger.info("fileEntryCount"+fileEntryCount);
			if (fileEntryCount == 0)
			{
				returnResult=true;
				logger.info("No files Present in the location - "+localFilePath+ " on NIA SFTP server. Hence no files transferred to CITI server.");
			}
			else {

				for (final File fileEntry : folder.listFiles()) 
				{
					
					logger.info("fileEntry.getname"+fileEntry.getName());
					
					if (fileEntry.isDirectory()) {
						
						logger.info("fileEntry is directory "+fileEntry.getName());


					} else {
						fcount = fcount+1;

						returnResult = false;
						//File f = new File(localFilePath+System.getProperty("file.separator")+fileEntry.getName());
						File f = new File(localFilePath+"/"+fileEntry.getName());
						String fileName = f.getName();
						logger.info("fileName---"+fileName);
						FileInputStream f_stream = new FileInputStream(f); 
						logger.info("113");
						long t1=System.currentTimeMillis();
						channelSftp.put(f_stream, fileName);
						logger.info("116");
						f_stream.close();
						long t2=System.currentTimeMillis();
						returnResult = true;
						double bytes = f.length();
						double kilobytes = (bytes / 1024); 

						SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");

						infoLogger.info(fileName+","+String.format("%.2f", kilobytes)+" kb,"+(t2-t1)/1000+" sec,"+dateFormat.format(new Date()));
						files=files+fileName+",";
					}
				}

			}





			/* Added by Pawan for Reading directory for all files 14-05-2015 [End] */

			if(fcount>0){
				files=files.substring(0,files.length()-1);
				logger.info("Total:"+fcount+" Files Copied to CDSL Server");
				logger.info("Files copied :- "+files);
			}


		} catch (JSchException e) {
			logger.error("JSchException ::",e);
			retryCount+=1;

			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath, isDelete, retryCount, retryAttemptsAllowed);
			}
		} catch (SftpException e) {
			logger.error("JSchException ::",e);
			retryCount+=1;

			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath, isDelete, retryCount, retryAttemptsAllowed);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(channel!=null)
				channel.disconnect();
			if(session!=null)
				session.disconnect();
		}

		return returnResult;
	}





}

