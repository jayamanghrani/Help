package com.nia.helper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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
			String localFilePath, String remoteDirPath, String remoteFileName, String targetFilePrefix,boolean deletefile,int retryCount,int retryAttemptsAllowed){
		return moveFileToDir(sftpHost, sftpPort,sftpUser,sftpPassword,localFilePath, remoteDirPath, 
				remoteFileName,targetFilePrefix, deletefile,retryCount,retryAttemptsAllowed);
	}

	public boolean moveFileToDir(String sftpHost,int sftpPort, String sftpUser, String sftpPassword,String localFilePath, 
			String remoteDirPath, String remoteFileName, String targetFilePrefix,boolean isDelete,int retryCount,int retryAttemptsAllowed){
		boolean returnResult = false;
		boolean deleteSuccess = false;
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;
		if(retryCount>0)
			logger.info("Retry attemt number::"+retryCount);
		try{
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
			if(null != remoteDirPath)
				channelSftp.cd(remoteDirPath);
			else
				channelSftp.cd(remoteDirPath);
			
			
			File f = new File(localFilePath);
			
			String fileName = targetFilePrefix+f.getName();
			if(null != remoteFileName && remoteFileName.length() > 0){
				f = new File(remoteFileName);
				fileName = targetFilePrefix+f.getName();
			}
			FileInputStream f_stream = new FileInputStream(f);
			channelSftp.put(f_stream, fileName);
			f_stream.close();

			
			if(isDelete){
				deleteSuccess = f.delete();
				
			}else{
				deleteSuccess = true;
			}
			returnResult = deleteSuccess;
		}catch (FileNotFoundException e) {
			logger.debug("File Not found at the location. "+System.getProperty("line.separator")+" Hence file not copied"+System.getProperty("line.separator"));
		} catch (SftpException e) {
			logger.debug("SftpException Cause::",e);
			retryCount+=1;

			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath, remoteFileName, targetFilePrefix, isDelete, retryCount, retryAttemptsAllowed);

			}
			else{
				returnResult=false;
			}
			
		} catch (JSchException e) {
			
			logger.debug("JSchException Cause::",e);
			retryCount+=1;

			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath, remoteFileName, targetFilePrefix, isDelete, retryCount, retryAttemptsAllowed);
			}
			else{
				returnResult=false;
			}
		} catch (IOException e) {
			logger.debug("Exception in reading file");
		}
		finally{
			//Disconnecting the channel
			if(channel!=null)
				channel.disconnect();
				if(session!=null)
				session.disconnect();
			
			
		}

		return returnResult;
	}

	
	
}

