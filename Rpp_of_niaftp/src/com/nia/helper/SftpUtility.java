package com.nia.helper;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
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
			String localFilePath, String remoteDirPath,String remoteBackupDirPath, String remoteFileName,boolean deletefile,int retryCount,int retryAttemptsAllowed){
		return moveFileToDir(sftpHost, sftpPort,sftpUser,sftpPassword,localFilePath, remoteDirPath,remoteBackupDirPath,
				remoteFileName, deletefile,retryCount,retryAttemptsAllowed);
	}
 
	public boolean moveFileToDir(String sftpHost,int sftpPort, String sftpUser, String sftpPassword,String localFilePath, 
			String remoteDirPath,String remoteBackupDirPath,String remoteFileName,boolean isDelete,int retryCount,int retryAttemptsAllowed){
		boolean returnResult = false;
		boolean backup_status=false;
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
			
			logger.info("localfilepath in SftpUtility Class::"+localFilePath);
			logger.info("remoteFileName in SftpUtility Class::"+remoteFileName);
		
			File folder = new File(localFilePath);
			
			String fileName =remoteFileName;
					
			String fpath=localFilePath+fileName;
			FileInputStream f_stream = new FileInputStream(fpath);
			channelSftp.put(f_stream, fileName);
			f_stream.close();
          
			 File file = new File(fpath);
			 logger.info("Value of isDelete in SFTPUtility::"+isDelete);
			  
			 
			
				
			if(!(isDelete)){
				 backup_status=plainPaymentFile_backup(sftpHost,sftpPort,sftpUser,sftpPassword,localFilePath,remoteBackupDirPath,
				remoteFileName,retryCount,retryAttemptsAllowed);
			     if(backup_status){
				deleteSuccess = file.delete();
				logger.info("remoteFileName has been deleted from remote Server in Sftputility class, in if Condition"+deleteSuccess);		
			     }
			     
			}else{
				deleteSuccess = true;
				logger.info("remoteFileName has been deleted from remote Server in Sftputility class, in else condition"+deleteSuccess);	
			}
			returnResult = deleteSuccess;
		
		}catch (FileNotFoundException e) {
			logger.debug("File Not found at the location. "+System.getProperty("line.separator")+" Hence file not copied"+System.getProperty("line.separator"));
		} catch (SftpException e) {
			logger.debug("SftpException Cause::",e);
			retryCount+=1;
			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath,remoteBackupDirPath, remoteFileName, isDelete, retryCount, retryAttemptsAllowed);

			}
			else{
				returnResult=false;
			}
			
		} catch (JSchException e) {
			
			logger.debug("JSchException Cause::",e);
			retryCount+=1;

		
			if(retryCount <= retryAttemptsAllowed){
				returnResult=moveFileToDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, 
						remoteDirPath,remoteBackupDirPath, remoteFileName, isDelete, retryCount, retryAttemptsAllowed);
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

	
	

	private boolean plainPaymentFile_backup (String sftpHost, int sftpPort,
			String sftpUser, String sftpPassword, String localFilePath,
			String remoteBackupDirPath,String remoteFileName, int backup_retryCount, int retryAttemptsAllowed) {
		   
		boolean remoteBackup_status=false;
		
		  Session     sessionObj     = null;
		  Channel     channelObj     = null;
		  ChannelSftp channelSftpObj = null;
		
		  if(backup_retryCount>0)
				logger.info("Retry attemt number in plainPaymentFile_backup ::"+backup_retryCount);
			try{
				JSch jsch = new JSch();
				sessionObj = jsch.getSession(sftpUser,sftpHost,sftpPort);
				sessionObj.setPassword(sftpPassword);
				java.util.Properties config = new java.util.Properties();
				config.put("StrictHostKeyChecking", "no");
				sessionObj.setConfig(config);
				sessionObj.connect();
				channelObj = sessionObj.openChannel("sftp");
				channelObj.connect();
				channelSftpObj = (ChannelSftp)channelObj;
				logger.info("remoteBackupDirPath in SftpUtility Class ::"+remoteBackupDirPath);
				if(null != remoteBackupDirPath)
					channelSftpObj.cd(remoteBackupDirPath);
				else
					channelSftpObj.cd(remoteBackupDirPath);
				
				logger.info("localfilepath for backup in SftpUtility Class::"+localFilePath);
				logger.info("remotebackupFileName for backup in SftpUtility Class::"+remoteFileName);
			
				File folder = new File(localFilePath);
				logger.info("local folder in SftpUtility Class::"+folder);
				String backupfileName =remoteFileName;
						
				String fbackupPath=localFilePath+backupfileName;
				FileInputStream f_bkpstream = new FileInputStream(fbackupPath);
				channelSftpObj.put(f_bkpstream, backupfileName);
				f_bkpstream.close();
	            
				remoteBackup_status=true;
				
			  }catch (FileNotFoundException e) {
				logger.debug("File Not found at the location. "+System.getProperty("line.separator")+" Hence file not copied"+System.getProperty("line.separator"));
			  } catch (SftpException e) {
				  logger.debug("SftpException Cause::",e);
				   backup_retryCount+=1;
					if(backup_retryCount <= retryAttemptsAllowed){
						remoteBackup_status=plainPaymentFile_backup(sftpHost,sftpPort,sftpUser,sftpPassword,localFilePath,remoteBackupDirPath,
								remoteFileName,backup_retryCount,retryAttemptsAllowed);
					}
					else{
						remoteBackup_status=false;
					}
				
			  }catch (JSchException e) {				
				logger.debug("JSchException Cause::",e);
				backup_retryCount+=1;
				if(backup_retryCount <= retryAttemptsAllowed){
					remoteBackup_status=plainPaymentFile_backup(sftpHost,sftpPort,sftpUser,sftpPassword,localFilePath,remoteBackupDirPath,
							remoteFileName,backup_retryCount,retryAttemptsAllowed);
				}
				else{
					remoteBackup_status=false;
				}
			  } catch (IOException e) {
				logger.debug("Exception in reading file");
			   }
				   finally{
						//Disconnecting the channel
						if(channelObj!=null)
							channelObj.disconnect();
							if(sessionObj!=null)
							 sessionObj.disconnect();
						
						
					}
		
		return remoteBackup_status;
	}



	public boolean callOutboundShFile(String bank, String merchantCode,
			String splited_payment_filename, String remitterAccountNumber,
			int SplittedRecordsNumber, String sftpHost, int sftpPort, String sftpUser,
			String tPassword,String remotePathdir,String shFilePath, int retryCount,
			int retryAttemptsAllowed) {
		  logger.info("inside callOutboundShFile method");
		  boolean returnResult = false;
		  SplittedRecordsNumber=SplittedRecordsNumber+1;
		      logger.info("SftpUtility : Entry callOutboundShFile method ");
			if(retryCount>0)
				logger.info("SftpUtility : callOutboundShFile : Retry attemt number :: "+retryCount);
			
			Session     session1     = null;
			ChannelExec channelExec=null;
			
			try{
				JSch jsch = new JSch();
				
				session1 = jsch.getSession(sftpUser,sftpHost,sftpPort);
				session1.setPassword(tPassword);
				java.util.Properties config = new java.util.Properties();
				

				config.put("StrictHostKeyChecking", "no");
				session1.setConfig(config);
				session1.connect();
				
				channelExec = (ChannelExec)session1.openChannel("exec");
				InputStream in = channelExec.getInputStream();
				logger.info("SftpUtility : callOutboundShFile method : Before SH run command == shFilePath == "+shFilePath);	
				logger.info("a[0] in string:bankcode " + bank);
				logger.info("a[1] in string:bankcode  " + merchantCode);
				logger.info("a[2] in string:remotePathdir  " + remotePathdir);
				logger.info("a[3] in string:splited_payment_filename  " + splited_payment_filename);
				logger.info("a[4] in string :bankcode " + remitterAccountNumber);	
				logger.info("a[5] split number:bankcode  " + SplittedRecordsNumber);
				channelExec.setCommand("cd /PNBSFTP/TestRPP/; java -cp RPP.jar com.tcs.rpp.service.UploadPaymentService "+bank+" "+merchantCode+" "+remotePathdir+" "+splited_payment_filename+" "+remitterAccountNumber+" "+String.valueOf(SplittedRecordsNumber)+";");				
				channelExec.setErrStream(System.err);
				
			
				
				
				
				logger.info("SftpUtility : callOutboundShFile method : After SH run command ");
				channelExec.connect();
				logger.info("session is connected" +channelExec.isConnected());
				
				BufferedReader reader = new BufferedReader(new InputStreamReader(in));
				logger.info("value of reader :"+reader);
				String line;
				while ((line = reader.readLine()) != null)
					         {   
					           logger.info("value of (line = reader.readLine()) != null):"+reader.readLine());
					         }

				returnResult=true;
				int exitStatus = channelExec.getExitStatus();
				logger.info("value of exitStatus :"+exitStatus);

			} catch (JSchException e) {
				
				logger.error("SftpUtility : callOutboundShFile :: JSchException occurred : "+e.getMessage());
				retryCount+=1;

				if(retryCount <= retryAttemptsAllowed){
					returnResult=callOutboundShFile(bank,merchantCode,splited_payment_filename,remitterAccountNumber,
							SplittedRecordsNumber,sftpHost,sftpPort,sftpUser,tPassword, remotePathdir,shFilePath,retryCount,retryAttemptsAllowed);
				}
				else{
					returnResult=false;
				}
			} 
			catch(Exception ex)
			{
				logger.error("SftpUtility : callOutboundShFile :: Exception Occurred : "+ex.getMessage());
				ex.printStackTrace();
			}
			finally{
				//Disconnecting the channel
				if(channelExec!=null)
					channelExec.disconnect();
					if(session1!=null)
					session1.disconnect();
			}
			
			 logger.info("SftpUtility : callOutboundShFile :: Exit callOutboundShFile method ");
			return returnResult;
		
	}

	
	
}

