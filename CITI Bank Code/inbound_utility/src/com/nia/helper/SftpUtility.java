package com.nia.helper;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpATTRS;
import com.jcraft.jsch.SftpException;
import com.nia.jaxb.FIdentifier;
import com.nia.jpa.exception.NoFileFoundException;

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



	/* Download File from Sftp Server */



	public boolean getFileFromRemoteDir(String sftpHost,int sftpPort, String sftpUser, String sftpPassword,
			String localFilePath, String remoteDirPath, boolean delete_remote_file, 
			List<FIdentifier> sprefix,int retryCount,int retryAttemptsAllowed) throws NoFileFoundException 
			{

		if(retryCount>0)
			logger.info("Retry attemt number::"+retryCount);
		boolean returnResult = false;
		//boolean deleteSuccess = false;
		Session     session     = null;
		Channel     channel     = null;
		ChannelSftp channelSftp = null;

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
			int count=0;
			//SftpATTRS ftpattr = channelSftp.lstat(remoteDirPath);

			Vector<LsEntry> dir_list = channelSftp.ls(remoteDirPath);
			String files="";
			if (dir_list.size()> 0)
			{   


				for (LsEntry fileEntry : dir_list)
				{
					//System.out.println("File Name "+fileEntry.getFilename());

					//Check if Remote directory file prefix is match with condition 

					for (FIdentifier fidentifier: sprefix)
					{
						if(fileEntry.getFilename().startsWith(fidentifier.getFPrefix()))
						{
							String fileName=fileEntry.getFilename();
							logger.info("Downloading file::"+fileName);
							long t1=System.currentTimeMillis();
							channelSftp.get(fileName , localFilePath);  
							long t2=System.currentTimeMillis();
							files=files+fileName+",";

							count++;
							SftpATTRS attrs=fileEntry.getAttrs();
							double bytes =attrs.getSize();
							double kilobytes = (bytes / 1024);
							FileProp fp=new FileProp();
							fp.setFileName(fileName);
							fp.setFileSize(String.format("%.2f", kilobytes)+" kb");
							SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
							fp.setDate(dateFormat.format(new Date()));
							fp.setTimeTaken((t2-t1)/1000 +" sec");
							Constants.downloadedFiles.add(fp);
						}
					} 



					returnResult = true;   
				}

				/* files=dir_list.get(0).getFilename();
				   channelSftp.get(dir_list.get(0).getFilename() , localFilePath); 
				   returnResult = true;*/

			}
			else {
				returnResult = false;
				logger.info("No files found in directory " +remoteDirPath);
				throw new NoFileFoundException("No files are found in the directory "+remoteDirPath+" on CITI server."
						+System.getProperty("line.separator")+" Hence no files are downloaded from CITI server. " 
						+System.getProperty("line.separator")+System.getProperty("line.separator"));

			}

			if(count==0){
				returnResult = false;
				logger.info("No file with the required prefix found in directory "+remoteDirPath);
				throw new NoFileFoundException("No files are found in the directory "+remoteDirPath+" on CITI server. "
						+System.getProperty("line.separator")+ " Hence no files are downloaded from CITI server. "
						+System.getProperty("line.separator"));
			}

			logger.info(count +" Files:: "+files + " :: have been copied from Citi server");

		}
		catch(JSchException e){
			logger.error("JSchException Cause::",e);
			retryCount+=1;

			if(retryCount <= retryAttemptsAllowed){
				returnResult=getFileFromRemoteDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, remoteDirPath,
						delete_remote_file, sprefix, retryCount,retryAttemptsAllowed);

			}
			else{
				returnResult=false;
			}


		} catch (SftpException e) {


			logger.error("SFTPException Cause::",e);
			returnResult=false;
			retryCount+=1;
			e.printStackTrace();
			if(retryCount <= retryAttemptsAllowed){
				returnResult=getFileFromRemoteDir(sftpHost, sftpPort, sftpUser, sftpPassword, localFilePath, remoteDirPath,
						delete_remote_file, sprefix, retryCount,retryAttemptsAllowed);

			}
			else{
				returnResult=false;
			}

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

