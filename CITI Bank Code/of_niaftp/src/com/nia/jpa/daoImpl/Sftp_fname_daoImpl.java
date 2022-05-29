package com.nia.jpa.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.nia.helper.ResourceManager;
import com.nia.jpa.dao.Sftp_fname_dao;
import com.nia.jpa.dto.Sftp_fname_dto;
import com.nia.jpa.exception.Sftp_fname_dao_Exception;


public class Sftp_fname_daoImpl implements Sftp_fname_dao  {

	final static Logger logger = Logger.getLogger(Sftp_fname_daoImpl.class);
	
	// Dev MySQL Query
	protected final String SQL_TPAFileName = 
	"SELECT fcr.outfile_name" +
	" FROM apps.fnd_concurrent_requests fcr, apps.fnd_conc_req_summary_v v " +
	" WHERE fcr.request_id = v.request_id " +
	" and fcr.status_code = 'C' " +
	" AND v.PROGRAM in ('NIA CITI NEFT HO') " +
	" and v.actual_start_date = " +
	" (select max(actual_start_date) " +
	" from fnd_conc_req_summary_v " +
	" where program_short_name = 'NIA_CITI_NEFT' " +
	" and status_code = 'C' " +
	" and trunc(actual_start_date) = TRUNC(SYSDATE-4)) " +
	" and rownum = 1"; 
	
	//protected final String SQL_TPAFileName = "SELECT fcr.outfile_name   FROM fnd_concurrent_requests fcr";
	
	protected static final int COLUMN_file_name=1;
	protected static final int NUMBER_OF_COLUMNS = 1;
	
	
	
	
	
	public Sftp_fname_dto findAll(Connection conn) throws Sftp_fname_dao_Exception {
		
		
		// declare variables
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			// construct the SQL statement
			
			if (logger.isTraceEnabled()) {
				logger.trace( "Executing : " + SQL_TPAFileName);
			}
			// prepare statement
			stmt = conn.prepareStatement( SQL_TPAFileName );
		
			rs = stmt.executeQuery();
			
			// fetch the results
			return fetchSingleResults(rs);
		}
		
		catch (SQLException _e) {
			logger.error( "Exception: " + _e.getMessage(), _e );
			throw new Sftp_fname_dao_Exception("SQLException occured while fetching the payment file name and location details. " 
			+ System.getProperty("line.separator")+
					"Error details - " +_e.getMessage() +System.getProperty("line.separator")+
					"Hence file transfer failed."+ System.getProperty("line.separator") +
					"Please check log file for more details on error. "
					+ System.getProperty("line.separator") +System.getProperty("line.separator"));
		}
		finally {
			ResourceManager.close(rs);
			ResourceManager.close(conn);
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			//ResourceManager.close(stmt);
		
		}
		
		
	}
	
	
	protected Sftp_fname_dto fetchSingleResults(ResultSet rs) throws SQLException
	{
		
		if (rs.next())
		{
			Sftp_fname_dto dto = new Sftp_fname_dto();	
			populateDto( dto, rs);
			return dto;
		}
		else {
			return null;
		}
		
		
	}
	
		/** 
	 * Populates a DTO with data from a ResultSet
	11 */
	protected void populateDto(Sftp_fname_dto dto, ResultSet rs) throws SQLException
	{
		
		dto.setFile_name( rs.getString( COLUMN_file_name ) );
				
	}

	
	

}
