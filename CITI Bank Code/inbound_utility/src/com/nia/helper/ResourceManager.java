
package com.nia.helper;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import com.hp.gagawa.java.Document;
import com.hp.gagawa.java.DocumentType;
import com.hp.gagawa.java.elements.Br;
import com.hp.gagawa.java.elements.Table;
import com.hp.gagawa.java.elements.Td;
import com.hp.gagawa.java.elements.Text;
import com.hp.gagawa.java.elements.Tr;
import com.nia.jaxb.Config;
import com.nia.jaxb.EmailProp;
import com.nia.jaxb.FilecopyProperty;
import com.nia.jaxb.FiledecryptProp;
import com.nia.jpa.exception.FilecopytoRemote_Exception;
import com.nia.jpa.exception.Mail_Exception;
import com.nia.jpa.exception.NoFileFoundException;
import com.nia.jpa.exception.UnixCmd_Exception;



public class ResourceManager
{

	protected static final Logger logger = Logger.getLogger( ResourceManager.class );
	/* Read Config File and Create Database Connection */

	Config logConfig;
	FiledecryptProp fdecrypt_property;
	EmailProp femail_prop;
	List<FilecopyProperty> fcopy_property;
	Properties props = new Properties();
	InputStream input = null;
	StringBuilder sb;
	String target_filePath;
	String Source_filePath;
	String Source_serverip;
	int Source_port;
	String Source_userid;
	String Source_passwd;

	/* Email Configuration  */


	Connection con;
	private static ResourceManager instance;


	private void LoadProperty() throws JAXBException
	{

		logConfig = com.nia.helper.PropertyLoader.getInstance().getLogConfiguration();

		fcopy_property = logConfig.getFcopyProp();
		fdecrypt_property=logConfig.getFdecryptProp();
		femail_prop=logConfig.getEmailProp();
		sb=new StringBuilder();
		Constants.signature_constant=femail_prop.getMessageSignature();
	}


	public ResourceManager() throws JAXBException 
	{

		LoadProperty();

	}





	public Config getLogConfig() {
		return logConfig;
	}


	public void setLogConfig(Config logConfig) {
		this.logConfig = logConfig;
	}


	public static synchronized ResourceManager getInstance() throws JAXBException
	{
		if (instance == null)
		{
			instance = new ResourceManager();
		}
		return instance;
	}






	/* Copy Local file From Remote FTP Server directory */
	public boolean copyFilesfromRemoteDir(int retryCount) throws FilecopytoRemote_Exception,  NoFileFoundException
	{
		boolean result = false;

		for (FilecopyProperty fcopyprop : fcopy_property )
		{

			result = 	SftpUtility.getInstance().getFileFromRemoteDir(fcopyprop.getFcopySource().getSIp(), fcopyprop.getFcopySource().getSPort(),
					fcopyprop.getFcopySource().getSUsername(), fcopyprop.getFcopySource().getSPassword(),
					fcopyprop.getFcopyTarget().getTPath(), fcopyprop.getFcopySource().getSPath(), 
					fcopyprop.getFcopySource().isDelSourceFile(),fcopyprop.getFcopySource().getSPrefix(),retryCount,fcopyprop.getFcopySource().getRetryAttempts());

			if(!result)
			{
				throw new FilecopytoRemote_Exception("File transfer from CITI Server to Location:"
						+fcopyprop.getFcopyTarget().getTPath()+" on NIA SFTP server failed. "  +System.getProperty("line.separator")
						+"Please check the log file for more details on error."
						+System.getProperty("line.separator")+System.getProperty("line.separator"));
			}

		}


		return result;
	}


	public boolean backupDownloadedFile() throws UnixCmd_Exception {
		boolean result = false;


		String cmd ="mv "+ fdecrypt_property.getEncryptedFilePath() +"* " 
				+ fdecrypt_property.getEncryptedFileBackupPath(); 
		result=UnixCommUtility.getInstance().backupFile(cmd);

		if(!result){
			throw new UnixCmd_Exception("Error in moving File from "+ fdecrypt_property.getPlainFilePath()
					+ " to " + fdecrypt_property.getPlainFileBackupPath() +"on NIA SFTP server. "
					+System.getProperty("line.separator")+System.getProperty("line.separator"));
		}

		return result;
	}


	public boolean decryptFiles() throws UnixCmd_Exception   {
		boolean result = false;

		
		String cmd = fdecrypt_property.getShFilePath() + "decryptandverify.sh"; 
		result=UnixCommUtility.getInstance().decryptFiles(cmd);
		if(!result){
			throw new UnixCmd_Exception("Error in Decrypting the File from "+fdecrypt_property.getShFilePath()+" on NIA SFTP server. "
					+System.getProperty("line.separator")+"Please check the log file for more details on error."
							+System.getProperty("line.separator")+System.getProperty("line.separator"));

		}
		return result;
	}

	public boolean backupDecryptedFile() throws UnixCmd_Exception {
		boolean result = false;


		String cmd ="mv "+ fdecrypt_property.getPlainFilePath() +"* " 
				+ fdecrypt_property.getPlainFileBackupPath(); 
		result=UnixCommUtility.getInstance().backupFile(cmd);

		if(!result){
			throw new UnixCmd_Exception("Error in Backing Up Encrypted File from " 
					+ fdecrypt_property.getEncryptedFilePath()	+ " to " 
					+ fdecrypt_property.getEncryptedFileBackupPath() +System.getProperty("line.separator")+
					"Please check the log file for more details on error."
					+System.getProperty("line.separator")+System.getProperty("line.separator"));
		}

		return result;
	}


	public  boolean sendMail() throws Mail_Exception{
		boolean result=false;
		for (FilecopyProperty fcopyprop : fcopy_property )
		{
			Email_Sender emailSender=new Email_Sender(); 
			result=emailSender.sendEmailWithAttachments(femail_prop.getSenderEmailAdddress(), 
					femail_prop.getMailFrom(), femail_prop.getPassword(), femail_prop.getErrorMailReceipentList(), 
					femail_prop.getErrorSubject(), femail_prop.getSmtpHost(), femail_prop.getSmtpPort(), 
					femail_prop.isSmtpAuth(), femail_prop.isSmtpSslEnable(), 
					fdecrypt_property.getPlainFilePath(), fcopyprop.getFcopySource().getSPrefix() ,
					femail_prop.getMessageBody());



		}
		if(!result){

			throw new Mail_Exception("Mail not sent ");


		}

		return result;
	}


	public  boolean sendMailOnSuccess() throws Mail_Exception, IOException{
		boolean result=false;

		Map<String, List<FileProp>> map=new HashMap<String, List<FileProp>>();
		map.put("Downloads", Constants.downloadedFiles);

		Calendar cal = Calendar.getInstance();	     
		SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat dmyFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String logFile=fdecrypt_property.getInputFilePath();
		FileInputStream fstream = new FileInputStream(logFile);
		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
		String strLine;
		String downloadText="Files downloaded from CITI on "+ dmyFormat.format(cal.getTime()) +" are as follows:-";
		System.out.println(cal.get(Calendar.DAY_OF_WEEK));
		
		
		/***** If Day is monday, then outbound date needs to be of Friday ******/
		if(cal.get(Calendar.DAY_OF_WEEK)==2){
			cal.add(Calendar.DATE, -3);
		}else{
			cal.add(Calendar.DATE, -1);
		}
		
		List<FileProp> uploadedFiles=new ArrayList<FileProp>();
		/* read log line by line */
		
		String uploadText="Files uploaded to CITI on "+ dmyFormat.format(cal.getTime()) +" are as follows:-";
		
		while ((strLine = br.readLine()) != null)   {
			/* parse strLine to obtain what you want */
			logger.debug(strLine);
			String [] str=strLine.split(" - ",-1);
			String dateInfo=str[0];
			String logDate=dateInfo.split("\\s+", -1)[0];
			if(logDate.equals(ymdFormat.format(cal.getTime()))){

				String [] attrs=str[1].split(",",-1);
				FileProp fp=new FileProp();
				fp.setFileName(attrs[0]);
				fp.setFileSize(attrs[1]);
				fp.setTimeTaken(attrs[2]);
				fp.setDate(attrs[3]);
				uploadedFiles.add(fp);
			}




		}

		br.close();

		map.put("Uploads", uploadedFiles);
		Document document=prepareEmailMsg(map,uploadText,downloadText);
		
		Email_Sender emailSender=new Email_Sender(); 
		String subject=femail_prop.getSuccessSubject()+" "+dmyFormat.format(cal.getTime());
		result=emailSender.sendHTMLEmail(femail_prop.getMailFrom(), femail_prop.getPassword(), femail_prop.getSuccessMailRecipientList(), 
				femail_prop.getSmtpHost(), femail_prop.getSmtpPort(), femail_prop.isSmtpAuth(), 
				femail_prop.isSmtpSslEnable(), subject, document);






		if(!result){

			throw new Mail_Exception("Mail not sent");


		}

		return result;
	}
	
public Document prepareEmailMsg(Map<String,List<FileProp>> map,String uploadText,String downloadText){
		

		Document document = new Document(DocumentType.XHTMLStrict);	

		

		/********** Writing files uploaded info [start]********/
		
		document.body.appendText(uploadText);
		document.body.appendChild(new Br());
		List<FileProp> uploadedfiles=map.get("Uploads");
		Table table=new Table();
		table.setBorder("1");
		table.setCellpadding("0.5");
		table = prepareHeader(table);
		table=prepareTableBody(table,uploadedfiles);
		
		document.body.appendChild(table);
		document.body.appendChild(new Br());
		/********** Writing files uploaded info [end]********/
		/********** Writing files downloaded info [start]********/
		
		document.body.appendText(downloadText);
		document.body.appendChild(new Br());
		List<FileProp> downloadedfiles=map.get("Downloads");
		table=new Table();
		table.setBorder("1");
		table.setCellpadding("0.5");
		table = prepareHeader(table);
		table=prepareTableBody(table,downloadedfiles);
		document.body.appendChild(table);
		/********** Writing files downloaded info [end]********/

		

		return document;
	}

	public Table prepareTableBody(Table table,List<FileProp> list ){
		int row=1;
		if(list.size()==0)
		{
			//System.out.println("Program list Size is NUll=========");
			Tr tr_row = new Tr();
			
			Td td_noRow = new Td();
			td_noRow.setColspan("5");
			tr_row.appendChild(td_noRow);
			td_noRow.appendChild(new Text("No Files transferred"));
			table.appendChild(tr_row);

		}
		else{

			for ( FileProp fp: list)
			{

				Tr tr_row = new Tr();

				Td td_sr_no = new Td();
				tr_row.appendChild(td_sr_no);
				td_sr_no.appendChild(new Text(row));

				Td td_fileName = new Td();
				tr_row.appendChild(td_fileName);
				td_fileName.appendChild(new Text(fp.getFileName()));

				Td td_fileSize = new Td();
				tr_row.appendChild(td_fileSize);
				td_fileSize.appendChild(new Text(fp.getFileSize()));

				Td td_Time = new Td();
				tr_row.appendChild(td_Time);
				td_Time.appendChild(new Text(fp.getTimeTaken()));

				

				table.appendChild(tr_row);
				row = row+1;

			}

		}
		return table;
	}


	public Table prepareHeader(Table table){

		Tr tr_row = new Tr();
		tr_row.setBgcolor("#2AA7FA");

		Td td_sr_no = new Td();
		tr_row.appendChild(td_sr_no);
		td_sr_no.appendChild(new Text("Sr. No"));


		Td td_fileName = new Td();
		tr_row.appendChild(td_fileName);
		td_fileName.appendChild(new Text("File Name"));


		Td td_fileSize = new Td();
		tr_row.appendChild(td_fileSize);
		td_fileSize.appendChild(new Text("File Size"));

		Td td_Time = new Td();
		tr_row.appendChild(td_Time);
		td_Time.appendChild(new Text("Time Taken"));

		/*Td td_Date = new Td();
		tr_row.appendChild(td_Date);
		td_Date.appendChild(new Text("Date"));*/

		table.appendChild(tr_row);
		return table;
	}

	public  void sendMailOnError(String message) throws Mail_Exception{
		String signature=Constants.signature+Constants.signature_constant;
		message=Constants.salutation+message+signature;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String subject=femail_prop.getErrorSubject()+" "+dateFormat.format(new Date());
		Email_Sender emailSender=new Email_Sender(); 
		boolean result=emailSender.sendEmail( femail_prop.getMailFrom(), femail_prop.getPassword(), femail_prop.getErrorMailReceipentList(), 
				femail_prop.getSmtpHost(), femail_prop.getSmtpPort(), femail_prop.isSmtpAuth(), 
				femail_prop.isSmtpSslEnable(), subject, message);




		if(!result){

			throw new Mail_Exception("Mail not sent ");


		}
		else{
			logger.info("Error mail has been sent");
		}


	}

	


}
