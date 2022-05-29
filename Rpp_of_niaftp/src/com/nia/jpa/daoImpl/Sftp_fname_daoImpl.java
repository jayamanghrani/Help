package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nia.helper.Constants;
import com.nia.helper.ResourceManager;
import com.nia.jpa.dao.Sftp_fname_dao;
import com.nia.jpa.dto.Sftp_fname_dto;
import com.nia.jpa.exception.Sftp_fname_dao_Exception;


public class Sftp_fname_daoImpl implements Sftp_fname_dao  {
 
	final static Logger logger = Logger.getLogger(Sftp_fname_daoImpl.class);
	
	protected static final int COLUMN_file_name=1;
	protected static final int NUMBER_OF_COLUMNS = 1;
	
	public Sftp_fname_dto findAll(Connection con, String officeCode,
			String sourceName, String bankName) throws Sftp_fname_dao_Exception {
		
		// declare variables
		PreparedStatement stmt_filepath = null;
		ResultSet rs_filepath = null;
		String queryForFilePath=null;
		PreparedStatement stmt_fileprefix = null;
		ResultSet rs_fileprefix = null;
		String queryForFilePrefix=null;
		Sftp_fname_dto fileDetails=null;
		
		try {
			//Query : fetch fileName with Path
			queryForFilePath=getQueryForFilePath();
			stmt_filepath = con.prepareStatement(queryForFilePath);
			stmt_filepath.setString(1, officeCode.trim());
			stmt_filepath.setString(2, sourceName.trim());
			stmt_filepath.setString(3, bankName.trim());
			stmt_filepath.setString(4, officeCode.trim());
			stmt_filepath.setString(5, sourceName.trim());
			stmt_filepath.setString(6, bankName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : "+queryForFilePath);
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 1 : "+officeCode.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 2 : "+sourceName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 3 : "+bankName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 4 : "+officeCode.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 5 : "+sourceName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : param 6 : "+bankName.trim());
			rs_filepath = stmt_filepath.executeQuery();
			
			//Query : fetch file prefix and destination path
			queryForFilePrefix=getQueryForFilePrefix();
			stmt_fileprefix = con.prepareStatement(queryForFilePrefix);
			stmt_fileprefix.setString(1, officeCode.trim());
			stmt_fileprefix.setString(2, sourceName.trim());
			stmt_fileprefix.setString(3, bankName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch file Prefix : "+queryForFilePrefix);
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch file Prefix : param 1 : "+officeCode.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch file Prefix : param 2 : "+sourceName.trim());
			logger.info("Sftp_fname_daoImpl : findAll : Query to fetch file Prefix : param 3 : "+bankName.trim());
			rs_fileprefix = stmt_fileprefix.executeQuery();
			
			//check if both resultset are not empty
			if(null ==rs_fileprefix)
			{
				logger.error("Sftp_fname_daoImpl : findAll : file prefix and file destination path data is not fetched from query ");
				throw new Sftp_fname_dao_Exception("SQLException occured while fetching the payment file prefix and file destination path details. " 
						+ System.getProperty("line.separator")+
								"Error details - Please check the Query for fetching File prefix and path with parameter passed." +
								"Hence file transfer failed."+ System.getProperty("line.separator") +
								"Please check log file for more details on error. "
								+ System.getProperty("line.separator") +System.getProperty("line.separator"));
				
			}
			
			// fetch the results
			fileDetails=fetchFilePath(rs_filepath);
			fileDetails=fetchFilePrefix(rs_fileprefix,fileDetails);
			return fileDetails;
		}
		
		catch (SQLException _e) {
			logger.error("Sftp_fname_daoImpl : findAll : Exception: "+ _e.getMessage());
			throw new Sftp_fname_dao_Exception("SQLException occured while fetching the payment file name and location details. " 
			+ System.getProperty("line.separator")+
					"Error details - " +_e.getMessage() +System.getProperty("line.separator")+
					"Hence file transfer failed."+ System.getProperty("line.separator") +
					"Please check log file for more details on error. "
					+ System.getProperty("line.separator") +System.getProperty("line.separator"));
		}
		finally {
			ResourceManager.close(rs_filepath);
			ResourceManager.close(rs_fileprefix);
			ResourceManager.close(con);
			try {
				stmt_filepath.close();
				stmt_fileprefix.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			//ResourceManager.close(stmt);
		
		}
		
		
	}
	public String getQueryForFilePath()
	{
		String fetchQuery=null;
		fetchQuery=Constants.QUERY_FETCH_FILENAME_FROM_SERVER.COMMON_QUERY_TO_GET_FILE_PATH;
		return fetchQuery;
	}
	public String getQueryForFilePrefix()
	{
		String fetchQuery=null;
		fetchQuery=Constants.QUERY_FETCH_FILENAME_FROM_SERVER.COMMON_QUERY_TO_GET_FILE_PREFIX;
		return fetchQuery;
	}
	
	protected Sftp_fname_dto fetchFilePath(ResultSet rs_filepath) throws SQLException
	{
		
		if (rs_filepath.next())
		{
			Sftp_fname_dto dto = new Sftp_fname_dto();	
			populateDto( dto, rs_filepath);
			return dto;
		}
		else {
			return null;
		}
	}
	/*** Populates a DTO with data from a ResultSet ***/
	protected void populateDto(Sftp_fname_dto dto, ResultSet rs) throws SQLException
	{
		  logger.info("Sftp_fname_daoImpl : findAll : Query to fetch filename with path : "+(rs.getString(1)));
		dto.setFile_name(rs.getString(1));
	}
	protected Sftp_fname_dto fetchFilePrefix(ResultSet rs_fileprefix,Sftp_fname_dto fileDetail) throws SQLException
	{
		
		if (rs_fileprefix.next())
		{
			populateDtoForFilePrefix( fileDetail, rs_fileprefix);
			return fileDetail;
		}
		else {
			return null;
		}
	}
	
	protected void populateDtoForFilePrefix(Sftp_fname_dto dto, ResultSet rs) throws SQLException
	{
		if(null != dto)
		{
		dto.setDestinationPath( rs.getString(1));
		dto.setFilePrefix(rs.getString(2));	
		dto.setBankWiseOutboundAPath(rs.getString(3));
		dto.setBankWisePlainFileBackupPath(rs.getString(4));
		dto.setBankWiseEncryptedFileBackupPath(rs.getString(5));
		dto.setRejectedFileCommonPath(rs.getString(6));
		
	   
		}
	}
	
	
	
		
	}

	

	

